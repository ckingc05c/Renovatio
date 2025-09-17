package RenovatioMod.renovatio.item.custom.trinket.underarmor;

import RenovatioMod.renovatio.item.custom.equipment.armor.ModArmorMaterials;
import RenovatioMod.renovatio.item.custom.equipment.armor.plate.CustomArmorItem;
import RenovatioMod.renovatio.item.custom.trinket.underarmor.underarmor_c.UnderArmorChest;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.minecraft.item.ArmorItem.Type.HELMET;

public class ModUnderArmor {
    public static final UnderArmorChest LEATHER_UNDERARMOR_CHEST = new UnderArmorChest(new FabricItemSettings());





    public static void registerItems(){
        Registry.register(Registries.ITEM, new Identifier("renovatio", "leather_under_chest"),  LEATHER_UNDERARMOR_CHEST);

    }
}
