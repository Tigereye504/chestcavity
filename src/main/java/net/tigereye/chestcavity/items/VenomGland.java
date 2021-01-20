package net.tigereye.chestcavity.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.tigereye.chestcavity.listeners.OrganOnHitListener;
import net.tigereye.chestcavity.managers.ChestCavityManager;

public class VenomGland extends Organ implements OrganOnHitListener {

    @Override
    public float onHit(DamageSource source, LivingEntity attacker, LivingEntity target, ChestCavityManager chestCavity, ItemStack organ, float damage) {
        if(attacker.getStackInHand(attacker.getActiveHand()).isEmpty()){
            target.applyStatusEffect(new StatusEffectInstance(StatusEffects.POISON,200,0));
        }
        return damage;
    }
}
