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
        OrganAddStatusEffectCallback.EVENT.register(OrganAddStatusEffectListeners::ApplyBuffPurging);
        OrganAddStatusEffectCallback.EVENT.register(OrganAddStatusEffectListeners::ApplyDetoxification);
        OrganAddStatusEffectCallback.EVENT.register(OrganAddStatusEffectListeners::ApplyWithered);
    }

    private static StatusEffectInstance ApplyBuffPurging(LivingEntity entity, ChestCavityInstance cc, StatusEffectInstance instance) {
        if(cc.getOrganScore(CCOrganScores.BUFF_PURGING) > 0
                && ((CCStatusEffect)(instance.getEffectType())).CC_IsBeneficial())
        {
            CCStatusEffectInstance ccInstance = (CCStatusEffectInstance) instance;
            ccInstance.CC_setDuration((int)(instance.getDuration()/
                    (1+(ChestCavity.config.BUFF_PURGING_DURATION_FACTOR*cc.getOrganScore(CCOrganScores.BUFF_PURGING)))));
        }
        return instance;
    }

    private static StatusEffectInstance ApplyDetoxification(LivingEntity entity, ChestCavityInstance cc, StatusEffectInstance instance) {
        float defaultDetox = cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.DETOXIFICATION);
        float detox = cc.getOrganScore(CCOrganScores.DETOXIFICATION);

        float extraFiltration = cc.getOrganScore(CCOrganScores.FILTRATION) - cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.FILTRATION);
        if (extraFiltration > 0) {
            detox += extraFiltration;
        }

        if(defaultDetox <= 0 || detox == defaultDetox)
        {
            return instance;
        }
        CCStatusEffect ccStatusEffect = (CCStatusEffect)instance.getEffectType();
        if(ccStatusEffect.CC_IsHarmful()){
            CCStatusEffectInstance ccInstance = (CCStatusEffectInstance) instance;
            float detoxRatio = detox/defaultDetox;
            ccInstance.CC_setDuration((int) Math.max(1,instance.getDuration() * 2 / (1 + detoxRatio)));
        }
        return instance;
    }

    private static StatusEffectInstance ApplyFiltration(LivingEntity entity, ChestCavityInstance cc, StatusEffectInstance instance) {
        float filtrationDiff = cc.getOrganScore(CCOrganScores.FILTRATION) - cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.FILTRATION);
        if(filtrationDiff > 0
                && instance.getEffectType() == StatusEffects.POISON)
        {
            CCStatusEffectInstance ccInstance = (CCStatusEffectInstance) instance;
            ccInstance.CC_setDuration((int)(instance.getDuration()/
                    (1+(ChestCavity.config.FILTRATION_DURATION_FACTOR*cc.getOrganScore(CCOrganScores.FILTRATION)))));
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
