package net.tigereye.chestcavity.crossmod.anthropophagy;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.crossmod.age_of_exile.CCAOEChestCavities;

public class CCAntropophagy {
    public static final String MODID = "anthropophagy";
    public static final String NAME = "Anthropophagy";

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME,ChestCavity.config.ANTHROPOPHAGY_INTEGRATION)){
            //CCAnthropophagyItems.register();
            //CCAnthropophagyListeners.register();
            CCAnthropophagyChestCavities.register();
        }
    }
}
