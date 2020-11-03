package net.tigereye.chestcavity.mixin;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.tigereye.chestcavity.interfaces.CCStatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StatusEffect.class)
public class MixinStatusEffect implements CCStatusEffect {

    @Shadow
    private StatusEffectType type;

    @Override
    public boolean CC_IsHarmful() {
        return (type == StatusEffectType.HARMFUL);
    }
}
