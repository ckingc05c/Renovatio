package ckingc05c.renovatio.item.custom.equipment;

import ckingc05c.renovatio.item.ModToolMaterials;
import ckingc05c.renovatio.item.custom.equipment.weapon.ranged.bow.LongBowItem;
import ckingc05c.renovatio.item.custom.equipment.weapon.ranged.bow.TwinBowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * This class is responsible for registering all the ranged weapons in the mod.
 */
public class ModRangedWeapons {
    /**
     * The wooden longbow item.
     */
    public static final Item WOODEN_LONGBOW = new LongBowItem(ToolMaterials.WOOD);
    /**
     * The golden longbow item.
     */
    public static final Item GOLDEN_LONGBOW = new LongBowItem(ToolMaterials.GOLD);
    /**
     * The iron longbow item.
     */
    public static final Item IRON_LONGBOW = new LongBowItem(ToolMaterials.IRON);
    /**
     * The refined iron longbow item.
     */
    public static final Item REFINED_IRON_LONGBOW = new LongBowItem(ModToolMaterials.REFINED_IRON);

    /**
     * The void crystal longbow item.
     */
    public static final Item VOID_CRYSTAL_LONGBOW = new LongBowItem(ModToolMaterials.VOID_CRYSTAL);
    /**
     * The diamond longbow item.
     */
    public static final Item DIAMOND_LONGBOW = new LongBowItem(ToolMaterials.DIAMOND);
    /**
     * The netherite longbow item.
     */
    public static final Item NETHERITE_LONGBOW = new LongBowItem(ToolMaterials.NETHERITE);
    /**
     * The iron twinbow item.
     */
    public static final Item IRON_TWINBOW = new TwinBowItem(ToolMaterials.IRON);


    /**
     * Registers all the items defined in this class.
     * This method should be called from your mod's main initializer.
     */
    public static void registerItems() {
        Registry.register(Registries.ITEM, new Identifier("renovatio", "wooden_longbow"), WOODEN_LONGBOW);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "golden_longbow"), GOLDEN_LONGBOW);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "iron_longbow"), IRON_LONGBOW);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "refined_iron_longbow"), REFINED_IRON_LONGBOW);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "diamond_longbow"), DIAMOND_LONGBOW);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "netherite_longbow"), NETHERITE_LONGBOW);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "void_crsytal_longbow"), VOID_CRYSTAL_LONGBOW);

        Registry.register(Registries.ITEM, new Identifier("renovatio", "iron_twinbow"), IRON_TWINBOW);


    }
}
