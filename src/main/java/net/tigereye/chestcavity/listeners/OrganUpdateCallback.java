package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import java.util.Map;

public interface OrganUpdateCallback {
    Event<OrganUpdateCallback> EVENT = EventFactory.createArrayBacked(OrganUpdateCallback.class,
            (listeners) -> (player, oldScores, newScores) -> {
                for (OrganUpdateCallback listener : listeners) {
                    listener.onOrganUpdate(player,oldScores,newScores);
                }
            });

    void onOrganUpdate(LivingEntity player, Map<Identifier,Float> oldScores, Map<Identifier,Float> newScores);
}
