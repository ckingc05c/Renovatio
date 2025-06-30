package RenovatioMod.renovatio.recipe;

import RenovatioMod.renovatio.registry.ModRecipeTypes;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class IronBlastingRecipe extends AbstractCookingRecipe {

    public IronBlastingRecipe(Identifier id, String group, CookingRecipeCategory category, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(ModRecipeTypes.IRON_BLASTING, id, group, category, input, output, experience, cookTime);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.IRON_BLASTING_SERIALIZER;
    }
}