package net.tigereye.chestcavity.registration;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import org.jetbrains.annotations.Nullable;

public class CCDamageSources {
    public static final RegistryKey<DamageType> HEARTBLEED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(ChestCavity.MODID,"ccHeartbleed"));
    public static final RegistryKey<DamageType> ORGAN_REJECTION = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(ChestCavity.MODID,"ccOrganRejection"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }

    public static DamageSource of(World world, RegistryKey<DamageType> key, @Nullable Entity attacker) {
        if(attacker != null) {
            return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key), attacker);
        }
        else {
            return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
        }
    }

}
