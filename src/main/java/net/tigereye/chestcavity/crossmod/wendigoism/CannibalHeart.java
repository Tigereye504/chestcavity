package net.tigereye.chestcavity.crossmod.wendigoism;

import moriyashiine.wendigoism.api.accessor.WendigoAccessor;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tigereye.chestcavity.items.OrganBase;
import net.tigereye.chestcavity.managers.ChestCavityManager;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CannibalHeart extends OrganBase {

    public static final float BONUS_HEART_PER_100_WENDIGOISM = .25f;

    public static float getBonusHeart(int wendigoism){
        return BONUS_HEART_PER_100_WENDIGOISM * wendigoism / 100;
    }
    @Override
    public Map<Identifier, Float> getOrganQualityMap(ItemStack item, LivingEntity entity) {
        Map<Identifier,Float> retMap = new HashMap<>(organQualityMap);
        retMap.put(CCOrganScores.HEART,retMap.getOrDefault(CCOrganScores.HEART,0f)+getBonusHeart(((WendigoAccessor)entity).getWendigoLevel()));
        return retMap;
    }

}
