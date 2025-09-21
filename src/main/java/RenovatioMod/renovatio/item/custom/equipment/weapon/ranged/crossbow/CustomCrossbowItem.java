package RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.crossbow;

import RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;

/**
 * This class represents a custom crossbow item.
 * It is a base class for other crossbows.
 */
public class CustomCrossbowItem extends CrossbowItem {
    private static final RangedWeaponStats STATS = RangedWeaponStats.CROSSBOW;

    /**
     * Constructs a new CustomCrossbowItem.
     * @param settings The item settings.
     */
    public CustomCrossbowItem(Item.Settings settings) {
        super(settings);
    }
}
