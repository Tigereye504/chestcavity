package net.tigereye.chestcavity.compat;


import net.tigereye.chestcavity.compat.backrooms.CCBackrooms;
import net.tigereye.chestcavity.compat.requiem.CCRequiem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrossModContent {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void register(){
        CCBackrooms.register();
        CCRequiem.register();
    }

    public static boolean checkIntegration(String modid, String name, boolean config){
        if (net.minecraftforge.fml.ModList.isLoaded(modid)){
            CrossModContent.LOGGER.debug("[Chest Cavity] "+name+" Detected!");
            if(config) {
                CrossModContent.LOGGER.debug("[Chest Cavity] Integrating with "+name);
                return true;
            }
            else{
                CrossModContent.LOGGER.debug("[Chest Cavity] "+name+" integration has been disabled in the config.");
            }
        }
        return false;
    }
}
