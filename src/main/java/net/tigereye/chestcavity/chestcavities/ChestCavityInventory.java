package net.tigereye.chestcavity.chestcavities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

import java.util.ArrayList;
import java.util.List;

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

    public void readTags(NbtList tags) {
        clear();
        for(int j = 0; j < tags.size(); ++j) {
            NbtCompound NbtCompound = tags.getCompound(j);
            int k = NbtCompound.getByte("Slot") & 255;
            boolean f = NbtCompound.getBoolean("Forbidden");
            if (k >= 0 && k < this.size()) {
                this.setStack(k, ItemStack.fromNbt(NbtCompound));
            }
        }

    }

    public NbtList getTags() {
        NbtList list = new NbtList();

        for(int i = 0; i < this.size(); ++i) {
            ItemStack itemStack = this.getStack(i);
            if (!itemStack.isEmpty()) {
                NbtCompound NbtCompound = new NbtCompound();
                NbtCompound.putByte("Slot", (byte)i);
                itemStack.writeNbt(NbtCompound);
                list.add(NbtCompound);
            }
        }

        return list;
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
