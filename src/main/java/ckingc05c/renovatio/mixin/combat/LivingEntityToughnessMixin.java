package ckingc05c.renovatio.mixin.combat;

import ckingc05c.renovatio.combat.toughness.ToughnessEntity;
import ckingc05c.renovatio.combat.toughness.ToughnessManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityToughnessMixin {

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeToughnessToNbt(NbtCompound nbt, CallbackInfo ci) {
        ToughnessEntity toughnessEntity = new ToughnessEntity((LivingEntity)(Object)this);
        ToughnessManager.writeNbt(toughnessEntity, nbt);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readToughnessFromNbt(NbtCompound nbt, CallbackInfo info) {
        ToughnessEntity toughnessEntity = new ToughnessEntity((LivingEntity)(Object)this);
        ToughnessManager.readNbt(toughnessEntity, nbt);
    }
}