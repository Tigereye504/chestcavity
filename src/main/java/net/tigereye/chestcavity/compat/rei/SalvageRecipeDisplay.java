package net.tigereye.chestcavity.compat.rei;

import me.shedaniel.rei_common.category.CategoryIdentifier;
import me.shedaniel.rei_common.display.Display;
import me.shedaniel.rei_common.entry.EntryIngredient;
import me.shedaniel.rei_common.util.EntryIngredients;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
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
        output = Collections.singletonList(EntryIngredients.of(recipe.getOutput()));
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
