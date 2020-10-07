package net.tigereye.chestcavity.items;

import com.google.common.collect.Maps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Map;

public class OrganBase extends Item implements ChestCavityOrgan {


	protected Map<Identifier, Float> organQualityMap = Maps.newHashMap();


	public OrganBase() {
		super(CCItems.ORGAN_SETTINGS_1);
	}

	public OrganBase(Item.Settings settings) {
		super(settings);
	}

	public Map<Identifier, Float> getOrganQualityMap() {
		return organQualityMap;
	}
	public Map<Identifier, Float> getOrganQualityMap(ItemStack item) {
		return getOrganQualityMap();
	}

	public float getOrganQuality(Identifier id) {
		return organQualityMap.getOrDefault(id, 0f);
	}

	public OrganBase setOrganQuality(Identifier id, float value) {
		organQualityMap.put(id, value);
		return this;
	}
}
