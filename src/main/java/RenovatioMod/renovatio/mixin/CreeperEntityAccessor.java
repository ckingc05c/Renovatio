package RenovatioMod.renovatio.mixin;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreeperEntity.class)
public interface CreeperEntityAccessor {

    // setFuseTime is no longer needed.

    @Accessor("explosionRadius")
    void setExplosionRadius(int explosionRadius);

    @Accessor("CHARGED")
    static TrackedData<Boolean> getChargedTrackedData() {
        throw new AssertionError();
    }
}