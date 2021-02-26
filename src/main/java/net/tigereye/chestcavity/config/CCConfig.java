package net.tigereye.chestcavity.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import net.tigereye.chestcavity.ChestCavity;

@Config(name = ChestCavity.MODID)
public class CCConfig implements ConfigData {

    public float ORGAN_BUNDLE_LOOTING_BOOST = .01f;
    public float UNIVERSAL_DONOR_RATE = .035f;

    public int HEARTBLEED_RATE = 20; //how fast you die from lacking a heart in ticks
    public int KIDNEY_RATE = 60; //how often the kidneys prevent blood poisoning in ticks
    public float APPENDIX_LUCK = .1f; //how lucky your appendix is
    public float HEART_HP = 4; //how much health each heart is worth
    public float MUSCLE_STRENGTH = 1f; //how much 8 stacks of muscles contribute to attack damage
    public float MUSCLE_SPEED = .5f; //how much 8 stacks of muscles contribute to movement speed
    public float BONE_DEFENSE = .5f; //damage reduction from 4 stacks of ribs

    public int ARROW_DODGE_DISTANCE = 32; //how far you can teleport when dodging projectiles
    public float FIREPROOF_DEFENSE = .75f; //damage reduction from 4 stacks of fireproof organs
    public int MAX_TELEPORT_ATTEMPTS = 5;
    public int RUMINATION_TIME = 400; //time to eat a unit of grass
    public int RUMINATION_GRASS_PER_SQUARE = 2; //number of grass units are in a square
    public int RUMINATION_SQUARES_PER_STOMACH = 3; //number of grass squares a stomach can hold
    public int SHULKER_BULLET_TARGETING_RANGE = 20;
    public float WITHERED_DURATION_FACTOR = .5f; //how much withered bones reduce wither duration

    public int ARROW_DODGE_COOLDOWN = 200; //how often an entity is allowed to dodge projectiles
    public int EXPLOSION_COOLDOWN = 200; //how often an entity is allowed to try exploding
    public int FORCEFUL_SPIT_COOLDOWN = 20; //how often an entity is allowed to try exploding
    public int GHASTLY_COOLDOWN = 60; //how often an entity is allowed to fire ghast bombs
    public int PYROMANCY_COOLDOWN = 78; //how often an entity is allowed to spew fireballs
    public int SHULKER_BULLET_COOLDOWN = 100; //how often an entity is allowed to shoot bullets
    public int SILK_COOLDOWN = 20; //how often an entity is allowed to lay silk
    public int VENOM_COOLDOWN = 40; //how often an entity is allowed to poison targets

    public int ORGAN_REJECTION_DAMAGE = 2; //how much rejecting organs hurts
    public int ORGAN_REJECTION_RATE = 600; //base speed of organ rejection
    public float RISK_OF_PRIONS = .01f; //risk of debuffs from human-derived foods

    public boolean CAN_OPEN_OTHER_PLAYERS = false;
    public boolean KEEP_CHEST_CAVITY = false;
    public boolean DISABLE_ORGAN_REJECTION = false;

    public boolean AGE_OF_EXILE_INTEGRATION = true;
    public boolean ANTHROPOPHAGY_INTEGRATION = true;
    public boolean BACKROOMS_INTEGRATION = true;
    public boolean BEWITCHMENT_INTEGRATION = true;
    public boolean BIOME_MAKEOVER_INTEGRATION = true;
    public boolean DIREBATS_INTEGRATION = true;
    public boolean RATS_MISCHIEF_INTEGRATION = true;
    public boolean REQUIEM_INTEGRATION = true;
}
