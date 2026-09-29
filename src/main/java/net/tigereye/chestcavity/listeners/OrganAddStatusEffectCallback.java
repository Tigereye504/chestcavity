package net.tigereye.chestcavity.listeners;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import net.tigereye.chestcavity.util.Event;
import net.tigereye.chestcavity.util.EventFactory;

import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

public interface OrganAddStatusEffectCallback {
    Event<OrganAddStatusEffectCallback> EVENT = EventFactory.createArrayBacked(OrganAddStatusEffectCallback.class,
            (listeners) -> (player, chestCavity, statusEffectInstance) -> {
                for (OrganAddStatusEffectCallback listener : listeners) {
                    statusEffectInstance = listener.onAddStatusEffect(player,chestCavity,statusEffectInstance);
                }
                return statusEffectInstance;
            });

    MobEffectInstance onAddStatusEffect(LivingEntity player, ChestCavityInstance chestCavity, MobEffectInstance statusEffectInstance);
}
