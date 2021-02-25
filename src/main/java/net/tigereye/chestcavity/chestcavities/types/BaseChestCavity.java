package net.tigereye.chestcavity.chestcavities.types;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.util.ChestCavityUtil;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;

import java.util.*;

public class BaseChestCavity implements ChestCavityType {

    private Map<Identifier,Float> defaultOrganScores = null;
    private ChestCavityInventory defaultChestCavity = new ChestCavityInventory();
    //public boolean[] forbiddenSlots;

    public BaseChestCavity(){
        fillChestCavityInventory(defaultChestCavity);
    }

    @Override
    public Map<Identifier,Float> getDefaultOrganScores(){
        if(defaultOrganScores == null){
            defaultOrganScores = new HashMap<>();
            if(!ChestCavityUtil.determineDefaultOrganScores(this)){
                defaultOrganScores = null;
            }
        }
        return defaultOrganScores;
    }
    @Override
    public float getDefaultOrganScore(Identifier id){return getDefaultOrganScores().getOrDefault(id,0f);}
    @Override
    public ChestCavityInventory getDefaultChestCavity(){return defaultChestCavity;}
    /*
    @Override
    public boolean isSlotForbidden(int index){
        if(forbiddenSlots.length < index){
            return true;
        }
        return forbiddenSlots[index];
    }
    */
    @Override
    public void fillChestCavityInventory(ChestCavityInventory chestCavity) {
        chestCavity.clear();
        for(int i = 0; i < chestCavity.size(); i++){
            chestCavity.setStack(i,new ItemStack(Items.DIRT,64));
        }
    }
    /*
    @Override
    public void shapeChestCavity() {
        forbiddenSlots = new boolean[getDefaultChestCavity().size()];
    }
    */
    @Override
    public void loadBaseOrganScores(Map<Identifier, Float> organScores){
        organScores.clear();
    }

    @Override
    public boolean catchExceptionalOrgan(ItemStack slot, Map<Identifier, Float> organScores){
        return false;
    }

    @Override
    public List<ItemStack> generateLootDrops(Random random, int looting) {
        List<ItemStack> loot = new ArrayList<>();
        if(random.nextFloat() < ChestCavity.config.UNIVERSAL_DONOR_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*looting)) {
            generateRareOrganDrops(random,looting,loot);
        }
        generateGuaranteedOrganDrops(random,looting,loot);
        return loot;
    }
    public void generateRareOrganDrops(Random random, int looting, List<ItemStack> loot){}
    public void generateGuaranteedOrganDrops(Random random, int looting, List<ItemStack> loot){}

    @Override
    public void setOrganCompatibility(ChestCavityInstance instance){
        ChestCavityInventory chestCavity = instance.inventory;
        //first, make all organs personal
        for(int i = 0; i < chestCavity.size();i++){
            ItemStack itemStack = chestCavity.getStack(i);
            if(itemStack != null && itemStack != itemStack.EMPTY){
                CompoundTag tag = new CompoundTag();
                tag.putUuid("owner",instance.compatibility_id);
                tag.putString("name",instance.owner.getDisplayName().getString());
                itemStack.putSubTag(ChestCavity.COMPATIBILITY_TAG.toString(),tag);
            }
        }
        int universalOrgans = 0;
        Random random = instance.owner.getRandom();
        if(random.nextFloat() < ChestCavity.config.UNIVERSAL_DONOR_RATE){
            universalOrgans = 1+random.nextInt(3)+random.nextInt(3);
        }
        //each attempt, roll a random slot in the chestcavity and turn that organ, if any, compatible
        while(universalOrgans > 0){
            int i = random.nextInt(chestCavity.size());
            ItemStack itemStack = chestCavity.getStack(i);
            if(itemStack != null && itemStack != ItemStack.EMPTY){
                itemStack.removeSubTag(ChestCavity.COMPATIBILITY_TAG.toString());
            }
            universalOrgans--;
        }
    }

    @Override
    public float getHeartBleedCap(){return Float.MAX_VALUE;}

    @Override
    public boolean isOpenable(ChestCavityInstance instance){return true;}

    @Override
    public void onDeath(ChestCavityInstance cc) {
        cc.projectileQueue.clear();
        if(cc.opened) {
            ChestCavityUtil.dropUnboundOrgans(cc);
        }
    }

}
