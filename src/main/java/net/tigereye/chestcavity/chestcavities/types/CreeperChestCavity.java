package net.tigereye.chestcavity.chestcavities.types;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class CreeperChestCavity extends BaseChestCavity implements ChestCavityType {
    @Override
    public void fillChestCavityInventory(ChestCavityInventory chestCavity) {
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
    public boolean catchExceptionalOrgan(ItemStack slot, Map<Identifier, Float> organScores){
        //creepers are plant monsters, using leaves for flesh and wood for bone
        if(slot.getItem().isIn(ItemTags.LEAVES)){
            ChestCavityUtil.addOrganScore(CCOrganScores.STRENGTH, 4f*slot.getCount()/slot.getMaxCount(),organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.SPEED, 4f*slot.getCount()/slot.getMaxCount(),organScores);
            return true;
        }
        if(slot.getItem() == Items.STICK){
            ChestCavityUtil.addOrganScore(CCOrganScores.DEFENSE, .25f*slot.getCount(),organScores);
            return true;
        }
        if(slot.getItem().isIn(ItemTags.LOGS)){
            ChestCavityUtil.addOrganScore(CCOrganScores.DEFENSE, .75f*slot.getCount(),organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.NERVOUS_SYSTEM, 1f*slot.getCount(),organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.HEALTH, 1f*slot.getCount(),organScores);
            return true;
        }
        return false;
    }

    @Override
    public void generateRareOrganDrops(Random random, int looting, List<ItemStack> loot) {
        loot.add(new ItemStack(CCItems.CREEPER_APPENDIX));
    }

}
