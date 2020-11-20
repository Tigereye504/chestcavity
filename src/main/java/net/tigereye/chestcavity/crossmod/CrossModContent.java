package net.tigereye.chestcavity.crossmod;

import net.tigereye.chestcavity.crossmod.ageofexile.CCAOE;
import net.tigereye.chestcavity.crossmod.anthropophagy.CCAntropophagy;
import net.tigereye.chestcavity.crossmod.backrooms.CCBackrooms;
import net.tigereye.chestcavity.crossmod.direbats.CCDirebats;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrossModContent {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void register(){
        CCAOE.register();
        CCAntropophagy.register();
        CCBackrooms.register();
        CCDirebats.register();
    }
}
