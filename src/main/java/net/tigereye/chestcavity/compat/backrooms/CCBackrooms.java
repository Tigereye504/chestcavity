package net.tigereye.chestcavity.compat.backrooms;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.compat.CrossModContent;

public class CCBackrooms {
    public static final String MODID = "backrooms";
    private static final String NAME = "Backrooms";

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME,ChestCavity.config.BACKROOMS_INTEGRATION)){
            CCBackroomsLootRegister.register();
        }
    }
}
