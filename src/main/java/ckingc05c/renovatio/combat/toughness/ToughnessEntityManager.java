package ckingc05c.renovatio.combat.toughness;

import net.minecraft.entity.LivingEntity;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages all ToughnessEntity instances to ensure only one exists per LivingEntity.
 */
public class ToughnessEntityManager {

    private static final Map<UUID, ToughnessEntity> instances = new ConcurrentHashMap<>();

    /**
     * Gets the existing ToughnessEntity for a given LivingEntity, or creates a new one if it doesn't exist.
     * @param entity The LivingEntity.
     * @return The unique ToughnessEntity for the given entity.
     */
    public static ToughnessEntity get(LivingEntity entity) {
        return instances.computeIfAbsent(entity.getUuid(), u -> new ToughnessEntity(entity));
    }

    /**
     * Removes the ToughnessEntity from the manager when the LivingEntity is removed from the world.
     * @param entity The LivingEntity being removed.
     */
    public static void remove(LivingEntity entity) {
        instances.remove(entity.getUuid());
    }
}