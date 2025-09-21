package RenovatioMod.renovatio.item.custom.equipment;

import RenovatioMod.renovatio.item.ModToolMaterials;
import RenovatioMod.renovatio.item.custom.equipment.tool.CustomPickaxeItem;
import RenovatioMod.renovatio.item.custom.equipment.tool.DrillPickaxeItem;
import RenovatioMod.renovatio.item.custom.equipment.weapon.melee.axe.BattleAxeItem;
import RenovatioMod.renovatio.item.custom.equipment.weapon.melee.axe.CustomAxeItem;
import RenovatioMod.renovatio.item.custom.equipment.weapon.melee.sword.*;
import RenovatioMod.renovatio.item.custom.equipment.weapon.melee.hoe.CustomHoeItem;
import RenovatioMod.renovatio.item.custom.equipment.weapon.melee.hoe.ScytheHoeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * This class is responsible for registering all the melee weapons in the mod.
 */
public class ModMeleeWeapons {
    /**
     * The rose gold sword item.
     */
    public static final Item ROSE_GOLD_SWORD = new CustomSwordItem(
            ModToolMaterials.ROSE_GOLD,
            new FabricItemSettings()
    );
    /**
     * The rose gold pickaxe item.
     */
    public static final Item ROSE_GOLD_PICKAXE = new PickaxeItem(
            ModToolMaterials.ROSE_GOLD,
            0, // 3 Total attack damage 0(Pickaxe) + 2 (Material) + 1(Item)
            -2.8f, // attack speed
            new FabricItemSettings()

    );

    /**
     * The rose gold battleaxe item.
     */
    public static final Item ROSE_GOLD_BATTLEAXE = new BattleAxeItem(
            ModToolMaterials.ROSE_GOLD,
            new FabricItemSettings()

    );

    /**
     * The rose gold longsword item.
     */
    public static final Item ROSE_GOLD_LONGSWORD = new LongSwordItem(
            ModToolMaterials.ROSE_GOLD,
            new FabricItemSettings()

    );
    /**
     * The refined iron sword item.
     */
    public static final Item REFINED_IRON_SWORD = new CustomSwordItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    /**
     * The refined iron longsword item.
     */
    public static final Item REFINED_IRON_LONGSWORD = new LongSwordItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    /**
     * The refined iron shortsword item.
     */
    public static final Item REFINED_IRON_SHORTSWORD = new ShortSwordItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    /**
     * The refined iron claymore item.
     */
    public static final Item REFINED_IRON_CLAYMORE = new ClaymoreSwordItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    /**
     * The refined iron cutlass item.
     */
    public static final Item REFINED_IRON_CUTLASS = new CutlassSwordItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    /**
     * The refined iron dagger item.
     */
    public static final Item REFINED_IRON_DAGGER = new DaggerSwordItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    /**
     * The refined iron axe item.
     */
    public static final Item REFINED_IRON_AXE = new CustomAxeItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    /**
     * The refined iron battleaxe item.
     */
    public static final Item REFINED_IRON_BATTLEAXE = new BattleAxeItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    /**
     * The refined iron hoe item.
     */
    public static final Item REFINED_IRON_HOE = new CustomHoeItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    /**
     * The refined iron scythe item.
     */
    public static final Item REFINED_IRON_SCYTHE = new ScytheHoeItem(
            ModToolMaterials.REFINED_IRON,
            new FabricItemSettings()
    );

    /**
     * The copper sword item.
     */
    public static final Item COPPER_SWORD = new CustomSwordItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    /**
     * The copper longsword item.
     */
    public static final Item COPPER_LONGSWORD = new LongSwordItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    /**
     * The copper shortsword item.
     */
    public static final Item COPPER_SHORTSWORD = new ShortSwordItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    /**
     * The copper claymore item.
     */
    public static final Item COPPER_CLAYMORE = new ClaymoreSwordItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    /**
     * The copper cutlass item.
     */
    public static final Item COPPER_CUTLASS = new CutlassSwordItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    /**
     * The copper dagger item.
     */
    public static final Item COPPER_DAGGER = new DaggerSwordItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    /**
     * The copper axe item.
     */
    public static final Item COPPER_AXE = new CustomAxeItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    /**
     * The copper battleaxe item.
     */
    public static final Item COPPER_BATTLEAXE = new BattleAxeItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    /**
     * The copper hoe item.
     */
    public static final Item COPPER_HOE = new CustomHoeItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    /**
     * The copper pickaxe item.
     */
    public static final Item COPPER_PICKAXE = new CustomPickaxeItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    /**
     * The copper shovel item.
     */
    public static final Item COPPER_SHOVEL = new ShovelItem(
            ModToolMaterials.COPPER,
            0.5f,  // attack damage
            -3.0f,  // attack speed
            new FabricItemSettings()
    );

    /**
     * The copper scythe item.
     */
    public static final Item COPPER_SCYTHE = new ScytheHoeItem(
            ModToolMaterials.COPPER,
            new FabricItemSettings()
    );

    /**
     * Registers all the melee weapons.
     */
    public static void registerItems() {
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

    }
}
