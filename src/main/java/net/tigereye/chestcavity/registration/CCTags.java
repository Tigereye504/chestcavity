package net.tigereye.chestcavity.registration;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;

public class CCTags {
    public static final TagKey<Item> BUTCHERING_TOOL = TagKey.of(RegistryKeys.ITEM, new Identifier(ChestCavity.MODID,"butchering_tool"));
    public static final TagKey<Item> ROTTEN_FOOD = TagKey.of(RegistryKeys.ITEM, new Identifier(ChestCavity.MODID,"rotten_food"));
    public static final TagKey<Item> CARNIVORE_FOOD = TagKey.of(RegistryKeys.ITEM, new Identifier(ChestCavity.MODID,"carnivore_food"));
    public static final TagKey<Item> SALVAGEABLE = TagKey.of(RegistryKeys.ITEM, new Identifier(ChestCavity.MODID,"salvageable"));
    public static final TagKey<Item> IRON_REPAIR_MATERIAL = TagKey.of(RegistryKeys.ITEM, new Identifier(ChestCavity.MODID,"iron_repair_material"));

    //public static final Tag<Item> BUTCHERING_TOOL = TagRegistry.item(new Identifier(ChestCavity.MODID,"butchering_tool"));
    //public static final Tag<Item> ROTTEN_FOOD = TagRegistry.item(new Identifier(ChestCavity.MODID,"rotten_food"));
    //public static final Tag<Item> CARNIVORE_FOOD = TagRegistry.item(new Identifier(ChestCavity.MODID,"carnivore_food"));
    //public static final Tag<Item> SALVAGEABLE = TagRegistry.item(new Identifier(ChestCavity.MODID,"salvageable"));
    //public static final Tag<Item> IRON_REPAIR_MATERIAL = TagRegistry.item(new Identifier(ChestCavity.MODID,"iron_repair_material"));
}
