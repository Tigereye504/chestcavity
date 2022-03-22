package net.tigereye.chestcavity.crossmod.requiem;

import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;

public class CCRequiem {
    public static String MODID = "requiem";
    public static String NAME = "Requiem";
    public static boolean REQUIEM_ACTIVE = false;
    public static Identifier PLAYER_SHELL_ID = new Identifier(MODID,"player_shell");

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME,ChestCavity.config.REQUIEM_INTEGRATION)){
            REQUIEM_ACTIVE = true;
            ChestCavity.LOGGER.error("WARNING: Chest Cavity / Requiem compatibility does not function in 1.18.2.");
            ChestCavity.LOGGER.error("Expect strange results if you use your chest cavity while undead.");
        }
    }
}
