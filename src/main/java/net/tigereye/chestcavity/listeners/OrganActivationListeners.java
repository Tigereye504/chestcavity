package net.tigereye.chestcavity.listeners;
import net.minecraft.core.BlockPos;

import net.minecraft.nbt.CompoundTag;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;

import net.minecraft.world.effect.MobEffectInstance;

import net.minecraft.world.entity.EquipmentSlot;

import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.ItemStack;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import it.unimi.dsi.fastutil.ints.IntComparators;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;
import net.tigereye.chestcavity.registration.CCTags;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import net.tigereye.chestcavity.util.OrganUtil;

import java.util.*;
import java.util.function.BiConsumer;

public class OrganActivationListeners {
    private static Map<Identifier, BiConsumer<LivingEntity,ChestCavityInstance>> abilityIDMap = new HashMap<>();
    public static void register(){
        register(CCOrganScores.CREEPY, OrganActivationListeners::ActivateCreepy);
        register(CCOrganScores.DRAGON_BREATH, OrganActivationListeners::ActivateDragonBreath);
        register(CCOrganScores.DRAGON_BOMBS, OrganActivationListeners::ActivateDragonBombs);
        register(CCOrganScores.FORCEFUL_SPIT, OrganActivationListeners::ActivateForcefulSpit);
        register(CCOrganScores.FURNACE_POWERED, OrganActivationListeners::ActivateFurnacePowered);
        register(CCOrganScores.IRON_REPAIR, OrganActivationListeners::ActivateIronRepair);
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
        if(entity.hasEffect(CCStatusEffects.EXPLOSION_COOLDOWN)){
            return;
        }
        float explosion_yield = cc.getOrganScore(CCOrganScores.EXPLOSIVE);
        ChestCavityUtil.destroyOrgansWithKey(cc,CCOrganScores.EXPLOSIVE);
        OrganUtil.explode(entity, explosion_yield);
        if(entity.isAlive()) {
            entity.addEffect(new MobEffectInstance(CCStatusEffects.EXPLOSION_COOLDOWN, ChestCavity.config.EXPLOSION_COOLDOWN, 0, false, false, true));
        }
    }

    public static void ActivateDragonBreath(LivingEntity entity, ChestCavityInstance cc){
        //if(entity.level().isClientSide()){
        //    return; //this is spawning entities, this is no place for a client
        //}
        float breath = cc.getOrganScore(CCOrganScores.DRAGON_BREATH);
        if(entity instanceof Player){
            ((Player)entity).causeFoodExhaustion(breath*.6f);
        }
        if(breath <= 0){
            return;
        }

        if(!entity.hasEffect(CCStatusEffects.DRAGON_BREATH_COOLDOWN)){
            entity.addEffect(new MobEffectInstance(CCStatusEffects.DRAGON_BREATH_COOLDOWN, ChestCavity.config.DRAGON_BREATH_COOLDOWN, 0, false, false, true));
            cc.projectileQueue.add(OrganUtil::spawnDragonBreath);
        }
    }

    public static void ActivateDragonBombs(LivingEntity entity, ChestCavityInstance cc){
        //if(entity.level().isClientSide()){
        //    return; //this is spawning entities, this is no place for a client
        //}
        float projectiles = cc.getOrganScore(CCOrganScores.DRAGON_BOMBS);
        if(projectiles < 1){
            return;
        }
        if(!entity.hasEffect(CCStatusEffects.DRAGON_BOMB_COOLDOWN)){
            OrganUtil.queueDragonBombs(entity,cc,(int)projectiles);
        }
    }

    public static void ActivateForcefulSpit(LivingEntity entity, ChestCavityInstance cc){
        //if(entity.level().isClientSide()){
        //    return; //we are spawning entities, this is no place for a client
        //}
        float projectiles = cc.getOrganScore(CCOrganScores.FORCEFUL_SPIT);
        if(projectiles < 1){
            return;
        }
        if(!entity.hasEffect(CCStatusEffects.FORCEFUL_SPIT_COOLDOWN)){
            OrganUtil.queueForcefulSpit(entity,cc,(int)projectiles);
        }
    }

    public static void ActivateFurnacePowered(LivingEntity entity, ChestCavityInstance cc){
        int furnacePowered = Math.round(cc.getOrganScore(CCOrganScores.FURNACE_POWERED));
        if(furnacePowered < 1){
            return;
        }

        //test for fuel
        int fuelValue = 0;
        ItemStack itemStack = cc.owner.getItemBySlot(EquipmentSlot.MAINHAND);
        if(itemStack != null && itemStack != ItemStack.EMPTY) {
            try {
                fuelValue = net.tigereye.chestcavity.util.FuelUtil.burnDuration(entity,itemStack);
            }
            catch (Exception e){
                fuelValue = 0;
            }
        }
        if(fuelValue == 0){
            itemStack = cc.owner.getItemBySlot(EquipmentSlot.OFFHAND);
            if(itemStack != null && itemStack != ItemStack.EMPTY) {
                try{
                fuelValue = net.tigereye.chestcavity.util.FuelUtil.burnDuration(entity,itemStack);
                }
                catch (Exception e){
                    fuelValue = 0;
                }
            }
        }
        if(fuelValue == 0){
            return;
        }

        //setup the new status effect
        MobEffectInstance newSEI = null;
        if(cc.owner.hasEffect(CCStatusEffects.FURNACE_POWER)) {
            MobEffectInstance oldPower = cc.owner.getEffect(CCStatusEffects.FURNACE_POWER);
            if(oldPower.getAmplifier() >= furnacePowered-1){
                return; //you can only fuel up if you have room
            }
            CompoundTag oldTag = new CompoundTag();
            List<Integer> durations = new ArrayList<>();
            durations.add(fuelValue);

            oldTag=(CompoundTag)MobEffectInstance.CODEC.encodeStart(net.minecraft.resources.RegistryOps.create(net.minecraft.nbt.NbtOps.INSTANCE,entity.registryAccess()),oldPower).getOrThrow();
            while(true) {
                durations.add(oldTag.getIntOr("duration",0));
                if (oldTag.contains("hidden_effect")) {
                    oldTag = oldTag.getCompoundOrEmpty("hidden_effect");
                } else {
                    break;
                }
            }

            durations.sort(IntComparators.OPPOSITE_COMPARATOR);
            int amplifier = 0;
            for (Integer duration:
                 durations) {
                newSEI = new MobEffectInstance(CCStatusEffects.FURNACE_POWER, duration, amplifier, false, false, true,newSEI);
                amplifier++;
            }
        }
        else{
            newSEI = new MobEffectInstance(CCStatusEffects.FURNACE_POWER, fuelValue, 0, false, false, true);
        }
        entity.removeEffect(CCStatusEffects.FURNACE_POWER);
        entity.addEffect(newSEI);
        itemStack.shrink(1);
    }

    public static void ActivateIronRepair(LivingEntity entity, ChestCavityInstance cc){
        float ironRepair = cc.getOrganScore(CCOrganScores.IRON_REPAIR) - cc.getChestCavityType().getDefaultOrganScore(CCOrganScores.IRON_REPAIR);
        //test for ability
        if(ironRepair <= 0){
            return;
        }
        //test for cooldown
        if(cc.owner.hasEffect(CCStatusEffects.IRON_REPAIR_COOLDOWN)){
            return;
        }
        //test for missing health
        if(cc.owner.getHealth() >= cc.owner.getMaxHealth()){
            return;
        }
        //test for iron
        ItemStack itemStack = cc.owner.getItemBySlot(EquipmentSlot.MAINHAND);
        if(itemStack == null || !itemStack.is(CCTags.IRON_REPAIR_MATERIAL)) {
            itemStack = cc.owner.getItemBySlot(EquipmentSlot.OFFHAND);
            if(itemStack == null || !itemStack.is(CCTags.IRON_REPAIR_MATERIAL)) {
                return;
            }
        }

        //success! heal target
        cc.owner.heal(cc.owner.getMaxHealth()*ChestCavity.config.IRON_REPAIR_PERCENT);
        entity.playSound(SoundEvents.IRON_GOLEM_REPAIR, .75f, 1);
        cc.owner.addEffect(new MobEffectInstance(CCStatusEffects.IRON_REPAIR_COOLDOWN, (int)(ChestCavity.config.IRON_REPAIR_COOLDOWN/ironRepair), 0, false, false, true));
        itemStack.shrink(1);

    }

    public static void ActivateGhastly(LivingEntity entity, ChestCavityInstance cc){
        //if(entity.level().isClientSide()){
        //    return; //this is spawning entities, this is no place for a client
        //}
        float ghastly = cc.getOrganScore(CCOrganScores.GHASTLY);
        if(ghastly < 1){
            return;
        }
        if(!entity.hasEffect(CCStatusEffects.GHASTLY_COOLDOWN)){
            OrganUtil.queueGhastlyFireballs(entity,cc,(int)ghastly);
        }
    }

    private static void ActivateGrazing(LivingEntity entity, ChestCavityInstance cc) {
        float grazing = cc.getOrganScore(CCOrganScores.GRAZING);
        if(grazing <= 0){
            return;
        }
        BlockPos blockPos = entity.blockPosition().below();
        boolean ateGrass = false;
        if (entity.level().getBlockState(blockPos).is(Blocks.GRASS_BLOCK)
        || entity.level().getBlockState(blockPos).is(Blocks.MYCELIUM)){
            //entity.level().levelEvent(2001, blockPos, Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
            entity.level().setBlock(blockPos, Blocks.DIRT.defaultBlockState(), 2);
            ateGrass = true;
        }
        else if(entity.level().getBlockState(blockPos).is(Blocks.CRIMSON_NYLIUM)
        || entity.level().getBlockState(blockPos).is(Blocks.WARPED_NYLIUM)){
            entity.level().setBlock(blockPos, Blocks.NETHERRACK.defaultBlockState(), 2);
            ateGrass = true;
        }
        if(ateGrass){
            int duration;
            if(entity.hasEffect(CCStatusEffects.RUMINATING)){
                MobEffectInstance ruminating = entity.getEffect(CCStatusEffects.RUMINATING);
                duration = (int)Math.min(ChestCavity.config.RUMINATION_TIME*ChestCavity.config.RUMINATION_GRASS_PER_SQUARE*ChestCavity.config.RUMINATION_SQUARES_PER_STOMACH*grazing,
                        ruminating.getDuration()+(ChestCavity.config.RUMINATION_TIME*ChestCavity.config.RUMINATION_GRASS_PER_SQUARE));
            }
            else{
                duration = ChestCavity.config.RUMINATION_TIME*ChestCavity.config.RUMINATION_GRASS_PER_SQUARE;
            }
            entity.addEffect(new MobEffectInstance(CCStatusEffects.RUMINATING, duration, 0, false, false, true));
        }
    }

    public static void ActivatePyromancy(LivingEntity entity, ChestCavityInstance cc){
        //if(entity.level().isClientSide()){
        //    return; //we are spawning entities, this is no place for a client
        //}
        float pyromancy = cc.getOrganScore(CCOrganScores.PYROMANCY);
        if(pyromancy < 1){
            return;
        }
        if(!entity.hasEffect(CCStatusEffects.PYROMANCY_COOLDOWN)){
            OrganUtil.queuePyromancyFireballs(entity,cc,(int)pyromancy);
        }
    }

    public static void ActivateShulkerBullets(LivingEntity entity, ChestCavityInstance cc){
        //if(entity.level().isClientSide()){
        //    return; //we are spawning entities, this is no place for a client
        //}
        float projectiles = cc.getOrganScore(CCOrganScores.SHULKER_BULLETS);
        if(projectiles < 1){
            return;
        }
        if(!entity.hasEffect(CCStatusEffects.SHULKER_BULLET_COOLDOWN)){
            OrganUtil.queueShulkerBullets(entity,cc,(int)projectiles);
        }
    }

    public static void ActivateSilk(LivingEntity entity, ChestCavityInstance cc){
        if(cc.getOrganScore(CCOrganScores.SILK) == 0){
            return;
        }
        if(entity.hasEffect(CCStatusEffects.SILK_COOLDOWN)){
            return;
        }
        if(OrganUtil.spinWeb(entity,cc, cc.getOrganScore(CCOrganScores.SILK))) {
            entity.addEffect(new MobEffectInstance(CCStatusEffects.SILK_COOLDOWN,ChestCavity.config.SILK_COOLDOWN,0,false,false,true));
        }
    }
}