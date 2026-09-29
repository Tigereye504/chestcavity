package net.tigereye.chestcavity.registration;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.tigereye.chestcavity.util.FoodSpec;

import net.tigereye.chestcavity.ChestCavity;

public class CCFoodComponents {
    public static final FoodSpec ANIMAL_MUSCLE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(1).saturationMod(.4f).meat().fast().build();
    public static final FoodSpec SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(1).saturationMod(.2f).meat().fast().build();
    public static final FoodSpec BURNT_MEAT_CHUNK_COMPONENT = new FoodSpec.Builder().nutrition(1).saturationMod(.8f).meat().fast().build();
    public static final FoodSpec RAW_BUTCHERED_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(2).saturationMod(.4f).meat().build();
    public static final FoodSpec COOKED_BUTCHERED_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(3).saturationMod(.8f).meat().build();
    public static final FoodSpec RAW_ORGAN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(2).saturationMod(0.6f).meat().fast().build();
    public static final FoodSpec COOKED_ORGAN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(3).saturationMod(1.2f).meat().fast().build();
    public static final FoodSpec RAW_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(4).saturationMod(.4f).meat().build();
    public static final FoodSpec COOKED_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(8).saturationMod(.8f).meat().build();
    public static final FoodSpec RAW_RICH_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(4).saturationMod(.6f).meat().build();
    public static final FoodSpec COOKED_RICH_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(8).saturationMod(1.2f).meat().build();
    public static final FoodSpec RAW_MINI_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(3).saturationMod(.4f).meat().build();
    public static final FoodSpec COOKED_MINI_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(6).saturationMod(.8f).meat().build();
    public static final FoodSpec RAW_RICH_MINI_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(3).saturationMod(.6f).meat().build();
    public static final FoodSpec COOKED_RICH_MINI_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(6).saturationMod(1.2f).meat().build();

    public static final FoodSpec ROTTEN_MUSCLE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(1).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.8F).meat().build();
    public static final FoodSpec ROTTEN_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(8).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.8F).meat().build();

    public static final FoodSpec INSECT_MUSCLE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(1).saturationMod(.4f).meat().fast()
            .effect(new MobEffectInstance(MobEffects.POISON, 80), 1f).build();
    public static final FoodSpec RAW_TOXIC_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(2).saturationMod(.4f).meat()
            .effect(new MobEffectInstance(MobEffects.POISON, 80), 1f).build();
    public static final FoodSpec COOKED_TOXIC_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(3).saturationMod(.8f).meat()
            .effect(new MobEffectInstance(MobEffects.NAUSEA, 160, 1), 1f).build();
    public static final FoodSpec RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(2).saturationMod(0.6f).meat().fast()
            .effect(new MobEffectInstance(MobEffects.POISON, 80), 1f).build();
    public static final FoodSpec COOKED_TOXIC_ORGAN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(3).saturationMod(1.2f).meat().fast()
            .effect(new MobEffectInstance(MobEffects.NAUSEA, 160, 1), 1f).build();
    public static final FoodSpec RAW_TOXIC_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(4).saturationMod(.4f).meat()
            .effect(new MobEffectInstance(MobEffects.POISON, 80), 1f).build();
    public static final FoodSpec COOKED_TOXIC_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(8).saturationMod(.8f).meat()
            .effect(new MobEffectInstance(MobEffects.NAUSEA, 160, 1), 1f).build();
    public static final FoodSpec RAW_RICH_TOXIC_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(4).saturationMod(.6f).meat()
            .effect(new MobEffectInstance(MobEffects.POISON, 80), 1f).build();
    public static final FoodSpec COOKED_RICH_TOXIC_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(8).saturationMod(1.2f).meat()
            .effect(new MobEffectInstance(MobEffects.NAUSEA, 160, 1), 1f).build();

    public static final FoodSpec ALIEN_MUSCLE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(1).saturationMod(.4f).meat().fast()
            .effect(new MobEffectInstance(MobEffects.LEVITATION, 20), 1f).build();
    public static final FoodSpec RAW_ALIEN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(2).saturationMod(.4f).meat()
            .effect(new MobEffectInstance(MobEffects.LEVITATION, 80), 1f).build();
    public static final FoodSpec COOKED_ALIEN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(3).saturationMod(.8f).meat()
            .effect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 1), 1f).build();
    public static final FoodSpec RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(2).saturationMod(0.6f).meat().fast()
            .effect(new MobEffectInstance(MobEffects.LEVITATION, 40), 1f).build();
    public static final FoodSpec COOKED_ALIEN_ORGAN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(3).saturationMod(1.2f).meat().fast()
            .effect(new MobEffectInstance(MobEffects.SLOW_FALLING, 15, 1), 1f).build();
    public static final FoodSpec RAW_ALIEN_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(4).saturationMod(.4f).meat()
            .effect(new MobEffectInstance(MobEffects.LEVITATION, 80), 1f).build();
    public static final FoodSpec COOKED_ALIEN_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(8).saturationMod(.8f).meat()
            .effect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 1), 1f).build();
    public static final FoodSpec RAW_RICH_ALIEN_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(4).saturationMod(.6f).meat()
            .effect(new MobEffectInstance(MobEffects.LEVITATION, 320), 1f).build();
    public static final FoodSpec COOKED_RICH_ALIEN_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(8).saturationMod(1.2f).meat()
            .effect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 1), 1f).build();

    public static final FoodSpec DRAGON_MUSCLE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(1).saturationMod(.4f).meat().fast()
            .effect(new MobEffectInstance(MobEffects.STRENGTH, 300), 1f)
            .effect(new MobEffectInstance(MobEffects.HASTE, 300), 1f)
            .alwaysEat().build();
    public static final FoodSpec RAW_DRAGON_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(2).saturationMod(.4f).meat()
            .effect(new MobEffectInstance(MobEffects.STRENGTH, 900), 1f)
            .effect(new MobEffectInstance(MobEffects.HASTE, 900), 1f)
            .alwaysEat().build();
    public static final FoodSpec COOKED_DRAGON_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(3).saturationMod(.8f).meat()
            .effect(new MobEffectInstance(MobEffects.STRENGTH, 150, 1), 1f)
            .effect(new MobEffectInstance(MobEffects.HASTE, 150, 1), 1f)
            .alwaysEat().build();
    public static final FoodSpec RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(2).saturationMod(0.6f).meat().fast()
            .effect(new MobEffectInstance(MobEffects.STRENGTH, 90*20), 1f)
            .effect(new MobEffectInstance(MobEffects.HASTE, 90*20), 1f)
            .alwaysEat().build();
    public static final FoodSpec COOKED_DRAGON_ORGAN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(3).saturationMod(1.2f).meat().fast()
            .effect(new MobEffectInstance(MobEffects.STRENGTH, 15*20, 1), 1f)
            .effect(new MobEffectInstance(MobEffects.HASTE, 15*20, 1), 1f)
            .alwaysEat().build();
    public static final FoodSpec RAW_DRAGON_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(4).saturationMod(.4f).meat()
            .effect(new MobEffectInstance(MobEffects.STRENGTH, 480*20), 1f)
            .effect(new MobEffectInstance(MobEffects.HASTE, 480*20), 1f)
            .alwaysEat().build();
    public static final FoodSpec COOKED_DRAGON_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(8).saturationMod(.8f).meat()
            .effect(new MobEffectInstance(MobEffects.STRENGTH, 90*20, 1), 1f)
            .effect(new MobEffectInstance(MobEffects.HASTE, 90*20, 1), 1f)
            .alwaysEat().build();
    public static final FoodSpec RAW_RICH_DRAGON_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(4).saturationMod(.6f).meat()
            .effect(new MobEffectInstance(MobEffects.STRENGTH, 960*20), 1f)
            .effect(new MobEffectInstance(MobEffects.HASTE, 960*20), 1f)
            .alwaysEat().build();
    public static final FoodSpec COOKED_RICH_DRAGON_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(8).saturationMod(1.2f).meat()
            .effect(new MobEffectInstance(MobEffects.STRENGTH, 180*20, 1), 1f)
            .effect(new MobEffectInstance(MobEffects.HASTE, 180*20, 1), 1f)
            .alwaysEat().build();
    public static final FoodSpec DRAGON_HEART_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(1).saturationMod(.4f).meat().fast()
            .effect(new MobEffectInstance(MobEffects.STRENGTH, 30*20, 3), 1f)
            .effect(new MobEffectInstance(MobEffects.HASTE, 30*20, 3), 1f)
            .effect(new MobEffectInstance(MobEffects.POISON, 2, 3), 1f)
            .effect(new MobEffectInstance(MobEffects.WITHER, 2, 3), 1f)
            .effect(new MobEffectInstance(MobEffects.SLOWNESS, 2, 3), 1f)
            .effect(new MobEffectInstance(MobEffects.HUNGER, 2, 3), 1f)
            .effect(new MobEffectInstance(MobEffects.NAUSEA, 2, 3), 1f)
            .effect(new MobEffectInstance(MobEffects.BLINDNESS, 2, 3), 1f)
            .effect(new MobEffectInstance(MobEffects.SLOW_FALLING, 2, 3), 1f)
            .effect(new MobEffectInstance(MobEffects.LEVITATION, 2, 3), 1f)
            .alwaysEat().build();
    
    public static final FoodSpec HUMAN_MUSCLE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(2).saturationMod(.4f).meat().fast()
            .effect(new MobEffectInstance(MobEffects.SLOWNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.WEAKNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodSpec RAW_MAN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(3).saturationMod(.4f).meat()
            .effect(new MobEffectInstance(MobEffects.SLOWNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.WEAKNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodSpec COOKED_MAN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(4).saturationMod(.8f).meat()
            .effect(new MobEffectInstance(MobEffects.SLOWNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.WEAKNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodSpec RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(3).saturationMod(0.6f).meat().fast()
            .effect(new MobEffectInstance(MobEffects.SLOWNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.WEAKNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodSpec COOKED_HUMAN_ORGAN_MEAT_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(4).saturationMod(1.2f).meat().fast()
            .effect(new MobEffectInstance(MobEffects.SLOWNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.WEAKNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodSpec RAW_HUMAN_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(5).saturationMod(.4f).meat()
            .effect(new MobEffectInstance(MobEffects.SLOWNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.WEAKNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodSpec COOKED_HUMAN_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(9).saturationMod(.8f).meat()
            .effect(new MobEffectInstance(MobEffects.SLOWNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.WEAKNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodSpec RAW_RICH_HUMAN_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(5).saturationMod(.6f).meat()
            .effect(new MobEffectInstance(MobEffects.SLOWNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.WEAKNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodSpec COOKED_RICH_HUMAN_SAUSAGE_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(9).saturationMod(1.2f).meat()
            .effect(new MobEffectInstance(MobEffects.SLOWNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).effect(new MobEffectInstance(MobEffects.WEAKNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).build();

    public static final FoodSpec CUD_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(1).saturationMod(.1f).build();
    public static final FoodSpec FURNACE_POWER_FOOD_COMPONENT = new FoodSpec.Builder().nutrition(1).saturationMod(.6f).build();
}
