package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.*;

public class WitherSkeletonChestCavityManager extends ChestCavityManager{


    public WitherSkeletonChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public WitherSkeletonChestCavityManager(LivingEntity owner, int size) {
        super(owner,size);
    }

    protected static final Map<Identifier,Float> defaultOrganScores = new HashMap<>();

    static{
        initializeDefaultOrganScores();
    }

    private static void initializeDefaultOrganScores(){
        defaultOrganScores.put(CCOrganScores.LUCK,1f);
        defaultOrganScores.put(CCOrganScores.DEFENSE,4.75f);
        defaultOrganScores.put(CCOrganScores.HEALTH,1f);
        defaultOrganScores.put(CCOrganScores.NUTRITION,4f);
        defaultOrganScores.put(CCOrganScores.FILTRATION,2f);
        defaultOrganScores.put(CCOrganScores.DETOXIFICATION,1f);
        defaultOrganScores.put(CCOrganScores.BREATH,2f);
        defaultOrganScores.put(CCOrganScores.ENDURANCE,2f);
        defaultOrganScores.put(CCOrganScores.STRENGTH,8f);
        defaultOrganScores.put(CCOrganScores.SPEED,8f);
        defaultOrganScores.put(CCOrganScores.NERVOUS_SYSTEM,1f);
        defaultOrganScores.put(CCOrganScores.METABOLISM,1f);
        defaultOrganScores.put(CCOrganScores.DIGESTION,1f);
        defaultOrganScores.put(CCOrganScores.WITHERED,5f);
    }

    @Override
    public void fillChestCavityInventory() {
        chestCavity.clear();
        chestCavity.setStack(0, ItemStack.EMPTY);
        chestCavity.setStack(1, new ItemStack(CCItems.WITHERED_RIB, CCItems.WITHERED_RIB.getMaxCount()));
        chestCavity.setStack(2, ItemStack.EMPTY);
        chestCavity.setStack(3, ItemStack.EMPTY);
        chestCavity.setStack(4, ItemStack.EMPTY);
        chestCavity.setStack(5, ItemStack.EMPTY);
        chestCavity.setStack(6, ItemStack.EMPTY);
        chestCavity.setStack(7, new ItemStack(CCItems.WITHERED_RIB, CCItems.WITHERED_RIB.getMaxCount()));
        chestCavity.setStack(8, ItemStack.EMPTY);
        chestCavity.setStack(9, ItemStack.EMPTY);
        chestCavity.setStack(10, new ItemStack(CCItems.WITHERED_RIB, CCItems.WITHERED_RIB.getMaxCount()));
        chestCavity.setStack(11, ItemStack.EMPTY);
        chestCavity.setStack(12, ItemStack.EMPTY);
        chestCavity.setStack(13, new ItemStack(CCItems.WITHERED_SPINE, CCItems.WITHERED_SPINE.getMaxCount()));
        chestCavity.setStack(14, ItemStack.EMPTY);
        chestCavity.setStack(15, ItemStack.EMPTY);
        chestCavity.setStack(16, new ItemStack(CCItems.WITHERED_RIB, CCItems.WITHERED_RIB.getMaxCount()));
        chestCavity.setStack(17, ItemStack.EMPTY);
        chestCavity.setStack(18, ItemStack.EMPTY);
        chestCavity.setStack(19, ItemStack.EMPTY);
        chestCavity.setStack(20, ItemStack.EMPTY);
        chestCavity.setStack(21, ItemStack.EMPTY);
        chestCavity.setStack(22, ItemStack.EMPTY);
        chestCavity.setStack(23, ItemStack.EMPTY);
        chestCavity.setStack(24, ItemStack.EMPTY);
        chestCavity.setStack(25, ItemStack.EMPTY);
        chestCavity.setStack(26, ItemStack.EMPTY);
    }

    @Override
    protected void resetOrganScores(){
        //animated by unholy magic, skeletons get to pretend they have organs they actually don't!
        //they also don't have any organs, but imagine if they did...
        organScores.clear();
        organScores.put(CCOrganScores.LUCK, 1f);
        organScores.put(CCOrganScores.DEFENSE, 2.375f);
        organScores.put(CCOrganScores.HEALTH, 1f);
        organScores.put(CCOrganScores.NUTRITION, 4f);
        organScores.put(CCOrganScores.FILTRATION, 2f);
        organScores.put(CCOrganScores.DETOXIFICATION, 1f);
        organScores.put(CCOrganScores.STRENGTH, 8f);
        organScores.put(CCOrganScores.SPEED, 8f);
        organScores.put(CCOrganScores.NERVOUS_SYSTEM, .5f);
        organScores.put(CCOrganScores.METABOLISM, 1f);
        organScores.put(CCOrganScores.DIGESTION, 1f);
        organScores.put(CCOrganScores.BREATH, 2f);
    }

    @Override
    protected void generateRareOrganDrops(Random random, int looting, List<ItemStack> loot) {
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
}
