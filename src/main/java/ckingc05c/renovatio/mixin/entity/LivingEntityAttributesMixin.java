package ckingc05c.renovatio.mixin.entity;

import ckingc05c.renovatio.attribute.ModAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityAttributesMixin {

    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void addToughnessAttribute(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        // This adds your custom attribute to every living entity's attribute set
        cir.getReturnValue().add(ModAttributes.GENERIC_MAX_TOUGHNESS);
    }
}