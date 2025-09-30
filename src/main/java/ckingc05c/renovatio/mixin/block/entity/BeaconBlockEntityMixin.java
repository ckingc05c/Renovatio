package ckingc05c.renovatio.mixin.block.entity;

import ckingc05c.renovatio.effect.status.ModStatusEffect;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeaconBlockEntity.class)
public class BeaconBlockEntityMixin {

    @Redirect(
            method = "applyPlayerEffects",
            at = @At(
                    value = "NEW",
                    target = "net/minecraft/entity/effect/StatusEffectInstance"
            )
    )
    private static StatusEffectInstance redirectResistanceEffect(StatusEffect effect, int duration, int amplifier, boolean ambient, boolean showParticles) {
        if (effect == StatusEffects.RESISTANCE) {
            // If the game tries to create a Resistance effect, create our Fortification effect instead.
            return new StatusEffectInstance(ModStatusEffect.FORTIFICATION_EFFECT, duration, amplifier, ambient, showParticles);
        }
        // Otherwise, create the original effect as normal.
        return new StatusEffectInstance(effect, duration, amplifier, ambient, showParticles);
    }
}