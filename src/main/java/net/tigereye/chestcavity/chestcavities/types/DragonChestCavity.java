package net.tigereye.chestcavity.chestcavities.types;

import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DragonChestCavity extends BaseChestCavity implements ChestCavityType {
    protected static final float heartbleedCap = 5f;

    @Override
    public void fillChestCavityInventory(ChestCavityInventory chestCavity) {
        chestCavity.clear();
        chestCavity.setStack(0, new ItemStack(CCItems.DRAGON_MUSCLE, CCItems.DRAGON_MUSCLE.getMaxCount()));
        chestCavity.setStack(1, new ItemStack(CCItems.DRAGON_RIB, CCItems.DRAGON_RIB.getMaxCount()));
        chestCavity.setStack(2, new ItemStack(CCItems.DRAGON_APPENDIX, CCItems.DRAGON_APPENDIX.getMaxCount()));
        chestCavity.setStack(3, new ItemStack(CCItems.DRAGON_LUNG, CCItems.DRAGON_LUNG.getMaxCount()));
        chestCavity.setStack(4, new ItemStack(CCItems.DRAGON_HEART, CCItems.DRAGON_HEART.getMaxCount()));
        chestCavity.setStack(5, new ItemStack(CCItems.DRAGON_LUNG, CCItems.DRAGON_LUNG.getMaxCount()));
        chestCavity.setStack(6, ItemStack.EMPTY);
        chestCavity.setStack(7, new ItemStack(CCItems.DRAGON_RIB, CCItems.DRAGON_RIB.getMaxCount()));
        chestCavity.setStack(8, new ItemStack(CCItems.DRAGON_MUSCLE, CCItems.DRAGON_MUSCLE.getMaxCount()));
        chestCavity.setStack(9, new ItemStack(CCItems.DRAGON_MUSCLE, CCItems.DRAGON_MUSCLE.getMaxCount()));
        chestCavity.setStack(10, new ItemStack(CCItems.DRAGON_RIB, CCItems.DRAGON_RIB.getMaxCount()));
        chestCavity.setStack(11, new ItemStack(CCItems.DRAGON_SPLEEN, CCItems.DRAGON_SPLEEN.getMaxCount()));
        chestCavity.setStack(12, new ItemStack(CCItems.DRAGON_KIDNEY, CCItems.DRAGON_KIDNEY.getMaxCount()));
        chestCavity.setStack(13, new ItemStack(CCItems.DRAGON_SPINE, CCItems.DRAGON_SPINE.getMaxCount()));
        chestCavity.setStack(14, new ItemStack(CCItems.DRAGON_KIDNEY, CCItems.DRAGON_KIDNEY.getMaxCount()));
        chestCavity.setStack(15, new ItemStack(CCItems.DRAGON_LIVER, CCItems.DRAGON_LIVER.getMaxCount()));
        chestCavity.setStack(16, new ItemStack(CCItems.DRAGON_RIB, CCItems.DRAGON_RIB.getMaxCount()));
        chestCavity.setStack(17, new ItemStack(CCItems.DRAGON_MUSCLE, CCItems.DRAGON_MUSCLE.getMaxCount()));
        chestCavity.setStack(18, new ItemStack(CCItems.DRAGON_MUSCLE, CCItems.DRAGON_MUSCLE.getMaxCount()));
        chestCavity.setStack(19, new ItemStack(CCItems.DRAGON_MUSCLE, CCItems.DRAGON_MUSCLE.getMaxCount()));
        chestCavity.setStack(20, new ItemStack(CCItems.MANA_REACTOR, CCItems.MANA_REACTOR.getMaxCount()));
        chestCavity.setStack(21, new ItemStack(CCItems.MANA_REACTOR, CCItems.MANA_REACTOR.getMaxCount()));
        chestCavity.setStack(22, new ItemStack(CCItems.MANA_REACTOR, CCItems.MANA_REACTOR.getMaxCount()));
        chestCavity.setStack(23, new ItemStack(CCItems.MANA_REACTOR, CCItems.MANA_REACTOR.getMaxCount()));
        chestCavity.setStack(24, new ItemStack(CCItems.MANA_REACTOR, CCItems.MANA_REACTOR.getMaxCount()));
        chestCavity.setStack(25, new ItemStack(CCItems.DRAGON_MUSCLE, CCItems.DRAGON_MUSCLE.getMaxCount()));
        chestCavity.setStack(26, new ItemStack(CCItems.DRAGON_MUSCLE, CCItems.DRAGON_MUSCLE.getMaxCount()));
    }

    @Override
    public void loadBaseOrganScores(Map<Identifier, Float> organScores){
        organScores.clear();
        organScores.put(CCOrganScores.STRENGTH, 1f);
        organScores.put(CCOrganScores.NERVOUS_SYSTEM, .5f);
        organScores.put(CCOrganScores.BREATH, .25f);
    }

    @Override
    public void setOrganCompatibility(ChestCavityInstance cc){
        //the dragon is guaranteed to have 1-3 compatible organs
        for(int i = 0; i < cc.inventory.size();i++){
            ItemStack itemStack = cc.inventory.getStack(i);
            if(itemStack != null && itemStack != ItemStack.EMPTY){
                CompoundTag tag = new CompoundTag();
                tag.putUuid("owner",cc.compatibility_id);
                tag.putString("name",cc.owner.getDisplayName().getString());
                itemStack.putSubTag(ChestCavity.COMPATIBILITY_TAG.toString(),tag);
            }
        }
        int universalOrgans = 0;
        Random random = cc.owner.getRandom();
        universalOrgans = 1+random.nextInt(2)+random.nextInt(2);

        while(universalOrgans > 0){
            int i = random.nextInt(cc.inventory.size());
            ItemStack itemStack = cc.inventory.getStack(i);
            if(itemStack != null && itemStack != ItemStack.EMPTY){
                itemStack.removeSubTag(ChestCavity.COMPATIBILITY_TAG.toString());
                universalOrgans--;
            }
        }
    }

    @Override
    public void generateGuaranteedOrganDrops(Random random, int looting, List<ItemStack> loot) {
        LinkedList<Item> organPile = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            organPile.add(CCItems.DRAGON_RIB);
        }
        for (int i = 0; i < 8; i++) {
            organPile.add(CCItems.DRAGON_MUSCLE);
        }
        for (int i = 0; i < 5; i++) {
            organPile.add(CCItems.MANA_REACTOR);
        }
        organPile.add(CCItems.DRAGON_APPENDIX);
        organPile.add(CCItems.DRAGON_HEART);
        organPile.add(CCItems.DRAGON_KIDNEY);
        organPile.add(CCItems.DRAGON_KIDNEY);
        organPile.add(CCItems.DRAGON_LIVER);
        organPile.add(CCItems.DRAGON_LUNG);
        organPile.add(CCItems.DRAGON_LUNG);
        organPile.add(CCItems.DRAGON_SPINE);
        organPile.add(CCItems.DRAGON_SPLEEN);
        int rolls = 3 + random.nextInt(2+looting) + random.nextInt(2+looting);
        for (int i = 0; i < rolls; i++) {
            int roll = random.nextInt(organPile.size());
            int count = 1;
            Item rolledItem = organPile.get(roll);
            if (rolledItem.getMaxCount() > 1) {
                count += random.nextInt(rolledItem.getMaxCount());
            }
            loot.add(new ItemStack(organPile.remove(roll), count));
        }
    }

    @Override
    public float getHeartBleedCap(){
        return heartbleedCap;
    }

}
