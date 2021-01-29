package net.tigereye.chestcavity.crossmod.backrooms;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;

public class CCBackrooms {
    public static final String MODID = "backrooms";
    public static void register(){
        if (FabricLoader.getInstance().isModLoaded(MODID)){
            CrossModContent.LOGGER.info("[Chest Cavity] Backrooms Detected!");
            if(ChestCavity.config.BACKROOMS_INTEGRATION) {
                CrossModContent.LOGGER.info("[Chest Cavity] Integrating with Backrooms");
                CCBackroomsLootRegister.register();
            }
            else{
                CrossModContent.LOGGER.info("[Chest Cavity] Backrooms integration has been disabled in the config.");
            }
        }
    }
}
