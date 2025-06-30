package RenovatioMod.renovatio.registry;

import RenovatioMod.renovatio.Renovatio;
import RenovatioMod.renovatio.recipe.IronBlastingRecipe;
import RenovatioMod.renovatio.recipe.IronBlastingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeTypes {

    public static RecipeType<IronBlastingRecipe> IRON_BLASTING;
    public static RecipeSerializer<IronBlastingRecipe> IRON_BLASTING_SERIALIZER;

    public static void register() {
        IRON_BLASTING = Registry.register(Registries.RECIPE_TYPE, new Identifier(Renovatio.MOD_ID, "iron_blasting"), new RecipeType<IronBlastingRecipe>() {});
        IRON_BLASTING_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Renovatio.MOD_ID, "iron_blasting"), new IronBlastingRecipeSerializer(100));
    }
}