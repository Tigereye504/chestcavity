package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

public interface OrganActivationCallback {
    Event<OrganActivationCallback> EVENT = EventFactory.createArrayBacked(OrganActivationCallback.class,
            (listeners) -> (player, chestCavity) -> {
                for (OrganActivationCallback listener : listeners) {
                    listener.onOrganActivation(player,chestCavity);
                }
            });

    void onOrganActivation(LivingEntity player, ChestCavityInstance chestCavity);
}
