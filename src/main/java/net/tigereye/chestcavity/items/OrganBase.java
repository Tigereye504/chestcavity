package net.tigereye.chestcavity.items;

import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

public class OrganBase extends Item implements ChestCavityOrgan {

	float apendixQuality = 0;
	float heartQuality = 0;
	float intestineQuality = 0;
	float kidneyQuality = 0;
	float liverQuality = 0;
	float lungQuality = 0;
	float muscleQuality = 0;
	float ribQuality = 0;
	float spineQuality = 0;
	float spleenQuality = 0;
	float stomachQuality = 0;


	public OrganBase() {
		super(CC_Items.ORGAN_SETTINGS_1);
	}

	public OrganBase(float quality,Tag<Item> tag) {
		super(CC_Items.ORGAN_SETTINGS_1);
		setOrganQuality(quality,tag);
	}

	public OrganBase(Item.Settings settings) {
		super(settings);
	}

	public float getOrganQuality(Tag<Item> tag) {
		if(tag == CC_Items.ORGANS_APENDIX){
			return apendixQuality;
		}
		else if(tag == CC_Items.ORGANS_HEART){
			return heartQuality;
		}
		else if(tag == CC_Items.ORGANS_INTESTINE){
			return intestineQuality;
		}
		else if(tag == CC_Items.ORGANS_KIDNEY){
			return kidneyQuality;
		}
		else if(tag == CC_Items.ORGANS_LIVER){
			return liverQuality;
		}
		else if(tag == CC_Items.ORGANS_LUNG){
			return lungQuality;
		}
		else if(tag == CC_Items.ORGANS_MUSCLE){
			return muscleQuality;
		}
		else if(tag == CC_Items.ORGANS_RIB){
			return ribQuality;
		}
		else if(tag == CC_Items.ORGANS_SPINE){
			return spineQuality;
		}
		else if(tag == CC_Items.ORGANS_SPLEEN){
			return spleenQuality;
		}
		else if(tag == CC_Items.ORGANS_STOMACH){
			return stomachQuality;
		}
		return 0;
	}

	public OrganBase setOrganQuality(float quality, Tag<Item> tag){
		if(tag == CC_Items.ORGANS_APENDIX){
			apendixQuality = quality;
		}
		else if(tag == CC_Items.ORGANS_HEART){
			heartQuality = quality;
		}
		else if(tag == CC_Items.ORGANS_INTESTINE){
			intestineQuality = quality;
		}
		else if(tag == CC_Items.ORGANS_KIDNEY){
			kidneyQuality = quality;
		}
		else if(tag == CC_Items.ORGANS_LIVER){
			liverQuality = quality;
		}
		else if(tag == CC_Items.ORGANS_LUNG){
			lungQuality = quality;
		}
		else if(tag == CC_Items.ORGANS_MUSCLE){
			muscleQuality = quality;
		}
		else if(tag == CC_Items.ORGANS_RIB){
			ribQuality = quality;
		}
		else if(tag == CC_Items.ORGANS_SPINE){
			spineQuality = quality;
		}
		else if(tag == CC_Items.ORGANS_SPLEEN){
			spleenQuality = quality;
		}
		else if(tag == CC_Items.ORGANS_STOMACH){
			stomachQuality = quality;
		}
		return this;
	}
}
