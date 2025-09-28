package ckingc05c.renovatio.item.custom.equipment.weapon.ranged.bow;

import ckingc05c.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.fabric_extras.ranged_weapon.api.CustomBow;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

/**
 * This class represents a custom bow item.
 * It is a base class for other bows.
 */
public class CustomBowItem extends CustomBow {
    private static final RangedWeaponStats STATS = RangedWeaponStats.BOW;



    /**
     * Constructs a new CustomBowItem.
     * @param material The tool material.
     */
    public CustomBowItem(ToolMaterial material)
    {
        super(new Item.Settings().maxDamage(STATS.getFinalDurability(material)), (material::getRepairIngredient));
        this.configure(new RangedConfig(STATS.getDrawspeed(), STATS.getDamage(material), STATS.getVelocity()));

    }

}
