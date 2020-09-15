package net.tigereye.chestcavity.components;

//import dev.onyxstudios.cca.api.v3.util.PlayerComponent;
import net.tigereye.chestcavity.items.CC_Items;
import net.tigereye.chestcavity.listeners.*;

import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import net.minecraft.item.ItemStack;

public class CCComponent implements InventoryComponent, EntitySyncedComponent, PlayerComponent<InventoryComponent> {
    private EnderChestInventory inventory = new EnderChestInventory();
    private PlayerEntity owner;
    private ChestCavityListener CCListener;

    public CCComponent (PlayerEntity owner){
        this.owner = owner;
        initChestCavityInventory();
        initCCListener();
    }

    @Override
    public void sync() {
        if (!this.getEntity().world.isClient) {
            //only sync with the holder, not everyone
            this.syncWith((ServerPlayerEntity) this.getEntity());
        }
    }

    @Override
    public boolean shouldCopyForRespawn(boolean lossless, boolean keepInventory) {
        return true;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        inventory.readTags((ListTag) tag.get("chestcavity"));
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.put("chestcavity", inventory.getTags());
        return tag;
    }

    @Override
    public LivingEntity getEntity() {
        return this.owner;
    }

    @Override
    public EnderChestInventory getInventory() {
        return this.inventory;
    }

    public ChestCavityListener getCCListener(){
        return this.CCListener;
    }
    public void setInventory(EnderChestInventory inventory){
        this.inventory = inventory;
        sync();
    }

    public void setEntity(PlayerEntity player){
        this.owner = player;
        sync();
    }

    public void initCCListener()
	{
		System.out.println("Initializing Chest Cavity Listener!");
		if(CCListener == null)
		{
			CCListener = new ChestCavityListener(owner);
            inventory.addListener(CCListener);
            CCListener.EvaluateChestCavity(inventory);
		}
			
    }
    
    public void initChestCavityInventory()
	{
		
        inventory = new EnderChestInventory();
        inventory.setStack(0, new ItemStack(CC_Items.muscle, 64));
        inventory.setStack(1, new ItemStack(CC_Items.rib, 4));
        inventory.setStack(2, new ItemStack(CC_Items.apendix, 1));
        inventory.setStack(3, new ItemStack(CC_Items.lung, 1));
        inventory.setStack(4, new ItemStack(CC_Items.heart, 1));
        inventory.setStack(5, new ItemStack(CC_Items.lung, 1));
        //inventory.setStack(6, new ItemStack(NOTHING));
        inventory.setStack(7, new ItemStack(CC_Items.rib, 4));
        inventory.setStack(8, new ItemStack(CC_Items.muscle, 64));
        inventory.setStack(9, new ItemStack(CC_Items.muscle, 64));
        inventory.setStack(10, new ItemStack(CC_Items.rib, 4));
        inventory.setStack(11, new ItemStack(CC_Items.spleen, 1));
        inventory.setStack(12, new ItemStack(CC_Items.kidney, 1));
        inventory.setStack(13, new ItemStack(CC_Items.spine, 1));
        inventory.setStack(14, new ItemStack(CC_Items.kidney, 1));
        inventory.setStack(15, new ItemStack(CC_Items.liver, 1));
        inventory.setStack(16, new ItemStack(CC_Items.rib, 4));
        inventory.setStack(17, new ItemStack(CC_Items.muscle, 64));
        inventory.setStack(18, new ItemStack(CC_Items.muscle, 64));
        inventory.setStack(19, new ItemStack(CC_Items.muscle, 64));
        inventory.setStack(20, new ItemStack(CC_Items.intestine));
        inventory.setStack(21, new ItemStack(CC_Items.intestine));
        inventory.setStack(22, new ItemStack(CC_Items.stomach));
        inventory.setStack(23, new ItemStack(CC_Items.intestine));
        inventory.setStack(24, new ItemStack(CC_Items.intestine));
        inventory.setStack(25, new ItemStack(CC_Items.muscle, 64));
        inventory.setStack(26, new ItemStack(CC_Items.muscle, 64));
			
	}

    public void chestCavityPostMortem(){
        rejectForeignObjects();
        insertWelfareOrgans();
    }

    private void rejectForeignObjects(){
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.getStack(i).getItem() != CC_Items.apendix
            && inventory.getStack(i).getItem() != CC_Items.heart
            && inventory.getStack(i).getItem() != CC_Items.intestine
            && inventory.getStack(i).getItem() != CC_Items.kidney
            && inventory.getStack(i).getItem() != CC_Items.liver
            && inventory.getStack(i).getItem() != CC_Items.lung
            && inventory.getStack(i).getItem() != CC_Items.muscle
            && inventory.getStack(i).getItem() != CC_Items.rib
            && inventory.getStack(i).getItem() != CC_Items.spine
            && inventory.getStack(i).getItem() != CC_Items.spleen
            && inventory.getStack(i).getItem() != CC_Items.stomach
            )
            owner.dropStack(inventory.removeStack(i));
        }
    }

    private void insertWelfareOrgans(){
        //urgently essential organs are: heart, spine, lung
        if(inventory.count(CC_Items.heart) == 0){
            forcefullyAddStack(new ItemStack(CC_Items.rottenHeart),4);
        }
        if(inventory.count(CC_Items.lung) == 0){
            forcefullyAddStack(new ItemStack(CC_Items.rottenLung),3);
            forcefullyAddStack(new ItemStack(CC_Items.rottenLung),5);
        }
        if(inventory.count(CC_Items.spine) == 0){
            forcefullyAddStack(new ItemStack(CC_Items.rottenSpine),13);
        }
    }

    private void forcefullyAddStack(ItemStack stack, int slot){
        if(!inventory.canInsert(stack)){
            owner.dropStack(inventory.removeStack(slot));
        }
        inventory.addStack(stack);
    }
}