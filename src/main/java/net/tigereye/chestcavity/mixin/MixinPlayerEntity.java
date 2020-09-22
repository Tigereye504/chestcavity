package net.tigereye.chestcavity.mixin;

import net.tigereye.chestcavity.interfaces.CCPlayerEntityInterface;
import net.tigereye.chestcavity.items.CC_Items;
import net.tigereye.chestcavity.listeners.OrganTickCallback;
import net.tigereye.chestcavity.listeners.OrganTickListeners;
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

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.components.CCComponent;
import net.tigereye.chestcavity.listeners.ChestCavityListener;
//import net.tigereye.chestcavity.interfaces.CCHungerManagerInterface;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity extends LivingEntity implements CCPlayerEntityInterface {

	private int CCHeartTimer = 0;
	private int CCKidneyTimer = 0;
	private int CCLiverTimer = 0;
	private int CCSpleenTimer = 0;
	private int CCLungRemainder = 0;

	protected MixinPlayerEntity(PlayerEntity entityType, World world) {
		super(EntityType.PLAYER, world);
	}

	public void baseTick() {
		ChestCavityListener chestCavity = ((CCComponent) (ChestCavity.INVENTORYCOMPONENT
				.get((PlayerEntity) (Object) this))).getCCListener();
		OrganTickCallback.EVENT.invoker().onOrganTick(((PlayerEntity) (Object) this), chestCavity);
		super.baseTick();
	}

	protected int getNextAirUnderwater(int air) {
		ChestCavityListener chestCavity = ((CCComponent) (ChestCavity.INVENTORYCOMPONENT
				.get((PlayerEntity) (Object) this))).getCCListener();
		int i = EnchantmentHelper.getRespiration(this);
		return i > 0 && this.random.nextInt(i + 1) > 0 ? air : Math.max(air - chestCavity.applyLungCapacityInWater(),-20);
	}

	@ModifyVariable(at = @At("HEAD"), method = "damage")
	public float chestCavityPlayerEntityDamageMixin(float amount){
		ChestCavityListener chestCavity = ((CCComponent) (ChestCavity.INVENTORYCOMPONENT
				.get((PlayerEntity) (Object) this))).getCCListener();
		return chestCavity.applyBoneDefense(amount);
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
	public void equipStack(EquipmentSlot slot, ItemStack stack) {}

	@Shadow
	public Arm getMainArm() {
		return null;
	}

	public int getCCHeartTimer() {
		return CCHeartTimer;
	}

	public void setCCHeartTimer(int CCHeartTimer) {
		this.CCHeartTimer = CCHeartTimer;
	}

	public int getCCKidneyTimer() {
		return CCKidneyTimer;
	}

	public void setCCKidneyTimer(int CCKidneyTimer) {
		this.CCKidneyTimer = CCKidneyTimer;
	}

	public int getCCLiverTimer() {
		return CCLiverTimer;
	}

	public void setCCLiverTimer(int CCLiverTimer) {
		this.CCLiverTimer = CCLiverTimer;
	}

	public int getCCSpleenTimer() {
		return CCSpleenTimer;
	}

	public void setCCSpleenTimer(int CCSpleenTimer) {
		this.CCSpleenTimer = CCSpleenTimer;
	}

	public int getCCLungRemainder() {
		return CCLungRemainder;
	}

	public void setCCLungRemainder(int CCLungRemainder) {
		this.CCLungRemainder = CCLungRemainder;
	}
}
