package net.tigereye.chestcavity.mixin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.world.entity.*;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.chestcavities.instance.*;
import net.tigereye.chestcavity.util.*;

@Mixin(net.minecraft.world.entity.projectile.throwableitemprojectile.AbstractThrownPotion.class)
public abstract class MixinPotionEntity {
    @Inject(method="affectEntitiesAround",at=@At("TAIL")) private void chestcavity$water(net.minecraft.server.level.ServerLevel level,net.minecraft.world.item.alchemy.PotionContents potion,CallbackInfo ci){
        if(potion.is(net.minecraft.tags.PotionTags.HURTS_WATER_SENSITIVE_ENTITIES))ChestCavityUtil.splashHydrophobicWithWater((net.minecraft.world.entity.projectile.throwableitemprojectile.AbstractThrownPotion)(Object)this);
    }
}
