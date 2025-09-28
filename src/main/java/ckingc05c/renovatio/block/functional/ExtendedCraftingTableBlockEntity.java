package ckingc05c.renovatio.block.functional;

import ckingc05c.renovatio.registry.ModBlockEntities;
import ckingc05c.renovatio.screen.ExtendedCraftingScreenHandler;
import ckingc05c.renovatio.screen.ModScreenHandlers;
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

/**
 * Represents the block entity for the extended crafting table.
 * This class is responsible for the logic of the extended crafting table, such as its inventory and screen handler.
 */
public class ExtendedCraftingTableBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(26, ItemStack.EMPTY);

    /**
     * Constructs a new ExtendedCraftingTableBlockEntity.
     *
     * @param pos The position of the block entity.
     * @param state The block state of the block entity.
     */
    public ExtendedCraftingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EXTENDED_CRAFTING_TABLE, pos, state);
    }

    /**
     * Gets the display name of the extended crafting table.
     *
     * @return The display name.
     */
    @Override
    public Text getDisplayName() {
        return Text.translatable("container.renovatio.extended_crafting");
    }


    /**
     * Creates a new screen handler for the extended crafting table.
     *
     * @param syncId The sync ID for the screen handler.
     * @param playerInventory The player's inventory.
     * @param player The player who is opening the screen.
     * @return A new {@link ExtendedCraftingScreenHandler} instance.
     */
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        // We now pass the ScreenHandlerType to the constructor
        return new ExtendedCraftingScreenHandler(ModScreenHandlers.EXTENDED_CRAFTING_TABLE_SCREEN_HANDLER, syncId, playerInventory, new SimpleInventory(this.inventory.size()));
    }

    /**
     * Writes the inventory to the NBT compound.
     *
     * @param nbt The NBT compound to write to.
     */
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
    }

    /**
     * Reads the inventory from the NBT compound.
     *
     * @param nbt The NBT compound to read from.
     */
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
    }
}