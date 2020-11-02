package net.tigereye.chestcavity.listeners;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCDamageSource;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.managers.ChestCavityManager;
import net.tigereye.chestcavity.interfaces.CCStatusEffect;
import net.tigereye.chestcavity.interfaces.CCStatusEffectInstance;
import net.tigereye.chestcavity.registration.CCStatusEffects;

import java.util.Map;

public class OrganTickListeners {

    public static void register(){
        OrganTickCallback.EVENT.register(OrganTickListeners::TickHeart);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickKidney);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickLiver);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickLung);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickIncompatibility);
    }

    public static void TickHeart(LivingEntity player, ChestCavityManager chestCavity){
        if (chestCavity.getOrganScore(CCOrganScores.HEART) <= 0)
        {
            chestCavity.setHeartTimer(chestCavity.getHeartTimer()+1);
            if(chestCavity.getHeartTimer() % ChestCavity.config.HEARTBLEED_RATE == 0){
                player.damage(CCDamageSource.HEARTBLEED, (chestCavity.getHeartTimer() / ChestCavity.config.HEARTBLEED_RATE));
            }
        }
        else{
            chestCavity.setHeartTimer(0);
        }
    }

    public static void TickKidney(LivingEntity player,ChestCavityManager chestCavity){
        if(player.getEntityWorld().isClient()){ //this is a server-side event
            return;
        }
        float kidneyScore = chestCavity.getOrganScore(CCOrganScores.KIDNEY);
        if(kidneyScore < 2)
        {
            int kidneyTimer =chestCavity.getKidneyTimer()+1;
            if(kidneyTimer >= ChestCavity.config.KIDNEY_RATE){
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, (int)(24*(2-kidneyScore))));
                kidneyTimer = 0;
            }
            chestCavity.setKidneyTimer(kidneyTimer);
        }
    }

    public static void TickLiver(LivingEntity player,ChestCavityManager chestCavity){
        if(player.getEntityWorld().isClient()){ //this is a server-side event
            return;
        }
        float liverScore = chestCavity.getOrganScore(CCOrganScores.LIVER);
        int newDur;
        int liverTimer = chestCavity.getLiverTimer()+1;
        if(liverTimer >= ChestCavity.config.LIVER_RATE && liverScore != 1)
        {
            for(Map.Entry<StatusEffect,StatusEffectInstance> iter : player.getActiveStatusEffects().entrySet()){
                if (((CCStatusEffect)iter.getValue().getEffectType()).CC_IsHarmful()) {
                    newDur = Math.max(0, iter.getValue().getDuration() + Math.round(ChestCavity.config.LIVER_RATE * (1 - liverScore) / 2));
                    ((CCStatusEffectInstance) iter.getValue()).CC_setDuration(newDur);
                }
            }
            liverTimer = 0;
        }
        chestCavity.setLiverTimer(liverTimer);
    }

    public static void TickLung(LivingEntity player,ChestCavityManager chestCavity){
        if (chestCavity.getOrganScore(CCOrganScores.LUNG) <= 0)
        {
            player.damage(DamageSource.DROWN, 1);
        }
    }

    public static void TickIncompatibility(LivingEntity player,ChestCavityManager chestCavity){
        if(player.getEntityWorld().isClient()){ //this is a server-side event
            return;
        }
        float incompatibility = chestCavity.getOrganScore(CCOrganScores.INCOMPATIBILITY);
        if(incompatibility > 0)
        {//TODO: glowing is a placeholder. replace with organ rejection status effect
            if(!player.hasStatusEffect(CCStatusEffects.ORGAN_REJECTION)){
                player.addStatusEffect(new StatusEffectInstance(CCStatusEffects.ORGAN_REJECTION, (int)(ChestCavity.config.ORGAN_REJECTION_RATE /incompatibility),0, false, true, true));
            }
        }
    }
}