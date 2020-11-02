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

public class SmallAnimalChestCavityManager extends ChestCavityManager{


    public SmallAnimalChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public SmallAnimalChestCavityManager(LivingEntity owner, int size) {
        super(owner,size);
    }

    @Override
    public void fillChestCavityInventory() {
        chestCavity.clear();
        chestCavity.setStack(0, ItemStack.EMPTY);
        chestCavity.setStack(1, new ItemStack(CCItems.ANIMAL_MUSCLE, CCItems.ANIMAL_MUSCLE.getMaxCount()/2));
        chestCavity.setStack(2, new ItemStack(Items.BONE_MEAL, 1));
        chestCavity.setStack(3, new ItemStack(CCItems.RAW_ORGAN_MEAT, 1));
        chestCavity.setStack(4, new ItemStack(Items.BONE, 1));
        chestCavity.setStack(5, new ItemStack(CCItems.RAW_ORGAN_MEAT, 1));
        chestCavity.setStack(6, new ItemStack(Items.BONE_MEAL, 1));
        chestCavity.setStack(7, new ItemStack(CCItems.ANIMAL_MUSCLE, CCItems.ANIMAL_MUSCLE.getMaxCount()/2));
        chestCavity.setStack(8, ItemStack.EMPTY);
        chestCavity.setStack(9, ItemStack.EMPTY);
        chestCavity.setStack(10, new ItemStack(CCItems.ANIMAL_MUSCLE, CCItems.ANIMAL_MUSCLE.getMaxCount()/2));
        chestCavity.setStack(11, new ItemStack(Items.BONE_MEAL, 1));
        chestCavity.setStack(12, new ItemStack(CCItems.RAW_ORGAN_MEAT, 1));
        chestCavity.setStack(13, new ItemStack(CCItems.RAW_ORGAN_MEAT, 1));
        chestCavity.setStack(14, new ItemStack(CCItems.RAW_ORGAN_MEAT, 1));
        chestCavity.setStack(15, new ItemStack(Items.BONE_MEAL, 1));
        chestCavity.setStack(16, new ItemStack(CCItems.ANIMAL_MUSCLE, CCItems.ANIMAL_MUSCLE.getMaxCount()/2));
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
    protected boolean catchExceptionalOrgan(ItemStack slot){
        //for small animals, organ meat serves as a general-purpose meaty organ //TODO: add small animal organs
        //for small animals, bonemeal serves as bone and a bone as the spine //TODO: add small animal bones and spine
        //for small animals, animal muscle is massively more effective than it has any right to be //TODO: add small animal muscle
        if(slot.getItem() == CCItems.RAW_ORGAN_MEAT){
            addOrganScore(CCOrganScores.APPENDIX,.2f*slot.getCount());
            addOrganScore(CCOrganScores.HEART,.2f*slot.getCount());
            addOrganScore(CCOrganScores.INTESTINE,.8f*slot.getCount());
            addOrganScore(CCOrganScores.KIDNEY,.4f*slot.getCount());
            addOrganScore(CCOrganScores.LIVER,.2f*slot.getCount());
            addOrganScore(CCOrganScores.LUNG,.4f*slot.getCount());
            addOrganScore(CCOrganScores.SPLEEN,.2f*slot.getCount());
            addOrganScore(CCOrganScores.STOMACH,.6f*slot.getCount());
            return true;
        }
        else if(slot.getItem() == CCItems.ANIMAL_MUSCLE){
            addOrganScore(CCOrganScores.BONE,4f*slot.getCount()/slot.getMaxCount());
            return true;
        }
        else if(slot.getItem() == Items.BONE_MEAL){
            addOrganScore(CCOrganScores.BONE,slot.getCount());
            return true;
        }
        else if(slot.getItem() == Items.BONE){
            addOrganScore(CCOrganScores.SPINE,slot.getCount());
            addOrganScore(CCOrganScores.BONE,.75f*slot.getCount());
            return true;
        }
        return false;
    }

    @Override
    public List<ItemStack> generateLootDrops(Random random, int looting){
        List<ItemStack> loot = new ArrayList<>();
        if(random.nextFloat() < ChestCavity.config.ORGAN_BUNDLE_DROP_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*looting)) {
            loot.add(new ItemStack(CCItems.RAW_ORGAN_MEAT,1 + random.nextInt(2) + random.nextInt(2)));
        }
        return loot;
    }
}
