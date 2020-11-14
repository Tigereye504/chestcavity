package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCTags;

import java.util.*;

public class CreeperChestCavityManager extends ChestCavityManager{


    public CreeperChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public CreeperChestCavityManager(LivingEntity owner, int size) {
        super(owner,size);
    }

    @Override
    public void fillChestCavityInventory() {
        chestCavity.clear();
        chestCavity.setStack(0, new ItemStack(Items.OAK_LEAVES, 16));
        chestCavity.setStack(1, new ItemStack(Items.STICK, 4));
        chestCavity.setStack(2, ItemStack.EMPTY);
        chestCavity.setStack(3, ItemStack.EMPTY);
        chestCavity.setStack(4, new ItemStack(CCItems.CREEPER_APPENDIX, CCItems.CREEPER_APPENDIX.getMaxCount()));
        chestCavity.setStack(5, ItemStack.EMPTY);
        chestCavity.setStack(6, ItemStack.EMPTY);
        chestCavity.setStack(7, new ItemStack(Items.STICK, 4));
        chestCavity.setStack(8, new ItemStack(Items.OAK_LEAVES, 16));
        chestCavity.setStack(9, new ItemStack(Items.OAK_LEAVES, 16));
        chestCavity.setStack(10, new ItemStack(Items.STICK, 4));
        chestCavity.setStack(11, ItemStack.EMPTY);
        chestCavity.setStack(12, new ItemStack(Items.GUNPOWDER, 1));
        chestCavity.setStack(13, new ItemStack(Items.OAK_LOG, 1));
        chestCavity.setStack(14, new ItemStack(Items.GUNPOWDER, 1));
        chestCavity.setStack(15, ItemStack.EMPTY);
        chestCavity.setStack(16, new ItemStack(Items.STICK, 4));
        chestCavity.setStack(17, new ItemStack(Items.OAK_LEAVES, 16));
        chestCavity.setStack(18, new ItemStack(Items.OAK_LEAVES, 16));
        chestCavity.setStack(19, new ItemStack(Items.OAK_LEAVES, 16));
        chestCavity.setStack(20, ItemStack.EMPTY);
        chestCavity.setStack(21, new ItemStack(Items.GUNPOWDER, 1));
        chestCavity.setStack(22, new ItemStack(Items.GUNPOWDER, 1));
        chestCavity.setStack(23, new ItemStack(Items.GUNPOWDER, 1));
        chestCavity.setStack(24, ItemStack.EMPTY);
        chestCavity.setStack(25, new ItemStack(Items.OAK_LEAVES, 16));
        chestCavity.setStack(26, new ItemStack(Items.OAK_LEAVES, 16));
    }

    @Override
    protected boolean catchExceptionalOrgan(ItemStack slot){
        //creepers are plant monsters, using leaves for flesh and wood for bone
        //gunpowder is somehow used for the rest of its functions
        if(slot.getItem().isIn(ItemTags.LEAVES)){
            addOrganScore(CCOrganScores.STRENGTH, 4f*slot.getCount()/slot.getMaxCount());
            addOrganScore(CCOrganScores.SPEED, 4f*slot.getCount()/slot.getMaxCount());
            return true;
        }
        if(slot.getItem() == Items.STICK){
            addOrganScore(CCOrganScores.BONE, .25f*slot.getCount());
            return true;
        }
        if(slot.getItem().isIn(ItemTags.LOGS)){
            addOrganScore(CCOrganScores.BONE, .75f*slot.getCount());
            addOrganScore(CCOrganScores.NERVOUS_SYSTEM, 1f*slot.getCount());
            return true;
        }
        if(slot.getItem() == Items.GUNPOWDER){
            addOrganScore(CCOrganScores.EXPLOSIVE, 5f*slot.getCount());
            addOrganScore(CCOrganScores.HEART, (1f/5)*slot.getCount());
            addOrganScore(CCOrganScores.INTESTINE, (4f/5)*slot.getCount());
            addOrganScore(CCOrganScores.LIVER, (1f/5)*slot.getCount());
            addOrganScore(CCOrganScores.SPLEEN, (1f/5)*slot.getCount());
            addOrganScore(CCOrganScores.STOMACH, (1f/5)*slot.getCount());
            addOrganScore(CCOrganScores.LUNG, (2f/5)*slot.getCount());
            return true;
        }
        return false;
    }

    @Override
    protected void resetOrganScores(){
        //creepers are plant creatures, they have no blood
        //they don't need kidneys
        organScores.clear();
        organScores.put(CCOrganScores.KIDNEY, 2f);
    }
    
    @Override
    public List<ItemStack> generateLootDrops(Random random, int looting){
        List<ItemStack> loot = new ArrayList<>();
        if(random.nextFloat() < ChestCavity.config.ORGAN_BUNDLE_DROP_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*looting)) {
            loot.add(new ItemStack(CCItems.CREEPER_APPENDIX));
        }
        return loot;
    }
}
