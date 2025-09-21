package RenovatioMod.renovatio.mixin.entity.mob.creeper;

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

/**
 * This mixin modifies the CreeperEntity class to change the explosion strength based on the current stage.
 */
@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity {

    /**
     * Constructs a new CreeperEntityMixin.
     * @param entityType The entity type.
     * @param world The world.
     */
    protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Injects into the constructor of the CreeperEntity class to modify the explosion radius.
     * @param entityType The entity type.
     * @param world The world.
     * @param ci The callback info.
     */
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