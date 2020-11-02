package net.tigereye.chestcavity.registration;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.mob_effect.OrganRejection;

public class CCStatusEffects {

    public static StatusEffect ORGAN_REJECTION = new OrganRejection();



    public static void register(){
        Registry.register(Registry.STATUS_EFFECT, new Identifier(ChestCavity.MODID, "organ_rejection"), ORGAN_REJECTION);
    }
}
