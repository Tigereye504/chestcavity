package net.tigereye.chestcavity.mixin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.world.entity.*;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.chestcavities.instance.*;
import net.tigereye.chestcavity.util.*;

@Mixin(net.minecraft.world.entity.player.Player.class)
public abstract class MixinPlayer {
    @Inject(method="interactOn",at=@At("HEAD"),cancellable=true)
    private void chestcavity$interact(Entity target,net.minecraft.world.InteractionHand hand,net.minecraft.world.phys.Vec3 hitLocation,CallbackInfoReturnable<net.minecraft.world.InteractionResult> cir){
        var player=(net.minecraft.world.entity.player.Player)(Object)this;
        if(target instanceof net.minecraft.world.entity.boss.enderdragon.EnderDragonPart part)target=part.parentMob;
        if(target instanceof LivingEntity living && player.getItemInHand(hand).getItem() instanceof net.tigereye.chestcavity.items.ChestOpener opener
            && (!(living instanceof net.minecraft.world.entity.player.Player)||net.tigereye.chestcavity.ChestCavity.config.CAN_OPEN_OTHER_PLAYERS)){
            if(opener.openChestCavity(player,living))cir.setReturnValue(net.minecraft.world.InteractionResult.SUCCESS);
        }
    }
}
