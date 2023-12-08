package net.tigereye.chestcavity.recipes;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCRecipes;
import net.tigereye.chestcavity.util.OrganUtil;

public class InfuseVenomGland extends SpecialCraftingRecipe {
    public InfuseVenomGland(Identifier id, CraftingRecipeCategory craftingRecipeCategory) {
        super(id, craftingRecipeCategory);
    }

    @Override
    public boolean matches(RecipeInputInventory craftingInventory, World world) {
        boolean foundVenomGland = false;
        boolean foundPotion = false;
        for(int i = 0; i < craftingInventory.getWidth(); ++i) {
            for(int j = 0; j < craftingInventory.getHeight(); ++j) {
                ItemStack itemStack = craftingInventory.getStack(i + j * craftingInventory.getWidth());
                if (itemStack.getItem() == CCItems.VENOM_GLAND) {
                    if(foundVenomGland){
                        return false;
                    }
                    foundVenomGland = true;
                }
                else if(itemStack.getItem() == Items.POTION ||
                        itemStack.getItem() == Items.SPLASH_POTION ||
                        itemStack.getItem() == Items.LINGERING_POTION){
                    if(foundPotion){
                        return false;
                    }
                    foundPotion = true;
                }
            }
        }
        return foundVenomGland&&foundPotion;
    }

    @Override
    public ItemStack craft(RecipeInputInventory craftingInventory, DynamicRegistryManager registryManager) {
        ItemStack venomGland = null;
        ItemStack potion = null;
        ItemStack output = null;
        for(int i = 0; i < craftingInventory.getWidth(); ++i) {
            for(int j = 0; j < craftingInventory.getHeight(); ++j) {
                ItemStack itemStack = craftingInventory.getStack(i + j * craftingInventory.getWidth());
                if (itemStack.getItem() == CCItems.VENOM_GLAND) {
                    if(venomGland != null){
                        return ItemStack.EMPTY;
                    }
                    venomGland = itemStack;
                }
                else if(itemStack.getItem() == Items.POTION ||
                        itemStack.getItem() == Items.SPLASH_POTION ||
                        itemStack.getItem() == Items.LINGERING_POTION){
                    if(potion != null){
                        return ItemStack.EMPTY;
                    }
                    potion = itemStack;
                }
            }
        }
        if(venomGland != null && potion != null){
            output = venomGland.copy();
            OrganUtil.setStatusEffects(output, potion);
            return output;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return (width * height >= 2);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CCRecipes.INFUSE_VENOM_GLAND;
    }
}
