package net.tigereye.chestcavity.listeners;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;
import net.tigereye.chestcavity.util.ChestCavityUtil;
import net.tigereye.chestcavity.util.OrganUtil;

public class OrganActivationListeners {

    public static void register(){
        OrganActivationCallback.EVENT.register(OrganActivationListeners::ActivateCreepy);
        OrganActivationCallback.EVENT.register(OrganActivationListeners::ActivateForcefulSpit);
        OrganActivationCallback.EVENT.register(OrganActivationListeners::ActivatePyromancy);
        OrganActivationCallback.EVENT.register(OrganActivationListeners::ActivateGhastly);
        OrganActivationCallback.EVENT.register(OrganActivationListeners::ActivateGrazing);
        OrganActivationCallback.EVENT.register(OrganActivationListeners::ActivateShulkerBullets);
        OrganActivationCallback.EVENT.register(OrganActivationListeners::ActivateSilk);
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

    public static void ActivateForcefulSpit(LivingEntity entity, ChestCavityInstance cc){
        //if(entity.world.isClient){
        //    return; //we are spawning entities, this is no place for a client
        //}
        float projectiles = cc.getOrganScore(CCOrganScores.FORCEFUL_SPIT);
        if(cc.getOrganScore(CCOrganScores.FORCEFUL_SPIT) < 1){
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
        if(cc.getOrganScore(CCOrganScores.GHASTLY) < 1){
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
        if(cc.getOrganScore(CCOrganScores.PYROMANCY) < 1){
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
        if(cc.getOrganScore(CCOrganScores.SHULKER_BULLETS) < 1){
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