package net.tigereye.chestcavity.crossmod.wendigoism;

import moriyashiine.wendigoism.api.accessor.WendigoAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.listeners.OrganUpdateListeners;
import net.tigereye.chestcavity.managers.ChestCavityManager;
import net.tigereye.chestcavity.listeners.OrganTickCallback;
import net.tigereye.chestcavity.listeners.OrganUpdateCallback;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.Map;
import java.util.UUID;

public class CCWendigoismListeners {
    private static final Identifier WENDIGOISM_TRACKER = new Identifier(ChestCavity.MODID, "wendigoism_tracker");
    public static final Identifier WENDIGOISM_TARGET = new Identifier(ChestCavity.MODID, "wendigoism_target");
    private static final UUID cannibalHeartID = UUID.fromString("140317b9-74be-40cb-802e-95971fbc6d29");

    public static void register(){
        OrganUpdateCallback.EVENT.register(CCWendigoismListeners::UpdateCannibalHeart);
        OrganTickCallback.EVENT.register(CCWendigoismListeners::TickTetheredCannibalHeart);
        OrganTickCallback.EVENT.register(CCWendigoismListeners::TickCannibalHeart);
    }

    private static void UpdateCannibalHeart(LivingEntity entity, Map<Identifier, Float> oldScores, Map<Identifier, Float> newScores) {
        //this lets us detect when the user's wendigoism changes
        if((!entity.world.isClient()) && entity instanceof WendigoAccessor && newScores.getOrDefault(CCWendigoismOrganScores.CANNIBAL_HEART,0f) > 0) {
            WendigoAccessor accessor = (WendigoAccessor)entity;
            newScores.put(WENDIGOISM_TRACKER,(float)accessor.getWendigoLevel());
        }
    }

    public static void TickTetheredCannibalHeart(LivingEntity entity, ChestCavityManager chestCavity){
        if ((!entity.world.isClient()) && chestCavity.getOrganScore(CCWendigoismOrganScores.TETHERED_CANNIBAL_HEART) > 0 && entity instanceof WendigoAccessor){
            if(chestCavity.getOrganScore(WENDIGOISM_TARGET) > ((WendigoAccessor)entity).getWendigoLevel()){
                if(ChestCavity.DEBUG_MODE && ((WendigoAccessor)entity).getWendigoLevel() % 10 == 0){
                    System.out.println("Tethered Cabbibal Heart Increasing Wendigoism");
                }
                int newWendigoismLevel = Math.min(
                        ((WendigoAccessor)entity).getWendigoLevel() + (int)chestCavity.getOrganScore(CCWendigoismOrganScores.TETHERED_CANNIBAL_HEART),
                        (int)chestCavity.getOrganScore(WENDIGOISM_TARGET));
                newWendigoismLevel = Math.min(newWendigoismLevel,300);
                ((WendigoAccessor)entity).setWendigoLevel(newWendigoismLevel);
            }
            else if(chestCavity.getOrganScore(WENDIGOISM_TARGET) < ((WendigoAccessor)entity).getWendigoLevel()){
                if(ChestCavity.DEBUG_MODE && ((WendigoAccessor)entity).getWendigoLevel() % 10 == 0){
                    System.out.println("Tethered Cabbibal Heart Decreasing Wendigoism");
                }
                int newWendigoismLevel = Math.max(
                        ((WendigoAccessor)entity).getWendigoLevel() - (int)chestCavity.getOrganScore(CCWendigoismOrganScores.TETHERED_CANNIBAL_HEART),
                        (int)chestCavity.getOrganScore(WENDIGOISM_TARGET));
                newWendigoismLevel = Math.max(newWendigoismLevel,0);
                ((WendigoAccessor)entity).setWendigoLevel(newWendigoismLevel);
            }
        }
    }

    public static void TickCannibalHeart(LivingEntity entity,ChestCavityManager chestCavity){
        if ((!entity.world.isClient()) && chestCavity.getOrganScore(CCWendigoismOrganScores.CANNIBAL_HEART) > 0 && entity instanceof WendigoAccessor){
            if(chestCavity.getOrganScore(WENDIGOISM_TRACKER) != ((WendigoAccessor)entity).getWendigoLevel()){
                int oldWendigoism = (int)chestCavity.getOrganScore(WENDIGOISM_TRACKER);
                int newWendigoism = ((WendigoAccessor)entity).getWendigoLevel();
                float HeartScoreModifier = (CannibalHeart.getBonusHeart(newWendigoism) - (CannibalHeart.getBonusHeart(oldWendigoism))
                        * chestCavity.getOrganScore(CCWendigoismOrganScores.CANNIBAL_HEART));
                chestCavity.addOrganScore(CCOrganScores.HEART,HeartScoreModifier);
            }
        }
    }
}
