package RenovatioMod.renovatio.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ExtendedCraftingScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public ExtendedCraftingScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(26));
    }

    public ExtendedCraftingScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.EXTENDED_CRAFTING_TABLE_SCREEN_HANDLER, syncId);
        checkSize(inventory, 26);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);

        int m;
        int l;

        // Crafting Grid Slots (5x5)
        for (m = 0; m < 5; ++m) {
            for (l = 0; l < 5; ++l) {
                this.addSlot(new Slot(inventory, l + m * 5, 30 + l * 18, 20 + m * 18)); // Adjusted X and Y
            }
        }

        // Crafting Result Slot - Positioned to the right
        this.addSlot(new Slot(inventory, 25, 134, 44)); // Adjusted X and Y

        // Player Inventory Slots
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 116 + m * 18)); // Adjusted Y
            }
        }

        // Player Hotbar Slots
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 174)); // Adjusted Y
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            itemStack = originalStack.copy();
            if (slotIndex < 26) {
                if (!this.insertItem(originalStack, 26, 62, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, 25, false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return itemStack;
    }
}