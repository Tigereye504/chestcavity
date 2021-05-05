package net.tigereye.chestcavity.crossmod.biomemakeover;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;

public class CCBiomeMakeover {
    public static final String MODID = "biomemakeover";
    private static final String NAME = "Biome Makeover";

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME,ChestCavity.config.BIOME_MAKEOVER_INTEGRATION)){
            CCBiomeMakeoverChestCavities.register();
        }
    }
}
