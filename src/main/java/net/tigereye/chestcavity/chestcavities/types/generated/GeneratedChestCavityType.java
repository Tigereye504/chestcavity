package net.tigereye.chestcavity.chestcavities.types.generated;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.chestcavities.organs.GeneratedOrganManager;
import net.tigereye.chestcavity.items.Organ;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.*;

public class GeneratedChestCavityType implements ChestCavityType {

    private Map<Identifier,Float> defaultOrganScores = null;
    private ChestCavityInventory defaultChestCavity = null;
    private Map<Identifier,Float> baseOrganScores = null;
    private Map<Ingredient,Map<Identifier,Float>> exceptionalOrganList = null;
    private List<ItemStack> droppableOrgans = null;
    private List<Integer> forbiddenSlots = new ArrayList<>();
    private boolean bossChestCavity = false;
    private boolean playerChestCavity = false;

    public GeneratedChestCavityType(){
        //fillChestCavityInventory(defaultChestCavity);
        //shapeChestCavity();
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
    public void setDefaultChestCavity(ChestCavityInventory inv){defaultChestCavity = inv;}

    public Map<Identifier,Float> getBaseOrganScores(){return baseOrganScores;}
    public float getBaseOrganScore(Identifier id){return getBaseOrganScores().getOrDefault(id,0f);}
    public void setBaseOrganScores(Map<Identifier,Float> organScores){baseOrganScores = organScores;}
    public void setBaseOrganScore(Identifier id, float score){baseOrganScores.put(id,score);}

    public Map<Ingredient,Map<Identifier,Float>> getExceptionalOrganList(){return exceptionalOrganList;}
    public Map<Identifier,Float> getExceptionalOrganScore(ItemStack itemStack){
        for(Ingredient ingredient:
                getExceptionalOrganList().keySet()){
            if(ingredient.test(itemStack)){
                return getExceptionalOrganList().get(ingredient);
            }
        }
        return null;
    }
    public void setExceptionalOrganList(Map<Ingredient,Map<Identifier,Float>> list){exceptionalOrganList = list;}
    public void setExceptionalOrgan(Ingredient ingredient,Map<Identifier,Float> scores){exceptionalOrganList.put(ingredient,scores);}

    public List<ItemStack> getDroppableOrgans(){
        if(droppableOrgans == null){
            deriveDroppableOrgans();
        }
        return droppableOrgans;}
    public void setDroppableOrgans(List<ItemStack> list){droppableOrgans = list;}
    private void deriveDroppableOrgans() {
        droppableOrgans = new LinkedList<>();
        for(int i = 0; i < defaultChestCavity.size(); i++){
            ItemStack stack = defaultChestCavity.getStack(i);
            if(stack.getItem() instanceof Organ || GeneratedOrganManager.isTrueOrgan(stack.getItem())){
                droppableOrgans.add(stack);
            }
        }
    }

    public List<Integer> getForbiddenSlots(){return forbiddenSlots;}
    public void setForbiddenSlots(List<Integer> list){forbiddenSlots = list;}
    public void forbidSlot(int slot){forbiddenSlots.add(slot);}
    public void allowSlot(int slot){
        int index = forbiddenSlots.indexOf(slot);
        if(index != -1){
            forbiddenSlots.remove(index);
        }
    }
    @Override
    public boolean isSlotForbidden(int index){
        return forbiddenSlots.contains(index);
    }

    public boolean isBossChestCavity(){return bossChestCavity;}
    public void setBossChestCavity(boolean bool){
        bossChestCavity = bool;}

    public boolean isPlayerChestCavity(){return playerChestCavity;}
    public void setPlayerChestCavity(boolean bool){playerChestCavity = bool;}

    @Override
    public void fillChestCavityInventory(ChestCavityInventory chestCavity) {
        chestCavity.clear();
        for(int i = 0; i < chestCavity.size(); i++){
            chestCavity.setStack(i,defaultChestCavity.getStack(i));
        }
    }

    //TODO: methods are copy pasted and so likely bad from here. Move to utility?

    @Override
    public void shapeChestCavity() {
    }

    @Override
    public void loadBaseOrganScores(Map<Identifier, Float> organScores){
        organScores.clear();
    }

    @Override
    public boolean catchExceptionalOrgan(ItemStack slot, Map<Identifier, Float> organScores){
        Map<Identifier,Float> organMap = getExceptionalOrganScore(slot);
        if(organMap != null){
            organMap.forEach((key, value) ->
                    ChestCavityUtil.addOrganScore(key, value * Math.min(((float)slot.getCount()) / slot.getMaxCount(),1),organScores));
            return true;
        }
        return false;
    }

    @Override
    public List<ItemStack> generateLootDrops(Random random, int looting) {
        List<ItemStack> loot = new ArrayList<>();
        if(playerChestCavity){
            return loot;
        }
        if(bossChestCavity){
            generateGuaranteedOrganDrops(random,looting,loot);
            return loot;
        }
        if(random.nextFloat() < ChestCavity.config.UNIVERSAL_DONOR_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*looting)) {
            generateRareOrganDrops(random,looting,loot);
        }
        return loot;
    }
    public void generateRareOrganDrops(Random random, int looting, List<ItemStack> loot){
        LinkedList<ItemStack> organPile = new LinkedList<>(getDroppableOrgans());
        int rolls = 1 + random.nextInt(3) + random.nextInt(3);
        ChestCavityUtil.drawOrgansFromPilev2(organPile,rolls,random,loot);

    }
    public void generateGuaranteedOrganDrops(Random random, int looting, List<ItemStack> loot){
        LinkedList<ItemStack> organPile = new LinkedList<>(getDroppableOrgans());
        int rolls = 3 + random.nextInt(2+looting) + random.nextInt(2+looting);
        ChestCavityUtil.drawOrgansFromPilev2(organPile,rolls,random,loot);
    }

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
        if(!playerChestCavity) {
            int universalOrgans = 0;
            Random random = instance.owner.getRandom();
            if (bossChestCavity){
                universalOrgans = 3+random.nextInt(2)+random.nextInt(2);
            }
            else if (random.nextFloat() < ChestCavity.config.UNIVERSAL_DONOR_RATE) {
                universalOrgans = 1 + random.nextInt(3) + random.nextInt(3);
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
    }

    @Override
    public float getHeartBleedCap(){
        if(bossChestCavity){
            return 5;
        }
        return Float.MAX_VALUE;
    }

    @Override
    public boolean isOpenable(ChestCavityInstance instance){
        boolean weakEnough = instance.owner.getHealth() <= ChestCavity.config.CHEST_OPENER_ABSOLUTE_HEALTH_THRESHOLD
                || instance.owner.getHealth() <= instance.owner.getMaxHealth()*ChestCavity.config.CHEST_OPENER_FRACTIONAL_HEALTH_THRESHOLD;
        boolean chestVulnerable = instance.owner.getEquippedStack(EquipmentSlot.CHEST).isEmpty();
        boolean easeOfAccess = instance.getOrganScore(CCOrganScores.EASE_OF_ACCESS) > 0;
        return chestVulnerable && (easeOfAccess || weakEnough);
    }

    @Override
    public void onDeath(ChestCavityInstance cc) {
        cc.projectileQueue.clear();
        if(cc.connectedCrystal != null) {
            cc.connectedCrystal.setBeamTarget(null);
            cc.connectedCrystal = null;
        }
        if(cc.opened && !(playerChestCavity && ChestCavity.config.KEEP_CHEST_CAVITY)) {
            ChestCavityUtil.dropUnboundOrgans(cc);
        }
        if(playerChestCavity){
            ChestCavityUtil.insertWelfareOrgans(cc);
        }
    }


}
