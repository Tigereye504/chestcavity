package net.tigereye.chestcavity.util;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.effect.MobEffectInstance;
public record FoodSpec(FoodProperties food, Consumable consumable, boolean meat) {
    public static final class Builder {
        private final FoodProperties.Builder food = new FoodProperties.Builder();
        private final Consumable.Builder consumable = Consumable.builder();
        private boolean meat;
        public Builder nutrition(int n) { food.nutrition(n); return this; }
        public Builder saturationMod(float s) { food.saturationModifier(s); return this; }
        public Builder meat() { meat=true; return this; }
        public Builder fast() { consumable.consumeSeconds(0.8f); return this; }
        public Builder alwaysEat() { food.alwaysEdible(); return this; }
        public Builder alwaysEdible() { food.alwaysEdible(); return this; }
        public Builder effect(MobEffectInstance effect,float probability) { consumable.onConsume(new ApplyStatusEffectsConsumeEffect(effect,probability)); return this; }
        public FoodSpec build() { return new FoodSpec(food.build(),consumable.build(),meat); }
    }
}
