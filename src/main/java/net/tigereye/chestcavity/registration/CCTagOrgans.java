package net.tigereye.chestcavity.registration;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class CCTagOrgans {

    public static Map<TagKey<Item>,Map<Identifier,Float>> tagMap = new HashMap<>();

    public static void init(){
        Map<Identifier,Float> ease_of_access = new HashMap<>();
        ease_of_access.put(CCOrganScores.EASE_OF_ACCESS,1f*Items.OAK_DOOR.getMaxCount());
        tagMap.put(ItemTags.DOORS,ease_of_access);
        tagMap.put(ItemTags.TRAPDOORS,ease_of_access);
    }
}