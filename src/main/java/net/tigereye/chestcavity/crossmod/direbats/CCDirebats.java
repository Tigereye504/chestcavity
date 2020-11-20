package net.tigereye.chestcavity.crossmod.direbats;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.crossmod.ageofexile.CCAOEManagers;

public class CCDirebats {
    public static final String MODID = "direbats";
    public static void register(){
        CCAOEManagers.register();
        if (FabricLoader.getInstance().isModLoaded(MODID)){
            CrossModContent.LOGGER.info("[Chest Cavity] Direbats Detected!");
            if(ChestCavity.config.DIREBATS_INTEGRATION) {
                CrossModContent.LOGGER.info("[Chest Cavity] Integrating with Direbats");
                CCDirebatsManagers.register();
            }
            else{
                CrossModContent.LOGGER.info("[Chest Cavity] Direbats integration has been disabled in the config.");
            }
        }
    }
}
