package net.tigereye.chestcavity.recipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.util.OrganUtil;
public final class InfuseVenomGland extends CustomRecipe {
    public static final RecipeSerializer<InfuseVenomGland> SERIALIZER=new RecipeSerializer<>(com.mojang.serialization.MapCodec.unit(InfuseVenomGland::new),net.minecraft.network.codec.StreamCodec.unit(new InfuseVenomGland()));
    @Override public boolean matches(CraftingInput input,Level level){return !assemble(input).isEmpty();}
    @Override public ItemStack assemble(CraftingInput input){
        ItemStack gland=ItemStack.EMPTY,potion=ItemStack.EMPTY;
        for(ItemStack item:input.items()) {
            if(item.isEmpty())continue;
            if(item.is(CCItems.VENOM_GLAND)&&gland.isEmpty())gland=item;
            else if((item.is(Items.POTION)||item.is(Items.SPLASH_POTION)||item.is(Items.LINGERING_POTION))&&potion.isEmpty())potion=item;
            else return ItemStack.EMPTY;
        }
        if(gland.isEmpty()||potion.isEmpty())return ItemStack.EMPTY;
        ItemStack result=gland.copyWithCount(1);OrganUtil.setStatusEffects(result,potion);return result;
    }
    @Override public RecipeSerializer<InfuseVenomGland> getSerializer(){return SERIALIZER;}
}
