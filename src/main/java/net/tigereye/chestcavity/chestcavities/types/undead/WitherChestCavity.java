package net.tigereye.chestcavity.chestcavities.types.undead;

import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.chestcavities.types.BaseChestCavity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WitherChestCavity extends BaseChestCavity implements ChestCavityType {
    protected static final float heartbleedCap = 5f;

    @Override
    public void fillChestCavityInventory(ChestCavityInventory chestCavity) {
        chestCavity.clear();
        chestCavity.setStack(0, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(1, new ItemStack(CCItems.WITHERED_RIB, CCItems.WITHERED_RIB.getMaxCount()));
        chestCavity.setStack(2, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(3, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(4, new ItemStack(Items.NETHER_STAR, 1));
        chestCavity.setStack(5, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(6, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(7, new ItemStack(CCItems.WITHERED_RIB, CCItems.WITHERED_RIB.getMaxCount()));
        chestCavity.setStack(8, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(9, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(10, new ItemStack(CCItems.WITHERED_RIB, CCItems.WITHERED_RIB.getMaxCount()));
        chestCavity.setStack(11, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(12, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(13, new ItemStack(CCItems.WITHERED_SPINE, CCItems.WITHERED_SPINE.getMaxCount()));
        chestCavity.setStack(14, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(15, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(16, new ItemStack(CCItems.WITHERED_RIB, CCItems.WITHERED_RIB.getMaxCount()));
        chestCavity.setStack(17, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(18, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(19, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(20, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(21, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(22, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(23, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(24, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(25, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
        chestCavity.setStack(26, new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
    }

    @Override
    public boolean catchExceptionalOrgan(ItemStack slot, Map<Identifier, Float> organScores){
        if(slot.getItem() == Items.NETHER_STAR){
            ChestCavityUtil.addOrganScore(CCOrganScores.HEALTH, 1f*slot.getCount(),organScores);
            return true;
        }
        return false;
    }

    @Override
    public void loadBaseOrganScores(Map<Identifier, Float> organScores){
        organScores.clear();
        organScores.put(CCOrganScores.LUCK, 1f);
        organScores.put(CCOrganScores.DEFENSE, 2.375f);
        organScores.put(CCOrganScores.NUTRITION, 4f);
        organScores.put(CCOrganScores.FILTRATION, 2f);
        organScores.put(CCOrganScores.DETOXIFICATION, 1f);
        organScores.put(CCOrganScores.NERVOUS_SYSTEM, .5f);
        organScores.put(CCOrganScores.METABOLISM, 1f);
        organScores.put(CCOrganScores.DIGESTION, 1f);
        organScores.put(CCOrganScores.BREATH, 2f);
    }

    @Override
    public void setOrganCompatibility(ChestCavityInstance cc){
        //the wither is guaranteed to have 1-3 compatible organs
        for(int i = 0; i < cc.inventory.size();i++){
            ItemStack itemStack = cc.inventory.getStack(i);
            if(itemStack != null && itemStack != ItemStack.EMPTY){
                CompoundTag tag = new CompoundTag();
                tag.putUuid("owner",cc.owner.getUuid());
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
            }
            universalOrgans--;
        }
    }

    @Override
    public void generateRareOrganDrops(Random random, int looting, List<ItemStack> loot) {
        LinkedList<Item> organPile = new LinkedList<>();
        for(int i = 0; i < 4; i++){
            organPile.add(CCItems.WITHERED_RIB);
        }
        organPile.add(CCItems.WITHERED_SPINE);
        int rolls = 1 + random.nextInt(1) + random.nextInt(1);
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

    @Override
    public void generateGuaranteedOrganDrops(Random random, int looting, List<ItemStack> loot) {
        int soulsandCount = 16 + random.nextInt(4 + 4*looting) + random.nextInt(4 + 4*looting) + random.nextInt(4 + 4*looting) + random.nextInt(4 + 4*looting);
        while(soulsandCount > CCItems.WRITHING_SOULSAND.getMaxCount()){
            loot.add(new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
            soulsandCount -= CCItems.WRITHING_SOULSAND.getMaxCount();
        }
        loot.add(new ItemStack(CCItems.WRITHING_SOULSAND, soulsandCount));
    }

    @Override
    public float getHeartBleedCap(){
        return heartbleedCap;
    }

    @Override
    public boolean isOpenable(ChestCavityInstance cc){
        if(cc.owner instanceof WitherEntity){
            return ((WitherEntity)cc.owner).getInvulnerableTimer() <= 0;
        }
        return true;
    }

}
