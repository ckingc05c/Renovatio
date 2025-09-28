package RenovatioMod.renovatio.client;

import RenovatioMod.renovatio.client.screen.ExtendedCraftingScreen; // Add this import
import ckingc05c.renovatio.screen.ModScreenHandlers; // Add this import
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens; // Add this import

/**
 * This class is the client-side initializer for the mod.
 */
public class RenovatioClient implements ClientModInitializer {

    /**
     * Initializes the client-side of the mod.
     */
    @Override
    public void onInitializeClient() {
        WeaponAttributeTooltip.initialize();

        // Register your screen
        HandledScreens.register(ModScreenHandlers.EXTENDED_CRAFTING_TABLE_SCREEN_HANDLER, ExtendedCraftingScreen::new);
    }
}