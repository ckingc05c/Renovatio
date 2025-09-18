package RenovatioMod.renovatio.client.screen;

import RenovatioMod.renovatio.Renovatio;
import RenovatioMod.renovatio.screen.ExtendedCraftingScreenHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * This class represents the screen for the extended crafting table.
 */
public class ExtendedCraftingScreen extends HandledScreen<ExtendedCraftingScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Renovatio.MOD_ID, "textures/gui/container/extended_crafting_table.png");

    /**
     * Constructs a new ExtendedCraftingScreen.
     * @param handler The screen handler.
     * @param inventory The player inventory.
     * @param title The title of the screen.
     */
    public ExtendedCraftingScreen(ExtendedCraftingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 256;
        this.backgroundHeight = 256;

        // Set the "Inventory" text Y-position to fit in our new gap
        this.playerInventoryTitleY = 111;
        this.playerInventoryTitleX = 48;
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        titleY = 8; // A bit more space from the top
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}