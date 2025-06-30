package RenovatioMod.renovatio.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.text.Text;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;

public class RenovatioClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        WeaponAttributeTooltip.initialize();
    }
}
