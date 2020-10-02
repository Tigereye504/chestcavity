package net.tigereye.chestcavity.items;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.tigereye.chestcavity.ChestCavity;

public class CCItems {
	
	public static final Identifier ORGANS_APPENDIX = new Identifier(ChestCavity.MODID, "organs_appendix");
	public static final Identifier ORGANS_HEART = new Identifier(ChestCavity.MODID, "organs_heart");
	public static final Identifier ORGANS_INTESTINE = new Identifier(ChestCavity.MODID, "organs_intestine");
	public static final Identifier ORGANS_KIDNEY = new Identifier(ChestCavity.MODID, "organs_kidney");
	public static final Identifier ORGANS_LIVER = new Identifier(ChestCavity.MODID, "organs_liver");
	public static final Identifier ORGANS_LUNG = new Identifier(ChestCavity.MODID, "organs_lung");
	public static final Identifier ORGANS_MUSCLE = new Identifier(ChestCavity.MODID, "organs_muscle");
	public static final Identifier ORGANS_RIB = new Identifier(ChestCavity.MODID, "organs_rib");
	public static final Identifier ORGANS_SPINE = new Identifier(ChestCavity.MODID, "organs_spine");
	public static final Identifier ORGANS_SPLEEN = new Identifier(ChestCavity.MODID, "organs_spleen");
	public static final Identifier ORGANS_STOMACH = new Identifier(ChestCavity.MODID, "organs_stomach");





	public static final Tag<Item> HUMAN_ORGANS = TagRegistry.item(new Identifier(ChestCavity.MODID,"human_organs"));
	public static final Tag<Item> SALVAGEABLE_ROTTEN_ORGANS = TagRegistry.item(new Identifier(ChestCavity.MODID,"salvageable_rotten_organs"));
	public static final Tag<Item> SALVAGEABLE_MEAT_ORGANS = TagRegistry.item(new Identifier(ChestCavity.MODID,"salvageable_meat_organs"));
	public static final Tag<Item> SALVAGEABLE_BONE_ORGANS = TagRegistry.item(new Identifier(ChestCavity.MODID,"salvageable_bone_organs"));
	public static final Tag<Item> ORGANS_APPENDIX_TAG = TagRegistry.item(ORGANS_APPENDIX);
	public static final Tag<Item> ORGANS_HEART_TAG = TagRegistry.item(ORGANS_HEART);
	public static final Tag<Item> ORGANS_INTESTINE_TAG = TagRegistry.item(ORGANS_INTESTINE);
	public static final Tag<Item> ORGANS_KIDNEY_TAG = TagRegistry.item(ORGANS_KIDNEY);
	public static final Tag<Item> ORGANS_LIVER_TAG = TagRegistry.item(ORGANS_LIVER);
	public static final Tag<Item> ORGANS_LUNG_TAG = TagRegistry.item(ORGANS_LUNG);
	public static final Tag<Item> ORGANS_MUSCLE_TAG = TagRegistry.item(ORGANS_MUSCLE);
	public static final Tag<Item> ORGANS_RIB_TAG = TagRegistry.item(ORGANS_RIB);
	public static final Tag<Item> ORGANS_SPINE_TAG = TagRegistry.item(ORGANS_SPINE);
	public static final Tag<Item> ORGANS_SPLEEN_TAG = TagRegistry.item(ORGANS_SPLEEN);
	public static final Tag<Item> ORGANS_STOMACH_TAG = TagRegistry.item(ORGANS_STOMACH);

	public static final Item.Settings CHEST_OPENER_SETTINGS = new Item.Settings().maxCount(1).group(ItemGroup.TOOLS);
	public static final Item.Settings ORGAN_SETTINGS_1 = new Item.Settings().maxCount(1).group(ItemGroup.MISC);
	public static final Item.Settings ORGAN_SETTINGS_2 = new Item.Settings().maxCount(2).group(ItemGroup.MISC);
	public static final Item.Settings ORGAN_SETTINGS_4 = new Item.Settings().maxCount(4).group(ItemGroup.MISC);
	public static final Item.Settings ORGAN_SETTINGS_64 = new Item.Settings().maxCount(64).group(ItemGroup.MISC);
	public static final Item.Settings RAW_ORGAN_MEAT_SETTINGS = new Item.Settings().maxCount(64).group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(4).saturationModifier(1.25f).build());
	public static final Item.Settings COOKED_ORGAN_MEAT_SETTINGS = new Item.Settings().maxCount(64).group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(8).saturationModifier(1f).build());
	
	public static final Item CHEST_OPENER = new ChestOpener();

	public static final Item APPENDIX = new OrganBase().setOrganQuality(ORGANS_APPENDIX,1f);
	public static final Item HEART = new OrganBase().setOrganQuality(ORGANS_HEART,1f);
	public static final Item INTESTINE = new OrganBase().setOrganQuality(ORGANS_INTESTINE,1f);
	public static final Item KIDNEY = new OrganBase().setOrganQuality(ORGANS_KIDNEY,1f);
	public static final Item LIVER = new OrganBase().setOrganQuality(ORGANS_LIVER,1f);
	public static final Item LUNG = new OrganBase().setOrganQuality(ORGANS_LUNG,1f);
	public static final Item MUSCLE = new OrganBase(ORGAN_SETTINGS_64).setOrganQuality(ORGANS_MUSCLE,1f);
	public static final Item RIB = new OrganBase(ORGAN_SETTINGS_4).setOrganQuality(ORGANS_RIB,1f);
	public static final Item SPINE = new OrganBase().setOrganQuality(ORGANS_SPINE,1f);
	public static final Item SPLEEN = new OrganBase().setOrganQuality(ORGANS_SPLEEN,1f);
	public static final Item STOMACH = new OrganBase().setOrganQuality(ORGANS_STOMACH,1f);

	public static final Item ROTTEN_APPENDIX = new OrganBase().setOrganQuality(ORGANS_APPENDIX,.5f);
	public static final Item ROTTEN_HEART = new OrganBase().setOrganQuality(ORGANS_HEART,.5f);
	public static final Item ROTTEN_INTESTINE = new OrganBase().setOrganQuality(ORGANS_INTESTINE,.5f);
	public static final Item ROTTEN_KIDNEY = new OrganBase().setOrganQuality(ORGANS_KIDNEY,.5f);
	public static final Item ROTTEN_LIVER = new OrganBase().setOrganQuality(ORGANS_LIVER,.5f);
	public static final Item ROTTEN_LUNG = new OrganBase().setOrganQuality(ORGANS_LUNG,.5f);
	public static final Item ROTTEN_RIB = new OrganBase(ORGAN_SETTINGS_4).setOrganQuality(ORGANS_RIB,.5f);
	public static final Item ROTTEN_SPINE = new OrganBase().setOrganQuality(ORGANS_SPINE,.5f);
	public static final Item ROTTEN_SPLEEN = new OrganBase().setOrganQuality(ORGANS_SPLEEN,.5f);
	public static final Item ROTTEN_STOMACH = new OrganBase().setOrganQuality(ORGANS_STOMACH,.5f);

	public static final Item ANIMAL_APPENDIX = new OrganBase().setOrganQuality(ORGANS_APPENDIX,.75f);
	public static final Item ANIMAL_HEART = new OrganBase().setOrganQuality(ORGANS_HEART,.75f);
	public static final Item ANIMAL_INTESTINE = new OrganBase().setOrganQuality(ORGANS_INTESTINE,.75f);
	public static final Item ANIMAL_KIDNEY = new OrganBase().setOrganQuality(ORGANS_KIDNEY,.75f);
	public static final Item ANIMAL_LIVER = new OrganBase().setOrganQuality(ORGANS_LIVER,.75f);
	public static final Item ANIMAL_LUNG = new OrganBase().setOrganQuality(ORGANS_LUNG,.75f);
	public static final Item ANIMAL_RIB = new OrganBase(ORGAN_SETTINGS_4).setOrganQuality(ORGANS_RIB,.75f);
	public static final Item ANIMAL_SPINE = new OrganBase().setOrganQuality(ORGANS_SPINE,.75f);
	public static final Item ANIMAL_SPLEEN = new OrganBase().setOrganQuality(ORGANS_SPLEEN,.75f);
	public static final Item ANIMAL_STOMACH = new OrganBase().setOrganQuality(ORGANS_STOMACH,.75f);

	public static final Item SALTWATER_HEART = new OrganBase().setOrganQuality(ORGANS_HEART,1f).setOrganQuality(ORGANS_LUNG,.25f);
	public static final Item SALTWATER_LUNG = new OrganBase().setOrganQuality(ORGANS_LUNG,1f).setOrganQuality(ORGANS_HEART,.25f);

	public static final Item RAW_ORGAN_MEAT = new Item(RAW_ORGAN_MEAT_SETTINGS);
	public static final Item COOKED_ORGAN_MEAT = new Item(COOKED_ORGAN_MEAT_SETTINGS);

	public static void register() {
		registerItem("chest_opener", CHEST_OPENER);

		registerItem("appendix", APPENDIX);
		registerItem("heart", HEART);
		registerItem("intestine", INTESTINE);
		registerItem("kidney", KIDNEY);
		registerItem("liver", LIVER);
		registerItem("lung", LUNG);
		registerItem("muscle", MUSCLE);
		registerItem("rib", RIB);
		registerItem("spine", SPINE);
		registerItem("spleen", SPLEEN);
		registerItem("stomach", STOMACH);
		
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
		registerItem("animal_rib", ANIMAL_RIB);
		registerItem("animal_spine", ANIMAL_SPINE);
		registerItem("animal_spleen", ANIMAL_SPLEEN);
		registerItem("animal_stomach", ANIMAL_STOMACH);

		registerItem("saltwater_heart", SALTWATER_HEART);
		registerItem("saltwater_lung", SALTWATER_LUNG);
		
		registerItem("raw_organ_meat", RAW_ORGAN_MEAT);
		registerItem("cooked_organ_meat", COOKED_ORGAN_MEAT);
	}
	
	private static void registerItem(String name, Item item) {
		Registry.register(Registry.ITEM, ChestCavity.MODID + ":" + name, item);
    }
}
