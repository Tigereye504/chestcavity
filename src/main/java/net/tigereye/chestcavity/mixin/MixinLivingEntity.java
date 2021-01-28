package net.tigereye.chestcavity.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.items.SilkGland;
import net.tigereye.chestcavity.managers.ChestCavityManagerFactory;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.items.ChestOpener;
import net.tigereye.chestcavity.managers.ChestCavityManager;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCOrganScores;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public class MixinLivingEntity extends Entity implements ChestCavityEntity{
    private ChestCavityManager chestCavityManager;

    protected MixinLivingEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("TAIL"), method = "<init>")
    public void chestCavityLivingEntityConstructorMixin(EntityType<? extends LivingEntity> entityType, World world,CallbackInfo info){
        chestCavityManager = ChestCavityManagerFactory.newChestCavityManager(entityType,(LivingEntity)(Object)this);
        chestCavityManager.init();
    }

    @Inject(at = @At("HEAD"), method = "baseTick")
    public void chestCavityLivingEntityBaseTickMixin(CallbackInfo info){
        chestCavityManager.onTick();
    }

    @Inject(at = @At("TAIL"), method = "baseTick")
    protected void chestCavityLivingEntityBaseTickBreathAirMixin(CallbackInfo info) {
        if(!this.isSubmergedIn(FluidTags.WATER) || this.world.getBlockState(new BlockPos(this.getX(), this.getEyeY(), this.getZ())).isOf(Blocks.BUBBLE_COLUMN)) {
            this.setAir(chestCavityManager.applyBreathOnLand(this.getAir(), this.getNextAirOnLand(0)));
        }
    }

    @ModifyVariable(at = @At(value = "CONSTANT", args = "floatValue=0.0F", ordinal = 0), ordinal = 0, method = "applyDamage")
    public float chestCavityLivingEntityOnHitMixin(float amount, DamageSource source){
        if(source.getAttacker() instanceof LivingEntity){
            Optional<ChestCavityEntity> cce = ChestCavityEntity.of(source.getAttacker());
            if(cce.isPresent()){
                    amount = cce.get().getChestCavityManager().onHit(source, (LivingEntity)(Object)this,amount);
            }
        }
        return amount;
    }

    @Inject(at = @At("RETURN"), method = "getNextAirUnderwater", cancellable = true)
    protected void chestCavityLivingEntityGetNextAirUnderwaterMixin(int air, CallbackInfoReturnable info) {
        info.setReturnValue(chestCavityManager.applyBreathInWater(air,info.getReturnValueI()));
    }

    @Inject(at = @At("RETURN"), method = "applyArmorToDamage",cancellable = true)
    public void chestCavityLivingEntityDamageMixin(DamageSource source, float amount, CallbackInfoReturnable<Float> info){
        if(!source.bypassesArmor()) {
            info.setReturnValue(chestCavityManager.applyBoneDefense(info.getReturnValueF()));
        }
    }

    @Inject(at = @At("HEAD"), method = "dropInventory")
    public void chestCavityLivingEntityDropInventoryMixin(CallbackInfo info){
        chestCavityManager.chestCavityPostMortem();
    }

    @ModifyVariable(at = @At("HEAD"), method = "addStatusEffect", ordinal = 0)
    public StatusEffectInstance chestCavityLivingEntityAddStatusEffectMixin(StatusEffectInstance effect){
        return chestCavityManager.onAddStatusEffect(effect);
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

    @Mixin(MobEntity.class)
    private static abstract class Mob extends LivingEntity{
        protected Mob(EntityType<? extends LivingEntity> entityType, World world) {super(entityType, world);}

        @Inject(at = @At("HEAD"), method = "method_29506", cancellable = true) //if this breaks, its likely because yarn changed the name to interactWithItem
        protected void chestCavityLivingEntityInteractMobMixin(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> info) {
            if(player.getStackInHand(hand).getItem() == CCItems.CHEST_OPENER){
                ((ChestOpener)player.getStackInHand(hand).getItem()).openChestCavity(player,(LivingEntity)(Object)this);
                info.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }

    @Mixin(CowEntity.class)
    private static abstract class Cow extends AnimalEntity {

        protected Cow(EntityType<? extends AnimalEntity> entityType, World world) {super(entityType, world);}

        @Inject(method = "interactMob",
                /*at = @At(value = "INVOKE",
                        target = "Lnet/minecraft/entity/LivingEntity;setStackInHand(" +
                                    "Lnet/minecraft/util/Hand;" +
                                    "Lnet/minecraft/item/ItemStack;" +
                                    ")V",
                        shift = At.Shift.AFTER)*/
                at = @At(value = "RETURN", ordinal = 0)
                )
        protected void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> info) {
            SilkGland.milkSilk(this);
        }
    }

    @Mixin(CreeperEntity.class)
    private static abstract class Creeper extends HostileEntity {
        @Shadow
        private int currentFuseTime;

        protected Creeper(EntityType<? extends HostileEntity> entityType, World world) {super(entityType, world);}

        @Inject(at = @At("HEAD"), method = "tick")
        protected void chestCavityCreeperTickMixin(CallbackInfo info) {
            if(this.isAlive()
                    && currentFuseTime > 1){
                ChestCavityEntity.of(this).ifPresent(cce -> {
                    if(cce.getChestCavityManager().getOpened()
                            && cce.getChestCavityManager().getOrganScore(CCOrganScores.CREEPY) <= 0){
                        currentFuseTime = 1;
                    }
                });
            }
        }
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

    @Mixin(SheepEntity.class)
    private static abstract class Sheep extends AnimalEntity {

        protected Sheep(EntityType<? extends AnimalEntity> entityType, World world) {super(entityType, world);}

        @Inject(method = "sheared",
                at = @At(value = "HEAD")
        )
        protected void chestCavitySheared(SoundCategory shearedSoundCategory, CallbackInfo info) {
            SilkGland.shearSilk(this);
        }
    }

    @Mixin(WitherEntity.class)
    private static abstract class Wither extends HostileEntity {


        protected Wither(EntityType<? extends HostileEntity> entityType, World world) {
            super(entityType, world);
        }

        //Lnet/minecraft/entity/boss/WitherEntity;dropItem(      //note that this might just be Entity instead.
        //  Lnet/minecraft/item/ItemConvertible;
        //)Lnet/minecraft/entity/ItemEntity;
        @Inject(method = "dropEquipment",
                at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/boss/WitherEntity;dropItem(Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/entity/ItemEntity;"),
                cancellable = true
        )
        protected void chestCavityPreventNetherStarDrop(DamageSource source, int lootingMultiplier, boolean allowDrops, CallbackInfo info) {
            Optional<ChestCavityEntity> chestCavityEntity = ChestCavityEntity.of(this);
            if(chestCavityEntity.isPresent()){
                ChestCavityManager ccm = chestCavityEntity.get().getChestCavityManager();

                //if the nether star was taken from the wither's chest, remove one from the loot pile.
                if(ccm.getOpened() && chestCavityEntity.get().getChestCavityManager().getChestCavity().count(Items.NETHER_STAR) == 0){
                    info.cancel();
                }
            }
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

    @Shadow
    protected int getNextAirOnLand(int air) {
        return 0;
    }
}
