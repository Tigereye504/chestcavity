package net.tigereye.chestcavity.crossmod.bewitchment;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.crossmod.backrooms.CCBackroomsLootRegister;

public class CCBewitchment {
    public static final String MODID = "bewitchment";
    private static final String NAME = "Backrooms";

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME,ChestCavity.config.BEWITCHMENT_INTEGRATION)){
            CCBewitchmentChestCavities.register();
        }
    }
}
