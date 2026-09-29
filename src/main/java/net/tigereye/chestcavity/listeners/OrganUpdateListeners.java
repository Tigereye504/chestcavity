package net.tigereye.chestcavity.listeners;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;

import java.util.UUID;

public class OrganUpdateListeners {

    private static final net.minecraft.resources.Identifier APPENDIX_ID = net.minecraft.resources.Identifier.fromNamespaceAndPath("chestcavity","appendix_id");
    private static final net.minecraft.resources.Identifier HEART_ID = net.minecraft.resources.Identifier.fromNamespaceAndPath("chestcavity","heart_id");
    private static final net.minecraft.resources.Identifier MUSCLE_STRENGTH_ID = net.minecraft.resources.Identifier.fromNamespaceAndPath("chestcavity","muscle_strength_id");
    private static final net.minecraft.resources.Identifier MUSCLE_SPEED_ID = net.minecraft.resources.Identifier.fromNamespaceAndPath("chestcavity","muscle_speed_id");
    private static final net.minecraft.resources.Identifier SPINE_ATTACK_SPEED_ID = net.minecraft.resources.Identifier.fromNamespaceAndPath("chestcavity","spine_attack_speed_id");
    private static final net.minecraft.resources.Identifier SPINE_MOVEMENT_ID = net.minecraft.resources.Identifier.fromNamespaceAndPath("chestcavity","spine_movement_id");
    private static final net.minecraft.resources.Identifier KNOCKBACK_RESISTANCE_ID = net.minecraft.resources.Identifier.fromNamespaceAndPath("chestcavity","knockback_resistance_id");

    public static void register(){
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateAppendix);
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateHeart);
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateStrength);
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateSpeed);
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateSpine);
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateKnockbackResistance);
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateIncompatibility);
    }

    public static void UpdateAppendix(LivingEntity entity, ChestCavityInstance cc) {
        //Update Max Health Modifier
        if(cc.getOldOrganScore(CCOrganScores.LUCK) != cc.getOrganScore(CCOrganScores.LUCK)){
            AttributeInstance att = entity.getAttribute(Attributes.LUCK);
            if(att != null) {
                AttributeModifier mod = new AttributeModifier(APPENDIX_ID,
                        (cc.getOrganScore(CCOrganScores.LUCK) - cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.LUCK))
                                * ChestCavity.config.APPENDIX_LUCK, AttributeModifier.Operation.ADD_VALUE);
                ReplaceAttributeModifier(att, mod);
            }
        }
    }

    public static void UpdateHeart(LivingEntity entity, ChestCavityInstance cc) {
        //Update Max Health Modifier
        if(cc.getOldOrganScore(CCOrganScores.HEALTH) != cc.getOrganScore(CCOrganScores.HEALTH)){
            AttributeInstance att = entity.getAttribute(Attributes.MAX_HEALTH);
            if(att != null) {
                AttributeModifier mod = new AttributeModifier(HEART_ID,
                        (cc.getOrganScore(CCOrganScores.HEALTH) - cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.HEALTH))
                                * ChestCavity.config.HEART_HP, AttributeModifier.Operation.ADD_VALUE);
                ReplaceAttributeModifier(att, mod);
            }
        }
    }

    public static void UpdateStrength(LivingEntity entity, ChestCavityInstance cc) {
        if(cc.getOldOrganScore(CCOrganScores.STRENGTH) != cc.getOrganScore(CCOrganScores.STRENGTH)) {
            //Update Damage Modifier and Speed Modifier
            AttributeInstance att = entity.getAttribute(Attributes.ATTACK_DAMAGE);
            if (att != null) {
                AttributeModifier mod = new AttributeModifier(MUSCLE_STRENGTH_ID,
                        (cc.getOrganScore(CCOrganScores.STRENGTH) - cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.STRENGTH))
                        * ChestCavity.config.MUSCLE_STRENGTH / 8, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
                ReplaceAttributeModifier(att, mod);
            }
        }
    }

    public static void UpdateSpeed(LivingEntity entity, ChestCavityInstance cc) {
        if(cc.getOldOrganScore(CCOrganScores.MOVEMENT_SPEED) != cc.getOrganScore(CCOrganScores.MOVEMENT_SPEED)) {
            //Update Damage Modifier and Speed Modifier
            AttributeInstance att = entity.getAttribute(Attributes.MOVEMENT_SPEED);
            if(att != null) {
                AttributeModifier mod = new AttributeModifier(MUSCLE_SPEED_ID,
                        (cc.getOrganScore(CCOrganScores.MOVEMENT_SPEED) - cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.MOVEMENT_SPEED))
                                * ChestCavity.config.MUSCLE_SPEED / 8, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
                ReplaceAttributeModifier(att, mod);
            }
        }
    }

    public static void UpdateSpine(LivingEntity entity, ChestCavityInstance cc) {
        if(cc.getOldOrganScore(CCOrganScores.NERVES) != cc.getOrganScore(CCOrganScores.NERVES)
                && cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.NERVES) != 0) {
            //Update Speed Modifier. No spine? GUI_NO MOVING.
            AttributeInstance att = entity.getAttribute(Attributes.MOVEMENT_SPEED);
            if(att != null) {
                AttributeModifier mod = new AttributeModifier(SPINE_MOVEMENT_ID,
                        cc.getOrganScore(CCOrganScores.NERVES) > 0 ? 0 : -1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
                ReplaceAttributeModifier(att, mod);
            }
            //Update Attack Speed Modifier.
            att = entity.getAttribute(Attributes.ATTACK_SPEED);
            if(att != null){
                AttributeModifier mod = new AttributeModifier(SPINE_ATTACK_SPEED_ID,
                        (cc.getOrganScore(CCOrganScores.NERVES) - cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.NERVES))
                                * ChestCavity.config.NERVES_HASTE, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
                ReplaceAttributeModifier(att, mod);
            }
        }
    }

    public static void UpdateKnockbackResistance(LivingEntity entity, ChestCavityInstance cc) {
        if(cc.getOldOrganScore(CCOrganScores.KNOCKBACK_RESISTANT) != cc.getOrganScore(CCOrganScores.KNOCKBACK_RESISTANT)) {
            //Update Knockback Res Modifier
            AttributeInstance att = entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
            if(att != null) {
                AttributeModifier mod = new AttributeModifier(KNOCKBACK_RESISTANCE_ID,
                        (cc.getOrganScore(CCOrganScores.KNOCKBACK_RESISTANT) - cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.KNOCKBACK_RESISTANT))
                                * .1, AttributeModifier.Operation.ADD_VALUE);
                ReplaceAttributeModifier(att, mod);
            }
        }
    }

    public static void UpdateIncompatibility(LivingEntity entity, ChestCavityInstance cc) {
        if(cc.getOldOrganScore(CCOrganScores.INCOMPATIBILITY) != cc.getOrganScore(CCOrganScores.INCOMPATIBILITY)) {
            try {
                entity.removeEffect(CCStatusEffects.ORGAN_REJECTION);
            }
            catch(Exception ignore){}
        }
    }

    private static void ReplaceAttributeModifier(AttributeInstance att, AttributeModifier mod)
    {
        //removes any existing mod and replaces it with the updated one.
        att.removeModifier(mod);
        att.addPermanentModifier(mod);
    }
}
