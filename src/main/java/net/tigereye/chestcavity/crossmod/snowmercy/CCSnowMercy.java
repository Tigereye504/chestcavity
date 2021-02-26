package net.tigereye.chestcavity.crossmod.snowmercy;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;

public class CCSnowMercy {
    public static final String MODID = "snowmercy";
    private static final String NAME = "Snow Mercy";

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME,ChestCavity.config.SNOW_MERCY_INTEGRATION)){
            CCSnowMercyChestCavities.register();
        }
    }
}
