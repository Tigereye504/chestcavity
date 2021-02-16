package net.tigereye.chestcavity.listeners;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.registration.CCDamageSource;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;
import net.tigereye.chestcavity.util.ChestCavityUtil;
import net.tigereye.chestcavity.util.OrganUtil;

public class OrganTickListeners {

    public static void register(){
        OrganTickCallback.EVENT.register(OrganTickListeners::TickIncompatibility);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickProjectileQueue);

        OrganTickCallback.EVENT.register(OrganTickListeners::TickHealth);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickFiltration);

        OrganTickCallback.EVENT.register(OrganTickListeners::TickBuoyant);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickCreepiness);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickForcefulSpit);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickHydroallergenic);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickHydrophobia);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickPyromancy);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickGhastly);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickShulkerBullets);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickSilk);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickGlowing);
    }

    public static void TickBuoyant(LivingEntity entity, ChestCavityInstance chestCavity){
        if(entity.isOnGround() || entity.hasNoGravity()){
            return;
        }
        float buoyancy = chestCavity.getOrganScore(CCOrganScores.BUOYANT);
        if(buoyancy > 0)
        {
            entity.addVelocity(0.0D, buoyancy*0.02D, 0.0D);
        }
    }

    public static void TickHealth(LivingEntity entity, ChestCavityInstance cc){
        if (cc.getOrganScore(CCOrganScores.HEALTH) <= 0
                && cc.type.getDefaultOrganScore(CCOrganScores.HEALTH) != 0)
        {
            if(entity.world.getTime() % ChestCavity.config.HEARTBLEED_RATE == 0) {
                cc.heartBleedTimer = cc.heartBleedTimer + 1;
                entity.damage(CCDamageSource.HEARTBLEED, Math.min(cc.heartBleedTimer,cc.type.getHeartBleedCap()));
            }
        }
        else{
            cc.heartBleedTimer = 0;
        }
    }

    public static void TickFiltration(LivingEntity entity, ChestCavityInstance cc){
        if(entity.getEntityWorld().isClient()){ //this is a server-side event
            return;
        }
        if(cc.type.getDefaultOrganScore(CCOrganScores.FILTRATION) <= 0){ //don't bother if the target doesn't need kidneys
            return;
        }
        float KidneyRatio = cc.getOrganScore(CCOrganScores.FILTRATION)/cc.type.getDefaultOrganScore(CCOrganScores.FILTRATION);
        if(KidneyRatio < 1)
        {
            cc.bloodPoisonTimer = cc.bloodPoisonTimer+1;
            if(cc.bloodPoisonTimer >= ChestCavity.config.KIDNEY_RATE){
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, (int)(48*(1-KidneyRatio))));
                cc.bloodPoisonTimer = 0;
            }
        }
    }

    public static void TickCreepiness(LivingEntity entity,ChestCavityInstance cc){
        if(cc.getOrganScore(CCOrganScores.CREEPY) < 1){
            return;
        }
        if(entity.hasStatusEffect(CCStatusEffects.EXPLOSION_COOLDOWN)){
            return;
        }
        else if(entity.getPose() == EntityPose.CROUCHING /*|| entity.isOnFire()*/){
            float explosion_yield = cc.getOrganScore(CCOrganScores.EXPLOSIVE);
            ChestCavityUtil.destroyOrgansWithKey(cc,CCOrganScores.EXPLOSIVE);
            OrganUtil.explode(entity, explosion_yield);
            if(entity.isAlive()) {
                entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.EXPLOSION_COOLDOWN, ChestCavity.config.EXPLOSION_COOLDOWN, 0, false, false, true));
            }
        }
    }

    public static void TickForcefulSpit(LivingEntity entity,ChestCavityInstance cc){
        //if(entity.world.isClient){
        //    return; //we are spawning entities, this is no place for a client
        //}
        float projectiles = cc.getOrganScore(CCOrganScores.FORCEFUL_SPIT);
        if(cc.getOrganScore(CCOrganScores.FORCEFUL_SPIT) < 1){
            return;
        }
        if(!entity.hasStatusEffect(CCStatusEffects.FORCEFUL_SPIT_COOLDOWN) && entity.getPose() == EntityPose.CROUCHING){
            OrganUtil.queueForcefulSpit(entity,cc,(int)projectiles);
        }
    }

    public static void TickGhastly(LivingEntity entity,ChestCavityInstance cc){
        //if(entity.world.isClient){
        //    return; //this is spawning entities, this is no place for a client
        //}
        float ghastly = cc.getOrganScore(CCOrganScores.GHASTLY);
        if(cc.getOrganScore(CCOrganScores.GHASTLY) < 1){
            return;
        }
        if(!entity.hasStatusEffect(CCStatusEffects.GHASTLY_COOLDOWN) && entity.getPose() == EntityPose.CROUCHING){
            OrganUtil.queueGhastlyFireballs(entity,cc,(int)ghastly);
        }
    }

    private static void TickProjectileQueue(LivingEntity entity, ChestCavityInstance cc) {
        //if(entity.world.isClient){
            //this is spawning entities, this is no place for a client
            //return;
        //}
        if(cc.projectileCooldown > 0){
            cc.projectileCooldown -= 1;
            return;
        }
        if(!cc.projectileQueue.isEmpty()) {
            cc.projectileCooldown = 5;
            cc.projectileQueue.pop().accept(entity);
        }
    }

    public static void TickPyromancy(LivingEntity entity,ChestCavityInstance cc){
        //if(entity.world.isClient){
        //    return; //we are spawning entities, this is no place for a client
        //}
        float pyromancy = cc.getOrganScore(CCOrganScores.PYROMANCY);
        if(cc.getOrganScore(CCOrganScores.PYROMANCY) < 1){
            return;
        }
        if(!entity.hasStatusEffect(CCStatusEffects.PYROMANCY_COOLDOWN) && entity.getPose() == EntityPose.CROUCHING){
            OrganUtil.queuePyromancyFireballs(entity,cc,(int)pyromancy);
        }
    }

    public static void TickShulkerBullets(LivingEntity entity,ChestCavityInstance cc){
        //if(entity.world.isClient){
        //    return; //we are spawning entities, this is no place for a client
        //}
        float projectiles = cc.getOrganScore(CCOrganScores.SHULKER_BULLETS);
        if(cc.getOrganScore(CCOrganScores.SHULKER_BULLETS) < 1){
            return;
        }
        if(!entity.hasStatusEffect(CCStatusEffects.SHULKER_BULLET_COOLDOWN) && entity.getPose() == EntityPose.CROUCHING){
            OrganUtil.queueShulkerBullets(entity,cc,(int)projectiles);
        }
    }

    public static void TickSilk(LivingEntity entity,ChestCavityInstance cc){
        if(cc.getOrganScore(CCOrganScores.SILK) == 0){
            return;
        }
        if(entity.hasStatusEffect(CCStatusEffects.SILK_COOLDOWN)){
            return;
        }
        else if(entity.getPose() == EntityPose.CROUCHING){
            if(OrganUtil.spinWeb(entity,cc.getOrganScore(CCOrganScores.SILK))) {
                entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.SILK_COOLDOWN,ChestCavity.config.SILK_COOLDOWN,0,false,false,true));
            }
        }
    }

    private static void TickHydroallergenic(LivingEntity entity, ChestCavityInstance cc) {
        if(entity.getEntityWorld().isClient()){ //this is a server-side event
            return;
        }
        float Hydroallergy = cc.getOrganScore(CCOrganScores.HYDROALLERGENIC);
        if(Hydroallergy == 0){   //do nothing if the target isn't hydrophobic
            return;                                                                 //TODO: make enderman water-damage dependent on hydroallergenic
        }
        if (entity.isSubmergedInWater()) {
            if(!entity.hasStatusEffect(CCStatusEffects.WATER_VULNERABILITY)) {
                entity.damage(DamageSource.MAGIC, 10);
                entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.WATER_VULNERABILITY, (int) (260 / Hydroallergy), 0, false, false, true));
            }
        }
        else if (entity.isTouchingWaterOrRain()) {
            if(!entity.hasStatusEffect(CCStatusEffects.WATER_VULNERABILITY)) {
                entity.damage(DamageSource.MAGIC, 1);
                entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.WATER_VULNERABILITY, (int) (260 / Hydroallergy), 0, false, false, true));
            }
        }
    }

    public static void TickHydrophobia(LivingEntity entity, ChestCavityInstance cc){
        float hydrophobia = cc.getOrganScore(CCOrganScores.HYDROPHOBIA);
        if(hydrophobia == 0                                                         //do nothing if the target isn't hydrophobic
            || cc.type.getDefaultOrganScore(CCOrganScores.HYDROPHOBIA) != 0){   //do nothing if they are by default, otherwise endermen will spaz even harder
            return;                                                                 //TODO: make enderman water-teleporting dependent on hydrophobia
        }
        if(entity.isTouchingWaterOrRain()){
            OrganUtil.teleportRandomly(entity,hydrophobia*32);
        }
    }

    public static void TickIncompatibility(LivingEntity entity,ChestCavityInstance chestCavity){
        if(entity.getEntityWorld().isClient()){ //this is a server-side event
            return;
        }
        float incompatibility = chestCavity.getOrganScore(CCOrganScores.INCOMPATIBILITY);
        if(incompatibility > 0)
        {
            if(!entity.hasStatusEffect(CCStatusEffects.ORGAN_REJECTION)){
                entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.ORGAN_REJECTION, (int)(ChestCavity.config.ORGAN_REJECTION_RATE /incompatibility),0, false, true, true));
            }
        }
    }

    public static void TickGlowing(LivingEntity entity,ChestCavityInstance chestCavity){
        if(entity.getEntityWorld().isClient()){ //this is a server-side event
            return;
        }
        float glowing = chestCavity.getOrganScore(CCOrganScores.GLOWING);
        if(glowing > 0)
        {
            if(!entity.hasStatusEffect(StatusEffects.GLOWING)){
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200,0, false, true, true));
            }
        }
    }
}