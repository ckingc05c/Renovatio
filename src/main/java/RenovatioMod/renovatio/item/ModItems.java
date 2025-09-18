package RenovatioMod.renovatio.item;

import RenovatioMod.renovatio.item.custom.equipment.ModMeleeWeapons;
import RenovatioMod.renovatio.item.custom.equipment.ModPlateArmor;
import RenovatioMod.renovatio.item.custom.equipment.ModRangedWeapons;
import RenovatioMod.renovatio.item.custom.trinket.underarmor.ModUnderArmor;
import RenovatioMod.renovatio.item.custom.trinket.underarmor.underarmor_c.UnderArmorChest;

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
