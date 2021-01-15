package net.tigereye.chestcavity.managers;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ChestCavityManagerFactory {

    //private static Map<EntityType<? extends LivingEntity>, Function<LivingEntity,ChestCavityManager>> entityTypeMap = new HashMap<>();
    private static Map<Identifier, Function<LivingEntity,ChestCavityManager>> entityIdentifierMap = new HashMap<>();
    private static Map<Class<? extends LivingEntity>, Function<LivingEntity,ChestCavityManager>> entityClassMap = new HashMap<>();
    //public Map<LivingEntity>

    static{ //note that these can be overwritten
        register(EntityType.BAT,SmallAnimalChestCavityManager::new);
        register(EntityType.BEE,BeeChestCavityManager::new);
        register(EntityType.CAT,SmallAnimalChestCavityManager::new);
        register(EntityType.CHICKEN,SmallAnimalChestCavityManager::new);
        register(EntityType.COD,SmallAnimalChestCavityManager::new);
        register(EntityType.FOX,SmallAnimalChestCavityManager::new);
        register(EntityType.OCELOT,SmallAnimalChestCavityManager::new);
        register(EntityType.PARROT,SmallAnimalChestCavityManager::new);
        register(EntityType.PUFFERFISH,SmallAnimalChestCavityManager::new);
        register(EntityType.RABBIT,RabbitChestCavityManager::new);
        register(EntityType.SALMON,SmallAnimalChestCavityManager::new);
        register(EntityType.TROPICAL_FISH,SmallAnimalChestCavityManager::new);

        register(EntityType.ENDERMITE,SmallAnimalChestCavityManager::new);
        register(EntityType.SILVERFISH,SmallAnimalChestCavityManager::new);
        register(EntityType.VEX,SmallAnimalChestCavityManager::new);

        register(EntityType.COW,AnimalChestCavityManager::new);
        register(EntityType.DOLPHIN,AnimalChestCavityManager::new);
        register(EntityType.DONKEY,AnimalChestCavityManager::new);
        register(EntityType.HORSE,AnimalChestCavityManager::new);
        register(EntityType.LLAMA,AnimalChestCavityManager::new);
        register(EntityType.TRADER_LLAMA,AnimalChestCavityManager::new);
        register(EntityType.MOOSHROOM,AnimalChestCavityManager::new);
        register(EntityType.MULE,AnimalChestCavityManager::new);
        register(EntityType.PANDA,AnimalChestCavityManager::new);
        register(EntityType.PIG,AnimalChestCavityManager::new);
        register(EntityType.POLAR_BEAR,AnimalChestCavityManager::new);
        register(EntityType.SHEEP,AnimalChestCavityManager::new);
        register(EntityType.SQUID,AnimalChestCavityManager::new);
        register(EntityType.STRIDER,AnimalChestCavityManager::new);
        register(EntityType.TURTLE,AnimalChestCavityManager::new);
        register(EntityType.WOLF,AnimalChestCavityManager::new);


        register(EntityType.ELDER_GUARDIAN,AnimalChestCavityManager::new);
        register(EntityType.GUARDIAN,AnimalChestCavityManager::new);
        register(EntityType.GHAST,AnimalChestCavityManager::new);
        register(EntityType.HOGLIN,AnimalChestCavityManager::new);
        register(EntityType.RAVAGER,AnimalChestCavityManager::new);
        register(EntityType.SHULKER, AnimalChestCavityManager::new);
        register(EntityType.PIGLIN,AnimalChestCavityManager::new);
        register(EntityType.PIGLIN_BRUTE,AnimalChestCavityManager::new);

        register(EntityType.SPIDER,SpiderChestCavityManager::new);
        register(EntityType.CAVE_SPIDER,CaveSpiderChestCavityManager::new);
        register(EntityType.BLAZE,SpiderChestCavityManager::new);

        register(EntityType.DROWNED,ZombieChestCavityManager::new);
        register(EntityType.HUSK,ZombieChestCavityManager::new);
        register(EntityType.PHANTOM,ZombieChestCavityManager::new);
        register(EntityType.ZOGLIN,ZombieChestCavityManager::new);
        register(EntityType.ZOMBIFIED_PIGLIN,ZombieChestCavityManager::new);
        register(EntityType.ZOMBIE,ZombieChestCavityManager::new);
        register(EntityType.ZOMBIE_HORSE,ZombieChestCavityManager::new);
        register(EntityType.ZOMBIE_VILLAGER,ZombieChestCavityManager::new);

        register(EntityType.SKELETON,SkeletonChestCavityManager::new);
        register(EntityType.SKELETON_HORSE,SkeletonChestCavityManager::new);
        register(EntityType.STRAY,SkeletonChestCavityManager::new);
        register(EntityType.WITHER_SKELETON,SkeletonChestCavityManager::new);
        register(EntityType.WITHER,SkeletonChestCavityManager::new);

        register(EntityType.EVOKER,HumanChestCavityManager::new);
        register(EntityType.PILLAGER,HumanChestCavityManager::new);
        register(EntityType.VILLAGER,HumanChestCavityManager::new);
        register(EntityType.VINDICATOR,HumanChestCavityManager::new);
        register(EntityType.WANDERING_TRADER,HumanChestCavityManager::new);
        register(EntityType.WITCH,HumanChestCavityManager::new);

        register(EntityType.CREEPER,CreeperChestCavityManager::new);
        register(EntityType.ENDERMAN,EndermanChestCavityManager::new);
        //register(EntityType.IRON_GOLEM,IronGolemChestCavityManager::new);
        register(EntityType.MAGMA_CUBE,SlimeChestCavityManager::new);
        register(EntityType.SLIME,SlimeChestCavityManager::new);
        register(EntityType.SNOW_GOLEM,SnowGolemChestCavityManager::new);

        register(EntityType.PLAYER,PlayerChestCavityManager::new);
    }

    public static ChestCavityManager newChestCavityManager(EntityType<? extends LivingEntity> entityType, LivingEntity owner){
        //if(entityTypeMap.containsKey(entityType)){
        //    return entityTypeMap.get(entityType).apply(owner);
        //}
        if(entityIdentifierMap.containsKey(Registry.ENTITY_TYPE.getId(entityType))){
            return entityIdentifierMap.get(Registry.ENTITY_TYPE.getId(entityType)).apply(owner);
        }
        return new ChestCavityManager(owner);
    }

    public static void register(EntityType<? extends LivingEntity> entityType,Function<LivingEntity,ChestCavityManager> constructor){
        entityIdentifierMap.put(Registry.ENTITY_TYPE.getId(entityType),constructor);
    }
    public static void register(Identifier entityIdentifier, Function<LivingEntity,ChestCavityManager> constructor){
        entityIdentifierMap.put(entityIdentifier,constructor);
    }
}
