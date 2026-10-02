package net.tigereye.chestcavity.mixin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.world.entity.*;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.chestcavities.instance.*;
import net.tigereye.chestcavity.util.*;

@Mixin(net.minecraft.world.food.FoodProperties.class)
public abstract class MixinFoodProperties {
    @Redirect(method="onConsume",at=@At(value="INVOKE",target="Lnet/minecraft/world/food/FoodData;eat(Lnet/minecraft/world/food/FoodProperties;)V"))
    private void chestcavity$food(net.minecraft.world.food.FoodData hunger,net.minecraft.world.food.FoodProperties food,net.minecraft.world.level.Level level,LivingEntity user,net.minecraft.world.item.ItemStack stack,net.minecraft.world.item.component.Consumable consumable){
        var cce=(ChestCavityEntity)user;var cc=cce.getChestCavityInstance();
        var scores=new net.tigereye.chestcavity.listeners.EffectiveFoodScores(cc.getOrganScore(net.tigereye.chestcavity.registration.CCOrganScores.DIGESTION),cc.getOrganScore(net.tigereye.chestcavity.registration.CCOrganScores.NUTRITION));
        scores=net.tigereye.chestcavity.listeners.OrganFoodCallback.EVENT.invoker().onEatFood(stack.getItem(),food,cce,scores);
        int nutrition=ChestCavityUtil.applyDigestion(cc,scores.digestion,food.nutrition());
        float saturation=ChestCavityUtil.applyNutrition(cc,scores.nutrition,food.saturation());
        hunger.eat(new net.minecraft.world.food.FoodProperties(nutrition,saturation,food.canAlwaysEat()));
    }
}
