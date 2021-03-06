package net.tigereye.chestcavity.registration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.items.*;

public class CCItems {


	public static final Item.Settings CHEST_OPENER_SETTINGS = new Item.Settings().maxCount(1).group(ItemGroup.TOOLS);
	public static final Item.Settings FOOD_ITEM_SETTINGS = new Item.Settings().maxCount(64).group(ItemGroup.FOOD);

	public static final Item CHEST_OPENER = new ChestOpener();

	public static final Organ HUMAN_APPENDIX = new Organ().setOrganQuality(CCOrganScores.LUCK,1f);
	public static final Organ HUMAN_HEART = new Organ().setOrganQuality(CCOrganScores.HEALTH,1f);
	public static final Organ HUMAN_INTESTINE = new Organ().setOrganQuality(CCOrganScores.NUTRITION,1f);
	public static final Organ HUMAN_KIDNEY = new Organ().setOrganQuality(CCOrganScores.FILTRATION,1f);
	public static final Organ HUMAN_LIVER = new Organ().setOrganQuality(CCOrganScores.DETOXIFICATION,1f);
	public static final Organ HUMAN_LUNG = new Organ().setOrganQuality(CCOrganScores.BREATH,1f).setOrganQuality(CCOrganScores.ENDURANCE,1f);
	public static final Organ HUMAN_MUSCLE = new Organ(16,CCFoodComponents.HUMAN_MUSCLE_FOOD_COMPONENT).setOrganQuality(CCOrganScores.STRENGTH,1f).setOrganQuality(CCOrganScores.SPEED,1f);
	public static final Organ HUMAN_RIB = new Organ(4).setOrganQuality(CCOrganScores.DEFENSE,1f);
	public static final Organ HUMAN_SPINE = new Organ().setOrganQuality(CCOrganScores.NERVOUS_SYSTEM,1f).setOrganQuality(CCOrganScores.DEFENSE,.75f);
	public static final Organ HUMAN_SPLEEN = new Organ().setOrganQuality(CCOrganScores.METABOLISM,1f);
	public static final Organ HUMAN_STOMACH = new Organ().setOrganQuality(CCOrganScores.DIGESTION,1f);

	public static final Organ ROTTEN_APPENDIX = new Organ().setOrganQuality(CCOrganScores.LUCK,.5f);
	public static final Organ ROTTEN_HEART = new Organ().setOrganQuality(CCOrganScores.HEALTH,.5f);
	public static final Organ ROTTEN_INTESTINE = new Organ().setOrganQuality(CCOrganScores.NUTRITION,.5f).setOrganQuality(CCOrganScores.ROTGUT,1f);
	public static final Organ ROTTEN_KIDNEY = new Organ().setOrganQuality(CCOrganScores.FILTRATION,.5f);
	public static final Organ ROTTEN_LIVER = new Organ().setOrganQuality(CCOrganScores.DETOXIFICATION,.5f);
	public static final Organ ROTTEN_LUNG = new Organ().setOrganQuality(CCOrganScores.BREATH,.5f).setOrganQuality(CCOrganScores.ENDURANCE,.5f);
	public static final Organ ROTTEN_RIB = new Organ(4).setOrganQuality(CCOrganScores.DEFENSE,.5f);
	public static final Organ ROTTEN_SPINE = new Organ().setOrganQuality(CCOrganScores.NERVOUS_SYSTEM,.5f).setOrganQuality(CCOrganScores.DEFENSE,.375f);
	public static final Organ ROTTEN_SPLEEN = new Organ().setOrganQuality(CCOrganScores.METABOLISM,.5f);
	public static final Organ ROTTEN_STOMACH = new Organ().setOrganQuality(CCOrganScores.DIGESTION,.5f).setOrganQuality(CCOrganScores.ROT_DIGESTION,1f);
	public static final Organ WITHERED_RIB = new Organ(4).setOrganQuality(CCOrganScores.DEFENSE,.5f).setOrganQuality(CCOrganScores.WITHERED,1);
	public static final Organ WITHERED_SPINE = new Organ().setOrganQuality(CCOrganScores.NERVOUS_SYSTEM,.5f).setOrganQuality(CCOrganScores.DEFENSE,.375f).setOrganQuality(CCOrganScores.WITHERED,1);
	public static final Organ WRITHING_SOULSAND = new Organ(16).setOrganQuality(CCOrganScores.STRENGTH,1.5f).setOrganQuality(CCOrganScores.SPEED,.5f);

	public static final Organ ANIMAL_APPENDIX = new Organ().setOrganQuality(CCOrganScores.LUCK,.75f);
	public static final Organ ANIMAL_HEART = new Organ().setOrganQuality(CCOrganScores.HEALTH,.75f);
	public static final Organ ANIMAL_INTESTINE = new Organ().setOrganQuality(CCOrganScores.NUTRITION,.75f);
	public static final Organ ANIMAL_KIDNEY = new Organ().setOrganQuality(CCOrganScores.FILTRATION,.75f);
	public static final Organ ANIMAL_LIVER = new Organ().setOrganQuality(CCOrganScores.DETOXIFICATION,.75f);
	public static final Organ ANIMAL_LUNG = new Organ().setOrganQuality(CCOrganScores.BREATH,.75f).setOrganQuality(CCOrganScores.ENDURANCE,.75f);
	public static final Organ ANIMAL_MUSCLE = new Organ(16,CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT).setOrganQuality(CCOrganScores.STRENGTH,.75f).setOrganQuality(CCOrganScores.SPEED,.75f);
	public static final Organ ANIMAL_RIB = new Organ(4).setOrganQuality(CCOrganScores.DEFENSE,.75f);
	public static final Organ ANIMAL_SPINE = new Organ().setOrganQuality(CCOrganScores.NERVOUS_SYSTEM,.75f).setOrganQuality(CCOrganScores.DEFENSE,.5625f);
	public static final Organ ANIMAL_SPLEEN = new Organ().setOrganQuality(CCOrganScores.METABOLISM,.75f);
	public static final Organ ANIMAL_STOMACH = new Organ().setOrganQuality(CCOrganScores.DIGESTION,.75f);
	public static final Organ AQUATIC_MUSCLE = new Organ(16,CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT).setOrganQuality(CCOrganScores.STRENGTH,1f).setOrganQuality(CCOrganScores.SPEED,.5f).setOrganQuality(CCOrganScores.SWIM_SPEED, 1f);
	public static final Organ FISH_MUSCLE = new Organ(16,CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT).setOrganQuality(CCOrganScores.STRENGTH,.75f).setOrganQuality(CCOrganScores.SPEED,.25f).setOrganQuality(CCOrganScores.SWIM_SPEED, .75f);
	public static final Organ GILLS = new Organ().setOrganQuality(CCOrganScores.WATERBREATH,1);
	public static final Organ LLAMA_LUNG = new Organ().setOrganQuality(CCOrganScores.BREATH,.75f).setOrganQuality(CCOrganScores.ENDURANCE,.75f).setOrganQuality(CCOrganScores.FORCEFUL_SPIT,1);
	public static final Organ CARNIVORE_STOMACH = new Organ().setOrganQuality(CCOrganScores.HERBIVOROUS_DIGESTION,.25f).setOrganQuality(CCOrganScores.CARNIVOROUS_DIGESTION,1.25f);
	public static final Organ CARNIVORE_INTESTINE = new Organ().setOrganQuality(CCOrganScores.HERBIVOROUS_NUTRITION,.25f).setOrganQuality(CCOrganScores.CARNIVOROUS_NUTRITION,1.25f);
	public static final Organ HERBIVORE_RUMEN = new Organ().setOrganQuality(CCOrganScores.HERBIVOROUS_DIGESTION,1f).setOrganQuality(CCOrganScores.CARNIVOROUS_DIGESTION,-.5f).setOrganQuality(CCOrganScores.GRAZING,1f);
	public static final Organ HERBIVORE_STOMACH = new Organ().setOrganQuality(CCOrganScores.HERBIVOROUS_DIGESTION,1.25f).setOrganQuality(CCOrganScores.CARNIVOROUS_DIGESTION,.25f);
	public static final Organ HERBIVORE_INTESTINE = new Organ().setOrganQuality(CCOrganScores.HERBIVOROUS_NUTRITION,1.25f).setOrganQuality(CCOrganScores.CARNIVOROUS_NUTRITION,.25f);

	public static final Organ SMALL_ANIMAL_APPENDIX = new Organ().setOrganQuality(CCOrganScores.LUCK,.5f);
	public static final Organ SMALL_ANIMAL_HEART = new Organ().setOrganQuality(CCOrganScores.HEALTH,.5f);
	public static final Organ SMALL_ANIMAL_INTESTINE = new Organ().setOrganQuality(CCOrganScores.NUTRITION,.5f);
	public static final Organ SMALL_ANIMAL_KIDNEY = new Organ().setOrganQuality(CCOrganScores.FILTRATION,.5f);
	public static final Organ SMALL_ANIMAL_LIVER = new Organ().setOrganQuality(CCOrganScores.DETOXIFICATION,.5f);
	public static final Organ SMALL_ANIMAL_LUNG = new Organ().setOrganQuality(CCOrganScores.BREATH,.5f).setOrganQuality(CCOrganScores.ENDURANCE,.5f);
	public static final Organ SMALL_ANIMAL_MUSCLE = new Organ(16,CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT).setOrganQuality(CCOrganScores.STRENGTH,.5f).setOrganQuality(CCOrganScores.SPEED,.5f);
	public static final Organ SMALL_ANIMAL_RIB = new Organ(4).setOrganQuality(CCOrganScores.DEFENSE,.5f);
	public static final Organ SMALL_ANIMAL_SPINE = new Organ().setOrganQuality(CCOrganScores.NERVOUS_SYSTEM,.5f).setOrganQuality(CCOrganScores.DEFENSE,.375f);
	public static final Organ SMALL_ANIMAL_SPLEEN = new Organ().setOrganQuality(CCOrganScores.METABOLISM,.5f);
	public static final Organ SMALL_ANIMAL_STOMACH = new Organ().setOrganQuality(CCOrganScores.DIGESTION,.5f);
	public static final Organ RABBIT_HEART = new Organ().setOrganQuality(CCOrganScores.HEALTH,.5f).setOrganQuality(CCOrganScores.SPEED, 1f);
	public static final Organ SMALL_AQUATIC_MUSCLE = new Organ(16,CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT).setOrganQuality(CCOrganScores.STRENGTH,.5f).setOrganQuality(CCOrganScores.SPEED,.25f).setOrganQuality(CCOrganScores.SWIM_SPEED, .5f);
	public static final Organ SMALL_FISH_MUSCLE = new Organ(16,CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT).setOrganQuality(CCOrganScores.STRENGTH,.25f).setOrganQuality(CCOrganScores.SPEED,.25f).setOrganQuality(CCOrganScores.SWIM_SPEED, .25f);
	public static final Organ SMALL_GILLS = new Organ().setOrganQuality(CCOrganScores.WATERBREATH,.5f);
	public static final Organ SMALL_CARNIVORE_STOMACH = new Organ().setOrganQuality(CCOrganScores.HERBIVOROUS_DIGESTION,.25f).setOrganQuality(CCOrganScores.CARNIVOROUS_DIGESTION,.75f);
	public static final Organ SMALL_CARNIVORE_INTESTINE = new Organ().setOrganQuality(CCOrganScores.HERBIVOROUS_NUTRITION,.25f).setOrganQuality(CCOrganScores.CARNIVOROUS_NUTRITION,.75f);
	public static final Organ SMALL_HERBIVORE_STOMACH = new Organ().setOrganQuality(CCOrganScores.HERBIVOROUS_DIGESTION,.75f).setOrganQuality(CCOrganScores.CARNIVOROUS_DIGESTION,.25f);
	public static final Organ SMALL_HERBIVORE_INTESTINE = new Organ().setOrganQuality(CCOrganScores.HERBIVOROUS_NUTRITION,.75f).setOrganQuality(CCOrganScores.CARNIVOROUS_NUTRITION,.25f);

	public static final Organ INSECT_HEART = new Organ().setOrganQuality(CCOrganScores.HEALTH,.5f);
	public static final Organ INSECT_INTESTINE = new Organ().setOrganQuality(CCOrganScores.NUTRITION,.5f);
	public static final Organ INSECT_LUNG = new Organ().setOrganQuality(CCOrganScores.BREATH,.75f).setOrganQuality(CCOrganScores.ENDURANCE,.75f);
	public static final Organ INSECT_MUSCLE = new Organ(16,CCFoodComponents.INSECT_MUSCLE_FOOD_COMPONENT).setOrganQuality(CCOrganScores.STRENGTH,.5f).setOrganQuality(CCOrganScores.SPEED,1.25f);
	public static final Organ INSECT_STOMACH = new Organ().setOrganQuality(CCOrganScores.DIGESTION,.5f).setOrganQuality(CCOrganScores.NUTRITION,.25f);
	public static final Organ INSECT_CAECA = new Organ().setOrganQuality(CCOrganScores.DIGESTION, .25f).setOrganQuality(CCOrganScores.NUTRITION, .5f).setOrganQuality(CCOrganScores.NERVOUS_SYSTEM,.5f);
	public static final Organ SILK_GLAND = new Organ().setOrganQuality(CCOrganScores.SILK,1f);
	public static final Organ VENOM_GLAND = new VenomGland().setOrganQuality(CCOrganScores.VENOMOUS,1f);

	public static final Organ ENDER_APPENDIX = new Organ().setOrganQuality(CCOrganScores.LUCK,1.25f).setOrganQuality(CCOrganScores.ARROW_DODGING,1f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,1f);
	public static final Organ ENDER_HEART = new Organ().setOrganQuality(CCOrganScores.HEALTH,1.25f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,1f);
	public static final Organ ENDER_INTESTINE = new Organ().setOrganQuality(CCOrganScores.NUTRITION,1.25f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,1f);
	public static final Organ ENDER_KIDNEY = new Organ().setOrganQuality(CCOrganScores.FILTRATION,1f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,1f).setOrganQuality(CCOrganScores.HYDROPHOBIA, 1f);
	public static final Organ ENDER_LIVER = new Organ().setOrganQuality(CCOrganScores.DETOXIFICATION,1.25f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,1f);
	public static final Organ ENDER_LUNG = new Organ().setOrganQuality(CCOrganScores.BREATH,1.25f).setOrganQuality(CCOrganScores.ENDURANCE,1.25f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,1f);
	public static final Organ ENDER_MUSCLE = new Organ(16,CCFoodComponents.ALIEN_MUSCLE_FOOD_COMPONENT).setOrganQuality(CCOrganScores.STRENGTH,1.25f).setOrganQuality(CCOrganScores.SPEED,1.25f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,1f);
	public static final Organ ENDER_RIB = new Organ(4).setOrganQuality(CCOrganScores.DEFENSE,1.25f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,1f);
	public static final Organ ENDER_SPINE = new Organ().setOrganQuality(CCOrganScores.NERVOUS_SYSTEM,1.25f).setOrganQuality(CCOrganScores.DEFENSE,.9375f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,1f);
	public static final Organ ENDER_SPLEEN = new Organ().setOrganQuality(CCOrganScores.METABOLISM,1.25f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,1f);
	public static final Organ ENDER_STOMACH = new Organ().setOrganQuality(CCOrganScores.DIGESTION,1.25f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,1f);

	public static final Organ DRAGON_APPENDIX = new Organ().setOrganQuality(CCOrganScores.LUCK,.75f).setOrganQuality(CCOrganScores.DRAGON_BOMBS,1f);
	//TODO: Dragon Bombs
	public static final Organ DRAGON_HEART = new Organ().setOrganQuality(CCOrganScores.HEALTH,1.5f).setOrganQuality(CCOrganScores.ENDURANCE,-.5f);
	public static final Organ DRAGON_KIDNEY = new Organ().setOrganQuality(CCOrganScores.FILTRATION,1.5f).setOrganQuality(CCOrganScores.ENDURANCE,-.5f);
	public static final Organ DRAGON_LIVER = new Organ().setOrganQuality(CCOrganScores.DETOXIFICATION,1.5f).setOrganQuality(CCOrganScores.BUFF_PURGING,1f);
	public static final Organ DRAGON_LUNG = new Organ().setOrganQuality(CCOrganScores.BREATH,.5f).setOrganQuality(CCOrganScores.ENDURANCE,1f).setOrganQuality(CCOrganScores.DRAGON_BREATH,1f);
	//TODO: Dragon Breath
	public static final Organ DRAGON_MUSCLE = new Organ(16,CCFoodComponents.DRAGON_MUSCLE_FOOD_COMPONENT).setOrganQuality(CCOrganScores.STRENGTH,.5f).setOrganQuality(CCOrganScores.SPEED,1.5f).setOrganQuality(CCOrganScores.LAUNCHING,1f).setOrganQuality(CCOrganScores.ENDURANCE,-.25f);
	//TODO: Launching
	public static final Organ DRAGON_RIB = new Organ(4).setOrganQuality(CCOrganScores.DEFENSE,1f).setOrganQuality(CCOrganScores.IMPACT_RESISTANT,1f).setOrganQuality(CCOrganScores.DESTRUCTIVE_COLLISIONS,1f).setOrganQuality(CCOrganScores.ENDURANCE,-.25f);
	//TODO: Impact Resistance
	//TODO: Destructive Collisions
	public static final Organ DRAGON_SPINE = new Organ().setOrganQuality(CCOrganScores.NERVOUS_SYSTEM,1f).setOrganQuality(CCOrganScores.DEFENSE,.75f).setOrganQuality(CCOrganScores.IMPACT_RESISTANT,1f).setOrganQuality(CCOrganScores.DESTRUCTIVE_COLLISIONS,1f).setOrganQuality(CCOrganScores.ENDURANCE,-.25f);
	public static final Organ DRAGON_SPLEEN = new Organ().setOrganQuality(CCOrganScores.METABOLISM,1.5f).setOrganQuality(CCOrganScores.ENDURANCE,-.5f);
	public static final Organ MANA_REACTOR = new Organ().setOrganQuality(CCOrganScores.CRYSTALSYNTHESIS,1f);

	public static final Organ ACTIVE_BLAZE_ROD = new Organ(3).setOrganQuality(CCOrganScores.PYROMANCY,3f).setOrganQuality(CCOrganScores.FIRE_RESISTANT,1f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,3f);
	public static final Organ BLAZE_SHELL = new Organ(4).setOrganQuality(CCOrganScores.DEFENSE,1f).setOrganQuality(CCOrganScores.FIRE_RESISTANT,1f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,3f);
	public static final Organ BLAZE_CORE = new Organ().setOrganQuality(CCOrganScores.NERVOUS_SYSTEM,.5f).setOrganQuality(CCOrganScores.HEALTH,1f).setOrganQuality(CCOrganScores.FIRE_RESISTANT,1f).setOrganQuality(CCOrganScores.HYDROALLERGENIC,3f);

	public static final Organ GAS_BLADDER = new Organ().setOrganQuality(CCOrganScores.BREATH,1.5f).setOrganQuality(CCOrganScores.ENDURANCE,.25f).setOrganQuality(CCOrganScores.BUOYANT,1f).setOrganQuality(CCOrganScores.FIRE_RESISTANT,1f);
	public static final Organ VOLATILE_STOMACH = new Organ().setOrganQuality(CCOrganScores.DIGESTION,.5f).setOrganQuality(CCOrganScores.GHASTLY,1f).setOrganQuality(CCOrganScores.FIRE_RESISTANT,1f);

	public static final Organ SALTWATER_HEART = new Organ().setOrganQuality(CCOrganScores.HEALTH,1f).setOrganQuality(CCOrganScores.WATERBREATH,.25f);
	public static final Organ SALTWATER_LUNG = new Organ().setOrganQuality(CCOrganScores.BREATH,1f).setOrganQuality(CCOrganScores.ENDURANCE,1f).setOrganQuality(CCOrganScores.WATERBREATH,.25f);
	public static final Organ CREEPER_APPENDIX = new CreeperAppendix().setOrganQuality(CCOrganScores.LUCK,.75f).setOrganQuality(CCOrganScores.CREEPY,1).setOrganQuality(CCOrganScores.EXPLOSIVE,1);
	public static final Organ SHULKER_SPLEEN = new Organ().setOrganQuality(CCOrganScores.METABOLISM,.75f).setOrganQuality(CCOrganScores.SHULKER_BULLETS,1f);

	public static final Item SAUSAGE_SKIN = new Item(new Item.Settings().maxCount(64));
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

	public static final Item CUD = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.CUD_FOOD_COMPONENT));
	public static void register() {
		registerItem("chest_opener", CHEST_OPENER);

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
		
		registerItem("saltwater_heart", SALTWATER_HEART);
		registerItem("saltwater_lung", SALTWATER_LUNG);
		registerItem("creeper_appendix", CREEPER_APPENDIX);
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
	}
	
	private static void registerItem(String name, Item item) {
		Registry.register(Registry.ITEM, ChestCavity.MODID + ":" + name, item);
    }
}
