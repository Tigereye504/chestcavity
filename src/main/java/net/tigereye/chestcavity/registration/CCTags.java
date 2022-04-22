package net.tigereye.chestcavity.registration;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;

public class CCTags {
    public static final TagKey<Item> BUTCHERING_TOOL = TagKey.of(Registry.ITEM_KEY, new Identifier(ChestCavity.MODID,"butchering_tool"));
    public static final TagKey<Item> ROTTEN_FOOD = TagKey.of(Registry.ITEM_KEY, new Identifier(ChestCavity.MODID,"rotten_food"));
    public static final TagKey<Item> CARNIVORE_FOOD = TagKey.of(Registry.ITEM_KEY, new Identifier(ChestCavity.MODID,"carnivore_food"));
    public static final TagKey<Item> SALVAGEABLE = TagKey.of(Registry.ITEM_KEY, new Identifier(ChestCavity.MODID,"salvageable"));
    public static final TagKey<Item> IRON_REPAIR_MATERIAL = TagKey.of(Registry.ITEM_KEY, new Identifier(ChestCavity.MODID,"iron_repair_material"));

    //public static final Tag<Item> BUTCHERING_TOOL = TagRegistry.item(new Identifier(ChestCavity.MODID,"butchering_tool"));
    //public static final Tag<Item> ROTTEN_FOOD = TagRegistry.item(new Identifier(ChestCavity.MODID,"rotten_food"));
    //public static final Tag<Item> CARNIVORE_FOOD = TagRegistry.item(new Identifier(ChestCavity.MODID,"carnivore_food"));
    //public static final Tag<Item> SALVAGEABLE = TagRegistry.item(new Identifier(ChestCavity.MODID,"salvageable"));
    //public static final Tag<Item> IRON_REPAIR_MATERIAL = TagRegistry.item(new Identifier(ChestCavity.MODID,"iron_repair_material"));
}
