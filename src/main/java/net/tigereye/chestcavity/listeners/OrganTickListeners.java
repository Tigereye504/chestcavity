package net.tigereye.chestcavity.listeners;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.items.CreeperAppendix;
import net.tigereye.chestcavity.items.EnderKidney;
import net.tigereye.chestcavity.items.SilkGland;
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

        OrganTickCallback.EVENT.register(OrganTickListeners::TickCreepiness);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickHydrophobia);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickSilk);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickGlowing);
    }

    public static void TickHeart(LivingEntity entity, ChestCavityManager chestCavity){
        if (chestCavity.getOrganScore(CCOrganScores.HEART) <= 0 && chestCavity.getDefaultOrganScore(CCOrganScores.HEART) != 0)
        {
            chestCavity.setHeartTimer(chestCavity.getHeartTimer()+1);
            if(chestCavity.getHeartTimer() % ChestCavity.config.HEARTBLEED_RATE == 0){
                entity.damage(CCDamageSource.HEARTBLEED, (chestCavity.getHeartTimer() / ChestCavity.config.HEARTBLEED_RATE));
            }
        }
        else{
            chestCavity.setHeartTimer(0);
        }
    }

    public static void TickKidney(LivingEntity entity,ChestCavityManager chestCavity){
        if(entity.getEntityWorld().isClient()){ //this is a server-side event
            return;
        }
        if(chestCavity.getDefaultOrganScore(CCOrganScores.KIDNEY) <= 0){ //don't bother if the target doesn't need kidneys
            return;
        }
        float KidneyRatio = chestCavity.getOrganScore(CCOrganScores.KIDNEY)/chestCavity.getDefaultOrganScore(CCOrganScores.KIDNEY);
        if(KidneyRatio < 1)
        {
            int kidneyTimer =chestCavity.getKidneyTimer()+1;
            if(kidneyTimer >= ChestCavity.config.KIDNEY_RATE){
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, (int)(48*(1-KidneyRatio))));
                kidneyTimer = 0;
            }
            chestCavity.setKidneyTimer(kidneyTimer);
        }
    }

    public static void TickLiver(LivingEntity entity,ChestCavityManager chestCavity){
        if(entity.getEntityWorld().isClient()){ //this is a server-side event
            return;
        }
        if(chestCavity.getDefaultOrganScore(CCOrganScores.LIVER) <= 0){ //don't bother if the target doesn't need a liver
            return;
        }
        float liverRatio = chestCavity.getOrganScore(CCOrganScores.LIVER)/chestCavity.getDefaultOrganScore(CCOrganScores.LIVER);
        int newDur;
        int liverTimer = chestCavity.getLiverTimer()+1;
        if(liverTimer >= ChestCavity.config.LIVER_RATE && liverRatio != 1)
        {
            for(Map.Entry<StatusEffect,StatusEffectInstance> iter : entity.getActiveStatusEffects().entrySet()){
                if (((CCStatusEffect)iter.getValue().getEffectType()).CC_IsHarmful()) {
                    newDur = Math.max(0, iter.getValue().getDuration() + Math.round(ChestCavity.config.LIVER_RATE * (1 - liverRatio) / 2));
                    ((CCStatusEffectInstance) iter.getValue()).CC_setDuration(newDur);
                }
            }
            liverTimer = 0;
        }
        chestCavity.setLiverTimer(liverTimer);
    }

    public static void TickLung(LivingEntity entity,ChestCavityManager chestCavity){
        if (chestCavity.getOrganScore(CCOrganScores.LUNG) <= 0 && chestCavity.getDefaultOrganScore(CCOrganScores.LUNG) != 0)
        {
            entity.damage(DamageSource.DROWN, 1);
        }
    }

    public static void TickCreepiness(LivingEntity entity,ChestCavityManager chestCavity){
        if(chestCavity.getOrganScore(CCOrganScores.CREEPINESS) < 1){
            return;
        }
        if(entity.hasStatusEffect(CCStatusEffects.EXPLOSION_COOLDOWN)){
            return;
        }
        else if(entity.getPose() == EntityPose.CROUCHING /*|| entity.isOnFire()*/){
            float explosion_yield = chestCavity.getOrganScore(CCOrganScores.EXPLOSIVE);
            chestCavity.destroyOrgansWithKey(CCOrganScores.EXPLOSIVE);
            CreeperAppendix.explode(entity, explosion_yield);
            if(entity.isAlive()) {
                entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.EXPLOSION_COOLDOWN, ChestCavity.config.EXPLOSION_COOLDOWN, 0, false, false, true));
            }
        }
    }

    public static void TickSilk(LivingEntity entity,ChestCavityManager chestCavity){
        if(chestCavity.getOrganScore(CCOrganScores.SILK) == 0){
            return;
        }
        if(entity.hasStatusEffect(CCStatusEffects.SILK_COOLDOWN)){
            return;
        }
        else if(entity.getPose() == EntityPose.CROUCHING){
            if(SilkGland.spinWeb(entity,chestCavity.getOrganScore(CCOrganScores.SILK))) {
                entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.SILK_COOLDOWN,ChestCavity.config.SILK_COOLDOWN,0,false,false,true));
            }
        }
    }

    public static void TickHydrophobia(LivingEntity entity, ChestCavityManager chestCavity){
        if(chestCavity.getOrganScore(CCOrganScores.HYDROPHOBIA) == 0                //do nothing if the target isn't hydrophobic
            || chestCavity.getDefaultOrganScore(CCOrganScores.HYDROPHOBIA) != 0){   //do nothing if they are by default, otherwise endermen will spaz even harder
            return;                                                                 //TODO: make enderman water-teleporting dependent on hydrophobia
        }
        if(entity.isTouchingWaterOrRain()){
            EnderKidney.teleportRandomly(entity);
        }
    }

    public static void TickIncompatibility(LivingEntity entity,ChestCavityManager chestCavity){
        if(entity.getEntityWorld().isClient()){ //this is a server-side event
            return;
        }
        float incompatibility = chestCavity.getOrganScore(CCOrganScores.INCOMPATIBILITY);
        if(incompatibility > 0)
        {
            if(!entity.hasStatusEffect(CCStatusEffects.ORGAN_REJECTION)){
                entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.ORGAN_REJECTION, (int)(ChestCavity.config.ORGAN_REJECTION_RATE /incompatibility),0, false, true, true));
            }
        }
    }

    public static void TickGlowing(LivingEntity entity,ChestCavityManager chestCavity){
        if(entity.getEntityWorld().isClient()){ //this is a server-side event
            return;
        }
        float glowing = chestCavity.getOrganScore(CCOrganScores.GLOWING);
        if(glowing > 0)
        {
            if(!entity.hasStatusEffect(StatusEffects.GLOWING)){
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200,0, false, true, true));
            }
        }
    }
}