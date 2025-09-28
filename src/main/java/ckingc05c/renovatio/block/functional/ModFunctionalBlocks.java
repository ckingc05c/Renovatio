package ckingc05c.renovatio.block.functional;

import ckingc05c.renovatio.Renovatio;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * This class is responsible for registering all the functional blocks in the mod.
 * Functional blocks are blocks that have a specific function, such as crafting or smelting.
 */
public class ModFunctionalBlocks {

    /**
     * The iron blast furnace block.
     */
    public static final Block IRON_BLAST_FURNACE = new IronBlastFurnaceBlock(
            FabricBlockSettings.copyOf(Blocks.BLAST_FURNACE)
    );

    /**
     * The extended crafting table block.
     */
    public static final Block EXTENDED_CRAFTING_TABLE = new ExtendedCraftingTableBlock(
            FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE)
    );

    /**
     * Helper method to register a block.
     *
     * @param name The name of the block.
     * @param block The block instance to register.
     * @return The registered block.
     */
    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Renovatio.MOD_ID, name), block);
    }

    /**
     * Helper method to register a block's item so it appears in the inventory.
     *
     * @param name The name of the block item.
     * @param block The block associated with the item.
     * @return The registered item.
     */
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Renovatio.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    /**
     * This is the main registration method called from your Renovatio.java
     */
    public static void registerModBlocks() {
        Renovatio.LOGGER.info("Registering ModBlocks for " + Renovatio.MOD_ID);

        // Register the Iron Blast Furnace and its item
        registerBlock("iron_blast_furnace", IRON_BLAST_FURNACE);
        registerBlockItem("iron_blast_furnace", IRON_BLAST_FURNACE);

        // Register the Extended Crafting Table and its item
        registerBlock("extended_crafting_table", EXTENDED_CRAFTING_TABLE);
        registerBlockItem("extended_crafting_table", EXTENDED_CRAFTING_TABLE);
    }
}