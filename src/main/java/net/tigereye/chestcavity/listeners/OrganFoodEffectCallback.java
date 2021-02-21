package net.tigereye.chestcavity.listeners;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

import java.util.ArrayList;
import java.util.List;

public interface OrganFoodEffectCallback {
    Event<OrganFoodEffectCallback> EVENT = EventFactory.createArrayBacked(OrganFoodEffectCallback.class,
            (listeners) -> (list,stack,world,targetEntity,cc) -> {
                for (OrganFoodEffectCallback listener : listeners) {
                    list = listener.onApplyFoodEffects(list, stack, world, targetEntity, cc);
                }
                return list;
            });

    List<Pair<StatusEffectInstance, Float>> onApplyFoodEffects(List<Pair<StatusEffectInstance, Float>> list, ItemStack stack, World world, LivingEntity targetEntity, ChestCavityInstance cc);
}
