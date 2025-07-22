package RenovatioMod.renovatio.mixin.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StatusEffect.class)
public class StatusEffectMixin {

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