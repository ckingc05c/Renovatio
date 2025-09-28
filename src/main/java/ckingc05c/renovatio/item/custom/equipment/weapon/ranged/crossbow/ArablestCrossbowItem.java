package ckingc05c.renovatio.item.custom.equipment.weapon.ranged.crossbow;

import ckingc05c.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;

/**
 * This class represents an arbalest crossbow item.
 * It is a custom crossbow with a very long draw time and very high damage.
 */
public class ArablestCrossbowItem extends CrossbowItem {
    private static final RangedWeaponStats STATS = RangedWeaponStats.ARABLEST;

    /**
     * Constructs a new ArablestCrossbowItem.
     * @param settings The item settings.
     */
    public ArablestCrossbowItem(Item.Settings settings) {
        super(settings);
    }
}
