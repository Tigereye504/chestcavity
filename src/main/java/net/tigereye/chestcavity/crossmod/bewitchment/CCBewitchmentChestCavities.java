package net.tigereye.chestcavity.crossmod.bewitchment;

import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstanceFactory;
import net.tigereye.chestcavity.registration.CCChestCavityTypes;

public class CCBewitchmentChestCavities {


    public static void register(){
        ChestCavityInstanceFactory.register(new Identifier(CCBewitchment.MODID,"ghost"), CCChestCavityTypes.NULL_CHEST_CAVITY);
    }
}
