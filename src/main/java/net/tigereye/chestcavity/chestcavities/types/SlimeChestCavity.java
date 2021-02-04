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

public class SlimeChestCavity extends BaseChestCavity implements ChestCavityType {
    protected static final float heartbleedCap = 5f;

    @Override
    public void fillChestCavityInventory(ChestCavityInventory chestCavity) {
        chestCavity.clear();
        chestCavity.setStack(4, new ItemStack(Items.SLIME_BALL, 1));
    }

    @Override
    public boolean catchExceptionalOrgan(ItemStack slot, Map<Identifier, Float> organScores){
        if(slot.getItem() == Items.SLIME_BALL){
            ChestCavityUtil.addOrganScore(CCOrganScores.HEALTH, slot.getCount()*.5f,organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.STRENGTH, slot.getCount(),organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.SPEED, slot.getCount(),organScores);
            ChestCavityUtil.addOrganScore(CCOrganScores.DEFENSE, slot.getCount(),organScores);
            return true;
        }
        return false;
    }

    @Override
    public void loadBaseOrganScores(Map<Identifier, Float> organScores){
        organScores.clear();
        organScores.put(CCOrganScores.HEALTH, 0.5f);
    }
}
