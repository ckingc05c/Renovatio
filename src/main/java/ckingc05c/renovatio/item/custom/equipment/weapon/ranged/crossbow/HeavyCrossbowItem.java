package ckingc05c.renovatio.item.custom.equipment.weapon.ranged.crossbow;

import ckingc05c.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;

/**
 * This class represents a heavy crossbow item.
 * It is a custom crossbow with a long draw time and high damage.
 */
public class HeavyCrossbowItem extends CrossbowItem {
    private static final RangedWeaponStats STATS = RangedWeaponStats.HEAVY_CROSSBOW;

    /**
     * Constructs a new HeavyCrossbowItem.
     * @param settings The item settings.
     */
    public HeavyCrossbowItem(Item.Settings settings) {
        super(settings);
    }
}
