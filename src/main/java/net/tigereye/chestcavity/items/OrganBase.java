package net.tigereye.chestcavity.items;

import com.google.common.collect.Maps;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Map;

public class OrganBase extends Item implements ChestCavityOrgan {


	private Map<Identifier, Float> organQualityMap = Maps.newHashMap();


	public OrganBase() {
		super(CCItems.ORGAN_SETTINGS_1);
	}

	public OrganBase(Item.Settings settings) {
		super(settings);
	}

	public Map<Identifier, Float> getOrganQualityMap() {
		return organQualityMap;
	}

	public float getOrganQuality(Identifier id) {
		return organQualityMap.getOrDefault(id, 0f);
	}

	public OrganBase setOrganQuality(Identifier id, float value) {
		organQualityMap.put(id, value);
		return this;
	}
}
