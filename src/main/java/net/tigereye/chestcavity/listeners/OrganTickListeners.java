package net.tigereye.chestcavity.listeners;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.registration.CCDamageSource;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;
import net.tigereye.chestcavity.util.OrganUtil;

import java.util.List;

public class OrganTickListeners {

    public static void register(){
        OrganTickCallback.EVENT.register(OrganTickListeners::TickIncompatibility);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickProjectileQueue);

        OrganTickCallback.EVENT.register(OrganTickListeners::TickHealth);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickFiltration);

        OrganTickCallback.EVENT.register(OrganTickListeners::TickBuoyant);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickCrystalsynthesis);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickPhotosynthesis);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickHydroallergenic);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickHydrophobia);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickGlowing);
    }

    public static void TickBuoyant(LivingEntity entity, ChestCavityInstance chestCavity){
        float buoyancy = chestCavity.getOrganScore(CCOrganScores.BUOYANT) - chestCavity.getChestCavityType().getDefaultOrganScore(CCOrganScores.BUOYANT);
        if((entity instanceof Player ent && ent.isCreative() && ent.getAbilities().flying) || entity.onGround() || entity.isNoGravity() || buoyancy == 0)
        {
            return;
        }
        entity.push(0.0D, buoyancy*0.02D, 0.0D);
    }

    public static void TickCrystalsynthesis(LivingEntity entity, ChestCavityInstance cc){
        float crystalsynthesis = cc.getOrganScore(CCOrganScores.CRYSTALSYNTHESIS);
        //if the old crystal had been exploded, suffer
        if (cc.connectedCrystal != null) {
            if(cc.connectedCrystal.isRemoved()) {
                entity.hurt(entity.damageSources().starve(), crystalsynthesis * 2);
                cc.connectedCrystal = null;
            }
            else{
                if(crystalsynthesis != 0){
                    cc.connectedCrystal.setBeamTarget(entity.blockPosition().below(2));
                }
                else{
                    cc.connectedCrystal.setBeamTarget(null);
                    cc.connectedCrystal = null;
                }
            }
        }
        if(crystalsynthesis != 0 && entity.level().getGameTime() % ChestCavity.config.CRYSTALSYNTHESIS_FREQUENCY == 0 && !(entity instanceof EnderDragon))
        {
            EndCrystal oldcrystal = cc.connectedCrystal;
            //attempt to bind to a crystal
            List<EndCrystal> list = entity.level().getEntitiesOfClass(EndCrystal.class, entity.getBoundingBox().inflate(ChestCavity.config.CRYSTALSYNTHESIS_RANGE));
            EndCrystal endCrystalEntity = null;
            double d = Double.POSITIVE_INFINITY;

            for (EndCrystal endCrystalEntity2 : list) {
                double e = endCrystalEntity2.distanceToSqr(entity);
                if (e < d) {
                    d = e;
                    endCrystalEntity = endCrystalEntity2;
                }
            }
            cc.connectedCrystal = endCrystalEntity;
            if(oldcrystal != null && oldcrystal != cc.connectedCrystal){
                oldcrystal.setBeamTarget(null);
            }
            //then, if a crystal has been bound to
            if (cc.connectedCrystal != null) {
                if(entity instanceof Player){
                    Player playerEntity = (Player)entity;
                    FoodData hungerManager = playerEntity.getFoodData();
                    //first restore hunger
                    if(hungerManager.needsFood()) {
                        if(crystalsynthesis >= 5
                                || entity.level().getGameTime() % (ChestCavity.config.CRYSTALSYNTHESIS_FREQUENCY *5) < (ChestCavity.config.CRYSTALSYNTHESIS_FREQUENCY *crystalsynthesis)) {
                            hungerManager.eat(1, 0f);
                        }
                    }
                    //then restore saturation
                    else if(hungerManager.getSaturationLevel() < hungerManager.getFoodLevel()){
                        hungerManager.eat(1, crystalsynthesis/10);
                    }
                    //then restore health
                    else {
                        playerEntity.heal(crystalsynthesis / 5);
                    }
                }
                else{
                    //just restore health...
                    entity.heal(crystalsynthesis/5);
                }
                //finally, something about shiny lines linking to crystals?
            }

        }
    }

    public static void TickPhotosynthesis(LivingEntity entity, ChestCavityInstance cc){
        if(entity.level().isClientSide()){
            return;
        }
        float photosynthesis = cc.getOrganScore(CCOrganScores.PHOTOSYNTHESIS) - cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.PHOTOSYNTHESIS);
        if(photosynthesis > 0){
            cc.photosynthesisProgress += photosynthesis*entity.level().getMaxLocalRawBrightness(entity.blockPosition());
            if(cc.photosynthesisProgress > ChestCavity.config.PHOTOSYNTHESIS_FREQUENCY*8*15){
                cc.photosynthesisProgress = 0;
                if(entity instanceof Player){
                    Player playerEntity = (Player)entity;
                    FoodData hungerManager = playerEntity.getFoodData();
                    //first restore hunger
                    if(hungerManager.needsFood()) {
                        hungerManager.eat(1,0);
                    }
                    //then restore saturation
                    else if(hungerManager.getSaturationLevel() < hungerManager.getFoodLevel()){
                        hungerManager.eat(1, .5f);
                    }
                    //then restore health
                    else {
                        playerEntity.heal(1);
                    }
                }
                else{
                    //just restore health
                    entity.heal(1);
                }
            }
        }
    }

    public static void TickHealth(LivingEntity entity, ChestCavityInstance cc){
        if (cc.getOrganScore(CCOrganScores.HEALTH) <= 0
                && cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.HEALTH) != 0)
        {
            if(entity.level().getGameTime() % ChestCavity.config.HEARTBLEED_RATE == 0) {
                cc.heartBleedTimer = cc.heartBleedTimer + 1;
                entity.hurt(CCDamageSource.get(entity,CCDamageSource.HEARTBLEED), Math.min(cc.heartBleedTimer,cc.getChestCavityType().getHeartBleedCap()));
            }
        }
        else{
            cc.heartBleedTimer = 0;
        }
    }

    public static void TickFiltration(LivingEntity entity, ChestCavityInstance cc){
        if(entity.level().isClientSide()){ //this is a server-side event
            return;
        }
        if(cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.FILTRATION) <= 0){ //don't bother if the target doesn't need kidneys
            return;
        }
        float KidneyRatio = cc.getOrganScore(CCOrganScores.FILTRATION)/cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.FILTRATION);
        if(KidneyRatio < 1)
        {
            cc.bloodPoisonTimer = cc.bloodPoisonTimer+1;
            if(cc.bloodPoisonTimer >= ChestCavity.config.KIDNEY_RATE){
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, (int)Math.max(1,48*(1-KidneyRatio))));
                cc.bloodPoisonTimer = 0;
            }
        }
    }

    private static void TickProjectileQueue(LivingEntity entity, ChestCavityInstance cc) {
        //if(entity.level().isClientSide()){
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

    private static void TickHydroallergenic(LivingEntity entity, ChestCavityInstance cc) {
        if(entity.level().isClientSide()){ //this is a server-side event
            return;
        }
        float Hydroallergy = cc.getOrganScore(CCOrganScores.HYDROALLERGENIC);
        if(Hydroallergy <= 0){   //do nothing if the target isn't hydrophobic
            return;                                                                 //TODO: make enderman water-damage dependent on hydroallergenic
        }
        if (entity.isUnderWater()) {
            if(!entity.hasEffect(CCStatusEffects.WATER_VULNERABILITY)) {
                entity.hurt(entity.damageSources().magic(), 10);
                entity.addEffect(new MobEffectInstance(CCStatusEffects.WATER_VULNERABILITY, (int) (260 / Hydroallergy), 0, false, false, true));
            }
        }
        else if (entity.isInWaterOrRain()) {
            if(!entity.hasEffect(CCStatusEffects.WATER_VULNERABILITY)) {
                entity.hurt(entity.damageSources().magic(), 1);
                entity.addEffect(new MobEffectInstance(CCStatusEffects.WATER_VULNERABILITY, (int) (260 / Hydroallergy), 0, false, false, true));
            }
        }
    }

    public static void TickHydrophobia(LivingEntity entity, ChestCavityInstance cc){
        float hydrophobia = cc.getOrganScore(CCOrganScores.HYDROPHOBIA);
        if(hydrophobia <= 0                                                         //do nothing if the target isn't hydrophobic
            || cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.HYDROPHOBIA) != 0){   //do nothing if they are by default, otherwise endermen will spaz even harder
            return;                                                                 //TODO: make enderman water-teleporting dependent on hydrophobia
        }
        if(entity.isInWaterOrRain()){
            OrganUtil.teleportRandomly(entity,hydrophobia*32);
        }
    }

    public static void TickIncompatibility(LivingEntity entity,ChestCavityInstance chestCavity){
        if(entity.level().isClientSide() || ChestCavity.config.DISABLE_ORGAN_REJECTION){ //this is a server-side event
            return;
        }
        float incompatibility = chestCavity.getOrganScore(CCOrganScores.INCOMPATIBILITY);
        if(incompatibility > 0)
        {
            if(!entity.hasEffect(CCStatusEffects.ORGAN_REJECTION)){
                entity.addEffect(new MobEffectInstance(CCStatusEffects.ORGAN_REJECTION, (int)(ChestCavity.config.ORGAN_REJECTION_RATE /incompatibility),0, false, true, true));
            }
        }
    }

    public static void TickGlowing(LivingEntity entity,ChestCavityInstance chestCavity){
        if(entity.level().isClientSide()){ //this is a server-side event
            return;
        }
        float glowing = chestCavity.getOrganScore(CCOrganScores.GLOWING);
        if(glowing > 0)
        {
            if(!entity.hasEffect(MobEffects.GLOWING)){
                entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200,0, false, true, true));
            }
        }
    }
}
