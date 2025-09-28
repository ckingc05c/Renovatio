package ckingc05c.renovatio.item.custom.equipment.armor.plate;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

/**
 * This class represents a custom armor item.
 */
public class CustomArmorItem extends ArmorItem {

    /**
     * Constructs a new CustomArmorItem.
     * @param material The armor material.
     * @param type The armor type.
     * @param settings The item settings.
     */
    public CustomArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }
}
