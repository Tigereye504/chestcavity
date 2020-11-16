package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.HashMap;
import java.util.Map;

public class SnowGolemChestCavityManager extends ChestCavityManager{


    public SnowGolemChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public SnowGolemChestCavityManager(LivingEntity owner, int size) {
        super(owner,size);
    }

    protected static final Map<Identifier,Float> defaultOrganScores = new HashMap<>();

    static{
        initializeDefaultOrganScores();
    }

    private static void initializeDefaultOrganScores(){
        defaultOrganScores.put(CCOrganScores.BONE,4.75f);
        defaultOrganScores.put(CCOrganScores.HEART,1f);
        defaultOrganScores.put(CCOrganScores.STRENGTH,8f);
        defaultOrganScores.put(CCOrganScores.SPEED,8f);
        defaultOrganScores.put(CCOrganScores.NERVOUS_SYSTEM,1f);
    }

    @Override
    public Map<Identifier,Float> getDefaultOrganScores(){
        return defaultOrganScores;
    }

    @Override
    public void fillChestCavityInventory() {
        chestCavity.clear();
        chestCavity.setStack(0, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(1, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(2, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(3, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(4, new ItemStack(Items.CHARCOAL, 1));
        chestCavity.setStack(5, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(6, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(7, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(8, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(9, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(10, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(11, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(12, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(13, new ItemStack(Items.CHARCOAL, 1));
        chestCavity.setStack(14, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(15, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(16, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(17, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(18, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(19, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(20, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(21, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(22, new ItemStack(Items.CHARCOAL, 1));
        chestCavity.setStack(23, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(24, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(25, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
        chestCavity.setStack(26, new ItemStack(Items.SNOWBALL, Items.SNOWBALL.getMaxCount()));
    }

    @Override
    protected boolean catchExceptionalOrgan(ItemStack slot){
        //charcoal functions as heart and bone and spine
        //snowballs function as muscle
        if(slot.getItem() == Items.SNOWBALL){
            addOrganScore(CCOrganScores.STRENGTH, slot.getCount()/(3f*slot.getMaxCount()));
            addOrganScore(CCOrganScores.SPEED, slot.getCount()/(3f*slot.getMaxCount()));
            return true;
        }
        if(slot.getItem() == Items.CHARCOAL){
            addOrganScore(CCOrganScores.HEART, slot.getCount()*(1f/3));
            addOrganScore(CCOrganScores.BONE, slot.getCount()*(4.75f/3));
            addOrganScore(CCOrganScores.NERVOUS_SYSTEM, slot.getCount()*(1f/3));
            return true;
        }
        if(slot.getItem() == Items.SNOW_BLOCK){
            addOrganScore(CCOrganScores.STRENGTH, slot.getCount()/(1f*slot.getMaxCount()));
            addOrganScore(CCOrganScores.SPEED, slot.getCount()/(1f*slot.getMaxCount()));
            return true;
        }
        return false;
    }

    @Override
    protected void resetOrganScores(){
        //snow golems dont breath, don't eat, and dont have blood
        //as such they don't need the organs related to such
        //that is almost all the organs though...
        organScores.clear();
        organScores.put(CCOrganScores.APPENDIX, 1f);
        organScores.put(CCOrganScores.INTESTINE, 4f);
        organScores.put(CCOrganScores.KIDNEY, 2f);
        organScores.put(CCOrganScores.LIVER, 1f);
        organScores.put(CCOrganScores.SPLEEN, 1f);
        organScores.put(CCOrganScores.STOMACH, 1f);
        organScores.put(CCOrganScores.LUNG, 2f);
    }
}
