package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCTags;

import java.util.*;

public class RabbitChestCavityManager extends SmallAnimalChestCavityManager{


    public RabbitChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public RabbitChestCavityManager(LivingEntity owner, int size) {
        super(owner,size);
    }

    @Override
    public void fillChestCavityInventory() {
        chestCavity.clear();
        chestCavity.setStack(0, new ItemStack(CCItems.SMALL_ANIMAL_MUSCLE, CCItems.SMALL_ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(1, new ItemStack(CCItems.SMALL_ANIMAL_RIB, CCItems.SMALL_ANIMAL_RIB.getMaxCount()));
        chestCavity.setStack(2, new ItemStack(CCItems.SMALL_ANIMAL_APPENDIX, CCItems.SMALL_ANIMAL_APPENDIX.getMaxCount()));
        chestCavity.setStack(3, new ItemStack(CCItems.SMALL_ANIMAL_LUNG, CCItems.SMALL_ANIMAL_LUNG.getMaxCount()));
        chestCavity.setStack(4, new ItemStack(CCItems.RABBIT_HEART, CCItems.RABBIT_HEART.getMaxCount()));
        chestCavity.setStack(5, new ItemStack(CCItems.SMALL_ANIMAL_LUNG, CCItems.SMALL_ANIMAL_LUNG.getMaxCount()));
        chestCavity.setStack(6, ItemStack.EMPTY);
        chestCavity.setStack(7, new ItemStack(CCItems.SMALL_ANIMAL_RIB, CCItems.SMALL_ANIMAL_RIB.getMaxCount()));
        chestCavity.setStack(8, new ItemStack(CCItems.SMALL_ANIMAL_MUSCLE, CCItems.SMALL_ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(9, new ItemStack(CCItems.SMALL_ANIMAL_MUSCLE, CCItems.SMALL_ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(10, new ItemStack(CCItems.SMALL_ANIMAL_RIB, CCItems.SMALL_ANIMAL_RIB.getMaxCount()));
        chestCavity.setStack(11, new ItemStack(CCItems.SMALL_ANIMAL_SPLEEN, CCItems.SMALL_ANIMAL_SPLEEN.getMaxCount()));
        chestCavity.setStack(12, new ItemStack(CCItems.SMALL_ANIMAL_KIDNEY, CCItems.SMALL_ANIMAL_KIDNEY.getMaxCount()));
        chestCavity.setStack(13, new ItemStack(CCItems.SMALL_ANIMAL_SPINE, CCItems.SMALL_ANIMAL_SPINE.getMaxCount()));
        chestCavity.setStack(14, new ItemStack(CCItems.SMALL_ANIMAL_KIDNEY, CCItems.SMALL_ANIMAL_KIDNEY.getMaxCount()));
        chestCavity.setStack(15, new ItemStack(CCItems.SMALL_ANIMAL_LIVER, CCItems.SMALL_ANIMAL_LIVER.getMaxCount()));
        chestCavity.setStack(16, new ItemStack(CCItems.SMALL_ANIMAL_RIB, CCItems.SMALL_ANIMAL_RIB.getMaxCount()));
        chestCavity.setStack(17, new ItemStack(CCItems.SMALL_ANIMAL_MUSCLE, CCItems.SMALL_ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(18, new ItemStack(CCItems.SMALL_ANIMAL_MUSCLE, CCItems.SMALL_ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(19, new ItemStack(CCItems.SMALL_ANIMAL_MUSCLE, CCItems.SMALL_ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(20, new ItemStack(CCItems.SMALL_ANIMAL_INTESTINE, CCItems.SMALL_ANIMAL_INTESTINE.getMaxCount()));
        chestCavity.setStack(21, new ItemStack(CCItems.SMALL_ANIMAL_INTESTINE, CCItems.SMALL_ANIMAL_INTESTINE.getMaxCount()));
        chestCavity.setStack(22, new ItemStack(CCItems.SMALL_ANIMAL_STOMACH, CCItems.SMALL_ANIMAL_STOMACH.getMaxCount()));
        chestCavity.setStack(23, new ItemStack(CCItems.SMALL_ANIMAL_INTESTINE, CCItems.SMALL_ANIMAL_INTESTINE.getMaxCount()));
        chestCavity.setStack(24, new ItemStack(CCItems.SMALL_ANIMAL_INTESTINE, CCItems.SMALL_ANIMAL_INTESTINE.getMaxCount()));
        chestCavity.setStack(25, new ItemStack(CCItems.SMALL_ANIMAL_MUSCLE, CCItems.SMALL_ANIMAL_MUSCLE.getMaxCount()));
        chestCavity.setStack(26, new ItemStack(CCItems.SMALL_ANIMAL_MUSCLE, CCItems.SMALL_ANIMAL_MUSCLE.getMaxCount()));
    }

    @Override
    protected boolean catchExceptionalOrgan(ItemStack slot){
        //rabbits don't get the special speed boost from rabbit hearts
        if(slot.getItem() == CCItems.RABBIT_HEART){
            addOrganScore(CCOrganScores.HEART,1f*slot.getCount()/slot.getMaxCount());
            return true;
        }
        return super.catchExceptionalOrgan(slot); //still, they are small animals
    }

    @Override
    public List<ItemStack> generateLootDrops(Random random, int looting){
        List<ItemStack> loot = new ArrayList<>();
        if(random.nextFloat() < ChestCavity.config.ORGAN_BUNDLE_DROP_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*looting)) {
            LinkedList<Item> organPile = new LinkedList<>();
            for(int i = 0; i < 4; i++){
                organPile.add(CCItems.SMALL_ANIMAL_RIB);
            }
            for(int i = 0; i < 8; i++){
                organPile.add(CCItems.SMALL_ANIMAL_MUSCLE);
            }
            for(int i = 0; i < 4; i++){
                organPile.add(CCItems.SMALL_ANIMAL_INTESTINE);
            }
            organPile.add(CCItems.SMALL_ANIMAL_APPENDIX);
            organPile.add(CCItems.RABBIT_HEART);
            organPile.add(CCItems.SMALL_ANIMAL_KIDNEY);
            organPile.add(CCItems.SMALL_ANIMAL_KIDNEY);
            organPile.add(CCItems.SMALL_ANIMAL_LIVER);
            organPile.add(CCItems.SMALL_ANIMAL_LUNG);
            organPile.add(CCItems.SMALL_ANIMAL_LUNG);
            organPile.add(CCItems.SMALL_ANIMAL_SPINE);
            organPile.add(CCItems.SMALL_ANIMAL_SPLEEN);
            organPile.add(CCItems.SMALL_ANIMAL_STOMACH);
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
