package ckingc05c.renovatio.item.custom.equipment;

import ckingc05c.renovatio.item.custom.equipment.armor.ModArmorMaterials;
import ckingc05c.renovatio.item.custom.equipment.armor.plate.CustomArmorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.minecraft.item.ArmorItem.Type.*;

/**
 * This class is responsible for registering all the plate armor items in the mod.
 */
public class ModPlateArmor {

    /**
     * The copper helmet item.
     */
    public static final CustomArmorItem COPPER_HELMET = new CustomArmorItem(ModArmorMaterials.COPPER, HELMET, new FabricItemSettings());

    /**
     * The copper chestplate item.
     */
    public static final CustomArmorItem COPPER_CHESTPLATE = new CustomArmorItem(ModArmorMaterials.COPPER, CHESTPLATE, new FabricItemSettings());

    /**
     * The copper leggings item.
     */
    public static final CustomArmorItem COPPER_LEGGINGS = new CustomArmorItem(ModArmorMaterials.COPPER, LEGGINGS, new FabricItemSettings());

    /**
     * The copper boots item.
     */
    public static final CustomArmorItem COPPER_BOOTS = new CustomArmorItem(ModArmorMaterials.COPPER, BOOTS, new FabricItemSettings());


    /**
     * Registers all the plate armor items.
     */
    public static void registerItems() {
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_helmet"), COPPER_HELMET);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_chestplate"), COPPER_CHESTPLATE);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_leggings"), COPPER_LEGGINGS);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_boots"), COPPER_BOOTS);

    }

}
