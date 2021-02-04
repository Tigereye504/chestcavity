package net.tigereye.chestcavity.crossmod.anthropophagy;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;

public class CCAntropophagy {
    public static final String MODID = "anthropophagy";
    public static void register(){
        if (FabricLoader.getInstance().isModLoaded(MODID)){
            CrossModContent.LOGGER.info("[Chest Cavity] Anthropophagy Detected!");
            if(ChestCavity.config.ANTHROPOPHAGY_INTEGRATION) {
                CrossModContent.LOGGER.info("[Chest Cavity] Integrating with Anthropophagy");
                //CCAnthropophagyItems.register();
                //CCAnthropophagyListeners.register();
                CCAnthropophagyChestCavities.register();
            }
            else{
                CrossModContent.LOGGER.info("[Chest Cavity] Anthropophagy integration has been disabled in the config.");
            }
        }
    }
}
