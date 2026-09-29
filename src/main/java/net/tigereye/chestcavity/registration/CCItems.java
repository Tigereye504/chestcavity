package net.tigereye.chestcavity.registration;
import net.minecraft.core.Registry;

import net.minecraft.world.food.Foods;

import net.minecraft.world.item.CreativeModeTab;

import net.minecraft.world.item.Item;



import net.minecraft.world.item.ToolMaterial;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.items.ChestOpener;
import net.tigereye.chestcavity.items.CreeperAppendix;
import net.tigereye.chestcavity.items.VenomGland;

public class CCItems {
    public static final java.util.Map<String, Item> ALL = new java.util.LinkedHashMap<>();

	public static final Item CHEST_OPENER = track("chest_opener", new ChestOpener(properties("chest_opener")));
	public static final Item WOODEN_CLEAVER = track("wooden_cleaver", new Item(properties("wooden_cleaver").sword(ToolMaterial.WOOD,6,-3.2f)));
	public static final Item GOLD_CLEAVER = track("gold_cleaver", new Item(properties("gold_cleaver").sword(ToolMaterial.GOLD,6,-3.0f)));
	public static final Item STONE_CLEAVER = track("stone_cleaver", new Item(properties("stone_cleaver").sword(ToolMaterial.STONE,7,-3.2f)));
	public static final Item IRON_CLEAVER = track("iron_cleaver", new Item(properties("iron_cleaver").sword(ToolMaterial.IRON,6,-3.1f)));
	public static final Item DIAMOND_CLEAVER = track("diamond_cleaver", new Item(properties("diamond_cleaver").sword(ToolMaterial.DIAMOND,5,-3.0f)));
	public static final Item NETHERITE_CLEAVER = track("netherite_cleaver", new Item(properties("netherite_cleaver").fireResistant().sword(ToolMaterial.NETHERITE,5,-3.0f)));

	public static final Item HUMAN_APPENDIX = track("appendix", new Item(properties("appendix").stacksTo(1).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item HUMAN_HEART = track("heart", new Item(properties("heart").stacksTo(1).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item HUMAN_INTESTINE = track("intestine", new Item(properties("intestine").stacksTo(1).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item HUMAN_KIDNEY = track("kidney", new Item(properties("kidney").stacksTo(1).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item HUMAN_LIVER = track("liver", new Item(properties("liver").stacksTo(1).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item HUMAN_LUNG = track("lung", new Item(properties("lung").stacksTo(1).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item HUMAN_MUSCLE = track("muscle", new Item(properties("muscle").stacksTo(16).food(CCFoodComponents.HUMAN_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.HUMAN_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item HUMAN_RIB = track("rib", new Item(properties("rib").stacksTo(4)));
	public static final Item HUMAN_SPINE = track("spine", new Item(properties("spine").stacksTo(1)));
	public static final Item HUMAN_SPLEEN = track("spleen", new Item(properties("spleen").stacksTo(1).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item HUMAN_STOMACH = track("stomach", new Item(properties("stomach").stacksTo(1).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));

	public static final Item ROTTEN_APPENDIX = track("rotten_appendix", new Item(properties("rotten_appendix").stacksTo(1).food(Foods.ROTTEN_FLESH, net.minecraft.world.item.component.Consumables.ROTTEN_FLESH)));
	public static final Item ROTTEN_HEART = track("rotten_heart", new Item(properties("rotten_heart").stacksTo(1).food(Foods.ROTTEN_FLESH, net.minecraft.world.item.component.Consumables.ROTTEN_FLESH)));
	public static final Item ROTTEN_INTESTINE = track("rotten_intestine", new Item(properties("rotten_intestine").stacksTo(1).food(Foods.ROTTEN_FLESH, net.minecraft.world.item.component.Consumables.ROTTEN_FLESH)));
	public static final Item ROTTEN_KIDNEY = track("rotten_kidney", new Item(properties("rotten_kidney").stacksTo(1).food(Foods.ROTTEN_FLESH, net.minecraft.world.item.component.Consumables.ROTTEN_FLESH)));
	public static final Item ROTTEN_LIVER = track("rotten_liver", new Item(properties("rotten_liver").stacksTo(1).food(Foods.ROTTEN_FLESH, net.minecraft.world.item.component.Consumables.ROTTEN_FLESH)));
	public static final Item ROTTEN_LUNG = track("rotten_lung", new Item(properties("rotten_lung").stacksTo(1).food(Foods.ROTTEN_FLESH, net.minecraft.world.item.component.Consumables.ROTTEN_FLESH)));
	public static final Item ROTTEN_MUSCLE = track("rotten_muscle", new Item(properties("rotten_muscle").stacksTo(16).food(CCFoodComponents.ROTTEN_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.ROTTEN_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item ROTTEN_RIB = track("rotten_rib", new Item(properties("rotten_rib").stacksTo(4)));
	public static final Item ROTTEN_SPINE = track("rotten_spine", new Item(properties("rotten_spine").stacksTo(1)));
	public static final Item ROTTEN_SPLEEN = track("rotten_spleen", new Item(properties("rotten_spleen").stacksTo(1).food(Foods.ROTTEN_FLESH, net.minecraft.world.item.component.Consumables.ROTTEN_FLESH)));
	public static final Item ROTTEN_STOMACH = track("rotten_stomach", new Item(properties("rotten_stomach").stacksTo(1).food(Foods.ROTTEN_FLESH, net.minecraft.world.item.component.Consumables.ROTTEN_FLESH)));
	public static final Item WITHERED_RIB = track("withered_rib", new Item(properties("withered_rib").stacksTo(4)));
	public static final Item WITHERED_SPINE = track("withered_spine", new Item(properties("withered_spine").stacksTo(1)));
	public static final Item WRITHING_SOULSAND = track("writhing_soulsand", new Item(properties("writhing_soulsand").stacksTo(16)));

	public static final Item ANIMAL_APPENDIX = track("animal_appendix", new Item(properties("animal_appendix").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ANIMAL_HEART = track("animal_heart", new Item(properties("animal_heart").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ANIMAL_INTESTINE = track("animal_intestine", new Item(properties("animal_intestine").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ANIMAL_KIDNEY = track("animal_kidney", new Item(properties("animal_kidney").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ANIMAL_LIVER = track("animal_liver", new Item(properties("animal_liver").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ANIMAL_LUNG = track("animal_lung", new Item(properties("animal_lung").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ANIMAL_MUSCLE = track("animal_muscle", new Item(properties("animal_muscle").stacksTo(16).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item ANIMAL_RIB = track("animal_rib", new Item(properties("animal_rib").stacksTo(4)));
	public static final Item ANIMAL_SPINE = track("animal_spine", new Item(properties("animal_spine").stacksTo(1)));
	public static final Item ANIMAL_SPLEEN = track("animal_spleen", new Item(properties("animal_spleen").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ANIMAL_STOMACH = track("animal_stomach", new Item(properties("animal_stomach").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item AQUATIC_MUSCLE = track("aquatic_muscle", new Item(properties("aquatic_muscle").stacksTo(16).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item FISH_MUSCLE = track("fish_muscle", new Item(properties("fish_muscle").stacksTo(16).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item GILLS = track("gills", new Item(properties("gills").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item LLAMA_LUNG = track("llama_lung", new Item(properties("llama_lung").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item CARNIVORE_STOMACH = track("carnivore_stomach", new Item(properties("carnivore_stomach").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item CARNIVORE_INTESTINE = track("carnivore_intestine", new Item(properties("carnivore_intestine").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item HERBIVORE_RUMEN = track("herbivore_rumen", new Item(properties("herbivore_rumen").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item HERBIVORE_STOMACH = track("herbivore_stomach", new Item(properties("herbivore_stomach").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item HERBIVORE_INTESTINE = track("herbivore_intestine", new Item(properties("herbivore_intestine").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item BRUTISH_MUSCLE = track("brutish_muscle", new Item(properties("brutish_muscle").stacksTo(16).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SWIFT_MUSCLE = track("swift_muscle", new Item(properties("swift_muscle").stacksTo(16).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SPRINGY_MUSCLE = track("springy_muscle", new Item(properties("springy_muscle").stacksTo(16).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));

	public static final Item FIREPROOF_APPENDIX = track("fireproof_appendix", new Item(properties("fireproof_appendix").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item FIREPROOF_HEART = track("fireproof_heart", new Item(properties("fireproof_heart").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item FIREPROOF_INTESTINE = track("fireproof_intestine", new Item(properties("fireproof_intestine").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item FIREPROOF_KIDNEY = track("fireproof_kidney", new Item(properties("fireproof_kidney").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item FIREPROOF_LIVER = track("fireproof_liver", new Item(properties("fireproof_liver").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item FIREPROOF_LUNG = track("fireproof_lung", new Item(properties("fireproof_lung").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item FIREPROOF_MUSCLE = track("fireproof_muscle", new Item(properties("fireproof_muscle").stacksTo(16).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item FIREPROOF_RIB = track("fireproof_rib", new Item(properties("fireproof_rib").stacksTo(4)));
	public static final Item FIREPROOF_SPINE = track("fireproof_spine", new Item(properties("fireproof_spine").stacksTo(1)));
	public static final Item FIREPROOF_SPLEEN = track("fireproof_spleen", new Item(properties("fireproof_spleen").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item FIREPROOF_STOMACH = track("fireproof_stomach", new Item(properties("fireproof_stomach").stacksTo(1).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	
	public static final Item SMALL_ANIMAL_APPENDIX = track("small_animal_appendix", new Item(properties("small_animal_appendix").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_ANIMAL_HEART = track("small_animal_heart", new Item(properties("small_animal_heart").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_ANIMAL_INTESTINE = track("small_animal_intestine", new Item(properties("small_animal_intestine").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_ANIMAL_KIDNEY = track("small_animal_kidney", new Item(properties("small_animal_kidney").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_ANIMAL_LIVER = track("small_animal_liver", new Item(properties("small_animal_liver").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_ANIMAL_LUNG = track("small_animal_lung", new Item(properties("small_animal_lung").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_ANIMAL_MUSCLE = track("small_animal_muscle", new Item(properties("small_animal_muscle").stacksTo(16).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_ANIMAL_RIB = track("small_animal_rib", new Item(properties("small_animal_rib").stacksTo(4)));
	public static final Item SMALL_ANIMAL_SPINE = track("small_animal_spine", new Item(properties("small_animal_spine").stacksTo(1)));
	public static final Item SMALL_ANIMAL_SPLEEN = track("small_animal_spleen", new Item(properties("small_animal_spleen").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_ANIMAL_STOMACH = track("small_animal_stomach", new Item(properties("small_animal_stomach").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item RABBIT_HEART = track("rabbit_heart", new Item(properties("rabbit_heart").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_AQUATIC_MUSCLE = track("small_aquatic_muscle", new Item(properties("small_aquatic_muscle").stacksTo(16).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_FISH_MUSCLE = track("small_fish_muscle", new Item(properties("small_fish_muscle").stacksTo(16).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_SPRINGY_MUSCLE = track("small_springy_muscle", new Item(properties("small_springy_muscle").stacksTo(16).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_GILLS = track("small_gills", new Item(properties("small_gills").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_CARNIVORE_STOMACH = track("small_carnivore_stomach", new Item(properties("small_carnivore_stomach").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_CARNIVORE_INTESTINE = track("small_carnivore_intestine", new Item(properties("small_carnivore_intestine").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_HERBIVORE_STOMACH = track("small_herbivore_stomach", new Item(properties("small_herbivore_stomach").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item SMALL_HERBIVORE_INTESTINE = track("small_herbivore_intestine", new Item(properties("small_herbivore_intestine").stacksTo(1).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT.consumable())));

	public static final Item INSECT_HEART = track("insect_heart", new Item(properties("insect_heart").stacksTo(1).food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item INSECT_INTESTINE = track("insect_intestine", new Item(properties("insect_intestine").stacksTo(1).food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item INSECT_LUNG = track("insect_lung", new Item(properties("insect_lung").stacksTo(1).food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item INSECT_MUSCLE = track("insect_muscle", new Item(properties("insect_muscle").stacksTo(16).food(CCFoodComponents.INSECT_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.INSECT_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item INSECT_STOMACH = track("insect_stomach", new Item(properties("insect_stomach").stacksTo(1).food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item INSECT_CAECA = track("insect_caeca", new Item(properties("insect_caeca").stacksTo(1).food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item SILK_GLAND = track("silk_gland", new Item(properties("silk_gland").stacksTo(1).food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final VenomGland VENOM_GLAND = track("venom_gland", new VenomGland(properties("venom_gland")));//.setOrganQuality(CCOrganScores.VENOMOUS,1f);

	public static final Item ENDER_APPENDIX = track("ender_appendix", new Item(properties("ender_appendix").stacksTo(1).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ENDER_HEART = track("ender_heart", new Item(properties("ender_heart").stacksTo(1).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ENDER_INTESTINE = track("ender_intestine", new Item(properties("ender_intestine").stacksTo(1).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ENDER_KIDNEY = track("ender_kidney", new Item(properties("ender_kidney").stacksTo(1).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ENDER_LIVER = track("ender_liver", new Item(properties("ender_liver").stacksTo(1).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ENDER_LUNG = track("ender_lung", new Item(properties("ender_lung").stacksTo(1).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ENDER_MUSCLE = track("ender_muscle", new Item(properties("ender_muscle").stacksTo(16).food(CCFoodComponents.ALIEN_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.ALIEN_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item ENDER_RIB = track("ender_rib", new Item(properties("ender_rib").stacksTo(4)));
	public static final Item ENDER_SPINE = track("ender_spine", new Item(properties("ender_spine").stacksTo(1)));
	public static final Item ENDER_SPLEEN = track("ender_spleen", new Item(properties("ender_spleen").stacksTo(1).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item ENDER_STOMACH = track("ender_stomach", new Item(properties("ender_stomach").stacksTo(1).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));

	public static final Item DRAGON_APPENDIX = track("dragon_appendix", new Item(properties("dragon_appendix").stacksTo(1).food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item DRAGON_HEART = track("dragon_heart", new Item(properties("dragon_heart").stacksTo(1).food(CCFoodComponents.DRAGON_HEART_FOOD_COMPONENT.food(), CCFoodComponents.DRAGON_HEART_FOOD_COMPONENT.consumable())));
	public static final Item DRAGON_KIDNEY = track("dragon_kidney", new Item(properties("dragon_kidney").stacksTo(1).food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item DRAGON_LIVER = track("dragon_liver", new Item(properties("dragon_liver").stacksTo(1).food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item DRAGON_LUNG = track("dragon_lung", new Item(properties("dragon_lung").stacksTo(1).food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item DRAGON_MUSCLE = track("dragon_muscle", new Item(properties("dragon_muscle").stacksTo(16).food(CCFoodComponents.DRAGON_MUSCLE_FOOD_COMPONENT.food(), CCFoodComponents.DRAGON_MUSCLE_FOOD_COMPONENT.consumable())));
	public static final Item DRAGON_RIB = track("dragon_rib", new Item(properties("dragon_rib").stacksTo(4)));
	//TODO: Destructive Collisions
	public static final Item DRAGON_SPINE = track("dragon_spine", new Item(properties("dragon_spine").stacksTo(1)));
	public static final Item DRAGON_SPLEEN = track("dragon_spleen", new Item(properties("dragon_spleen").stacksTo(1).food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item MANA_REACTOR = track("mana_reactor", new Item(properties("mana_reactor").stacksTo(1).food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.consumable())));

	public static final Item ACTIVE_BLAZE_ROD = track("active_blaze_rod", new Item(properties("active_blaze_rod").stacksTo(3)));
	public static final Item BLAZE_SHELL = track("blaze_shell", new Item(properties("blaze_shell").stacksTo(4)));
	public static final Item BLAZE_CORE = track("blaze_core", new Item(properties("blaze_core").stacksTo(1)));

	public static final Item GAS_BLADDER = track("gas_bladder", new Item(properties("gas_bladder").stacksTo(1)));
	public static final Item VOLATILE_STOMACH = track("volatile_stomach", new Item(properties("volatile_stomach").stacksTo(1)));

	public static final Item GOLEM_CABLE = track("golem_cable", new Item(properties("golem_cable").stacksTo(1)));
	public static final Item GOLEM_PLATING = track("golem_plating", new Item(properties("golem_plating").stacksTo(4)));
	public static final Item GOLEM_CORE = track("golem_core", new Item(properties("golem_core").stacksTo(1)));
	public static final Item INNER_FURNACE = track("inner_furnace", new Item(properties("inner_furnace").stacksTo(1)));
	public static final Item PISTON_MUSCLE = track("piston_muscle", new Item(properties("piston_muscle").stacksTo(16)));
	public static final Item IRON_SCRAP = track("iron_scrap", new Item(properties("iron_scrap")));

	public static final Item SALTWATER_HEART = track("saltwater_heart", new Item(properties("saltwater_heart").stacksTo(1)));
	public static final Item SALTWATER_LUNG = track("saltwater_lung", new Item(properties("saltwater_lung").stacksTo(1)));
	public static final Item SALTWATER_MUSCLE = track("saltwater_muscle", new Item(properties("saltwater_muscle").stacksTo(16)));
	public static final Item CREEPER_APPENDIX = track("creeper_appendix", new CreeperAppendix(properties("creeper_appendix")));
	public static final Item SHIFTING_LEAVES = track("shifting_leaves", new Item(properties("shifting_leaves").stacksTo(16)));
	public static final Item SHULKER_SPLEEN = track("shulker_spleen", new Item(properties("shulker_spleen").stacksTo(1)));

	public static final Item SAUSAGE_SKIN = track("sausage_skin", new Item(properties("sausage_skin").stacksTo(64)));
	public static final Item MINI_SAUSAGE_SKIN = track("mini_sausage_skin", new Item(properties("mini_sausage_skin").stacksTo(64)));

	public static final Item BURNT_MEAT_CHUNK = track("burnt_meat_chunk", new Item(properties("burnt_meat_chunk").food(CCFoodComponents.BURNT_MEAT_CHUNK_COMPONENT.food(), CCFoodComponents.BURNT_MEAT_CHUNK_COMPONENT.consumable())));
	public static final Item RAW_ORGAN_MEAT = track("raw_organ_meat", new Item(properties("raw_organ_meat").food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_ORGAN_MEAT = track("cooked_organ_meat", new Item(properties("cooked_organ_meat").food(CCFoodComponents.COOKED_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item RAW_BUTCHERED_MEAT = track("raw_butchered_meat", new Item(properties("raw_butchered_meat").food(CCFoodComponents.RAW_BUTCHERED_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_BUTCHERED_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_BUTCHERED_MEAT = track("cooked_butchered_meat", new Item(properties("cooked_butchered_meat").food(CCFoodComponents.COOKED_BUTCHERED_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_BUTCHERED_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item RAW_SAUSAGE = track("raw_sausage", new Item(properties("raw_sausage").food(CCFoodComponents.RAW_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.RAW_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_SAUSAGE = track("sausage", new Item(properties("sausage").food(CCFoodComponents.COOKED_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item RAW_RICH_SAUSAGE = track("raw_rich_sausage", new Item(properties("raw_rich_sausage").food(CCFoodComponents.RAW_RICH_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.RAW_RICH_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_RICH_SAUSAGE = track("rich_sausage", new Item(properties("rich_sausage").food(CCFoodComponents.COOKED_RICH_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_RICH_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item RAW_MINI_SAUSAGE = track("raw_mini_sausage", new Item(properties("raw_mini_sausage").food(CCFoodComponents.RAW_MINI_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.RAW_MINI_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_MINI_SAUSAGE = track("mini_sausage", new Item(properties("mini_sausage").food(CCFoodComponents.COOKED_MINI_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_MINI_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item RAW_RICH_MINI_SAUSAGE = track("raw_rich_mini_sausage", new Item(properties("raw_rich_mini_sausage").food(CCFoodComponents.RAW_RICH_MINI_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.RAW_RICH_MINI_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_RICH_MINI_SAUSAGE = track("rich_mini_sausage", new Item(properties("rich_mini_sausage").food(CCFoodComponents.COOKED_RICH_MINI_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_RICH_MINI_SAUSAGE_FOOD_COMPONENT.consumable())));

	public static final Item ROTTEN_SAUSAGE = track("rotten_sausage", new Item(properties("rotten_sausage").food(CCFoodComponents.ROTTEN_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.ROTTEN_SAUSAGE_FOOD_COMPONENT.consumable())));

	public static final Item RAW_TOXIC_ORGAN_MEAT = track("raw_toxic_organ_meat", new Item(properties("raw_toxic_organ_meat").food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_TOXIC_ORGAN_MEAT = track("cooked_toxic_organ_meat", new Item(properties("cooked_toxic_organ_meat").food(CCFoodComponents.COOKED_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item RAW_TOXIC_MEAT = track("raw_toxic_meat", new Item(properties("raw_toxic_meat").food(CCFoodComponents.RAW_TOXIC_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_TOXIC_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_TOXIC_MEAT = track("cooked_toxic_meat", new Item(properties("cooked_toxic_meat").food(CCFoodComponents.COOKED_TOXIC_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_TOXIC_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item RAW_TOXIC_SAUSAGE = track("raw_toxic_sausage", new Item(properties("raw_toxic_sausage").food(CCFoodComponents.RAW_TOXIC_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.RAW_TOXIC_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_TOXIC_SAUSAGE = track("toxic_sausage", new Item(properties("toxic_sausage").food(CCFoodComponents.COOKED_TOXIC_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_TOXIC_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item RAW_RICH_TOXIC_SAUSAGE = track("raw_rich_toxic_sausage", new Item(properties("raw_rich_toxic_sausage").food(CCFoodComponents.RAW_RICH_TOXIC_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.RAW_RICH_TOXIC_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_RICH_TOXIC_SAUSAGE = track("rich_toxic_sausage", new Item(properties("rich_toxic_sausage").food(CCFoodComponents.COOKED_RICH_TOXIC_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_RICH_TOXIC_SAUSAGE_FOOD_COMPONENT.consumable())));
	
	public static final Item RAW_HUMAN_ORGAN_MEAT = track("raw_human_organ_meat", new Item(properties("raw_human_organ_meat").food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_HUMAN_ORGAN_MEAT = track("cooked_human_organ_meat", new Item(properties("cooked_human_organ_meat").food(CCFoodComponents.COOKED_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_HUMAN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item RAW_MAN_MEAT = track("raw_man_meat", new Item(properties("raw_man_meat").food(CCFoodComponents.RAW_MAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_MAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_MAN_MEAT = track("cooked_man_meat", new Item(properties("cooked_man_meat").food(CCFoodComponents.COOKED_MAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_MAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item RAW_HUMAN_SAUSAGE = track("raw_human_sausage", new Item(properties("raw_human_sausage").food(CCFoodComponents.RAW_HUMAN_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.RAW_HUMAN_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_HUMAN_SAUSAGE = track("human_sausage", new Item(properties("human_sausage").food(CCFoodComponents.COOKED_HUMAN_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_HUMAN_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item RAW_RICH_HUMAN_SAUSAGE = track("raw_rich_human_sausage", new Item(properties("raw_rich_human_sausage").food(CCFoodComponents.RAW_RICH_HUMAN_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.RAW_RICH_HUMAN_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_RICH_HUMAN_SAUSAGE = track("rich_human_sausage", new Item(properties("rich_human_sausage").food(CCFoodComponents.COOKED_RICH_HUMAN_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_RICH_HUMAN_SAUSAGE_FOOD_COMPONENT.consumable())));

	public static final Item RAW_ALIEN_ORGAN_MEAT = track("raw_alien_organ_meat", new Item(properties("raw_alien_organ_meat").food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_ALIEN_ORGAN_MEAT = track("cooked_alien_organ_meat", new Item(properties("cooked_alien_organ_meat").food(CCFoodComponents.COOKED_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_ALIEN_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item RAW_ALIEN_MEAT = track("raw_alien_meat", new Item(properties("raw_alien_meat").food(CCFoodComponents.RAW_ALIEN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ALIEN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_ALIEN_MEAT = track("cooked_alien_meat", new Item(properties("cooked_alien_meat").food(CCFoodComponents.COOKED_ALIEN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_ALIEN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item RAW_ALIEN_SAUSAGE = track("raw_alien_sausage", new Item(properties("raw_alien_sausage").food(CCFoodComponents.RAW_ALIEN_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.RAW_ALIEN_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_ALIEN_SAUSAGE = track("alien_sausage", new Item(properties("alien_sausage").food(CCFoodComponents.COOKED_ALIEN_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_ALIEN_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item RAW_RICH_ALIEN_SAUSAGE = track("raw_rich_alien_sausage", new Item(properties("raw_rich_alien_sausage").food(CCFoodComponents.RAW_RICH_ALIEN_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.RAW_RICH_ALIEN_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_RICH_ALIEN_SAUSAGE = track("rich_alien_sausage", new Item(properties("rich_alien_sausage").food(CCFoodComponents.COOKED_RICH_ALIEN_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_RICH_ALIEN_SAUSAGE_FOOD_COMPONENT.consumable())));

	public static final Item RAW_DRAGON_ORGAN_MEAT = track("raw_dragon_organ_meat", new Item(properties("raw_dragon_organ_meat").food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_DRAGON_ORGAN_MEAT = track("cooked_dragon_organ_meat", new Item(properties("cooked_dragon_organ_meat").food(CCFoodComponents.COOKED_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_DRAGON_ORGAN_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item RAW_DRAGON_MEAT = track("raw_dragon_meat", new Item(properties("raw_dragon_meat").food(CCFoodComponents.RAW_DRAGON_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_DRAGON_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_DRAGON_MEAT = track("cooked_dragon_meat", new Item(properties("cooked_dragon_meat").food(CCFoodComponents.COOKED_DRAGON_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_DRAGON_MEAT_FOOD_COMPONENT.consumable())));
	public static final Item RAW_DRAGON_SAUSAGE = track("raw_dragon_sausage", new Item(properties("raw_dragon_sausage").food(CCFoodComponents.RAW_DRAGON_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.RAW_DRAGON_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_DRAGON_SAUSAGE = track("dragon_sausage", new Item(properties("dragon_sausage").food(CCFoodComponents.COOKED_DRAGON_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_DRAGON_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item RAW_RICH_DRAGON_SAUSAGE = track("raw_rich_dragon_sausage", new Item(properties("raw_rich_dragon_sausage").food(CCFoodComponents.RAW_RICH_DRAGON_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.RAW_RICH_DRAGON_SAUSAGE_FOOD_COMPONENT.consumable())));
	public static final Item COOKED_RICH_DRAGON_SAUSAGE = track("rich_dragon_sausage", new Item(properties("rich_dragon_sausage").food(CCFoodComponents.COOKED_RICH_DRAGON_SAUSAGE_FOOD_COMPONENT.food(), CCFoodComponents.COOKED_RICH_DRAGON_SAUSAGE_FOOD_COMPONENT.consumable())));

	public static final Item CUD = track("cud", new Item(properties("cud").food(CCFoodComponents.CUD_FOOD_COMPONENT.food(), CCFoodComponents.CUD_FOOD_COMPONENT.consumable())));
	public static final Item FURNACE_POWER = track("furnace_power", new Item(properties("furnace_power").food(CCFoodComponents.FURNACE_POWER_FOOD_COMPONENT.food(), CCFoodComponents.FURNACE_POWER_FOOD_COMPONENT.consumable())));

    private static Item.Properties properties(String name) {
        return new Item.Properties().setId(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, net.minecraft.resources.Identifier.fromNamespaceAndPath("chestcavity",name)));
    }
    private static <T extends Item> T track(String name,T item) { ALL.put(name,item); return item; }
    public static void register(net.minecraftforge.registries.RegisterEvent event) {
        event.register(net.minecraft.core.registries.Registries.ITEM, helper -> ALL.forEach((name,item) -> helper.register(net.minecraft.resources.Identifier.fromNamespaceAndPath("chestcavity",name),item)));
    }
}
