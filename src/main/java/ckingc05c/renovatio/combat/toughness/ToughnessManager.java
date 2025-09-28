package ckingc05c.renovatio.combat.toughness;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import ckingc05c.renovatio.Renovatio;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ToughnessManager {
    private static final ConcurrentHashMap<UUID, Float> toughnessData = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<UUID, Long> lastDamageTime = new ConcurrentHashMap<>();
    private static final String TOUGHNESS_NBT_KEY = "renovatio_toughness";
    private static final int REGEN_DELAY_TICKS = 100; // 5 seconds (20 ticks/sec)


    public static float getToughness(ToughnessEntity toughnessEntity) {
        return toughnessData.getOrDefault(toughnessEntity.getUuid(), 0.0f);
    }

    public static void setToughness(ToughnessEntity toughnessEntity, float value) {
        float maxToughness = (float) toughnessEntity.getMaxToughness();
        float newToughness = Math.max(0, Math.min(value, maxToughness));
        toughnessData.put(toughnessEntity.getUuid(), newToughness);
        if (newToughness <= 0){
            toughnessEntity.setToughnessState(ToughnessState.BROKEN);
        }
    }


    public static void recordDamage(ToughnessEntity toughnessEntity) {

        lastDamageTime.put(toughnessEntity.getUuid(), toughnessEntity.getWorld().getTime());
    }

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

    public static void regenerate(ToughnessEntity toughnessEntity) {
        float regenAmount = 1.0f;
        float newToughness = getToughness(toughnessEntity) + regenAmount;
        setToughness(toughnessEntity, newToughness);
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
     * @param toughnessEntity The entity whose shield broke.
     * @param originalSource The original damage source, to attribute the kill correctly.
     */
    public static void handleBreak(ToughnessEntity toughnessEntity, DamageSource originalSource) {
        /*
        if (getToughness(toughnessEntity) > 0){
            toughnessEntity.setToughnessState(ToughnessState.UNBROKEN);
            System.out.println("Toughness State set to Ubroken");
            System.out.println("Current Toughness is: ");
            System.out.print(getToughness(toughnessEntity));
            return;
        }
         */
        float breakDamageHealthRatio = 0.25f;
        float damage = toughnessEntity.getMaxHealth() * breakDamageHealthRatio * toughnessEntity.getToughnessState().getDamageModifier();
        Renovatio.LOGGER.info("Toughness value is: {}", getToughness(toughnessEntity));
        Renovatio.LOGGER.info("Toughness state is: {}", toughnessEntity.getToughnessState().toString());

        toughnessEntity.dealBreakEffects(damage, originalSource);
    }



    public static void readNbt(ToughnessEntity toughnessEntity, NbtCompound nbt) {
        if (nbt.contains(TOUGHNESS_NBT_KEY)) {
            setToughness(toughnessEntity, nbt.getFloat(TOUGHNESS_NBT_KEY));
        }
    }

    public static void writeNbt(ToughnessEntity toughnessEntity, NbtCompound nbt) {
        nbt.putFloat(TOUGHNESS_NBT_KEY, getToughness(toughnessEntity));
    }

    public static float handleDamage(ToughnessEntity toughnessEntity, float initialAttackPower, DamageSource source){
        float currentToughness = getToughness(toughnessEntity);
        float damageToHealth = initialAttackPower; // By default, health takes 100% of the damage
        ToughnessState state = toughnessEntity.getToughnessState();

        if (state.isBroken()){
            return damageToHealth*state.getDamageModifier();
        }
        Renovatio.LOGGER.info("Current Toughness state is: {}", toughnessEntity.getToughnessState().toString());



        float effectiveToughness = currentToughness;
        //Base Increase in Toughness from the State
        effectiveToughness *= state.getEffectiveToughnessModifier();
        //Furhet Increase in Toughness from Resistnace based the State=
        effectiveToughness *= toughnessEntity.getResistanceEffectiveToughnessModifier(source);






        // If the shield can absorb the full hit
        if (effectiveToughness > initialAttackPower) {
            //Set Damage to Toughness to initial Attack Power
            float toughnessDamage = initialAttackPower;
            //Set the base passthrough Dame to health to the State's damage modifer
            float healthDamageMultiplier = state.getDamageModifier(); // Base 90% damage reduction
            // Resistance reduces damage taken by Toughness
            toughnessDamage *= toughnessEntity.getResistanceToughnessDamageReductionModifier(source);
            // Resistance also reduces the passthrough damage to health
            healthDamageMultiplier -= toughnessEntity.getDamageToHealthThrough(source);


            setToughness(toughnessEntity, currentToughness - toughnessDamage);
            damageToHealth = initialAttackPower * Math.max(0, healthDamageMultiplier);

        } else {
        // If the shield breaks on this hit
            setToughness(toughnessEntity, 0);
            //Ratio between Damage and Effective Toughness.


            damageToHealth = initialAttackPower;
            handleBreak(toughnessEntity, source);
        }

        return damageToHealth;

    }
}