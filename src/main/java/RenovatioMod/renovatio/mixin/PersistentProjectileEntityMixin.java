package RenovatioMod.renovatio.mixin;

import RenovatioMod.renovatio.effects.StageEffects;
import RenovatioMod.renovatio.stage.Stage;
import RenovatioMod.renovatio.stage.StageManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {

    @Inject(method = "setOwner", at = @At("TAIL"))
    private void onSetOwner(@Nullable Entity owner, CallbackInfo ci) {
        if (!(owner instanceof LivingEntity shooter)) return;
        if (shooter.getWorld().isClient) return;

        // Skip if shooter is a player
        if (shooter instanceof PlayerEntity) return;

        Stage stage = StageManager.get((ServerWorld) shooter.getWorld()).getStage();
        double multiplier = StageEffects.getDamageModifier(stage);

        PersistentProjectileEntity self = (PersistentProjectileEntity)(Object)this;
        self.setDamage(self.getDamage() * multiplier);
    }
}
