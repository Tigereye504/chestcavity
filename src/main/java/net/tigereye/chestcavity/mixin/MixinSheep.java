package net.tigereye.chestcavity.mixin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.world.entity.*;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.chestcavities.instance.*;
import net.tigereye.chestcavity.util.*;

@Mixin(net.minecraft.world.entity.animal.sheep.Sheep.class)
public abstract class MixinSheep {
    @Inject(method="shear",at=@At("HEAD")) private void chestcavity$shear(net.minecraft.server.level.ServerLevel level,net.minecraft.sounds.SoundSource sound,net.minecraft.world.item.ItemStack tool,CallbackInfo ci){OrganUtil.shearSilk((LivingEntity)(Object)this);}
}
