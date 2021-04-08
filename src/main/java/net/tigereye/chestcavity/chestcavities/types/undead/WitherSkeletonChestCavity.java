package net.tigereye.chestcavity.chestcavities.types.undead;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class WitherSkeletonChestCavity extends BaseChestCavity implements ChestCavityType {
    @Override
    public void fillChestCavityInventory(ChestCavityInventory chestCavity) {
        chestCavity.clear();
        chestCavity.setStack(0, ItemStack.EMPTY);
        chestCavity.setStack(1, new ItemStack(CCItems.WITHERED_RIB, CCItems.WITHERED_RIB.getMaxCount()));
        chestCavity.setStack(2, ItemStack.EMPTY);
        chestCavity.setStack(3, ItemStack.EMPTY);
        chestCavity.setStack(4, ItemStack.EMPTY);
        chestCavity.setStack(5, ItemStack.EMPTY);
        chestCavity.setStack(6, ItemStack.EMPTY);
        chestCavity.setStack(7, new ItemStack(CCItems.WITHERED_RIB, CCItems.WITHERED_RIB.getMaxCount()));
        chestCavity.setStack(8, ItemStack.EMPTY);
        chestCavity.setStack(9, ItemStack.EMPTY);
        chestCavity.setStack(10, new ItemStack(CCItems.WITHERED_RIB, CCItems.WITHERED_RIB.getMaxCount()));
        chestCavity.setStack(11, ItemStack.EMPTY);
        chestCavity.setStack(12, ItemStack.EMPTY);
        chestCavity.setStack(13, new ItemStack(CCItems.WITHERED_SPINE, CCItems.WITHERED_SPINE.getMaxCount()));
        chestCavity.setStack(14, ItemStack.EMPTY);
        chestCavity.setStack(15, ItemStack.EMPTY);
        chestCavity.setStack(16, new ItemStack(CCItems.WITHERED_RIB, CCItems.WITHERED_RIB.getMaxCount()));
        chestCavity.setStack(17, ItemStack.EMPTY);
        chestCavity.setStack(18, ItemStack.EMPTY);
        chestCavity.setStack(19, ItemStack.EMPTY);
        chestCavity.setStack(20, ItemStack.EMPTY);
        chestCavity.setStack(21, ItemStack.EMPTY);
        chestCavity.setStack(22, ItemStack.EMPTY);
        chestCavity.setStack(23, ItemStack.EMPTY);
        chestCavity.setStack(24, ItemStack.EMPTY);
        chestCavity.setStack(25, ItemStack.EMPTY);
        chestCavity.setStack(26, ItemStack.EMPTY);
    }

    @Override
    public void shapeChestCavity() {
        forbiddenSlots.add(0);
        forbiddenSlots.add(8);
        forbiddenSlots.add(9);
        forbiddenSlots.add(17);
        forbiddenSlots.add(18);
        forbiddenSlots.add(19);
        forbiddenSlots.add(20);
        forbiddenSlots.add(21);
        forbiddenSlots.add(22);
        forbiddenSlots.add(23);
        forbiddenSlots.add(24);
        forbiddenSlots.add(25);
        forbiddenSlots.add(26);
    }

    @Override
    public void loadBaseOrganScores(Map<Identifier, Float> organScores){
        organScores.clear();
        organScores.put(CCOrganScores.LUCK, 1f);
        organScores.put(CCOrganScores.DEFENSE, 2.375f);
        organScores.put(CCOrganScores.HEALTH, 1f);
        organScores.put(CCOrganScores.NUTRITION, 4f);
        organScores.put(CCOrganScores.FILTRATION, 2f);
        organScores.put(CCOrganScores.DETOXIFICATION, 1f);
        organScores.put(CCOrganScores.STRENGTH, 8f);
        organScores.put(CCOrganScores.SPEED, 8f);
        organScores.put(CCOrganScores.NERVOUS_SYSTEM, .5f);
        organScores.put(CCOrganScores.METABOLISM, 1f);
        organScores.put(CCOrganScores.DIGESTION, 1f);
        organScores.put(CCOrganScores.BREATH, 2f);
        organScores.put(CCOrganScores.ENDURANCE, 2f);
    }

    @Override
    public void generateRareOrganDrops(Random random, int looting, List<ItemStack> loot) {
        LinkedList<Item> organPile = new LinkedList<>();
        for(int i = 0; i < 4; i++){
            organPile.add(CCItems.WITHERED_RIB);
        }
        organPile.add(CCItems.WITHERED_SPINE);
        int rolls = 1 + random.nextInt(1) + random.nextInt(1);
        ChestCavityUtil.drawOrgansFromPile(organPile,rolls,random,loot);
    }

}
