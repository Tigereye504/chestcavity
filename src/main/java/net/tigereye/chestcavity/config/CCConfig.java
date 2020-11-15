package net.tigereye.chestcavity.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import net.tigereye.chestcavity.ChestCavity;

@Config(name = ChestCavity.MODID)
public class CCConfig implements ConfigData {
    public float ORGAN_BUNDLE_DROP_RATE = .025f;
    public float ORGAN_BUNDLE_LOOTING_BOOST = .01f;
    public float UNIVERSAL_DONOR_RATE = .06f;

    public int HEARTBLEED_RATE = 20; //how fast you die from lacking a heart in ticks
    public int LIVER_RATE = 40; //how often the liver purifies status effects in ticks
    public int KIDNEY_RATE = 59; //how often the kidneys prevent blood poisoning in ticks
        //avoid clean multiples or factors of LIVER_SPEED to avoid strange sweet or sour spots in kidney/liver scores.
    public float APPENDIX_LUCK = .1f; //how lucky your appendix is
    public float HEART_HP = 4; //how much health each heart is worth
    public float MUSCLE_STRENGTH = 1f; //how much 8 stacks of muscles contribute to attack damage
    public float MUSCLE_SPEED = .5f; //how much 8 stacks of muscles contribute to movement speed

    public int EXPLOSION_COOLDOWN = 20; //how often an entity is allowed to try exploding
    public int ORGAN_REJECTION_DAMAGE = 2; //how much rejecting organs hurts
    public int ORGAN_REJECTION_RATE = 600; //base speed of organ rejection
    public float RISK_OF_PRIONS = .01f; //risk of debuffs from human-derived foods

    public boolean WENDIGOISM_INTEGRATION = true;
    public boolean BACKROOMS_INTEGRATION = true;
}
