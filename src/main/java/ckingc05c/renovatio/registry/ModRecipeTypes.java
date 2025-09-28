package ckingc05c.renovatio.registry;

import ckingc05c.renovatio.Renovatio;
import ckingc05c.renovatio.recipe.IronBlastingRecipe;
import ckingc05c.renovatio.recipe.IronBlastingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * This class is responsible for registering all the custom recipe types and serializers in the mod.
 */
public class ModRecipeTypes {

    /**
     * The recipe type for iron blasting.
     */
    public static RecipeType<IronBlastingRecipe> IRON_BLASTING;
    /**
     * The recipe serializer for iron blasting.
     */
    public static RecipeSerializer<IronBlastingRecipe> IRON_BLASTING_SERIALIZER;

    /**
     * Registers the recipe types and serializers.
     */
    public static void register() {
        IRON_BLASTING = Registry.register(Registries.RECIPE_TYPE, new Identifier(Renovatio.MOD_ID, "iron_blasting"), new RecipeType<IronBlastingRecipe>() {});
        IRON_BLASTING_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Renovatio.MOD_ID, "iron_blasting"), new IronBlastingRecipeSerializer(100));
    }
}