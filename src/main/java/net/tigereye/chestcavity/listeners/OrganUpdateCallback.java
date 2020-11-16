package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.managers.ChestCavityManager;

import java.util.Map;

public interface OrganUpdateCallback {
    Event<OrganUpdateCallback> EVENT = EventFactory.createArrayBacked(OrganUpdateCallback.class,
            (listeners) -> (player, chestCavity) -> {
                for (OrganUpdateCallback listener : listeners) {
                    listener.onOrganUpdate(player,chestCavity);
                }
            });

    void onOrganUpdate(LivingEntity player, ChestCavityManager chestCavity);
}
