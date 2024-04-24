package net.tigereye.chestcavity.registration;

import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.items.ChestOpener;
import net.tigereye.chestcavity.items.CreeperAppendix;
import net.tigereye.chestcavity.items.VenomGland;

public class CCItems {


	public static final Item.Settings CHEST_OPENER_SETTINGS = new Item.Settings().maxCount(1).group(ItemGroup.TOOLS);
	public static final Item.Settings FOOD_ITEM_SETTINGS = new Item.Settings().maxCount(64).group(ItemGroup.FOOD);
	public static final Item.Settings INVISIBLE_FOOD_SETTINGS = new Item.Settings().maxCount(64);

	public static final Item CHEST_OPENER = new ChestOpener();
	public static final SwordItem WOODEN_CLEAVER = new SwordItem(ToolMaterials.WOOD,6,-3.2f,new Item.Settings().group(ItemGroup.COMBAT));
	public static final SwordItem GOLD_CLEAVER = new SwordItem(ToolMaterials.GOLD,6,-3.0f,new Item.Settings().group(ItemGroup.COMBAT));
	public static final SwordItem STONE_CLEAVER = new SwordItem(ToolMaterials.STONE,7,-3.2f,new Item.Settings().group(ItemGroup.COMBAT));
	public static final SwordItem IRON_CLEAVER = new SwordItem(ToolMaterials.IRON,6,-3.1f,new Item.Settings().group(ItemGroup.COMBAT));
	public static final SwordItem DIAMOND_CLEAVER = new SwordItem(ToolMaterials.DIAMOND,5,-3.0f,new Item.Settings().group(ItemGroup.COMBAT));
	public static final SwordItem NETHERITE_CLEAVER = new SwordItem(ToolMaterials.NETHERITE,5,-3.0f,new Item.Settings().group(ItemGroup.COMBAT).fireproof());

	public static final Item HUMAN_APPENDIX = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item HUMAN_HEART = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item HUMAN_INTESTINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item HUMAN_KIDNEY = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item HUMAN_LIVER = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item HUMAN_LUNG = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item HUMAN_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.HUMAN_MUSCLE_FOOD_COMPONENT));
	public static final Item HUMAN_RIB = new Item(new Item.Settings().maxCount(4).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item HUMAN_SPINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item HUMAN_SPLEEN = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item HUMAN_STOMACH = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT));

	public static final Item ROTTEN_APPENDIX = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(FoodComponents.ROTTEN_FLESH));
	public static final Item ROTTEN_HEART = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(FoodComponents.ROTTEN_FLESH));
	public static final Item ROTTEN_INTESTINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(FoodComponents.ROTTEN_FLESH));
	public static final Item ROTTEN_KIDNEY = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(FoodComponents.ROTTEN_FLESH));
	public static final Item ROTTEN_LIVER = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(FoodComponents.ROTTEN_FLESH));
	public static final Item ROTTEN_LUNG = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(FoodComponents.ROTTEN_FLESH));
	public static final Item ROTTEN_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.ROTTEN_MUSCLE_FOOD_COMPONENT));
	public static final Item ROTTEN_RIB = new Item(new Item.Settings().maxCount(4).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item ROTTEN_SPINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item ROTTEN_SPLEEN = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(FoodComponents.ROTTEN_FLESH));
	public static final Item ROTTEN_STOMACH = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(FoodComponents.ROTTEN_FLESH));
	public static final Item WITHERED_RIB = new Item(new Item.Settings().maxCount(4).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item WITHERED_SPINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item WRITHING_SOULSAND = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP));

	public static final Item ANIMAL_APPENDIX = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ANIMAL_HEART = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ANIMAL_INTESTINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ANIMAL_KIDNEY = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ANIMAL_LIVER = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ANIMAL_LUNG = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ANIMAL_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item ANIMAL_RIB = new Item(new Item.Settings().maxCount(4).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item ANIMAL_SPINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item ANIMAL_SPLEEN = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ANIMAL_STOMACH = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item AQUATIC_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item FISH_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item GILLS = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item LLAMA_LUNG = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item CARNIVORE_STOMACH = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item CARNIVORE_INTESTINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item HERBIVORE_RUMEN = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item HERBIVORE_STOMACH = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item HERBIVORE_INTESTINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item BRUTISH_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SWIFT_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SPRINGY_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT));

	public static final Item FIREPROOF_APPENDIX = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item FIREPROOF_HEART = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item FIREPROOF_INTESTINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item FIREPROOF_KIDNEY = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item FIREPROOF_LIVER = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item FIREPROOF_LUNG = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item FIREPROOF_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item FIREPROOF_RIB = new Item(new Item.Settings().maxCount(4).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item FIREPROOF_SPINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item FIREPROOF_SPLEEN = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item FIREPROOF_STOMACH = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	
	public static final Item SMALL_ANIMAL_APPENDIX = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_ANIMAL_HEART = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_ANIMAL_INTESTINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_ANIMAL_KIDNEY = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_ANIMAL_LIVER = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_ANIMAL_LUNG = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_ANIMAL_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_ANIMAL_RIB = new Item(new Item.Settings().maxCount(4).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item SMALL_ANIMAL_SPINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item SMALL_ANIMAL_SPLEEN = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_ANIMAL_STOMACH = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item RABBIT_HEART = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_AQUATIC_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_FISH_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_SPRINGY_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_GILLS = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_CARNIVORE_STOMACH = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_CARNIVORE_INTESTINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_HERBIVORE_STOMACH = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));
	public static final Item SMALL_HERBIVORE_INTESTINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT));

	public static final Item INSECT_HEART = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item INSECT_INTESTINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item INSECT_LUNG = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item INSECT_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.INSECT_MUSCLE_FOOD_COMPONENT));
	public static final Item INSECT_STOMACH = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item INSECT_CAECA = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item SILK_GLAND = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT));
	public static final VenomGland VENOM_GLAND = new VenomGland();//.setOrganQuality(CCOrganScores.VENOMOUS,1f);

	public static final Item ENDER_APPENDIX = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ENDER_HEART = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ENDER_INTESTINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ENDER_KIDNEY = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ENDER_LIVER = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ENDER_LUNG = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ENDER_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.ALIEN_MUSCLE_FOOD_COMPONENT));
	public static final Item ENDER_RIB = new Item(new Item.Settings().maxCount(4).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item ENDER_SPINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item ENDER_SPLEEN = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item ENDER_STOMACH = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT));

	public static final Item DRAGON_APPENDIX = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item DRAGON_HEART = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.DRAGON_HEART_FOOD_COMPONENT));
	public static final Item DRAGON_KIDNEY = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item DRAGON_LIVER = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item DRAGON_LUNG = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item DRAGON_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.DRAGON_MUSCLE_FOOD_COMPONENT));
	public static final Item DRAGON_RIB = new Item(new Item.Settings().maxCount(4).group(ChestCavity.ORGAN_ITEM_GROUP));
	//TODO: Destructive Collisions
	public static final Item DRAGON_SPINE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item DRAGON_SPLEEN = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item MANA_REACTOR = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP).food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT));

	public static final Item ACTIVE_BLAZE_ROD = new Item(new Item.Settings().maxCount(3).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item BLAZE_SHELL = new Item(new Item.Settings().maxCount(4).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item BLAZE_CORE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));

	public static final Item GAS_BLADDER = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item VOLATILE_STOMACH = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));

	public static final Item GOLEM_CABLE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item GOLEM_PLATING = new Item(new Item.Settings().maxCount(4).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item GOLEM_CORE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item INNER_FURNACE = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item PISTON_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item IRON_SCRAP = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

	public static final Item SALTWATER_HEART = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item SALTWATER_LUNG = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item SALTWATER_MUSCLE = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item CREEPER_APPENDIX = new CreeperAppendix();
	public static final Item SHIFTING_LEAVES = new Item(new Item.Settings().maxCount(16).group(ChestCavity.ORGAN_ITEM_GROUP));
	public static final Item SHULKER_SPLEEN = new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));

	public static final Item SAUSAGE_SKIN = new Item(new Item.Settings().maxCount(64).group(ItemGroup.MISC));
	public static final Item MINI_SAUSAGE_SKIN = new Item(new Item.Settings().maxCount(64));

	public static final Item BURNT_MEAT_CHUNK = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.BURNT_MEAT_CHUNK_COMPONENT));
	public static final Item RAW_ORGAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item COOKED_ORGAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item RAW_BUTCHERED_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_BUTCHERED_MEAT_FOOD_COMPONENT));
	public static final Item COOKED_BUTCHERED_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_BUTCHERED_MEAT_FOOD_COMPONENT));
	public static final Item RAW_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_SAUSAGE_FOOD_COMPONENT));
	public static final Item RAW_RICH_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_RICH_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_RICH_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_RICH_SAUSAGE_FOOD_COMPONENT));
	public static final Item RAW_MINI_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_MINI_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_MINI_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_MINI_SAUSAGE_FOOD_COMPONENT));
	public static final Item RAW_RICH_MINI_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_RICH_MINI_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_RICH_MINI_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_RICH_MINI_SAUSAGE_FOOD_COMPONENT));

	public static final Item ROTTEN_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.ROTTEN_SAUSAGE_FOOD_COMPONENT));

	public static final Item RAW_TOXIC_ORGAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item COOKED_TOXIC_ORGAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_TOXIC_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item RAW_TOXIC_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_TOXIC_MEAT_FOOD_COMPONENT));
	public static final Item COOKED_TOXIC_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_TOXIC_MEAT_FOOD_COMPONENT));
	public static final Item RAW_TOXIC_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_TOXIC_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_TOXIC_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_TOXIC_SAUSAGE_FOOD_COMPONENT));
	public static final Item RAW_RICH_TOXIC_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_RICH_TOXIC_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_RICH_TOXIC_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_RICH_TOXIC_SAUSAGE_FOOD_COMPONENT));
	
	public static final Item RAW_HUMAN_ORGAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item COOKED_HUMAN_ORGAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_HUMAN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item RAW_MAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_MAN_MEAT_FOOD_COMPONENT));
	public static final Item COOKED_MAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_MAN_MEAT_FOOD_COMPONENT));
	public static final Item RAW_HUMAN_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_HUMAN_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_HUMAN_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_HUMAN_SAUSAGE_FOOD_COMPONENT));
	public static final Item RAW_RICH_HUMAN_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_RICH_HUMAN_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_RICH_HUMAN_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_RICH_HUMAN_SAUSAGE_FOOD_COMPONENT));

	public static final Item RAW_ALIEN_ORGAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item COOKED_ALIEN_ORGAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_ALIEN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item RAW_ALIEN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_ALIEN_MEAT_FOOD_COMPONENT));
	public static final Item COOKED_ALIEN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_ALIEN_MEAT_FOOD_COMPONENT));
	public static final Item RAW_ALIEN_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_ALIEN_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_ALIEN_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_ALIEN_SAUSAGE_FOOD_COMPONENT));
	public static final Item RAW_RICH_ALIEN_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_RICH_ALIEN_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_RICH_ALIEN_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_RICH_ALIEN_SAUSAGE_FOOD_COMPONENT));

	public static final Item RAW_DRAGON_ORGAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item COOKED_DRAGON_ORGAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_DRAGON_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item RAW_DRAGON_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_DRAGON_MEAT_FOOD_COMPONENT));
	public static final Item COOKED_DRAGON_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_DRAGON_MEAT_FOOD_COMPONENT));
	public static final Item RAW_DRAGON_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_DRAGON_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_DRAGON_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_DRAGON_SAUSAGE_FOOD_COMPONENT));
	public static final Item RAW_RICH_DRAGON_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_RICH_DRAGON_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_RICH_DRAGON_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_RICH_DRAGON_SAUSAGE_FOOD_COMPONENT));

	public static final Item CUD = new Item(INVISIBLE_FOOD_SETTINGS.food(CCFoodComponents.CUD_FOOD_COMPONENT));
	public static final Item FURNACE_POWER = new Item(INVISIBLE_FOOD_SETTINGS.food(CCFoodComponents.FURNACE_POWER_FOOD_COMPONENT));

	public static void register() {
		registerItem("chest_opener", CHEST_OPENER);
		registerItem("wooden_cleaver", WOODEN_CLEAVER);
		registerItem("stone_cleaver", STONE_CLEAVER);
		registerItem("gold_cleaver", GOLD_CLEAVER);
		registerItem("iron_cleaver", IRON_CLEAVER);
		registerItem("diamond_cleaver", DIAMOND_CLEAVER);
		registerItem("netherite_cleaver", NETHERITE_CLEAVER);

		registerItem("appendix", HUMAN_APPENDIX);
		registerItem("heart", HUMAN_HEART);
		registerItem("intestine", HUMAN_INTESTINE);
		registerItem("kidney", HUMAN_KIDNEY);
		registerItem("liver", HUMAN_LIVER);
		registerItem("lung", HUMAN_LUNG);
		registerItem("muscle", HUMAN_MUSCLE);
		registerItem("rib", HUMAN_RIB);
		registerItem("spine", HUMAN_SPINE);
		registerItem("spleen", HUMAN_SPLEEN);
		registerItem("stomach", HUMAN_STOMACH);
		
		registerItem("rotten_appendix", ROTTEN_APPENDIX);
		registerItem("rotten_heart", ROTTEN_HEART);
		registerItem("rotten_intestine", ROTTEN_INTESTINE);
		registerItem("rotten_kidney", ROTTEN_KIDNEY);
		registerItem("rotten_liver", ROTTEN_LIVER);
		registerItem("rotten_lung", ROTTEN_LUNG);
		registerItem("rotten_muscle", ROTTEN_MUSCLE);
		registerItem("rotten_rib", ROTTEN_RIB);
		registerItem("rotten_spine", ROTTEN_SPINE);
		registerItem("rotten_spleen", ROTTEN_SPLEEN);
		registerItem("rotten_stomach", ROTTEN_STOMACH);
		registerItem("withered_rib", WITHERED_RIB);
		registerItem("withered_spine", WITHERED_SPINE);
		registerItem("writhing_soulsand", WRITHING_SOULSAND);

		registerItem("animal_appendix", ANIMAL_APPENDIX);
		registerItem("animal_heart", ANIMAL_HEART);
		registerItem("animal_intestine", ANIMAL_INTESTINE);
		registerItem("animal_kidney", ANIMAL_KIDNEY);
		registerItem("animal_liver", ANIMAL_LIVER);
		registerItem("animal_lung", ANIMAL_LUNG);
		registerItem("animal_muscle", ANIMAL_MUSCLE);
		registerItem("animal_rib", ANIMAL_RIB);
		registerItem("animal_spine", ANIMAL_SPINE);
		registerItem("animal_spleen", ANIMAL_SPLEEN);
		registerItem("animal_stomach", ANIMAL_STOMACH);
		registerItem("aquatic_muscle", AQUATIC_MUSCLE);
		registerItem("fish_muscle", FISH_MUSCLE);
		registerItem("gills", GILLS);
		registerItem("llama_lung", LLAMA_LUNG);
		registerItem("carnivore_stomach",CARNIVORE_STOMACH);
		registerItem("carnivore_intestine",CARNIVORE_INTESTINE);
		registerItem("herbivore_rumen",HERBIVORE_RUMEN);
		registerItem("herbivore_stomach",HERBIVORE_STOMACH);
		registerItem("herbivore_intestine",HERBIVORE_INTESTINE);
		registerItem("brutish_muscle", BRUTISH_MUSCLE);
		registerItem("swift_muscle", SWIFT_MUSCLE);
		registerItem("springy_muscle", SPRINGY_MUSCLE);

		registerItem("fireproof_appendix", FIREPROOF_APPENDIX);
		registerItem("fireproof_heart", FIREPROOF_HEART);
		registerItem("fireproof_intestine", FIREPROOF_INTESTINE);
		registerItem("fireproof_kidney", FIREPROOF_KIDNEY);
		registerItem("fireproof_liver", FIREPROOF_LIVER);
		registerItem("fireproof_lung", FIREPROOF_LUNG);
		registerItem("fireproof_muscle", FIREPROOF_MUSCLE);
		registerItem("fireproof_rib", FIREPROOF_RIB);
		registerItem("fireproof_spine", FIREPROOF_SPINE);
		registerItem("fireproof_spleen", FIREPROOF_SPLEEN);
		registerItem("fireproof_stomach", FIREPROOF_STOMACH);
		
		registerItem("small_animal_appendix", SMALL_ANIMAL_APPENDIX);
		registerItem("small_animal_heart", SMALL_ANIMAL_HEART);
		registerItem("small_animal_intestine", SMALL_ANIMAL_INTESTINE);
		registerItem("small_animal_kidney", SMALL_ANIMAL_KIDNEY);
		registerItem("small_animal_liver", SMALL_ANIMAL_LIVER);
		registerItem("small_animal_lung", SMALL_ANIMAL_LUNG);
		registerItem("small_animal_muscle", SMALL_ANIMAL_MUSCLE);
		registerItem("small_animal_rib", SMALL_ANIMAL_RIB);
		registerItem("small_animal_spine", SMALL_ANIMAL_SPINE);
		registerItem("small_animal_spleen", SMALL_ANIMAL_SPLEEN);
		registerItem("small_animal_stomach", SMALL_ANIMAL_STOMACH);
		registerItem("rabbit_heart", RABBIT_HEART);
		registerItem("small_aquatic_muscle", SMALL_AQUATIC_MUSCLE);
		registerItem("small_fish_muscle", SMALL_FISH_MUSCLE);
		registerItem("small_springy_muscle", SMALL_SPRINGY_MUSCLE);
		registerItem("small_gills", SMALL_GILLS);
		registerItem("small_carnivore_stomach",SMALL_CARNIVORE_STOMACH);
		registerItem("small_carnivore_intestine",SMALL_CARNIVORE_INTESTINE);
		registerItem("small_herbivore_stomach",SMALL_HERBIVORE_STOMACH);
		registerItem("small_herbivore_intestine",SMALL_HERBIVORE_INTESTINE);

		registerItem("insect_heart", INSECT_HEART);
		registerItem("insect_intestine", INSECT_INTESTINE);
		registerItem("insect_lung", INSECT_LUNG);
		registerItem("insect_muscle", INSECT_MUSCLE);
		registerItem("insect_stomach", INSECT_STOMACH);
		registerItem("insect_caeca", INSECT_CAECA);
		registerItem("silk_gland", SILK_GLAND);
		registerItem("venom_gland", VENOM_GLAND);

		registerItem("ender_appendix", ENDER_APPENDIX);
		registerItem("ender_heart", ENDER_HEART);
		registerItem("ender_intestine", ENDER_INTESTINE);
		registerItem("ender_kidney", ENDER_KIDNEY);
		registerItem("ender_liver", ENDER_LIVER);
		registerItem("ender_lung", ENDER_LUNG);
		registerItem("ender_muscle", ENDER_MUSCLE);
		registerItem("ender_rib", ENDER_RIB);
		registerItem("ender_spine", ENDER_SPINE);
		registerItem("ender_spleen", ENDER_SPLEEN);
		registerItem("ender_stomach", ENDER_STOMACH);

		registerItem("dragon_appendix", DRAGON_APPENDIX);
		registerItem("dragon_heart", DRAGON_HEART);
		registerItem("dragon_kidney", DRAGON_KIDNEY);
		registerItem("dragon_liver", DRAGON_LIVER);
		registerItem("dragon_lung", DRAGON_LUNG);
		registerItem("dragon_muscle", DRAGON_MUSCLE);
		registerItem("dragon_rib", DRAGON_RIB);
		registerItem("dragon_spine", DRAGON_SPINE);
		registerItem("dragon_spleen", DRAGON_SPLEEN);
		registerItem("mana_reactor", MANA_REACTOR);

		registerItem("active_blaze_rod", ACTIVE_BLAZE_ROD);
		registerItem("blaze_shell", BLAZE_SHELL);
		registerItem("blaze_core", BLAZE_CORE);

		registerItem("gas_bladder", GAS_BLADDER);
		registerItem("volatile_stomach", VOLATILE_STOMACH);

		registerItem("golem_cable", GOLEM_CABLE);
		registerItem("golem_plating", GOLEM_PLATING);
		registerItem("golem_core", GOLEM_CORE);
		registerItem("inner_furnace", INNER_FURNACE);
		registerItem("piston_muscle", PISTON_MUSCLE);
		registerItem("iron_scrap", IRON_SCRAP);

		registerItem("saltwater_heart", SALTWATER_HEART);
		registerItem("saltwater_lung", SALTWATER_LUNG);
		registerItem("saltwater_muscle", SALTWATER_MUSCLE);
		registerItem("creeper_appendix", CREEPER_APPENDIX);
		registerItem("shifting_leaves", SHIFTING_LEAVES);
		registerItem("shulker_spleen", SHULKER_SPLEEN);

		registerItem("sausage_skin", SAUSAGE_SKIN);
		registerItem("mini_sausage_skin", MINI_SAUSAGE_SKIN);

		registerItem("burnt_meat_chunk", BURNT_MEAT_CHUNK);
		registerItem("raw_organ_meat", RAW_ORGAN_MEAT);
		registerItem("cooked_organ_meat", COOKED_ORGAN_MEAT);
		registerItem("raw_butchered_meat", RAW_BUTCHERED_MEAT);
		registerItem("cooked_butchered_meat", COOKED_BUTCHERED_MEAT);
		registerItem("raw_sausage", RAW_SAUSAGE);
		registerItem("sausage", COOKED_SAUSAGE);
		registerItem("raw_rich_sausage", RAW_RICH_SAUSAGE);
		registerItem("rich_sausage", COOKED_RICH_SAUSAGE);
		registerItem("raw_mini_sausage", RAW_MINI_SAUSAGE);
		registerItem("mini_sausage", COOKED_MINI_SAUSAGE);
		registerItem("raw_rich_mini_sausage", RAW_RICH_MINI_SAUSAGE);
		registerItem("rich_mini_sausage", COOKED_RICH_MINI_SAUSAGE);

		registerItem("rotten_sausage", ROTTEN_SAUSAGE);

		registerItem("raw_toxic_organ_meat", RAW_TOXIC_ORGAN_MEAT);
		registerItem("cooked_toxic_organ_meat", COOKED_TOXIC_ORGAN_MEAT);
		registerItem("raw_toxic_meat", RAW_TOXIC_MEAT);
		registerItem("cooked_toxic_meat", COOKED_TOXIC_MEAT);
		registerItem("raw_toxic_sausage", RAW_TOXIC_SAUSAGE);
		registerItem("toxic_sausage", COOKED_TOXIC_SAUSAGE);
		registerItem("raw_rich_toxic_sausage", RAW_RICH_TOXIC_SAUSAGE);
		registerItem("rich_toxic_sausage", COOKED_RICH_TOXIC_SAUSAGE);
		
		registerItem("raw_human_organ_meat", RAW_HUMAN_ORGAN_MEAT);
		registerItem("cooked_human_organ_meat", COOKED_HUMAN_ORGAN_MEAT);
		registerItem("raw_man_meat", RAW_MAN_MEAT);
		registerItem("cooked_man_meat", COOKED_MAN_MEAT);
		registerItem("raw_human_sausage", RAW_HUMAN_SAUSAGE);
		registerItem("human_sausage", COOKED_HUMAN_SAUSAGE);
		registerItem("raw_rich_human_sausage", RAW_RICH_HUMAN_SAUSAGE);
		registerItem("rich_human_sausage", COOKED_RICH_HUMAN_SAUSAGE);

		registerItem("raw_alien_organ_meat", RAW_ALIEN_ORGAN_MEAT);
		registerItem("cooked_alien_organ_meat", COOKED_ALIEN_ORGAN_MEAT);
		registerItem("raw_alien_meat", RAW_ALIEN_MEAT);
		registerItem("cooked_alien_meat", COOKED_ALIEN_MEAT);
		registerItem("raw_alien_sausage", RAW_ALIEN_SAUSAGE);
		registerItem("alien_sausage", COOKED_ALIEN_SAUSAGE);
		registerItem("raw_rich_alien_sausage", RAW_RICH_ALIEN_SAUSAGE);
		registerItem("rich_alien_sausage", COOKED_RICH_ALIEN_SAUSAGE);

		registerItem("raw_dragon_organ_meat", RAW_DRAGON_ORGAN_MEAT);
		registerItem("cooked_dragon_organ_meat", COOKED_DRAGON_ORGAN_MEAT);
		registerItem("raw_dragon_meat", RAW_DRAGON_MEAT);
		registerItem("cooked_dragon_meat", COOKED_DRAGON_MEAT);
		registerItem("raw_dragon_sausage", RAW_DRAGON_SAUSAGE);
		registerItem("dragon_sausage", COOKED_DRAGON_SAUSAGE);
		registerItem("raw_rich_dragon_sausage", RAW_RICH_DRAGON_SAUSAGE);
		registerItem("rich_dragon_sausage", COOKED_RICH_DRAGON_SAUSAGE);

		registerItem("cud",CUD);
		registerItem("furnace_power",FURNACE_POWER);
	}
	
	private static void registerItem(String name, Item item) {
		Registry.register(Registry.ITEM, ChestCavity.MODID + ":" + name, item);
    }
}
