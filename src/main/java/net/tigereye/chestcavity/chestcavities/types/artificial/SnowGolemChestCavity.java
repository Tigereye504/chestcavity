package net.tigereye.chestcavity.chestcavities.types.artificial;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.types.BaseChestCavity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SnowGolemChestCavity extends BaseChestCavity implements ChestCavityType {
    @Override
    public void fillChestCavityInventory(ChestCavityInventory chestCavity) {
        chestCavity.clear();
        chestCavity.setStack(0, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(1, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(2, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(3, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(4, new ItemStack(Items.CHARCOAL, 1));
        chestCavity.setStack(5, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(6, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(7, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(8, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(9, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(10, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(11, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(12, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(13, new ItemStack(Items.CHARCOAL, 1));
        chestCavity.setStack(14, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(15, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(16, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(17, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(18, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(19, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(20, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(21, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(22, new ItemStack(Items.CHARCOAL, 1));
        chestCavity.setStack(23, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(24, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(25, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(26, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
    }

    @Override
    public boolean catchExceptionalOrgan(ItemStack slot, Map<Identifier, Float> organScores){
        //charcoal functions as heart and bone and spine
        //snowballs function as muscle
        if(slot.getItem() == Items.SNOWBALL){
            ChestCavityUtil.addOrganScore(CCOrganScores.STRENGTH, slot.getCount()/(3f*slot.getMaxCount()),organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.SPEED, slot.getCount()/(3f*slot.getMaxCount()),organScores);
            return true;
        }
        if(slot.getItem() == Items.CHARCOAL){
            ChestCavityUtil.addOrganScore(CCOrganScores.HEALTH, slot.getCount()*(1f/3),organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.DEFENSE, slot.getCount()*(4.75f/3),organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.NERVOUS_SYSTEM, slot.getCount()*(1f/3),organScores);
            return true;
        }
        if(slot.getItem() == Items.SNOW_BLOCK){
            ChestCavityUtil.addOrganScore(CCOrganScores.STRENGTH, slot.getCount()/(1f*slot.getMaxCount()),organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.SPEED, slot.getCount()/(1f*slot.getMaxCount()),organScores);
            return true;
        }
        return false;
    }

    @Override
    public void loadBaseOrganScores(Map<Identifier, Float> organScores){
        //snow golems dont breath, don't eat, and dont have blood
        //as such they don't need the organs related to such
        organScores.clear();
        organScores.put(CCOrganScores.LUCK, 1f);
        organScores.put(CCOrganScores.NUTRITION, 4f);
        organScores.put(CCOrganScores.FILTRATION, 2f);
        organScores.put(CCOrganScores.DETOXIFICATION, 1f);
        organScores.put(CCOrganScores.METABOLISM, 1f);
        organScores.put(CCOrganScores.DIGESTION, 1f);
        organScores.put(CCOrganScores.BREATH, 2f);
    }

}
