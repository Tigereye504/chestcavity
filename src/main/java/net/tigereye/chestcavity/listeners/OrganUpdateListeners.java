package net.tigereye.chestcavity.listeners;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;

import java.util.Map;
import java.util.UUID;

public class OrganUpdateListeners {

    private static final UUID APPENDIX_ID = UUID.fromString("ac606ec3-4cc3-42b5-9399-7fa8ceba8722");
    private static final UUID HEART_ID = UUID.fromString("edb1e124-a951-48bd-b711-782ec1364722");
    private static final UUID MUSCLE_STRENGTH_ID = UUID.fromString("bf560396-9855-496e-a942-99824467e1ad");
    private static final UUID MUSCLE_SPEED_ID = UUID.fromString("979aa156-3f01-45d3-8784-56185eeef96d");
    private static final UUID SPINE_ID = UUID.fromString("8f56feed-589f-416f-86c5-315765d41f57");

    public static void register(){
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateAppendix);
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateHeart);
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateStrength);
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateSpeed);
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateSpine);
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateIncompatibility);
    }

    public static void UpdateAppendix(LivingEntity player, Map<Identifier, Float> oldScores, Map<Identifier, Float> newScores) {
        //Update Max Health Modifier
        if(oldScores.getOrDefault(CCOrganScores.APPENDIX,0f) != newScores.getOrDefault(CCOrganScores.APPENDIX,0f)){
            EntityAttributeInstance att = player.getAttributeInstance(EntityAttributes.GENERIC_LUCK);
            if(att != null) {
                EntityAttributeModifier mod = new EntityAttributeModifier(APPENDIX_ID, "ChestCavityAppendixLuck",
                        (newScores.getOrDefault(CCOrganScores.APPENDIX, 0f) - 1) * ChestCavity.config.APPENDIX_LUCK, EntityAttributeModifier.Operation.ADDITION);
                ReplaceAttributeModifier(att, mod);
            }
        }
    }

    public static void UpdateHeart(LivingEntity player, Map<Identifier, Float> oldScores, Map<Identifier, Float> newScores) {
        //Update Max Health Modifier
        if(oldScores.getOrDefault(CCOrganScores.HEART,0f) != newScores.getOrDefault(CCOrganScores.HEART,0f)){
            EntityAttributeInstance att = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
            if(att != null) {
                EntityAttributeModifier mod = new EntityAttributeModifier(HEART_ID, "ChestCavityHeartMaxHP",
                        (newScores.getOrDefault(CCOrganScores.HEART, 0f) - 1) * ChestCavity.config.HEART_HP, EntityAttributeModifier.Operation.ADDITION);
                ReplaceAttributeModifier(att, mod);
            }
        }
    }

    public static void UpdateStrength(LivingEntity player, Map<Identifier, Float> oldScores, Map<Identifier, Float> newScores) {
        if(oldScores.getOrDefault(CCOrganScores.STRENGTH,0f) != newScores.getOrDefault(CCOrganScores.STRENGTH,0f)) {
            //Update Damage Modifier and Speed Modifier
            EntityAttributeInstance att = player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            if (att != null) {
                EntityAttributeModifier mod = new EntityAttributeModifier(MUSCLE_STRENGTH_ID, "ChestCavityMuscleAttackDamage",
                        ((newScores.getOrDefault(CCOrganScores.STRENGTH, 0f) / 8) - 1)
                                * ChestCavity.config.MUSCLE_STRENGTH, EntityAttributeModifier.Operation.MULTIPLY_BASE);
                ReplaceAttributeModifier(att, mod);
            }
        }
    }

    public static void UpdateSpeed(LivingEntity player, Map<Identifier, Float> oldScores, Map<Identifier, Float> newScores) {
        if(oldScores.getOrDefault(CCOrganScores.SPEED,0f) != newScores.getOrDefault(CCOrganScores.SPEED,0f)) {
            //Update Damage Modifier and Speed Modifier
            EntityAttributeInstance att = player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            if(att != null) {
                EntityAttributeModifier mod = new EntityAttributeModifier(MUSCLE_SPEED_ID, "ChestCavityMovementSpeed",
                        ((newScores.getOrDefault(CCOrganScores.SPEED, 0f) / 8) - 1)
                                * ChestCavity.config.MUSCLE_SPEED, EntityAttributeModifier.Operation.MULTIPLY_BASE);
                ReplaceAttributeModifier(att, mod);
            }
        }
    }

    public static void UpdateSpine(LivingEntity player, Map<Identifier, Float> oldScores, Map<Identifier, Float> newScores) {
        if(oldScores.getOrDefault(CCOrganScores.NERVOUS_SYSTEM,0f) != newScores.getOrDefault(CCOrganScores.NERVOUS_SYSTEM,0f)) {
            //Update Speed Modifier. No spine? NO MOVING.
            EntityAttributeInstance att = player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            if(att != null) {
                EntityAttributeModifier mod = new EntityAttributeModifier(SPINE_ID, "ChestCavitySpineMovement",
                        Math.min(0, newScores.getOrDefault(CCOrganScores.NERVOUS_SYSTEM, 0f) - 1), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                ReplaceAttributeModifier(att, mod);
            }
        }
    }

    public static void UpdateIncompatibility(LivingEntity player, Map<Identifier, Float> oldScores, Map<Identifier, Float> newScores) {
        if(oldScores.getOrDefault(CCOrganScores.INCOMPATIBILITY,0f) != newScores.getOrDefault(CCOrganScores.INCOMPATIBILITY,0f)) {
            try {
                player.removeStatusEffect(CCStatusEffects.ORGAN_REJECTION);
            }
            catch(Exception e){}
        }
    }

    private static void ReplaceAttributeModifier(EntityAttributeInstance att, EntityAttributeModifier mod)
    {
        //removes any existing mod and replaces it with the updated one.
        att.removeModifier(mod);
        att.addPersistentModifier(mod);
    }
}
