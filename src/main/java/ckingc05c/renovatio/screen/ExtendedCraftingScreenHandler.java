package ckingc05c.renovatio.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

/**
 * This class represents the screen handler for the extended crafting table.
 */
public class ExtendedCraftingScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    /**
     * This is the client-side constructor. It calls the main constructor below.
     * @param syncId The sync ID.
     * @param playerInventory The player's inventory.
     */
    public ExtendedCraftingScreenHandler(int syncId, PlayerInventory playerInventory) {
        // We pass our registered ScreenHandlerType here instead of 'this'.
        this(ModScreenHandlers.EXTENDED_CRAFTING_TABLE_SCREEN_HANDLER, syncId, playerInventory, new SimpleInventory(26));
    }

    /**
     * This is the main constructor.
     * @param type The screen handler type.
     * @param syncId The sync ID.
     * @param playerInventory The player's inventory.
     * @param inventory The inventory.
     */
    public ExtendedCraftingScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(type, syncId);
        checkSize(inventory, 26);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        int gapBetweenCraftingAndInventory = 34;
        int m;
        int l;

        int gridX = 48;
        int gridY = 20;

        // 5x5 Crafting Grid
        for (m = 0; m < 5; ++m) {
            for (l = 0; l < 5; ++l) {
                this.addSlot(new Slot(inventory, l + m * 5, gridX + l * 18, gridY + m * 18));
            }
        }

        // Result Slot - Y is now gridY + (2 * 18) to align with the 3rd row
        this.addSlot(new Slot(inventory, 25, 178, gridY + 2 * 18));

        // Player Inventory - Pushed down to create a gap
        int playerInvY = 122;
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 48 + l * 18, playerInvY + m * 18));
            }
        }

        // Player Hotbar - Pushed down to create a gap
        int hotbarY = 180;
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 48 + m * 18, hotbarY));
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