package net.tigereye.chestcavity.items;

import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.Map;

public interface ChestCavityOrgan {
    Map<Identifier,Float> getOrganQualityMap();
}