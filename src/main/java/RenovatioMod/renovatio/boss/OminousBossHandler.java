package RenovatioMod.renovatio.boss;

import RenovatioMod.renovatio.stage.Stage;
import RenovatioMod.renovatio.stage.StageManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

/**
 * This class handles the logic for Ominous Bosses.
 * Ominous Bosses are stronger versions of vanilla bosses that can be spawned under certain conditions.
 */
public class OminousBossHandler {
    /**
     * The NBT tag used to mark an entity as an Ominous Boss.
     */
    public static final String TAG = "RenovatioOminous";

    /**
     * Checks if an entity should be an Ominous Boss.
     * @param entity The entity to check.
     * @return True if the entity should be an Ominous Boss, false otherwise.
     */
    public static boolean shouldApplyOminous(LivingEntity entity) {
        if (!(entity.getWorld() instanceof ServerWorld world)) return false;

        StageManager manager = StageManager.get(world);
        if (manager.getStage() != Stage.CHAMPION) return false;

        // Get nearest player who may have spawned the boss
        PlayerEntity nearest = world.getClosestPlayer(entity, 32);
        if (nearest == null || !nearest.hasStatusEffect(StatusEffects.BAD_OMEN)) return false;

        return true;
    }

    /**
     * Applies the Ominous modifiers to an entity.
     * @param entity The entity to apply the modifiers to.
     */
    public static void applyOminousModifiers(LivingEntity entity) {
        if (entity.getWorld().isClient) return;

        NbtCompound nbt = new NbtCompound();
        entity.writeCustomDataToNbt(nbt);
        nbt.putBoolean(TAG, true);
        entity.readCustomDataFromNbt(nbt);

        entity.setCustomName(Text.literal("Ominous " + entity.getType().getName().getString()));
        entity.setCustomNameVisible(true);


        // Optional: scale HP or other stats based on type
        if (entity instanceof WitherEntity || entity instanceof EnderDragonEntity) {
            entity.setHealth(entity.getMaxHealth()*4f);
        }
    }

    /**
     * Checks if an entity is an Ominous Boss.
     * @param entity The entity to check.
     * @return True if the entity is an Ominous Boss, false otherwise.
     */
    public static boolean isOminous(Entity entity) {
        if (!(entity instanceof LivingEntity living)) return false;
        NbtCompound nbt = new NbtCompound();
        living.writeCustomDataToNbt(nbt);
        return nbt.getBoolean(TAG);
    }
}
