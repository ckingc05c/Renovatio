package RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.crossbow;

import RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

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
