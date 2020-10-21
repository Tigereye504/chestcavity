package net.tigereye.chestcavity.mixin;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.tigereye.chestcavity.interfaces.CCStatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StatusEffectInstance.class)
public abstract class MixinStatusEffectInstance implements CCStatusEffectInstance {

	@Shadow
	private int duration;

	public void CC_setDuration(int duration) {
		this.duration = duration;
	}
}
