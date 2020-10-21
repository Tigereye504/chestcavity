package net.tigereye.chestcavity.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.ChestCavityManager;
import net.tigereye.chestcavity.interfaces.CCPlayerEntityInterface;
import net.tigereye.chestcavity.listeners.OrganTickCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity extends LivingEntity implements CCPlayerEntityInterface {

	private ChestCavityManager chestCavityManager  = new ChestCavityManager((PlayerEntity)(Object)this);

	protected MixinPlayerEntity(PlayerEntity entityType, World world) {
		super(EntityType.PLAYER, world);
	}

	public void baseTick() { //TODO: consider moving this to LivingEntity and check if its a PlayerEntity
		chestCavityManager.onTick();
		super.baseTick();
	}

	protected int getNextAirUnderwater(int air) { //TODO: consider moving this to LivingEntity and check if its a PlayerEntity
		int i = EnchantmentHelper.getRespiration(this);
		return i > 0 && this.random.nextInt(i + 1) > 0 ? air : Math.max(air - chestCavityManager.applyLungCapacityInWater(),-20);
	}

	@ModifyVariable(at = @At("HEAD"), method = "damage")
	public float chestCavityPlayerEntityDamageMixin(float amount){
		return chestCavityManager.applyBoneDefense(amount);
	}

	@Inject(at = @At("HEAD"), method = "dropInventory")
	public void chestCavityPlayerEntityDropInventoryMixin(CallbackInfo info){
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
		chestCavityManager.fromTag(tag,(PlayerEntity)(Object)this);
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
			CCPlayerEntityInterface.of(this).ifPresent(ccPlayerEntityInterface -> CCPlayerEntityInterface.of(oldPlayer).ifPresent(oldCCPlayerEntityInterface -> {
				if(ChestCavity.DEBUG_MODE){
					System.out.println("Copying ChestCavityManager");
				}
				ccPlayerEntityInterface.getChestCavityManager().clone(oldCCPlayerEntityInterface.getChestCavityManager());
			}));
		}
	}

	@Shadow
	public Iterable<ItemStack> getArmorItems() {
		return null;
	}

	@Shadow
	public ItemStack getEquippedStack(EquipmentSlot slot) {
		return null;
	}

	@Shadow
	public void equipStack(EquipmentSlot slot, ItemStack stack) {}

	@Shadow
	public Arm getMainArm() {
		return null;
	}


}
