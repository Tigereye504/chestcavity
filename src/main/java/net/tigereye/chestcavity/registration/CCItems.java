package net.tigereye.chestcavity.registration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.items.ChestOpener;
import net.tigereye.chestcavity.items.CreeperAppendix;
import net.tigereye.chestcavity.items.EnderKidney;
import net.tigereye.chestcavity.items.OrganBase;

public class CCItems {


	public static final Item.Settings CHEST_OPENER_SETTINGS = new Item.Settings().maxCount(1).group(ItemGroup.TOOLS);
	public static final Item.Settings ORGAN_SETTINGS_1 = new Item.Settings().maxCount(1).group(ItemGroup.MISC);
	public static final Item.Settings ORGAN_SETTINGS_2 = new Item.Settings().maxCount(2).group(ItemGroup.MISC);
	public static final Item.Settings ORGAN_SETTINGS_4 = new Item.Settings().maxCount(4).group(ItemGroup.MISC);
	public static final Item.Settings ORGAN_SETTINGS_16 = new Item.Settings().maxCount(16).group(ItemGroup.MISC);
	public static final Item.Settings ORGAN_SETTINGS_64 = new Item.Settings().maxCount(64).group(ItemGroup.MISC);
	public static final Item.Settings FOOD_ITEM_SETTINGS = new Item.Settings().maxCount(64).group(ItemGroup.FOOD);


	public static final Item CHEST_OPENER = new ChestOpener();

	public static final Item HUMAN_APPENDIX = new OrganBase().setOrganQuality(CCOrganScores.APPENDIX,1f);
	public static final Item HUMAN_HEART = new OrganBase().setOrganQuality(CCOrganScores.HEART,1f);
	public static final Item HUMAN_INTESTINE = new OrganBase().setOrganQuality(CCOrganScores.INTESTINE,1f);
	public static final Item HUMAN_KIDNEY = new OrganBase().setOrganQuality(CCOrganScores.KIDNEY,1f);
	public static final Item HUMAN_LIVER = new OrganBase().setOrganQuality(CCOrganScores.LIVER,1f);
	public static final Item HUMAN_LUNG = new OrganBase().setOrganQuality(CCOrganScores.LUNG,1f);
	public static final Item HUMAN_MUSCLE = new OrganBase(ORGAN_SETTINGS_16.food(CCFoodComponents.HUMAN_MUSCLE_FOOD_COMPONENT)).setOrganQuality(CCOrganScores.STRENGTH,1f).setOrganQuality(CCOrganScores.SPEED,1f);
	public static final Item HUMAN_RIB = new OrganBase(ORGAN_SETTINGS_4).setOrganQuality(CCOrganScores.BONE,1f);
	public static final Item HUMAN_SPINE = new OrganBase().setOrganQuality(CCOrganScores.NERVOUS_SYSTEM,1f).setOrganQuality(CCOrganScores.BONE,.75f);
	public static final Item HUMAN_SPLEEN = new OrganBase().setOrganQuality(CCOrganScores.SPLEEN,1f);
	public static final Item HUMAN_STOMACH = new OrganBase().setOrganQuality(CCOrganScores.STOMACH,1f);

	public static final Item ROTTEN_APPENDIX = new OrganBase().setOrganQuality(CCOrganScores.APPENDIX,.5f);
	public static final Item ROTTEN_HEART = new OrganBase().setOrganQuality(CCOrganScores.HEART,.5f);
	public static final Item ROTTEN_INTESTINE = new OrganBase().setOrganQuality(CCOrganScores.INTESTINE,.5f);
	public static final Item ROTTEN_KIDNEY = new OrganBase().setOrganQuality(CCOrganScores.KIDNEY,.5f);
	public static final Item ROTTEN_LIVER = new OrganBase().setOrganQuality(CCOrganScores.LIVER,.5f);
	public static final Item ROTTEN_LUNG = new OrganBase().setOrganQuality(CCOrganScores.LUNG,.5f);
	public static final Item ROTTEN_RIB = new OrganBase(ORGAN_SETTINGS_4).setOrganQuality(CCOrganScores.BONE,.5f);
	public static final Item ROTTEN_SPINE = new OrganBase().setOrganQuality(CCOrganScores.NERVOUS_SYSTEM,.5f).setOrganQuality(CCOrganScores.BONE,.375f);
	public static final Item ROTTEN_SPLEEN = new OrganBase().setOrganQuality(CCOrganScores.SPLEEN,.5f);
	public static final Item ROTTEN_STOMACH = new OrganBase().setOrganQuality(CCOrganScores.STOMACH,.5f);

	public static final Item ANIMAL_APPENDIX = new OrganBase().setOrganQuality(CCOrganScores.APPENDIX,.75f);
	public static final Item ANIMAL_HEART = new OrganBase().setOrganQuality(CCOrganScores.HEART,.75f);
	public static final Item ANIMAL_INTESTINE = new OrganBase().setOrganQuality(CCOrganScores.INTESTINE,.75f);
	public static final Item ANIMAL_KIDNEY = new OrganBase().setOrganQuality(CCOrganScores.KIDNEY,.75f);
	public static final Item ANIMAL_LIVER = new OrganBase().setOrganQuality(CCOrganScores.LIVER,.75f);
	public static final Item ANIMAL_LUNG = new OrganBase().setOrganQuality(CCOrganScores.LUNG,.75f);
	public static final Item ANIMAL_MUSCLE = new OrganBase(ORGAN_SETTINGS_16.food(CCFoodComponents.ANIMAL_MUSCLE_FOOD_COMPONENT)).setOrganQuality(CCOrganScores.STRENGTH,.75f).setOrganQuality(CCOrganScores.SPEED,.75f);
	public static final Item ANIMAL_RIB = new OrganBase(ORGAN_SETTINGS_4).setOrganQuality(CCOrganScores.BONE,.75f);
	public static final Item ANIMAL_SPINE = new OrganBase().setOrganQuality(CCOrganScores.NERVOUS_SYSTEM,.75f).setOrganQuality(CCOrganScores.BONE,.5625f);
	public static final Item ANIMAL_SPLEEN = new OrganBase().setOrganQuality(CCOrganScores.SPLEEN,.75f);
	public static final Item ANIMAL_STOMACH = new OrganBase().setOrganQuality(CCOrganScores.STOMACH,.75f);

	public static final Item SMALL_ANIMAL_APPENDIX = new OrganBase().setOrganQuality(CCOrganScores.APPENDIX,.5f);
	public static final Item SMALL_ANIMAL_HEART = new OrganBase().setOrganQuality(CCOrganScores.HEART,.5f);
	public static final Item SMALL_ANIMAL_INTESTINE = new OrganBase().setOrganQuality(CCOrganScores.INTESTINE,.5f);
	public static final Item SMALL_ANIMAL_KIDNEY = new OrganBase().setOrganQuality(CCOrganScores.KIDNEY,.5f);
	public static final Item SMALL_ANIMAL_LIVER = new OrganBase().setOrganQuality(CCOrganScores.LIVER,.5f);
	public static final Item SMALL_ANIMAL_LUNG = new OrganBase().setOrganQuality(CCOrganScores.LUNG,.5f);
	public static final Item SMALL_ANIMAL_MUSCLE = new OrganBase(ORGAN_SETTINGS_16.food(CCFoodComponents.SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT)).setOrganQuality(CCOrganScores.STRENGTH,.5f).setOrganQuality(CCOrganScores.SPEED,.5f);
	public static final Item SMALL_ANIMAL_RIB = new OrganBase(ORGAN_SETTINGS_4).setOrganQuality(CCOrganScores.BONE,.5f);
	public static final Item SMALL_ANIMAL_SPINE = new OrganBase().setOrganQuality(CCOrganScores.NERVOUS_SYSTEM,.5f).setOrganQuality(CCOrganScores.BONE,.375f);
	public static final Item SMALL_ANIMAL_SPLEEN = new OrganBase().setOrganQuality(CCOrganScores.SPLEEN,.5f);
	public static final Item SMALL_ANIMAL_STOMACH = new OrganBase().setOrganQuality(CCOrganScores.STOMACH,.5f);

	public static final Item SALTWATER_HEART = new OrganBase().setOrganQuality(CCOrganScores.HEART,1f).setOrganQuality(CCOrganScores.LUNG,.25f);
	public static final Item SALTWATER_LUNG = new OrganBase().setOrganQuality(CCOrganScores.LUNG,1f).setOrganQuality(CCOrganScores.HEART,.25f);
	public static final Item RABBIT_HEART = new OrganBase().setOrganQuality(CCOrganScores.HEART,.5f).setOrganQuality(CCOrganScores.SPEED, 1f);
	public static final Item CREEPER_APPENDIX = new CreeperAppendix().setOrganQuality(CCOrganScores.APPENDIX,.75f).setOrganQuality(CCOrganScores.CREEPINESS,1);
	public static final Item ENDER_KIDNEY = new EnderKidney().setOrganQuality(CCOrganScores.KIDNEY, .75f).setOrganQuality(CCOrganScores.HYDROPHOBIA, 1);

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
	public static final Item RAW_HUMAN_ORGAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item COOKED_HUMAN_ORGAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_HUMAN_ORGAN_MEAT_FOOD_COMPONENT));
	public static final Item RAW_MAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_MAN_MEAT_FOOD_COMPONENT));
	public static final Item COOKED_MAN_MEAT = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_MAN_MEAT_FOOD_COMPONENT));
	public static final Item RAW_HUMAN_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_HUMAN_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_HUMAN_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_HUMAN_SAUSAGE_FOOD_COMPONENT));
	public static final Item RAW_RICH_HUMAN_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.RAW_RICH_HUMAN_SAUSAGE_FOOD_COMPONENT));
	public static final Item COOKED_RICH_HUMAN_SAUSAGE = new Item(FOOD_ITEM_SETTINGS.food(CCFoodComponents.COOKED_RICH_HUMAN_SAUSAGE_FOOD_COMPONENT));

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

		registerItem("saltwater_heart", SALTWATER_HEART);
		registerItem("saltwater_lung", SALTWATER_LUNG);
		registerItem("rabbit_heart", RABBIT_HEART);
		registerItem("creeper_appendix", CREEPER_APPENDIX);
		registerItem("ender_kidney", ENDER_KIDNEY);

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
		registerItem("raw_human_organ_meat", RAW_HUMAN_ORGAN_MEAT);
		registerItem("cooked_human_organ_meat", COOKED_HUMAN_ORGAN_MEAT);
		registerItem("raw_man_meat", RAW_MAN_MEAT);
		registerItem("cooked_man_meat", COOKED_MAN_MEAT);
		registerItem("raw_human_sausage", RAW_HUMAN_SAUSAGE);
		registerItem("human_sausage", COOKED_HUMAN_SAUSAGE);
		registerItem("raw_rich_human_sausage", RAW_RICH_HUMAN_SAUSAGE);
		registerItem("rich_human_sausage", COOKED_RICH_HUMAN_SAUSAGE);
	}
	
	private static void registerItem(String name, Item item) {
		Registry.register(Registry.ITEM, ChestCavity.MODID + ":" + name, item);
    }
}
