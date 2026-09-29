package net.tigereye.chestcavity.util;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
public final class ItemData {
    public static CompoundTag get(ItemStack stack) { return stack.getOrDefault(DataComponents.CUSTOM_DATA,CustomData.EMPTY).copyTag(); }
    public static void put(ItemStack stack,String key,CompoundTag tag) { CustomData.update(DataComponents.CUSTOM_DATA,stack,data -> data.put(key,tag)); }
    public static void remove(ItemStack stack,String key) { CustomData.update(DataComponents.CUSTOM_DATA,stack,data -> data.remove(key)); }
}
