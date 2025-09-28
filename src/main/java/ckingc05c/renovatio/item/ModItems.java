package ckingc05c.renovatio.item;

import ckingc05c.renovatio.item.custom.equipment.ModMeleeWeapons;
import ckingc05c.renovatio.item.custom.equipment.ModPlateArmor;
import ckingc05c.renovatio.item.custom.equipment.ModRangedWeapons;
import ckingc05c.renovatio.item.custom.trinket.underarmor.ModUnderArmor;

/**
 * This class is responsible for registering all the items in the mod.
 */
public class ModItems {


    /**
     * Registers all the items in the mod.
     */
    public static void registerItems() {
        ModMeleeWeapons.registerItems();
        ModRangedWeapons.registerItems();
        ModMaterialItems.registerItems();
        ModPlateArmor.registerItems();
        ModUnderArmor.registerItems();



    }
}
