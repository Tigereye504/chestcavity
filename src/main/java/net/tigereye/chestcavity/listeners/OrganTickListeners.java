package net.tigereye.chestcavity.listeners;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.tigereye.chestcavity.interfaces.CCPlayerEntityInterface;
import net.tigereye.chestcavity.interfaces.CCStatusEffect;
import net.tigereye.chestcavity.interfaces.CCStatusEffectInstance;
import net.tigereye.chestcavity.items.CCItems;

import java.util.Map;

public class OrganTickListeners {
    private static final int HEARTBLEED_SPEED = 20; //how fast you die from lacking a heart
    private static final int LIVER_SPEED = 40; //how often the liver purifies status effects
    private static final int KIDNEY_SPEED = 59; //how often the kidneys prevent blood poisoning, avoid clean multiples of LIVERSPEED

    public static void register(){
        OrganTickCallback.EVENT.register(OrganTickListeners::TickHeart);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickKidney);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickLiver);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickLung);
    }

    public static void TickHeart(PlayerEntity player,ChestCavityListener chestCavity){
        if (chestCavity.getOrganScore(CCItems.ORGANS_HEART) <= 0)
        {
            int heartTimer =((CCPlayerEntityInterface)player).getCCHeartTimer()+1;
            ((CCPlayerEntityInterface)player).setCCHeartTimer(heartTimer);
            if(heartTimer % HEARTBLEED_SPEED == 0){
                player.damage(DamageSource.STARVE, (heartTimer / HEARTBLEED_SPEED));
            }
        }
        else{
            ((CCPlayerEntityInterface)player).setCCHeartTimer(0);
        }
    }

    public static void TickKidney(PlayerEntity player,ChestCavityListener chestCavity){
        if(player.getEntityWorld().isClient()){ //this is a server-side event
            return;
        }
        float kidneyScore = chestCavity.getOrganScore(CCItems.ORGANS_KIDNEY);
        if(kidneyScore < 2)
        {
            int kidneyTimer =((CCPlayerEntityInterface)player).getCCKidneyTimer()+1;
            if(kidneyTimer >= KIDNEY_SPEED){
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, (int)(24*(2-kidneyScore))));
                kidneyTimer = 0;
            }
            ((CCPlayerEntityInterface)player).setCCKidneyTimer(kidneyTimer);
        }
    }

    public static void TickLiver(PlayerEntity player,ChestCavityListener chestCavity){
        if(player.getEntityWorld().isClient()){ //this is a server-side event
            return;
        }
        float liverScore = chestCavity.getOrganScore(CCItems.ORGANS_LIVER);
        int newDur;
        int liverTimer = ((CCPlayerEntityInterface)player).getCCLiverTimer()+1;
        if(liverTimer >= LIVER_SPEED && liverScore != 1)
        {
            for(Map.Entry<StatusEffect,StatusEffectInstance> iter : player.getActiveStatusEffects().entrySet()){
                if (((CCStatusEffect)iter.getValue().getEffectType()).CC_IsHarmful()) {
                    newDur = Math.max(0, iter.getValue().getDuration() + Math.round(LIVER_SPEED * (1 - liverScore) / 2));
                    System.out.println("updating status effect: old duration "+iter.getValue().getDuration()+", new duration "+newDur+".");
                    ((CCStatusEffectInstance) iter.getValue()).CC_setDuration(newDur);
                }
            }
            liverTimer = 0;
        }
        ((CCPlayerEntityInterface)player).setCCLiverTimer(liverTimer);
    }

    public static void TickLung(PlayerEntity player,ChestCavityListener chestCavity){
        if (chestCavity.getOrganScore(CCItems.ORGANS_LUNG) <= 0)
        {
            player.damage(DamageSource.DROWN, 1);
        }
    }
}
