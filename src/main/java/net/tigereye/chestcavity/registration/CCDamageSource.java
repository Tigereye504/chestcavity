package net.tigereye.chestcavity.registration;
import net.minecraft.resources.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.LivingEntity;
public final class CCDamageSource {
    public static final ResourceKey<DamageType> HEARTBLEED=ResourceKey.create(Registries.DAMAGE_TYPE,Identifier.fromNamespaceAndPath("chestcavity","heartbleed"));
    public static final ResourceKey<DamageType> ORGAN_REJECTION=ResourceKey.create(Registries.DAMAGE_TYPE,Identifier.fromNamespaceAndPath("chestcavity","organ_rejection"));
    public static DamageSource get(LivingEntity entity,ResourceKey<DamageType> key){return new DamageSource(entity.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(key));}
}
