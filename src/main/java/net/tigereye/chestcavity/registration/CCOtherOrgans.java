package net.tigereye.chestcavity.registration;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.HashMap;
import java.util.Map;

public class CCOtherOrgans {

    public static Map<Item,Map<Identifier,Float>> map = new HashMap<>();
    public static Map<Tag<Item>,Map<Identifier,Float>> tagMap = new HashMap<>();

    public static void init(){
        Map<Identifier,Float> dirt = new HashMap<>();
        dirt.put(CCOrganScores.APPENDIX,1f/27);
        dirt.put(CCOrganScores.HEART,1f/27);
        dirt.put(CCOrganScores.STRENGTH,8f/27);
        dirt.put(CCOrganScores.SPEED,8f/27);
        dirt.put(CCOrganScores.NERVOUS_SYSTEM,1f/27);
        dirt.put(CCOrganScores.LIVER,1f/27);
        dirt.put(CCOrganScores.KIDNEY,2f/27);
        dirt.put(CCOrganScores.SPLEEN,1f/27);
        dirt.put(CCOrganScores.LUNG,2f/27);
        dirt.put(CCOrganScores.INTESTINE,4f/27);
        dirt.put(CCOrganScores.BONE,4f/27);
        dirt.put(CCOrganScores.STOMACH,1f/27);
        map.put(Items.DIRT,dirt);

        Map<Identifier,Float> rottenFlesh = new HashMap<>();
        rottenFlesh.put(CCOrganScores.STRENGTH,.5f);
        rottenFlesh.put(CCOrganScores.SPEED,.5f);
        map.put(Items.ROTTEN_FLESH,rottenFlesh);

        Map<Identifier,Float> animalFlesh = new HashMap<>();
        animalFlesh.put(CCOrganScores.STRENGTH,.75f);
        animalFlesh.put(CCOrganScores.SPEED,.75f);
        map.put(Items.BEEF,animalFlesh);
        map.put(Items.PORKCHOP,animalFlesh);
        map.put(Items.MUTTON,animalFlesh);

        Map<Identifier,Float> bone = new HashMap<>();
        bone.put(CCOrganScores.BONE,.5f);
        map.put(Items.BONE,bone);

        Map<Identifier,Float> gunpowder = new HashMap<>();
        gunpowder.put(CCOrganScores.EXPLOSIVE,3f*Items.GUNPOWDER.getMaxCount());
        map.put(Items.GUNPOWDER,gunpowder);

        Map<Identifier,Float> tnt = new HashMap<>();
        tnt.put(CCOrganScores.EXPLOSIVE,16f*Items.GUNPOWDER.getMaxCount());
        map.put(Items.TNT,tnt);

        Map<Identifier,Float> ease_of_access = new HashMap<>();
        ease_of_access.put(CCOrganScores.EASE_OF_ACCESS,1f);
        tagMap.put(ItemTags.DOORS,ease_of_access);
        tagMap.put(ItemTags.TRAPDOORS,ease_of_access);
    }
}