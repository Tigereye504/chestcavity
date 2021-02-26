package net.tigereye.chestcavity.crossmod.rats_mischief;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.crossmod.age_of_exile.CCAOEChestCavities;
import net.tigereye.chestcavity.crossmod.direbats.CCDirebatsChestCavities;

public class CCRatsMischief {
    public static final String MODID = "ratsmischief";
    private static final String NAME = "Rat's Mischief";

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME,ChestCavity.config.RATS_MISCHIEF_INTEGRATION)){
            CCRatsMischiefChestCavities.register();
        }
    }
}
