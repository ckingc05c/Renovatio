package RenovatioMod.renovatio.item.custom;

public enum WeaponStats {
    SWORD(3, 1.6f, 0.0f, 0.0f),
    LONG_SWORD(4, 1.4f, 0.25f, 0.25f),
    SHORT_SWORD(2, 2.0f, -0.25f, -0.10f),
    CLAYMORE(5, 1.2f, 0.5f, 0.50f),
    CUTLASS(3, 1.5f, 0.0f, 0.15f),
    DAGGER(1.5f, 2.4f, -0.5f, -0.25f),
    AXE(5, 1.0f, 0.0f, 0.0f),
    BATTLE_AXE(7, 0.85f, 0.5f, 0.25f),
    HOE(0, 2.5f, -0.5f, 0.15f),
    SCYTHE(3, 1.2f, 1.0f, 0.30f),
    LANCE(7, 0.5f, 1.0f, 0.25f),      // Added based on table
    MACE(7, 0.5f, 0.75f, 1.0f);      // Added based on table

    private final float attackDamageBonus;  // To be added to ToolMaterial.getAttackDamage()
    private final float attackSpeed;
    private final float reachOffset;        // Relative to base reach (3 blocks)
    private final float critDamage;

    WeaponStats(float attackDamageBonus, float attackSpeed, float reachOffset, float critDamage) {
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
