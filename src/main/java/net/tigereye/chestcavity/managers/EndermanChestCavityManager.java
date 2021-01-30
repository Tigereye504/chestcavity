package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.*;

public class EndermanChestCavityManager extends ChestCavityManager{


    public EndermanChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public EndermanChestCavityManager(LivingEntity owner, int size) {
        super(owner,size);
    }

    protected static final Map<Identifier,Float> defaultOrganScores = new HashMap<>();

    static{
        initializeDefaultOrganScores();
    }

    private static void initializeDefaultOrganScores(){
        defaultOrganScores.put(CCOrganScores.LUCK,1.25f);
        defaultOrganScores.put(CCOrganScores.ARROW_DODGING,1f);
        defaultOrganScores.put(CCOrganScores.DEFENSE,5.9375f);
        defaultOrganScores.put(CCOrganScores.HEALTH,1.25f);
        defaultOrganScores.put(CCOrganScores.HYDROALLERGENIC,26f);
        defaultOrganScores.put(CCOrganScores.HYDROPHOBIA,2f);
        defaultOrganScores.put(CCOrganScores.NUTRITION,5f);
        defaultOrganScores.put(CCOrganScores.FILTRATION,2.5f);
        defaultOrganScores.put(CCOrganScores.DETOXIFICATION,1.25f);
        defaultOrganScores.put(CCOrganScores.BREATH,2.5f);
        defaultOrganScores.put(CCOrganScores.ENDURANCE,2.5f);
        defaultOrganScores.put(CCOrganScores.STRENGTH,7.5f);
        defaultOrganScores.put(CCOrganScores.SPEED,7.5f);
        defaultOrganScores.put(CCOrganScores.NERVOUS_SYSTEM,1.25f);
        defaultOrganScores.put(CCOrganScores.METABOLISM,1.25f);
        defaultOrganScores.put(CCOrganScores.DIGESTION,1.25f);
    }

    @Override
    public Map<Identifier,Float> getDefaultOrganScores(){
        return defaultOrganScores;
    }

    @Override
    public void fillChestCavityInventory() {
        chestCavity.clear();
        chestCavity.setStack(0, new ItemStack(CCItems.ENDER_MUSCLE, CCItems.ENDER_MUSCLE.getMaxCount()));
        chestCavity.setStack(1, new ItemStack(CCItems.ENDER_RIB, CCItems.ENDER_RIB.getMaxCount()));
        chestCavity.setStack(2, new ItemStack(CCItems.ENDER_APPENDIX, CCItems.ENDER_APPENDIX.getMaxCount()));
        chestCavity.setStack(3, new ItemStack(CCItems.ENDER_LUNG, CCItems.ENDER_LUNG.getMaxCount()));
        chestCavity.setStack(4, new ItemStack(CCItems.ENDER_HEART, CCItems.ENDER_HEART.getMaxCount()));
        chestCavity.setStack(5, new ItemStack(CCItems.ENDER_LUNG, CCItems.ENDER_LUNG.getMaxCount()));
        chestCavity.setStack(6, ItemStack.EMPTY);
        chestCavity.setStack(7, new ItemStack(CCItems.ENDER_RIB, CCItems.ENDER_RIB.getMaxCount()));
        chestCavity.setStack(8, new ItemStack(CCItems.ENDER_MUSCLE, CCItems.ENDER_MUSCLE.getMaxCount()));
        chestCavity.setStack(9, new ItemStack(CCItems.ENDER_MUSCLE, CCItems.ENDER_MUSCLE.getMaxCount()));
        chestCavity.setStack(10, new ItemStack(CCItems.ENDER_RIB, CCItems.ENDER_RIB.getMaxCount()));
        chestCavity.setStack(11, new ItemStack(CCItems.ENDER_SPLEEN, CCItems.ENDER_SPLEEN.getMaxCount()));
        chestCavity.setStack(12, new ItemStack(CCItems.ENDER_KIDNEY, CCItems.ENDER_KIDNEY.getMaxCount()));
        chestCavity.setStack(13, new ItemStack(CCItems.ENDER_SPINE, CCItems.ENDER_SPINE.getMaxCount()));
        chestCavity.setStack(14, new ItemStack(CCItems.ENDER_KIDNEY, CCItems.ENDER_KIDNEY.getMaxCount()));
        chestCavity.setStack(15, new ItemStack(CCItems.ENDER_LIVER, CCItems.ENDER_LIVER.getMaxCount()));
        chestCavity.setStack(16, new ItemStack(CCItems.ENDER_RIB, CCItems.ENDER_RIB.getMaxCount()));
        chestCavity.setStack(17, new ItemStack(CCItems.ENDER_MUSCLE, CCItems.ENDER_MUSCLE.getMaxCount()));
        chestCavity.setStack(18, new ItemStack(CCItems.ENDER_MUSCLE, CCItems.ENDER_MUSCLE.getMaxCount()));
        chestCavity.setStack(19, new ItemStack(CCItems.ENDER_MUSCLE, CCItems.ENDER_MUSCLE.getMaxCount()));
        chestCavity.setStack(20, new ItemStack(CCItems.ENDER_INTESTINE, CCItems.ENDER_INTESTINE.getMaxCount()));
        chestCavity.setStack(21, new ItemStack(CCItems.ENDER_INTESTINE, CCItems.ENDER_INTESTINE.getMaxCount()));
        chestCavity.setStack(22, new ItemStack(CCItems.ENDER_STOMACH, CCItems.ENDER_STOMACH.getMaxCount()));
        chestCavity.setStack(23, new ItemStack(CCItems.ENDER_INTESTINE, CCItems.ENDER_INTESTINE.getMaxCount()));
        chestCavity.setStack(24, new ItemStack(CCItems.ENDER_INTESTINE, CCItems.ENDER_INTESTINE.getMaxCount()));
        chestCavity.setStack(25, new ItemStack(CCItems.ENDER_MUSCLE, CCItems.ENDER_MUSCLE.getMaxCount()));
        chestCavity.setStack(26, new ItemStack(CCItems.ENDER_MUSCLE, CCItems.ENDER_MUSCLE.getMaxCount()));
    }

    @Override
    protected void generateRareOrganDrops(Random random, int looting, List<ItemStack> loot) {LinkedList<Item> organPile = new LinkedList<>();
        for(int i = 0; i < 4; i++){
            organPile.add(CCItems.ENDER_RIB);
        }
        for(int i = 0; i < 8; i++){
            organPile.add(CCItems.ENDER_MUSCLE);
        }
        for(int i = 0; i < 4; i++){
            organPile.add(CCItems.ENDER_INTESTINE);
        }
        organPile.add(CCItems.ENDER_APPENDIX);
        organPile.add(CCItems.ENDER_HEART);
        organPile.add(CCItems.ENDER_KIDNEY);
        organPile.add(CCItems.ENDER_KIDNEY);
        organPile.add(CCItems.ENDER_LIVER);
        organPile.add(CCItems.ENDER_LUNG);
        organPile.add(CCItems.ENDER_LUNG);
        organPile.add(CCItems.ENDER_SPINE);
        organPile.add(CCItems.ENDER_SPLEEN);
        organPile.add(CCItems.ENDER_STOMACH);
        int rolls = 1 + random.nextInt(3) + random.nextInt(3);
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
}
