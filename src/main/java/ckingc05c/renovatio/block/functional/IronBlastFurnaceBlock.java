package ckingc05c.renovatio.block.functional;

import ckingc05c.renovatio.registry.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an iron blast furnace block in the game.
 * This block is an upgraded version of the vanilla blast furnace.
 */
public class IronBlastFurnaceBlock extends AbstractFurnaceBlock {
    /**
     * The direction the furnace is facing.
     */
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    /**
     * Whether the furnace is currently lit.
     */
    public static final BooleanProperty LIT = Properties.LIT;

    /**
     * Constructs a new IronBlastFurnaceBlock with the given settings.
     *
     * @param settings The settings for the block.
     */
    public IronBlastFurnaceBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(LIT, false));
    }

    /**
     * This new method adds the particle effects when the furnace is lit.
     *
     * @param state The current block state.
     * @param world The world in which the block is located.
     * @param pos The position of the block.
     * @param random A random number generator.
     */
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            double d = (double)pos.getX() + 0.5;
            double e = (double)pos.getY();
            double f = (double)pos.getZ() + 0.5;
            if (random.nextDouble() < 0.1) {
                world.playSound(d, e, f, SoundEvents.BLOCK_BLASTFURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
            }
            Direction direction = state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            double g = 0.52;
            double h = random.nextDouble() * 0.6 - 0.3;
            double i = axis == Direction.Axis.X ? (double)direction.getOffsetX() * g : h;
            double j = random.nextDouble() * 6.0 / 16.0;
            double k = axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * g : h;
            world.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.FLAME, d + i, e + j, f + k, 0.0, 0.0, 0.0);
        }
    }

    /**
     * Gets the block state to be placed in the world.
     *
     * @param ctx The item placement context.
     * @return The block state to be placed.
     */
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    /**
     * Rotates the block state.
     *
     * @param state The current block state.
     * @param rotation The rotation to apply.
     * @return The rotated block state.
     */
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    /**
     * Mirrors the block state.
     *
     * @param state The current block state.
     * @param mirror The mirror to apply.
     * @return The mirrored block state.
     */
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    /**
     * Appends properties to the block state.
     *
     * @param builder The state manager builder.
     */
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    /**
     * Opens the screen for the furnace.
     *
     * @param world The world in which the block is located.
     * @param pos The position of the block.
     * @param player The player who is opening the screen.
     */
    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof NamedScreenHandlerFactory) {
            player.openHandledScreen((NamedScreenHandlerFactory)blockEntity);
        }
    }

    /**
     * Creates a new block entity for the iron blast furnace.
     *
     * @param pos The position of the block.
     * @param state The current block state.
     * @return A new {@link IronBlastFurnaceBlockEntity} instance.
     */
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new IronBlastFurnaceBlockEntity(pos, state);
    }

    /**
     * Gets the ticker for the block entity.
     *
     * @param world The world in which the block is located.
     * @param state The current block state.
     * @param type The block entity type.
     * @return The block entity ticker.
     */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.IRON_BLAST_FURNACE, IronBlastFurnaceBlockEntity::tick);
    }
}