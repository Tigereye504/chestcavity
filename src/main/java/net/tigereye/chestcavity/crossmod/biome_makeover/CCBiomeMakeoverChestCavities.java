package net.tigereye.chestcavity.crossmod.biome_makeover;

import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstanceFactory;
import net.tigereye.chestcavity.crossmod.direbats.CCDirebats;
import net.tigereye.chestcavity.registration.CCChestCavityTypes;

public class CCBiomeMakeoverChestCavities {

    public static void register(){
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"blightbat"), CCChestCavityTypes.SMALL_OMNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"cowboy"), CCChestCavityTypes.HUMAN_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"decayed"), CCChestCavityTypes.ZOMBIE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"dragonfly"), CCChestCavityTypes.SMALL_CARNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"giant_slime"), CCChestCavityTypes.SLIME_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"ghost"), CCChestCavityTypes.NULL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"glowfish"), CCChestCavityTypes.SMALL_OMNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"lightning_bug"), CCChestCavityTypes.SMALL_HERBIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"lightning_bug_alternate"), CCChestCavityTypes.SMALL_HERBIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"mushroom_trader"), CCChestCavityTypes.HUMAN_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"scuttler"), CCChestCavityTypes.SMALL_OMNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"tadpole"), CCChestCavityTypes.SMALL_OMNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"toad"), CCChestCavityTypes.SMALL_CARNIVORE_CHEST_CAVITY);
    }
}
