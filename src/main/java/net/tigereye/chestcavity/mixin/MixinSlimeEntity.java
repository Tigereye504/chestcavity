package net.tigereye.chestcavity.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;
import net.tigereye.chestcavity.managers.ChestCavityManager;
import net.tigereye.chestcavity.managers.SlimeChestCavityManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlimeEntity.class)
public class MixinSlimeEntity{
    private ChestCavityManager chestCavityManager  = new SlimeChestCavityManager((SlimeEntity)(Object)this);

    @Inject(at = @At("TAIL"), method = "<init>")
    public void chestCavityLivingEntityConstructorMixin(EntityType<? extends LivingEntity> entityType, World world, CallbackInfo info){
        SlimeChestCavityManager.init((LivingEntity)(Object)this,chestCavityManager);
    }
}
