package net.tigereye.chestcavity.recipes;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.recipes.json.SalvageRecipeSerializer;
import net.tigereye.chestcavity.registration.CCRecipes;

public class SalvageRecipe implements CraftingRecipe {
    private final Ingredient input;
    private int required;
    private final ItemStack outputStack;
    private final Identifier id;

    public SalvageRecipe(Ingredient input, int required, ItemStack outputStack, Identifier id){
        this.input = input;
        this.required = required;
        this.outputStack = outputStack;
        this.id = id;
    }

    public Ingredient getInput(){
        return input;
    }

    public int getRequired(){return required;}

    @Override
    public boolean matches(CraftingInventory inv, World world) {
        //ChestCavity.LOGGER.info("Attempting to match salvage recipe");
        int count = 0;
        ItemStack target;
        for(int i = 0; i < inv.size(); ++i) {
            target = inv.getStack(i);
            if(target != null && target != ItemStack.EMPTY && target.getItem() != Items.AIR) {
                if (input.test(inv.getStack(i))) {
                    count++;
                    //ChestCavity.LOGGER.info("Salvage recipe counts "+count+" "+target.getName()+"s");
                }
                else{
                    //ChestCavity.LOGGER.info("Salvage recipe found bad item: "+target.getName().getString());
                    return false;
                }
            }
        }
        //if(count > 0){
            //ChestCavity.LOGGER.info("Found salvage recipe match");
        //}
        return count > 0 && count % required == 0;
    }

    @Override
    public ItemStack craft(CraftingInventory inv) {
        //ChestCavity.LOGGER.info("Attempting to craft salvage recipe");
        int count = 0;
        ItemStack target;
        for(int i = 0; i < inv.size(); ++i) {
            target = inv.getStack(i);
            if(target != null && target != ItemStack.EMPTY && target.getItem() != Items.AIR) {
                if (input.test(inv.getStack(i))) {
                    count++;
                }
                else{
                    return ItemStack.EMPTY;
                }
            }
        }
        //ChestCavity.LOGGER.info("Found salvage recipe, count "+count);
        if(count == 0 || count % required != 0){
            //ChestCavity.LOGGER.info("Salvage recipe failed modulo check");
            return ItemStack.EMPTY;
        }
        count = (count / required) * outputStack.getCount();
        if(count > outputStack.getMaxCount()) {
            //ChestCavity.LOGGER.info("Salvage recipe exceeded max stack size");
            return ItemStack.EMPTY;
        }
        ItemStack out = getOutput();
        out.setCount(count);
        return out;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return outputStack.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CCRecipes.SALVAGE_RECIPE_SERIALIZER;
    }

    /*@Override
    public RecipeType<SalvageRecipe> getType() {
        return CCRecipes.SALVAGE_RECIPE_TYPE;
    }
    */

}
