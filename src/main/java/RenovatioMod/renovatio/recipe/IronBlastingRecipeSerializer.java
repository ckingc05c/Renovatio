package RenovatioMod.renovatio.recipe;

import net.minecraft.recipe.CookingRecipeSerializer;

/**
 * This class is the serializer for the iron blasting recipe.
 */
public class IronBlastingRecipeSerializer extends CookingRecipeSerializer<IronBlastingRecipe> {
    /**
     * Constructs a new IronBlastingRecipeSerializer.
     * @param cookingTime The cooking time for the recipe.
     */
    public IronBlastingRecipeSerializer(int cookingTime) {
        super(IronBlastingRecipe::new, cookingTime);
    }
}