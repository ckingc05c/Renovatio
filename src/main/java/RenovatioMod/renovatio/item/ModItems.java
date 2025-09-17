package RenovatioMod.renovatio.item;

import RenovatioMod.renovatio.item.custom.equipment.ModMeleeWeapons;
import RenovatioMod.renovatio.item.custom.equipment.ModPlateArmor;
import RenovatioMod.renovatio.item.custom.equipment.ModRangedWeapons;
import RenovatioMod.renovatio.item.custom.trinket.underarmor.ModUnderArmor;
import RenovatioMod.renovatio.item.custom.trinket.underarmor.underarmor_c.UnderArmorChest;

public class ModItems {


    public static void registerItems() {
        ModMeleeWeapons.registerItems();
        ModRangedWeapons.registerItems();
        ModMaterialItems.registerItems();
        ModPlateArmor.registerItems();
        ModUnderArmor.registerItems();



    }
}
