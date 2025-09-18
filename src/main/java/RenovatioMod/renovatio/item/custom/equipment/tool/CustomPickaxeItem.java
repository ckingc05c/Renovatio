package RenovatioMod.renovatio.item.custom.equipment.tool;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

/**
 * This class represents a custom pickaxe item.
 */
public class CustomPickaxeItem extends PickaxeItem {
    /**
     * Constructs a new CustomPickaxeItem.
     * @param material The tool material.
     * @param attackDamage The attack damage.
     * @param attackSpeed The attack speed.
     * @param settings The item settings.
     */
    public CustomPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
