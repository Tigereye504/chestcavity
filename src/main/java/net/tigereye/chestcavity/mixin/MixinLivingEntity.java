package net.tigereye.chestcavity.mixin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.world.entity.*;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.chestcavities.instance.*;
import net.tigereye.chestcavity.util.*;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements ChestCavityEntity {
    @Unique private ChestCavityInstance chestcavity$instance;
    @Shadow protected abstract int increaseAirSupply(int air);
    @Override public ChestCavityInstance getChestCavityInstance() {
        if(chestcavity$instance==null) { LivingEntity self=(LivingEntity)(Object)this;chestcavity$instance=ChestCavityInstanceFactory.newChestCavityInstance(self.getType(),self); }
        return chestcavity$instance;
    }
    @Override public void setChestCavityInstance(ChestCavityInstance instance){chestcavity$instance=instance;}
    @Inject(method="baseTick",at=@At("HEAD")) private void chestcavity$tick(CallbackInfo ci){
        LivingEntity self=(LivingEntity)(Object)this;
        if(!self.level().isClientSide()) ChestCavityUtil.onTick(getChestCavityInstance());
    }
    @Inject(method="baseTick",at=@At("TAIL")) private void chestcavity$air(CallbackInfo ci){
        LivingEntity self=(LivingEntity)(Object)this;
        if(!self.isEyeInFluid(net.minecraft.tags.FluidTags.WATER)||self.level().getBlockState(net.minecraft.core.BlockPos.containing(self.getX(),self.getEyeY(),self.getZ())).is(net.minecraft.world.level.block.Blocks.BUBBLE_COLUMN))
            self.setAirSupply(ChestCavityUtil.applyBreathOnLand(getChestCavityInstance(),self.getAirSupply(),increaseAirSupply(0)));
    }
    @Inject(method="decreaseAirSupply",at=@At("RETURN"),cancellable=true) private void chestcavity$water(int air,CallbackInfoReturnable<Integer> cir){cir.setReturnValue(ChestCavityUtil.applyBreathInWater(getChestCavityInstance(),air,cir.getReturnValueI()));}
    @Inject(method="getDamageAfterArmorAbsorb",at=@At("RETURN"),cancellable=true) private void chestcavity$armor(net.minecraft.world.damagesource.DamageSource source,float damage,CallbackInfoReturnable<Float> cir){cir.setReturnValue(ChestCavityUtil.applyDefenses(getChestCavityInstance(),source,cir.getReturnValueF()));}
    @Inject(method="getJumpPower(F)F",at=@At("RETURN"),cancellable=true) private void chestcavity$jump(float multiplier,CallbackInfoReturnable<Float> cir){cir.setReturnValue(ChestCavityUtil.applyLeaping(getChestCavityInstance(),cir.getReturnValueF()));}
    @ModifyVariable(method="addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z",at=@At("HEAD"),argsOnly=true,ordinal=0)
    private net.minecraft.world.effect.MobEffectInstance chestcavity$effect(net.minecraft.world.effect.MobEffectInstance effect){return ChestCavityUtil.onAddStatusEffect(getChestCavityInstance(),effect);}
    @Inject(method="dropAllDeathLoot",at=@At("TAIL")) private void chestcavity$death(net.minecraft.server.level.ServerLevel level,net.minecraft.world.damagesource.DamageSource source,CallbackInfo ci){ChestCavityUtil.onDeath(this);}
    @Inject(method="readAdditionalSaveData",at=@At("TAIL")) private void chestcavity$read(net.minecraft.world.level.storage.ValueInput input,CallbackInfo ci){
        var root=new net.minecraft.nbt.CompoundTag();input.read("ChestCavity",net.minecraft.nbt.CompoundTag.CODEC).ifPresent(tag->root.put("ChestCavity",tag));getChestCavityInstance().fromTag(root,(LivingEntity)(Object)this);
    }
    @Inject(method="addAdditionalSaveData",at=@At("TAIL")) private void chestcavity$write(net.minecraft.world.level.storage.ValueOutput output,CallbackInfo ci){
        var root=new net.minecraft.nbt.CompoundTag();getChestCavityInstance().toTag(root);output.store("ChestCavity",net.minecraft.nbt.CompoundTag.CODEC,root.getCompoundOrEmpty("ChestCavity"));
    }
    @ModifyArg(method="travelInWater",at=@At(value="INVOKE",target="Lnet/minecraft/world/entity/LivingEntity;moveRelative(FLnet/minecraft/world/phys/Vec3;)V"),index=0)
    private float chestcavity$swim(float speed){return speed*ChestCavityUtil.applySwimSpeedInWater(getChestCavityInstance());}
}
