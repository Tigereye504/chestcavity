package net.tigereye.chestcavity.crossmod.backrooms;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.crossmod.anthropophagy.CCAnthropophagyChestCavities;

public class CCBackrooms {
    public static final String MODID = "backrooms";
    private static final String NAME = "Backrooms";

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME,ChestCavity.config.BACKROOMS_INTEGRATION)){
            CCBackroomsLootRegister.register();
        }
    }
}
