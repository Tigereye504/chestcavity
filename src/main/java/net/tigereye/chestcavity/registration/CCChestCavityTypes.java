package net.tigereye.chestcavity.registration;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.chestcavities.*;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstanceFactory;
import net.tigereye.chestcavity.chestcavities.types.*;

public class CCChestCavityTypes {
    public static final ChestCavityType BASE_CHEST_CAVITY = new BaseChestCavity();

    public static final ChestCavityType ANIMAL_CHEST_CAVITY = new AnimalChestCavity();
    public static final ChestCavityType BEE_CHEST_CAVITY = new BeeChestCavity();
    public static final ChestCavityType CREEPER_CHEST_CAVITY = new CreeperChestCavity();
    public static final ChestCavityType ENDERMAN_CHEST_CAVITY = new EndermanChestCavity();
    public static final ChestCavityType HUMAN_CHEST_CAVITY = new HumanChestCavity();
    public static final ChestCavityType LARGE_FISH_CHEST_CAVITY = new LargeFishChestCavity();
    public static final ChestCavityType PLAYER_CHEST_CAVITY = new PlayerChestCavity();
    public static final ChestCavityType RABBIT_CHEST_CAVITY = new RabbitChestCavity();
    public static final ChestCavityType SKELETON_CHEST_CAVITY = new SkeletonChestCavity();
    public static final ChestCavityType SLIME_CHEST_CAVITY = new SlimeChestCavity();
    public static final ChestCavityType SMALL_ANIMAL_CHEST_CAVITY = new SmallAnimalChestCavity();
    public static final ChestCavityType SMALL_FISH_CHEST_CAVITY = new SmallFishChestCavity();
    public static final ChestCavityType SNOW_GOLEM_CHEST_CAVITY = new SnowGolemChestCavity();
    public static final ChestCavityType SPIDER_CHEST_CAVITY = new SpiderChestCavity();
    public static final ChestCavityType CAVE_SPIDER_CHEST_CAVITY = new CaveSpiderChestCavity();
    public static final ChestCavityType WITHER_CHEST_CAVITY = new WitherChestCavity();
    public static final ChestCavityType WITHER_SKELETON_CHEST_CAVITY = new WitherSkeletonChestCavity();
    public static final ChestCavityType ZOMBIE_CHEST_CAVITY = new ZombieChestCavity();

    public static void register(){
        ChestCavityInstanceFactory.register(EntityType.BAT, SMALL_ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.CAT,SMALL_ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.CHICKEN,SMALL_ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.FOX,SMALL_ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.OCELOT,SMALL_ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PARROT,SMALL_ANIMAL_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.ENDERMITE,SMALL_ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SILVERFISH,SMALL_ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.VEX,SMALL_ANIMAL_CHEST_CAVITY);
        
        ChestCavityInstanceFactory.register(EntityType.RABBIT, RABBIT_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.BEE, BEE_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.COD, SMALL_FISH_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PUFFERFISH,SMALL_FISH_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SALMON,SMALL_FISH_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.TROPICAL_FISH,SMALL_FISH_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.COW, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.DOLPHIN, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.DONKEY, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.HORSE, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.LLAMA, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.TRADER_LLAMA, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.MOOSHROOM, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.MULE, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PANDA, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PIG, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.POLAR_BEAR, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SHEEP, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.STRIDER, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.TURTLE, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.WOLF, ANIMAL_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.GHAST, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.HOGLIN, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.RAVAGER, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SHULKER, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PIGLIN, ANIMAL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PIGLIN_BRUTE, ANIMAL_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.SPIDER,SPIDER_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.CAVE_SPIDER,CAVE_SPIDER_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.BLAZE,SPIDER_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.SQUID,LARGE_FISH_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.ELDER_GUARDIAN,LARGE_FISH_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.GUARDIAN,LARGE_FISH_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.DROWNED,ZOMBIE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.HUSK,ZOMBIE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PHANTOM,ZOMBIE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.ZOGLIN,ZOMBIE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.ZOMBIFIED_PIGLIN,ZOMBIE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.ZOMBIE,ZOMBIE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.ZOMBIE_HORSE,ZOMBIE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.ZOMBIE_VILLAGER,ZOMBIE_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.SKELETON,SKELETON_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SKELETON_HORSE,SKELETON_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.STRAY,SKELETON_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.WITHER_SKELETON,WITHER_SKELETON_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.WITHER,WITHER_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.EVOKER, HUMAN_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PILLAGER, HUMAN_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.VILLAGER, HUMAN_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.VINDICATOR, HUMAN_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.WANDERING_TRADER, HUMAN_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.WITCH, HUMAN_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.CREEPER, CREEPER_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.ENDERMAN, ENDERMAN_CHEST_CAVITY);
        //ChestCavityInstanceFactory.register(EntityType.IRON_GOLEM, IRON_GOLEM_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.MAGMA_CUBE, SLIME_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SLIME, SLIME_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SNOW_GOLEM, SNOW_GOLEM_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PLAYER, PLAYER_CHEST_CAVITY);
    }
}
