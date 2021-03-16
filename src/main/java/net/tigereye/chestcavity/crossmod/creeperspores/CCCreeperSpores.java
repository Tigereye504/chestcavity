package net.tigereye.chestcavity.crossmod.creeperspores;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.crossmod.direbats.CCDirebatsChestCavities;

public class CCCreeperSpores {
    public static final String MODID = "creeperspores";
    private static final String NAME = "Creeper Spores";

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME, ChestCavity.config.CREEPER_SPORES_INTEGRATION)){
            CCCreeperSporesChestCavities.register();
        }
    }
}
