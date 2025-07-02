package RenovatioMod.renovatio.item.custom.weapon.ranged;

import net.fabric_extras.ranged_weapon.api.CustomBow;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;


public class LongBowItem extends CustomBow {
    public LongBowItem(Settings settings, Supplier<Ingredient> repairIngredientSupplier) {
        super(settings, repairIngredientSupplier);
    }
}
