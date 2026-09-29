package net.tigereye.chestcavity.listeners;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import net.tigereye.chestcavity.util.Event;
import net.tigereye.chestcavity.util.EventFactory;

import net.tigereye.chestcavity.interfaces.ChestCavityEntity;

public interface OrganFoodCallback {
    Event<OrganFoodCallback> EVENT = EventFactory.createArrayBacked(OrganFoodCallback.class,
            (listeners) -> (item, foodComponent, cce, efs) -> {
                for (OrganFoodCallback listener : listeners) {
                    efs = listener.onEatFood(item, foodComponent, cce, efs);
                }
                return efs;
            });

    EffectiveFoodScores onEatFood(Item item, FoodProperties foodComponent, ChestCavityEntity cce, EffectiveFoodScores efs);
}
