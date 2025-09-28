package ckingc05c.renovatio.registry;

import ckingc05c.renovatio.Renovatio;
import ckingc05c.renovatio.block.functional.ModFunctionalBlocks;
import ckingc05c.renovatio.block.functional.ExtendedCraftingTableBlockEntity;
import ckingc05c.renovatio.block.functional.IronBlastFurnaceBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * This class is responsible for registering all the custom block entity types in the mod.
 */
public class ModBlockEntities {

    /**
     * The block entity type for the iron blast furnace.
     */
    public static BlockEntityType<IronBlastFurnaceBlockEntity> IRON_BLAST_FURNACE;

    /**
     * The block entity type for the extended crafting table.
     */
    public static BlockEntityType<ExtendedCraftingTableBlockEntity> EXTENDED_CRAFTING_TABLE;

    /**
     * Registers the block entity types.
     */
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