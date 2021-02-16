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

public class BlazeChestCavity extends BaseChestCavity implements ChestCavityType {
    @Override
    public void fillChestCavityInventory(ChestCavityInventory chestCavity) {
        chestCavity.clear();
        chestCavity.setStack(0, ItemStack.EMPTY);
        chestCavity.setStack(1, new ItemStack(CCItems.ACTIVE_BLAZE_ROD, 1));
        chestCavity.setStack(2, new ItemStack(CCItems.BLAZE_SHELL, CCItems.BLAZE_SHELL.getMaxCount()));
        chestCavity.setStack(3, new ItemStack(Items.MAGMA_BLOCK, 4));
        chestCavity.setStack(4, new ItemStack(Items.MAGMA_BLOCK, 4));
        chestCavity.setStack(5, new ItemStack(Items.MAGMA_BLOCK, 4));
        chestCavity.setStack(6, new ItemStack(CCItems.BLAZE_SHELL, CCItems.BLAZE_SHELL.getMaxCount()));
        chestCavity.setStack(7, ItemStack.EMPTY);
        chestCavity.setStack(8, ItemStack.EMPTY);
        chestCavity.setStack(9, ItemStack.EMPTY);
        chestCavity.setStack(10, ItemStack.EMPTY);
        chestCavity.setStack(11, new ItemStack(CCItems.BLAZE_SHELL, CCItems.BLAZE_SHELL.getMaxCount()));
        chestCavity.setStack(12, new ItemStack(Items.MAGMA_BLOCK, 4));
        chestCavity.setStack(13, new ItemStack(CCItems.BLAZE_CORE, CCItems.BLAZE_CORE.getMaxCount()));
        chestCavity.setStack(14, new ItemStack(Items.MAGMA_BLOCK, 4));
        chestCavity.setStack(15, new ItemStack(CCItems.BLAZE_SHELL, CCItems.BLAZE_SHELL.getMaxCount()));
        chestCavity.setStack(16, new ItemStack(CCItems.ACTIVE_BLAZE_ROD, 1));
        chestCavity.setStack(17, ItemStack.EMPTY);
        chestCavity.setStack(18, new ItemStack(CCItems.ACTIVE_BLAZE_ROD, 1));
        chestCavity.setStack(19, ItemStack.EMPTY);
        chestCavity.setStack(20, new ItemStack(CCItems.BLAZE_SHELL, CCItems.BLAZE_SHELL.getMaxCount()));
        chestCavity.setStack(21, new ItemStack(Items.MAGMA_BLOCK, 4));
        chestCavity.setStack(22, new ItemStack(Items.MAGMA_BLOCK, 4));
        chestCavity.setStack(23, new ItemStack(Items.MAGMA_BLOCK, 4));
        chestCavity.setStack(24, new ItemStack(CCItems.BLAZE_SHELL, CCItems.BLAZE_SHELL.getMaxCount()));
        chestCavity.setStack(25, ItemStack.EMPTY);
        chestCavity.setStack(26, ItemStack.EMPTY);
    }

    @Override
    public boolean catchExceptionalOrgan(ItemStack slot, Map<Identifier, Float> organScores){
        //creepers are plant monsters, using leaves for flesh and wood for bone
        if(slot.getItem() == Items.MAGMA_BLOCK){
            ChestCavityUtil.addOrganScore(CCOrganScores.DETOXIFICATION, 2f,organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.BREATH, 4f,organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.FILTRATION, 4f,organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.METABOLISM, 2f,organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.LUCK, 2f,organScores);
            return true;
        }
        if(slot.getItem() == CCItems.ACTIVE_BLAZE_ROD){
            ChestCavityUtil.addOrganScore(CCOrganScores.STRENGTH, 1f,organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.SPEED, 1f,organScores);
            return true;
        }
        return false;
    }

    @Override
    public void generateRareOrganDrops(Random random, int looting, List<ItemStack> loot) {
        LinkedList<Item> organPile = new LinkedList<>();
        organPile.add(CCItems.ACTIVE_BLAZE_ROD);
        organPile.add(CCItems.BLAZE_SHELL);
        organPile.add(CCItems.BLAZE_SHELL);
        organPile.add(CCItems.BLAZE_SHELL);
        organPile.add(CCItems.BLAZE_SHELL);
        organPile.add(CCItems.BLAZE_SHELL);
        organPile.add(CCItems.BLAZE_SHELL);
        organPile.add(CCItems.BLAZE_CORE);
        int rolls = 1 + random.nextInt(3) + random.nextInt(3);
        for (int i = 0; i < rolls; i++){
            int roll = random.nextInt(organPile.size());
            int count = 1;
            Item rolledItem = organPile.get(roll);
            if(rolledItem.getMaxCount() > 1){
                count += random.nextInt(rolledItem.getMaxCount());
            }
            loot.add(new ItemStack(organPile.remove(roll),count));
        }
    }

}
