package net.tigereye.chestcavity.interfaces;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public interface CCHungerManagerInterface {
    
    public void ccEat(Item item, Player player);
}