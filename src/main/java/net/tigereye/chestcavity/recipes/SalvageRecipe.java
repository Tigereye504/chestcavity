package net.tigereye.chestcavity.recipes;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.recipes.json.SalvageRecipeSerializer;

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
        int count = 0;
        ItemStack target;
        for(int i = 0; i < inv.size(); ++i) {
            target = inv.getStack(i);
            if(target != null && target != ItemStack.EMPTY) {
                if (input.test(inv.getStack(i))) {
                    count++;
                    //ChestCavity.LOGGER.info("Salvage recipe counts "+count+" "+target.getName()+"s");
                }
                else{
                    //ChestCavity.LOGGER.info("Salvage recipe found bad item: "+target.getName());
                    return false;
                }
            }
        }
        //if(count > 0){
        //    ChestCavity.LOGGER.info("Found salvage recipe, count "+count);
        //}
        return count > 0 && count % required == 0;
    }

    @Override
    public ItemStack craft(CraftingInventory inv) {
        int count = 0;
        ItemStack target;
        for(int i = 0; i < inv.size(); ++i) {
            target = inv.getStack(i);
            if(target != null && target != ItemStack.EMPTY) {
                if (input.test(inv.getStack(i))) {
                    count++;
                }
                else{
                    return ItemStack.EMPTY;
                }
            }
        }

        if(count == 0 || count % required != 0) return ItemStack.EMPTY;
        count = (count / required) * outputStack.getCount() ;
        if(count > 64) return ItemStack.EMPTY;
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
        return SalvageRecipeSerializer.INSTANCE;
    }
    /*
    public static class Type implements RecipeType<SalvageRecipe> {
        // Define ExampleRecipe.Type as a singleton by making its constructor private and exposing an instance.
        private Type() {}
        public static final Type INSTANCE = new Type();

        // This will be needed in step 4
        public static final String ID = "crafting_salvage";
    }
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    */
}
