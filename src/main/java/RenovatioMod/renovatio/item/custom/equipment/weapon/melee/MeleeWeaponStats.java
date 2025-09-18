package RenovatioMod.renovatio.item.custom.equipment.weapon.melee;

/**
 * This interface defines the stats for a melee weapon.
 */
public interface MeleeWeaponStats {

    // These would be constants if defined here, but for dynamic per-weapon stats, use getters.

    /**
     * Base attack damage bonus that is added to ToolMaterial.getAttackDamage().
     * @return The attack damage bonus.
     */
    float getAttackDamageBonus();

    /**
     * The total attack speed (base + offset).
     * @return The total attack speed.
     */
    float getTotalAttackSpeed();

    /**
     * Offset applied to base attack speed.
     * @return The attack speed offset.
     */
    float getAttackSpeedOffset();

    /**
     * Multiplier or value added on a critical hit.
     * @return The critical damage.
     */
    float getCritDamage();

    /**
     * Offset to reach distance (default base is 3.0 blocks).
     * @return The reach offset.
     */
    float getReachOffset();

    /**
     * Returns the total reach including base (3.0) and offset.
     * @return The total reach.
     */
    default float getTotalReach() {
        return 3.0f + getReachOffset();
    }
}
