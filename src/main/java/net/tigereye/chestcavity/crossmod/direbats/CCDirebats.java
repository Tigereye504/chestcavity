package net.tigereye.chestcavity.crossmod.direbats;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.crossmod.age_of_exile.CCAOEChestCavities;
import net.tigereye.chestcavity.crossmod.bewitchment.CCBewitchmentChestCavities;

public class CCDirebats {
    public static final String MODID = "direbats";
    private static final String NAME = "Direbats";

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME,ChestCavity.config.DIREBATS_INTEGRATION)){
            CCDirebatsChestCavities.register();
        }
    }
}
