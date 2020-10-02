package net.tigereye.chestcavity.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.components.CCComponent;
import net.tigereye.chestcavity.listeners.ChestCavityListener;

@Mixin(HungerManager.class)
public class MixinHungerManager {

        @Shadow
        private int foodStarvationTimer;
        @Shadow
        private int foodLevel;
        @Shadow
        private float foodSaturationLevel;

        private PlayerEntity CC_player = null;

        @Inject(at = @At("HEAD"), method = "update", cancellable = true)
        public void chestCavityUpdateMixin(PlayerEntity player, CallbackInfo info) {
                if(CC_player == null){
                        CC_player = player;
                }
                ChestCavityListener chestCavity = ((CCComponent) (ChestCavity.INVENTORYCOMPONENT.get(player)))
                                .getCCListener();
                foodStarvationTimer = chestCavity.applySpleenMetabolism(this.foodStarvationTimer);
        }


        @ModifyVariable(at = @At("HEAD"), method = "eat")
        public Item chestCavityEatMixin(Item item) {
                if(item.isFood() && CC_player != null){
                        ChestCavityListener chestCavity = ((CCComponent) (ChestCavity.INVENTORYCOMPONENT.get(CC_player)))
                                .getCCListener();
                        //saturation gains are equal to hungerValue*saturationModifier*2
                        //this is kinda stupid, if I half the hunger gains from food I don't want to also half saturation gains
                        //so before hunger changes, calculate the saturation gain I intend
                        float saturationGain = chestCavity.applyIntestinesSaturation(item.getFoodComponent().getSaturationModifier())*item.getFoodComponent().getHunger()*2.0F;
                        //now find the modified hunger gains
                        int hungerGain = chestCavity.applyStomachHunger(item.getFoodComponent().getHunger());
                        //now calculate the saturation modifier that gives me what I want
                        float newSaturation = saturationGain / (hungerGain*2);
                        //now make a dummy food item with the modified stats and feed it to HungerManager.eat();
                        FoodComponent dummyFood = new FoodComponent.Builder().hunger(hungerGain).saturationModifier(newSaturation).build();
                        Item.Settings dummySettings = new Item.Settings().food(dummyFood);
                        return new Item(dummySettings);
                }
                return item;
        }

}