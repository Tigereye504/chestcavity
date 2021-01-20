package net.tigereye.chestcavity.items;

import com.google.common.collect.Maps;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tigereye.chestcavity.managers.ChestCavityManager;
import net.tigereye.chestcavity.registration.CCItems;

import java.util.*;

import static net.tigereye.chestcavity.managers.ChestCavityManager.COMPATIBILITY_TAG;

public class Organ extends Item implements ChestCavityOrgan {

	protected Map<Identifier, Float> organQualityMap = Maps.newHashMap();



	public Organ() {
		super(CCItems.ORGAN_SETTINGS_1);
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
			if(score > 1.25f){
				tier = "Supernatural";
			}
			else if(score > 1){
				tier = "Exceptional";
			}
			else if(score > .75f){
				tier = "Good";
			}
			else if(score > .5f){
				tier = "Average";
			}
			else if(score > .25f){
				tier = "Poor";
			}
			else{
				tier = "Pathetic";
			}
			TranslatableText text = new TranslatableText("organscore."+organ.getNamespace()+"."+organ.getPath(), tier);
			tooltip.add(text);
		});
	}

	protected void displaySoulBinding(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
		CompoundTag tag = itemStack.getTag();
		if (tag != null && tag.contains(COMPATIBILITY_TAG.toString())) {
			tag = tag.getCompound(COMPATIBILITY_TAG.toString());
			if (tag.getInt("type") == ChestCavityManager.COMPATIBILITY_TYPE_PERSONAL) {
				String name = tag.getString("name");
				Text text = new LiteralText("Owner: "+name);
				tooltip.add(text);
			}
		}
	}
}
