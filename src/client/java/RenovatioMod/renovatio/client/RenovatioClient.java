package RenovatioMod.renovatio.client;

import RenovatioMod.renovatio.client.screen.ExtendedCraftingScreen; // Add this import
import RenovatioMod.renovatio.screen.ModScreenHandlers; // Add this import
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens; // Add this import

public class RenovatioClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        WeaponAttributeTooltip.initialize();

        // Register your screen
        HandledScreens.register(ModScreenHandlers.EXTENDED_CRAFTING_TABLE_SCREEN_HANDLER, ExtendedCraftingScreen::new);
    }
}