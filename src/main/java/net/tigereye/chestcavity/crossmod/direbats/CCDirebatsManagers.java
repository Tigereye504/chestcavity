package net.tigereye.chestcavity.crossmod.direbats;

import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.managers.*;

public class CCDirebatsManagers {

    public static void register(){
        ChestCavityManagerFactory.register(new Identifier(CCDirebats.MODID,"direbat"), AnimalChestCavityManager::new);
    }
}
