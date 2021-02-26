package net.tigereye.chestcavity.crossmod.biome_makeover;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.crossmod.age_of_exile.CCAOEChestCavities;
import net.tigereye.chestcavity.crossmod.bewitchment.CCBewitchmentChestCavities;
import net.tigereye.chestcavity.crossmod.direbats.CCDirebatsChestCavities;

public class CCBiomeMakeover {
    public static final String MODID = "biomemakeover";
    private static final String NAME = "Biome Makeover";

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME,ChestCavity.config.BIOME_MAKEOVER_INTEGRATION)){
            CCBiomeMakeoverChestCavities.register();
        }
    }
}
