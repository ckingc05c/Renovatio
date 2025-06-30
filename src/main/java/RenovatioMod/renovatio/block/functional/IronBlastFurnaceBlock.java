package RenovatioMod.renovatio.block.functional;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IronBlastFurnaceBlock extends AbstractFurnaceBlock {
    public IronBlastFurnaceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new IronBlastFurnaceBlockEntity(pos, state);
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        if (world.isClient) return; // Only open on server

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof NamedScreenHandlerFactory factory && player instanceof ServerPlayerEntity serverPlayer) {
            serverPlayer.openHandledScreen(factory);
        }
    }
}