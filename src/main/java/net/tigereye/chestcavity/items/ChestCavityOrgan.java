package net.tigereye.chestcavity.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.managers.ChestCavityManager;

import java.util.Map;

public interface ChestCavityOrgan {
    Map<Identifier,Float> getOrganQualityMap();
    Map<Identifier,Float> getOrganQualityMap(ItemStack item);
    Map<Identifier,Float> getOrganQualityMap(ItemStack item, LivingEntity entity);
}