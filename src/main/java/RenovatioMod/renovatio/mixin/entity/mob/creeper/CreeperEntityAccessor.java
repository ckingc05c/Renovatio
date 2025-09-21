package RenovatioMod.renovatio.mixin.entity.mob.creeper;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * This mixin provides access to private fields in the CreeperEntity class.
 */
@Mixin(CreeperEntity.class)
public interface CreeperEntityAccessor {

    // setFuseTime is no longer needed.

    /**
     * Sets the explosion radius of the creeper.
     * @param explosionRadius The new explosion radius.
     */
    @Accessor("explosionRadius")
    void setExplosionRadius(int explosionRadius);

    /**
     * Gets the tracked data for the charged state of the creeper.
     * @return The tracked data for the charged state.
     */
    @Accessor("CHARGED")
    static TrackedData<Boolean> getChargedTrackedData() {
        throw new AssertionError();
    }
}