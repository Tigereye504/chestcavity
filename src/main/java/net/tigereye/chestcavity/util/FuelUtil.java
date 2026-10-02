package net.tigereye.chestcavity.util;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.parameters.*;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CookingFuel;
public final class FuelUtil {
    public static int burnDuration(LivingEntity entity,ItemStack stack){
        var level=(net.minecraft.server.level.ServerLevel)entity.level();
        var params=new LootParams.Builder(level).withParameter(LootContextParams.ORIGIN,entity.position()).withParameter(LootContextParams.THIS_ENTITY,entity).create(LootContextParamSets.CHEST);
        var context=new LootContext.Builder(params).create(java.util.Optional.empty());
        int duration=ResolvableInt.getFromItem(stack,DataComponents.COOKING_FUEL,CookingFuel::burnTime,context,0);
        return net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack,duration,net.minecraft.world.item.crafting.RecipeType.SMELTING);
    }
}
