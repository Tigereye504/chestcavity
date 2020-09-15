package net.tigereye.chestcavity.items;

import net.tigereye.chestcavity.ChestCavity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ChestOpener extends Item {
	
	public ChestOpener() {
		super(CC_Items.CHEST_OPENER_SETTINGS);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
	
		EnderChestInventory inv = ChestCavity.INVENTORYCOMPONENT.get(player).getInventory();
		player.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
			return GenericContainerScreenHandler.createGeneric9x3(i, playerInventory, inv);
		 }, new TranslatableText("Chest Cavity")));
		return super.use(world, player, hand);
	}
}
