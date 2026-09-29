package net.tigereye.chestcavity.listeners;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.mojang.datafixers.util.Pair;
import net.tigereye.chestcavity.util.Event;
import net.tigereye.chestcavity.util.EventFactory;

import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

import java.util.List;

public interface OrganFoodEffectCallback {
    Event<OrganFoodEffectCallback> EVENT = EventFactory.createArrayBacked(OrganFoodEffectCallback.class,
            (listeners) -> (list,stack,world,targetEntity,cc) -> {
                for (OrganFoodEffectCallback listener : listeners) {
                    list = listener.onApplyFoodEffects(list, stack, world, targetEntity, cc);
                }
                return list;
            });

    List<Pair<MobEffectInstance, Float>> onApplyFoodEffects(List<Pair<MobEffectInstance, Float>> list, ItemStack stack, Level world, LivingEntity targetEntity, ChestCavityInstance cc);
}
