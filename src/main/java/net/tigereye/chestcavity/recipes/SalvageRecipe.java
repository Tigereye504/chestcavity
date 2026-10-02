package net.tigereye.chestcavity.recipes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.*;
import net.minecraft.world.level.Level;
import java.util.List;
public record SalvageRecipe(Ingredient input,int required,ItemStackTemplate result) implements CraftingRecipe {
    public static final RecipeSerializer<SalvageRecipe> SERIALIZER=new RecipeSerializer<>(
        RecordCodecBuilder.mapCodec(i -> i.group(Ingredient.CODEC.fieldOf("ingredient").forGetter(SalvageRecipe::input),Codec.intRange(1,9).optionalFieldOf("required",1).forGetter(SalvageRecipe::required),ItemStackTemplate.CODEC.fieldOf("result").forGetter(SalvageRecipe::result)).apply(i,SalvageRecipe::new)),
        StreamCodec.composite(Ingredient.CONTENTS_STREAM_CODEC,SalvageRecipe::input,ByteBufCodecs.VAR_INT,SalvageRecipe::required,ItemStackTemplate.STREAM_CODEC,SalvageRecipe::result,SalvageRecipe::new));
    public Ingredient getInput(){return input;}
    public int getRequired(){return required;}
    public ItemStack getOutput(){return result.create();}
    public List<Ingredient> getIngredients(){return java.util.Collections.nCopies(required,input);}
    @Override public boolean matches(CraftingInput inv,Level level){return !assemble(inv).isEmpty();}
    @Override public ItemStack assemble(CraftingInput inv){
        int count=0;for(ItemStack item:inv.items()) if(!item.isEmpty()){if(!input.test(item))return ItemStack.EMPTY;count++;}
        if(count==0||count%required!=0)return ItemStack.EMPTY;
        ItemStack out=getOutput();count=count/required*out.getCount();if(count>out.getMaxStackSize())return ItemStack.EMPTY;out.setCount(count);return out;
    }
    @Override public RecipeSerializer<SalvageRecipe> getSerializer(){return SERIALIZER;}
    @Override public CraftingBookCategory category(){return CraftingBookCategory.MISC;}
    @Override public String group(){return "";}
    @Override public boolean showNotification(){return true;}
    @Override public PlacementInfo placementInfo(){return PlacementInfo.create(getIngredients());}
    @Override public List<RecipeDisplay> display(){return List.of(new ShapelessCraftingRecipeDisplay(getIngredients().stream().map(Ingredient::display).toList(),new SlotDisplay.ItemStackSlotDisplay(result),new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)));}
}
