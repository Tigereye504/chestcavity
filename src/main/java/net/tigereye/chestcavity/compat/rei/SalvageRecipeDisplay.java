package net.tigereye.chestcavity.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.recipes.SalvageRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SalvageRecipeDisplay implements Display {
    public static final CategoryIdentifier<SalvageRecipeDisplay> IDENTIFIER = CategoryIdentifier.of(new Identifier(ChestCavity.MODID,"crafting_salvage"));

    public List<EntryIngredient> input;
    public List<EntryIngredient> output;

    public String getCoordinate(float value, boolean absolute) {
        return (absolute ? "~" : "") + value;
    }

    public String getOffset(float x, float z, boolean absolute) {
        return getCoordinate(x, absolute) + ", " + getCoordinate(z, absolute);
    }

    public SalvageRecipeDisplay(SalvageRecipe recipe) {

        int required = recipe.getRequired();
        EntryIngredient inputIngredient = EntryIngredients.ofIngredient(recipe.getInput());
        input = new ArrayList<>();
        for (int i = 0; i < required; i++) {
            input.add(inputIngredient);
        }
        World world = MinecraftClient.getInstance().world;
        output = Collections.singletonList(EntryIngredients.of(recipe.getOutput(world != null ? world.getRegistryManager() : null)));
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return input;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return output;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return IDENTIFIER;
    }
}
