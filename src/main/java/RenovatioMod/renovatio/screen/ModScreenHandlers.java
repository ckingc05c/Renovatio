package RenovatioMod.renovatio.screen;

import RenovatioMod.renovatio.Renovatio;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    public static ScreenHandlerType<ExtendedCraftingScreenHandler> EXTENDED_CRAFTING_TABLE_SCREEN_HANDLER;

    public static void register() {
        EXTENDED_CRAFTING_TABLE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(
                new Identifier(Renovatio.MOD_ID, "extended_crafting_table"),
                ExtendedCraftingScreenHandler::new
        );
    }
}