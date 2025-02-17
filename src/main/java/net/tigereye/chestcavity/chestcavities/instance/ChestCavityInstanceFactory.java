package net.tigereye.chestcavity.chestcavities.instance;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.types.FallbackChestCavityType;
import net.tigereye.chestcavity.chestcavities.types.json.GeneratedChestCavityAssignmentManager;

import java.util.HashMap;
import java.util.Map;

import static net.tigereye.chestcavity.chestcavities.types.json.GeneratedChestCavityTypeManager.GeneratedChestCavityTypes;

public class ChestCavityInstanceFactory {

    private static final Map<Identifier, ChestCavityType> entityIdentifierMap = new HashMap<>();
    private static final ChestCavityType FALLBACK_CHEST_CAVITY_TYPE = new FallbackChestCavityType();

    public static ChestCavityInstance newChestCavityInstance(EntityType<? extends LivingEntity> entityType, LivingEntity owner){
        Identifier entityID = Registries.ENTITY_TYPE.getId(entityType);
        if(GeneratedChestCavityAssignmentManager.GeneratedChestCavityAssignments.containsKey(entityID)){
            Identifier chestCavityTypeID = GeneratedChestCavityAssignmentManager.GeneratedChestCavityAssignments.get(entityID);
            if(GeneratedChestCavityTypes.containsKey(chestCavityTypeID)){
                return new ChestCavityInstance(GeneratedChestCavityTypes.get(chestCavityTypeID),owner);
            }
        }
        if(entityIdentifierMap.containsKey(entityID)){
            return new ChestCavityInstance(entityIdentifierMap.get(Registries.ENTITY_TYPE.getId(entityType)),owner);
        }
        Identifier defaultID = new Identifier(ChestCavity.config.DEFAULT_CHEST_CAVITY);
        if (GeneratedChestCavityTypes.containsKey(defaultID)) {
            return new ChestCavityInstance(GeneratedChestCavityTypes.get(defaultID), owner);
        }
        return new ChestCavityInstance(FALLBACK_CHEST_CAVITY_TYPE,owner);
    }

    public static void register(EntityType<? extends LivingEntity> entityType,ChestCavityType chestCavityType){
        entityIdentifierMap.put(Registries.ENTITY_TYPE.getId(entityType),chestCavityType);
    }
    public static void register(Identifier entityIdentifier, ChestCavityType chestCavityType){
        entityIdentifierMap.put(entityIdentifier,chestCavityType);
    }
}
