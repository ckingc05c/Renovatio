package RenovatioMod.renovatio.item.custom.equipment.tool;

import RenovatioMod.renovatio.item.custom.equipment.weapon.melee.MeleeWeaponStats;

public enum MiningToolStats implements MeleeWeaponStats {

    PICKAXE(0, 1.2f, 0.0f, 0.0f),
    DRILL(-0.5f, 1.1f, 0.0f, 0.0f),
    HAMMER(3.0f, 0.4f, 1.0f, 0.5f),
    SHOVEL(0.5f, 1.0f, 0.0f, 0.0f),
    EXCAVATOR(2.5f, 0.8f, 1.0f, 0.0f),
    PAXEL(3.0f, 1.0f, 0.5f, 0.25f),
    LUMBER_AXE(5.0f, 0.8f, 0.5f, 0.25f);


    private final float attackDamageBonus;  // To be added to ToolMaterial.getAttackDamage()
    private final float attackSpeed;
    private final float reachOffset;        // Relative to base reach (3 blocks)
    private final float critDamage;


    MiningToolStats(float attackDamageBonus, float attackSpeed, float reachOffset, float critDamage) {
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

