package RenovatioMod.renovatio.item;

import RenovatioMod.renovatio.item.custom.equipment.ModMeleeWeapons;
import RenovatioMod.renovatio.item.custom.equipment.ModPlateArmor;
import RenovatioMod.renovatio.item.custom.equipment.ModRangedWeapons;

public class ModItems {


    public static void registerItems() {
        ModMeleeWeapons.registerItems();
        ModRangedWeapons.registerItems();
        ModMaterialItems.registerItems();
        ModPlateArmor.registerItems();


    }
}
