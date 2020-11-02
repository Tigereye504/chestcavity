package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ZombieChestCavityManager extends ChestCavityManager{


    public ZombieChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public ZombieChestCavityManager(LivingEntity owner, int size) {
        super(owner,size);
    }

    @Override
    public void fillChestCavityInventory() {
        chestCavity.clear();
        chestCavity.setStack(0, new ItemStack(Items.ROTTEN_FLESH, Items.ROTTEN_FLESH.getMaxCount()));
        chestCavity.setStack(1, new ItemStack(CCItems.ROTTEN_RIB, CCItems.ROTTEN_RIB.getMaxCount()));
        chestCavity.setStack(2, new ItemStack(CCItems.ROTTEN_APPENDIX, CCItems.ROTTEN_APPENDIX.getMaxCount()));
        chestCavity.setStack(3, new ItemStack(CCItems.ROTTEN_LUNG, CCItems.ROTTEN_LUNG.getMaxCount()));
        chestCavity.setStack(4, new ItemStack(CCItems.ROTTEN_HEART, CCItems.ROTTEN_HEART.getMaxCount()));
        chestCavity.setStack(5, new ItemStack(CCItems.ROTTEN_LUNG, CCItems.ROTTEN_LUNG.getMaxCount()));
        chestCavity.setStack(6, ItemStack.EMPTY);
        chestCavity.setStack(7, new ItemStack(CCItems.ROTTEN_RIB, CCItems.ROTTEN_RIB.getMaxCount()));
        chestCavity.setStack(8, new ItemStack(Items.ROTTEN_FLESH, Items.ROTTEN_FLESH.getMaxCount()));
        chestCavity.setStack(9, new ItemStack(Items.ROTTEN_FLESH, Items.ROTTEN_FLESH.getMaxCount()));
        chestCavity.setStack(10, new ItemStack(CCItems.ROTTEN_RIB, CCItems.ROTTEN_RIB.getMaxCount()));
        chestCavity.setStack(11, new ItemStack(CCItems.ROTTEN_SPLEEN, CCItems.ROTTEN_SPLEEN.getMaxCount()));
        chestCavity.setStack(12, new ItemStack(CCItems.ROTTEN_KIDNEY, CCItems.ROTTEN_KIDNEY.getMaxCount()));
        chestCavity.setStack(13, new ItemStack(CCItems.ROTTEN_SPINE, CCItems.ROTTEN_SPINE.getMaxCount()));
        chestCavity.setStack(14, new ItemStack(CCItems.ROTTEN_KIDNEY, CCItems.ROTTEN_KIDNEY.getMaxCount()));
        chestCavity.setStack(15, new ItemStack(CCItems.ROTTEN_LIVER, CCItems.ROTTEN_LIVER.getMaxCount()));
        chestCavity.setStack(16, new ItemStack(CCItems.ROTTEN_RIB, CCItems.ROTTEN_RIB.getMaxCount()));
        chestCavity.setStack(17, new ItemStack(Items.ROTTEN_FLESH, Items.ROTTEN_FLESH.getMaxCount()));
        chestCavity.setStack(18, new ItemStack(Items.ROTTEN_FLESH, Items.ROTTEN_FLESH.getMaxCount()));
        chestCavity.setStack(19, new ItemStack(Items.ROTTEN_FLESH, Items.ROTTEN_FLESH.getMaxCount()));
        chestCavity.setStack(20, new ItemStack(CCItems.ROTTEN_INTESTINE, CCItems.ROTTEN_INTESTINE.getMaxCount()));
        chestCavity.setStack(21, new ItemStack(CCItems.ROTTEN_INTESTINE, CCItems.ROTTEN_INTESTINE.getMaxCount()));
        chestCavity.setStack(22, new ItemStack(CCItems.ROTTEN_STOMACH, CCItems.ROTTEN_STOMACH.getMaxCount()));
        chestCavity.setStack(23, new ItemStack(CCItems.ROTTEN_INTESTINE, CCItems.ROTTEN_INTESTINE.getMaxCount()));
        chestCavity.setStack(24, new ItemStack(CCItems.ROTTEN_INTESTINE, CCItems.ROTTEN_INTESTINE.getMaxCount()));
        chestCavity.setStack(25, new ItemStack(Items.ROTTEN_FLESH, Items.ROTTEN_FLESH.getMaxCount()));
        chestCavity.setStack(26, new ItemStack(Items.ROTTEN_FLESH, Items.ROTTEN_FLESH.getMaxCount()));
    }

    @Override
    protected void ResetOrganScores(){
        //animated by unholy magic, zombies function at half capacity even when completely organ-less.
        //lucky them, as their default organs are rotted and thus only 50% effective
        organScores.clear();
        organScores.put(CCOrganScores.APPENDIX, .5f);
        organScores.put(CCOrganScores.BONE, 2.375f);
        organScores.put(CCOrganScores.HEART, 0.5f);
        organScores.put(CCOrganScores.INTESTINE, 2f);
        organScores.put(CCOrganScores.KIDNEY, 1f);
        organScores.put(CCOrganScores.LIVER, .5f);
        organScores.put(CCOrganScores.MUSCLE, 4f);
        organScores.put(CCOrganScores.SPINE, .5f);
        organScores.put(CCOrganScores.SPLEEN, .5f);
        organScores.put(CCOrganScores.STOMACH, .5f);
        organScores.put(CCOrganScores.LUNG, 1f);
    }

    @Override
    public List<ItemStack> generateLootDrops(Random random, int looting){
        List<ItemStack> loot = new ArrayList<>();
        if(random.nextFloat() < ChestCavity.config.ORGAN_BUNDLE_DROP_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*looting)) {
            LinkedList<Item> organPile = new LinkedList<>();
            organPile.add(CCItems.ROTTEN_APPENDIX);
            organPile.add(CCItems.ROTTEN_HEART);
            organPile.add(CCItems.ROTTEN_INTESTINE);
            organPile.add(CCItems.ROTTEN_KIDNEY);
            organPile.add(CCItems.ROTTEN_LIVER);
            organPile.add(CCItems.ROTTEN_LUNG);
            organPile.add(CCItems.ROTTEN_SPLEEN);
            organPile.add(CCItems.ROTTEN_STOMACH);
            int rolls = 1 + random.nextInt(3) + random.nextInt(3);
            for (int i = 0; i < rolls; i++){
                int roll = random.nextInt(organPile.size());
                loot.add(new ItemStack(organPile.remove(roll)));

            }
        }
        return loot;
    }

}
