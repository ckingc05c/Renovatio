package RenovatioMod.renovatio.item.custom.equipment;

import RenovatioMod.renovatio.item.custom.equipment.armor.ModArmorMaterials;
import RenovatioMod.renovatio.item.custom.equipment.armor.ModArmorMaterials.*;
import RenovatioMod.renovatio.item.custom.equipment.armor.plate.CustomArmorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.minecraft.item.ArmorItem.Type.*;

public class ModPlateArmor {

    public static final CustomArmorItem COPPER_HELMET = new CustomArmorItem(ModArmorMaterials.COPPER, HELMET, new FabricItemSettings());

    public static final CustomArmorItem COPPER_CHESTPLATE = new CustomArmorItem(ModArmorMaterials.COPPER, CHESTPLATE, new FabricItemSettings());

    public static final CustomArmorItem COPPER_LEGGINGS = new CustomArmorItem(ModArmorMaterials.COPPER, LEGGINGS, new FabricItemSettings());

    public static final CustomArmorItem COPPER_BOOTS = new CustomArmorItem(ModArmorMaterials.COPPER, BOOTS, new FabricItemSettings());


    public static void registerItems() {
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_helmet"), COPPER_HELMET);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_chestplate"), COPPER_CHESTPLATE);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_leggings"), COPPER_LEGGINGS);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_boots"), COPPER_BOOTS);

    }

}
