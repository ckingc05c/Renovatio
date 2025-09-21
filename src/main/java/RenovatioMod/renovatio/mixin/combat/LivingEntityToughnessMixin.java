package RenovatioMod.renovatio.mixin.combat;

import RenovatioMod.renovatio.combat.toughness.ToughnessManager;
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
        ToughnessManager.writeNbt((LivingEntity)(Object)this, nbt);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readToughnessFromNbt(NbtCompound nbt, CallbackInfo info) {
        ToughnessManager.readNbt((LivingEntity)(Object)this, nbt);
    }
}