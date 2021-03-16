package net.tigereye.chestcavity.crossmod;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.age_of_exile.CCAOE;
import net.tigereye.chestcavity.crossmod.age_of_exile.CCAOEChestCavities;
import net.tigereye.chestcavity.crossmod.anthropophagy.CCAntropophagy;
import net.tigereye.chestcavity.crossmod.backrooms.CCBackrooms;
import net.tigereye.chestcavity.crossmod.bewitchment.CCBewitchment;
import net.tigereye.chestcavity.crossmod.biome_makeover.CCBiomeMakeover;
import net.tigereye.chestcavity.crossmod.creeperspores.CCCreeperSpores;
import net.tigereye.chestcavity.crossmod.direbats.CCDirebats;
import net.tigereye.chestcavity.crossmod.rats_mischief.CCRatsMischief;
import net.tigereye.chestcavity.crossmod.requiem.CCRequiem;
import net.tigereye.chestcavity.crossmod.small_endermen.CCSmallEndermen;
import net.tigereye.chestcavity.crossmod.snowmercy.CCSnowMercy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrossModContent {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void register(){
        CCAOE.register();
        CCAntropophagy.register();
        CCBackrooms.register();
        CCBewitchment.register();
        CCBiomeMakeover.register();
        CCCreeperSpores.register();
        CCDirebats.register();
        CCRatsMischief.register();
        CCRequiem.register();
        CCSmallEndermen.register();
        CCSnowMercy.register();
    }

    public static boolean checkIntegration(String modid, String name, boolean config){
        if (FabricLoader.getInstance().isModLoaded(modid)){
            CrossModContent.LOGGER.info("[Chest Cavity] "+name+" Detected!");
            if(config) {
                CrossModContent.LOGGER.info("[Chest Cavity] Integrating with "+name);
                return true;
            }
            else{
                CrossModContent.LOGGER.info("[Chest Cavity] "+name+" integration has been disabled in the config.");
            }
        }
        return false;
    }
}
