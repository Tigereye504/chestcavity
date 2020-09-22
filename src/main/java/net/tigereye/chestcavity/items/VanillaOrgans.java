package net.tigereye.chestcavity.items;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class VanillaOrgans {

    public static Map<Item,Map<Identifier,Float>> map = new HashMap<>();

    public static void init(){
        Map<Identifier,Float> dirt = new HashMap<>();
        dirt.put(CC_Items.ORGANS_APPENDIX,1f/64*27);
        dirt.put(CC_Items.ORGANS_HEART,1f/64*27);
        dirt.put(CC_Items.ORGANS_MUSCLE,8f/27);
        dirt.put(CC_Items.ORGANS_SPINE,1f/64*27);
        dirt.put(CC_Items.ORGANS_LIVER,1f/64*27);
        dirt.put(CC_Items.ORGANS_KIDNEY,2f/64*27);
        dirt.put(CC_Items.ORGANS_SPLEEN,1f/64*27);
        dirt.put(CC_Items.ORGANS_LUNG,2f/64*27);
        dirt.put(CC_Items.ORGANS_INTESTINE,4f/64*27);
        dirt.put(CC_Items.ORGANS_RIB,4f/16*27);
        dirt.put(CC_Items.ORGANS_STOMACH,1f/64*27);
        Map<Identifier,Float> rottenFlesh = new HashMap<>();
        rottenFlesh.put(CC_Items.ORGANS_MUSCLE,.5f);
        Map<Identifier,Float> bone = new HashMap<>();
        rottenFlesh.put(CC_Items.ORGANS_RIB,.5f/16);
        map.put(Items.DIRT,dirt);
        map.put(Items.ROTTEN_FLESH,rottenFlesh);
        map.put(Items.BONE,bone);
    }
}