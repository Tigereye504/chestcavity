package net.tigereye.chestcavity.util;

import net.minecraft.ChatFormatting;


import net.minecraft.core.BlockPos;

import net.minecraft.core.Direction;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;

import net.minecraft.resources.Identifier;

import net.minecraft.sounds.SoundEvents;

import net.minecraft.util.Mth;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.equine.Llama;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.entity.projectile.LlamaSpit;

import net.minecraft.world.entity.projectile.ShulkerBullet;

import net.minecraft.world.entity.projectile.hurtingprojectile.DragonFireball;

import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.Blocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;

import net.minecraft.world.phys.Vec3;


import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.CCStatusEffectInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCEnchantments;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;

import java.util.*;

public class OrganUtil {

    public static void displayOrganQuality(Map<Identifier,Float> organQualityMap, List<Component> tooltip){
        organQualityMap.forEach((organ,score) -> {
            String tier;
            if(organ.equals(CCOrganScores.HYDROALLERGENIC)){
                if(score >= 2){
                    tier = "quality.chestcavity.severely";
                }
                else{
                    tier = "";
                }
            }
            else {
                if (score >= 1.5f) {
                    tier = "quality.chestcavity.supernatural";
                } else if (score >= 1.25) {
                    tier = "quality.chestcavity.exceptional";
                } else if (score >= 1) {
                    tier = "quality.chestcavity.good";
                } else if (score >= .75f) {
                    tier = "quality.chestcavity.average";
                } else if (score >= .5f) {
                    tier = "quality.chestcavity.poor";
                } else if (score >= 0) {
                    tier = "quality.chestcavity.pathetic";
                } else if (score >= -.25f) {
                    tier = "quality.chestcavity.slightly_reduces";
                } else if (score >= -.5f) {
                    tier = "quality.chestcavity.reduces";
                } else if (score >= -.75f) {
                    tier = "quality.chestcavity.greatly_reduces";
                } else {
                    tier = "quality.chestcavity.greatly_reduces";
                }
            }
            Component text = Component.translatable("organscore." + organ.getNamespace() + "." + organ.getPath(), Component.translatable(tier));
            tooltip.add(text);
        });
    }

    public static void explode(LivingEntity entity, float explosionYield) {
        if (!entity.level().isClientSide()) {
            Level.ExplosionInteraction destructionType = Level.ExplosionInteraction.MOB;
            entity.level().explode(null, entity.getX(), entity.getY(), entity.getZ(), (float)Math.sqrt(explosionYield), destructionType);
            spawnEffectsCloud(entity);
        }

    }

    public static List<MobEffectInstance> getStatusEffects(ItemStack organ){
        var list=new ArrayList<MobEffectInstance>();
        organ.getOrDefault(net.minecraft.core.component.DataComponents.POTION_CONTENTS,net.minecraft.world.item.alchemy.PotionContents.EMPTY).getAllEffects().forEach(effect->list.add(new MobEffectInstance(effect)));
        return list;
    }

    public static void milkSilk(LivingEntity entity){
        if(!entity.hasEffect(CCStatusEffects.SILK_COOLDOWN)){
            ChestCavityEntity.of(entity).ifPresent(cce -> {
                if(cce.getChestCavityInstance().opened){
                    ChestCavityInstance cc = cce.getChestCavityInstance();
                    float silk = cc.getOrganScore(CCOrganScores.SILK);
                    if(silk > 0){
                        if(spinWeb(entity,cc,silk)) {
                            entity.addEffect(new MobEffectInstance(CCStatusEffects.SILK_COOLDOWN, ChestCavity.config.SILK_COOLDOWN,0,false,false,true));
                        }
                    }
                }
            });
        }
    }

    public static void queueDragonBombs(LivingEntity entity, ChestCavityInstance cc, int bombs){
        if(entity instanceof Player){
            ((Player)entity).causeFoodExhaustion(bombs*.6f);
        }
        for(int i = 0; i < bombs;i++){
            cc.projectileQueue.add(OrganUtil::spawnDragonBomb);
        }
        entity.addEffect(new MobEffectInstance(CCStatusEffects.DRAGON_BOMB_COOLDOWN, ChestCavity.config.DRAGON_BOMB_COOLDOWN, 0, false, false, true));
    }

    public static void queueForcefulSpit(LivingEntity entity, ChestCavityInstance cc, int projectiles){
        if(entity instanceof Player){
            ((Player)entity).causeFoodExhaustion(projectiles*.1f);
        }
        for(int i = 0; i < projectiles;i++){
            cc.projectileQueue.add(OrganUtil::spawnSpit);
        }
        entity.addEffect(new MobEffectInstance(CCStatusEffects.FORCEFUL_SPIT_COOLDOWN, ChestCavity.config.FORCEFUL_SPIT_COOLDOWN, 0, false, false, true));
    }

    public static void queueGhastlyFireballs(LivingEntity entity, ChestCavityInstance cc, int ghastly){
        if(entity instanceof Player){
            ((Player)entity).causeFoodExhaustion(ghastly*.3f);
        }
        for(int i = 0; i < ghastly;i++){
            cc.projectileQueue.add(OrganUtil::spawnGhastlyFireball);
        }
        entity.addEffect(new MobEffectInstance(CCStatusEffects.GHASTLY_COOLDOWN, ChestCavity.config.GHASTLY_COOLDOWN, 0, false, false, true));
    }

    public static void queuePyromancyFireballs(LivingEntity entity, ChestCavityInstance cc, int pyromancy){
        if(entity instanceof Player){
            ((Player)entity).causeFoodExhaustion(pyromancy*.1f);
        }
        for(int i = 0; i < pyromancy;i++){
            cc.projectileQueue.add(OrganUtil::spawnPyromancyFireball);
        }
        entity.addEffect(new MobEffectInstance(CCStatusEffects.PYROMANCY_COOLDOWN, ChestCavity.config.PYROMANCY_COOLDOWN, 0, false, false, true));
    }

    public static void queueShulkerBullets(LivingEntity entity, ChestCavityInstance cc, int shulkerBullets){
        if(entity instanceof Player){
            ((Player)entity).causeFoodExhaustion(shulkerBullets*.3f);
        }
        for(int i = 0; i < shulkerBullets;i++){
            cc.projectileQueue.add(OrganUtil::spawnShulkerBullet);
        }
        entity.addEffect(new MobEffectInstance(CCStatusEffects.SHULKER_BULLET_COOLDOWN, ChestCavity.config.SHULKER_BULLET_COOLDOWN, 0, false, false, true));
    }

    public static void setStatusEffects(ItemStack organ, ItemStack potion){
        List<MobEffectInstance> potionList = getStatusEffects(potion);
        List<MobEffectInstance> list = new ArrayList<>();
        for (MobEffectInstance effect : potionList) {
            MobEffectInstance effectCopy = new MobEffectInstance(effect);
            ((CCStatusEffectInstance) effectCopy).CC_setDuration(Math.max(1,effectCopy.getDuration()/4));
            list.add(effectCopy);
        }
        setStatusEffects(organ,list);
    }

    public static void setStatusEffects(ItemStack organ, List<MobEffectInstance> list){
        organ.set(net.minecraft.core.component.DataComponents.POTION_CONTENTS,new net.minecraft.world.item.alchemy.PotionContents(Optional.empty(),Optional.empty(),List.copyOf(list),Optional.empty()));
    }

    public static void shearSilk(LivingEntity entity){
        ChestCavityEntity.of(entity).ifPresent(cce -> {
            if(cce.getChestCavityInstance().opened){
                float silk = cce.getChestCavityInstance().getOrganScore(CCOrganScores.SILK);

                if(silk > 0){
                    if(silk >= 2){
                        ItemStack stack = new ItemStack(Items.COBWEB,((int)silk)/2);
                        ItemEntity itemEntity = new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), stack);
                        entity.level().addFreshEntity(itemEntity);
                    }
                    if(silk % 2 >= 1){
                        ItemStack stack = new ItemStack(Items.STRING);
                        ItemEntity itemEntity = new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), stack);
                        entity.level().addFreshEntity(itemEntity);
                    }
                }
            }
        });
    }

    public static void spawnEffectsCloud(LivingEntity entity) {
        Collection<MobEffectInstance> collection = entity.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloud areaEffectCloudEntity = new AreaEffectCloud(entity.level(), entity.getX(), entity.getY(), entity.getZ());
            areaEffectCloudEntity.setRadius(2.5F);
            areaEffectCloudEntity.setRadiusOnUse(-0.5F);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setDuration(areaEffectCloudEntity.getDuration() / 2);
            areaEffectCloudEntity.setRadiusPerTick(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
            Iterator<MobEffectInstance> var3 = collection.iterator();

            while(var3.hasNext()) {
                MobEffectInstance statusEffectInstance = var3.next();
                areaEffectCloudEntity.addEffect(new MobEffectInstance(statusEffectInstance));
            }

            entity.level().addFreshEntity(areaEffectCloudEntity);
        }

    }

    public static void spawnSilk(LivingEntity entity){
        entity.spawnAtLocation((net.minecraft.server.level.ServerLevel)entity.level(),Items.STRING);
    }

    public static void spawnSpit(LivingEntity entity){
        Vec3 entityFacing = entity.getLookAngle().normalize();

        Llama fakeLlama = new Llama(net.minecraft.world.entity.EntityTypes.LLAMA,entity.level());
        fakeLlama.setPos(entity.getX(),entity.getY(),entity.getZ());
        fakeLlama.setXRot(entity.getXRot());
        fakeLlama.setYRot(entity.getYRot());
        fakeLlama.yBodyRot = entity.yBodyRot;
        LlamaSpit llamaSpitEntity = new LlamaSpit(entity.level(), fakeLlama);
        llamaSpitEntity.setOwner(entity);
        llamaSpitEntity.setDeltaMovement(entityFacing.x*2,entityFacing.y*2,entityFacing.z*2);
        entity.level().addFreshEntity(llamaSpitEntity);
        entityFacing = entityFacing.scale(-.1D);
        entity.push(entityFacing.x,entityFacing.y,entityFacing.z);
    }

    public static void spawnDragonBomb(LivingEntity entity){
        Vec3 entityFacing = entity.getLookAngle().normalize();
        DragonFireball fireballEntity = new DragonFireball(entity.level(), entity, entityFacing);
        fireballEntity.setPos(fireballEntity.getX(), entity.getY(0.5D) + 0.3D, fireballEntity.getZ());
        entity.level().addFreshEntity(fireballEntity);
        entityFacing = entityFacing.scale(-0.2D);
        entity.push(entityFacing.x,entityFacing.y,entityFacing.z);
    }

    public static void spawnDragonBreath(LivingEntity entity){
        Optional<ChestCavityEntity> optional = ChestCavityEntity.of(entity);
        if(optional.isEmpty()){
            return;
        }
        ChestCavityEntity cce = optional.get();
        ChestCavityInstance cc= cce.getChestCavityInstance();
        float breath = cc.getOrganScore(CCOrganScores.DRAGON_BREATH);
        double range = Math.sqrt(breath/2)*5;
        HitResult result = entity.pick(range, 0, false);
        Vec3 pos = result.getLocation();
        double x = pos.x;
        double y = pos.y;
        double z = pos.z;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(x,y,z);
        while(entity.level().isEmptyBlock(mutable)) {
            --y;
            if (y < 0.0D) {
                return;
            }

            mutable.set(x,y,z);
        }
        y = (Mth.floor(y) + 1);
        AreaEffectCloud breathEntity = new AreaEffectCloud(entity.level(), x, y, z);
        breathEntity.setOwner(entity);
        breathEntity.setRadius((float)Math.max(range/2,Math.min(range, MathUtil.horizontalDistanceTo(breathEntity,entity))));
        breathEntity.setDuration(200);
        breathEntity.setCustomParticle(net.minecraft.core.particles.PowerParticleOption.create(ParticleTypes.DRAGON_BREATH,1));
        breathEntity.addEffect(new MobEffectInstance(MobEffects.INSTANT_DAMAGE));
        entity.level().addFreshEntity(breathEntity);

    }

    public static void spawnGhastlyFireball(LivingEntity entity){
        Vec3 entityFacing = entity.getLookAngle().normalize();
        LargeFireball fireballEntity = new LargeFireball(entity.level(), entity, entityFacing, 1);
        fireballEntity.setPos(fireballEntity.getX(), entity.getY(0.5D) + 0.3D, fireballEntity.getZ());
        entity.level().addFreshEntity(fireballEntity);
        entityFacing = entityFacing.scale(-.8D);
        entity.push(entityFacing.x,entityFacing.y,entityFacing.z);
    }

    public static void spawnPyromancyFireball(LivingEntity entity){
        Vec3 entityFacing = entity.getLookAngle().normalize();
        SmallFireball smallFireballEntity = new SmallFireball(entity.level(), entity, new Vec3(entityFacing.x + entity.getRandom().nextGaussian() * .1, entityFacing.y, entityFacing.z + entity.getRandom().nextGaussian() * .1));
        smallFireballEntity.setPos(smallFireballEntity.getX(), entity.getY(0.5D) + 0.3D, smallFireballEntity.getZ());
        entity.level().addFreshEntity(smallFireballEntity);
        entityFacing = entityFacing.scale(-.2D);
        entity.push(entityFacing.x,entityFacing.y,entityFacing.z);
    }

    public static void spawnShulkerBullet(LivingEntity entity){
        //Vec3 entityFacing = entity.getLookAngle().normalize();
        TargetingConditions targetPredicate = TargetingConditions.forCombat();
        targetPredicate.range(ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE*2);
        LivingEntity target = ((net.minecraft.server.level.ServerLevel)entity.level()).getNearestEntity(LivingEntity.class,
                targetPredicate, entity, entity.getX(), entity.getY(),entity.getZ(),
                new AABB(entity.getX()-ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE,entity.getY()-ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE,entity.getZ()-ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE,
                        entity.getX()+ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE,entity.getY()+ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE,entity.getZ()+ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE));
        if(target == null){
            return;
        }
        ShulkerBullet shulkerBulletEntity = new ShulkerBullet(entity.level(),entity,target, Direction.Axis.Y);
        shulkerBulletEntity.setPos(shulkerBulletEntity.getX(), entity.getY(0.5D) + 0.3D, shulkerBulletEntity.getZ());
        entity.level().addFreshEntity(shulkerBulletEntity);
        //entityFacing = entityFacing.scale(-.4D);
        //entity.push(entityFacing.x,entityFacing.y,entityFacing.z);
    }

    public static boolean spinWeb(LivingEntity entity, ChestCavityInstance cc, float silkScore){
        int hungerCost = 0;
        Player player = null;
        if(entity instanceof Player){
            player = (Player)entity;
            if(player.getFoodData().getFoodLevel() < 6){
                return false;
            }
        }

        if(silkScore >= 2) {
            BlockPos pos = entity.blockPosition().relative(entity.getDirection().getOpposite());
            if(entity.level().getBlockState(pos).isAir()){
                if(silkScore >= 3) {
                    hungerCost = 16;
                    silkScore -= 3;
                    entity.level().setBlock(pos, Blocks.WOOL.white().defaultBlockState(), 2);
                }
                else{
                    hungerCost = 8;
                    silkScore -= 2;
                    entity.level().setBlock(pos, Blocks.COBWEB.defaultBlockState(), 2);
                }
            }
        }
        while(silkScore >= 1) {
            silkScore--;
            hungerCost += 4;
            cc.projectileQueue.add(OrganUtil::spawnSilk);
        }
        if(player != null){
            player.causeFoodExhaustion(hungerCost);
        }
        return hungerCost > 0;
    }

    public static boolean teleportRandomly(LivingEntity entity, float range) {
        if (!entity.level().isClientSide() && entity.isAlive()) {
            for(int i = 0; i < ChestCavity.config.MAX_TELEPORT_ATTEMPTS; i++) {
                double d = entity.getX() + ((entity.getRandom().nextDouble() - 0.5D) * range);
                double e = Math.max(1, entity.getY() + ((entity.getRandom().nextDouble() - 0.5D) * range));
                double f = entity.getZ() + ((entity.getRandom().nextDouble() - 0.5D) * range);
                if(teleportTo(entity, d, e, f)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean teleportTo(LivingEntity entity,double x,double y,double z){
        if(!(entity.level() instanceof net.minecraft.server.level.ServerLevel level))return false;
        double oldX=entity.getX(),oldY=entity.getY(),oldZ=entity.getZ();
        if(!entity.randomTeleport(x,y,z,true,state -> !state.getFluidState().isEmpty()))return false;
        if(!entity.isSilent()){
            level.playSound(null,oldX,oldY,oldZ,SoundEvents.ENDERMAN_TELEPORT,entity.getSoundSource(),1,1);
            entity.playSound(SoundEvents.ENDERMAN_TELEPORT,1,1);
        }
        return true;
    }
}
