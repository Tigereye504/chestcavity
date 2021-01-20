package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.*;

public class CreeperChestCavityManager extends ChestCavityManager{


    public CreeperChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public CreeperChestCavityManager(LivingEntity owner, int size) {
        super(owner,size);
    }

    protected static final Map<Identifier,Float> defaultOrganScores = new HashMap<>();

    static{
        initializeDefaultOrganScores();
    }

    private static void initializeDefaultOrganScores(){
        //creepers are plant creatures, they have no blood
        //they don't need kidneys, livers, intestines, or stomachs
        //they are, however, rather explosive buggers
        defaultOrganScores.put(CCOrganScores.CREEPY,1f);
        defaultOrganScores.put(CCOrganScores.EXPLOSIVE,15f);
        defaultOrganScores.put(CCOrganScores.APPENDIX,.75f);
        defaultOrganScores.put(CCOrganScores.DEFENSE,4.75f);
        defaultOrganScores.put(CCOrganScores.HEALTH,1f);
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
        if(slot.getItem().isIn(ItemTags.LEAVES)){
            addOrganScore(CCOrganScores.STRENGTH, 4f*slot.getCount()/slot.getMaxCount());
            addOrganScore(CCOrganScores.SPEED, 4f*slot.getCount()/slot.getMaxCount());
            return true;
        }
        if(slot.getItem() == Items.STICK){
            addOrganScore(CCOrganScores.DEFENSE, .25f*slot.getCount());
            return true;
        }
        if(slot.getItem().isIn(ItemTags.LOGS)){
            addOrganScore(CCOrganScores.DEFENSE, .75f*slot.getCount());
            addOrganScore(CCOrganScores.NERVOUS_SYSTEM, 1f*slot.getCount());
            addOrganScore(CCOrganScores.HEALTH, 1f*slot.getCount());
            return true;
        }
        return false;
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
