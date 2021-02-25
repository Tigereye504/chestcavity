package net.tigereye.chestcavity.chestcavities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

public class ChestCavityInventory extends SimpleInventory {

    ChestCavityInstance instance;
    boolean test;

    public ChestCavityInstance getInstance() {
        return instance;
    }

    public void setInstance(ChestCavityInstance instance) {
        this.instance = instance;
    }

    public ChestCavityInventory() {
        super(27);
    }

    public ChestCavityInventory(int size,ChestCavityInstance instance) {
        super(size);
        this.instance = instance;
    }

    public void readTags(ListTag tags) {
        //int j;
        //for(j = 0; j < this.size(); ++j) {
        //    this.setStack(j, ItemStack.EMPTY);
        //}
        clear();

        for(int j = 0; j < tags.size(); ++j) {
            CompoundTag compoundTag = tags.getCompound(j);
            int k = compoundTag.getByte("Slot") & 255;
            boolean f = compoundTag.getBoolean("Forbidden");
            if (k >= 0 && k < this.size()) {
                this.setStack(k, ItemStack.fromTag(compoundTag));
            }
        }

    }

    public ListTag getTags() {
        ListTag listTag = new ListTag();

        for(int i = 0; i < this.size(); ++i) {
            ItemStack itemStack = this.getStack(i);
            if (!itemStack.isEmpty()) {
                CompoundTag compoundTag = new CompoundTag();
                compoundTag.putByte("Slot", (byte)i);
                itemStack.toTag(compoundTag);
                listTag.add(compoundTag);
            }
        }

        return listTag;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {

        if(instance == null) {
            return true;
        } //this is for if something goes wrong with that first moment before things sync
        if(instance.owner.isDead()){return false;}
        return (player.distanceTo(instance.owner) < 8);
    }
}
