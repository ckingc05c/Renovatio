package RenovatioMod.renovatio.mixin;

import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ToolMaterials.class)
public class ToolMaterialsMixin {

    @Inject(method = "getDurability", at = @At("HEAD"), cancellable = true)
    private void getDurability(CallbackInfoReturnable<Integer> cir) {
        // Get the specific material instance (e.g., IRON, DIAMOND)
        ToolMaterials material = (ToolMaterials) (Object) this;

        // Check which material it is and set a new durability
        // The original values are: WOOD(59), STONE(131), IRON(250), DIAMOND(1561), GOLD(32), NETHERITE(2031)
        if (material == ToolMaterials.IRON) {
            // Example: Increase Iron tool durability from 250 to 512
            cir.setReturnValue(512);
        } else if (material == ToolMaterials.DIAMOND) {
            // Example: Increase Diamond tool durability from 1561 to 2048
            cir.setReturnValue(2048);
        }else if (material == ToolMaterials.NETHERITE) {
            // Example: Increase Netherite tool durability from 2031 to 1560
            cir.setReturnValue(2560);
        }
    }
    @Inject(method = "getAttackDamage", at = @At("HEAD"), cancellable = true)
    private void getAttackDamage(CallbackInfoReturnable<Float> cir) {
        // Get the specific material instance (e.g., IRON, DIAMOND)
        ToolMaterials material = (ToolMaterials) (Object) this;

        // Check which material it is and set a new damage

        if (material == ToolMaterials.DIAMOND) {
            // Example: Increase Diamond sword damage from 7 to 8
            cir.setReturnValue(4.0f);
        }else if (material == ToolMaterials.NETHERITE) {
            // Example: Increase Netherite sword damage from 7 to 8
            cir.setReturnValue(5.0f);
        }


    }
}