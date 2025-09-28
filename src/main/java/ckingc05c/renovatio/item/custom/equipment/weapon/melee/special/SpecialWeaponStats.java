package ckingc05c.renovatio.item.custom.equipment.weapon.melee.special;

import ckingc05c.renovatio.item.custom.equipment.weapon.melee.MeleeWeaponStats;

/**
 * This enum contains the stats for the different special weapons.
 */
public enum SpecialWeaponStats implements MeleeWeaponStats {
        SWORD(3, 1.6f, 0.0f, 0.0f),
        LONG_SWORD(4, 1.4f, 0.25f, 0.25f),
        SHORT_SWORD(2, 2.0f, -0.25f, -0.10f),
        CLAYMORE(5, 1.0f, 0.75f, 0.50f),
        CUTLASS(3, 1.5f, 0.0f, 0.15f),
        DAGGER(1.5f, 2.4f, -0.5f, -0.25f);

        private final float attackDamageBonus;  // To be added to ToolMaterial.getAttackDamage()
        private final float attackSpeed;
        private final float reachOffset;        // Relative to base reach (3 blocks)
        private final float critDamage;

    /**
     * Constructs a new SpecialWeaponStats.
     * @param attackDamageBonus The attack damage bonus.
     * @param attackSpeed The attack speed.
     * @param reachOffset The reach offset.
     * @param critDamage The critical damage.
     */
    SpecialWeaponStats(float attackDamageBonus, float attackSpeed, float reachOffset, float critDamage) {
        this.attackDamageBonus = attackDamageBonus;
        this.attackSpeed = attackSpeed;
        this.reachOffset = reachOffset;
        this.critDamage = critDamage;
    }

    /**
     * Gets the attack damage bonus.
     * @return The attack damage bonus.
     */
    public float getAttackDamageBonus() {
        return attackDamageBonus;
    }

    /**
     * Gets the total attack speed.
     * @return The total attack speed.
     */
    public float getTotalAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Gets the attack speed offset.
     * @return The attack speed offset.
     */
    public float getAttackSpeedOffset() {
        return attackSpeed-4.0f;
    }

    /**
     * Gets the critical damage.
     * @return The critical damage.
     */
    public float getCritDamage() {
        return critDamage;
    }

    /**
     * Gets the reach offset.
     * @return The reach offset.
     */
    public float getReachOffset() {
        return reachOffset;
    }

    /**
     * Gets the total reach.
     * @return The total reach.
     */
    public float getTotalReach() {
            return 3.0f + reachOffset;
        }



}
