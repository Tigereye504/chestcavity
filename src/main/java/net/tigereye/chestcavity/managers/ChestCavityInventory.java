package net.tigereye.chestcavity.managers;

import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ChestCavityInventory extends SimpleInventory {

    ChestCavityManager manager;

    public ChestCavityManager getManager() {
        return manager;
    }

    public void setManager(ChestCavityManager manager) {
        this.manager = manager;
    }

    public ChestCavityInventory() {
        super(27);
    }

    public ChestCavityInventory(int size,ChestCavityManager manager) {
        super(size);
        this.manager = manager;
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
        if(manager == null) {return true;} //this is for if something goes wrong with that first moment before things sync
        if(manager.owner.isDead()){return false;}
        return !(player.distanceTo(manager.owner) >= 8);
    }
}
