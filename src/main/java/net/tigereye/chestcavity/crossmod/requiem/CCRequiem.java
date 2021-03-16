package net.tigereye.chestcavity.crossmod.requiem;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.crossmod.age_of_exile.CCAOEChestCavities;
import net.tigereye.chestcavity.crossmod.rats_mischief.CCRatsMischiefChestCavities;

public class CCRequiem {
    public static String MODID = "requiem";
    public static String NAME = "Requiem";
    public static boolean REQUIEM_ACTIVE = false;
    public static Identifier PLAYER_SHELL_ID = new Identifier(MODID,"player_shell");

    public static void register(){
        if(CrossModContent.checkIntegration(MODID,NAME,ChestCavity.config.REQUIEM_INTEGRATION)){
            REQUIEM_ACTIVE = true;
        }
    }
}
