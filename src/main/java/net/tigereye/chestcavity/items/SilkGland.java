package net.tigereye.chestcavity.items;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;

public class SilkGland extends Organ {

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
        if(hungerCost > 0){
            return true;
        }
        return false;
    }

    public static void milkSilk(LivingEntity entity){
        if(!entity.hasStatusEffect(CCStatusEffects.SILK_COOLDOWN)){
            ChestCavityEntity.of(entity).ifPresent(cce -> {
                if(cce.getChestCavityManager().getOpened()){
                    float silk = cce.getChestCavityManager().getOrganScore(CCOrganScores.SILK);
                    if(silk > 0){
                        if(SilkGland.spinWeb(entity,silk)) {
                            entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.SILK_COOLDOWN, ChestCavity.config.SILK_COOLDOWN,0,false,false,true));
                        }
                    }
                }
            });
        }
    }

    public static void shearSilk(LivingEntity entity){
        ChestCavityEntity.of(entity).ifPresent(cce -> {
            if(cce.getChestCavityManager().getOpened()){
                float silk = cce.getChestCavityManager().getOrganScore(CCOrganScores.SILK);

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
}
