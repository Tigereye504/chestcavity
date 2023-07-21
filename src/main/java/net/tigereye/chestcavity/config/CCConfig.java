package net.tigereye.chestcavity.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.tigereye.chestcavity.ChestCavity;

@Config(name = ChestCavity.MODID)
public class CCConfig implements ConfigData {

    @ConfigEntry.Category("core")
    public float ORGAN_BUNDLE_LOOTING_BOOST = .04f;
    @ConfigEntry.Category("core")
    public float UNIVERSAL_DONOR_RATE = .1f;
    @ConfigEntry.Category("core")
    public int ORGAN_REJECTION_DAMAGE = 2; //how much rejecting organs hurts
    @ConfigEntry.Category("core")
    public int ORGAN_REJECTION_RATE = 600; //base speed of organ rejection
    @ConfigEntry.Category("core")
    public int HEARTBLEED_RATE = 20; //how fast you die from lacking a heart in ticks
    @ConfigEntry.Category("core")
    public int KIDNEY_RATE = 60; //how often the kidneys prevent blood poisoning in ticks
    @ConfigEntry.Category("core")
    public float FILTRATION_DURATION_FACTOR = 1f; //how much extra kidneys reduce poison duration
    @ConfigEntry.Category("core")
    public float APPENDIX_LUCK = .1f; //how lucky your appendix is
    @ConfigEntry.Category("core")
    public float HEART_HP = 4; //how much health each heart is worth
    @ConfigEntry.Category("core")
    public float MUSCLE_STRENGTH = 1f; //how much 8 stacks of muscles contribute to attack damage
    @ConfigEntry.Category("core")
    public float MUSCLE_SPEED = .5f; //how much 8 stacks of muscles contribute to movement speed
    @ConfigEntry.Category("core")
    public float NERVES_HASTE = .1f; //how much a spine contributes to mining and attack speed
    @ConfigEntry.Category("core")
    public float BONE_DEFENSE = .5f; //damage reduction from 4 stacks of ribs
    @ConfigEntry.Category("core")
    public float RISK_OF_PRIONS = .01f; //risk of debuffs from human-derived foods
    @ConfigEntry.Category("core")
    public int CHEST_OPENER_ABSOLUTE_HEALTH_THRESHOLD = 20; //health below which a chest can be opened
    @ConfigEntry.Category("core")
    public float CHEST_OPENER_FRACTIONAL_HEALTH_THRESHOLD = .5f; //health below which a chest can be opened
    @ConfigEntry.Category("core")
    public boolean CAN_OPEN_OTHER_PLAYERS = false;
    @ConfigEntry.Category("core")
    public boolean KEEP_CHEST_CAVITY = false;
    @ConfigEntry.Category("core")
    public boolean DISABLE_ORGAN_REJECTION = false;

    @ConfigEntry.Category("more")
    public int ARROW_DODGE_DISTANCE = 32; //how far you can teleport when dodging projectiles
    @ConfigEntry.Category("more")
    public float BUFF_PURGING_DURATION_FACTOR = .5f; //how much withered bones reduce wither duration
    @ConfigEntry.Category("more")
    public int CRYSTALSYNTHESIS_RANGE = 32; //range at which you can link to a End Crystal
    @ConfigEntry.Category("more")
    public int CRYSTALSYNTHESIS_FREQUENCY = 10; //how often the link to an End Crystal is updated and perks gained
    @ConfigEntry.Category("more")
    public float FIREPROOF_DEFENSE = .75f; //damage reduction from 4 stacks of fireproof organs
    @ConfigEntry.Category("more")
    public float IMPACT_DEFENSE = .75f; //damage reduction from 4 stacks of impact resistant organs
    @ConfigEntry.Category("more")
    public float IRON_REPAIR_PERCENT = .25f; //damage reduction from 4 stacks of impact resistant organs
    @ConfigEntry.Category("more")
    public float LAUNCHING_POWER = .1f; //upward velocity per launching
    @ConfigEntry.Category("more")
    public int MAX_TELEPORT_ATTEMPTS = 5;
    @ConfigEntry.Category("more")
    public int PHOTOSYNTHESIS_FREQUENCY = 50; //how many ticks 8 photosynthetic organs in direct sunlight need to restore 1 hunger
    @ConfigEntry.Category("more")
    public int GLOWING_STRENGTH = 64; //how high glowing has to be to count as 1 light level for the purposes of photosynthesis
    @ConfigEntry.Category("more")
    public int RUMINATION_TIME = 400; //time to eat a unit of grass
    @ConfigEntry.Category("more")
    public int RUMINATION_GRASS_PER_SQUARE = 2; //number of grass units are in a square
    @ConfigEntry.Category("more")
    public int RUMINATION_SQUARES_PER_STOMACH = 3; //number of grass squares a stomach can hold
    @ConfigEntry.Category("more")
    public int SHULKER_BULLET_TARGETING_RANGE = 20;
    @ConfigEntry.Category("more")
    public float SWIMSPEED_FACTOR = 1f; //how much 8 swimming muscles boost swim speed
    @ConfigEntry.Category("more")
    public float WITHERED_DURATION_FACTOR = .5f; //how much withered bones reduce wither duration

    @ConfigEntry.Category("cooldown")
    public int ARROW_DODGE_COOLDOWN = 200; //how often an entity is allowed to dodge projectiles
    @ConfigEntry.Category("cooldown")
    public int DRAGON_BOMB_COOLDOWN = 200; //how often an entity is allowed to fire bombs
    @ConfigEntry.Category("cooldown")
    public int DRAGON_BREATH_COOLDOWN = 200; //how often an entity is allowed to fire bombs
    @ConfigEntry.Category("cooldown")
    public int EXPLOSION_COOLDOWN = 200; //how often an entity is allowed to try exploding
    @ConfigEntry.Category("cooldown")
    public int FORCEFUL_SPIT_COOLDOWN = 20; //how often an entity is allowed to spit
    @ConfigEntry.Category("cooldown")
    public int GHASTLY_COOLDOWN = 60; //how often an entity is allowed to fire ghast bombs
    @ConfigEntry.Category("cooldown")
    public int IRON_REPAIR_COOLDOWN = 1200; //how often an entity is allowed to use iron to heal
    @ConfigEntry.Category("cooldown")
    public int PYROMANCY_COOLDOWN = 78; //how often an entity is allowed to spew fireballs
    @ConfigEntry.Category("cooldown")
    public int SHULKER_BULLET_COOLDOWN = 100; //how often an entity is allowed to shoot bullets
    @ConfigEntry.Category("cooldown")
    public int SILK_COOLDOWN = 20; //how often an entity is allowed to lay silk
    @ConfigEntry.Category("cooldown")
    public int VENOM_COOLDOWN = 40; //how often an entity is allowed to poison targets

    @ConfigEntry.Category("integration")
    public boolean BACKROOMS_INTEGRATION = true;
    @ConfigEntry.Category("integration")
    public int BACKROOMS_CHEST_ORGAN_LOOT_ATTEMPTS = 2;
    @ConfigEntry.Category("integration")
    public float BACKROOMS_CHEST_ORGAN_LOOT_CHANCE = 0.2f;
    @ConfigEntry.Category("integration")
    public boolean REQUIEM_INTEGRATION = true;
}
