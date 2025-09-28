package ckingc05c.renovatio.block.functional;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an extended crafting table block in the game.
 * This block has a custom GUI and is associated with a {@link ExtendedCraftingTableBlockEntity}.
 */
public class ExtendedCraftingTableBlock extends BlockWithEntity {

    /**
     * Constructs a new ExtendedCraftingTableBlock with the given settings.
     *
     * @param settings The settings for the block.
     */
    public ExtendedCraftingTableBlock(Settings settings) {
        super(settings);
    }

    /**
     * Gets the render type of the block.
     *
     * @param state The current block state.
     * @return The render type, which is {@link BlockRenderType#MODEL} for a normal block.
     */
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // We want our block to be a normal, visible block
        return BlockRenderType.MODEL;
    }

    /**
     * Creates a new block entity for the extended crafting table.
     *
     * @param pos The position of the block.
     * @param state The current block state.
     * @return A new {@link ExtendedCraftingTableBlockEntity} instance.
     */
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        // Link the block to its BlockEntity
        return new ExtendedCraftingTableBlockEntity(pos, state);
    }

    /**
     * Called when the block is used by a player.
     * This method opens the custom GUI for the extended crafting table.
     *
     * @param state The current block state.
     * @param world The world in which the block is located.
     * @param pos The position of the block.
     * @param player The player who used the block.
     * @param hand The hand with which the player used the block.
     * @param hit The result of the ray trace that hit the block.
     * @return {@link ActionResult#SUCCESS} if the GUI was opened, otherwise the result of the superclass method.
     */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            // This is what opens the GUI.
            // We get the factory from the BlockEntity.
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }
}