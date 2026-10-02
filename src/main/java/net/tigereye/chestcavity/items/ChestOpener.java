package net.tigereye.chestcavity.items;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

//import ladysnake.requiem.api.v1.possession.PossessionComponent;

import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.compat.requiem.CCRequiem;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.ui.ChestCavityScreenHandler;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.Optional;

public class ChestOpener extends Item {
	
	public ChestOpener(Item.Properties properties) {
		super(properties.stacksTo(1));
	}

	@Override
	public InteractionResult use(Level world, Player player, InteractionHand hand) {
		LivingEntity target = null;
		//Requiem is no longer a fabric mod.
		/*if(CCRequiem.REQUIEM_ACTIVE){
			target = PossessionComponent.getHost(player);
		}*/
		if(target == null) {
			target = player;
		}
		if (openChestCavity(player, target,false)) {
			return InteractionResult.SUCCESS;
		} else {
			return InteractionResult.PASS;
		}
	}

	public boolean openChestCavity(Player player, LivingEntity target){
		return openChestCavity(player,target,true);
	}
	public boolean openChestCavity(Player player, LivingEntity target, boolean shouldKnockback){
        var optional=ChestCavityEntity.of(target);
        if(optional.isEmpty())return false;
        var cc=optional.get().getChestCavityInstance();
        if(target!=player && !cc.getChestCavityType().isOpenable(cc)){
            if(player instanceof net.minecraft.server.level.ServerPlayer sp)sp.sendSystemMessage(Component.translatable(target.getItemBySlot(EquipmentSlot.CHEST).isEmpty()?"message.chestcavity.too_healthy":"message.chestcavity.obstructed"),true);
            return false;
        }
        if(!(player instanceof net.minecraft.server.level.ServerPlayer serverPlayer))return true;
        if(cc.getOrganScore(CCOrganScores.EASE_OF_ACCESS)>0)player.level().playSound(null,target.blockPosition(),SoundEvents.CHEST_OPEN,SoundSource.PLAYERS,.75f,1);
        else target.hurt(shouldKnockback?target.damageSources().playerAttack(player):target.damageSources().generic(),4f);
        if(target.isAlive()){
            var inv=ChestCavityUtil.openChestCavity(cc);
            ((ChestCavityEntity)player).getChestCavityInstance().ccBeingOpened=cc;
            serverPlayer.openMenu(new SimpleMenuProvider((id,inventory,viewer)->new ChestCavityScreenHandler(id,inventory,inv),Component.translatable("container.chestcavity",target.getDisplayName())));
        }
        return true;
    }
}
