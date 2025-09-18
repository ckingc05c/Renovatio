package RenovatioMod.renovatio.screen;

import RenovatioMod.renovatio.Renovatio;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

/**
 * This class is responsible for registering all the screen handlers in the mod.
 */
public class ModScreenHandlers {

    /**
     * The screen handler type for the extended crafting table.
     */
    public static ScreenHandlerType<ExtendedCraftingScreenHandler> EXTENDED_CRAFTING_TABLE_SCREEN_HANDLER;

    /**
     * Registers all the screen handlers.
     */
    public static void register() {
        EXTENDED_CRAFTING_TABLE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(
                new Identifier(Renovatio.MOD_ID, "extended_crafting_table"),
                ExtendedCraftingScreenHandler::new
        );
    }
}