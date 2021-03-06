package net.tigereye.chestcavity.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.listeners.OrganOnHitCallback;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;
import net.tigereye.chestcavity.util.NetworkUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Entity.class)
public class MixinEntity {

    @ModifyVariable(at = @At("HEAD"), ordinal = 0, method = "fall")
    public double chestCavityEntityFallMixin(double finalHeightDifference, double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition){
        if (heightDifference < 0.0D) {
            Optional<ChestCavityEntity> cce = ChestCavityEntity.of((Entity) (Object) this);
            if (cce.isPresent()) {
                finalHeightDifference = heightDifference * (1 - (cce.get().getChestCavityInstance().getOrganScore(CCOrganScores.BUOYANT)/3));
            }
        }
        return finalHeightDifference;
    }
/*
    @Inject(at = @At("TAIL"), method = "dealDamage")
    public void chestCavityDealDamageMixin(LivingEntity attacker, Entity target, CallbackInfo info) {
        Optional<ChestCavityEntity> cce = ChestCavityEntity.of(attacker);
        if (cce.isPresent() && target instanceof LivingEntity) {
            OrganOnHitCallback.EVENT.invoker().onHit(attacker, (LivingEntity)target, cce.get().getChestCavityInstance());
        }
    }*/
    
}
