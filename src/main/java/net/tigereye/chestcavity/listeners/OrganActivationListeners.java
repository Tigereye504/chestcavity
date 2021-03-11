package net.tigereye.chestcavity.listeners;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.RaycastContext;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;
import net.tigereye.chestcavity.util.ChestCavityUtil;
import net.tigereye.chestcavity.util.MathUtil;
import net.tigereye.chestcavity.util.OrganUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class OrganActivationListeners {
    private static Map<Identifier, BiConsumer<LivingEntity,ChestCavityInstance>> abilityIDMap = new HashMap<>();
    public static void register(){
        register(CCOrganScores.CREEPY, OrganActivationListeners::ActivateCreepy);
        register(CCOrganScores.DRAGON_BREATH, OrganActivationListeners::ActivateDragonBreath);
        register(CCOrganScores.DRAGON_BOMBS, OrganActivationListeners::ActivateDragonBombs);
        register(CCOrganScores.FORCEFUL_SPIT, OrganActivationListeners::ActivateForcefulSpit);
        register(CCOrganScores.PYROMANCY, OrganActivationListeners::ActivatePyromancy);
        register(CCOrganScores.GHASTLY, OrganActivationListeners::ActivateGhastly);
        register(CCOrganScores.GRAZING, OrganActivationListeners::ActivateGrazing);
        register(CCOrganScores.SHULKER_BULLETS, OrganActivationListeners::ActivateShulkerBullets);
        register(CCOrganScores.SILK, OrganActivationListeners::ActivateSilk);
    }
    public static void register(Identifier id,BiConsumer<LivingEntity,ChestCavityInstance> ability){
        abilityIDMap.put(id,ability);
    }
    public static boolean activate(Identifier id, ChestCavityInstance cc){
        if(abilityIDMap.containsKey(id)) {
            abilityIDMap.get(id).accept(cc.owner,cc);
            return true;
        }
        else{
            return false;
        }
    }

    public static void ActivateCreepy(LivingEntity entity, ChestCavityInstance cc){
        if(cc.getOrganScore(CCOrganScores.CREEPY) < 1){
            return;
        }
        if(entity.hasStatusEffect(CCStatusEffects.EXPLOSION_COOLDOWN)){
            return;
        }
        float explosion_yield = cc.getOrganScore(CCOrganScores.EXPLOSIVE);
        ChestCavityUtil.destroyOrgansWithKey(cc,CCOrganScores.EXPLOSIVE);
        OrganUtil.explode(entity, explosion_yield);
        if(entity.isAlive()) {
            entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.EXPLOSION_COOLDOWN, ChestCavity.config.EXPLOSION_COOLDOWN, 0, false, false, true));
        }
    }

    public static void ActivateDragonBreath(LivingEntity entity, ChestCavityInstance cc){
        //if(entity.world.isClient){
        //    return; //this is spawning entities, this is no place for a client
        //}
        float breath = cc.getOrganScore(CCOrganScores.DRAGON_BREATH);
        if(entity instanceof PlayerEntity){
            ((PlayerEntity)entity).addExhaustion(breath*.6f);
        }
        if(breath <= 0){
            return;
        }
        if(!entity.hasStatusEffect(CCStatusEffects.DRAGON_BREATH_COOLDOWN)){
            entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.DRAGON_BREATH_COOLDOWN, ChestCavity.config.DRAGON_BREATH_COOLDOWN, 0, false, false, true));
            double range = Math.sqrt(breath/2)*5;
            HitResult result = entity.raycast(range, 0, false);
            Vec3d pos = result.getPos();
            double x = pos.x;
            double y = pos.y;
            double z = pos.z;
            BlockPos.Mutable mutable = new BlockPos.Mutable(x,y,z);
            while(entity.world.isAir(mutable)) {
                --y;
                if (y < 0.0D) {
                    return;
                }

                mutable.set(x,y,z);
            }
            y = (double)(MathHelper.floor(y) + 1);
            AreaEffectCloudEntity breathEntity = new AreaEffectCloudEntity(entity.world, x, y, z);
            breathEntity.setOwner(entity);
            breathEntity.setRadius((float)Math.max(range/2,Math.min(range, MathUtil.horizontalDistanceTo(breathEntity,entity))));
            breathEntity.setDuration((int)(200));
            breathEntity.setParticleType(ParticleTypes.DRAGON_BREATH);
            breathEntity.addEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE));
            entity.world.spawnEntity(breathEntity);
        }
    }

    public static void ActivateDragonBombs(LivingEntity entity, ChestCavityInstance cc){
        //if(entity.world.isClient){
        //    return; //this is spawning entities, this is no place for a client
        //}
        float projectiles = cc.getOrganScore(CCOrganScores.DRAGON_BOMBS);
        if(projectiles < 1){
            return;
        }
        if(!entity.hasStatusEffect(CCStatusEffects.DRAGON_BOMB_COOLDOWN)){
            OrganUtil.queueDragonBombs(entity,cc,(int)projectiles);
        }
    }

    public static void ActivateForcefulSpit(LivingEntity entity, ChestCavityInstance cc){
        //if(entity.world.isClient){
        //    return; //we are spawning entities, this is no place for a client
        //}
        float projectiles = cc.getOrganScore(CCOrganScores.FORCEFUL_SPIT);
        if(projectiles < 1){
            return;
        }
        if(!entity.hasStatusEffect(CCStatusEffects.FORCEFUL_SPIT_COOLDOWN)){
            OrganUtil.queueForcefulSpit(entity,cc,(int)projectiles);
        }
    }

    public static void ActivateGhastly(LivingEntity entity, ChestCavityInstance cc){
        //if(entity.world.isClient){
        //    return; //this is spawning entities, this is no place for a client
        //}
        float ghastly = cc.getOrganScore(CCOrganScores.GHASTLY);
        if(ghastly < 1){
            return;
        }
        if(!entity.hasStatusEffect(CCStatusEffects.GHASTLY_COOLDOWN)){
            OrganUtil.queueGhastlyFireballs(entity,cc,(int)ghastly);
        }
    }

    private static void ActivateGrazing(LivingEntity entity, ChestCavityInstance cc) {
        float grazing = cc.getOrganScore(CCOrganScores.GRAZING);
        if(grazing <= 0){
            return;
        }
        BlockPos blockPos = entity.getBlockPos().down();
        boolean ateGrass = false;
        if (entity.world.getBlockState(blockPos).isOf(Blocks.GRASS_BLOCK)
        || entity.world.getBlockState(blockPos).isOf(Blocks.MYCELIUM)){
            //entity.world.syncWorldEvent(2001, blockPos, Block.getRawIdFromState(Blocks.GRASS_BLOCK.getDefaultState()));
            entity.world.setBlockState(blockPos, Blocks.DIRT.getDefaultState(), 2);
            ateGrass = true;
        }
        else if(entity.world.getBlockState(blockPos).isOf(Blocks.CRIMSON_NYLIUM)
        || entity.world.getBlockState(blockPos).isOf(Blocks.WARPED_NYLIUM)){
            entity.world.setBlockState(blockPos, Blocks.NETHERRACK.getDefaultState(), 2);
            ateGrass = true;
        }
        if(ateGrass){
            int duration;
            if(entity.hasStatusEffect(CCStatusEffects.RUMINATING)){
                StatusEffectInstance ruminating = entity.getStatusEffect(CCStatusEffects.RUMINATING);
                duration = (int)Math.min(ChestCavity.config.RUMINATION_TIME*ChestCavity.config.RUMINATION_GRASS_PER_SQUARE*ChestCavity.config.RUMINATION_SQUARES_PER_STOMACH*grazing,
                        ruminating.getDuration()+(ChestCavity.config.RUMINATION_TIME*ChestCavity.config.RUMINATION_GRASS_PER_SQUARE));
            }
            else{
                duration = ChestCavity.config.RUMINATION_TIME*ChestCavity.config.RUMINATION_GRASS_PER_SQUARE;
            }
            entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.RUMINATING, duration, 0, false, false, true));
        }
    }

    public static void ActivatePyromancy(LivingEntity entity, ChestCavityInstance cc){
        //if(entity.world.isClient){
        //    return; //we are spawning entities, this is no place for a client
        //}
        float pyromancy = cc.getOrganScore(CCOrganScores.PYROMANCY);
        if(pyromancy < 1){
            return;
        }
        if(!entity.hasStatusEffect(CCStatusEffects.PYROMANCY_COOLDOWN)){
            OrganUtil.queuePyromancyFireballs(entity,cc,(int)pyromancy);
        }
    }

    public static void ActivateShulkerBullets(LivingEntity entity, ChestCavityInstance cc){
        //if(entity.world.isClient){
        //    return; //we are spawning entities, this is no place for a client
        //}
        float projectiles = cc.getOrganScore(CCOrganScores.SHULKER_BULLETS);
        if(projectiles < 1){
            return;
        }
        if(!entity.hasStatusEffect(CCStatusEffects.SHULKER_BULLET_COOLDOWN)){
            OrganUtil.queueShulkerBullets(entity,cc,(int)projectiles);
        }
    }

    public static void ActivateSilk(LivingEntity entity, ChestCavityInstance cc){
        if(cc.getOrganScore(CCOrganScores.SILK) == 0){
            return;
        }
        if(entity.hasStatusEffect(CCStatusEffects.SILK_COOLDOWN)){
            return;
        }
        if(OrganUtil.spinWeb(entity,cc.getOrganScore(CCOrganScores.SILK))) {
            entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.SILK_COOLDOWN,ChestCavity.config.SILK_COOLDOWN,0,false,false,true));
        }
    }
}