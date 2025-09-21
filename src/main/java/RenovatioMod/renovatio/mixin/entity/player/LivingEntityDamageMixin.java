package RenovatioMod.renovatio.mixin.entity.player;

import RenovatioMod.renovatio.stage.effects.StageEffects;
import RenovatioMod.renovatio.stage.Stage;
import RenovatioMod.renovatio.stage.StageManager;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(LivingEntity.class)
public abstract class LivingEntityDamageMixin {

    // Shadow methods to access vanilla functionality safely
    @Shadow public abstract boolean hasStatusEffect(net.minecraft.entity.effect.StatusEffect effect);
    @Shadow public abstract StatusEffectInstance getStatusEffect(net.minecraft.entity.effect.StatusEffect effect);

    @Shadow @Nullable private LivingEntity attacker;
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

    @Unique
    private static final Set<RegistryKey<DamageType>> PHYSICAL_DAMAGE_TYPES = ImmutableSet.of(
            DamageTypes.THORNS
    );

    @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
    private float modifyDamage(float amount, DamageSource source) {
        LivingEntity target = (LivingEntity) (Object) this;

        if (!(target instanceof PlayerEntity player) || target.getWorld().isClient()) {
            return amount;
        }

        // --- STAGE-BASED DAMAGE SCALING ---
        ServerWorld world = (ServerWorld) player.getWorld();
        Stage stage = StageManager.get(world).getStage();
        double multiplier = 1.0;

        RegistryKey<DamageType> damageTypeKey = source.getTypeRegistryEntry().getKey().orElse(null);
        if (damageTypeKey != null) {
            if (ENVIRONMENTAL_DAMAGE_TYPES.contains(damageTypeKey)) {
                multiplier = StageEffects.getEnvironmentalDamageModifier(stage);
            } else if (MAGIC_DAMAGE_TYPES.contains(damageTypeKey)) {
                multiplier = StageEffects.getMagicDamageModifier(stage);
            } else if (PROJECTILE_DAMAGE_TYPES.contains(damageTypeKey)) {
                multiplier = StageEffects.getProjectileDamageModifier(stage);
            } else if (PHYSICAL_DAMAGE_TYPES.contains(damageTypeKey)) {
                multiplier = StageEffects.getMeleeDamageModifier(stage);
            } else if (damageTypeKey.equals(DamageTypes.PLAYER_EXPLOSION) || damageTypeKey.equals(DamageTypes.EXPLOSION)) {
                multiplier = StageEffects.getExplosionDamageModifier(stage);
            }
        }

        // We don't scale melee damage here, as it's handled by attributes.
        // The check for `attacker instanceof LivingEntity` is no longer needed here
        // as the damage types cover those cases.

        float stageModifiedAmount = amount * (float) multiplier;

        // --- RESISTANCE CALCULATION ---
        // This is applied *after* the stage-based scaling.

        /*

        if (this.hasStatusEffect(StatusEffects.RESISTANCE)) {
            int amplifier = this.getStatusEffect(StatusEffects.RESISTANCE).getAmplifier();
            float reduction = (amplifier + 1) * 0.15f; // Your new 15% formula
            reduction = Math.min(reduction, 1.0f); // Cap at 100%
            return stageModifiedAmount * (1.0f - reduction);
        }
        */
        return stageModifiedAmount;
    }
    @Inject(method = "modifyAppliedDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;getAmplifier()I", ordinal = 0), cancellable = true)
    private void ResistanceCalculation(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        StatusEffectInstance resistance = entity.getStatusEffect(StatusEffects.RESISTANCE);

        if (resistance != null && !source.isIn(DamageTypeTags.BYPASSES_RESISTANCE)) {
            int effectLevel = resistance.getAmplifier() + 1;

            float multiplier;            if (effectLevel > 255) {
                // Beyond vanilla cap, treat as full immunity
                multiplier = 0.0F;
            } else {
                multiplier = (float) Math.pow(0.80F, effectLevel);
            }
            // Damage actually applied
            float finalDamage = amount * multiplier;

            // Damage resisted (the prevented portion)
            float resistedDamage = amount - finalDamage;

            // Update stats if resistedDamage is sane
            if (resistedDamage > 0.0F && resistedDamage < Float.MAX_VALUE) {
                if (entity instanceof ServerPlayerEntity player) {
                    // Player resisting damage
                    player.increaseStat(Stats.DAMAGE_RESISTED, Math.round(resistedDamage * 10.0F));
                } else if (source.getAttacker() instanceof ServerPlayerEntity attacker) {
                    // Player dealing resisted damage
                    attacker.increaseStat(Stats.DAMAGE_DEALT_RESISTED, Math.round(resistedDamage * 10.0F));
                }
            }

            // Ensure we never return a negative damage value
            cir.setReturnValue(Math.max(finalDamage, 0.0F));
        }
    }

}