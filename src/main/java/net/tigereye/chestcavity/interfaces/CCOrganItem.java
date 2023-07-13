package net.tigereye.chestcavity.interfaces;

import net.minecraft.item.ItemStack;
import net.tigereye.chestcavity.chestcavities.organs.OrganData;

public interface CCOrganItem {

    public OrganData getOrganData(ItemStack stack);
}
