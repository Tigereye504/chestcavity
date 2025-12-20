package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.chestcavities.organscores.OrganScore;

public interface ChestCavityUpdateCallback {
    Event<ChestCavityUpdateCallback> EVENT = EventFactory.createArrayBacked(ChestCavityUpdateCallback.class,
            (listeners) -> (player, chestCavity) -> {
                for (ChestCavityUpdateCallback listener : listeners) {
                    listener.onOrganUpdate(player,chestCavity);
                }
            });

    void onOrganUpdate(LivingEntity player, ChestCavityInstance chestCavity);

    static void registerOrganScore(OrganScore organScore){
        organScore.getID();
        ChestCavityUpdateCallback.EVENT.register((player, chestCavity) -> {
            float oldScore = chestCavity.getOldOrganScore(organScore);
            float newScore = chestCavity.getOrganScore(organScore);
            float defaultScore = chestCavity.getChestCavityType().getDefaultOrganScore(organScore);
            organScore.onChestCavityUpdate(player, chestCavity, oldScore, newScore, defaultScore);
        });
    }
}
