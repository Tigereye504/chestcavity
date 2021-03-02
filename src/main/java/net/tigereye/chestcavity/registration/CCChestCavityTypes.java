package net.tigereye.chestcavity.registration;

import net.minecraft.entity.EntityType;
import net.tigereye.chestcavity.chestcavities.*;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstanceFactory;
import net.tigereye.chestcavity.chestcavities.types.*;
import net.tigereye.chestcavity.chestcavities.types.animal.*;
import net.tigereye.chestcavity.chestcavities.types.artificial.BlazeChestCavity;
import net.tigereye.chestcavity.chestcavities.types.artificial.SnowGolemChestCavity;
import net.tigereye.chestcavity.chestcavities.types.humanoid.EndermanChestCavity;
import net.tigereye.chestcavity.chestcavities.types.humanoid.HumanChestCavity;
import net.tigereye.chestcavity.chestcavities.types.humanoid.PlayerChestCavity;
import net.tigereye.chestcavity.chestcavities.types.insects.BeeChestCavity;
import net.tigereye.chestcavity.chestcavities.types.insects.CaveSpiderChestCavity;
import net.tigereye.chestcavity.chestcavities.types.insects.InsectChestCavity;
import net.tigereye.chestcavity.chestcavities.types.insects.SpiderChestCavity;
import net.tigereye.chestcavity.chestcavities.types.undead.SkeletonChestCavity;
import net.tigereye.chestcavity.chestcavities.types.undead.WitherChestCavity;
import net.tigereye.chestcavity.chestcavities.types.undead.WitherSkeletonChestCavity;
import net.tigereye.chestcavity.chestcavities.types.undead.ZombieChestCavity;

public class CCChestCavityTypes {
    public static final ChestCavityType BASE_CHEST_CAVITY = new BaseChestCavity();
    public static final ChestCavityType EMPTY_CHEST_CAVITY = new EmptyChestCavity();
    public static final ChestCavityType NULL_CHEST_CAVITY = new NullChestCavity();

    public static final ChestCavityType OMNIVORE_CHEST_CAVITY = new OmnivoreChestCavity();
    public static final ChestCavityType CARNIVORE_CHEST_CAVITY = new CarnivoreChestCavity();
    public static final ChestCavityType DOLPHIN_CHEST_CAVITY = new DolphinChestCavity();
    public static final ChestCavityType HERBIVORE_CHEST_CAVITY = new HerbivoreChestCavity();
    public static final ChestCavityType LARGE_FISH_CHEST_CAVITY = new LargeFishChestCavity();
    public static final ChestCavityType LLAMA_CHEST_CAVITY = new LlamaChestCavity();
    public static final ChestCavityType PUFFERFISH_CHEST_CAVITY = new PufferfishChestCavity();
    public static final ChestCavityType RABBIT_CHEST_CAVITY = new RabbitChestCavity();
    public static final ChestCavityType RUMINANT_CHEST_CAVITY = new RuminantChestCavity();
    public static final ChestCavityType SMALL_OMNIVORE_CHEST_CAVITY = new SmallOmnivoreChestCavity();
    public static final ChestCavityType SMALL_CARNIVORE_CHEST_CAVITY = new SmallCarnivoreChestCavity();
    public static final ChestCavityType SMALL_FISH_CHEST_CAVITY = new SmallFishChestCavity();
    public static final ChestCavityType SMALL_HERBIVORE_CHEST_CAVITY = new SmallHerbivoreChestCavity();
    public static final ChestCavityType TURTLE_CHEST_CAVITY = new TurtleChestCavity();

    public static final ChestCavityType BEE_CHEST_CAVITY = new BeeChestCavity();
    public static final ChestCavityType CAVE_SPIDER_CHEST_CAVITY = new CaveSpiderChestCavity();
    public static final ChestCavityType INSECT_CHEST_CAVITY = new InsectChestCavity();
    public static final ChestCavityType SPIDER_CHEST_CAVITY = new SpiderChestCavity();

    public static final ChestCavityType SKELETON_CHEST_CAVITY = new SkeletonChestCavity();
    public static final ChestCavityType WITHER_CHEST_CAVITY = new WitherChestCavity();
    public static final ChestCavityType WITHER_SKELETON_CHEST_CAVITY = new WitherSkeletonChestCavity();
    public static final ChestCavityType ZOMBIE_CHEST_CAVITY = new ZombieChestCavity();

    public static final ChestCavityType ENDERMAN_CHEST_CAVITY = new EndermanChestCavity();
    public static final ChestCavityType HUMAN_CHEST_CAVITY = new HumanChestCavity();
    public static final ChestCavityType PLAYER_CHEST_CAVITY = new PlayerChestCavity();

    public static final ChestCavityType BLAZE_CHEST_CAVITY = new BlazeChestCavity();
    public static final ChestCavityType SNOW_GOLEM_CHEST_CAVITY = new SnowGolemChestCavity();

    public static final ChestCavityType CREEPER_CHEST_CAVITY = new CreeperChestCavity();
    public static final ChestCavityType GHAST_CHEST_CAVITY = new GhastChestCavity();
    public static final ChestCavityType SHULKER_CHEST_CAVITY = new ShulkerChestCavity();
    public static final ChestCavityType SLIME_CHEST_CAVITY = new SlimeChestCavity();

    public static void register(){
        ChestCavityInstanceFactory.register(EntityType.BAT, SMALL_OMNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.CAT, SMALL_CARNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.CHICKEN, SMALL_OMNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.FOX, SMALL_CARNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.RABBIT, RABBIT_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.OCELOT, SMALL_CARNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PARROT, SMALL_OMNIVORE_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.COD, SMALL_FISH_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PUFFERFISH,PUFFERFISH_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SALMON,SMALL_FISH_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.TROPICAL_FISH,SMALL_FISH_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.COW, RUMINANT_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.DOLPHIN, DOLPHIN_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.DONKEY, HERBIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.HORSE, HERBIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.LLAMA, LLAMA_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.TRADER_LLAMA, LLAMA_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.MOOSHROOM, RUMINANT_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.MULE, HERBIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PANDA, CARNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PIG, OMNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.POLAR_BEAR, CARNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SHEEP, RUMINANT_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.STRIDER, OMNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.TURTLE, TURTLE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.WOLF, CARNIVORE_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.HOGLIN, OMNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.RAVAGER, OMNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SHULKER, SHULKER_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PIGLIN, OMNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.PIGLIN_BRUTE, OMNIVORE_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.BEE, BEE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SPIDER,SPIDER_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.CAVE_SPIDER,CAVE_SPIDER_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.ENDERMITE,INSECT_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SILVERFISH,INSECT_CHEST_CAVITY);

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
        ChestCavityInstanceFactory.register(EntityType.VEX, SMALL_OMNIVORE_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.BLAZE,BLAZE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.CREEPER, CREEPER_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.ENDERMAN, ENDERMAN_CHEST_CAVITY);
        //ChestCavityInstanceFactory.register(EntityType.IRON_GOLEM, IRON_GOLEM_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.GHAST, GHAST_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.MAGMA_CUBE, SLIME_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SLIME, SLIME_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(EntityType.SNOW_GOLEM, SNOW_GOLEM_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(EntityType.PLAYER, PLAYER_CHEST_CAVITY);
    }
}
