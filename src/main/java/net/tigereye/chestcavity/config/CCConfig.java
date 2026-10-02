package net.tigereye.chestcavity.config;

import net.tigereye.chestcavity.ChestCavity;

public class CCConfig {

    public float ORGAN_BUNDLE_LOOTING_BOOST = .04f;

    public float UNIVERSAL_DONOR_RATE = .1f;

    public int ORGAN_REJECTION_DAMAGE = 2; //how much rejecting organs hurts

    public int ORGAN_REJECTION_RATE = 600; //base speed of organ rejection

    public int HEARTBLEED_RATE = 20; //how fast you die from lacking a heart in ticks

    public int KIDNEY_RATE = 60; //how often the kidneys prevent blood poisoning in ticks

    public float FILTRATION_DURATION_FACTOR = 1f; //how much extra kidneys reduce poison duration

    public float APPENDIX_LUCK = .1f; //how lucky your appendix is

    public float HEART_HP = 4; //how much health each heart is worth

    public float MUSCLE_STRENGTH = 1f; //how much 8 stacks of muscles contribute to attack damage

    public float MUSCLE_SPEED = .5f; //how much 8 stacks of muscles contribute to movement speed

    public float NERVES_HASTE = .1f; //how much a spine contributes to mining and attack speed

    public float BONE_DEFENSE = .5f; //damage reduction from 4 stacks of ribs

    public float RISK_OF_PRIONS = .01f; //risk of debuffs from human-derived foods

    public int CHEST_OPENER_ABSOLUTE_HEALTH_THRESHOLD = 20; //health below which a chest can be opened

    public float CHEST_OPENER_FRACTIONAL_HEALTH_THRESHOLD = .5f; //health below which a chest can be opened

    public boolean CAN_OPEN_OTHER_PLAYERS = false;

    public boolean KEEP_CHEST_CAVITY = false;

    public boolean DISABLE_ORGAN_REJECTION = false;

    public int ARROW_DODGE_DISTANCE = 32; //how far you can teleport when dodging projectiles

    public float BUFF_PURGING_DURATION_FACTOR = .5f; //how much withered bones reduce wither duration

    public int CRYSTALSYNTHESIS_RANGE = 32; //range at which you can link to a End Crystal

    public int CRYSTALSYNTHESIS_FREQUENCY = 10; //how often the link to an End Crystal is updated and perks gained

    public float FIREPROOF_DEFENSE = .75f; //damage reduction from 4 stacks of fireproof organs

    public float IMPACT_DEFENSE = .75f; //damage reduction from 4 stacks of impact resistant organs

    public float IRON_REPAIR_PERCENT = .25f; //damage reduction from 4 stacks of impact resistant organs

    public float LAUNCHING_POWER = .1f; //upward velocity per launching

    public int MAX_TELEPORT_ATTEMPTS = 5;

    public int PHOTOSYNTHESIS_FREQUENCY = 50; //how many ticks 8 photosynthetic organs in direct sunlight need to restore 1 hunger

    public int RUMINATION_TIME = 400; //time to eat a unit of grass

    public int RUMINATION_GRASS_PER_SQUARE = 2; //number of grass units are in a square

    public int RUMINATION_SQUARES_PER_STOMACH = 3; //number of grass squares a stomach can hold

    public int SHULKER_BULLET_TARGETING_RANGE = 20;

    public float SWIMSPEED_FACTOR = 1f; //how much 8 swimming muscles boost swim speed

    public float WITHERED_DURATION_FACTOR = .5f; //how much withered bones reduce wither duration

    public int ARROW_DODGE_COOLDOWN = 200; //how often an entity is allowed to dodge projectiles

    public int DRAGON_BOMB_COOLDOWN = 200; //how often an entity is allowed to fire bombs

    public int DRAGON_BREATH_COOLDOWN = 200; //how often an entity is allowed to fire bombs

    public int EXPLOSION_COOLDOWN = 200; //how often an entity is allowed to try exploding

    public int FORCEFUL_SPIT_COOLDOWN = 20; //how often an entity is allowed to spit

    public int GHASTLY_COOLDOWN = 60; //how often an entity is allowed to fire ghast bombs

    public int IRON_REPAIR_COOLDOWN = 1200; //how often an entity is allowed to use iron to heal

    public int PYROMANCY_COOLDOWN = 78; //how often an entity is allowed to spew fireballs

    public int SHULKER_BULLET_COOLDOWN = 100; //how often an entity is allowed to shoot bullets

    public int SILK_COOLDOWN = 20; //how often an entity is allowed to lay silk

    public int VENOM_COOLDOWN = 40; //how often an entity is allowed to poison targets

    public boolean BACKROOMS_INTEGRATION = true;

    public int BACKROOMS_CHEST_ORGAN_LOOT_ATTEMPTS = 2;

    public float BACKROOMS_CHEST_ORGAN_LOOT_CHANCE = 0.2f;

    public boolean REQUIEM_INTEGRATION = true;
    public static CCConfig load() {
        var path = net.minecraftforge.fml.loading.FMLPaths.CONFIGDIR.get().resolve("chestcavity.json");
        var gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
        try {
            if (java.nio.file.Files.exists(path)) {
                try (var reader = java.nio.file.Files.newBufferedReader(path)) {
                    return java.util.Objects.requireNonNull(gson.fromJson(reader, CCConfig.class));
                }
            }
            CCConfig config = new CCConfig();
            java.nio.file.Files.createDirectories(path.getParent());
            java.nio.file.Files.writeString(path, gson.toJson(config));
            return config;
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to load " + path, ex);
        }
    }
}
