package net.tigereye.chestcavity.registration;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;

public class CCTagOrgans {

    public static Map<TagKey<Item>,Map<Identifier,Float>> tagMap = new HashMap<>();

    public static void init(){
        Map<Identifier,Float> ease_of_access = new HashMap<>();
        ease_of_access.put(CCOrganScores.EASE_OF_ACCESS,64f);
        tagMap.put(TagKey.create(net.minecraft.core.registries.Registries.ITEM,Identifier.withDefaultNamespace("doors")),ease_of_access);
        tagMap.put(TagKey.create(net.minecraft.core.registries.Registries.ITEM,Identifier.withDefaultNamespace("trapdoors")),ease_of_access);
    }
}