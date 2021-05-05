package net.tigereye.chestcavity.crossmod.biomemakeover;

import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstanceFactory;
import net.tigereye.chestcavity.registration.CCChestCavityTypes;

public class CCBiomeMakeoverChestCavities {

    public static void register(){
        //TODO: jsonitize biome makeover chest cavities
        ChestCavityInstanceFactory.register(new Identifier(CCBiomeMakeover.MODID,"ghost"), CCChestCavityTypes.NULL_CHEST_CAVITY);
    }
}
