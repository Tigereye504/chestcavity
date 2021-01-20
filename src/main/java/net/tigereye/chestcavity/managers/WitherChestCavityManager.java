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

import java.util.*;

public class WitherChestCavityManager extends ChestCavityManager{


    public WitherChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public WitherChestCavityManager(LivingEntity owner, int size) {
        super(owner,size);
    }

    protected static final Map<Identifier,Float> defaultOrganScores = new HashMap<>();
    protected static final float heartbleedFactor = .2f;

    static{
        initializeDefaultOrganScores();
    }

    private static void initializeDefaultOrganScores(){
        defaultOrganScores.put(CCOrganScores.APPENDIX,1f);
        defaultOrganScores.put(CCOrganScores.DEFENSE,4.75f);
        defaultOrganScores.put(CCOrganScores.HEALTH,1f);
        defaultOrganScores.put(CCOrganScores.NUTRITION,4f);
        defaultOrganScores.put(CCOrganScores.FILTRATION,2f);
        defaultOrganScores.put(CCOrganScores.DETOXIFICATION,1f);
        defaultOrganScores.put(CCOrganScores.BREATH,2f);
        defaultOrganScores.put(CCOrganScores.ENDURANCE,2f);
        defaultOrganScores.put(CCOrganScores.STRENGTH,CCItems.WRITHING_SOULSAND.getOrganQuality(CCOrganScores.STRENGTH)*21);
        defaultOrganScores.put(CCOrganScores.SPEED,CCItems.WRITHING_SOULSAND.getOrganQuality(CCOrganScores.SPEED)*21);
        defaultOrganScores.put(CCOrganScores.NERVOUS_SYSTEM,1f);
        defaultOrganScores.put(CCOrganScores.METABOLISM,1f);
        defaultOrganScores.put(CCOrganScores.DIGESTION,1f);
        defaultOrganScores.put(CCOrganScores.WITHERED,5f);
    }

    @Override
    public void fillChestCavityInventory() {
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
    protected boolean catchExceptionalOrgan(ItemStack slot){
        //creepers are plant monsters, using leaves for flesh and wood for bone
        if(slot.getItem() == Items.NETHER_STAR){
            addOrganScore(CCOrganScores.HEALTH, 1f*slot.getCount());
            return true;
        }
        return false;
    }

    @Override
    protected void resetOrganScores(){
        //animated by unholy magic, withers get to pretend they have organs they actually don't!
        //they also don't have any organs, but imagine if they did...
        organScores.clear();
        organScores.put(CCOrganScores.APPENDIX, 1f);
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
    public List<ItemStack> generateLootDrops(Random random, int looting){
        List<ItemStack> loot = new ArrayList<>();
        if(random.nextFloat() < ChestCavity.config.ORGAN_BUNDLE_DROP_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*looting)) {
            LinkedList<Item> organPile = new LinkedList<>();
            for(int i = 0; i < 4; i++){
                organPile.add(CCItems.WITHERED_RIB);
            }
            organPile.add(CCItems.WITHERED_SPINE);
            int rolls = random.nextInt(1) + random.nextInt(1+ 8*looting);
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
        int soulsandCount = 16 + random.nextInt(4 + 4*looting) + random.nextInt(4 + 4*looting) + random.nextInt(4 + 4*looting) + random.nextInt(4 + 4*looting);
        while(soulsandCount > CCItems.WRITHING_SOULSAND.getMaxCount()){
            loot.add(new ItemStack(CCItems.WRITHING_SOULSAND, CCItems.WRITHING_SOULSAND.getMaxCount()));
            soulsandCount -= CCItems.WRITHING_SOULSAND.getMaxCount();
        }
        loot.add(new ItemStack(CCItems.WRITHING_SOULSAND, soulsandCount));
        return loot;
    }

    @Override
    public float getHeartbleedFactor(){
        return heartbleedFactor;
    }
}
