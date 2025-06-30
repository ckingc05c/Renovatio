package RenovatioMod.renovatio.registry;

import RenovatioMod.renovatio.Renovatio;
import RenovatioMod.renovatio.block.functional.IronBlastFurnaceBlockEntity;
import RenovatioMod.renovatio.block.functional.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static BlockEntityType<IronBlastFurnaceBlockEntity> IRON_BLAST_FURNACE;

    public static void register() {
        IRON_BLAST_FURNACE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Renovatio.MOD_ID, "iron_blast_furnace"),
                BlockEntityType.Builder.create(IronBlastFurnaceBlockEntity::new,
                        ModBlocks.IRON_BLAST_FURNACE).build(null));
    }
}