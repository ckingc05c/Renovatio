package RenovatioMod.renovatio.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.util.Identifier;

public class IronBlastingRecipeSerializer {

    public static final CookingRecipeSerializer<IronBlastingRecipe> INSTANCE =
            new CookingRecipeSerializer<>(IronBlastingRecipe::new, 100); // 100 is default cookTime

    public static final Identifier ID = new Identifier("renovatio", "iron_blasting");
}