package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.tigereye.chestcavity.registration.CCOrganScores;

public class SlimeChestCavityManager extends ChestCavityManager{


    public SlimeChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public SlimeChestCavityManager(LivingEntity owner,int size) {
        super(owner,size);
    }

    @Override
    public void fillChestCavityInventory() {
        chestCavity.clear();
        chestCavity.setStack(0, ItemStack.EMPTY);
        chestCavity.setStack(1, ItemStack.EMPTY);
        chestCavity.setStack(2, ItemStack.EMPTY);
        chestCavity.setStack(3, ItemStack.EMPTY);
        chestCavity.setStack(4, new ItemStack(Items.SLIME_BALL, 1));
        for(int i = 5; i < chestCavity.size(); i++){
            chestCavity.setStack(i, ItemStack.EMPTY);
        }
    }

    @Override
    protected void resetOrganScores(){
        //slimes are amorphous goo, they don't really have or need organs
        organScores.clear();
        organScores.put(CCOrganScores.APPENDIX, 1f);
        organScores.put(CCOrganScores.BONE, 3.75f);
        organScores.put(CCOrganScores.HEART, 0.5f);
        organScores.put(CCOrganScores.INTESTINE, 4f);
        organScores.put(CCOrganScores.KIDNEY, 2f);
        organScores.put(CCOrganScores.LIVER, 1f);
        organScores.put(CCOrganScores.STRENGTH, 7f);
        organScores.put(CCOrganScores.SPEED, 7f);
        organScores.put(CCOrganScores.NERVOUS_SYSTEM, 1f);
        organScores.put(CCOrganScores.SPLEEN, 1f);
        organScores.put(CCOrganScores.STOMACH, 1f);
        organScores.put(CCOrganScores.LUNG, 2f);
    }

    @Override
    protected boolean catchExceptionalOrgan(ItemStack slot){
        if(slot.getItem() == Items.SLIME_BALL){
            organScores.put(CCOrganScores.HEART, organScores.getOrDefault(CCOrganScores.HEART,0f)+(slot.getCount()*.5f));
            organScores.put(CCOrganScores.STRENGTH, organScores.getOrDefault(CCOrganScores.STRENGTH,0f)+slot.getCount());
            organScores.put(CCOrganScores.SPEED, organScores.getOrDefault(CCOrganScores.SPEED,0f)+slot.getCount());
            organScores.put(CCOrganScores.BONE, organScores.getOrDefault(CCOrganScores.BONE,0f)+slot.getCount());
            return true;
        }
        return false;
    }
}
