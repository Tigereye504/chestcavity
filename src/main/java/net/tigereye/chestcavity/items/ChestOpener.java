package net.tigereye.chestcavity.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.inventory.Inventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.ui.ChestCavityScreenHandler;

import java.util.Optional;

public class ChestOpener extends Item {
	
	public ChestOpener() {
		super(CCItems.CHEST_OPENER_SETTINGS);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		openChestCavity(player,player);
		return TypedActionResult.success(player.getStackInHand(hand),false);
	}

	public void openChestCavity(PlayerEntity player, LivingEntity target){
		if(target.getUuid() == player.getUuid()){
			target.damage(DamageSource.GENERIC,4f); // this is to prevent self-knockback, as that feels weird.
		}
		else {
			target.damage(DamageSource.player(player), 4f);
		}
		Optional<ChestCavityEntity> optional = ChestCavityEntity.of(target);
		if(optional.isPresent() && target.isAlive()) {
			ChestCavityEntity chestCavityEntity = optional.get();
			Inventory inv = chestCavityEntity.getChestCavityManager().openChestCavity();
			player.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
				return new ChestCavityScreenHandler(i, playerInventory, inv);
			}, new TranslatableText("Chest Cavity")));
		}
	}
}
