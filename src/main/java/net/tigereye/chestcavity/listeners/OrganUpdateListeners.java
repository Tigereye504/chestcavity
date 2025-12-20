package net.tigereye.chestcavity.listeners;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;

import java.util.UUID;

public class OrganUpdateListeners {

    private static final UUID SPINE_ATTACK_SPEED_ID = UUID.fromString("709e3e77-0586-4304-80b5-d28bc477e947");
    private static final UUID SPINE_MOVEMENT_ID = UUID.fromString("8f56feed-589f-416f-86c5-315765d41f57");


    public static void register(){
        ChestCavityUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateIncompatibility);
    }

    public static void UpdateAppendix(LivingEntity entity, ChestCavityInstance cc) {
        //Update Max Health Modifier

    }

    public static void UpdateIncompatibility(LivingEntity entity, ChestCavityInstance cc) {
        if(cc.getOldOrganScore(CCOrganScores.INCOMPATIBILITY) != cc.getOrganScore(CCOrganScores.INCOMPATIBILITY)) {
            try {
                entity.removeStatusEffect(CCStatusEffects.ORGAN_REJECTION);
            }
            catch(Exception ignore){}
        }
    }

    private static void ReplaceAttributeModifier(EntityAttributeInstance att, EntityAttributeModifier mod)
    {
        //removes any existing mod and replaces it with the updated one.
        att.removeModifier(mod);
        att.addPersistentModifier(mod);
    }
}
