package net.tigereye.chestcavity.mixin;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.tigereye.chestcavity.interfaces.CCStatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StatusEffect.class)
public class MixinStatusEffect implements CCStatusEffect {

    @Shadow
    private StatusEffectCategory category;

    @Override
    public boolean CC_IsHarmful() {
        return (category == StatusEffectCategory.HARMFUL);
    }

    @Override
    public boolean CC_IsBeneficial() {
        return (category == StatusEffectCategory.BENEFICIAL);
    }
}
