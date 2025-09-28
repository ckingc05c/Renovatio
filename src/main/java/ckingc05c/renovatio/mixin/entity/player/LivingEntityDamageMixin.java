package ckingc05c.renovatio.mixin.entity.player;

import ckingc05c.renovatio.combat.ModDamageTypes;
import ckingc05c.renovatio.combat.toughness.ToughnessEntity;
import ckingc05c.renovatio.combat.toughness.ToughnessEntityManager;
import ckingc05c.renovatio.combat.toughness.ToughnessManager;
import ckingc05c.renovatio.stage.effects.StageEffects;
import ckingc05c.renovatio.stage.Stage;
import ckingc05c.renovatio.stage.StageManager;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
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

/**
 * This mixin modifies the damage taken by living entities.
 */
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

    /**
     * Modifies the damage taken by a living entity based on the current stage and other factors.
     * @param amount The original damage amount.
     * @param source The damage source.
     * @return The modified damage amount.
     */
    @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
    private float modifyDamage(float amount, DamageSource source) {
        LivingEntity target = (LivingEntity) (Object) this;

        ToughnessEntity targetToughness = ToughnessEntityManager.get(target);

        if (target.getWorld().isClient()) {
            return amount;
        }
        ToughnessManager.recordDamage(targetToughness);
        float initialAttackPower = amount;

        // --- STAGE-BASED DAMAGE SCALING (Players Only) ---
        if (target instanceof PlayerEntity) {
            initialAttackPower *= (float) getMultiplier(source, StageManager.get((ServerWorld) target.getWorld()).getStage());
        }
        // --- NEW: Check for Break Damage Types ---
        RegistryKey<DamageType> damageTypeKey = source.getTypeRegistryEntry().getKey().orElse(null);
        if (damageTypeKey != null &&
                (damageTypeKey.equals(ModDamageTypes.TOUGHNESS_BREAK) || damageTypeKey.equals(ModDamageTypes.WEAKNESS_BREAK))) {
            // If this is a break attack, it bypasses all toughness calculations.
            return initialAttackPower;
        }
        return ToughnessManager.handleDamage(targetToughness, initialAttackPower, source);
        /*
        // --- TOUGHNESS AND DAMAGE LAYERS ---
        float currentToughness = ToughnessManager.getToughness(targetToughness);
        float damageToHealth = initialAttackPower; // By default, health takes 100% of the damage

        if (currentToughness > 0) {
            StatusEffectInstance resistance = target.getStatusEffect(StatusEffects.RESISTANCE);
            boolean hasValidResistance = resistance != null && !source.isIn(DamageTypeTags.BYPASSES_RESISTANCE);
            float effectiveToughness = currentToughness;

            // Each level of Resistance increases effectiveToughness by 20%
            if (hasValidResistance) {
                int effectLevel = resistance.getAmplifier() + 1;
                effectiveToughness *= (1 + (effectLevel * 0.2f));
            }

            // If the shield can absorb the full hit
            if (effectiveToughness >= initialAttackPower) {
                float toughnessDamage = initialAttackPower;
                float healthDamageMultiplier = 0.9f; // Base 90% damage reduction

                if (hasValidResistance) {
                    int effectLevel = resistance.getAmplifier() + 1;
                    // Resistance reduces damage taken by Toughness
                    toughnessDamage *= (float) Math.pow(0.80f, effectLevel);
                    // Resistance also reduces the passthrough damage to health
                    healthDamageMultiplier -= (effectLevel * 0.05f);
                }

                ToughnessManager.setToughness(targetToughness, currentToughness - toughnessDamage);
                damageToHealth = initialAttackPower * Math.max(0, healthDamageMultiplier);

            } else { // If the shield breaks on this hit
                ToughnessManager.setToughness(targetToughness, 0);
                damageToHealth = initialAttackPower;
                ToughnessManager.handleBreak(targetToughness, source);
            }
        }
         return damageToHealth;
        */

    }

    @Unique
    private static double getMultiplier(DamageSource source, Stage stage) {
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
        return multiplier;
    }

    @Inject(method = "modifyAppliedDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;getAmplifier()I", ordinal = 0), cancellable = true)
    private void ResistanceCalculation(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        StatusEffectInstance resistance = entity.getStatusEffect(StatusEffects.RESISTANCE);
        ToughnessEntity toughnessEntity = ToughnessEntityManager.get(entity);

        if (resistance != null && !source.isIn(DamageTypeTags.BYPASSES_RESISTANCE)) {
            float baseResistanceModifer = toughnessEntity.getToughnessState().getResistanceScaling();
                int effectLevel = resistance.getAmplifier() + 1;
                float multiplier = (float) Math.pow(baseResistanceModifer, effectLevel);
                float finalDamage = amount * multiplier;
                float resistedDamage = amount - finalDamage;

                if (resistedDamage > 0.0F && resistedDamage < Float.MAX_VALUE) {
                    if (entity instanceof ServerPlayerEntity player) {
                        player.increaseStat(Stats.DAMAGE_RESISTED, Math.round(resistedDamage * 10.0F));
                    } else if (source.getAttacker() instanceof ServerPlayerEntity attacker) {
                        attacker.increaseStat(Stats.DAMAGE_DEALT_RESISTED, Math.round(resistedDamage * 10.0F));
                    }
                }
                cir.setReturnValue(Math.max(finalDamage, 0.0F));

        }
    }

}