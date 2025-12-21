package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.chestcavities.organscores.OrganScore;

public interface OrganUpdateCallback {
    Event<OrganUpdateCallback> EVENT = EventFactory.createArrayBacked(OrganUpdateCallback.class,
            (listeners) -> (player, chestCavity) -> {
                for (OrganUpdateCallback listener : listeners) {
                    listener.onOrganUpdate(player,chestCavity);
                }
            });

    void onOrganUpdate(LivingEntity player, ChestCavityInstance chestCavity);

    static void registerOrganScore(OrganScore organScore){
        organScore.getID();
        OrganUpdateCallback.EVENT.register((player, chestCavity) -> {
            float oldScore = chestCavity.getOldOrganScore(organScore);
            float newScore = chestCavity.getOrganScore(organScore);
            float defaultScore = chestCavity.getChestCavityType().getDefaultOrganScore(organScore);
            organScore.onChestCavityUpdate(player, chestCavity, oldScore, newScore, defaultScore);
        });
    }
}
