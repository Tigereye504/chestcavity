package net.tigereye.chestcavity.mob_effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCDamageSource;

import java.util.Optional;

public class OrganRejection extends CCStatusEffect{

    public OrganRejection(){
        super(StatusEffectCategory.NEUTRAL, 0xC8FF00);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration <= 1;
    }

    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!(entity.world.isClient)){
            entity.damage(CCDamageSource.ORGAN_REJECTION, ChestCavity.config.ORGAN_REJECTION_DAMAGE);
        }
    }
}
