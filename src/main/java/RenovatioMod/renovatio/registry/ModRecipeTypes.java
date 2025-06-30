package RenovatioMod.renovatio.registry;

import RenovatioMod.renovatio.recipe.IronBlastingRecipe;
import RenovatioMod.renovatio.recipe.IronBlastingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries; // <-- note this import

import net.minecraft.registry.RegistryKeys;

public class ModRecipeTypes {

    public static final RecipeType<IronBlastingRecipe> IRON_BLASTING =
            Registry.register(
                    Registries.RECIPE_TYPE, // use Registries, not RegistryKeys
                    new Identifier("renovatio", "iron_blasting"),
                    new RecipeType<>() {}
            );

    public static final RecipeSerializer<IronBlastingRecipe> IRON_BLASTING_SERIALIZER =
            Registry.register(
                    Registries.RECIPE_SERIALIZER, // use Registries here too
                    IronBlastingRecipeSerializer.ID,
                    IronBlastingRecipeSerializer.INSTANCE
            );

    public static void register() {
        // Called on mod init
    }
}
