package net.tigereye.chestcavity.recipes.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.recipes.SalvageRecipe;

public class SalvageRecipeSerializer implements RecipeSerializer<SalvageRecipe> {

    @Override
    public SalvageRecipe read(Identifier id, JsonObject json) {
        SalvageRecipeJsonFormat recipeJson = new Gson().fromJson(json, SalvageRecipeJsonFormat.class);

        if (recipeJson.ingredient == null || recipeJson.result == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }

        if (recipeJson.required == 0) recipeJson.required = 1;
        if (recipeJson.count == 0) recipeJson.count = 1;
        Ingredient input = Ingredient.fromJson(recipeJson.ingredient);
        Item outputItem = Registries.ITEM.getOrEmpty(new Identifier(recipeJson.result))
                // Validate the inputted item actually exists
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.result));
        ItemStack output = new ItemStack(outputItem, recipeJson.count);

        return new SalvageRecipe(input, recipeJson.required, CraftingRecipeCategory.MISC, output, id);
    }

    @Override
    public SalvageRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient input = Ingredient.fromPacket(buf);
        int required = buf.readInt();
        ItemStack output = buf.readItemStack();
        return new SalvageRecipe(input, required, CraftingRecipeCategory.MISC, output, id);
    }

    @Override
    public void write(PacketByteBuf buf, SalvageRecipe recipe) {
        recipe.getInput().write(buf);
        buf.writeInt(recipe.getRequired());
        buf.writeItemStack(recipe.outputStack.copy());
    }
}
