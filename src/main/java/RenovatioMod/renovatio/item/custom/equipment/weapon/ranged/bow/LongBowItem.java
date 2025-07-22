package RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.bow;

import RenovatioMod.renovatio.item.ModToolMaterials;
import RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.RangedWeaponStats;
import net.fabric_extras.ranged_weapon.api.CustomBow;
import net.fabric_extras.ranged_weapon.api.RangedConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;


public class LongBowItem extends CustomBow {
    private static final RangedWeaponStats STATS = RangedWeaponStats.LONGBOW;

    public LongBowItem(ToolMaterial material) {
        super(createSettings(material), material::getRepairIngredient);
        this.configure(new RangedConfig(
                STATS.getDrawspeed(),
                STATS.getDamage(material),
                STATS.getVelocity()
        ));
    }

    private static Item.Settings createSettings(ToolMaterial material) {
        Item.Settings settings = new Item.Settings().maxDamage(STATS.getFinalDurability(material));

        if (material == ToolMaterials.NETHERITE
                || material == ModToolMaterials.OBSIDIAN_STEEL // example check if custom
                || material == ModToolMaterials.VOID_CRYSTAL) {
            settings.fireproof();
        }

        return settings;
    }
}


