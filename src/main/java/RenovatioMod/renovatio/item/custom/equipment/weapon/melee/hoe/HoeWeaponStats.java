package RenovatioMod.renovatio.item.custom.equipment.weapon.melee.hoe;

import RenovatioMod.renovatio.item.custom.equipment.weapon.melee.MeleeWeaponStats;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;

public enum HoeWeaponStats implements MeleeWeaponStats {
    HOE(0, 2.5f, 0.0f, 0.0f),
    SCYTHE(3, 1.2f, 1.0f, 0.30f);

        private final float attackDamageBonus;  // To be added to ToolMaterial.getAttackDamage()
        private final float attackSpeed;
        private final float reachOffset;        // Relative to base reach (3 blocks)
        private final float critDamage;

    HoeWeaponStats(float attackDamageBonus, float attackSpeed, float reachOffset, float critDamage) {
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
