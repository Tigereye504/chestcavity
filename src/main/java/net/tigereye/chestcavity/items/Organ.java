package net.tigereye.chestcavity.items;

import com.google.common.collect.Maps;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.*;

public class Organ extends Item implements ChestCavityOrgan {

	protected Map<Identifier, Float> organQualityMap = Maps.newHashMap();



	public Organ() {
		super(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	}
	public Organ(int stackSize) {
		super(new Item.Settings().maxCount(stackSize).group(ChestCavity.ORGAN_ITEM_GROUP));
	}
	public Organ(int stackSize, FoodComponent foodComponent) {
		super(new Item.Settings().maxCount(stackSize).group(ChestCavity.ORGAN_ITEM_GROUP).food(foodComponent));
	}

	public Organ(Item.Settings settings) {
		super(settings);
	}

	public Map<Identifier, Float> getOrganQualityMap() {
		return organQualityMap;
	}
	public Map<Identifier, Float> getOrganQualityMap(ItemStack item) {
		return getOrganQualityMap();
	}
	public Map<Identifier, Float> getOrganQualityMap(ItemStack item, LivingEntity entity) {
		return getOrganQualityMap();
	}

	public float getOrganQuality(Identifier id) {
		return organQualityMap.getOrDefault(id, 0f);
	}

	public Organ setOrganQuality(Identifier id, float value) {
		organQualityMap.put(id, value);
		return this;
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
		displayOrganQuality(itemStack,world,tooltip,tooltipContext);
		displaySoulBinding(itemStack,world,tooltip,tooltipContext);
		super.appendTooltip(itemStack,world,tooltip,tooltipContext);
	}

	protected void displayOrganQuality(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext){
		organQualityMap.forEach((organ,score) -> {
			String tier;
			if(organ == CCOrganScores.HYDROALLERGENIC){
				if(score >= 2){
					tier = "Severely ";
				}
				else{
					tier = "";
				}
			}
			else {
				if (score >= 1.5f) {
					tier = "Supernatural ";
				} else if (score >= 1.25) {
					tier = "Exceptional ";
				} else if (score >= 1) {
					tier = "Good ";
				} else if (score >= .75f) {
					tier = "Average ";
				} else if (score >= .5f) {
					tier = "Poor ";
				} else if (score >= 0) {
					tier = "Pathetic ";
				} else if (score >= -.25f) {
					tier = "Slightly Reduces ";
				} else if (score >= -.5f) {
					tier = "Reduces ";
				} else if (score >= -.75f) {
					tier = "Greatly Reduces ";
				} else {
					tier = "Cripples ";
				}
			}
			TranslatableText text = new TranslatableText("organscore." + organ.getNamespace() + "." + organ.getPath(), tier);
			tooltip.add(text);
		});
	}

	protected void displaySoulBinding(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
		CompoundTag tag = itemStack.getTag();
		if (tag != null && tag.contains(ChestCavity.COMPATIBILITY_TAG.toString())) {
			tag = tag.getCompound(ChestCavity.COMPATIBILITY_TAG.toString());
			String name = tag.getString("name");
			Text text = new LiteralText("Owner: "+name);
			tooltip.add(text);
		}
	}


}
