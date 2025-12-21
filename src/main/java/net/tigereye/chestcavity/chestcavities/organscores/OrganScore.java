package net.tigereye.chestcavity.chestcavities.organscores;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

public interface OrganScore {

    Identifier getID();
    default void onChestCavityUpdate(LivingEntity entity, ChestCavityInstance cc, float oldScore, float newScore, float defaultScore){}
    default void onChestCavityTick(LivingEntity entity, ChestCavityInstance cc, float score, float defaultScore){}
    void attachEventHooks();
}
