package net.tigereye.chestcavity.chestcavities.instance;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.types.DefaultChestCavityType;
import net.tigereye.chestcavity.chestcavities.types.json.GeneratedChestCavityAssignmentManager;
import net.tigereye.chestcavity.chestcavities.types.json.GeneratedChestCavityTypeManager;

import java.util.HashMap;
import java.util.Map;

public class ChestCavityInstanceFactory {

    private static final Map<Identifier, ChestCavityType> entityIdentifierMap = new HashMap<>();
    private static final ChestCavityType DEFAULT_CHEST_CAVITY_TYPE = new DefaultChestCavityType();

    public static ChestCavityInstance newChestCavityInstance(EntityType<?> entityType, LivingEntity owner){
        Identifier entityID = net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
        if(GeneratedChestCavityAssignmentManager.GeneratedChestCavityAssignments.containsKey(entityID)){
            Identifier chestCavityTypeID = GeneratedChestCavityAssignmentManager.GeneratedChestCavityAssignments.get(entityID);
            if(GeneratedChestCavityTypeManager.GeneratedChestCavityTypes.containsKey(chestCavityTypeID)){
                return new ChestCavityInstance(GeneratedChestCavityTypeManager.GeneratedChestCavityTypes.get(chestCavityTypeID),owner);
            }
        }
        if(entityIdentifierMap.containsKey(entityID)){
            return new ChestCavityInstance(entityIdentifierMap.get(net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(entityType)),owner);
        }
        return new ChestCavityInstance(DEFAULT_CHEST_CAVITY_TYPE,owner);
    }

    public static void register(EntityType<?> entityType,ChestCavityType chestCavityType){
        entityIdentifierMap.put(net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(entityType),chestCavityType);
    }
    public static void register(Identifier entityIdentifier, ChestCavityType chestCavityType){
        entityIdentifierMap.put(entityIdentifier,chestCavityType);
    }
}
