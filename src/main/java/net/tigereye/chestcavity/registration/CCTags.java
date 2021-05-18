package net.tigereye.chestcavity.registration;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;

public class CCTags {
    public static final Tag<Item> BUTCHERING_TOOL = TagRegistry.item(new Identifier(ChestCavity.MODID,"butchering_tool"));
    public static final Tag<Item> ROTTEN_FOOD = TagRegistry.item(new Identifier(ChestCavity.MODID,"rotten_food"));
    public static final Tag<Item> CARNIVORE_FOOD = TagRegistry.item(new Identifier(ChestCavity.MODID,"carnivore_food"));
    public static final Tag<Item> SALVAGEABLE = TagRegistry.item(new Identifier(ChestCavity.MODID,"salvageable"));
    public static final Tag<Item> IRON_REPAIR_MATERIAL = TagRegistry.item(new Identifier(ChestCavity.MODID,"iron_repair_material"));
}
