package ckingc05c.renovatio.mixin.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * This mixin modifies the StatusEffect class to change the way instant health and instant damage work.
 */
@Mixin(StatusEffect.class)
public class StatusEffectMixin {

    /**
     * Redirects the heal method to modify the amount of health restored by instant health.
     * @param entity The entity being healed.
     * @param originalAmount The original amount of health to restore.
     * @param target The target of the status effect.
     * @param amplifier The amplifier of the status effect.
     */
    @Redirect(
            method = "applyUpdateEffect(Lnet/minecraft/entity/LivingEntity;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;heal(F)V")
    )
    private void modifyHealAmount(LivingEntity entity, float originalAmount, LivingEntity target, int amplifier) {
        if ((Object) this == StatusEffects.INSTANT_HEALTH) {
            int level = amplifier + 1;
            entity.heal(4.0f * level);
        } else {
            entity.heal(originalAmount);
        }
    }

    /**
     * Redirects the damage method to modify the amount of damage dealt by instant damage.
     * @param entity The entity being damaged.
     * @param source The damage source.
     * @param originalAmount The original amount of damage to deal.
     * @param target The target of the status effect.
     * @param amplifier The amplifier of the status effect.
     * @return True if the entity was damaged, false otherwise.
     */
    @Redirect(
            method = "applyUpdateEffect(Lnet/minecraft/entity/LivingEntity;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z")
    )
    private boolean modifyDamageAmount(LivingEntity entity, DamageSource source, float originalAmount, LivingEntity target, int amplifier) {
        if ((Object) this == StatusEffects.INSTANT_DAMAGE) {
            int level = amplifier + 1;
            return entity.damage(source, 6.0f * level);
        } else {
            return entity.damage(source, originalAmount);
        }
    }
}