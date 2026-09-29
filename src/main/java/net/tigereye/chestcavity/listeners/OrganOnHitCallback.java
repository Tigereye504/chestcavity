package net.tigereye.chestcavity.listeners;

import net.minecraft.world.entity.LivingEntity;

import net.tigereye.chestcavity.util.Event;
import net.tigereye.chestcavity.util.EventFactory;

import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

public interface OrganOnHitCallback {
    Event<OrganOnHitCallback> EVENT = EventFactory.createArrayBacked(OrganOnHitCallback.class,
            (listeners) -> (attacker, target, chestCavity) -> {
                for (OrganOnHitCallback listener : listeners) {
                    listener.onHit(attacker, target, chestCavity);
                }
            });

    void onHit(LivingEntity attacker, LivingEntity target, ChestCavityInstance chestCavity);
}
