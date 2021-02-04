package net.tigereye.chestcavity.crossmod.direbats;

import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstanceFactory;
import net.tigereye.chestcavity.registration.CCChestCavityTypes;

public class CCDirebatsManagers {

    public static void register(){
        ChestCavityInstanceFactory.register(new Identifier(CCDirebats.MODID,"direbat"), CCChestCavityTypes.ANIMAL_CHEST_CAVITY);
    }
}
