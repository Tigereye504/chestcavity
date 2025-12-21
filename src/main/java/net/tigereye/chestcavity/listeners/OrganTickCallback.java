package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.chestcavities.organscores.OrganScore;

public interface OrganTickCallback {
    Event<OrganTickCallback> EVENT = EventFactory.createArrayBacked(OrganTickCallback.class,
            (listeners) -> (player, chestCavity) -> {
                for (OrganTickCallback listener : listeners) {
                    listener.onOrganTick(player,chestCavity);
                }
            });

    void onOrganTick(LivingEntity player, ChestCavityInstance chestCavity);

    static void registerOrganScore(OrganScore organScore){
        organScore.getID();
        OrganTickCallback.EVENT.register((player, chestCavity) -> {
            float score = chestCavity.getOrganScore(organScore);
            float defaultScore = chestCavity.getChestCavityType().getDefaultOrganScore(organScore);
            organScore.onChestCavityTick(player, chestCavity, score, defaultScore);
        });
    }
}
