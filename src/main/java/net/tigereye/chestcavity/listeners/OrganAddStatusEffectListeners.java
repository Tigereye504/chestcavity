package net.tigereye.chestcavity.listeners;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.CCStatusEffect;
import net.tigereye.chestcavity.interfaces.CCStatusEffectInstance;
import net.tigereye.chestcavity.registration.CCOrganScores;

public class OrganAddStatusEffectListeners {

    public static void register(){
        OrganAddStatusEffectCallback.EVENT.register(OrganAddStatusEffectListeners::ApplyDetoxification);
        OrganAddStatusEffectCallback.EVENT.register(OrganAddStatusEffectListeners::ApplyWithered);
    }

    private static StatusEffectInstance ApplyDetoxification(LivingEntity entity, ChestCavityInstance cc, StatusEffectInstance instance) {
        if(cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.DETOXIFICATION) <= 0)
                //|| entity.getEntityWorld().isClient())
        { //this is a server-side event only for things that use detox
            return instance;
        }
        CCStatusEffect ccStatusEffect = (CCStatusEffect)instance.getEffectType();
        if(ccStatusEffect.CC_IsHarmful()){
            CCStatusEffectInstance ccInstance = (CCStatusEffectInstance) instance;
            float detoxRatio = cc.getOrganScore(CCOrganScores.DETOXIFICATION)/cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.DETOXIFICATION);
            ccInstance.CC_setDuration((int) (instance.getDuration() * 2 / (1 + detoxRatio)));
        }
        return instance;
    }

    private static StatusEffectInstance ApplyWithered(LivingEntity entity, ChestCavityInstance cc, StatusEffectInstance instance) {
        if(cc.getOrganScore(CCOrganScores.WITHERED) > 0
                && instance.getEffectType() == StatusEffects.WITHER)
        {
            CCStatusEffectInstance ccInstance = (CCStatusEffectInstance) instance;
            ccInstance.CC_setDuration((int)(instance.getDuration()/
                    (1+(ChestCavity.config.WITHERED_DURATION_FACTOR*cc.getOrganScore(CCOrganScores.WITHERED)))));
        }
        return instance;
    }
}
