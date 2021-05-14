package net.tigereye.chestcavity.registration;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.tigereye.chestcavity.ChestCavity;

public class CCFoodComponents {
    public static final FoodComponent ANIMAL_MUSCLE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(1).saturationModifier(.4f).meat().snack().build();
    public static final FoodComponent SMALL_ANIMAL_MUSCLE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(1).saturationModifier(.2f).meat().snack().build();
    public static final FoodComponent BURNT_MEAT_CHUNK_COMPONENT = new FoodComponent.Builder().hunger(1).saturationModifier(.8f).meat().snack().build();
    public static final FoodComponent RAW_BUTCHERED_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(2).saturationModifier(.4f).meat().build();
    public static final FoodComponent COOKED_BUTCHERED_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(.8f).meat().build();
    public static final FoodComponent RAW_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(2).saturationModifier(0.6f).meat().snack().build();
    public static final FoodComponent COOKED_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(1.2f).meat().snack().build();
    public static final FoodComponent RAW_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(.4f).meat().build();
    public static final FoodComponent COOKED_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(8).saturationModifier(.8f).meat().build();
    public static final FoodComponent RAW_RICH_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(.6f).meat().build();
    public static final FoodComponent COOKED_RICH_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(8).saturationModifier(1.2f).meat().build();
    public static final FoodComponent RAW_MINI_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(.4f).meat().build();
    public static final FoodComponent COOKED_MINI_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(6).saturationModifier(.8f).meat().build();
    public static final FoodComponent RAW_RICH_MINI_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(.6f).meat().build();
    public static final FoodComponent COOKED_RICH_MINI_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).meat().build();

    public static final FoodComponent INSECT_MUSCLE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(1).saturationModifier(.4f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 80), 1f).build();
    public static final FoodComponent RAW_TOXIC_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(2).saturationModifier(.4f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 80), 1f).build();
    public static final FoodComponent COOKED_TOXIC_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(.8f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 160, 1), 1f).build();
    public static final FoodComponent RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(2).saturationModifier(0.6f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 80), 1f).build();
    public static final FoodComponent COOKED_TOXIC_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(1.2f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 160, 1), 1f).build();
    public static final FoodComponent RAW_TOXIC_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(.4f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 80), 1f).build();
    public static final FoodComponent COOKED_TOXIC_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(8).saturationModifier(.8f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 160, 1), 1f).build();
    public static final FoodComponent RAW_RICH_TOXIC_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(.6f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 80), 1f).build();
    public static final FoodComponent COOKED_RICH_TOXIC_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(8).saturationModifier(1.2f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 160, 1), 1f).build();

    public static final FoodComponent ALIEN_MUSCLE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(1).saturationModifier(.4f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 20), 1f).build();
    public static final FoodComponent RAW_ALIEN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(2).saturationModifier(.4f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 80), 1f).build();
    public static final FoodComponent COOKED_ALIEN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(.8f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 1), 1f).build();
    public static final FoodComponent RAW_ALIEN_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(2).saturationModifier(0.6f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40), 1f).build();
    public static final FoodComponent COOKED_ALIEN_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(1.2f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 15, 1), 1f).build();
    public static final FoodComponent RAW_ALIEN_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(.4f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 80), 1f).build();
    public static final FoodComponent COOKED_ALIEN_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(8).saturationModifier(.8f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20, 1), 1f).build();
    public static final FoodComponent RAW_RICH_ALIEN_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(.6f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 320), 1f).build();
    public static final FoodComponent COOKED_RICH_ALIEN_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(8).saturationModifier(1.2f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 40, 1), 1f).build();

    public static final FoodComponent DRAGON_MUSCLE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(1).saturationModifier(.4f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 300), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 300), 1f)
            .alwaysEdible().build();
    public static final FoodComponent RAW_DRAGON_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(2).saturationModifier(.4f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 900), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 900), 1f)
            .alwaysEdible().build();
    public static final FoodComponent COOKED_DRAGON_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(.8f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 150, 1), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 150, 1), 1f)
            .alwaysEdible().build();
    public static final FoodComponent RAW_DRAGON_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(2).saturationModifier(0.6f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 90*20), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 90*20), 1f)
            .alwaysEdible().build();
    public static final FoodComponent COOKED_DRAGON_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(1.2f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 15*20, 1), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 15*20, 1), 1f)
            .alwaysEdible().build();
    public static final FoodComponent RAW_DRAGON_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(.4f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 480*20), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 480*20), 1f)
            .alwaysEdible().build();
    public static final FoodComponent COOKED_DRAGON_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(8).saturationModifier(.8f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 90*20, 1), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 90*20, 1), 1f)
            .alwaysEdible().build();
    public static final FoodComponent RAW_RICH_DRAGON_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(.6f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 960*20), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 960*20), 1f)
            .alwaysEdible().build();
    public static final FoodComponent COOKED_RICH_DRAGON_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(8).saturationModifier(1.2f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 180*20, 1), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 180*20, 1), 1f)
            .alwaysEdible().build();
    public static final FoodComponent DRAGON_HEART_FOOD_COMPONENT = new FoodComponent.Builder().hunger(1).saturationModifier(.4f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 30*20, 3), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 30*20, 3), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 2, 3), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.WITHER, 2, 3), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 2, 3), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 2, 3), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 2, 3), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 2, 3), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 2, 3), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 2, 3), 1f)
            .alwaysEdible().build();
    
    public static final FoodComponent HUMAN_MUSCLE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(2).saturationModifier(.4f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodComponent RAW_MAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(.4f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodComponent COOKED_MAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(.8f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodComponent RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(0.6f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodComponent COOKED_HUMAN_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(1.2f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodComponent RAW_HUMAN_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(5).saturationModifier(.4f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodComponent COOKED_HUMAN_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(9).saturationModifier(.8f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodComponent RAW_RICH_HUMAN_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(5).saturationModifier(.6f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 24000, 1), ChestCavity.config.RISK_OF_PRIONS).build();
    public static final FoodComponent COOKED_RICH_HUMAN_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(9).saturationModifier(1.2f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 24000, 0), ChestCavity.config.RISK_OF_PRIONS).build();

    public static final FoodComponent CUD_FOOD_COMPONENT = new FoodComponent.Builder().hunger(1).saturationModifier(.1f).build();
    public static final FoodComponent FURNACE_POWER_FOOD_COMPONENT = new FoodComponent.Builder().hunger(1).saturationModifier(2f).build();
    public static final FoodComponent DUMMY_FOOD_COMPONENT = new FoodComponent.Builder().hunger(1).saturationModifier(.1f).build();
}
