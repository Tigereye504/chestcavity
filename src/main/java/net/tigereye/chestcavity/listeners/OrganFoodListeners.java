package net.tigereye.chestcavity.listeners;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;
import net.tigereye.chestcavity.registration.CCTags;

public class OrganFoodListeners {

    public static void register(){
        OrganFoodCallback.EVENT.register(OrganFoodListeners::applyHerbivorousCarnivorous);
        OrganFoodCallback.EVENT.register(OrganFoodListeners::applyRot);
        OrganFoodCallback.EVENT.register(OrganFoodListeners::applyFurnacePower);
    }

    private static EffectiveFoodScores applyHerbivorousCarnivorous(Item food, FoodProperties foodComponent, ChestCavityEntity cce, EffectiveFoodScores efs) {
        if(food.getDefaultInstance().is(net.minecraft.tags.ItemTags.MEAT) || food.getDefaultInstance().is(CCTags.CARNIVORE_FOOD)){
            efs.digestion += cce.getChestCavityInstance().getOrganScore(CCOrganScores.CARNIVOROUS_DIGESTION);
            efs.nutrition += cce.getChestCavityInstance().getOrganScore(CCOrganScores.CARNIVOROUS_NUTRITION);
        }
        else{
            efs.digestion += cce.getChestCavityInstance().getOrganScore(CCOrganScores.HERBIVOROUS_DIGESTION);
            efs.nutrition += cce.getChestCavityInstance().getOrganScore(CCOrganScores.HERBIVOROUS_NUTRITION);
        }
        return efs;
    }

    private static EffectiveFoodScores applyRot(Item food, FoodProperties foodComponent, ChestCavityEntity cce, EffectiveFoodScores efs) {
        if(food.getDefaultInstance().is(CCTags.ROTTEN_FOOD)){
            efs.digestion += cce.getChestCavityInstance().getOrganScore(CCOrganScores.ROT_DIGESTION);
            efs.nutrition += cce.getChestCavityInstance().getOrganScore(CCOrganScores.ROTGUT);
        }
        return efs;
    }

    private static EffectiveFoodScores applyFurnacePower(Item food, FoodProperties foodComponent, ChestCavityEntity cce, EffectiveFoodScores efs) {
        if(food == CCItems.FURNACE_POWER){
            int power = 0;
            if(cce.getChestCavityInstance().owner.hasEffect(CCStatusEffects.FURNACE_POWER)){
                power = cce.getChestCavityInstance().owner.getEffect(CCStatusEffects.FURNACE_POWER).getAmplifier() + 1;
            }
            //herbivorous will have gotten a false positive, so that needs corrected
            efs.digestion -= cce.getChestCavityInstance().getOrganScore(CCOrganScores.HERBIVOROUS_DIGESTION);
            efs.nutrition -= cce.getChestCavityInstance().getOrganScore(CCOrganScores.HERBIVOROUS_NUTRITION);
            //nutrition scales with furnaces
            efs.nutrition += power;
        }
        return efs;
    }
}
