package net.tigereye.chestcavity.mixin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.world.entity.*;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.chestcavities.instance.*;
import net.tigereye.chestcavity.util.*;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @ModifyVariable(method="checkFallDamage",at=@At("HEAD"),argsOnly=true,ordinal=0)
    private double chestcavity$fall(double height){
        if(height>=0)return height;
        return ChestCavityEntity.of((Entity)(Object)this).map(c->height*(1-c.getChestCavityInstance().getOrganScore(net.tigereye.chestcavity.registration.CCOrganScores.BUOYANT)/3)).orElse(height);
    }
}
