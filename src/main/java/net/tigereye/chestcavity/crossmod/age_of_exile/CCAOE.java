package net.tigereye.chestcavity.crossmod.age_of_exile;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;

public class CCAOE {
    public static final String MODID = "mmorpg";
    public static final String NAME = "Age Of Exile";

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME,ChestCavity.config.AGE_OF_EXILE_INTEGRATION)){
            CCAOEChestCavities.register();
        }
    }
}
