package RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.bow;

import RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.fabric_extras.ranged_weapon.api.CustomBow;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

/**
 * This class represents a compound bow item.
 * It is a custom bow with a medium draw time and medium damage.
 */
public class CompoundBowItem extends CustomBow {
    private static final RangedWeaponStats STATS = RangedWeaponStats.COMPOUND_BOW;



    /**
     * Constructs a new CompoundBowItem.
     * @param material The tool material.
     */
    public CompoundBowItem(ToolMaterial material)
    {
        super(new Item.Settings().maxDamage(STATS.getFinalDurability(material)), (material::getRepairIngredient));
        this.configure(new RangedConfig(STATS.getDrawspeed(), STATS.getDamage(material), STATS.getVelocity()));

    }
}
