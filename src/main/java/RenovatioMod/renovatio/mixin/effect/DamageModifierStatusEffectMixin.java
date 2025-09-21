package RenovatioMod.renovatio.mixin.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.DamageModifierStatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * This mixin modifies the DamageModifierStatusEffect class to change the way strength and weakness work.
 */
@Mixin(DamageModifierStatusEffect.class)
public class DamageModifierStatusEffectMixin {

    /**
     * Injects into the adjustModifierAmount method to change the way strength and weakness work.
     * @param amplifier The amplifier of the status effect.
     * @param modifier The entity attribute modifier.
     * @param cir The callback info.
     */
    @Inject(method = "adjustModifierAmount", at = @At("HEAD"), cancellable = true)
    private void adjustModifierToBeMultiplicative(int amplifier, EntityAttributeModifier modifier, CallbackInfoReturnable<Double> cir) {
        // Get the instance of the effect (e.g., Strength, Weakness)
        Object effect = this;
        int level = amplifier + 1;

        if (effect == StatusEffects.STRENGTH) {
            // New formula: 20% increase per level
            // We set the return value and cancel the original method.
            cir.setReturnValue(0.20 * level);
        } else if (effect == StatusEffects.WEAKNESS) {
            // New formula: -25% per level
            if (level < 4){
                cir.setReturnValue(-0.25 * level);
            } else {
                cir.setReturnValue(-0.99);
            }

        }
    }
}