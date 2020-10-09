package net.tigereye.chestcavity.crossmod;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.wendigoism.CCWendigoismItems;
import net.tigereye.chestcavity.crossmod.wendigoism.CCWendigoismListeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrossModContent {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void register(){
        if (FabricLoader.getInstance().isModLoaded("wendigoism")){
            LOGGER.info("[Chest Cavity] Wendigoism Detected!");
            if(ChestCavity.config.WENDIGOISM_INTEGRATION) {
                LOGGER.info("[Chest Cavity] Integrating with Wendigoism");
                CCWendigoismItems.register();
                CCWendigoismListeners.register();
            }
            else{
                LOGGER.info("[Chest Cavity] Wendigoism integration has been disabled in the config.");
            }
        }
    }
}