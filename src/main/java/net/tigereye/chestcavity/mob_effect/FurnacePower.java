package net.tigereye.chestcavity.mob_effect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;

import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCItems;

import java.util.Optional;

public class FurnacePower extends CCStatusEffect{

    public FurnacePower(){
        super(MobEffectCategory.BENEFICIAL, 0xC8FF00);
    }

    @Override public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override public boolean applyEffectTick(net.minecraft.server.level.ServerLevel level, LivingEntity entity, int amplifier) {
        if(entity instanceof Player){
            if(!(entity.level().isClientSide())) {
                Optional<ChestCavityEntity> optional = ChestCavityEntity.of(entity);
                if (optional.isPresent()) {
                    ChestCavityEntity cce = optional.get();
                    ChestCavityInstance cc = cce.getChestCavityInstance();
                    cc.furnaceProgress++;
                    if (cc.furnaceProgress >= 200) {
                        cc.furnaceProgress = 0;
                        FoodData hungerManager = ((Player) entity).getFoodData();
                        ItemStack furnaceFuel = new ItemStack(CCItems.FURNACE_POWER);
                        for (int i = 0; i <= amplifier; i++) {
                            furnaceFuel.get(net.minecraft.core.component.DataComponents.FOOD).onConsume(level,entity,furnaceFuel,net.minecraft.world.item.component.Consumables.DEFAULT_FOOD);
                        }
                    }
                }
            }
        }
        return true;
    }
}
