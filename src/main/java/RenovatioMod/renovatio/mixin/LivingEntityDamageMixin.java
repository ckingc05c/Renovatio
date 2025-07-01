package RenovatioMod.renovatio.mixin;

import RenovatioMod.renovatio.effects.StageEffects;
import RenovatioMod.renovatio.stage.Stage;
import RenovatioMod.renovatio.stage.StageManager;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Set;

@Mixin(LivingEntity.class)
public abstract class LivingEntityDamageMixin {

    @Unique
    private static final Set<RegistryKey<DamageType>> ENVIRONMENTAL_DAMAGE_TYPES = ImmutableSet.of(
            DamageTypes.FALLING_ANVIL, DamageTypes.CACTUS, DamageTypes.CRAMMING, DamageTypes.DROWN,
            DamageTypes.FALL, DamageTypes.FALLING_BLOCK, DamageTypes.FALLING_STALACTITE,
            DamageTypes.FLY_INTO_WALL, DamageTypes.FREEZE, DamageTypes.HOT_FLOOR,
            DamageTypes.IN_FIRE, DamageTypes.IN_WALL, DamageTypes.LAVA, DamageTypes.LIGHTNING_BOLT,
            DamageTypes.ON_FIRE, DamageTypes.OUT_OF_WORLD, DamageTypes.OUTSIDE_BORDER,
            DamageTypes.STALAGMITE, DamageTypes.STARVE, DamageTypes.SWEET_BERRY_BUSH,
            DamageTypes.UNATTRIBUTED_FIREBALL
    );

    @Unique
    private static final Set<RegistryKey<DamageType>> MAGIC_DAMAGE_TYPES = ImmutableSet.of(
            DamageTypes.MAGIC, DamageTypes.INDIRECT_MAGIC, DamageTypes.DRAGON_BREATH,
            DamageTypes.WITHER, DamageTypes.SONIC_BOOM
    );

    @Unique
    private static final Set<RegistryKey<DamageType>> PROJECTILE_DAMAGE_TYPES = ImmutableSet.of(
            DamageTypes.ARROW, DamageTypes.TRIDENT, DamageTypes.FIREBALL,
            DamageTypes.WITHER_SKULL, DamageTypes.FIREWORKS, DamageTypes.MOB_PROJECTILE
    );

    // Using THORNS here for the "physical" category as requested.
    @Unique
    private static final Set<RegistryKey<DamageType>> PHYSICAL_DAMAGE_TYPES = ImmutableSet.of(
            DamageTypes.THORNS
    );

    @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
    private float modifyDamage(float amount, DamageSource source) {
        LivingEntity target = (LivingEntity) (Object) this;

        // We only scale damage against players on the server.
        if (!(target instanceof PlayerEntity player) || target.getWorld().isClient()) {
            return amount;
        }

        ServerWorld world = (ServerWorld) player.getWorld();
        Stage stage = StageManager.get(world).getStage();
        Entity attacker = source.getAttacker();
        double multiplier = 1.0;

        // Get the registry key for the current damage source to check against our sets.
        RegistryKey<DamageType> damageTypeKey = source.getTypeRegistryEntry().getKey().orElse(null);
        if (damageTypeKey == null) {
            return amount; // Should not happen, but a good safe-check.
        }

        // --- Logic for all Damage Types ---
        if (ENVIRONMENTAL_DAMAGE_TYPES.contains(damageTypeKey)) {
            multiplier = StageEffects.getEnvironmentalDamageModifier(stage);
        } else if (MAGIC_DAMAGE_TYPES.contains(damageTypeKey)) {
            multiplier = StageEffects.getMagicDamageModifier(stage);
        } else if (PROJECTILE_DAMAGE_TYPES.contains(damageTypeKey)) {
            multiplier = StageEffects.getProjectileDamageModifier(stage);
        } else if (PHYSICAL_DAMAGE_TYPES.contains(damageTypeKey)) {
            // Thorns uses the melee modifier as requested.
            multiplier = StageEffects.getMeleeDamageModifier(stage);
        } else if (source.isOf(DamageTypes.EXPLOSION)) {
            multiplier = StageEffects.getExplosionDamageModifier(stage);
        } else if (attacker instanceof LivingEntity && !(attacker instanceof PlayerEntity)) {
            // This is our catch-all for any other direct mob attacks that are not
            // explosions, projectiles, or magic. This is effectively the melee category.
            // We do NOT use StageEffects.getMeleeDamageModifier(stage) here because
            // that damage is already scaled up via the mob's attributes. This is now a no-op.
            return amount;
        } else {
            // If it doesn't match any of our categories, don't modify the damage.
            return amount;
        }

        return (float) (amount * multiplier);
    }
}