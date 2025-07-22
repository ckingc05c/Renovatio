package RenovatioMod.renovatio.item.custom.equipment.weapon.melee.axe;

import RenovatioMod.renovatio.item.custom.equipment.weapon.melee.MeleeWeaponStats;

public enum AxeWeaponStats implements MeleeWeaponStats {

    AXE(5, 1.0f, 0.0f, 0.0f),
    BATTLE_AXE(7, 0.85f, 0.5f, 0.25f),
    GREAT_AXE(9, 0.7f, 1.0f, 0.5f),
    HATCHET(4, 1.05f, -0.25f, 0.0f);


    private final float attackDamageBonus;  // To be added to ToolMaterial.getAttackDamage()
    private final float attackSpeed;
    private final float reachOffset;        // Relative to base reach (3 blocks)
    private final float critDamage;

    AxeWeaponStats(float attackDamageBonus, float attackSpeed, float reachOffset, float critDamage) {
        this.attackDamageBonus = attackDamageBonus;
        this.attackSpeed = attackSpeed;
        this.reachOffset = reachOffset;
        this.critDamage = critDamage;
    }

    public float getAttackDamageBonus() {
        return attackDamageBonus;
    }

    public float getTotalAttackSpeed() {
        return attackSpeed;
    }
    public float getAttackSpeedOffset() {
        return attackSpeed-4.0f;
    }

    public float getCritDamage() {
        return critDamage;
    }

    public float getReachOffset() {
        return reachOffset;
    }

    public float getTotalReach() {
        return 3.0f + reachOffset;
    }
}
