package net.tigereye.chestcavity.chestcavities.types.humanoid;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.types.BaseChestCavity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.*;

public class HumanChestCavity extends BaseChestCavity implements ChestCavityType {
    /*
    public HumanChestCavity(){
        fillChestCavityInventory(defaultChestCavity);
        ChestCavityUtil.determineDefaultOrganScores(this);
    }
    */
    @Override
    public void fillChestCavityInventory(ChestCavityInventory chestCavity) {
        chestCavity.clear();
        chestCavity.setStack(0, new ItemStack(CCItems.HUMAN_MUSCLE, CCItems.HUMAN_MUSCLE.getMaxCount()));
        chestCavity.setStack(1, new ItemStack(CCItems.HUMAN_RIB, CCItems.HUMAN_RIB.getMaxCount()));
        chestCavity.setStack(2, new ItemStack(CCItems.HUMAN_APPENDIX, CCItems.HUMAN_APPENDIX.getMaxCount()));
        chestCavity.setStack(3, new ItemStack(CCItems.HUMAN_LUNG, CCItems.HUMAN_LUNG.getMaxCount()));
        chestCavity.setStack(4, new ItemStack(CCItems.HUMAN_HEART, CCItems.HUMAN_HEART.getMaxCount()));
        chestCavity.setStack(5, new ItemStack(CCItems.HUMAN_LUNG, CCItems.HUMAN_LUNG.getMaxCount()));
        chestCavity.setStack(6, ItemStack.EMPTY);
        chestCavity.setStack(7, new ItemStack(CCItems.HUMAN_RIB, CCItems.HUMAN_RIB.getMaxCount()));
        chestCavity.setStack(8, new ItemStack(CCItems.HUMAN_MUSCLE, CCItems.HUMAN_MUSCLE.getMaxCount()));
        chestCavity.setStack(9, new ItemStack(CCItems.HUMAN_MUSCLE, CCItems.HUMAN_MUSCLE.getMaxCount()));
        chestCavity.setStack(10, new ItemStack(CCItems.HUMAN_RIB, CCItems.HUMAN_RIB.getMaxCount()));
        chestCavity.setStack(11, new ItemStack(CCItems.HUMAN_SPLEEN, CCItems.HUMAN_SPLEEN.getMaxCount()));
        chestCavity.setStack(12, new ItemStack(CCItems.HUMAN_KIDNEY, CCItems.HUMAN_KIDNEY.getMaxCount()));
        chestCavity.setStack(13, new ItemStack(CCItems.HUMAN_SPINE, CCItems.HUMAN_SPINE.getMaxCount()));
        chestCavity.setStack(14, new ItemStack(CCItems.HUMAN_KIDNEY, CCItems.HUMAN_KIDNEY.getMaxCount()));
        chestCavity.setStack(15, new ItemStack(CCItems.HUMAN_LIVER, CCItems.HUMAN_LIVER.getMaxCount()));
        chestCavity.setStack(16, new ItemStack(CCItems.HUMAN_RIB, CCItems.HUMAN_RIB.getMaxCount()));
        chestCavity.setStack(17, new ItemStack(CCItems.HUMAN_MUSCLE, CCItems.HUMAN_MUSCLE.getMaxCount()));
        chestCavity.setStack(18, new ItemStack(CCItems.HUMAN_MUSCLE, CCItems.HUMAN_MUSCLE.getMaxCount()));
        chestCavity.setStack(19, new ItemStack(CCItems.HUMAN_MUSCLE, CCItems.HUMAN_MUSCLE.getMaxCount()));
        chestCavity.setStack(20, new ItemStack(CCItems.HUMAN_INTESTINE, CCItems.HUMAN_INTESTINE.getMaxCount()));
        chestCavity.setStack(21, new ItemStack(CCItems.HUMAN_INTESTINE, CCItems.HUMAN_INTESTINE.getMaxCount()));
        chestCavity.setStack(22, new ItemStack(CCItems.HUMAN_STOMACH, CCItems.HUMAN_STOMACH.getMaxCount()));
        chestCavity.setStack(23, new ItemStack(CCItems.HUMAN_INTESTINE, CCItems.HUMAN_INTESTINE.getMaxCount()));
        chestCavity.setStack(24, new ItemStack(CCItems.HUMAN_INTESTINE, CCItems.HUMAN_INTESTINE.getMaxCount()));
        chestCavity.setStack(25, new ItemStack(CCItems.HUMAN_MUSCLE, CCItems.HUMAN_MUSCLE.getMaxCount()));
        chestCavity.setStack(26, new ItemStack(CCItems.HUMAN_MUSCLE, CCItems.HUMAN_MUSCLE.getMaxCount()));

    }

    @Override
    public void generateRareOrganDrops(Random random, int looting, List<ItemStack> loot) {
        LinkedList<Item> organPile = new LinkedList<>();
        for(int i = 0; i < 4; i++){
            organPile.add(CCItems.HUMAN_RIB);
        }
        for(int i = 0; i < 8; i++){
            organPile.add(CCItems.HUMAN_MUSCLE);
        }
        for(int i = 0; i < 4; i++){
            organPile.add(CCItems.HUMAN_INTESTINE);
        }
        organPile.add(CCItems.HUMAN_APPENDIX);
        organPile.add(CCItems.HUMAN_HEART);
        organPile.add(CCItems.HUMAN_KIDNEY);
        organPile.add(CCItems.HUMAN_KIDNEY);
        organPile.add(CCItems.HUMAN_LIVER);
        organPile.add(CCItems.HUMAN_LUNG);
        organPile.add(CCItems.HUMAN_LUNG);
        organPile.add(CCItems.HUMAN_SPINE);
        organPile.add(CCItems.HUMAN_SPLEEN);
        organPile.add(CCItems.HUMAN_STOMACH);
        int rolls = 1 + random.nextInt(3) + random.nextInt(3);
        ChestCavityUtil.drawOrgansFromPile(organPile,rolls,random,loot);
    }

}
