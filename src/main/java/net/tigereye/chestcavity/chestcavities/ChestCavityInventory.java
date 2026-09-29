package net.tigereye.chestcavity.chestcavities;
import net.minecraft.nbt.*;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
public class ChestCavityInventory extends SimpleContainer {
    private ChestCavityInstance instance;
    private final java.util.Set<ChestCavityInstance> listeners = new java.util.HashSet<>();
    private boolean notifying;
    public void addListener(ChestCavityInstance listener){listeners.add(listener);}
    public void removeListener(ChestCavityInstance listener){listeners.remove(listener);}
    @Override public void setChanged(){
        if(notifying || listeners==null)return;
        notifying=true;
        try { for(var listener:java.util.List.copyOf(listeners))listener.containerChanged(this); }
        finally {notifying=false;}
    }
    public ChestCavityInventory() { super(27); }
    public ChestCavityInventory(int size,ChestCavityInstance instance) { super(size);this.instance=instance; }
    public ChestCavityInstance getInstance() { return instance; }
    public void setInstance(ChestCavityInstance instance) { this.instance=instance; }
    private RegistryOps<Tag> ops() { return RegistryOps.create(NbtOps.INSTANCE,instance.owner.registryAccess()); }
    public void readTags(ListTag tags) {
        clearContent();
        for(int i=0;i<tags.size();i++) {
            CompoundTag entry=tags.getCompoundOrEmpty(i);int slot=entry.getByteOr("Slot",(byte)0)&255;
            if(slot<getContainerSize()) setItem(slot,ItemStack.CODEC.parse(ops(),entry).getOrThrow());
        }
    }
    public ListTag getTags() {
        ListTag tags=new ListTag();
        for(int slot=0;slot<getContainerSize();slot++) if(!getItem(slot).isEmpty()) {
            CompoundTag entry=(CompoundTag)ItemStack.CODEC.encodeStart(ops(),getItem(slot)).getOrThrow();entry.putByte("Slot",(byte)slot);tags.add(entry);
        }
        return tags;
    }
    @Override public boolean stillValid(Player player) { return instance==null || (!instance.owner.isDeadOrDying() && player.distanceTo(instance.owner)<8); }
}
