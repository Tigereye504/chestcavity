package net.tigereye.chestcavity.compat.rei;

import me.shedaniel.rei_client.plugins.REIClientPlugin;
import me.shedaniel.rei_client.registry.category.CategoryRegistry;
import me.shedaniel.rei_client.registry.display.DisplayRegistry;
import net.tigereye.chestcavity.recipes.SalvageRecipe;

public class SalvageRecipeREIPlugin implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new SalvageRecipeCategory());
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(SalvageRecipe.class, SalvageRecipeDisplay::new);
    }
}
