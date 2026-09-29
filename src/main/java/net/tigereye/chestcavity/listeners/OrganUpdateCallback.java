package net.tigereye.chestcavity.listeners;
import net.minecraft.world.entity.LivingEntity;

import net.tigereye.chestcavity.util.Event;
import net.tigereye.chestcavity.util.EventFactory;

import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

public interface OrganUpdateCallback {
    Event<OrganUpdateCallback> EVENT = EventFactory.createArrayBacked(OrganUpdateCallback.class,
            (listeners) -> (player, chestCavity) -> {
                for (OrganUpdateCallback listener : listeners) {
                    listener.onOrganUpdate(player,chestCavity);
                }
            });

    void onOrganUpdate(LivingEntity player, ChestCavityInstance chestCavity);
}
