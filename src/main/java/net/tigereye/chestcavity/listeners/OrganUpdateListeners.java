package net.tigereye.chestcavity.listeners;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.items.CC_Items;

import java.util.Map;
import java.util.UUID;

public class OrganUpdateListeners {

    private static final UUID heartID = UUID.fromString("edb1e124-a951-48bd-b711-782ec1364722");
    private static final UUID muscleID1 = UUID.fromString("bf560396-9855-496e-a942-99824467e1ad");
    private static final UUID muscleID2 = UUID.fromString("979aa156-3f01-45d3-8784-56185eeef96d");
    private static final UUID spineID = UUID.fromString("8f56feed-589f-416f-86c5-315765d41f57");

    public static void register(){
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateHeart);
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateMuscle);
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateSpine);
    }

    private static void UpdateHeart(PlayerEntity player, Map<Identifier, Float> oldScores, Map<Identifier, Float> newScores) {
        //Update Max Health Modifier
        if(oldScores.getOrDefault(CC_Items.ORGANS_HEART,0f) != newScores.getOrDefault(CC_Items.ORGANS_HEART,0f)){
            EntityAttributeInstance att = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
            EntityAttributeModifier mod = new EntityAttributeModifier(heartID, "ChestCavityHeartMaxHP",
                    (newScores.getOrDefault(CC_Items.ORGANS_HEART,0f)*6)-6, EntityAttributeModifier.Operation.ADDITION);
            ReplaceAttributeModifier(att,mod);
        }
    }

    private static void UpdateMuscle(PlayerEntity player, Map<Identifier, Float> oldScores, Map<Identifier, Float> newScores) {
        if(oldScores.getOrDefault(CC_Items.ORGANS_MUSCLE,0f) != newScores.getOrDefault(CC_Items.ORGANS_MUSCLE,0f)) {
            //Update Damage Modifier
            EntityAttributeInstance att = player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            EntityAttributeModifier mod = new EntityAttributeModifier(muscleID1, "ChestCavityMuscleAttackDamage",
                    (newScores.getOrDefault(CC_Items.ORGANS_MUSCLE, 0f) / (64 * 8)) - 1, EntityAttributeModifier.Operation.MULTIPLY_BASE);
            ReplaceAttributeModifier(att, mod);
            //Update Move Speeeeed
            EntityAttributeInstance att2 = player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            EntityAttributeModifier mod2 = new EntityAttributeModifier(muscleID2, "ChestCavityMuscleMovementSpeed",
                    (newScores.getOrDefault(CC_Items.ORGANS_MUSCLE, 0f) / (64 * 8 * 2)) - .5, EntityAttributeModifier.Operation.MULTIPLY_BASE);
            ReplaceAttributeModifier(att2, mod2);
        }
    }

    private static void UpdateSpine(PlayerEntity player, Map<Identifier, Float> oldScores, Map<Identifier, Float> newScores) {
        if(oldScores.getOrDefault(CC_Items.ORGANS_SPINE,0f) != newScores.getOrDefault(CC_Items.ORGANS_SPINE,0f)) {
            //Update Speed Modifier. No spine? NO MOVING.
            EntityAttributeInstance att = player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            EntityAttributeModifier mod = new EntityAttributeModifier(spineID, "ChestCavitySpineMovement",
                    Math.min(0, newScores.getOrDefault(CC_Items.ORGANS_SPINE, 0f) - 1), EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            ReplaceAttributeModifier(att, mod);
        }
    }

    private static void ReplaceAttributeModifier(EntityAttributeInstance att, EntityAttributeModifier mod)
    {
        //removes any existing mod and replaces it with the updated one.
        //if(att.hasModifier(mod))
        //{
        att.removeModifier(mod);
        //}
        att.addPersistentModifier(mod);
    }
}
