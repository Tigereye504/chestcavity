package net.tigereye.chestcavity.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.components.CCComponent;
import net.tigereye.chestcavity.listeners.ChestCavityListener;
import net.tigereye.chestcavity.interfaces.CCHungerManagerInterface;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity extends LivingEntity {

	protected MixinPlayerEntity(PlayerEntity entityType, World world) {
		super(EntityType.PLAYER, world);
	}

	public void baseTick() {
		ChestCavityListener chestCavity = ((CCComponent) (ChestCavity.INVENTORYCOMPONENT
				.get((PlayerEntity) (Object) this))).getCCListener();
		chestCavity.TickHeart();
		chestCavity.TickKidney();
		chestCavity.TickLiver();
		super.baseTick();
	}

	protected int getNextAirUnderwater(int air) {
		ChestCavityListener chestCavity = ((CCComponent) (ChestCavity.INVENTORYCOMPONENT
				.get((PlayerEntity) (Object) this))).getCCListener();
		int i = EnchantmentHelper.getRespiration(this);
		return i > 0 && this.random.nextInt(i + 1) > 0 ? air : Math.max(air - chestCavity.applyLungCapacityInWater(),-20);
	 }
/*
	//TODO: Bug: gui doesn't sync up properly with air meter if land recovery is changed...
	//this part honestly isn't important, so I'll just turn it off and focus elsewhere for now.
	 protected int getNextAirOnLand(int air) {
		 if(air == this.getMaxAir()){
			return air;
		 }
		 else{
			ChestCavityListener chestCavity = ((CCComponent) (ChestCavity.INVENTORYCOMPONENT
			.get((PlayerEntity) (Object) this))).getCCListener();
			return Math.max(Math.min(air + ((int)(chestCavity.applyLungCapacityOnLand())), this.getMaxAir()),-20);
		 }
	 }
*/
	@Inject(at = @At("TAIL"), method = "damage", cancellable = true)
	public void chestCavityPlayerEntityDamageMixin(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info){
		ChestCavityListener chestCavity = ((CCComponent) (ChestCavity.INVENTORYCOMPONENT
				.get((PlayerEntity) (Object) this))).getCCListener();
		amount = chestCavity.applyBoneDefense(amount);
		info.setReturnValue(amount == 0.0F ? false : super.damage(source, amount));
		info.cancel();
	}

	@Inject(at = @At("HEAD"), method = "eatFood")
	public void chestCavityPlayerEatFoodMixin(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> info){
		((CCHungerManagerInterface)((PlayerEntity) (Object) this).getHungerManager()).ccEat(stack.getItem(), ((PlayerEntity) (Object) this));
	}

	@Inject(at = @At("HEAD"), method = "dropInventory")
	public void chestCavityPlayerEntityDropInventoryMixin(CallbackInfo info){
		((CCComponent) (ChestCavity.INVENTORYCOMPONENT.get((PlayerEntity) (Object) this))).chestCavityPostMortem();
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
	public void equipStack(EquipmentSlot slot, ItemStack stack) {

	}

	@Shadow
	public Arm getMainArm() {
		return null;
	}
}
