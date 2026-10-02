package net.tigereye.chestcavity.mixin;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import net.tigereye.chestcavity.interfaces.CCStatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MobEffect.class)
public class MixinStatusEffect implements CCStatusEffect {

    @Shadow
    private MobEffectCategory category;

    @Override
    public boolean CC_IsHarmful() {
        return (category == MobEffectCategory.HARMFUL);
    }

    @Override
    public boolean CC_IsBeneficial() {
        return (category == MobEffectCategory.BENEFICIAL);
    }
}
