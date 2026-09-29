package net.tigereye.chestcavity.chestcavities.instance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;
import net.minecraft.world.Container;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.inventory.ContainerListener;

//import ladysnake.requiem.api.v1.possession.PossessionComponent;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.compat.requiem.CCRequiem;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.listeners.OrganOnHitContext;
import net.tigereye.chestcavity.util.ChestCavityUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Consumer;

public class ChestCavityInstance {

    public static final Logger LOGGER = LogManager.getLogger();

    protected ChestCavityType type;
    public LivingEntity owner;
    public UUID compatibility_id;

    public boolean opened = false;
    public ChestCavityInventory inventory = new ChestCavityInventory();
    public Map<Identifier,Float> oldOrganScores = new HashMap<>();
    protected Map<Identifier,Float> organScores = new HashMap<>();
    public List<OrganOnHitContext> onHitListeners = new ArrayList<>();
    public LinkedList<Consumer<LivingEntity>> projectileQueue = new LinkedList<>();

    public int heartBleedTimer = 0;
    public int bloodPoisonTimer = 0;
    public int liverTimer = 0;
    public float metabolismRemainder = 0;
    public float lungRemainder = 0;
    public int projectileCooldown = 0;
    public int furnaceProgress = 0;
    public int photosynthesisProgress = 0;
    public EndCrystal connectedCrystal = null;

    public byte[] updatePacket = null;
    public ChestCavityInstance ccBeingOpened = null;

    public ChestCavityInstance(ChestCavityType type, LivingEntity owner){
        this.type = type;
        this.owner = owner;
        this.inventory.setInstance(this);
        this.compatibility_id = owner.getUUID();
        ChestCavityUtil.evaluateChestCavity(this);
    }

    public void setClientDefaultScores(Map<Identifier,Float> defaults){
        if(!owner.level().isClientSide())throw new IllegalStateException("Client cavity baseline on server");
        type=new net.tigereye.chestcavity.chestcavities.types.ClientChestCavityType(defaults);
    }
    public ChestCavityType getChestCavityType(){
        //Requiem is no longer a fabric mod. If it eventually becomes one again,
        //or becomes properly fabric compatible, this can be re-enabled.
        /*if(CCRequiem.REQUIEM_ACTIVE){
            Mob victim = PossessionComponent.getHost(owner);
            if(victim instanceof ChestCavityEntity){
                return ((ChestCavityEntity)victim).getChestCavityInstance().getChestCavityType();
            }
        }*/
        return this.type;
    }

    public Map<Identifier,Float> getOrganScores() {
        return organScores;
    }

    public void setOrganScores(Map<Identifier,Float> organScores) {
        this.organScores = organScores;
    }

    public float getOrganScore(Identifier id) {
        //Requiem is no longer a fabric mod. If it eventually becomes one again,
        //or becomes properly fabric compatible, this can be re-enabled.
        /*if(CCRequiem.REQUIEM_ACTIVE){
            Mob victim = PossessionComponent.getHost(owner);
            if(victim instanceof ChestCavityEntity){
                return ((ChestCavityEntity)victim).getChestCavityInstance().getOrganScore(id);
            }
        }*/
        return organScores.getOrDefault(id, 0f);
    }

    public float getOldOrganScore(Identifier id) {
        return oldOrganScores.getOrDefault(id, 0f);
    }

    public void containerChanged(Container sender) {
        ChestCavityUtil.clearForbiddenSlots(this);
        ChestCavityUtil.evaluateChestCavity(this);
    }

    public void fromTag(CompoundTag tag, LivingEntity owner) {
        LOGGER.debug("[Chest Cavity] Reading ChestCavityManager fromTag");
        this.owner = owner;
        this.inventory.setInstance(this);
        if(tag.contains("ChestCavity")){
            if(ChestCavity.DEBUG_MODE) {
                System.out.println("Found Save Data");
            }
            CompoundTag ccTag = tag.getCompoundOrEmpty("ChestCavity");
            this.opened = ccTag.getBooleanOr("opened",false);
            this.heartBleedTimer = ccTag.getIntOr("HeartTimer",0);
            this.bloodPoisonTimer = ccTag.getIntOr("KidneyTimer",0);
            this.liverTimer = ccTag.getIntOr("LiverTimer",0);
            this.metabolismRemainder = ccTag.getFloatOr("MetabolismRemainder",0f);
            this.lungRemainder = ccTag.getFloatOr("LungRemainder",0f);
            this.furnaceProgress = ccTag.getIntOr("FurnaceProgress",0);
            this.photosynthesisProgress = ccTag.getIntOr("PhotosynthesisProgress",0);
            if(ccTag.contains("compatibility_id")){
                this.compatibility_id = ccTag.read("compatibility_id", net.minecraft.core.UUIDUtil.CODEC).orElse(new java.util.UUID(0,0));
            }
            else{
                this.compatibility_id = owner.getUUID();
            }
            try {
                inventory.removeListener(this);
            }
            catch(NullPointerException ignored){}
            if (ccTag.contains("Container")) {
                ListTag ListTag = ccTag.getListOrEmpty("Container");
                this.inventory.readTags(ListTag);
            }
            else if(opened){
                LOGGER.warn("[Chest Cavity] "+owner.getName().getString()+"'s Chest Cavity is mangled. It will be replaced");
                ChestCavityUtil.generateChestCavityIfOpened(this);
            }
            inventory.addListener(this);
        }
        else if(tag.contains("cardinal_components")){
            CompoundTag temp = tag.getCompoundOrEmpty("cardinal_components");
            if(temp.contains("chestcavity:inventorycomponent")){
                temp = tag.getCompoundOrEmpty("chestcavity:inventorycomponent");
                if(temp.contains("chestcavity")){
                    LOGGER.debug("[Chest Cavity] Found "+owner.getName().getString()+"'s old [Cardinal Components] Chest Cavity.");
                    opened = true;
                    ListTag ListTag = temp.getListOrEmpty("Container");
                    try {
                        inventory.removeListener(this);
                    }
                    catch(NullPointerException ignored){}
                    inventory.readTags(ListTag);
                    inventory.addListener(this);
                }
            }
        }
        ChestCavityUtil.evaluateChestCavity(this);
    }

    public void toTag(CompoundTag tag) {
        if(ChestCavity.DEBUG_MODE) {
            System.out.println("Writing ChestCavityManager toTag");
        }
        CompoundTag ccTag = new CompoundTag();
        ccTag.putBoolean("opened", this.opened);
        ccTag.store("compatibility_id", net.minecraft.core.UUIDUtil.CODEC, this.compatibility_id);
        ccTag.putInt("HeartTimer", this.heartBleedTimer);
        ccTag.putInt("KidneyTimer", this.bloodPoisonTimer);
        ccTag.putInt("LiverTimer", this.liverTimer);
        ccTag.putFloat("MetabolismRemainder", this.metabolismRemainder);
        ccTag.putFloat("LungRemainder", this.lungRemainder);
        ccTag.putInt("FurnaceProgress", this.furnaceProgress);
        ccTag.putInt("PhotosynthesisProgress", this.photosynthesisProgress);
        ccTag.put("Container", this.inventory.getTags());
        tag.put("ChestCavity",ccTag);
    }

    public void clone(ChestCavityInstance other) {
        opened = other.opened;
        type = other.type;
        compatibility_id = other.compatibility_id;
        try {
            inventory.removeListener(this);
        }
        catch(NullPointerException ignored){}
        for(int i = 0; i < inventory.getContainerSize(); ++i) {
            inventory.setItem(i, other.inventory.getItem(i).copy());
            //inventory.forbiddenSlots = other.inventory.forbiddenSlots;
        }
        inventory.readTags(other.inventory.getTags());
        inventory.addListener(this);

        heartBleedTimer = other.heartBleedTimer;
        liverTimer = other.liverTimer;
        bloodPoisonTimer = other.bloodPoisonTimer;
        metabolismRemainder = other.metabolismRemainder;
        lungRemainder = other.lungRemainder;
        furnaceProgress = other.furnaceProgress;
        photosynthesisProgress = other.photosynthesisProgress;
        connectedCrystal = other.connectedCrystal;
        ChestCavityUtil.evaluateChestCavity(this);
    }

}
