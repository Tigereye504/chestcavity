package net.tigereye.chestcavity.items;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.tigereye.chestcavity.ChestCavity;

public class RegisterItems {
	public static final Item.Settings CHEST_OPENER_SETTINGS = new Item.Settings().maxCount(1).group(ItemGroup.TOOLS);
	public static final Item.Settings ORGAN_SETTINGS_1 = new Item.Settings().maxCount(1).group(ItemGroup.MISC);
	public static final Item.Settings ORGAN_SETTINGS_2 = new Item.Settings().maxCount(2).group(ItemGroup.MISC);
	public static final Item.Settings ORGAN_SETTINGS_4 = new Item.Settings().maxCount(4).group(ItemGroup.MISC);
	public static final Item.Settings ORGAN_SETTINGS_64 = new Item.Settings().maxCount(64).group(ItemGroup.MISC);
	public static final Item.Settings RAW_ORGAN_MEAT_SETTINGS = new Item.Settings().maxCount(64).group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(4).saturationModifier(2.5f).build());
	public static final Item.Settings COOKED_ORGAN_MEAT_SETTINGS = new Item.Settings().maxCount(64).group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(9).saturationModifier(18f).build());
	
	public static final Item chest_opener = new ChestOpener();
	public static final Item apendix = new OrganBase();
	public static final Item heart = new OrganBase();
	public static final Item intestine = new OrganBase();
	public static final Item kidney = new OrganBase();
	public static final Item liver = new OrganBase();
	public static final Item lung = new OrganBase();
	public static final Item muscle = new OrganBase(ORGAN_SETTINGS_64);
	public static final Item rib = new OrganBase(ORGAN_SETTINGS_4);
	public static final Item spine = new OrganBase();
	public static final Item spleen = new OrganBase();
	public static final Item stomach = new OrganBase();

	public static final Item rottenApendix = new OrganBase(.5f);
	public static final Item rottenHeart = new OrganBase(.5f);
	public static final Item rottenIntestine = new OrganBase(.5f);
	public static final Item rottenKidney = new OrganBase(.5f);
	public static final Item rottenLiver = new OrganBase(.5f);
	public static final Item rottenLung = new OrganBase(.5f);
	public static final Item rottenRib = new OrganBase(.5f);
	public static final Item rottenSpine = new OrganBase(.5f);
	public static final Item rottenSpleen = new OrganBase(.5f);
	public static final Item rottenStomach = new OrganBase(.5f);

	public static final Item animalApendix = new OrganBase(.75f);
	public static final Item animalHeart = new OrganBase(.75f);
	public static final Item animalIntestine = new OrganBase(.75f);
	public static final Item animalKidney = new OrganBase(.75f);
	public static final Item animalLiver = new OrganBase(.75f);
	public static final Item animalLung = new OrganBase(.75f);
	public static final Item animalRib = new OrganBase(.75f);
	public static final Item animalSpine = new OrganBase(.75f);
	public static final Item animalSpleen = new OrganBase(.75f);
	public static final Item animalStomach = new OrganBase(.75f);
	
	public static final Item rawOrganMeat = new Item(RAW_ORGAN_MEAT_SETTINGS);
	public static final Item cookedOrganMeat = new Item(COOKED_ORGAN_MEAT_SETTINGS);
	
	public static final Tag<Item> HUMAN_ORGANS = TagRegistry.item(new Identifier(ChestCavity.MODID,"human_organs"));
	public static final Tag<Item> SALVAGEABLE_ROTTEN_ORGANS = TagRegistry.item(new Identifier(ChestCavity.MODID,"salvageable_rotten_organs"));
	public static final Tag<Item> SALVAGEABLE_MEAT_ORGANS = TagRegistry.item(new Identifier(ChestCavity.MODID,"salvageable_meat_organs"));
	public static final Tag<Item> SALVAGEABLE_BONE_ORGANS = TagRegistry.item(new Identifier(ChestCavity.MODID,"salvageable_bone_organs"));
	public static final Tag<Item> ORGANS_APENDIX = TagRegistry.item(new Identifier(ChestCavity.MODID,"organs_apendix"));
	public static final Tag<Item> ORGANS_HEART = TagRegistry.item(new Identifier(ChestCavity.MODID,"organs_heart"));
	public static final Tag<Item> ORGANS_INTESTINE = TagRegistry.item(new Identifier(ChestCavity.MODID,"organs_intestine"));
	public static final Tag<Item> ORGANS_KIDNEY = TagRegistry.item(new Identifier(ChestCavity.MODID,"organs_kidney"));
	public static final Tag<Item> ORGANS_LIVER = TagRegistry.item(new Identifier(ChestCavity.MODID,"organs_liver"));
	public static final Tag<Item> ORGANS_LUNG = TagRegistry.item(new Identifier(ChestCavity.MODID,"organs_lung"));
	public static final Tag<Item> ORGANS_MUSCLE = TagRegistry.item(new Identifier(ChestCavity.MODID,"organs_muscle"));
	public static final Tag<Item> ORGANS_RIB = TagRegistry.item(new Identifier(ChestCavity.MODID,"organs_rib"));
	public static final Tag<Item> ORGANS_SPINE = TagRegistry.item(new Identifier(ChestCavity.MODID,"organs_spine"));
	public static final Tag<Item> ORGANS_SPLEEN = TagRegistry.item(new Identifier(ChestCavity.MODID,"organs_spleen"));
	public static final Tag<Item> ORGANS_STOMACH = TagRegistry.item(new Identifier(ChestCavity.MODID,"organs_stomach"));
	
	public static void register() {
		registerItem("chest_opener", chest_opener);

		registerItem("apendix", apendix);
		registerItem("heart", heart);
		registerItem("intestine", intestine);
		registerItem("kidney", kidney);
		registerItem("liver", liver);
		registerItem("lung", lung);
		registerItem("muscle", muscle);
		registerItem("rib", rib);
		registerItem("spine", spine);
		registerItem("spleen", spleen);
		registerItem("stomach", stomach);
		
		registerItem("rotten_apendix", rottenApendix);
		registerItem("rotten_heart", rottenHeart);
		registerItem("rotten_intestine", rottenIntestine);
		registerItem("rotten_kidney", rottenKidney);
		registerItem("rotten_liver", rottenLiver);
		registerItem("rotten_lung", rottenLung);
		registerItem("rotten_rib", rottenRib);
		registerItem("rotten_spine", rottenSpine);
		registerItem("rotten_spleen", rottenSpleen);
		registerItem("rotten_stomach", rottenStomach);

		registerItem("animal_apendix", animalApendix);
		registerItem("animal_heart", animalHeart);
		registerItem("animal_intestine", animalIntestine);
		registerItem("animal_kidney", animalKidney);
		registerItem("animal_liver", animalLiver);
		registerItem("animal_lung", animalLung);
		registerItem("animal_rib", animalRib);
		registerItem("animal_spine", animalSpine);
		registerItem("animal_spleen", animalSpleen);
		registerItem("animal_stomach", animalStomach);
		
		registerItem("raw_organ_meat", rawOrganMeat);
		registerItem("cooked_organ_meat", cookedOrganMeat);
	}
	
	private static void registerItem(String name, Item item) {
		Registry.register(Registry.ITEM, ChestCavity.MODID + ":" + name, item);
    }
}
