package net.tigereye.chestcavity.mixin;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.listeners.EffectiveFoodScores;
import net.tigereye.chestcavity.listeners.OrganFoodCallback;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;
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

@Mixin(HungerManager.class)
public class MixinHungerManager {

        @Shadow
        private int foodStarvationTimer;
        @Shadow
        private int foodLevel;
        @Shadow
        private float foodSaturationLevel;
        @Shadow
        private float exhaustion;

        private ChestCavityEntity CC_player = null;

        @Inject(at = @At("HEAD"), method = "update", cancellable = true)
        public void chestCavityUpdateMixin(PlayerEntity player, CallbackInfo info) {
                if(CC_player == null){
                        ChestCavityEntity.of(player).ifPresent(ccPlayerEntityInterface -> {
                                CC_player = ccPlayerEntityInterface;
                        });
                }
                foodStarvationTimer = ChestCavityUtil.applySpleenMetabolism(CC_player.getChestCavityInstance(),this.foodStarvationTimer);
        }


        @ModifyVariable(at = @At("HEAD"), method = "eat")
        public Item chestCavityEatMixin(Item item) {
                if(item.isFood() && CC_player != null){
                        //saturation gains are equal to hungerValue*saturationModifier*2
                        //this is kinda stupid, if I half the hunger gains from food I don't want to also half saturation gains
                        //so before hunger changes, calculate the saturation gain I intend
                        FoodComponent itemFoodComponent = item.getFoodComponent();
                        if(itemFoodComponent != null) {
                                EffectiveFoodScores efs = new EffectiveFoodScores(
                                        CC_player.getChestCavityInstance().getOrganScore(CCOrganScores.DIGESTION),
                                        CC_player.getChestCavityInstance().getOrganScore(CCOrganScores.NUTRITION));
                                efs = OrganFoodCallback.EVENT.invoker().onEatFood(item,itemFoodComponent,CC_player,efs);
                                float saturationGain = ChestCavityUtil.applyNutrition(CC_player.getChestCavityInstance(),efs.nutrition,item.getFoodComponent().getSaturationModifier())
                                         * item.getFoodComponent().getHunger() * 2.0F;
                                //now find the modified hunger gains
                                int hungerGain = ChestCavityUtil.applyDigestion(CC_player.getChestCavityInstance(),efs.digestion,item.getFoodComponent().getHunger());
                                //now calculate the saturation modifier that gives me what I want
                                float newSaturation = saturationGain / (hungerGain * 2);
                                //now make a dummy food item with the modified stats and feed it to HungerManager.eat();
                                FoodComponent dummyFood = new FoodComponent.Builder().hunger(hungerGain).saturationModifier(newSaturation).build();
                                Item.Settings dummySettings = new Item.Settings().food(dummyFood);
                                return new Item(dummySettings);
                        }
                }
                return item;
        }

        @ModifyVariable(at = @At("HEAD"), ordinal = 0, method = "addExhaustion")
        public float chestCavityAddExhaustionMixin(float exhaustion) {
                if(CC_player != null){
                        if(this.exhaustion != this.exhaustion){
                                //NaN check. Not sure what keep causing it...
                                this.exhaustion = 0;
                        }
                        float enduranceDif = CC_player.getChestCavityInstance().getOrganScore(CCOrganScores.ENDURANCE)-CC_player.getChestCavityInstance().getChestCavityType().getDefaultOrganScore(CCOrganScores.ENDURANCE);
                        ChestCavity.LOGGER.info("In: "+exhaustion);
                        float out;
                        if(enduranceDif > 0) {
                                out = exhaustion/(1+(enduranceDif/2));
                        }
                        else{
                                out = exhaustion*(1-(enduranceDif/2));
                        }
                        //float out = exhaustion*(float)Math.pow(ChestCavity.config.LUNG_ENDURANCE,enduranceDif/2);
                        ChestCavity.LOGGER.info("Out: "+out);
                        return out;
                }
                return exhaustion;
        }
}