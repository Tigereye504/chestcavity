package net.tigereye.chestcavity.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.BlockState;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstanceFactory;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.items.ChestOpener;
import net.tigereye.chestcavity.listeners.OrganFoodEffectCallback;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;
import net.tigereye.chestcavity.util.NetworkUtil;
import net.tigereye.chestcavity.util.OrganUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Mixin(value = LivingEntity.class,priority = 900)
public class MixinLivingEntity extends Entity implements ChestCavityEntity{
    @Unique
    private ChestCavityInstance chestCavityInstance;

    protected MixinLivingEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("TAIL"), method = "<init>")
    public void chestCavityLivingEntityConstructorMixin(EntityType<? extends LivingEntity> entityType, World world,CallbackInfo info){
        chestCavityInstance = ChestCavityInstanceFactory.newChestCavityInstance(entityType,(LivingEntity)(Object)this);
        //chestCavityInstance.init();
    }

    @Inject(at = @At("HEAD"), method = "baseTick")
    public void chestCavityLivingEntityBaseTickMixin(CallbackInfo info){
        ChestCavityUtil.onTick(chestCavityInstance);
    }

    @Inject(at = @At("TAIL"), method = "baseTick")
    protected void chestCavityLivingEntityBaseTickBreathAirMixin(CallbackInfo info) {
        if(!this.isSubmergedIn(FluidTags.WATER) || this.getWorld().getBlockState(this.getBlockPos()).isOf(Blocks.BUBBLE_COLUMN)) {
            this.setAir(ChestCavityUtil.applyBreathOnLand(chestCavityInstance,this.getAir(), this.getNextAirOnLand(0)));
        }
    }

    @ModifyVariable(at = @At(value = "CONSTANT", args = "floatValue=0.0F", ordinal = 0), ordinal = 0, method = "applyDamage")
    public float chestCavityLivingEntityOnHitMixin(float amount, DamageSource source){
        if(source.getAttacker() instanceof LivingEntity){
            Optional<ChestCavityEntity> cce = ChestCavityEntity.of(source.getAttacker());
            if(cce.isPresent()){
                    amount = ChestCavityUtil.onHit(cce.get().getChestCavityInstance(), source, (LivingEntity)(Object)this,amount);
            }
        }
        return amount;
    }

    @Inject(at = @At("RETURN"), method = "getNextAirUnderwater", cancellable = true)
    protected void chestCavityLivingEntityGetNextAirUnderwaterMixin(int air, CallbackInfoReturnable<Integer> info) {
        info.setReturnValue(ChestCavityUtil.applyBreathInWater(chestCavityInstance,air,info.getReturnValueI()));
    }

    @Inject(at = @At("RETURN"), method = "applyArmorToDamage",cancellable = true)
    public void chestCavityLivingEntityDamageMixin(DamageSource source, float amount, CallbackInfoReturnable<Float> info){
        info.setReturnValue(ChestCavityUtil.applyDefenses(chestCavityInstance, source, info.getReturnValueF()));
    }

    @Inject(at = @At("HEAD"), method = "dropInventory")
    public void chestCavityLivingEntityDropInventoryMixin(CallbackInfo info){
        ChestCavityUtil.onDeath(this);
    }

    @ModifyVariable(at = @At("HEAD"), method = "addStatusEffect", ordinal = 0)
    public StatusEffectInstance chestCavityLivingEntityAddStatusEffectMixin(StatusEffectInstance effect){
        return ChestCavityUtil.onAddStatusEffect(chestCavityInstance,effect);
    }

    @ModifyVariable(//at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;isFood()Z"),
            at = @At("STORE"),
            method = "applyFoodEffects", ordinal = 0)
    public List<Pair<StatusEffectInstance, Float>> chestCavityLivingEntityApplyFoodEffectsMixin(List<Pair<StatusEffectInstance, Float>> list, ItemStack stack, World world, LivingEntity targetEntity){
        Optional<ChestCavityEntity> option = ChestCavityEntity.of(targetEntity);
        if (option.isPresent()) {
            list = new LinkedList<>(list); //this divorces the list I shall modify from the item's long term list
            OrganFoodEffectCallback.EVENT.invoker().onApplyFoodEffects(list, stack, world, targetEntity, option.get().getChestCavityInstance());
        }
        return list;
    }

    /*
    Lnet/minecraft/entity/Entity; (maybe LivingEntity)
    updateVelocity(
            F
            Lnet/minecraft/util/math/Vec3d;
    )V
     */
    //Lnet/minecraft/entity/LivingEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z
    //@ModifyVariable(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateVelocity(FLnet/minecraft/util/math/Vec3d;)V"), method = "travel",ordinal = 1)
    //@Group(name = "WaterTravelMixins", max = 2, min = 1)
    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateVelocity(FLnet/minecraft/util/math/Vec3d;)V"), method = "travel",index = 0, require = 0)
    protected float chestCavityLivingEntityWaterTravelMixin(float g) {
        return g*ChestCavityUtil.applySwimSpeedInWater(chestCavityInstance);
    }
    //Origins combatibility variant. Why must I do this?!?
    //@Group(name = "WaterTravelMixins")
    //@ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateVelocity(Lnet/minecraft/entity/LivingEntity;FLnet/minecraft/util/math/Vec3d;)V"), method = "travel",index = 0)
    //protected double chestCavityLivingEntityWaterTravelMixinAlt(double g) {
    //    return g*ChestCavityUtil.applySwimSpeedInWater(chestCavityInstance);
    //}

    @Inject(at = @At("RETURN"), method = "getJumpVelocity",cancellable = true)
    public void chestCavityLivingEntityJumpVelocityMixin(CallbackInfoReturnable<Float> info){
        info.setReturnValue(ChestCavityUtil.applyLeaping(chestCavityInstance, info.getReturnValueF()));
    }

    public ChestCavityInstance getChestCavityInstance() {
        return chestCavityInstance;
    }

    public void setChestCavityInstance(ChestCavityInstance chestCavityInstance) {
        this.chestCavityInstance = chestCavityInstance;
    }


    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomDataFromNbt(NbtCompound tag, CallbackInfo callbackInfo) {
        chestCavityInstance.fromTag(tag,(LivingEntity)(Object)this);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomDataToNbt(NbtCompound tag, CallbackInfo callbackInfo) {
        chestCavityInstance.toTag(tag);
    }

    @Mixin(MobEntity.class)
    private static abstract class Mob extends LivingEntity{
        protected Mob(EntityType<? extends LivingEntity> entityType, World world) {super(entityType, world);}

        @Inject(at = @At("HEAD"), method = "interactWithItem"/*"method_29506"*/, cancellable = true) //if this breaks, its likely because yarn changed the name to interactWithItem
        protected void chestCavityLivingEntityInteractMobMixin(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> info) {
            if(player.getStackInHand(hand).getItem() == CCItems.CHEST_OPENER && (!(((LivingEntity)(Object)this) instanceof PlayerEntity))){
                ((ChestOpener)player.getStackInHand(hand).getItem()).openChestCavity(player,(LivingEntity)(Object)this);
                info.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
    
    @Mixin(PlayerEntity.class)
    public static abstract class Player extends LivingEntity{
        protected Player(EntityType<? extends LivingEntity> entityType, World world) {
            super(entityType, world);
        }

        @ModifyVariable(at = @At(value = "CONSTANT", args = "floatValue=0.0F", ordinal = 0), ordinal = 0, method = "applyDamage")
        public float chestCavityPlayerEntityOnHitMixin(float amount, DamageSource source){
            if(source.getAttacker() instanceof LivingEntity){
                Optional<ChestCavityEntity> cce = ChestCavityEntity.of(source.getAttacker());
                if(cce.isPresent()){
                    amount = ChestCavityUtil.onHit(cce.get().getChestCavityInstance(), source, (LivingEntity)(Object)this,amount);
                }
            }
            return amount;
        }

        @Inject(at = @At("HEAD"), method = "interact", cancellable = true)
        void chestCavityPlayerEntityInteractPlayerMixin(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> info) {
            if (entity instanceof LivingEntity && ChestCavity.config.CAN_OPEN_OTHER_PLAYERS) {
                PlayerEntity player = ((PlayerEntity) (Object) this);
                ItemStack stack = player.getStackInHand(hand);
    
                if (stack.getItem() == CCItems.CHEST_OPENER) {
                    ((ChestOpener) stack.getItem()).openChestCavity(player, (LivingEntity) entity);
                    info.setReturnValue(ActionResult.SUCCESS);
                    info.cancel();
                }
            }
        }
        @Inject(at = @At("RETURN"), method = "getBlockBreakingSpeed", cancellable = true)
        void chestCavityPlayerEntityGetBlockBreakingSpeedMixin(BlockState block, CallbackInfoReturnable<Float> cir) {
            cir.setReturnValue(ChestCavityUtil.applyNervesToMining(((ChestCavityEntity)this).getChestCavityInstance(),cir.getReturnValue()));
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
            OrganUtil.milkSilk(this);
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
                    if(cce.getChestCavityInstance().opened
                            && cce.getChestCavityInstance().getOrganScore(CCOrganScores.CREEPY) <= 0){
                        currentFuseTime = 1;
                    }
                });
            }
        }
        /*value = "FIELD",
                                target = "Lnet/minecraft/entity/CreeperEntity;dead:Z"*/
        //"Lnet/minecraft/world/explosion/Explosion;createExplosion("+
        //  "Lnet/minecraft/entity/Entity;"+
        //  "DDDF"+
        //  "Lnet/minecraft/world/explosion/Explosion/DestructionType;"+
        //  ")V"
        /*
        @ModifyVariable(at = @At(value = "INVOKE",
                target = "Lnet/minecraft/world/explosion/Explosion;createExplosion("+
                "Lnet/minecraft/entity/Entity;"+
                "DDDF"+
                "Lnet/minecraft/world/explosion/Explosion/DestructionType;"+
                ")V"), ordinal = 0, method = "explode")
        public float chestCavityCreeperExplodeMixin(float f){
            MutableFloat boom = new MutableFloat(f);
            ChestCavityEntity.of(this).ifPresent(cce -> {
                ChestCavityInstance cc = cce.getChestCavityInstance();
                if(cc.opened){
                    boom.setValue(f*Math.sqrt(cc.getOrganScore(CCOrganScores.EXPLOSIVE))
                            /Math.min(1,Math.sqrt(cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.EXPLOSIVE))));
                }
            });
            return f;
        }*/
    }

    @Mixin(ServerPlayerEntity.class)
    private static abstract class Server extends PlayerEntity {
        public Server(World world, BlockPos pos, float yaw, GameProfile profile) {
            super(world, pos, yaw, profile);
        }

        @Inject(method = "copyFrom", at = @At("TAIL"))
        public void copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo callbackInfo) {
            if(ChestCavity.DEBUG_MODE){
                System.out.println("Attempting to load ChestCavityInstance");
            }
            ChestCavityEntity.of(this).ifPresent(chestCavityEntity -> ChestCavityEntity.of(oldPlayer).ifPresent(oldCCPlayerEntityInterface -> {
                if(ChestCavity.DEBUG_MODE){
                    System.out.println("Copying ChestCavityInstance");
                }
                chestCavityEntity.getChestCavityInstance().clone(oldCCPlayerEntityInterface.getChestCavityInstance());
            }));
        }

        @Inject(at = @At("RETURN"), method = "moveToWorld")
        public void chestCavityEntityMoveToWorldMixin(ServerWorld destination, CallbackInfoReturnable<Entity> info){
            Entity entity = info.getReturnValue();
            if(entity instanceof ChestCavityEntity && !entity.getWorld().isClient){
                NetworkUtil.SendS2CChestCavityUpdatePacket(((ChestCavityEntity)entity).getChestCavityInstance());
            }
        }
    }

    @Mixin(SheepEntity.class)
    private static abstract class Sheep extends AnimalEntity {

        protected Sheep(EntityType<? extends AnimalEntity> entityType, World world) {super(entityType, world);}

        @Inject(method = "sheared",
                at = @At(value = "HEAD")
        )
        protected void chestCavitySheared(SoundCategory shearedSoundCategory, CallbackInfo info) {
            OrganUtil.shearSilk(this);
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
                ChestCavityInstance cc = chestCavityEntity.get().getChestCavityInstance();

                //if the nether star was taken from the wither's chest, refuse to drop
                if(cc.opened && cc.inventory.count(Items.NETHER_STAR) == 0){
                    info.cancel();
                }
            }
        }
    }

    @Shadow
    protected void initDataTracker() {}

    @Shadow
    protected void readCustomDataFromNbt(NbtCompound tag) {}

    @Shadow
    protected void writeCustomDataToNbt(NbtCompound tag) {}


    @Shadow
    protected int getNextAirOnLand(int air) {
        return 0;
    }
}
