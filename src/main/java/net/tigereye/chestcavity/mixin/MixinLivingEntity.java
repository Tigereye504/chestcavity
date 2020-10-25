package net.tigereye.chestcavity.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.managers.ChestCavityManager;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity extends Entity implements ChestCavityEntity{
    private ChestCavityManager chestCavityManager  = new ChestCavityManager((LivingEntity)(Object)this);

    protected MixinLivingEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("TAIL"), method = "<init>")
    public void chestCavityLivingEntityConstructorMixin(EntityType<? extends LivingEntity> entityType, World world,CallbackInfo info){
        ChestCavityManager.init((LivingEntity)(Object)this,chestCavityManager);
    }

    @Inject(at = @At("HEAD"), method = "baseTick")
    public void chestCavityLivingEntityBaseTickMixin(CallbackInfo info){
        chestCavityManager.onTick();
    }

    @Inject(at = @At("RETURN"), method = "getNextAirUnderwater")
    protected void chestCavityLivingEntityGetNextAirUnderwaterMixin(int air, CallbackInfoReturnable info) {
        int airloss = (air - info.getReturnValueI());
        if(airloss > 0){
            info.setReturnValue(airloss * chestCavityManager.applyLungCapacityInWater());
        }
    }

    @ModifyVariable(at = @At("HEAD"), method = "damage")
    public float chestCavityLivingEntityDamageMixin(float amount){
        return chestCavityManager.applyBoneDefense(amount);
    }

    @Inject(at = @At("HEAD"), method = "dropInventory")
    public void chestCavityLivingEntityDropInventoryMixin(CallbackInfo info){
        chestCavityManager.chestCavityPostMortem();
    }

    public ChestCavityManager getChestCavityManager() {
        return chestCavityManager;
    }

    public void setChestCavityManager(ChestCavityManager chestCavityManager) {
        this.chestCavityManager = chestCavityManager;
    }

    @Inject(method = "readCustomDataFromTag", at = @At("TAIL"))
    private void readCustomDataFromTag(CompoundTag tag, CallbackInfo callbackInfo) {
        chestCavityManager.fromTag(tag,(LivingEntity)(Object)this);
    }

    @Inject(method = "writeCustomDataToTag", at = @At("TAIL"))
    private void writeCustomDataToTag(CompoundTag tag, CallbackInfo callbackInfo) {
        chestCavityManager.toTag(tag);
    }

    @Mixin(ServerPlayerEntity.class)
    private static abstract class Server extends PlayerEntity {
        public Server(World world, BlockPos pos, float yaw, GameProfile profile) {
            super(world, pos, yaw, profile);
        }

        @Inject(method = "copyFrom", at = @At("TAIL"))
        public void copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo callbackInfo) {
            if(ChestCavity.DEBUG_MODE){
                System.out.println("Attempting to load ChestCavityManager");
            }
            ChestCavityEntity.of(this).ifPresent(chestCavityEntity -> ChestCavityEntity.of(oldPlayer).ifPresent(oldCCPlayerEntityInterface -> {
                if(ChestCavity.DEBUG_MODE){
                    System.out.println("Copying ChestCavityManager");
                }
                chestCavityEntity.getChestCavityManager().clone(oldCCPlayerEntityInterface.getChestCavityManager());
            }));
        }
    }

    @Shadow
    protected void initDataTracker() {}

    @Shadow
    protected void readCustomDataFromTag(CompoundTag tag) {}

    @Shadow
    protected void writeCustomDataToTag(CompoundTag tag) {}

    @Shadow
    public Packet<?> createSpawnPacket() {return null;}
}
