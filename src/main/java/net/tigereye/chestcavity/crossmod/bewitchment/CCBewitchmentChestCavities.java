package net.tigereye.chestcavity.crossmod.bewitchment;

import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstanceFactory;
import net.tigereye.chestcavity.registration.CCChestCavityTypes;

public class CCBewitchmentChestCavities {

    public static ChestCavityType WEREWOLF_CHEST_CAVITY = new WerewolfChestCavity();

    public static void register(){
        ChestCavityInstanceFactory.register(new Identifier(CCBewitchment.MODID,"owl"), CCChestCavityTypes.SMALL_CARNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBewitchment.MODID,"raven"), CCChestCavityTypes.SMALL_OMNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBewitchment.MODID,"snake"), CCChestCavityTypes.SMALL_CARNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBewitchment.MODID,"toad"), CCChestCavityTypes.SMALL_CARNIVORE_CHEST_CAVITY);

        ChestCavityInstanceFactory.register(new Identifier(CCBewitchment.MODID,"ghost"), CCChestCavityTypes.NULL_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBewitchment.MODID,"vampire"), CCChestCavityTypes.HUMAN_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBewitchment.MODID,"werewolf"), WEREWOLF_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBewitchment.MODID,"hellhound"), CCChestCavityTypes.CARNIVORE_CHEST_CAVITY);
        ChestCavityInstanceFactory.register(new Identifier(CCBewitchment.MODID,"demon"), CCChestCavityTypes.HUMAN_CHEST_CAVITY);
    }
}
