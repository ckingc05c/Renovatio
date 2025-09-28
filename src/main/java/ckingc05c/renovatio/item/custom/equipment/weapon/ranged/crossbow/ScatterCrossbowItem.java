package ckingc05c.renovatio.item.custom.equipment.weapon.ranged.crossbow;

import ckingc05c.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;

/**
 * This class represents a scatter crossbow item.
 * It is a custom crossbow that fires multiple arrows at once.
 */
public class ScatterCrossbowItem extends CrossbowItem {
    private static final RangedWeaponStats STATS = RangedWeaponStats.SCATTER_CROSSBOW;

    /**
     * Constructs a new ScatterCrossbowItem.
     * @param settings The item settings.
     */
    public ScatterCrossbowItem(Item.Settings settings) {
        super(settings);
    }
}
