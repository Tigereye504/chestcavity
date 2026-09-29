package net.tigereye.chestcavity.listeners;
import net.minecraft.world.item.ItemStack;

public class OrganOnHitContext {
    public OrganOnHitContext(ItemStack organ, OrganOnHitListener listener){
        this.organ = organ;
        this.listener = listener;
    }
    public ItemStack organ;
    public OrganOnHitListener listener;
}
