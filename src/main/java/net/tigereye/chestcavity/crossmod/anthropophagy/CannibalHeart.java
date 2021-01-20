package net.tigereye.chestcavity.crossmod.anthropophagy;

import moriyashiine.anthropophagy.api.accessor.CannibalAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.items.Organ;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.HashMap;
import java.util.Map;

public class CannibalHeart extends Organ {

    public static final float BONUS_HEART_PER_100_WENDIGOISM = .25f;

    public static float getBonusHeart(int wendigoism){
        return BONUS_HEART_PER_100_WENDIGOISM * wendigoism / 100;
    }
    @Override
    public Map<Identifier, Float> getOrganQualityMap(ItemStack item, LivingEntity entity) {
        Map<Identifier,Float> retMap = new HashMap<>(organQualityMap);
        retMap.put(CCOrganScores.HEALTH,retMap.getOrDefault(CCOrganScores.HEALTH,0f)+getBonusHeart(((CannibalAccessor)entity).getCannibalLevel()));
        return retMap;
    }

}
