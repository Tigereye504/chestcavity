package net.tigereye.chestcavity.listeners;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

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

    private static MobEffectInstance ApplyBuffPurging(LivingEntity entity, ChestCavityInstance cc, MobEffectInstance instance) {
        if(cc.getOrganScore(CCOrganScores.BUFF_PURGING) > 0
                && ((CCStatusEffect)(instance.getEffect().value())).CC_IsBeneficial())
        {
            CCStatusEffectInstance ccInstance = (CCStatusEffectInstance) instance;
            ccInstance.CC_setDuration((int)(instance.getDuration()/
                    (1+(ChestCavity.config.BUFF_PURGING_DURATION_FACTOR*cc.getOrganScore(CCOrganScores.BUFF_PURGING)))));
        }
        return instance;
    }

    private static MobEffectInstance ApplyDetoxification(LivingEntity entity, ChestCavityInstance cc, MobEffectInstance instance) {
        if(cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.DETOXIFICATION) <= 0
        || cc.getOrganScore(CCOrganScores.DETOXIFICATION) == cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.DETOXIFICATION))
        {
            return instance;
        }
        CCStatusEffect ccStatusEffect = (CCStatusEffect)instance.getEffect().value();
        if(ccStatusEffect.CC_IsHarmful()){
            CCStatusEffectInstance ccInstance = (CCStatusEffectInstance) instance;
            float detoxRatio = cc.getOrganScore(CCOrganScores.DETOXIFICATION)/cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.DETOXIFICATION);
            ccInstance.CC_setDuration((int) Math.max(1,instance.getDuration() * 2 / (1 + detoxRatio)));
        }
        return instance;
    }

    private static MobEffectInstance ApplyFiltration(LivingEntity entity, ChestCavityInstance cc, MobEffectInstance instance) {
        float filtrationDiff = cc.getOrganScore(CCOrganScores.FILTRATION) - cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.FILTRATION);
        if(filtrationDiff > 0
                && instance.getEffect() == MobEffects.POISON)
        {
            CCStatusEffectInstance ccInstance = (CCStatusEffectInstance) instance;
            ccInstance.CC_setDuration((int)(instance.getDuration()/
                    (1+(ChestCavity.config.FILTRATION_DURATION_FACTOR*cc.getOrganScore(CCOrganScores.FILTRATION)))));
        }
        return instance;
    }

    private static MobEffectInstance ApplyWithered(LivingEntity entity, ChestCavityInstance cc, MobEffectInstance instance) {
        if(cc.getOrganScore(CCOrganScores.WITHERED) > 0
                && instance.getEffect() == MobEffects.WITHER)
        {
            CCStatusEffectInstance ccInstance = (CCStatusEffectInstance) instance;
            ccInstance.CC_setDuration((int)(instance.getDuration()/
                    (1+(ChestCavity.config.WITHERED_DURATION_FACTOR*cc.getOrganScore(CCOrganScores.WITHERED)))));
        }
        return instance;
    }
}
