package net.tigereye.chestcavity.crossmod.requiem;

import net.fabricmc.loader.api.FabricLoader;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.crossmod.ageofexile.CCAOEChestCavities;

public class CCRequiem {
    public static boolean REQUIEM_ACTIVE = false;
    public static void register(){
        CCAOEChestCavities.register();
        if (FabricLoader.getInstance().isModLoaded("requiem")){
            CrossModContent.LOGGER.info("[Chest Cavity] Requiem Detected!");
            if(ChestCavity.config.REQUIEM_INTEGRATION) {
                CrossModContent.LOGGER.info("[Chest Cavity] Integrating with Requiem");
                REQUIEM_ACTIVE = true;
            }
            else{
                CrossModContent.LOGGER.info("[Chest Cavity] Requiem integration has been disabled in the config.");
            }
        }
    }
}
