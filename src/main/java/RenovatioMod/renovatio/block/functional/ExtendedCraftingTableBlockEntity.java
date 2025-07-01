package RenovatioMod.renovatio.block.functional;

import RenovatioMod.renovatio.registry.ModBlockEntities;
import RenovatioMod.renovatio.screen.ExtendedCraftingScreenHandler;
import RenovatioMod.renovatio.screen.ModScreenHandlers;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ExtendedCraftingTableBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(26, ItemStack.EMPTY);

    public ExtendedCraftingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EXTENDED_CRAFTING_TABLE, pos, state);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.renovatio.extended_crafting");
    }


    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        // We now pass the ScreenHandlerType to the constructor
        return new ExtendedCraftingScreenHandler(ModScreenHandlers.EXTENDED_CRAFTING_TABLE_SCREEN_HANDLER, syncId, playerInventory, new SimpleInventory(this.inventory.size()));
    }

    // Corrected signature for 1.20.1
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
    }

    // Corrected signature for 1.20.1
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
    }
}