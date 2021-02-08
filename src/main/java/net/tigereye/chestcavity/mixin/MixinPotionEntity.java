package net.tigereye.chestcavity.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;
import net.tigereye.chestcavity.util.OrganUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Mixin(PotionEntity.class)
public class MixinPotionEntity extends ThrownItemEntity {

    public MixinPotionEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }
    @Shadow
    protected Item getDefaultItem() {
        return null;
    }

    @Inject(at = @At("TAIL"), method = "damageEntitiesHurtByWater")
    private void ChestCavityPotionEntityDamageEntitiesHurtByWaterMixin(CallbackInfo info) {
        Box box = ((PotionEntity)(Object)this).getBoundingBox().expand(4.0D, 2.0D, 4.0D);
        List<LivingEntity> list = ((PotionEntity)(Object)this).world.getEntitiesByClass(LivingEntity.class, box, ChestCavityUtil::isHydroPhobicOrAllergic);
        if (!list.isEmpty()) {
            for(LivingEntity livingEntity:list) {
                double d = this.squaredDistanceTo(livingEntity);
                if(d < 16.0D) {
                    Optional<ChestCavityEntity> optional = ChestCavityEntity.of(livingEntity);
                    if (optional.isPresent()) {
                        ChestCavityInstance cc = optional.get().getChestCavityInstance();
                        float allergy = cc.getOrganScore(CCOrganScores.HYDROALLERGENIC);
                        float phobia = cc.getOrganScore(CCOrganScores.HYDROPHOBIA);
                        if (allergy > 0) {
                            livingEntity.damage(DamageSource.magic(livingEntity, this.getOwner()), allergy/26);
                        }
                        if (phobia > 0) {
                            OrganUtil.teleportRandomly(livingEntity,phobia*32);
                        }
                    }
                }
            }
        }
    }

}
