package net.tigereye.chestcavity.registration;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class CCFoodComponents {
    public static final FoodComponent ANIMAL_MUSCLE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(1).saturationModifier(.4f).meat().snack().build();
    public static final FoodComponent BURNT_MEAT_CHUNK_COMPONENT = new FoodComponent.Builder().hunger(1).saturationModifier(.8f).meat().snack().build();
    public static final FoodComponent RAW_BUTCHERED_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(2).saturationModifier(.4f).meat().build();
    public static final FoodComponent COOKED_BUTCHERED_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(.8f).meat().build();
    public static final FoodComponent RAW_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(2).saturationModifier(0.6f).meat().snack().build();
    public static final FoodComponent COOKED_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(1.2f).meat().snack().build();
    public static final FoodComponent RAW_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(.4f).meat().build();
    public static final FoodComponent COOKED_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(8).saturationModifier(.8f).meat().build();
    public static final FoodComponent RAW_RICH_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(.6f).meat().build();
    public static final FoodComponent COOKED_RICH_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(8).saturationModifier(1.2f).meat().build();
    public static final FoodComponent RAW_RIB_STEAK_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(.4f).meat().build();
    public static final FoodComponent COOKED_RIB_STEAK_FOOD_COMPONENT = new FoodComponent.Builder().hunger(8).saturationModifier(.8f).meat().build();

    public static final FoodComponent HUMAN_MUSCLE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(2).saturationModifier(.4f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1), 0.01F).build();
    public static final FoodComponent BURNT_HUMAN_MEAT_CHUNK_COMPONENT = new FoodComponent.Builder().hunger(2).saturationModifier(.8f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1), 0.01F).build();
    public static final FoodComponent RAW_MAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(.4f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1), 0.01F).build();
    public static final FoodComponent COOKED_MAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(.8f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1), 0.01F).build();
    public static final FoodComponent RAW_HUMAN_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(3).saturationModifier(0.6f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1), 0.01F).build();
    public static final FoodComponent COOKED_HUMAN_ORGAN_MEAT_FOOD_COMPONENT = new FoodComponent.Builder().hunger(4).saturationModifier(1.2f).meat().snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1), 0.01F).build();
    public static final FoodComponent RAW_BLOOD_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(5).saturationModifier(.4f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1), 0.01F).build();
    public static final FoodComponent COOKED_BLOOD_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(9).saturationModifier(.8f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1), 0.01F).build();
    public static final FoodComponent RAW_RICH_BLOOD_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(5).saturationModifier(.6f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1), 0.01F).build();
    public static final FoodComponent COOKED_RICH_BLOOD_SAUSAGE_FOOD_COMPONENT = new FoodComponent.Builder().hunger(9).saturationModifier(1.2f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1), 0.01F).build();
    public static final FoodComponent RAW_HUMAN_RIB_STEAK_FOOD_COMPONENT = new FoodComponent.Builder().hunger(5).saturationModifier(.4f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1), 0.01F).build();
    public static final FoodComponent COOKED_HUMAN_RIB_STEAK_FOOD_COMPONENT = new FoodComponent.Builder().hunger(9).saturationModifier(.8f).meat()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 600, 1), 0.01F).statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1), 0.01F).build();
}
