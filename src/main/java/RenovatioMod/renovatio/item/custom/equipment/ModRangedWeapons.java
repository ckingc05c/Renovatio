package RenovatioMod.renovatio.item.custom.equipment;

import RenovatioMod.renovatio.item.ModToolMaterials;
import RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.bow.LongBowItem;
import RenovatioMod.renovatio.item.custom.equipment.weapon.ranged.bow.TwinBowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRangedWeapons {
    public static final Item WOODEN_LONGBOW = new LongBowItem(ToolMaterials.WOOD);
    public static final Item GOLDEN_LONGBOW = new LongBowItem(ToolMaterials.GOLD);
    public static final Item IRON_LONGBOW = new LongBowItem(ToolMaterials.IRON);
    public static final Item REFINED_IRON_LONGBOW = new LongBowItem(ModToolMaterials.REFINED_IRON);

    public static final Item VOID_CRYSTAL_LONGBOW = new LongBowItem(ModToolMaterials.VOID_CRYSTAL);
    public static final Item DIAMOND_LONGBOW = new LongBowItem(ToolMaterials.DIAMOND);
    public static final Item NETHERITE_LONGBOW = new LongBowItem(ToolMaterials.NETHERITE);
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
