package RenovatioMod.renovatio.mixin;

import RenovatioMod.renovatio.effects.StageEffects;
import RenovatioMod.renovatio.stage.Stage;
import RenovatioMod.renovatio.stage.StageManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity {

    protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(EntityType<? extends CreeperEntity> entityType, World world, CallbackInfo ci) {
        // Only apply changes on the server
        if (!world.isClient) {
            CreeperEntity self = (CreeperEntity) (Object) this;
            CreeperEntityAccessor accessor = (CreeperEntityAccessor) self;
            Stage stage = StageManager.get((ServerWorld) world).getStage();

            // --- Set Explosion Strength ---
            int newExplosionStrength = StageEffects.getCreeperExplosionStrength(stage);
            // Add bonus strength for charged creepers
            if (self.getDataTracker().get(CreeperEntityAccessor.getChargedTrackedData())) {
                newExplosionStrength += 3;
            }
            accessor.setExplosionRadius(newExplosionStrength);
        }
    }
}