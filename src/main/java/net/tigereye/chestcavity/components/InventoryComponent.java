package net.tigereye.chestcavity.components;

import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.inventory.EnderChestInventory;

public interface InventoryComponent extends Component {
    EnderChestInventory getInventory();
    void setInventory(EnderChestInventory inv);
}