package RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.bow;

import RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.ArrowPattern;
import RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.fabric_extras.ranged_weapon.api.CustomBow;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

/**
 * This class represents a twin bow item.
 * It is a custom bow that fires two arrows at once.
 */
public class TwinBowItem extends CustomBow {
    private static final RangedWeaponStats STATS = RangedWeaponStats.TWINBOW;

    private static final ArrowPattern arrowPattern = STATS.getPattern();



    /**
     * Constructs a new TwinBowItem.
     * @param material The tool material.
     */
    public TwinBowItem(ToolMaterial material)
    {
        super(new Item.Settings().maxDamage(STATS.getFinalDurability(material)), (material::getRepairIngredient));
        this.configure(new RangedConfig(STATS.getDrawspeed(), STATS.getDamage(material), STATS.getVelocity()));

    }

    /**
     * Called when the player stops using the bow.
     * @param stack The item stack.
     * @param world The world.
     * @param user The entity that used the bow.
     * @param remainingUseTicks The number of ticks remaining in the use action.
     */
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof final PlayerEntity player)) {
            return;
        }

        // --- 1. AMMO & POWER CALCULATION ---
        boolean isCreative = player.getAbilities().creativeMode;
        boolean hasInfinity = EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
        ItemStack ammoStack = player.getProjectileType(stack);

        if (ammoStack.isEmpty() && (isCreative || hasInfinity)) {
            ammoStack = new ItemStack(Items.ARROW);
        }
        if (ammoStack.isEmpty()) {
            return;
        }

        int charge = this.getMaxUseTime(stack) - remainingUseTicks;
        float power = BowItem.getPullProgress(charge);
        if (power < 0.1F) {
            return;
        }

        // --- 2. SERVER-SIDE FIRING LOGIC ---
        if (!world.isClient) {
            ArrowItem arrowItem = (ArrowItem) (ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW);
            PersistentProjectileEntity[] arrowsToFire;

            // --- CREATE ARROWS BASED ON PATTERN ---
            if (arrowPattern == ArrowPattern.DOUBLE_VERTICAL) {
                arrowsToFire = new PersistentProjectileEntity[2];
                arrowsToFire[0] = arrowItem.createArrow(world, ammoStack, player);
                arrowsToFire[1] = arrowItem.createArrow(world, ammoStack, player);
                arrowsToFire[1].setPosition(arrowsToFire[1].getX(), arrowsToFire[1].getY() + 0.5, arrowsToFire[1].getZ());
            } else { // Default to ArrowPattern.SINGLE
                arrowsToFire = new PersistentProjectileEntity[1];
                arrowsToFire[0] = arrowItem.createArrow(world, ammoStack, player);
            }

            // --- CONFIGURE AND SPAWN EACH ARROW ---
            // Use a standard for-loop to get the index 'i' for pickup rules
            for (int i = 0; i < arrowsToFire.length; i++) {
                PersistentProjectileEntity arrowEntity = arrowsToFire[i];
                arrowEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, power * STATS.getVelocity(), 1.0F);

                if (power >= 1.0F) {
                    arrowEntity.setCritical(true);
                }

                // Apply enchantments
                int powerLevel = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                if (powerLevel > 0) {
                    arrowEntity.setDamage(arrowEntity.getDamage() + (double)powerLevel * 0.5 + 0.5);
                }
                int punchLevel = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                if (punchLevel > 0) {
                    arrowEntity.setPunch(punchLevel);
                }
                if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                    arrowEntity.setOnFireFor(100);
                }

                // --- REVISED PICKUP LOGIC ---
                // For DOUBLE_VERTICAL, the second arrow (i > 0) is never pickup-able
                if (arrowPattern == ArrowPattern.DOUBLE_VERTICAL && i > 0) {
                    arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                } else {
                    // Apply vanilla pickup rules for the first arrow (or single shot)
                    if (isCreative) {
                        arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    } else if (hasInfinity && ammoStack.isOf(Items.ARROW)) {
                        // Infinity only makes regular arrows non-pickup-able
                        arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    } else if (ammoStack.isOf(Items.SPECTRAL_ARROW) || ammoStack.getItem() instanceof net.minecraft.item.TippedArrowItem) {
                        // Spectral and Tipped arrows are never pickup-able in survival
                        arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    } else {
                        // Default survival behavior: arrow can be picked up
                        arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                    }
                }

                world.spawnEntity(arrowEntity);
            }

            // --- ADD DURABILITY DAMAGE ---
            stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));

            // --- FINISHING TOUCHES ---
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + power * 0.5F);

            // --- REVISED AMMO CONSUMPTION ---
            // Infinity enchantment only saves regular arrows
            if (!isCreative && !(hasInfinity && ammoStack.isOf(Items.ARROW))) {
                ammoStack.decrement(1);
                if (ammoStack.isEmpty()) {
                    player.getInventory().removeOne(ammoStack);
                }
            }
            player.incrementStat(Stats.USED.getOrCreateStat(this));
        }
    }
}
