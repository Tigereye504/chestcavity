package net.tigereye.chestcavity.mixin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.world.entity.*;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.chestcavities.instance.*;
import net.tigereye.chestcavity.util.*;

@Mixin(net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect.class)
public abstract class MixinFoodEffects {
    @Inject(method="apply",at=@At("HEAD"),cancellable=true) private void chestcavity$rotgut(net.minecraft.world.level.Level level,net.minecraft.world.item.ItemStack stack,LivingEntity user,CallbackInfoReturnable<Boolean> cir){
        var cc=((ChestCavityEntity)user).getChestCavityInstance();var score=net.tigereye.chestcavity.registration.CCOrganScores.ROTGUT;
        if(cc.getOrganScore(score)+cc.getOrganScore(net.tigereye.chestcavity.registration.CCOrganScores.ROT_DIGESTION)<=0 || !stack.is(net.tigereye.chestcavity.registration.CCTags.ROTTEN_FOOD))return;
        var effect=(net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect)(Object)this;
        if(effect.effects().stream().noneMatch(e -> e.getEffect().equals(net.minecraft.world.effect.MobEffects.HUNGER)))return;
        boolean changed=false;if(user.getRandom().nextFloat()<effect.probability())for(var status:effect.effects())if(!status.getEffect().equals(net.minecraft.world.effect.MobEffects.HUNGER))changed|=user.addEffect(new net.minecraft.world.effect.MobEffectInstance(status));
        cir.setReturnValue(changed);
    }
}
