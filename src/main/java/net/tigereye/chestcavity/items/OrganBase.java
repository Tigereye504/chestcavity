package net.tigereye.chestcavity.items;

import net.minecraft.item.Item;

public class OrganBase extends Item implements ChestCavityOrgan{

	float organQuality;
	public OrganBase() {
		super(RegisterItems.ORGAN_SETTINGS_1);
		organQuality = 1;
	}
	
	public OrganBase(float quality){
		super(RegisterItems.ORGAN_SETTINGS_1);
		this.organQuality = quality;
	}

	public OrganBase(Item.Settings settings){
		super(settings);
		this.organQuality = 1;
	}

	public OrganBase(float quality, Item.Settings settings){
		super(settings);
		this.organQuality = quality;
	}

	public float getOrganQuality(){
		return organQuality;
	}
}
