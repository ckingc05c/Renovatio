package RenovatioMod.renovatio.block.functional;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class ModBlocks {

    public static final Block IRON_BLAST_FURNACE = new IronBlastFurnaceBlock(
            FabricBlockSettings.copyOf(Blocks.BLAST_FURNACE)
    );

    public static void registerModBlocks() {
        Registry.register(Registries.BLOCK, new Identifier("renovatio", "iron_blast_furnace"), IRON_BLAST_FURNACE);
    }
}
