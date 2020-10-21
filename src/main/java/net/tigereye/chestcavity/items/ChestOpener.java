package net.tigereye.chestcavity.items;

import net.minecraft.inventory.SimpleInventory;
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
import net.tigereye.chestcavity.interfaces.CCPlayerEntityInterface;

import java.util.Optional;

public class ChestOpener extends Item {
	
	public ChestOpener() {
		super(CCItems.CHEST_OPENER_SETTINGS);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

		SimpleInventory inv;
		Optional<CCPlayerEntityInterface> optional = CCPlayerEntityInterface.of(player);
		if(optional.isPresent()){
			inv = optional.get().getChestCavityManager().getChestCavity();
			player.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
				return GenericContainerScreenHandler.createGeneric9x3(i, playerInventory, inv);
			}, new TranslatableText("Chest Cavity")));
		}
		return super.use(world, player, hand);
	}
}
