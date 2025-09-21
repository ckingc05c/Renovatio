package RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.crossbow;

import RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;

/**
 * This class represents a burst crossbow item.
 * It is a custom crossbow that fires a burst of arrows.
 */
public class BurstCrossbowItem extends CrossbowItem {
    private static final RangedWeaponStats STATS = RangedWeaponStats.BURST_CROSSBOW;

    /**
     * Constructs a new BurstCrossbowItem.
     * @param settings The item settings.
     */
    public BurstCrossbowItem(Item.Settings settings) {
        super(settings);
    }
}
