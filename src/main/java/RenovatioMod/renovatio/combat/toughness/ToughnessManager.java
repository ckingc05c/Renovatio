package RenovatioMod.renovatio.combat.toughness;

import RenovatioMod.renovatio.attribute.ModAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ToughnessManager {
    private static final ConcurrentHashMap<UUID, Float> toughnessData = new ConcurrentHashMap<>();
    private static final String TOUGHNESS_NBT_KEY = "renovatio_toughness";

    public static float getToughness(LivingEntity entity) {
        return toughnessData.getOrDefault(entity.getUuid(), 0.0f);
    }

    public static void setToughness(LivingEntity entity, float value) {
        float maxToughness = (float) entity.getAttributeValue(ModAttributes.GENERIC_MAX_TOUGHNESS);
        toughnessData.put(entity.getUuid(), Math.max(0, Math.min(value, maxToughness)));
    }

    public static void readNbt(LivingEntity entity, NbtCompound nbt) {
        if (nbt.contains(TOUGHNESS_NBT_KEY)) {
            setToughness(entity, nbt.getFloat(TOUGHNESS_NBT_KEY));
        } else {
            // Initialize toughness to max if it's not in the NBT
            setToughness(entity, (float) entity.getAttributeValue(ModAttributes.GENERIC_MAX_TOUGHNESS));
        }
    }

    public static void writeNbt(LivingEntity entity, NbtCompound nbt) {
        nbt.putFloat(TOUGHNESS_NBT_KEY, getToughness(entity));
    }
}