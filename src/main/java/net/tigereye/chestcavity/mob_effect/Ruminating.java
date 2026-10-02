package net.tigereye.chestcavity.mob_effect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCItems;

public class Ruminating extends CCStatusEffect{

    public Ruminating(){
        super(MobEffectCategory.BENEFICIAL, 0xC8FF00);
    }

    @Override public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % ChestCavity.config.RUMINATION_TIME == 1;
    }

    @Override public boolean applyEffectTick(net.minecraft.server.level.ServerLevel level, LivingEntity entity, int amplifier) {
        if(entity instanceof Player){
            if(!(entity.level().isClientSide())) {
                FoodData hungerManager = ((Player) entity).getFoodData();
                ItemStack cud=new ItemStack(CCItems.CUD);
                cud.get(net.minecraft.core.component.DataComponents.FOOD).onConsume(level,entity,cud,net.minecraft.world.item.component.Consumables.DEFAULT_FOOD);
            }
        }
        return true;
    }
}
