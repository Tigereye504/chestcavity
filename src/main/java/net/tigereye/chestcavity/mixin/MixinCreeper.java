package net.tigereye.chestcavity.mixin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.world.entity.*;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.chestcavities.instance.*;
import net.tigereye.chestcavity.util.*;

@Mixin(net.minecraft.world.entity.monster.Creeper.class)
public abstract class MixinCreeper {
    @Shadow private int swell;
    @Inject(method="tick",at=@At("HEAD")) private void chestcavity$defuse(CallbackInfo ci){
        var self=(LivingEntity)(Object)this;
        if(self.isAlive() && swell>1)ChestCavityEntity.of(self).ifPresent(c->{var cc=c.getChestCavityInstance();if(cc.opened&&cc.getOrganScore(net.tigereye.chestcavity.registration.CCOrganScores.CREEPY)<=0)swell=1;});
    }
}
