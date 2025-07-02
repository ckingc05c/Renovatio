package RenovatioMod.renovatio.mixin;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.DamageModifierStatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageModifierStatusEffect.class)
public class DamageModifierStatusEffectMixin {

    @Inject(method = "adjustModifierAmount", at = @At("HEAD"), cancellable = true)
    private void adjustModifierToBeMultiplicative(int amplifier, EntityAttributeModifier modifier, CallbackInfoReturnable<Double> cir) {
        // Get the instance of the effect (e.g., Strength, Weakness)
        Object effect = this;
        int level = amplifier + 1;

        if (effect == StatusEffects.STRENGTH) {
            // New formula: 30% increase per level
            // We set the return value and cancel the original method.
            cir.setReturnValue(0.30 * level);
        } else if (effect == StatusEffects.WEAKNESS) {
            // New formula: -40% per level
            cir.setReturnValue(-0.40 * level);
        }
    }
}