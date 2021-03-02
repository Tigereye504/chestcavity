package net.tigereye.chestcavity.chestcavities.types.animal;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.types.BaseChestCavity;
import net.tigereye.chestcavity.registration.CCItems;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PufferfishChestCavity extends BaseChestCavity implements ChestCavityType {
    @Override
    public void fillChestCavityInventory(ChestCavityInventory chestCavity) {
        chestCavity.clear();
        chestCavity.setStack(0, new ItemStack(CCItems.SMALL_FISH_MUSCLE, CCItems.SMALL_FISH_MUSCLE.getMaxCount()));
        chestCavity.setStack(1, new ItemStack(CCItems.SMALL_ANIMAL_RIB, CCItems.SMALL_ANIMAL_RIB.getMaxCount()));
        chestCavity.setStack(2, new ItemStack(CCItems.SMALL_ANIMAL_APPENDIX, CCItems.SMALL_ANIMAL_APPENDIX.getMaxCount()));
        chestCavity.setStack(3, new ItemStack(CCItems.SMALL_GILLS, CCItems.SMALL_GILLS.getMaxCount()));
        chestCavity.setStack(4, new ItemStack(CCItems.SMALL_ANIMAL_HEART, CCItems.SMALL_ANIMAL_HEART.getMaxCount()));
        chestCavity.setStack(5, new ItemStack(CCItems.SMALL_GILLS, CCItems.SMALL_GILLS.getMaxCount()));
        chestCavity.setStack(6, new ItemStack(CCItems.VENOM_GLAND, CCItems.VENOM_GLAND.getMaxCount()));
        chestCavity.setStack(7, new ItemStack(CCItems.SMALL_ANIMAL_RIB, CCItems.SMALL_ANIMAL_RIB.getMaxCount()));
        chestCavity.setStack(8, new ItemStack(CCItems.SMALL_FISH_MUSCLE, CCItems.SMALL_FISH_MUSCLE.getMaxCount()));
        chestCavity.setStack(9, new ItemStack(CCItems.SMALL_FISH_MUSCLE, CCItems.SMALL_FISH_MUSCLE.getMaxCount()));
        chestCavity.setStack(10, new ItemStack(CCItems.SMALL_ANIMAL_RIB, CCItems.SMALL_ANIMAL_RIB.getMaxCount()));
        chestCavity.setStack(11, new ItemStack(CCItems.SMALL_ANIMAL_SPLEEN, CCItems.SMALL_ANIMAL_SPLEEN.getMaxCount()));
        chestCavity.setStack(12, new ItemStack(CCItems.SMALL_ANIMAL_KIDNEY, CCItems.SMALL_ANIMAL_KIDNEY.getMaxCount()));
        chestCavity.setStack(13, new ItemStack(CCItems.SMALL_ANIMAL_SPINE, CCItems.SMALL_ANIMAL_SPINE.getMaxCount()));
        chestCavity.setStack(14, new ItemStack(CCItems.SMALL_ANIMAL_KIDNEY, CCItems.SMALL_ANIMAL_KIDNEY.getMaxCount()));
        chestCavity.setStack(15, new ItemStack(CCItems.SMALL_ANIMAL_LIVER, CCItems.SMALL_ANIMAL_LIVER.getMaxCount()));
        chestCavity.setStack(16, new ItemStack(CCItems.SMALL_ANIMAL_RIB, CCItems.SMALL_ANIMAL_RIB.getMaxCount()));
        chestCavity.setStack(17, new ItemStack(CCItems.SMALL_FISH_MUSCLE, CCItems.SMALL_FISH_MUSCLE.getMaxCount()));
        chestCavity.setStack(18, new ItemStack(CCItems.SMALL_FISH_MUSCLE, CCItems.SMALL_FISH_MUSCLE.getMaxCount()));
        chestCavity.setStack(19, new ItemStack(CCItems.SMALL_FISH_MUSCLE, CCItems.SMALL_FISH_MUSCLE.getMaxCount()));
        chestCavity.setStack(20, new ItemStack(CCItems.SMALL_ANIMAL_INTESTINE, CCItems.SMALL_ANIMAL_INTESTINE.getMaxCount()));
        chestCavity.setStack(21, new ItemStack(CCItems.SMALL_ANIMAL_INTESTINE, CCItems.SMALL_ANIMAL_INTESTINE.getMaxCount()));
        chestCavity.setStack(22, new ItemStack(CCItems.SMALL_ANIMAL_STOMACH, CCItems.SMALL_ANIMAL_STOMACH.getMaxCount()));
        chestCavity.setStack(23, new ItemStack(CCItems.SMALL_ANIMAL_INTESTINE, CCItems.SMALL_ANIMAL_INTESTINE.getMaxCount()));
        chestCavity.setStack(24, new ItemStack(CCItems.SMALL_ANIMAL_INTESTINE, CCItems.SMALL_ANIMAL_INTESTINE.getMaxCount()));
        chestCavity.setStack(25, new ItemStack(CCItems.SMALL_FISH_MUSCLE, CCItems.SMALL_FISH_MUSCLE.getMaxCount()));
        chestCavity.setStack(26, new ItemStack(CCItems.SMALL_FISH_MUSCLE, CCItems.SMALL_FISH_MUSCLE.getMaxCount()));
    }

    @Override
    public void generateRareOrganDrops(Random random, int looting, List<ItemStack> loot) {
        LinkedList<Item> organPile = new LinkedList<>();
        for(int i = 0; i < 4; i++){
            organPile.add(CCItems.SMALL_ANIMAL_RIB);
        }
        for(int i = 0; i < 8; i++){
            organPile.add(CCItems.SMALL_FISH_MUSCLE);
        }
        for(int i = 0; i < 4; i++){
            organPile.add(CCItems.SMALL_ANIMAL_INTESTINE);
        }
        organPile.add(CCItems.SMALL_ANIMAL_APPENDIX);
        organPile.add(CCItems.SMALL_ANIMAL_HEART);
        organPile.add(CCItems.SMALL_ANIMAL_KIDNEY);
        organPile.add(CCItems.SMALL_ANIMAL_KIDNEY);
        organPile.add(CCItems.SMALL_ANIMAL_LIVER);
        organPile.add(CCItems.SMALL_GILLS);
        organPile.add(CCItems.SMALL_GILLS);
        organPile.add(CCItems.SMALL_ANIMAL_SPINE);
        organPile.add(CCItems.SMALL_ANIMAL_SPLEEN);
        organPile.add(CCItems.SMALL_ANIMAL_STOMACH);
        organPile.add(CCItems.VENOM_GLAND);
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
