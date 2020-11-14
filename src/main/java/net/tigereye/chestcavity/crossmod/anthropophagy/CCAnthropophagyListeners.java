package net.tigereye.chestcavity.crossmod.anthropophagy;

import moriyashiine.anthropophagy.api.accessor.CannibalAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.managers.ChestCavityManager;
import net.tigereye.chestcavity.listeners.OrganTickCallback;
import net.tigereye.chestcavity.listeners.OrganUpdateCallback;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.Map;
import java.util.UUID;

public class CCAnthropophagyListeners {
    private static final Identifier CANNIBALISM_TRACKER = new Identifier(ChestCavity.MODID, "cannibalism_tracker");
    public static final Identifier CANNIBALISM_TARGET = new Identifier(ChestCavity.MODID, "cannibalism_target");
    private static final UUID cannibalHeartID = UUID.fromString("140317b9-74be-40cb-802e-95971fbc6d29");

    public static void register(){
        OrganUpdateCallback.EVENT.register(CCAnthropophagyListeners::UpdateCannibalHeart);
        OrganTickCallback.EVENT.register(CCAnthropophagyListeners::TickTetheredCannibalHeart);
        OrganTickCallback.EVENT.register(CCAnthropophagyListeners::TickCannibalHeart);
    }

    private static void UpdateCannibalHeart(LivingEntity entity, Map<Identifier, Float> oldScores, Map<Identifier, Float> newScores) {
        //this lets us detect when the user's Cannibalism changes
        if((!entity.world.isClient()) && entity instanceof CannibalAccessor && newScores.getOrDefault(CCAnthropophagyOrganScores.CANNIBAL_HEART,0f) > 0) {
            CannibalAccessor accessor = (CannibalAccessor)entity;
            newScores.put(CANNIBALISM_TRACKER,(float)accessor.getCannibalLevel());
        }
    }

    public static void TickTetheredCannibalHeart(LivingEntity entity, ChestCavityManager chestCavity){
        if ((!entity.world.isClient()) && chestCavity.getOrganScore(CCAnthropophagyOrganScores.TETHERED_CANNIBAL_HEART) > 0 && entity instanceof CannibalAccessor){
            if(chestCavity.getOrganScore(CANNIBALISM_TARGET) > ((CannibalAccessor)entity).getCannibalLevel()){
                if(ChestCavity.DEBUG_MODE && ((CannibalAccessor)entity).getCannibalLevel() % 10 == 0){
                    System.out.println("Tethered Cannibal Heart Increasing Cannibalism");
                }
                int newCannibalismLevel = Math.min(
                        ((CannibalAccessor)entity).getCannibalLevel() + (int)chestCavity.getOrganScore(CCAnthropophagyOrganScores.TETHERED_CANNIBAL_HEART),
                        (int)chestCavity.getOrganScore(CANNIBALISM_TARGET));
                newCannibalismLevel = Math.min(newCannibalismLevel,300);
                ((CannibalAccessor)entity).setCannibalLevel(newCannibalismLevel);
            }
            else if(chestCavity.getOrganScore(CANNIBALISM_TARGET) < ((CannibalAccessor)entity).getCannibalLevel()){
                if(ChestCavity.DEBUG_MODE && ((CannibalAccessor)entity).getCannibalLevel() % 10 == 0){
                    System.out.println("Tethered Cannibal Heart Decreasing Cannibalism");
                }
                int newCannibalismLevel = Math.max(
                        ((CannibalAccessor)entity).getCannibalLevel() - (int)chestCavity.getOrganScore(CCAnthropophagyOrganScores.TETHERED_CANNIBAL_HEART),
                        (int)chestCavity.getOrganScore(CANNIBALISM_TARGET));
                newCannibalismLevel = Math.max(newCannibalismLevel,0);
                ((CannibalAccessor)entity).setCannibalLevel(newCannibalismLevel);
            }
        }
    }

    public static void TickCannibalHeart(LivingEntity entity,ChestCavityManager chestCavity){
        if ((!entity.world.isClient()) && chestCavity.getOrganScore(CCAnthropophagyOrganScores.CANNIBAL_HEART) > 0 && entity instanceof CannibalAccessor){
            if(chestCavity.getOrganScore(CANNIBALISM_TRACKER) != ((CannibalAccessor)entity).getCannibalLevel()){
                int oldCannibalism = (int)chestCavity.getOrganScore(CANNIBALISM_TRACKER);
                int newCannibalism = ((CannibalAccessor)entity).getCannibalLevel();
                float HeartScoreModifier = (CannibalHeart.getBonusHeart(newCannibalism) - (CannibalHeart.getBonusHeart(oldCannibalism))
                        * chestCavity.getOrganScore(CCAnthropophagyOrganScores.CANNIBAL_HEART));
                chestCavity.addOrganScore(CCOrganScores.HEART,HeartScoreModifier);
            }
        }
    }
}
