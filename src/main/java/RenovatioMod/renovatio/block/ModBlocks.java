package RenovatioMod.renovatio.block;

import RenovatioMod.renovatio.Renovatio;
import RenovatioMod.renovatio.block.ModFunctionalBlocks;
import RenovatioMod.renovatio.block.functional.ExtendedCraftingTableBlock;
import RenovatioMod.renovatio.block.functional.IronBlastFurnaceBlock;
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


    // This is the main registration method called from your Renovatio.java
    public static void registerModBlocks() {
        Renovatio.LOGGER.info("Registering ModBlocks for " + Renovatio.MOD_ID);

        ModFunctionalBlocks.registerModBlocks();
    }
}