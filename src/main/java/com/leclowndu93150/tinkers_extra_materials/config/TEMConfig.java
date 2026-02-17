package com.leclowndu93150.tinkers_extra_materials.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class TEMConfig {

    public static final ForgeConfigSpec SPEC;

    public static final IntValue STAND_GROUND_COOLDOWN;
    public static final DoubleValue STAND_GROUND_ARMOR_TRIGGER_CHANCE;
    public static final IntValue STAND_LIFETIME;
    public static final DoubleValue STAND_HEALTH;
    public static final DoubleValue STAND_DAMAGE;
    public static final DoubleValue STAND_SPEED;
    public static final IntValue STAND_SEARCH_RANGE;
    public static final IntValue STAND_LEASH_RANGE;

    public static final IntValue ENGRAVING_PER_PART;

    public static final DoubleValue UNSTABLE_TRIGGER_CHANCE;
    public static final IntValue UNSTABLE_DURATION;

    public static final IntValue RESONANCE_DURATION;
    public static final IntValue RESONANCE_JUMP_AMPLIFIER;
    public static final IntValue RESONANCE_SPEED_AMPLIFIER;

    public static final IntValue EVOLVING_DURATION;
    public static final DoubleValue EVOLVING_SPEED_MULTIPLIER;
    public static final DoubleValue EVOLVING_DAMAGE_BONUS;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Tinkers' Extra Materials Modifier Configuration").push("modifiers");

        builder.comment("Stand Ground - Summons a Stand clone to fight for you").push("stand_ground");
        STAND_GROUND_COOLDOWN = builder
            .comment("Cooldown in ticks between Stand summons (20 ticks = 1 second)")
            .defineInRange("cooldown", 600, 20, 6000);
        STAND_GROUND_ARMOR_TRIGGER_CHANCE = builder
            .comment("Chance to summon Stand when hit while wearing armor (0.2 = 20%)")
            .defineInRange("armorTriggerChance", 0.2, 0.0, 1.0);
        STAND_LIFETIME = builder
            .comment("How long the Stand lives in ticks (20 ticks = 1 second)")
            .defineInRange("lifetime", 200, 20, 1200);
        STAND_HEALTH = builder
            .comment("Stand max health")
            .defineInRange("health", 20.0, 1.0, 100.0);
        STAND_DAMAGE = builder
            .comment("Stand attack damage")
            .defineInRange("damage", 6.0, 1.0, 50.0);
        STAND_SPEED = builder
            .comment("Stand movement speed")
            .defineInRange("speed", 0.35, 0.01, 1.0);
        STAND_SEARCH_RANGE = builder
            .comment("Range in blocks to search for targets when right-clicking")
            .defineInRange("searchRange", 32, 8, 64);
        STAND_LEASH_RANGE = builder
            .comment("Max distance in blocks before Stand teleports back to owner")
            .defineInRange("leashRange", 64, 16, 128);
        builder.pop();

        builder.comment("Engraving - Engrave tool part traits onto a tool").push("engraving");
        ENGRAVING_PER_PART = builder
            .comment("Number of engravings allowed per Runican part used")
            .defineInRange("engravingsPerPart", 3, 1, 10);
        builder.pop();

        builder.comment("Unstable - Random buff on hit or block break").push("unstable");
        UNSTABLE_TRIGGER_CHANCE = builder
            .comment("Chance to trigger a random effect (0.1 = 10%)")
            .defineInRange("triggerChance", 0.1, 0.0, 1.0);
        UNSTABLE_DURATION = builder
            .comment("Duration of the granted effect in ticks (20 ticks = 1 second)")
            .defineInRange("duration", 200, 20, 1200);
        builder.pop();

        builder.comment("Resonance - Jump and speed boost on hit, mine, or damage taken").push("resonance");
        RESONANCE_DURATION = builder
            .comment("Duration of the effects in ticks (20 ticks = 1 second)")
            .defineInRange("duration", 100, 20, 1200);
        RESONANCE_JUMP_AMPLIFIER = builder
            .comment("Jump Boost amplifier (0 = level I, 1 = level II)")
            .defineInRange("jumpAmplifier", 1, 0, 4);
        RESONANCE_SPEED_AMPLIFIER = builder
            .comment("Speed amplifier (0 = level I, 1 = level II)")
            .defineInRange("speedAmplifier", 0, 0, 4);
        builder.pop();

        builder.comment("Evolving - Temporary boost on XP gain").push("evolving");
        EVOLVING_DURATION = builder
            .comment("Duration of the boost in ticks (20 ticks = 1 second)")
            .defineInRange("duration", 200, 20, 1200);
        EVOLVING_SPEED_MULTIPLIER = builder
            .comment("Mining speed multiplier when boosted (1.3 = 30% faster)")
            .defineInRange("speedMultiplier", 1.3, 1.0, 3.0);
        EVOLVING_DAMAGE_BONUS = builder
            .comment("Extra melee damage when boosted")
            .defineInRange("damageBonus", 2.0, 0.0, 10.0);
        builder.pop();

        builder.pop();

        SPEC = builder.build();
    }
}
