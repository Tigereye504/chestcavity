package net.tigereye.chestcavity.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.components.CCComponent;
import net.tigereye.chestcavity.listeners.ChestCavityListener;

@Mixin(HungerManager.class)
public class MixinHungerManager{

    @Shadow
    private int foodStarvationTimer;

    @Inject(at = @At("HEAD"), method = "update", cancellable = true)
	public void chestCavityPlayerEntityDamageMixin(PlayerEntity player, CallbackInfo info){
		ChestCavityListener chestCavity = ((CCComponent) (ChestCavity.INVENTORYCOMPONENT
                .get(player))).getCCListener();
        foodStarvationTimer = chestCavity.applySpleenMetabolism(this.foodStarvationTimer);
	}

}