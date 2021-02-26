package net.tigereye.chestcavity.crossmod.small_endermen;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;

public class CCSmallEndermen {
    public static final String MODID = "smallendermen";
    private static final String NAME = "Small Endermen";

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME,ChestCavity.config.SMALL_ENDERMEN_INTEGRATION)){
            CCSmallEndermenChestCavities.register();
        }
    }
}
