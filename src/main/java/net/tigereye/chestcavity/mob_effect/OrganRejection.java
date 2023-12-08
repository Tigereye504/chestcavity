package net.tigereye.chestcavity.mob_effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCDamageSources;

public class OrganRejection extends CCStatusEffect{

    public OrganRejection(){
        super(StatusEffectCategory.NEUTRAL, 0xC8FF00);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration <= 1;
    }

    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!(entity.getWorld().isClient)){
            entity.damage(CCDamageSources.of(entity.getWorld(), CCDamageSources.ORGAN_REJECTION), ChestCavity.config.ORGAN_REJECTION_DAMAGE);
        }
    }
}
