package net.tigereye.chestcavity.registration;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.mob_effect.CCStatusEffect;
import net.tigereye.chestcavity.mob_effect.FurnacePower;
import net.tigereye.chestcavity.mob_effect.OrganRejection;
import net.tigereye.chestcavity.mob_effect.Ruminating;

public class CCStatusEffects {
    private static final java.util.Map<String, MobEffect> ALL = new java.util.LinkedHashMap<>();

    public static final net.minecraft.core.Holder<MobEffect> ORGAN_REJECTION = register("organ_rejection", new OrganRejection());
    public static final net.minecraft.core.Holder<MobEffect> ARROW_DODGE_COOLDOWN = register("arrow_dodge_cooldown", new CCStatusEffect(MobEffectCategory.NEUTRAL,0x000000));
    public static final net.minecraft.core.Holder<MobEffect> DRAGON_BOMB_COOLDOWN = register("dragon_bomb_cooldown", new CCStatusEffect(MobEffectCategory.NEUTRAL,0x000000));
    public static final net.minecraft.core.Holder<MobEffect> DRAGON_BREATH_COOLDOWN = register("dragon_breath_cooldown", new CCStatusEffect(MobEffectCategory.NEUTRAL,0x000000));
    public static final net.minecraft.core.Holder<MobEffect> EXPLOSION_COOLDOWN = register("explosion_cooldown", new CCStatusEffect(MobEffectCategory.NEUTRAL,0x000000));
    public static final net.minecraft.core.Holder<MobEffect> FORCEFUL_SPIT_COOLDOWN = register("forceful_spit_cooldown", new CCStatusEffect(MobEffectCategory.NEUTRAL,0x000000));
    public static final net.minecraft.core.Holder<MobEffect> FURNACE_POWER = register("furnace_power", new FurnacePower());
    public static final net.minecraft.core.Holder<MobEffect> GHASTLY_COOLDOWN = register("ghastly_cooldown", new CCStatusEffect(MobEffectCategory.NEUTRAL,0x000000));
    public static final net.minecraft.core.Holder<MobEffect> IRON_REPAIR_COOLDOWN = register("iron_repair_cooldown", new CCStatusEffect(MobEffectCategory.NEUTRAL,0x000000));
    public static final net.minecraft.core.Holder<MobEffect> PYROMANCY_COOLDOWN = register("pyromancy_cooldown", new CCStatusEffect(MobEffectCategory.NEUTRAL,0x000000));
    public static final net.minecraft.core.Holder<MobEffect> RUMINATING = register("ruminating", new Ruminating());
    public static final net.minecraft.core.Holder<MobEffect> SHULKER_BULLET_COOLDOWN = register("shulker_bullet_cooldown", new CCStatusEffect(MobEffectCategory.NEUTRAL,0x000000));
    public static final net.minecraft.core.Holder<MobEffect> SILK_COOLDOWN = register("silk_cooldown", new CCStatusEffect(MobEffectCategory.NEUTRAL,0x000000));
    public static final net.minecraft.core.Holder<MobEffect> VENOM_COOLDOWN = register("venom_cooldown", new CCStatusEffect(MobEffectCategory.NEUTRAL,0x000000));
    public static final net.minecraft.core.Holder<MobEffect> WATER_VULNERABILITY = register("water_vulnerability", new CCStatusEffect(MobEffectCategory.NEUTRAL,0x000000));


    private static net.minecraft.core.Holder<MobEffect> register(String name,MobEffect effect) {
        return Registry.registerForHolder(net.minecraft.core.registries.BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath("chestcavity",name),effect);
    }
    public static void register() { }
}
