package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.items.CCItems;
import net.tigereye.chestcavity.items.ChestCavityOrgan;
import net.tigereye.chestcavity.items.VanillaOrgans;
import net.tigereye.chestcavity.listeners.OrganTickCallback;
import net.tigereye.chestcavity.listeners.OrganUpdateCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ChestCavityManager implements InventoryChangedListener {
    public static final Logger LOGGER = LogManager.getLogger();

    protected LivingEntity owner;
    protected ChestCavityInventory chestCavity;
    protected Map<Identifier,Float> organScores = new HashMap<>();

    protected int heartTimer = 0;
    protected int kidneyTimer = 0;
    protected int liverTimer = 0;
    protected int spleenTimer = 0;
    protected float lungRemainder = 0;

    public ChestCavityManager(LivingEntity owner){
        this.chestCavity = new ChestCavityInventory(27);
        this.owner = owner;
        LOGGER.debug("[Chest Cavity] Initializing ChestCavityManager");

    }
    public ChestCavityManager(LivingEntity owner, int size){
        this.chestCavity = new ChestCavityInventory(size);
        this.owner = owner;
        LOGGER.debug("[Chest Cavity] Initializing ChestCavityManager");
    }

    public static void init(LivingEntity owner){
        ChestCavityManager chestCavityManager = new ChestCavityManager(owner);
        init(owner,chestCavityManager);
    }

    public static void init(LivingEntity owner, ChestCavityManager chestCavityManager){
        if(!owner.getEntityWorld().isClient()) {
            chestCavityManager.fillChestCavityInventory();
            chestCavityManager.EvaluateChestCavity();
        }
        chestCavityManager.getChestCavity().addListener(chestCavityManager);
    }

    public SimpleInventory getChestCavity() {
        return chestCavity;
    }

    public void setOwner(LivingEntity owner){
        this.owner = owner;
    }

    public int getHeartTimer() {
        return heartTimer;
    }

    public void setHeartTimer(int heartTimer) {
        this.heartTimer = heartTimer;
    }

    public int getKidneyTimer() {
        return kidneyTimer;
    }

    public void setKidneyTimer(int kidneyTimer) {
        this.kidneyTimer = kidneyTimer;
    }

    public int getLiverTimer() {
        return liverTimer;
    }

    public void setLiverTimer(int liverTimer) {
        this.liverTimer = liverTimer;
    }

    public int getSpleenTimer() {
        return spleenTimer;
    }

    public void setSpleenTimer(int spleenTimer) {
        this.spleenTimer = spleenTimer;
    }

    public float getLungRemainder() {
        return lungRemainder;
    }

    public void setLungRemainder(int lungRemainder) {
        this.lungRemainder = lungRemainder;
    }

    public float getOrganScore(Identifier id) {
        return organScores.getOrDefault(id, 0f);
    }

    public void setOrganScore(Identifier id, float value){
        organScores.put(id,value);
    }

    @Override
    public void onInventoryChanged(Inventory sender) {
        EvaluateChestCavity();
    }
    public boolean EvaluateChestCavity() {
        Map<Identifier,Float> oldScores = new HashMap<>(organScores);
        organScores.clear();

        for (int i = 0; i < chestCavity.size(); i++)
        {
            ItemStack slot = chestCavity.getStack(i);
            if (slot != null)
            {
                Item slotitem = slot.getItem();
                if(slotitem instanceof ChestCavityOrgan){
                    ((ChestCavityOrgan) slotitem).getOrganQualityMap(slot).forEach((key,value) ->
                            organScores.put(key,organScores.getOrDefault(key,0f)+(value*slot.getCount()/slot.getMaxCount())));
                }
                else {
                    //check vanilla organs
                    if(VanillaOrgans.map.containsKey(slotitem)){
                        VanillaOrgans.map.get(slotitem).forEach((key,value) ->
                                organScores.put(key,organScores.getOrDefault(key,0f)+(value*slot.getCount()/slot.getMaxCount())));
                    }
                }
            }
        }
        if(!oldScores.equals(organScores))
        {
            if(ChestCavity.DEBUG_MODE) {
                try {
                    Text name = owner.getName();
                    System.out.println("[Chest Cavity] Displaying " + name.getString() +"'s organ scores:");
                }
                catch(Exception e){
                    Text name = owner.getType().getName();
                    System.out.println("[Chest Cavity] Displaying "+ name.getString() +"'s organ scores:");
                }
                organScores.forEach((key, value) ->
                        System.out.print(key.toString() + ": " + value + " "));
                System.out.print("\n");
            }
            OrganUpdateCallback.EVENT.invoker().onOrganUpdate(owner, oldScores, organScores);
            return true;
        }
        return false;
    }

    public void onTick(){
        OrganTickCallback.EVENT.invoker().onOrganTick(owner, this);
    }

    public void fillChestCavityInventory() {
        chestCavity.setStack(0, new ItemStack(CCItems.MUSCLE, 64));
        chestCavity.setStack(1, new ItemStack(CCItems.RIB, 4));
        chestCavity.setStack(2, new ItemStack(CCItems.APPENDIX, 1));
        chestCavity.setStack(3, new ItemStack(CCItems.LUNG, 1));
        chestCavity.setStack(4, new ItemStack(CCItems.HEART, 1));
        chestCavity.setStack(5, new ItemStack(CCItems.LUNG, 1));
        chestCavity.setStack(6, ItemStack.EMPTY);
        chestCavity.setStack(7, new ItemStack(CCItems.RIB, 4));
        chestCavity.setStack(8, new ItemStack(CCItems.MUSCLE, 64));
        chestCavity.setStack(9, new ItemStack(CCItems.MUSCLE, 64));
        chestCavity.setStack(10, new ItemStack(CCItems.RIB, 4));
        chestCavity.setStack(11, new ItemStack(CCItems.SPLEEN, 1));
        chestCavity.setStack(12, new ItemStack(CCItems.KIDNEY, 1));
        chestCavity.setStack(13, new ItemStack(CCItems.SPINE, 1));
        chestCavity.setStack(14, new ItemStack(CCItems.KIDNEY, 1));
        chestCavity.setStack(15, new ItemStack(CCItems.LIVER, 1));
        chestCavity.setStack(16, new ItemStack(CCItems.RIB, 4));
        chestCavity.setStack(17, new ItemStack(CCItems.MUSCLE, 64));
        chestCavity.setStack(18, new ItemStack(CCItems.MUSCLE, 64));
        chestCavity.setStack(19, new ItemStack(CCItems.MUSCLE, 64));
        chestCavity.setStack(20, new ItemStack(CCItems.INTESTINE));
        chestCavity.setStack(21, new ItemStack(CCItems.INTESTINE));
        chestCavity.setStack(22, new ItemStack(CCItems.STOMACH));
        chestCavity.setStack(23, new ItemStack(CCItems.INTESTINE));
        chestCavity.setStack(24, new ItemStack(CCItems.INTESTINE));
        chestCavity.setStack(25, new ItemStack(CCItems.MUSCLE, 64));
        chestCavity.setStack(26, new ItemStack(CCItems.MUSCLE, 64));

    }

    public void chestCavityPostMortem(){
        if(!owner.getEntityWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
            rejectForeignObjects();
        }
        insertWelfareOrgans();
    }

    private void rejectForeignObjects(){
        for(int i = 0; i < chestCavity.size(); i++){
            if(!chestCavity.getStack(i).getItem().isIn(CCItems.HUMAN_ORGANS)) {
                owner.dropStack(chestCavity.removeStack(i));
            }
        }
    }

    private void insertWelfareOrgans(){
        //urgently essential organs are: heart, spine, lung
        if(chestCavity.count(CCItems.HEART) == 0){
            forcefullyAddStack(new ItemStack(CCItems.ROTTEN_HEART),4);
        }
        if(chestCavity.count(CCItems.LUNG) == 0){
            forcefullyAddStack(new ItemStack(CCItems.ROTTEN_LUNG),3);
        }
        if(chestCavity.count(CCItems.SPINE) == 0){
            forcefullyAddStack(new ItemStack(CCItems.ROTTEN_SPINE),13);
        }
    }

    private void forcefullyAddStack(ItemStack stack, int slot){
        if(chestCavity.canInsert(stack)){
            chestCavity.addStack(stack);
        }
        else if(owner.getEntityWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY) && owner instanceof PlayerEntity) {
            if(!((PlayerEntity)owner).inventory.insertStack(stack)){
                owner.dropStack(chestCavity.removeStack(slot));
            }
        }
        else {
            owner.dropStack(chestCavity.removeStack(slot));
        }
    }

    public void fromTag(CompoundTag tag, LivingEntity owner) {
        LOGGER.debug("[Chest Cavity] Reading ChestCavityManager fromTag");
        this.owner = owner;
        if(tag.contains("ChestCavity")){
            if(ChestCavity.DEBUG_MODE) {
                System.out.println("Found Save Data");
            }
            CompoundTag ccTag = tag.getCompound("ChestCavity");
            this.heartTimer = ccTag.getInt("HeartTimer");
            this.kidneyTimer = ccTag.getInt("KidneyTimer");
            this.liverTimer = ccTag.getInt("LiverTimer");
            this.spleenTimer = ccTag.getInt("SpleenTimer");
            this.lungRemainder = ccTag.getFloat("LungRemainder");
            chestCavity.removeListener(this);
            if (ccTag.contains("Inventory")) {
                ListTag listTag = ccTag.getList("Inventory", 10);
                this.chestCavity.readTags(listTag);
            }
            else{
                LOGGER.warn("[Chest Cavity] "+owner.getName().asString()+"'s Chest Cavity is mangled. It will be replaced");
                fillChestCavityInventory();
            }
            chestCavity.addListener(this);
        }
        else if(tag.contains("cardinal_components")){
                CompoundTag temp = tag.getCompound("cardinal_components");
                if(temp.contains("chestcavity:inventorycomponent")){
                    temp = tag.getCompound("chestcavity:inventorycomponent");
                    if(temp.contains("chestcavity")){
                        LOGGER.info("[Chest Cavity] Found "+owner.getName().asString()+"'s old Chest Cavity (v1).");
                        ListTag listTag = temp.getList("Inventory", 10);
                        chestCavity.removeListener(this);
                        this.chestCavity.readTags(listTag);
                        chestCavity.addListener(this);
                    }
                }
        }
        else{
            LOGGER.warn("[Chest Cavity] Cannot find "+owner.getName().asString()+"'s Chest Cavity, It will be replaced.");
            chestCavity.removeListener(this);
            fillChestCavityInventory();
            chestCavity.addListener(this);
        }
        EvaluateChestCavity();
    }

    public void toTag(CompoundTag tag) {
        if(ChestCavity.DEBUG_MODE) {
            System.out.println("Writing ChestCavityManager toTag");
        }
        CompoundTag ccTag = new CompoundTag();
        ccTag.putInt("HeartTimer", this.heartTimer);
        ccTag.putInt("KidneyTimer", this.kidneyTimer);
        ccTag.putInt("LiverTimer", this.liverTimer);
        ccTag.putInt("SpleenTimer", this.spleenTimer);
        ccTag.putFloat("LungRemainder", this.lungRemainder);
        ccTag.put("Inventory", this.chestCavity.getTags());
        tag.put("ChestCavity",ccTag);
    }

    public void clone(ChestCavityManager other) {
        chestCavity.removeListener(this);
        for(int i = 0; i < this.chestCavity.size(); ++i) {
            this.chestCavity.setStack(i, other.getChestCavity().getStack(i));
        }
        chestCavity.addListener(this);
        EvaluateChestCavity();
        heartTimer = other.getHeartTimer();
        liverTimer = other.getLiverTimer();
        kidneyTimer = other.getKidneyTimer();
        spleenTimer = other.getSpleenTimer();
        lungRemainder = other.getLungRemainder();
    }

    public float applyBoneDefense(float damage){
        float boneScore = organScores.getOrDefault(CCItems.ORGANS_BONE,0f);
        return damage*(5/(.25f+boneScore));
        //normal bone score of 4.75 means no change
    }

    public float applyIntestinesSaturation(float sat){
        return sat*organScores.getOrDefault(CCItems.ORGANS_INTESTINE,0f)/4;
    }

    public int applyStomachHunger(int hunger){
        //sadly, in order to get saturation at all we must grant at least half a haunch of food, unless we embrace incompatability
        return Math.max((int)(hunger*organScores.getOrDefault(CCItems.ORGANS_STOMACH,0f)),1);
    }

    public int applyLungCapacityInWater(){
        float airloss = 2f/Math.max(organScores.getOrDefault(CCItems.ORGANS_LUNG,0f),.1f) + lungRemainder;
        lungRemainder = airloss % 1;
        return (int) airloss;
    }

    public int applySpleenMetabolism(int foodStarvationTimer){
        spleenTimer++;
        if(spleenTimer >=2){
            foodStarvationTimer += organScores.getOrDefault(CCItems.ORGANS_SPLEEN,0f) - 1;
        }
        spleenTimer = 0;
        return foodStarvationTimer;
    }
}
