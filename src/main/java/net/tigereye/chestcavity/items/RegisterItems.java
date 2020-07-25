package net.tigereye.chestcavity.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;

public class RegisterItems {
	public static final Item.Settings CHEST_OPENER_SETTINGS = new Item.Settings().maxCount(1).group(ItemGroup.TOOLS);
	public static final Item.Settings ORGAN_SETTINGS_1 = new Item.Settings().maxCount(1).group(ItemGroup.MISC);
	public static final Item.Settings ORGAN_SETTINGS_2 = new Item.Settings().maxCount(2).group(ItemGroup.MISC);
	public static final Item.Settings ORGAN_SETTINGS_4 = new Item.Settings().maxCount(4).group(ItemGroup.MISC);
	public static final Item.Settings ORGAN_SETTINGS_64 = new Item.Settings().maxCount(64).group(ItemGroup.MISC);
	
	public static final Item chest_opener = new ChestOpener();
	public static final Item apendix = new OrganApendix();
	public static final Item heart = new OrganHeart();
	public static final Item intestine = new OrganIntestine();
	public static final Item kidney = new OrganKidney();
	public static final Item liver = new OrganLiver();
	public static final Item lung = new OrganLung();
	public static final Item muscle = new OrganMuscle();
	public static final Item rib = new OrganRib();
	public static final Item spine = new OrganSpine();
	public static final Item spleen = new OrganSpleen();
	public static final Item stomach = new OrganStomach();

	public static final Item rottenApendix = new OrganApendix();
	public static final Item rottenHeart = new OrganHeart();
	public static final Item rottenIntestine = new OrganIntestine();
	public static final Item rottenKidney = new OrganKidney();
	public static final Item rottenLiver = new OrganLiver();
	public static final Item rottenLung = new OrganLung();
	public static final Item rottenSpine = new OrganSpine();
	public static final Item rottenSpleen = new OrganSpleen();
	public static final Item rottenStomach = new OrganStomach();
	
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
		registerItem("rotten_spine", rottenSpine);
		registerItem("rotten_spleen", rottenSpleen);
		registerItem("rotten_stomach", rottenStomach);
	}
	
	private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, ChestCavity.MODID + ":" + name, item);
    }
}
