package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCTags;

import java.util.*;

public class AnimalChestCavityManager extends ChestCavityManager{


    public AnimalChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public AnimalChestCavityManager(LivingEntity owner, int size) {
        super(owner,size);
    }

    @Override
    public void fillChestCavityInventory() {
        chestCavity.clear();
        chestCavity.setStack(0, new ItemStack(CCItems.ANIMAL_MUSCLE, CCItems.ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(1, new ItemStack(CCItems.ANIMAL_RIB, CCItems.ANIMAL_RIB.getMaxCount()));
        chestCavity.setStack(2, new ItemStack(CCItems.ANIMAL_APPENDIX, CCItems.ANIMAL_APPENDIX.getMaxCount()));
        chestCavity.setStack(3, new ItemStack(CCItems.ANIMAL_LUNG, CCItems.ANIMAL_LUNG.getMaxCount()));
        chestCavity.setStack(4, new ItemStack(CCItems.ANIMAL_HEART, CCItems.ANIMAL_HEART.getMaxCount()));
        chestCavity.setStack(5, new ItemStack(CCItems.ANIMAL_LUNG, CCItems.ANIMAL_LUNG.getMaxCount()));
        chestCavity.setStack(6, ItemStack.EMPTY);
        chestCavity.setStack(7, new ItemStack(CCItems.ANIMAL_RIB, CCItems.ANIMAL_RIB.getMaxCount()));
        chestCavity.setStack(8, new ItemStack(CCItems.ANIMAL_MUSCLE, CCItems.ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(9, new ItemStack(CCItems.ANIMAL_MUSCLE, CCItems.ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(10, new ItemStack(CCItems.ANIMAL_RIB, CCItems.ANIMAL_RIB.getMaxCount()));
        chestCavity.setStack(11, new ItemStack(CCItems.ANIMAL_SPLEEN, CCItems.ANIMAL_SPLEEN.getMaxCount()));
        chestCavity.setStack(12, new ItemStack(CCItems.ANIMAL_KIDNEY, CCItems.ANIMAL_KIDNEY.getMaxCount()));
        chestCavity.setStack(13, new ItemStack(CCItems.ANIMAL_SPINE, CCItems.ANIMAL_SPINE.getMaxCount()));
        chestCavity.setStack(14, new ItemStack(CCItems.ANIMAL_KIDNEY, CCItems.ANIMAL_KIDNEY.getMaxCount()));
        chestCavity.setStack(15, new ItemStack(CCItems.ANIMAL_LIVER, CCItems.ANIMAL_LIVER.getMaxCount()));
        chestCavity.setStack(16, new ItemStack(CCItems.ANIMAL_RIB, CCItems.ANIMAL_RIB.getMaxCount()));
        chestCavity.setStack(17, new ItemStack(CCItems.ANIMAL_MUSCLE, CCItems.ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(18, new ItemStack(CCItems.ANIMAL_MUSCLE, CCItems.ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(19, new ItemStack(CCItems.ANIMAL_MUSCLE, CCItems.ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(20, new ItemStack(CCItems.ANIMAL_INTESTINE, CCItems.ANIMAL_INTESTINE.getMaxCount()));
        chestCavity.setStack(21, new ItemStack(CCItems.ANIMAL_INTESTINE, CCItems.ANIMAL_INTESTINE.getMaxCount()));
        chestCavity.setStack(22, new ItemStack(CCItems.ANIMAL_STOMACH, CCItems.ANIMAL_STOMACH.getMaxCount()));
        chestCavity.setStack(23, new ItemStack(CCItems.ANIMAL_INTESTINE, CCItems.ANIMAL_INTESTINE.getMaxCount()));
        chestCavity.setStack(24, new ItemStack(CCItems.ANIMAL_INTESTINE, CCItems.ANIMAL_INTESTINE.getMaxCount()));
        chestCavity.setStack(25, new ItemStack(CCItems.ANIMAL_MUSCLE, CCItems.ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(26, new ItemStack(CCItems.ANIMAL_MUSCLE, CCItems.ANIMAL_MUSCLE.getMaxCount()));
    }

    @Override
    protected boolean catchExceptionalOrgan(ItemStack slot){
        //to animals, animal organs are 33.3% more effective so that the usual .75 quality instead counts as 1 quality
        if(slot.getItem().isIn(CCTags.ANIMAL_ORGANS)){
            Map<Identifier,Float> organMap = lookupOrganScore(slot);
            if (lookupOrganScore(slot) != null) {
                organMap.forEach((key,value) ->
                        addOrganScore(key,(value*(4f/3)*slot.getCount()/slot.getMaxCount())));
            }
            return true;
        }
        return false;
    }

    @Override
    public List<ItemStack> generateLootDrops(Random random, int looting){
        List<ItemStack> loot = new ArrayList<>();
        if(random.nextFloat() < ChestCavity.config.ORGAN_BUNDLE_DROP_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*looting)) {
            LinkedList<Item> organPile = new LinkedList<>();
            for(int i = 0; i < 4; i++){
                organPile.add(CCItems.ANIMAL_RIB);
            }
            for(int i = 0; i < 8; i++){
                organPile.add(CCItems.ANIMAL_MUSCLE);
            }
            for(int i = 0; i < 4; i++){
                organPile.add(CCItems.ANIMAL_INTESTINE);
            }
            organPile.add(CCItems.ANIMAL_APPENDIX);
            organPile.add(CCItems.ANIMAL_HEART);
            organPile.add(CCItems.ANIMAL_KIDNEY);
            organPile.add(CCItems.ANIMAL_KIDNEY);
            organPile.add(CCItems.ANIMAL_LIVER);
            organPile.add(CCItems.ANIMAL_LUNG);
            organPile.add(CCItems.ANIMAL_LUNG);
            organPile.add(CCItems.ANIMAL_SPINE);
            organPile.add(CCItems.ANIMAL_SPLEEN);
            organPile.add(CCItems.ANIMAL_STOMACH);
            int rolls = 1 + random.nextInt(3) + random.nextInt(3);
            for (int i = 0; i < rolls; i++){
                int roll = random.nextInt(organPile.size());
                int count = 1;
                Item rolledItem = organPile.get(roll);
                if(rolledItem.getMaxCount() > 1){
                    count += random.nextInt(rolledItem.getMaxCount());
                }
                loot.add(new ItemStack(organPile.remove(roll)));
            }
        }
        return loot;
    }
}
