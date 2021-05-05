package net.tigereye.chestcavity.crossmod.small_endermen;

import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstanceFactory;
import net.tigereye.chestcavity.registration.CCChestCavityTypes;

public class CCSmallEndermenChestCavities {

    public static void register(){
        //TODO: jsonize small endermen
        //ChestCavityInstanceFactory.register(new Identifier(CCSmallEndermen.MODID,"small_enderman"), CCChestCavityTypes.ENDERMAN_CHEST_CAVITY);        ChestCavityInstanceFactory.register(new Identifier(CCSmallEndermen.MODID,"small_enderman"), CCChestCavityTypes.ENDERMAN_CHEST_CAVITY);
        //ChestCavityInstanceFactory.register(new Identifier(CCSmallEndermen.MODID,"tiny_enderman"), CCChestCavityTypes.ENDERMAN_CHEST_CAVITY);
    }
}
