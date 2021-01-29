package net.tigereye.chestcavity.crossmod.anthropophagy;

import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.managers.*;

public class CCAnthropophagyManagers {

    public static void register(){
        ChestCavityManagerFactory.register(new Identifier(CCAntropophagy.MODID,"piglutton"), HumanChestCavityManager::new);
    }
}
