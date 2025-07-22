package RenovatioMod.renovatio.item.custom.equipment.tool;

public interface MiningToolStats {

    // These would be constants if defined here, but for dynamic per-weapon stats, use getters.

    /**
     * Base attack damage bonus that is added to ToolMaterial.getAttackDamage().
     */
    float getAttackDamageBonus();

    /**
     * The total attack speed (base + offset).
     */
    float getTotalAttackSpeed();

    /**
     * Offset applied to base attack speed.
     */
    float getAttackSpeedOffset();


    /**
     * Offset to reach distance (default base is 3.0 blocks).
     */
    float getReachOffset();

    /**
     * Returns the total reach including base (3.0) and offset.
     */
    default float getTotalReach() {
        return 3.0f + getReachOffset();
    }
}

