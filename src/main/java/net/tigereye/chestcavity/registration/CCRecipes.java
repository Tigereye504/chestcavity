package net.tigereye.chestcavity.registration;

import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.recipes.InfuseVenomGland;
import net.tigereye.chestcavity.recipes.SalvageRecipe;
import net.tigereye.chestcavity.recipes.json.SalvageRecipeSerializer;

public class CCRecipes {
    public static SpecialRecipeSerializer<InfuseVenomGland> INFUSE_VENOM_GLAND;
    public static SalvageRecipeSerializer SALVAGE_RECIPE_SERIALIZER;
    public static RecipeType<SalvageRecipe> SALVAGE_RECIPE_TYPE;

    public static void register() {
        INFUSE_VENOM_GLAND = (SpecialRecipeSerializer<InfuseVenomGland>) Registry.register(Registry.RECIPE_SERIALIZER, "crafting_special_infuse_venom_gland", new SpecialRecipeSerializer<InfuseVenomGland>(InfuseVenomGland::new));
        SALVAGE_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, SalvageRecipeSerializer.ID, SalvageRecipeSerializer.INSTANCE);
        SALVAGE_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, SalvageRecipeSerializer.ID, SalvageRecipe.Type.INSTANCE);
    }
}