package net.tigereye.chestcavity.chestcavities.types;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EmptyChestCavity extends BaseChestCavity implements ChestCavityType {
    @Override
    public void fillChestCavityInventory(ChestCavityInventory chestCavity) {
        chestCavity.clear();
    }

    @Override
    public void loadBaseOrganScores(Map<Identifier, Float> organScores){
        organScores.clear();
        organScores.put(CCOrganScores.LUCK, 1f);
        organScores.put(CCOrganScores.DEFENSE, 4.75f);
        organScores.put(CCOrganScores.HEALTH, 1f);
        organScores.put(CCOrganScores.NUTRITION, 4f);
        organScores.put(CCOrganScores.FILTRATION, 2f);
        organScores.put(CCOrganScores.DETOXIFICATION, 1f);
        organScores.put(CCOrganScores.STRENGTH, 8f);
        organScores.put(CCOrganScores.SPEED, 8f);
        organScores.put(CCOrganScores.NERVOUS_SYSTEM, 1f);
        organScores.put(CCOrganScores.METABOLISM, 1f);
        organScores.put(CCOrganScores.DIGESTION, 1f);
        organScores.put(CCOrganScores.BREATH, 2f);
        organScores.put(CCOrganScores.ENDURANCE, 2f);
    }
}
