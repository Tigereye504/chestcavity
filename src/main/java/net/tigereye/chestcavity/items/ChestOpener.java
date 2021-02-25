package net.tigereye.chestcavity.items;

import ladysnake.requiem.api.v1.possession.PossessionComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.crossmod.requiem.CCRequiem;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.ui.ChestCavityScreenHandler;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.Optional;

public class ChestOpener extends Item {
	
	public ChestOpener() {
		super(CCItems.CHEST_OPENER_SETTINGS);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		LivingEntity target = null;
		if(CCRequiem.REQUIEM_ACTIVE){
			target = PossessionComponent.getPossessedEntity(player);

		}
		if(target == null) {
			target = player;
		}
		if (openChestCavity(player, target,false)) {
			return TypedActionResult.success(player.getStackInHand(hand), false);
		} else {
			return TypedActionResult.pass(player.getStackInHand(hand));
		}
	}

	public boolean openChestCavity(PlayerEntity player, LivingEntity target){
		return openChestCavity(player,target,true);
	}
	public boolean openChestCavity(PlayerEntity player, LivingEntity target, boolean shouldKnockback){
		Optional<ChestCavityEntity> optional = ChestCavityEntity.of(target);
		if(optional.isPresent()){
			ChestCavityEntity chestCavityEntity = optional.get();
			ChestCavityInstance cc = chestCavityEntity.getChestCavityInstance();
			if(cc.type.isOpenable(cc)) {
				if (cc.getOrganScore(CCOrganScores.EASE_OF_ACCESS) <= 0) {
					if (!shouldKnockback) {
						target.damage(DamageSource.GENERIC, 4f); // this is to prevent self-knockback, as that feels weird.
					} else {
						target.damage(DamageSource.player(player), 4f);
					}
				}
				if (target.isAlive()) {
					String name;
					try {
						name = target.getDisplayName().getString();
						name = name.concat("'s ");
					} catch (Exception e) {
						name = "";
					}
					ChestCavityInventory inv = ChestCavityUtil.openChestCavity(cc);
					((ChestCavityEntity)player).getChestCavityInstance().ccBeingOpened = cc;
					player.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
						return new ChestCavityScreenHandler(i, playerInventory, inv);
					}, new TranslatableText(name + "Chest Cavity")));
				}
				return true;
			}
			return false;
		}
		else{
			return false;
		}
	}
}
