package RenovatioMod.renovatio.registry;

import RenovatioMod.renovatio.Renovatio;
import RenovatioMod.renovatio.block.ModFunctionalBlocks;
import RenovatioMod.renovatio.block.functional.ExtendedCraftingTableBlockEntity;
import RenovatioMod.renovatio.block.functional.IronBlastFurnaceBlockEntity;
import RenovatioMod.renovatio.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    // The Iron Blast Furnace Block Entity
    public static BlockEntityType<IronBlastFurnaceBlockEntity> IRON_BLAST_FURNACE;

    // The new Extended Crafting Table Block Entity
    public static BlockEntityType<ExtendedCraftingTableBlockEntity> EXTENDED_CRAFTING_TABLE;

    public static void register() {
        IRON_BLAST_FURNACE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Renovatio.MOD_ID, "iron_blast_furnace"),
                FabricBlockEntityTypeBuilder.create(IronBlastFurnaceBlockEntity::new, ModFunctionalBlocks.IRON_BLAST_FURNACE).build(null)
        );

        EXTENDED_CRAFTING_TABLE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Renovatio.MOD_ID, "extended_crafting_table"),
                FabricBlockEntityTypeBuilder.create(ExtendedCraftingTableBlockEntity::new, ModFunctionalBlocks.EXTENDED_CRAFTING_TABLE).build(null)
        );
    }
}