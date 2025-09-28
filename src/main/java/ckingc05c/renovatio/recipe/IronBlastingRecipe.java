package ckingc05c.renovatio.recipe;

import ckingc05c.renovatio.registry.ModRecipeTypes;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

/**
 * This class represents an iron blasting recipe.
 */
public class IronBlastingRecipe extends AbstractCookingRecipe {

    /**
     * Constructs a new IronBlastingRecipe.
     * @param id The recipe ID.
     * @param group The recipe group.
     * @param category The recipe category.
     * @param input The input ingredient.
     * @param output The output item stack.
     * @param experience The experience granted by the recipe.
     * @param cookTime The cooking time for the recipe.
     */
    public IronBlastingRecipe(Identifier id, String group, CookingRecipeCategory category, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(ModRecipeTypes.IRON_BLASTING, id, group, category, input, output, experience, cookTime);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.IRON_BLASTING_SERIALIZER;
    }
}