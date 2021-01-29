package net.tigereye.chestcavity.crossmod.ageofexile;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;

public class CCAOE {
    public static final String MODID = "mmorpg";
    public static void register(){
        CCAOEManagers.register();
        if (FabricLoader.getInstance().isModLoaded(MODID)){
            CrossModContent.LOGGER.info("[Chest Cavity] Age of Exile Detected!");
            if(ChestCavity.config.AGE_OF_EXILE_INTEGRATION) {
                CrossModContent.LOGGER.info("[Chest Cavity] Integrating with Age of Exile");
                CCAOEManagers.register();
            }
            else{
                CrossModContent.LOGGER.info("[Chest Cavity] Age of Exile integration has been disabled in the config.");
            }
        }
    }
}
