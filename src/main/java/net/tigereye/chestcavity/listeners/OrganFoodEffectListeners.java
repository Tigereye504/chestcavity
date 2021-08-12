package net.tigereye.chestcavity.listeners;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCTags;

import java.util.List;

public class OrganFoodEffectListeners {

    public static void register(){
        OrganFoodEffectCallback.EVENT.register(OrganFoodEffectListeners::applyRotgut);
    }

    private static List<Pair<StatusEffectInstance, Float>> applyRotgut(List<Pair<StatusEffectInstance, Float>> list, ItemStack itemStack, World world, LivingEntity entity, ChestCavityInstance cc) {
        float rotten = cc.getOrganScore(CCOrganScores.ROTGUT)+cc.getOrganScore(CCOrganScores.ROT_DIGESTION);
        if(rotten > 0){
            if(itemStack.isIn(CCTags.ROTTEN_FOOD)) {
                list.removeIf(pair -> pair.getFirst().getEffectType() == StatusEffects.HUNGER);
            }
            else {
                StatusEffectInstance hunger = new StatusEffectInstance(StatusEffects.HUNGER, 600);
                list.add(new Pair<>(hunger, rotten * .2f));
            }
        }
        return list;
    }
}
