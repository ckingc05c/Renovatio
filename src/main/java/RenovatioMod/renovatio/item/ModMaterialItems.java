package RenovatioMod.renovatio.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * This class is responsible for registering all the material items in the mod.
 */
public class ModMaterialItems {
    // Existing Items
    /**
     * The rose gold ingot item.
     */
    public static final Item ROSE_GOLD_INGOT = new Item(new FabricItemSettings());
    /**
     * The refined iron ingot item.
     */
    public static final Item REFINED_IRON_INGOT = new Item(new FabricItemSettings());
    /**
     * The nether rose ingot item.
     */
    public static final Item NETHER_ROSE_INGOT = new Item(new FabricItemSettings());
    /**
     * The bronze ingot item.
     */
    public static final Item BRONZE_INGOT = new Item(new FabricItemSettings());
    /**
     * The steel ingot item.
     */
    public static final Item STEEL_INGOT = new Item(new FabricItemSettings());
    /**
     * The obsidian steel ingot item.
     */
    public static final Item OBSIDIAN_STEEL_INGOT = new Item(new FabricItemSettings());
    /**
     * The ender steel ingot item.
     */
    public static final Item ENDER_STEEL_INGOT = new Item(new FabricItemSettings());
    /**
     * The void crystal gem item.
     */
    public static final Item VOID_CRYSTAL_GEM = new Item(new FabricItemSettings());

    // New Items from Table
    /**
     * The rose steel ingot item.
     */
    public static final Item ROSE_STEEL_INGOT = new Item(new FabricItemSettings());
    /**
     * The elemental void crystal gem item.
     */
    public static final Item ELEMENTAL_VOID_CRYSTAL_GEM = new Item(new FabricItemSettings());
    /**
     * The primordial void crystal gem item.
     */
    public static final Item PRIMORDIAL_VOID_CRYSTAL_GEM = new Item(new FabricItemSettings());

    /**
     * The bronze nugget item.
     */
    public static final Item BRONZE_NUGGET = new Item(new FabricItemSettings());
    /**
     * The steel nugget item.
     */
    public static final Item STEEL_NUGGET = new Item(new FabricItemSettings());


    /**
     * Registers all the material items.
     */
    public static void registerItems() {
        // Existing Registrations
        Registry.register(Registries.ITEM, new Identifier("renovatio", "rose_gold_ingot"), ROSE_GOLD_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "refined_iron_ingot"), REFINED_IRON_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "nether_rose_ingot"), NETHER_ROSE_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "bronze_ingot"), BRONZE_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "steel_ingot"), STEEL_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "obsidian_steel_ingot"), OBSIDIAN_STEEL_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "ender_steel_ingot"), ENDER_STEEL_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "void_crystal_gem"), VOID_CRYSTAL_GEM);

        // New Item Registrations
        Registry.register(Registries.ITEM, new Identifier("renovatio", "rose_steel_ingot"), ROSE_STEEL_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "elemental_void_crystal_gem"), ELEMENTAL_VOID_CRYSTAL_GEM);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "primordial_void_crystal_gem"), PRIMORDIAL_VOID_CRYSTAL_GEM);

        Registry.register(Registries.ITEM, new Identifier("renovatio", "bronze_nugget"), BRONZE_NUGGET);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "steel_nugget"), STEEL_NUGGET);
    }
}