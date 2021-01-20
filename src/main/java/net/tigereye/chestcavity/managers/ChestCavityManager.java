package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.tag.Tag;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.items.*;
import net.tigereye.chestcavity.listeners.*;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCOtherOrgans;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Consumer;

public class ChestCavityManager implements InventoryChangedListener {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final Identifier COMPATIBILITY_TAG = new Identifier(ChestCavity.MODID,"organ_compatibility");
    public static final int COMPATIBILITY_TYPE_PERSONAL = 1;
    public static final int COMPATIBILITY_TYPE_SPECIES = 2;
    protected static final Map<Identifier,Float> defaultOrganScores = new HashMap<>();
    protected LivingEntity owner;
    protected ChestCavityInventory chestCavity;
    protected Map<Identifier,Float> oldOrganScores = new HashMap<>();
    protected Map<Identifier,Float> organScores = new HashMap<>();
    protected static List<OrganOnHitContext> onHitListeners = new ArrayList<>();

    protected boolean opened = false;
    protected int heartBleedTimer = 0;
    protected int bloodPoisonTimer = 0;
    protected int liverTimer = 0;
    protected int spleenTimer = 0;
    protected float lungRemainder = 0;

    static{
        initializeDefaultOrganScores();
    }

    public ChestCavityManager(LivingEntity owner){
        this.chestCavity = new ChestCavityInventory(27,this);
        this.owner = owner;
        LOGGER.debug("[Chest Cavity] Initializing ChestCavityManager");

    }
    public ChestCavityManager(LivingEntity owner, int size){
        this.chestCavity = new ChestCavityInventory(size,this);
        this.owner = owner;
        LOGGER.debug("[Chest Cavity] Initializing ChestCavityManager");
    }

    public void init(){
        if(!owner.getEntityWorld().isClient()) {
            //generateChestCavity();
            evaluateChestCavity();
        }
        getChestCavity().addListener(this);
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
        defaultOrganScores.put(CCOrganScores.STRENGTH,8f);
        defaultOrganScores.put(CCOrganScores.SPEED,8f);
        defaultOrganScores.put(CCOrganScores.NERVOUS_SYSTEM,1f);
        defaultOrganScores.put(CCOrganScores.METABOLISM,1f);
        defaultOrganScores.put(CCOrganScores.DIGESTION,1f);
    }

    public Map<Identifier,Float> getDefaultOrganScores(){
        return defaultOrganScores;
    }

    public ChestCavityInventory getChestCavity() {
        return chestCavity;
    }

    public void setOwner(LivingEntity owner){
        this.owner = owner;
    }

    public boolean getOpened(){
        return this.opened;
    }

    public void setOpened(boolean b) {
        this.opened = b;
    }

    public int getHeartBleedTimer() {
        return heartBleedTimer;
    }

    public void setHeartBleedTimer(int heartBleedTimer) {
        this.heartBleedTimer = heartBleedTimer;
    }

    public float getHeartBleedCap(){
        return Float.MAX_VALUE;
    }

    public int getBloodPoisonTimer() {
        return bloodPoisonTimer;
    }

    public void setBloodPoisonTimer(int bloodPoisonTimer) {
        this.bloodPoisonTimer = bloodPoisonTimer;
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

    public float getOldOrganScore(Identifier id) {
        return oldOrganScores.getOrDefault(id, 0f);
    }

    public float getDefaultOrganScore(Identifier id) {
        return getDefaultOrganScores().getOrDefault(id, 0f);
    }

    public void addOrganScore(Identifier id, float value){
        organScores.put(id,organScores.getOrDefault(id,0f)+value);
    }
    @Override
    public void onInventoryChanged(Inventory sender) {
        evaluateChestCavity();
    }
    public void evaluateChestCavity() {
        if(!opened){
            organScores.clear();
            organScores.putAll(getDefaultOrganScores());
        }
        else {
            onHitListeners.clear();
            resetOrganScores();

            for (int i = 0; i < chestCavity.size(); i++) {
                ItemStack itemStack = chestCavity.getStack(i);
                if (itemStack != null && itemStack != ItemStack.EMPTY) {
                    Item slotitem = itemStack.getItem();
                    if (!catchExceptionalOrgan(itemStack)) {//if a manager chooses to handle some organ in a special way, this lets it skip the normal evaluation.
                        Map<Identifier, Float> organMap = lookupOrganScore(itemStack);
                        if (lookupOrganScore(itemStack) != null) {
                            organMap.forEach((key, value) ->
                                    addOrganScore(key, value * Math.min(((float)itemStack.getCount()) / itemStack.getMaxCount(),1)));
                        }
                        if(slotitem instanceof OrganOnHitListener){
                            onHitListeners.add(new OrganOnHitContext(itemStack,(OrganOnHitListener)slotitem));
                        }
                    }
                    CompoundTag tag = itemStack.getTag();
                    if (tag != null && tag.contains(COMPATIBILITY_TAG.toString())) {
                        tag = tag.getCompound(COMPATIBILITY_TAG.toString());
                        if (tag.getInt("type") == COMPATIBILITY_TYPE_PERSONAL) {
                            if (!tag.getUuid("owner").equals(owner.getUuid())) {
                                if (ChestCavity.DEBUG_MODE && owner instanceof PlayerEntity) {
                                    System.out.println("incompatability found! item bound to UUID " + tag.getUuid("owner").toString() + " but player is UUID " + owner.getUuid());
                                }
                                addOrganScore(CCOrganScores.INCOMPATIBILITY, 1);
                            }
                        }
                    }
                }
            }
        }
        organUpdate();
    }

    protected Map<Identifier,Float> lookupOrganScore(ItemStack itemStack){
        Item item = itemStack.getItem();
        if(item instanceof ChestCavityOrgan){
            return ((ChestCavityOrgan) item).getOrganQualityMap(itemStack, owner);
        }
        else if(CCOtherOrgans.map.containsKey(item)){
                return CCOtherOrgans.map.get(item);
        }
        else{
            for (Tag<Item> itemTag:
            CCOtherOrgans.tagMap.keySet()) {
                if(item.isIn(itemTag)){
                    return CCOtherOrgans.tagMap.get(itemTag);
                }
            }
        }
        return null;
    }

    protected void organUpdate(){
        if(!oldOrganScores.equals(organScores))
        {
            if(ChestCavity.DEBUG_MODE && owner instanceof PlayerEntity) {
                outputOrganScoresString(System.out::println);
            }
            OrganUpdateCallback.EVENT.invoker().onOrganUpdate(owner, this);
            oldOrganScores.clear();
            oldOrganScores.putAll(organScores);
        }
    }

    protected void resetOrganScores(){
        organScores.clear();
    }

    protected boolean catchExceptionalOrgan(ItemStack slot){
        return false;
    }

    public void outputOrganScoresString(Consumer<String> output){
        try {
            Text name = owner.getDisplayName();
            output.accept("[Chest Cavity] Displaying " + name.getString() +"'s organ scores:");
        }
        catch(Exception e){
            output.accept("[Chest Cavity] Displaying organ scores:");
        }
        organScores.forEach((key, value) ->
                output.accept(key.getPath() + ": " + value + " "));
    }

    public void onTick(){
        if(opened) {
            OrganTickCallback.EVENT.invoker().onOrganTick(owner, this);
            organUpdate();
        }
    }

    public float onHit(DamageSource source, LivingEntity target, float damage){
        if(opened) {
            for (OrganOnHitContext e:
                 onHitListeners) {
                damage = e.listener.onHit(source,owner,target,this,e.organ,damage);
            }
            organUpdate();
        }
        return damage;
    }

    public ChestCavityInventory openChestCavity(){
        if(!opened) {
            getChestCavity().removeListener(this); //just in case really
            opened = true;
            generateChestCavity();
            getChestCavity().addListener(this);
        }
        return chestCavity;
    }

    public void generateChestCavity(){
        if(opened) {
            fillChestCavityInventory();
            //TODO: add event where listeners can overwrite specific organs before compatibility is set
            setOrganCompatibility();
        }
    }

    public void fillChestCavityInventory() {
        chestCavity.clear();
        for(int i = 0; i < chestCavity.size(); i++){
            chestCavity.setStack(i,new ItemStack(Items.DIRT,64));
        }
    }

    protected void setOrganCompatibility(){
        //first, make all organs personal
        for(int i = 0; i < chestCavity.size();i++){
            ItemStack itemStack = chestCavity.getStack(i);
            if(itemStack != null && itemStack != itemStack.EMPTY){
                CompoundTag tag = new CompoundTag();
                tag.putInt("type", COMPATIBILITY_TYPE_PERSONAL);
                tag.putUuid("owner",owner.getUuid());
                tag.putString("name",owner.getDisplayName().getString());
                itemStack.putSubTag(COMPATIBILITY_TAG.toString(),tag);
            }
        }
        int universalOrgans = 0;
        Random random = owner.getRandom();
        if(random.nextFloat() < ChestCavity.config.UNIVERSAL_DONOR_RATE){
            universalOrgans = 1+random.nextInt(3)+random.nextInt(3);
        }
        //each attempt, roll a random slot in the chestcavity and turn that organ, if any, compatible
        while(universalOrgans > 0){
            int i = random.nextInt(chestCavity.size());
            ItemStack itemStack = chestCavity.getStack(i);
            if(itemStack != null && itemStack != ItemStack.EMPTY){
                itemStack.removeSubTag(COMPATIBILITY_TAG.toString());
            }
            universalOrgans--;
        }
    }

    public void chestCavityPostMortem(){
        if(opened) {
            dropUnboundOrgans();
        }
    }

    public List<ItemStack> generateLootDrops(Random random, int looting){
        return new ArrayList<>();
    }

    protected void dropUnboundOrgans(){
        chestCavity.removeListener(this);
        for(int i = 0; i < chestCavity.size(); i++){
            ItemStack itemStack = chestCavity.getStack(i);
            if(itemStack != null && itemStack != itemStack.EMPTY) {
                CompoundTag tag = itemStack.getTag();
                if (tag != null && tag.contains(COMPATIBILITY_TAG.toString())) {
                    tag = tag.getCompound(COMPATIBILITY_TAG.toString());
                    if (tag.getInt("type") == COMPATIBILITY_TYPE_PERSONAL) {
                        if (!tag.getUuid("owner").equals(owner.getUuid())) {
                            //drop item
                            owner.dropStack(chestCavity.removeStack(i));
                        }
                    } else {
                        owner.dropStack(chestCavity.removeStack(i));
                    }
                } else {
                    owner.dropStack(chestCavity.removeStack(i));
                }
            }
        }
        chestCavity.addListener(this);
        evaluateChestCavity();
    }

    public void fromTag(CompoundTag tag, LivingEntity owner) {
        LOGGER.debug("[Chest Cavity] Reading ChestCavityManager fromTag");
        this.owner = owner;
        if(tag.contains("ChestCavity")){
            if(ChestCavity.DEBUG_MODE) {
                System.out.println("Found Save Data");
            }
            CompoundTag ccTag = tag.getCompound("ChestCavity");
            this.opened = ccTag.getBoolean("opened");
            this.heartBleedTimer = ccTag.getInt("HeartTimer");
            this.bloodPoisonTimer = ccTag.getInt("KidneyTimer");
            this.liverTimer = ccTag.getInt("LiverTimer");
            this.spleenTimer = ccTag.getInt("SpleenTimer");
            this.lungRemainder = ccTag.getFloat("LungRemainder");
            chestCavity.removeListener(this);
            if (ccTag.contains("Inventory")) {
                ListTag listTag = ccTag.getList("Inventory", 10);
                this.chestCavity.readTags(listTag);
            }
            else if(opened){
                LOGGER.warn("[Chest Cavity] "+owner.getName().asString()+"'s Chest Cavity is mangled. It will be replaced");
                generateChestCavity();
            }
            chestCavity.addListener(this);
        }
        else if(tag.contains("cardinal_components")){
                CompoundTag temp = tag.getCompound("cardinal_components");
                if(temp.contains("chestcavity:inventorycomponent")){
                    temp = tag.getCompound("chestcavity:inventorycomponent");
                    if(temp.contains("chestcavity")){
                        LOGGER.info("[Chest Cavity] Found "+owner.getName().asString()+"'s old [Cardinal Components] Chest Cavity.");
                        opened = true;
                        ListTag listTag = temp.getList("Inventory", 10);
                        chestCavity.removeListener(this);
                        this.chestCavity.readTags(listTag);
                        chestCavity.addListener(this);
                    }
                }
        }
        evaluateChestCavity();
    }

    public void toTag(CompoundTag tag) {
        if(ChestCavity.DEBUG_MODE) {
            System.out.println("Writing ChestCavityManager toTag");
        }
        CompoundTag ccTag = new CompoundTag();
        ccTag.putBoolean("opened", this.opened);
        ccTag.putInt("HeartTimer", this.heartBleedTimer);
        ccTag.putInt("KidneyTimer", this.bloodPoisonTimer);
        ccTag.putInt("LiverTimer", this.liverTimer);
        ccTag.putInt("SpleenTimer", this.spleenTimer);
        ccTag.putFloat("LungRemainder", this.lungRemainder);
        ccTag.put("Inventory", this.chestCavity.getTags());
        tag.put("ChestCavity",ccTag);
    }

    public void clone(ChestCavityManager other) {
        opened = other.getOpened();
        chestCavity.removeListener(this);
        for(int i = 0; i < this.chestCavity.size(); ++i) {
            this.chestCavity.setStack(i, other.getChestCavity().getStack(i));
        }
        chestCavity.addListener(this);

        heartBleedTimer = other.getHeartBleedTimer();
        liverTimer = other.getLiverTimer();
        bloodPoisonTimer = other.getBloodPoisonTimer();
        spleenTimer = other.getSpleenTimer();
        lungRemainder = other.getLungRemainder();
        evaluateChestCavity();
    }

    public float applyBoneDefense(float damage){
        if(!opened){
            return damage;
        }
        float boneDiff = (getOrganScore(CCOrganScores.DEFENSE) - getDefaultOrganScore(CCOrganScores.DEFENSE))/4;
        return (float)(damage*Math.pow(ChestCavity.config.BONE_DEFENSE,boneDiff));
    }

    public float applyIntestinesSaturation(float sat){
        if(!opened){
            return sat;
        }
        return sat*organScores.getOrDefault(CCOrganScores.NUTRITION,0f)/4;
        //TODO: find a use for intestines for non-players
    }

    public int applyStomachHunger(int hunger){
        if(!opened){
            return hunger;
        }
        //sadly, in order to get saturation at all we must grant at least half a haunch of food, unless we embrace incompatibility
        return Math.max((int)(hunger*organScores.getOrDefault(CCOrganScores.DIGESTION,0f)),1);
        //TODO: find a use for stomachs for non-players
    }

    public int applyLungCapacityInWater(int oldAir, int newAir){
        if(!opened || getDefaultOrganScore(CCOrganScores.BREATH) == 0 || owner.world.isClient()
            || ( getDefaultOrganScore(CCOrganScores.BREATH) == getOrganScore(CCOrganScores.BREATH) &&
                getDefaultOrganScore(CCOrganScores.WATERBREATH) == getOrganScore(CCOrganScores.WATERBREATH))){
            return newAir;
        }
        float airLoss = (oldAir - newAir);
        airLoss = airLoss*(1-getOrganScore(CCOrganScores.WATERBREATH));
        if(airLoss > 0) {
            float lungRatio = 20f;
            if (getOrganScore(CCOrganScores.BREATH) != 0) {
                lungRatio = Math.min(getDefaultOrganScore(CCOrganScores.BREATH) / getOrganScore(CCOrganScores.BREATH), 20f);
            }
            airLoss = airLoss * lungRatio + lungRemainder;
        }
        lungRemainder = airLoss % 1;
        return Math.min(oldAir - ((int) airLoss),owner.getMaxAir());
    }

    public int applySpleenMetabolism(int foodStarvationTimer){
        if(!opened){
            return foodStarvationTimer;
        }
        spleenTimer++;
        if(spleenTimer >= 2){
            foodStarvationTimer += getOrganScore(CCOrganScores.METABOLISM) - 1;
            spleenTimer = 0;
        }
        return foodStarvationTimer;
        //TODO: find a use for spleens for non-players
    }

    public void destroyOrgansWithKey(Identifier organ){
        for (int i = 0; i < chestCavity.size(); i++)
        {
            ItemStack slot = chestCavity.getStack(i);
            if (slot != null && slot != ItemStack.EMPTY)
            {
                if(lookupOrganScore(slot).containsKey(organ)){
                    chestCavity.removeStack(i);
                }
            }
        }
        chestCavity.markDirty();
    }

    public StatusEffectInstance onAddStatusEffect(StatusEffectInstance effect) {
        return OrganAddStatusEffectCallback.EVENT.invoker().onAddStatusEffect(owner, this,effect);
    }

    public boolean isOpenable(){
        return true;
    }
}
