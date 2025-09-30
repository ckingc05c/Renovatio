package ckingc05c.renovatio.combat.toughness;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import ckingc05c.renovatio.Renovatio;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages the toughness attribute for entities that implement {@link ToughnessEntity}.
 * This class handles toughness data storage, damage calculations, regeneration,
 * and serialization. It uses static methods to provide a global access point for toughness-related logic.
 */
public class ToughnessManager {
    // Maps an entity's UUID to its current toughness value.
    private static final ConcurrentHashMap<UUID, Float> toughnessData = new ConcurrentHashMap<>();
    // Maps an entity's UUID to the world time of its last damage event. Used for regeneration delay.
    private static final ConcurrentHashMap<UUID, Long> lastDamageTime = new ConcurrentHashMap<>();
    // The key used to store and retrieve toughness data from NBT.
    private static final String TOUGHNESS_NBT_KEY = "renovatio_toughness";
    // The delay in ticks after taking damage before toughness can start regenerating. 5 seconds.
    private static final int REGEN_DELAY_TICKS = 100; // 5 seconds (20 ticks/sec)


    /**
     * Gets the current toughness for a given entity.
     * @param toughnessEntity The entity whose toughness to get.
     * @return The current toughness value, or 0.0f if not present.
     */
    public static float getToughness(ToughnessEntity toughnessEntity) {
        return toughnessData.getOrDefault(toughnessEntity.getUuid(), 0.0f);
    }

    /**
     * Sets the toughness for a given entity, clamping the value between 0 and the entity's max toughness.
     * If the toughness drops to 0 or below, the entity's toughness state is set to BROKEN.
     * @param toughnessEntity The entity whose toughness to set.
     * @param value The new toughness value.
     */
    public static void setToughness(ToughnessEntity toughnessEntity, float value) {
        float maxToughness = (float) toughnessEntity.getMaxToughness();
        float newToughness = Math.max(0, Math.min(value, maxToughness));
        toughnessData.put(toughnessEntity.getUuid(), newToughness);
        // If toughness is depleted, set the state to BROKEN.
        if (newToughness <= 0){
            toughnessEntity.setToughnessState(ToughnessState.BROKEN);
        }
    }

    /**
     * Records the time an entity last took damage to manage regeneration delay.
     * @param toughnessEntity The entity that took damage.
     */
    public static void recordDamage(ToughnessEntity toughnessEntity) {

        lastDamageTime.put(toughnessEntity.getUuid(), toughnessEntity.getWorld().getTime());
    }

    /**
     * Checks if an entity is eligible to regenerate its toughness.
     * Regeneration can occur if the entity's state allows recovery, the regeneration delay has passed,
     * and its current toughness is below its maximum.
     * @param toughnessEntity The entity to check.
     * @return True if the entity can regenerate toughness, false otherwise.
     */
    public static boolean canRegenerate(ToughnessEntity toughnessEntity) {
        // Check if the entity can regenerate
        if (!toughnessEntity.getToughnessState().canRecover()){
            return false;
        }
        long lastDamage = lastDamageTime.getOrDefault(toughnessEntity.getUuid(), 0L);
        // Check if the entity has taken damage recently and if their toughness is not full
        return toughnessEntity.getWorld().getTime() - lastDamage > REGEN_DELAY_TICKS &&
                getToughness(toughnessEntity) < (float) toughnessEntity.getMaxToughness();
    }

    /**
     * Regenerates a small amount of toughness for the entity.
     * If regeneration causes toughness to exceed its maximum, the state is set to UNBROKEN.
     * @param toughnessEntity The entity to regenerate.
     */
    public static void regenerate(ToughnessEntity toughnessEntity) {
        float regenAmount = 1.0f;
        float newToughness = getToughness(toughnessEntity) + regenAmount;
        setToughness(toughnessEntity, newToughness);
        // If toughness is restored, ensure the state is UNBROKEN.
        if (newToughness > toughnessEntity.getMaxToughness()){
            toughnessEntity.setToughnessState(ToughnessState.UNBROKEN);
        }
    }

    /**
     * Initializes an entity's toughness value to its maximum.
     * This is called when an entity is loaded into the world.
     */
    public static void initializeToughness(ToughnessEntity toughnessEntity) {
        if (!toughnessData.containsKey(toughnessEntity.getUuid())) {
            setToughness(toughnessEntity, (float) toughnessEntity.getMaxToughness());
        }
    }

    /**
     * Applies a separate instance of shield break damage to the entity.
     * Determines the severity of the break (BROKEN, FRACTURED, SHATTERED) based on overkill damage.
     * @param toughnessEntity The entity whose toughness broke.
     * @param originalSource The original damage source, to attribute the kill correctly.
     * @param overkill The amount of damage that exceeded the toughness threshold.
     */
    public static void handleBreak(ToughnessEntity toughnessEntity, DamageSource originalSource, float overkill) {
        float maxHealth = toughnessEntity.getMaxHealth();
        // Determine the broken state based on the ratio of overkill damage to max health.
        if (overkill / maxHealth > 0.5f) {
            toughnessEntity.setToughnessState(ToughnessState.SHATTERED);
        } else if (overkill / maxHealth > 0.25f) {
            toughnessEntity.setToughnessState(ToughnessState.FRACTURED);
        } else {
            toughnessEntity.setToughnessState(ToughnessState.BROKEN);
        }
        // Calculate and apply bonus damage upon breaking, modified by the new toughness state.
        float breakDamageHealthRatio = 0.25f;
        float damage = toughnessEntity.getMaxHealth() * breakDamageHealthRatio * toughnessEntity.getToughnessState().getDamageModifier();
        Renovatio.LOGGER.info("Toughness value is: {}", getToughness(toughnessEntity));
        Renovatio.LOGGER.info("Toughness state is: {}", toughnessEntity.getToughnessState().toString());

        toughnessEntity.dealBreakEffects(damage, originalSource);
    }



    /**
     * Reads toughness data from an NBT compound and applies it to the entity.
     * @param toughnessEntity The entity to apply the data to.
     * @param nbt The NBT compound to read from.
     */
    public static void readNbt(ToughnessEntity toughnessEntity, NbtCompound nbt) {
        if (nbt.contains(TOUGHNESS_NBT_KEY)) {
            setToughness(toughnessEntity, nbt.getFloat(TOUGHNESS_NBT_KEY));
        }
    }

    /**
     * Writes the entity's current toughness data to an NBT compound.
     * @param toughnessEntity The entity whose data to write.
     * @param nbt The NBT compound to write to.
     */
    public static void writeNbt(ToughnessEntity toughnessEntity, NbtCompound nbt) {
        nbt.putFloat(TOUGHNESS_NBT_KEY, getToughness(toughnessEntity));
    }

    /**
     * Calculates the damage to be dealt to an entity's health after considering its toughness.
     * This is the core logic for toughness-based damage mitigation.
     * @param toughnessEntity The defending entity.
     * @param initialAttackPower The raw damage of the incoming attack.
     * @param source The source of the damage.
     * @return The final damage that should be applied to the entity's health.
     */
    public static float handleDamage(ToughnessEntity toughnessEntity, float initialAttackPower, DamageSource source, float elementalMultiplier){
        float currentToughness = getToughness(toughnessEntity);
        float damageToHealth = initialAttackPower;
        ToughnessState state = toughnessEntity.getToughnessState();

        if (state.isBroken()){
            return damageToHealth * state.getDamageModifier();
        }

        float effectiveToughness = currentToughness;
        effectiveToughness *= state.getEffectiveToughnessModifier();
        effectiveToughness *= toughnessEntity.getResistanceEffectiveToughnessModifier(source);

        // Apply the elemental multiplier to the incoming attack power before toughness calculation
        float elementalAttackPower = initialAttackPower * elementalMultiplier;

        if (effectiveToughness > elementalAttackPower) {
            float toughnessDamage = elementalAttackPower;
            float healthDamageMultiplier = state.getDamageModifier();
            toughnessDamage *= toughnessEntity.getResistanceToughnessDamageReductionModifier(source);
            healthDamageMultiplier -= toughnessEntity.getDamageToHealthThrough(source);

            setToughness(toughnessEntity, currentToughness - toughnessDamage);
            damageToHealth = initialAttackPower * Math.max(0, healthDamageMultiplier);
        } else {
            setToughness(toughnessEntity, 0);
            // Overkill is now calculated against the elemental attack power
            float overkill = elementalAttackPower - effectiveToughness;
            damageToHealth = initialAttackPower;
            handleBreak(toughnessEntity, source, overkill); // Pass overkill to handleBreak
        }
        return damageToHealth;
    }
}