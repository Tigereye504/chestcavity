package net.tigereye.chestcavity.crossmod.wendigoism;

import moriyashiine.wendigoism.api.accessor.WendigoAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.ChestCavityManager;
import net.tigereye.chestcavity.listeners.ChestCavityListener;
import net.tigereye.chestcavity.listeners.OrganTickCallback;
import net.tigereye.chestcavity.listeners.OrganUpdateCallback;

import java.util.Map;
import java.util.UUID;

public class CCWendigoismListeners {
    private static final Identifier WENDIGOISM_TRACKER = new Identifier(ChestCavity.MODID, "wendigoism_tracker");
    public static final Identifier WENDIGOISM_TARGET = new Identifier(ChestCavity.MODID, "wendigoism_target");
    private static final UUID cannibalHeartID = UUID.fromString("140317b9-74be-40cb-802e-95971fbc6d29");

    private static final float BONUS_HEART_PER_100_WENDIGOISM = .25f;

    public static void register(){
        OrganUpdateCallback.EVENT.register(CCWendigoismListeners::UpdateCannibalHeart);
        OrganTickCallback.EVENT.register(CCWendigoismListeners::TickTetheredCannibalHeart);
        OrganTickCallback.EVENT.register(CCWendigoismListeners::TickCannibalHeart);
    }

    private static void UpdateCannibalHeart(LivingEntity entity, Map<Identifier, Float> oldScores, Map<Identifier, Float> newScores) {
        if(oldScores.getOrDefault(CCWendigoismItems.ORGANS_CANNIBAL_HEART,0f) != newScores.getOrDefault(CCWendigoismItems.ORGANS_CANNIBAL_HEART,0f)) {
            SetCannibalHeartBonusHP(entity, newScores.getOrDefault(CCWendigoismItems.ORGANS_CANNIBAL_HEART,0f));
        }
        //this lets us detect when the user's wendigoism changes
        if((!entity.world.isClient()) && entity instanceof WendigoAccessor && newScores.getOrDefault(CCWendigoismItems.ORGANS_CANNIBAL_HEART,0f) > 0) {
            WendigoAccessor accessor = (WendigoAccessor)entity;
            newScores.put(WENDIGOISM_TRACKER,(float)accessor.getWendigoLevel());
        }
    }

    public static void TickTetheredCannibalHeart(LivingEntity entity, ChestCavityManager chestCavity){
        if ((!entity.world.isClient()) && chestCavity.getOrganScore(CCWendigoismItems.ORGANS_TETHERED_CANNIBAL_HEART) > 0 && entity instanceof WendigoAccessor){
            if(chestCavity.getOrganScore(WENDIGOISM_TARGET) > ((WendigoAccessor)entity).getWendigoLevel()){
                if(ChestCavity.DEBUG_MODE && ((WendigoAccessor)entity).getWendigoLevel() % 10 == 0){
                    System.out.println("Tethered Cabbibal Heart Increasing Wendigoism");
                }
                int newWendigoismLevel = Math.min(
                        ((WendigoAccessor)entity).getWendigoLevel() + (int)chestCavity.getOrganScore(CCWendigoismItems.ORGANS_TETHERED_CANNIBAL_HEART),
                        (int)chestCavity.getOrganScore(WENDIGOISM_TARGET));
                newWendigoismLevel = Math.min(newWendigoismLevel,300);
                ((WendigoAccessor)entity).setWendigoLevel(newWendigoismLevel);
            }
            else if(chestCavity.getOrganScore(WENDIGOISM_TARGET) < ((WendigoAccessor)entity).getWendigoLevel()){
                if(ChestCavity.DEBUG_MODE && ((WendigoAccessor)entity).getWendigoLevel() % 10 == 0){
                    System.out.println("Tethered Cabbibal Heart Decreasing Wendigoism");
                }
                int newWendigoismLevel = Math.max(
                        ((WendigoAccessor)entity).getWendigoLevel() - (int)chestCavity.getOrganScore(CCWendigoismItems.ORGANS_TETHERED_CANNIBAL_HEART),
                        (int)chestCavity.getOrganScore(WENDIGOISM_TARGET));
                newWendigoismLevel = Math.max(newWendigoismLevel,0);
                ((WendigoAccessor)entity).setWendigoLevel(newWendigoismLevel);
            }
        }
    }
    public static void TickCannibalHeart(LivingEntity entity,ChestCavityManager chestCavity){
        if ((!entity.world.isClient()) && chestCavity.getOrganScore(CCWendigoismItems.ORGANS_CANNIBAL_HEART) > 0 && entity instanceof WendigoAccessor){
            if(chestCavity.getOrganScore(WENDIGOISM_TRACKER) != ((WendigoAccessor)entity).getWendigoLevel()){
                chestCavity.setOrganScore(WENDIGOISM_TRACKER,((WendigoAccessor)entity).getWendigoLevel());
                SetCannibalHeartBonusHP(entity,chestCavity.getOrganScore(CCWendigoismItems.ORGANS_CANNIBAL_HEART));
            }
        }
    }

    private static void SetCannibalHeartBonusHP(LivingEntity entity,float CannibalHeartScore){
        EntityAttributeInstance att;
        float bonusHP = 0;
        try {
            att = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        }
        catch(NullPointerException e){
            return;
        }
        if(entity instanceof WendigoAccessor) {
            WendigoAccessor accessor = (WendigoAccessor)entity;
            bonusHP = ChestCavity.config.HEART_HP * BONUS_HEART_PER_100_WENDIGOISM * accessor.getWendigoLevel()
                    * CannibalHeartScore / 100;
        }
        EntityAttributeModifier mod = new EntityAttributeModifier(cannibalHeartID, "ChestCavityCannibalHeartBonusMaxHP",
                bonusHP, EntityAttributeModifier.Operation.ADDITION);
        ReplaceAttributeModifier(att,mod);
    }

    private static void ReplaceAttributeModifier(EntityAttributeInstance att, EntityAttributeModifier mod) {
        //removes any existing mod and replaces it with the updated one.
        //if(att.hasModifier(mod))
        //{
        att.removeModifier(mod);
        //}
        att.addPersistentModifier(mod);
    }
}
