package RenovatioMod.renovatio.block.functional;

import RenovatioMod.renovatio.registry.ModBlockEntities;
import RenovatioMod.renovatio.registry.ModRecipeTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.BlastFurnaceScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class IronBlastFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    public IronBlastFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IRON_BLAST_FURNACE, pos, state, ModRecipeTypes.IRON_BLASTING);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.renovatio.iron_blast_furnace");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new BlastFurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}