package RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.bow;

import RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.fabric_extras.ranged_weapon.api.CustomBow;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class CustomBowItem extends CustomBow {
    private static final RangedWeaponStats STATS = RangedWeaponStats.BOW;



    public CustomBowItem(ToolMaterial material)
    {
        super(new Item.Settings().maxDamage(STATS.getFinalDurability(material)), (material::getRepairIngredient));
        this.configure(new RangedConfig(STATS.getDrawspeed(), STATS.getDamage(material), STATS.getVelocity()));

    }

}
