package net.tigereye.chestcavity.chestcavities.organscores;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.listeners.OrganTickCallback;
import net.tigereye.chestcavity.listeners.OrganUpdateCallback;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;

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
    public void onChestCavityTick(LivingEntity entity, ChestCavityInstance cc, float score, float defaultScore){
        if(entity.getEntityWorld().isClient() || ChestCavity.config.DISABLE_ORGAN_REJECTION){ //this is a server-side event
            return;
        }
        if(score > 0)
        {
            if(!entity.hasStatusEffect(CCStatusEffects.ORGAN_REJECTION)){
                entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.ORGAN_REJECTION, (int)(ChestCavity.config.ORGAN_REJECTION_RATE /score),0, false, true, true));
            }
        }
    }

    @Override
    public void attachEventHooks() {
        OrganUpdateCallback.registerOrganScore(this);
        OrganTickCallback.registerOrganScore(this);
    }
}
