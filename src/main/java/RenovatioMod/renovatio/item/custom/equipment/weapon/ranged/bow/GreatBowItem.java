package RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.bow;

import RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.fabric_extras.ranged_weapon.api.CustomBow;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

/**
 * This class represents a greatbow item.
 * It is a custom bow with a very long draw time and very high damage.
 */
public class GreatBowItem extends CustomBow {
    private static final RangedWeaponStats STATS = RangedWeaponStats.GREATBOW;



    /**
     * Constructs a new GreatBowItem.
     * @param material The tool material.
     */
    public GreatBowItem(ToolMaterial material)
    {
        super(new Item.Settings().maxDamage(STATS.getFinalDurability(material)), (material::getRepairIngredient));
        this.configure(new RangedConfig(STATS.getDrawspeed(), STATS.getDamage(material), STATS.getVelocity()));

    }
}
