package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.HashMap;
import java.util.Map;

public class SlimeChestCavityManager extends ChestCavityManager{


    public SlimeChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public SlimeChestCavityManager(LivingEntity owner,int size) {
        super(owner,size);
    }

    protected static final Map<Identifier,Float> defaultOrganScores = new HashMap<>();

    static{
        initializeDefaultOrganScores();
    }

    private static void initializeDefaultOrganScores(){
        defaultOrganScores.put(CCOrganScores.DEFENSE,1f);
        defaultOrganScores.put(CCOrganScores.HEALTH,1f);
        defaultOrganScores.put(CCOrganScores.STRENGTH,1f);
        defaultOrganScores.put(CCOrganScores.SPEED,1f);
    }

    @Override
    public Map<Identifier,Float> getDefaultOrganScores(){
        return defaultOrganScores;
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
        organScores.put(CCOrganScores.HEALTH, 0.5f);
    }

    @Override
    protected boolean catchExceptionalOrgan(ItemStack slot){
        if(slot.getItem() == Items.SLIME_BALL){
            addOrganScore(CCOrganScores.HEALTH, slot.getCount()*.5f);
            addOrganScore(CCOrganScores.STRENGTH, slot.getCount());
            addOrganScore(CCOrganScores.SPEED, slot.getCount());
            addOrganScore(CCOrganScores.DEFENSE, slot.getCount());
            return true;
        }
        return false;
    }
}
