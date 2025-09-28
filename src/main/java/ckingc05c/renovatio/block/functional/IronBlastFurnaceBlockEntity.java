package ckingc05c.renovatio.block.functional;

import ckingc05c.renovatio.registry.ModBlockEntities;
import ckingc05c.renovatio.registry.ModRecipeTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.BlastFurnaceScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

/**
 * Represents the block entity for the iron blast furnace.
 * This class is responsible for the logic of the iron blast furnace, such as its inventory and screen handler.
 */
public class IronBlastFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    /**
     * Constructs a new IronBlastFurnaceBlockEntity.
     *
     * @param pos The position of the block entity.
     * @param state The block state of the block entity.
     */
    public IronBlastFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IRON_BLAST_FURNACE, pos, state, ModRecipeTypes.IRON_BLASTING);
    }

    /**
     * Gets the container name for the iron blast furnace.
     *
     * @return The container name.
     */
    @Override
    protected Text getContainerName() {
        return Text.translatable("container.renovatio.iron_blast_furnace");
    }

    /**
     * Creates a new screen handler for the iron blast furnace.
     *
     * @param syncId The sync ID for the screen handler.
     * @param playerInventory The player's inventory.
     * @return A new {@link BlastFurnaceScreenHandler} instance.
     */
    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new BlastFurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}