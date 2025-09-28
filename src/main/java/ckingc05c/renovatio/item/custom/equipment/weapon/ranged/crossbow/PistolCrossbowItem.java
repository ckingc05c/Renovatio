package ckingc05c.renovatio.item.custom.equipment.weapon.ranged.crossbow;

import ckingc05c.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;

/**
 * This class represents a pistol crossbow item.
 * It is a custom crossbow with a very short draw time and low damage.
 */
public class PistolCrossbowItem extends CrossbowItem {
    private static final RangedWeaponStats STATS = RangedWeaponStats.PISTOL_CROSSBOW;

    /**
     * Constructs a new PistolCrossbowItem.
     * @param settings The item settings.
     */
    public PistolCrossbowItem(Item.Settings settings) {
        super(settings);
    }
}
