package net.tigereye.chestcavity.listeners;
import net.minecraft.world.entity.LivingEntity;

import net.tigereye.chestcavity.util.Event;
import net.tigereye.chestcavity.util.EventFactory;

import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

public interface OrganTickCallback {
    Event<OrganTickCallback> EVENT = EventFactory.createArrayBacked(OrganTickCallback.class,
            (listeners) -> (player, chestCavity) -> {
                for (OrganTickCallback listener : listeners) {
                    listener.onOrganTick(player,chestCavity);
                }
            });

    void onOrganTick(LivingEntity player, ChestCavityInstance chestCavity);
}
