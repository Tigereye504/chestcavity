package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.*;

public class CaveSpiderChestCavityManager extends ChestCavityManager{


    public CaveSpiderChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public CaveSpiderChestCavityManager(LivingEntity owner, int size) {
        super(owner,size);
    }

    protected static final Map<Identifier,Float> defaultOrganScores = new HashMap<>();

    static{
        initializeDefaultOrganScores();
    }

    private static void initializeDefaultOrganScores(){
        defaultOrganScores.put(CCOrganScores.HEALTH,1.5f);
        defaultOrganScores.put(CCOrganScores.NUTRITION,5.25f);
        defaultOrganScores.put(CCOrganScores.BREATH,2.25f);
        defaultOrganScores.put(CCOrganScores.ENDURANCE,2.25f);
        defaultOrganScores.put(CCOrganScores.NERVOUS_SYSTEM,1.25f);
        defaultOrganScores.put(CCOrganScores.STRENGTH,4f);
        defaultOrganScores.put(CCOrganScores.SPEED,8f);
        defaultOrganScores.put(CCOrganScores.DIGESTION,1.75f);
        defaultOrganScores.put(CCOrganScores.VENOMOUS,1f);
        defaultOrganScores.put(CCOrganScores.SILK,1f);
    }

    @Override
    public Map<Identifier,Float> getDefaultOrganScores(){
        return defaultOrganScores;
    }

    @Override
    public void fillChestCavityInventory() {
        chestCavity.clear();
        chestCavity.setStack(0, new ItemStack(CCItems.INSECT_MUSCLE, CCItems.INSECT_MUSCLE.getMaxCount()));
        chestCavity.setStack(1, new ItemStack(CCItems.INSECT_HEART, CCItems.INSECT_HEART.getMaxCount()));
        chestCavity.setStack(2, new ItemStack(CCItems.INSECT_HEART, CCItems.INSECT_HEART.getMaxCount()));
        chestCavity.setStack(3, new ItemStack(CCItems.INSECT_CAECA, CCItems.INSECT_CAECA.getMaxCount()));
        chestCavity.setStack(4, new ItemStack(CCItems.INSECT_CAECA, CCItems.INSECT_CAECA.getMaxCount()));
        chestCavity.setStack(5, new ItemStack(CCItems.INSECT_CAECA, CCItems.INSECT_CAECA.getMaxCount()));
        chestCavity.setStack(6, new ItemStack(CCItems.INSECT_CAECA, CCItems.INSECT_CAECA.getMaxCount()));
        chestCavity.setStack(7, new ItemStack(CCItems.INSECT_CAECA, CCItems.INSECT_CAECA.getMaxCount()));
        chestCavity.setStack(8, new ItemStack(CCItems.INSECT_MUSCLE, CCItems.INSECT_MUSCLE.getMaxCount()));

        chestCavity.setStack(9, new ItemStack(CCItems.INSECT_MUSCLE, CCItems.INSECT_MUSCLE.getMaxCount()));
        chestCavity.setStack(10, new ItemStack(CCItems.INSECT_HEART, CCItems.INSECT_HEART.getMaxCount()));
        chestCavity.setStack(11, new ItemStack(CCItems.INSECT_STOMACH, CCItems.INSECT_STOMACH.getMaxCount()));
        chestCavity.setStack(12, new ItemStack(CCItems.INSECT_INTESTINE, CCItems.INSECT_INTESTINE.getMaxCount()));
        chestCavity.setStack(13, new ItemStack(CCItems.INSECT_INTESTINE, CCItems.INSECT_INTESTINE.getMaxCount()));
        chestCavity.setStack(14, new ItemStack(CCItems.INSECT_INTESTINE, CCItems.INSECT_INTESTINE.getMaxCount()));
        chestCavity.setStack(15, new ItemStack(CCItems.INSECT_INTESTINE, CCItems.INSECT_INTESTINE.getMaxCount()));
        chestCavity.setStack(16, new ItemStack(CCItems.INSECT_INTESTINE, CCItems.INSECT_INTESTINE.getMaxCount()));
        chestCavity.setStack(17, new ItemStack(CCItems.INSECT_MUSCLE, CCItems.INSECT_MUSCLE.getMaxCount()));

        chestCavity.setStack(18, new ItemStack(CCItems.VENOM_GLAND, CCItems.VENOM_GLAND.getMaxCount()));
        chestCavity.setStack(19, new ItemStack(CCItems.INSECT_MUSCLE, CCItems.INSECT_MUSCLE.getMaxCount()));
        chestCavity.setStack(20, new ItemStack(CCItems.INSECT_LUNG, CCItems.INSECT_LUNG.getMaxCount()));
        chestCavity.setStack(21, new ItemStack(CCItems.INSECT_LUNG, CCItems.INSECT_LUNG.getMaxCount()));
        chestCavity.setStack(22, new ItemStack(CCItems.INSECT_LUNG, CCItems.INSECT_LUNG.getMaxCount()));
        chestCavity.setStack(23, new ItemStack(CCItems.INSECT_MUSCLE, CCItems.INSECT_MUSCLE.getMaxCount()));
        chestCavity.setStack(24, new ItemStack(CCItems.INSECT_MUSCLE, CCItems.INSECT_MUSCLE.getMaxCount()));
        chestCavity.setStack(25, new ItemStack(CCItems.INSECT_MUSCLE, CCItems.INSECT_MUSCLE.getMaxCount()));
        chestCavity.setStack(26, new ItemStack(CCItems.SILK_GLAND, CCItems.SILK_GLAND.getMaxCount()));
    }

    @Override
    public List<ItemStack> generateLootDrops(Random random, int looting){
        List<ItemStack> loot = new ArrayList<>();
        if(random.nextFloat() < ChestCavity.config.ORGAN_BUNDLE_DROP_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*looting)) {
            LinkedList<Item> organPile = new LinkedList<>();
            for(int i = 0; i < 3; i++){
                organPile.add(CCItems.INSECT_HEART);
            }
            for(int i = 0; i < 8; i++){
                organPile.add(CCItems.INSECT_MUSCLE);
            }
            for(int i = 0; i < 5; i++){
                organPile.add(CCItems.INSECT_INTESTINE);
            }
            for(int i = 0; i < 5; i++){
                organPile.add(CCItems.INSECT_CAECA);
            }
            organPile.add(CCItems.SILK_GLAND);
            organPile.add(CCItems.SILK_GLAND);
            organPile.add(CCItems.INSECT_STOMACH);
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
        return loot;
    }
}
