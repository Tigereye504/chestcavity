package net.tigereye.chestcavity.mixin;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.world.entity.*;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.chestcavities.instance.*;
import net.tigereye.chestcavity.util.*;

@Mixin(net.minecraft.world.food.FoodData.class)
public abstract class MixinHungerManager {
    @Shadow private int tickTimer;
    @Shadow private float exhaustionLevel;
    @Unique private ChestCavityEntity chestcavity$player;
    @Inject(method="tick",at=@At("HEAD")) private void chestcavity$tick(net.minecraft.server.level.ServerPlayer player,CallbackInfo ci){
        chestcavity$player=(ChestCavityEntity)player;tickTimer=ChestCavityUtil.applySpleenMetabolism(chestcavity$player.getChestCavityInstance(),tickTimer);
    }
    @ModifyVariable(method="addExhaustion",at=@At("HEAD"),argsOnly=true,ordinal=0) private float chestcavity$endurance(float amount){
        if(chestcavity$player==null)return amount;
        if(!Float.isFinite(exhaustionLevel))exhaustionLevel=0;
        var cc=chestcavity$player.getChestCavityInstance();var id=net.tigereye.chestcavity.registration.CCOrganScores.ENDURANCE;
        float delta=cc.getOrganScore(id)-cc.getChestCavityType().getDefaultOrganScore(id);
        return delta>0?amount/(1+delta/2):amount*(1-delta/2);
    }
}
