package net.tigereye.chestcavity.ui;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;

public class ChestCavityScreenHandler extends AbstractContainerMenu {

    private final ChestCavityInventory inventory;
    private final int size;
    private final int rows;

    private static ChestCavityInventory getOrCreateChestCavityInventory(Inventory playerInventory){/*
        ChestCavityInstance playerCC = ((ChestCavityEntity)playerInventory.player).getChestCavityInstance();
        ChestCavityInstance targetCCI = playerCC.ccBeingOpened;
        if(targetCCI != null){
            ChestCavity.LOGGER.debug("Found CCI");
            return targetCCI.inventory;
        }
        ChestCavity.LOGGER.debug("Missed CCI");*/
        return new ChestCavityInventory();
    }

    public ChestCavityScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, getOrCreateChestCavityInventory(playerInventory));
    }

    public ChestCavityScreenHandler(int syncId, Inventory playerInventory, ChestCavityInventory inventory) {
        super(ChestCavity.CHEST_CAVITY_SCREEN_HANDLER, syncId);
        this.size = inventory.getContainerSize();
        this.inventory = inventory;
        this.rows = (size-1)/9 + 1;
        inventory.startOpen(playerInventory.player);
        int i = (rows - 4) * 18;

        int n;
        int m;
        for(n = 0; n < this.rows; ++n) {
            for(m = 0; m < 9 && (n*9)+m < size; ++m) {
                this.addSlot(new Slot(inventory, m + n * 9, 8 + m * 18, 18 + n * 18));//18 + n * 18));
            }
        }

        for(n = 0; n < 3; ++n) {
            for(m = 0; m < 9; ++m) {
                this.addSlot(new Slot(playerInventory, m + n * 9 + 9, 8 + m * 18, 102 + n * 18 + i));//103 + n * 18 + i));
            }
        }

        for(n = 0; n < 9; ++n) {
            this.addSlot(new Slot(playerInventory, n, 8 + n * 18, 160 + i));//161 + i));
        }

    }

    @Override
    public ItemStack quickMoveStack(Player player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.getContainerSize()) {
                /*if(inventory.getInstance().type.isSlotForbidden(invSlot)){
                    return ItemStack.EMPTY;
                }*/
                if (!this.moveItemStackTo(originalStack, this.inventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(originalStack, 0, this.inventory.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }
}
