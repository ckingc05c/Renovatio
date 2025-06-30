package RenovatioMod.renovatio.recipe;

import net.minecraft.recipe.CookingRecipeSerializer;

public class IronBlastingRecipeSerializer extends CookingRecipeSerializer<IronBlastingRecipe> {
    public IronBlastingRecipeSerializer(int cookingTime) {
        super(IronBlastingRecipe::new, cookingTime);
    }
}