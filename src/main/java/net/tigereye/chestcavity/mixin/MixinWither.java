package net.tigereye.chestcavity.mixin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.world.entity.*;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.chestcavities.instance.*;
import net.tigereye.chestcavity.util.*;

@Mixin(net.minecraft.world.entity.boss.wither.WitherBoss.class)
public abstract class MixinWither {
    @Inject(method="dropCustomDeathLoot",at=@At("HEAD"),cancellable=true) private void chestcavity$star(net.minecraft.server.level.ServerLevel level,net.minecraft.world.damagesource.DamageSource source,boolean playerKilled,CallbackInfo ci){
        var cc=((ChestCavityEntity)this).getChestCavityInstance();if(cc.opened&&cc.inventory.countItem(net.minecraft.world.item.Items.NETHER_STAR)==0)ci.cancel();
    }
}
