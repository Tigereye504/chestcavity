package net.tigereye.chestcavity.mixin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.world.entity.*;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.chestcavities.instance.*;
import net.tigereye.chestcavity.util.*;

@Mixin(net.minecraft.world.entity.animal.cow.AbstractCow.class)
public abstract class MixinCow {
    @Inject(method="mobInteract",at=@At(value="RETURN",ordinal=0)) private void chestcavity$milk(net.minecraft.world.entity.player.Player player,net.minecraft.world.InteractionHand hand,CallbackInfoReturnable<net.minecraft.world.InteractionResult> cir){
        LivingEntity self=(LivingEntity)(Object)this;if(!self.level().isClientSide())OrganUtil.milkSilk(self);
    }
}
