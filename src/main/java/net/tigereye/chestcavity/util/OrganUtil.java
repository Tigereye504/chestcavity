package net.tigereye.chestcavity.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.potion.PotionUtil;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameRules;
import net.minecraft.world.explosion.Explosion;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.interfaces.CCStatusEffectInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class OrganUtil {

    public static boolean teleportRandomly(LivingEntity entity, float range) {
        if (!entity.world.isClient() && entity.isAlive()) {
            double d = entity.getX() + ((entity.getRandom().nextDouble() - 0.5D) * range);
            double e = Math.max(1, entity.getY() + ((entity.getRandom().nextDouble() - 0.5D) * range));
            double f = entity.getZ() + ((entity.getRandom().nextDouble() - 0.5D) * range);
            return teleportTo(entity, d, e, f);
        } else {
            return false;
        }
    }

    public static boolean teleportTo(LivingEntity entity, double x, double y, double z) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);
        BlockState blockState = entity.world.getBlockState(mutable);
        while(mutable.getY() > 0 && !(blockState.getMaterial().blocksMovement()
                || blockState.getMaterial().isLiquid()))
        {
            mutable.move(Direction.DOWN);
            blockState = entity.world.getBlockState(mutable);
        }
        if(mutable.getY() <= 0){
            return false;
        }

        mutable.move(Direction.UP);
        blockState = entity.world.getBlockState(mutable);
        BlockState blockState2 = entity.world.getBlockState(mutable.up());
        while(blockState.getMaterial().blocksMovement()
                || blockState.getMaterial().isLiquid()
                || blockState2.getMaterial().blocksMovement()
                || blockState2.getMaterial().isLiquid()){
            mutable.move(Direction.UP);
            blockState = entity.world.getBlockState(mutable);
            blockState2 = entity.world.getBlockState(mutable.up());
        }

        entity.teleport(x, mutable.getY()+.1, z);
        if (!entity.isSilent()) {
            entity.world.playSound((PlayerEntity)null, entity.prevX, entity.prevY, entity.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, entity.getSoundCategory(), 1.0F, 1.0F);
            entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
        }

        return true;
    }

    public static boolean spinWeb(LivingEntity entity, float silkScore){
        int hungerCost = 0;
        PlayerEntity player = null;
        if(entity instanceof PlayerEntity){
            player = (PlayerEntity)entity;
            if(player.getHungerManager().getFoodLevel() < 18){
                return false;
            }
        }

        if(silkScore >= 2) {
            BlockPos pos = entity.getBlockPos().offset(entity.getHorizontalFacing().getOpposite());
            if(entity.getEntityWorld().getBlockState(pos).getBlock().is(Blocks.AIR)){
                if(silkScore >= 3) {
                    hungerCost = 32;
                    silkScore -= 3;
                    entity.getEntityWorld().setBlockState(pos, Blocks.WHITE_WOOL.getDefaultState(), 2);
                }
                else{
                    hungerCost = 16;
                    silkScore -= 2;
                    entity.getEntityWorld().setBlockState(pos, Blocks.COBWEB.getDefaultState(), 2);
                }
            }
        }
        while(silkScore >= 1) {
            silkScore--;
            hungerCost += 8;
            entity.dropItem(Items.STRING);
        }
        if(player != null){
            player.getHungerManager().addExhaustion(hungerCost);
        }
        return hungerCost > 0;
    }

    public static void milkSilk(LivingEntity entity){
        if(!entity.hasStatusEffect(CCStatusEffects.SILK_COOLDOWN)){
            ChestCavityEntity.of(entity).ifPresent(cce -> {
                if(cce.getChestCavityInstance().opened){
                    float silk = cce.getChestCavityInstance().getOrganScore(CCOrganScores.SILK);
                    if(silk > 0){
                        if(spinWeb(entity,silk)) {
                            entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.SILK_COOLDOWN, ChestCavity.config.SILK_COOLDOWN,0,false,false,true));
                        }
                    }
                }
            });
        }
    }

    public static void shearSilk(LivingEntity entity){
        ChestCavityEntity.of(entity).ifPresent(cce -> {
            if(cce.getChestCavityInstance().opened){
                float silk = cce.getChestCavityInstance().getOrganScore(CCOrganScores.SILK);

                if(silk > 0){
                    if(silk >= 2){
                        ItemStack stack = new ItemStack(Items.COBWEB,((int)silk)/2);
                        ItemEntity itemEntity = new ItemEntity(entity.world, entity.getX(), entity.getY(), entity.getZ(), stack);
                        entity.world.spawnEntity(itemEntity);
                    }
                    if(silk % 2 >= 1){
                        ItemStack stack = new ItemStack(Items.STRING);
                        ItemEntity itemEntity = new ItemEntity(entity.world, entity.getX(), entity.getY(), entity.getZ(), stack);
                        entity.world.spawnEntity(itemEntity);
                    }
                }
            }
        });
    }

    public static void setStatusEffects(ItemStack organ, ItemStack potion){
        List<StatusEffectInstance> potionList = PotionUtil.getPotionEffects(potion);
        List<StatusEffectInstance> list = new ArrayList<>();
        for (StatusEffectInstance effect : potionList) {
            StatusEffectInstance effectCopy = new StatusEffectInstance(effect);
            ((CCStatusEffectInstance) effectCopy).CC_setDuration(Math.max(1,effectCopy.getDuration()/4));
            list.add(effectCopy);
        }
        setStatusEffects(organ,list);
    }

    public static void setStatusEffects(ItemStack organ, List<StatusEffectInstance> list){
        CompoundTag tag = organ.getOrCreateTag();
        ListTag listTag = new ListTag();

        for(int i = 0; i < list.size(); ++i) {
            StatusEffectInstance effect = list.get(i);
            if (effect != null) {
                CompoundTag compoundTag = new CompoundTag();
                listTag.add(effect.toTag(compoundTag));
            }
        }
        tag.put("CustomPotionEffects",listTag);
    }

    public static List<StatusEffectInstance> getStatusEffects(ItemStack organ){
        CompoundTag tag = organ.getOrCreateTag();
        ListTag listTag;
        if (!tag.contains("CustomPotionEffects", 9)) {
            return new ArrayList<>();
        }
        else{
            listTag = tag.getList("CustomPotionEffects",10);
            List<StatusEffectInstance> list = new ArrayList<>();
            for(int i = 0; i < listTag.size(); ++i) {
                CompoundTag compoundTag = listTag.getCompound(i);
                StatusEffectInstance statusEffectInstance = StatusEffectInstance.fromTag(compoundTag);
                if (statusEffectInstance != null) {
                    list.add(statusEffectInstance);
                }
            }
            return list;
        }
    }

    public static void explode(LivingEntity entity, float explosionYield) {
        if (!entity.world.isClient) {
            Explosion.DestructionType destructionType = entity.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
            entity.world.createExplosion(null, entity.getX(), entity.getY(), entity.getZ(), (float)Math.sqrt(explosionYield), destructionType);
            spawnEffectsCloud(entity);
        }

    }

    public static void spawnEffectsCloud(LivingEntity entity) {
        Collection<StatusEffectInstance> collection = entity.getStatusEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(entity.world, entity.getX(), entity.getY(), entity.getZ());
            areaEffectCloudEntity.setRadius(2.5F);
            areaEffectCloudEntity.setRadiusOnUse(-0.5F);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setDuration(areaEffectCloudEntity.getDuration() / 2);
            areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
            Iterator<StatusEffectInstance> var3 = collection.iterator();

            while(var3.hasNext()) {
                StatusEffectInstance statusEffectInstance = var3.next();
                areaEffectCloudEntity.addEffect(new StatusEffectInstance(statusEffectInstance));
            }

            entity.world.spawnEntity(areaEffectCloudEntity);
        }

    }
}
