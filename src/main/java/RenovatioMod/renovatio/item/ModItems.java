package RenovatioMod.renovatio.item;

import RenovatioMod.renovatio.block.functional.ModBlocks;
import RenovatioMod.renovatio.item.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {
    public static final Item ROSE_GOLD_INGOT = new Item(new FabricItemSettings()); // Add if not already defined
    public static final Item REFINED_IRON_INGOT = new Item(new FabricItemSettings());
    public static final Item NETHER_ROSE_INGOT = new Item(new FabricItemSettings()); // Add if not already defined
    public static final Item BRONZE_INGOT = new Item(new FabricItemSettings());
    public static final Item STEEL_INGOT = new Item(new FabricItemSettings());
    public static final Item OBSIDIAN_STEEL_INGOT = new Item(new FabricItemSettings());
    public static final Item VOID_CRYSTAL_GEM = new Item(new FabricItemSettings());



    public static final Item ROSE_GOLD_SWORD = new SwordItem(
            ModToolMaterials.ROSE_GOLD,
            3,  // 6 Total attack damage 3(Sword) + 2 (Material) + 1(Item)
            -2.4f,  // attack speed 4-2.4 = 1.6
            new FabricItemSettings()
    );

    public static final Item ROSE_GOLD_PICKAXE = new PickaxeItem(
            ModToolMaterials.ROSE_GOLD,
            0, // 3 Total attack damage 0(Pickaxe) + 2 (Material) + 1(Item)
            -2.8f, // attack speed
            new FabricItemSettings()

    );

    public static final Item ROSE_GOLD_BATTLEAXE = new BattleAxeItem(
            ModToolMaterials.ROSE_GOLD,
            new FabricItemSettings()

    );

    public static final Item ROSE_GOLD_LONGSWORD = new LongSwordItem(
            ModToolMaterials.ROSE_GOLD,
            new FabricItemSettings()

    );
    public static final Item REFINED_IRON_SWORD = new SwordItem(
            ModToolMaterials.REFINED_IRON,
            3,  // attack damage
            -2.4f,  // attack speed
            new FabricItemSettings()
    );

    public static final Item REFINED_IRON_LONGSWORD = new LongSwordItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    public static final Item REFINED_IRON_SHORTSWORD = new ShortSwordItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    public static final Item REFINED_IRON_CLAYMORE = new ClaymoreSwordItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    public static final Item REFINED_IRON_CUTLASS = new CutlassSwordItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    public static final Item REFINED_IRON_DAGGER = new DaggerSwordItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    public static final Item REFINED_IRON_AXE = new AxeItem(
            ModToolMaterials.REFINED_IRON,
            4,  // attack damage
            -2.8f,  // attack speed
            new FabricItemSettings()
    );

    public static final Item REFINED_IRON_BATTLEAXE = new BattleAxeItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    public static final Item REFINED_IRON_HOE = new HoeItem(
            ModToolMaterials.REFINED_IRON,
            0,  // attack damage
            -3.0f,  // attack speed
            new FabricItemSettings()
    );

    public static final Item REFINED_IRON_SCYTHE = new ScytheHoeItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    public static final Item COPPER_SWORD = new SwordItem(
            ModToolMaterials.COPPER,
            3,  // attack damage
            -2.4f,  // attack speed
            new FabricItemSettings()
    );

    public static final Item COPPER_LONGSWORD = new LongSwordItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    public static final Item COPPER_SHORTSWORD = new ShortSwordItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    public static final Item COPPER_CLAYMORE = new ClaymoreSwordItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    public static final Item COPPER_CUTLASS = new CutlassSwordItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    public static final Item COPPER_DAGGER = new DaggerSwordItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    public static final Item COPPER_AXE = new AxeItem(
            ModToolMaterials.COPPER,
            4,  // attack damage
            -2.8f,  // attack speed
            new FabricItemSettings()
    );

    public static final Item COPPER_BATTLEAXE = new BattleAxeItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    public static final Item COPPER_HOE = new HoeItem(
            ModToolMaterials.COPPER,
            0,  // attack damage
            -3.0f,  // attack speed
            new FabricItemSettings()
    );

    public static final Item COPPER_PICKAXE = new PickaxeItem(
            ModToolMaterials.COPPER,
            0,  // attack damage
            -2.8f,  // attack speed
            new FabricItemSettings()
    );

    public static final Item COPPER_SHOVEL = new ShovelItem(
            ModToolMaterials.COPPER,
            0.5f,  // attack damage
            -3.0f,  // attack speed
            new FabricItemSettings()
    );

    public static final Item COPPER_SCYTHE = new ScytheHoeItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );
    public static void registerItems() {
        // Already existing registrations
        Registry.register(Registries.ITEM, new Identifier("renovatio", "rose_gold_ingot"), ROSE_GOLD_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "rose_gold_sword"), ROSE_GOLD_SWORD);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "rose_gold_pickaxe"), ROSE_GOLD_PICKAXE);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "rose_gold_battleaxe"), ROSE_GOLD_BATTLEAXE);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "rose_gold_longsword"), ROSE_GOLD_LONGSWORD);

        // Refined Iron items registrations
        Registry.register(Registries.ITEM, new Identifier("renovatio", "refined_iron_sword"), REFINED_IRON_SWORD);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "refined_iron_longsword"), REFINED_IRON_LONGSWORD);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "refined_iron_shortsword"), REFINED_IRON_SHORTSWORD);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "refined_iron_claymore"), REFINED_IRON_CLAYMORE);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "refined_iron_cutlass"), REFINED_IRON_CUTLASS);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "refined_iron_dagger"), REFINED_IRON_DAGGER);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "refined_iron_axe"), REFINED_IRON_AXE);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "refined_iron_battleaxe"), REFINED_IRON_BATTLEAXE);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "refined_iron_hoe"), REFINED_IRON_HOE);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "refined_iron_scythe"), REFINED_IRON_SCYTHE);

        // Copper items registrations
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_sword"), COPPER_SWORD);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_longsword"), COPPER_LONGSWORD);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_shortsword"), COPPER_SHORTSWORD);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_claymore"), COPPER_CLAYMORE);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_cutlass"), COPPER_CUTLASS);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_dagger"), COPPER_DAGGER);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_axe"), COPPER_AXE);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_battleaxe"), COPPER_BATTLEAXE);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_hoe"), COPPER_HOE);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_scythe"), COPPER_SCYTHE);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_shovel"), COPPER_SHOVEL);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "copper_pickaxe"), COPPER_PICKAXE);



        // New item registrations
        Registry.register(Registries.ITEM, new Identifier("renovatio", "refined_iron_ingot"), REFINED_IRON_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "nether_rose_ingot"), NETHER_ROSE_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "bronze_ingot"), BRONZE_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "steel_ingot"), STEEL_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "obsidian_steel_ingot"), OBSIDIAN_STEEL_INGOT);
        Registry.register(Registries.ITEM, new Identifier("renovatio", "void_crystal_gem"), VOID_CRYSTAL_GEM);


    }
}
