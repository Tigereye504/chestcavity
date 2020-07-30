package net.tigereye.chestcavity.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.components.CCComponent;
import net.tigereye.chestcavity.listeners.ChestCavityListener;
import net.tigereye.chestcavity.interfaces.CCHungerManagerInterface;

@Mixin(HungerManager.class)
public class MixinHungerManager implements CCHungerManagerInterface {

        private boolean justCCAte = false;
        @Shadow
        private int foodStarvationTimer;
        @Shadow
        private int foodLevel;
        @Shadow
        private float foodSaturationLevel;

        @Inject(at = @At("HEAD"), method = "update", cancellable = true)
        public void chestCavityUpdateMixin(PlayerEntity player, CallbackInfo info) {
                ChestCavityListener chestCavity = ((CCComponent) (ChestCavity.INVENTORYCOMPONENT.get(player)))
                                .getCCListener();
                foodStarvationTimer = chestCavity.applySpleenMetabolism(this.foodStarvationTimer);
        }

        @Override
        public void ccEat(Item item, PlayerEntity player) {
                if (item.isFood()) {
                        ChestCavityListener chestCavity = ((CCComponent) (ChestCavity.INVENTORYCOMPONENT.get(player)))
                                .getCCListener();
                        FoodComponent foodComponent = item.getFoodComponent();
                        int hungerToRestore = chestCavity.applyStomachHunger(foodComponent.getHunger());
                        this.foodLevel = Math.min(hungerToRestore + this.foodLevel, 20);
                        float saturationToRestore = (float)foodComponent.getHunger() * chestCavity.applyIntestinesSaturation(foodComponent.getSaturationModifier()) * 2.0F;
                        this.foodSaturationLevel = Math.min(this.foodSaturationLevel + saturationToRestore, (float)this.foodLevel);
                }
                justCCAte = true;
        }

        @Inject(at = @At("HEAD"), method = "eat", cancellable = true)
        public void chestCavityEatMixin(Item item, ItemStack stack, CallbackInfo info) {
                if(justCCAte){
                        justCCAte = false;
                        info.cancel();
                }
        }

}