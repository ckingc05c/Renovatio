package RenovatioMod.renovatio.block.functional;

import RenovatioMod.renovatio.Renovatio;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // Define the original Iron Blast Furnace
    public static final Block IRON_BLAST_FURNACE = new IronBlastFurnaceBlock(
            FabricBlockSettings.copyOf(Blocks.BLAST_FURNACE)
    );

    // Define the new Extended Crafting Table
    public static final Block EXTENDED_CRAFTING_TABLE = new ExtendedCraftingTableBlock(
            FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE)
    );

    // Helper method to register a block
    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Renovatio.MOD_ID, name), block);
    }

    // Helper method to register a block's item so it appears in the inventory
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Renovatio.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    // This is the main registration method called from your Renovatio.java
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