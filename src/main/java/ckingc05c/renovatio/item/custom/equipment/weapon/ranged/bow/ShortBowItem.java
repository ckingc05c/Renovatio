package ckingc05c.renovatio.item.custom.equipment.weapon.ranged.bow;

import ckingc05c.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.fabric_extras.ranged_weapon.api.CustomBow;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

/**
 * This class represents a shortbow item.
 * It is a custom bow with a shorter draw time and lower damage.
 */
public class ShortBowItem extends CustomBow {
    private static final RangedWeaponStats STATS = RangedWeaponStats.SHORTBOW;



    /**
     * Constructs a new ShortBowItem.
     * @param material The tool material.
     */
    public ShortBowItem(ToolMaterial material)
    {
        super(new Item.Settings().maxDamage(STATS.getFinalDurability(material)), (material::getRepairIngredient));
        this.configure(new RangedConfig(STATS.getDrawspeed(), STATS.getDamage(material), STATS.getVelocity()));

    }



}
