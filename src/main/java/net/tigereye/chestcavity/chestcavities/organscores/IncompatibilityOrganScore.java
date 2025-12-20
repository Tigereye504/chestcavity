package net.tigereye.chestcavity.chestcavities.organscores;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.listeners.ChestCavityUpdateCallback;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.UUID;

public class IncompatibilityOrganScore implements OrganScore{
    private final Identifier ID;

    public IncompatibilityOrganScore(Identifier id){
        this.ID = id;
    }
    @Override
    public Identifier getID() {
        return ID;
    }

    @Override
    public void onChestCavityUpdate(LivingEntity entity, ChestCavityInstance cc, float oldScore, float newScore, float defaultScore){
        if(oldScore != newScore) {
            try {
                entity.removeStatusEffect(CCStatusEffects.ORGAN_REJECTION);
            }
            catch(Exception ignore){}
        }
    }

    @Override
    public void attachEventHooks() {
        ChestCavityUpdateCallback.registerOrganScore(this);
    }
}
