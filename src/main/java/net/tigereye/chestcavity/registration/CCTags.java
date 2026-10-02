package net.tigereye.chestcavity.registration;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import net.tigereye.chestcavity.ChestCavity;

public class CCTags {
    public static final TagKey<Item> BUTCHERING_TOOL = TagKey.create(net.minecraft.core.registries.Registries.ITEM, Identifier.fromNamespaceAndPath(ChestCavity.MODID,"butchering_tool"));
    public static final TagKey<Item> ROTTEN_FOOD = TagKey.create(net.minecraft.core.registries.Registries.ITEM, Identifier.fromNamespaceAndPath(ChestCavity.MODID,"rotten_food"));
    public static final TagKey<Item> CARNIVORE_FOOD = TagKey.create(net.minecraft.core.registries.Registries.ITEM, Identifier.fromNamespaceAndPath(ChestCavity.MODID,"carnivore_food"));
    public static final TagKey<Item> SALVAGEABLE = TagKey.create(net.minecraft.core.registries.Registries.ITEM, Identifier.fromNamespaceAndPath(ChestCavity.MODID,"salvageable"));
    public static final TagKey<Item> IRON_REPAIR_MATERIAL = TagKey.create(net.minecraft.core.registries.Registries.ITEM, Identifier.fromNamespaceAndPath(ChestCavity.MODID,"iron_repair_material"));

    //public static final Tag<Item> BUTCHERING_TOOL = TagRegistry.item(Identifier.fromNamespaceAndPath(ChestCavity.MODID,"butchering_tool"));
    //public static final Tag<Item> ROTTEN_FOOD = TagRegistry.item(Identifier.fromNamespaceAndPath(ChestCavity.MODID,"rotten_food"));
    //public static final Tag<Item> CARNIVORE_FOOD = TagRegistry.item(Identifier.fromNamespaceAndPath(ChestCavity.MODID,"carnivore_food"));
    //public static final Tag<Item> SALVAGEABLE = TagRegistry.item(Identifier.fromNamespaceAndPath(ChestCavity.MODID,"salvageable"));
    //public static final Tag<Item> IRON_REPAIR_MATERIAL = TagRegistry.item(Identifier.fromNamespaceAndPath(ChestCavity.MODID,"iron_repair_material"));
}
