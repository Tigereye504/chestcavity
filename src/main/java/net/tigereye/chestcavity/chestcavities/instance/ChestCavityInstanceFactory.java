package net.tigereye.chestcavity.chestcavities.instance;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.types.generated.json.GeneratedChestCavityAssignmentManager;
import net.tigereye.chestcavity.chestcavities.types.generated.json.GeneratedChestCavityTypeManager;
import net.tigereye.chestcavity.registration.CCChestCavityTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ChestCavityInstanceFactory {

    private static final Map<Identifier, ChestCavityType> entityIdentifierMap = new HashMap<>();

    public static ChestCavityInstance newChestCavityInstance(EntityType<? extends LivingEntity> entityType, LivingEntity owner){
        Identifier entityID = Registry.ENTITY_TYPE.getId(entityType);
        if(GeneratedChestCavityAssignmentManager.GeneratedChestCavityAssignments.containsKey(entityID)){
            Identifier chestCavityTypeID = GeneratedChestCavityAssignmentManager.GeneratedChestCavityAssignments.get(entityID);
            if(GeneratedChestCavityTypeManager.GeneratedChestCavityTypes.containsKey(chestCavityTypeID)){
                return new ChestCavityInstance(GeneratedChestCavityTypeManager.GeneratedChestCavityTypes.get(chestCavityTypeID),owner);
            }
        }
        if(entityIdentifierMap.containsKey(entityID)){
            return new ChestCavityInstance(entityIdentifierMap.get(Registry.ENTITY_TYPE.getId(entityType)),owner);
        }
        return new ChestCavityInstance(CCChestCavityTypes.BASE_CHEST_CAVITY,owner);
    }

    public static void register(EntityType<? extends LivingEntity> entityType,ChestCavityType chestCavityType){
        entityIdentifierMap.put(Registry.ENTITY_TYPE.getId(entityType),chestCavityType);
    }
    public static void register(Identifier entityIdentifier, ChestCavityType chestCavityType){
        entityIdentifierMap.put(entityIdentifier,chestCavityType);
    }
}
