package net.tigereye.chestcavity.listeners;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

public interface OrganOnHitListener {
    float onHit(DamageSource source, LivingEntity attacker, LivingEntity target, ChestCavityInstance chestCavity, ItemStack organ, float damage);
}
