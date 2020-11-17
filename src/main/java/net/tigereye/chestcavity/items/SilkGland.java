package net.tigereye.chestcavity.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class SilkGland extends OrganBase{

    public static boolean spinWeb(LivingEntity entity, float silkScore){
        if(!(entity instanceof PlayerEntity)){
            return false;
        }
        PlayerEntity player = (PlayerEntity)entity;
        if(player.getHungerManager().getFoodLevel() < 18){
            return false;
        }
        if(silkScore >= 2) {
            BlockPos pos = player.getBlockPos().offset(player.getHorizontalFacing().getOpposite());
            if(entity.getEntityWorld().getBlockState(pos).getBlock().is(Blocks.AIR)){
                if(silkScore >= 3) {
                    player.getHungerManager().addExhaustion(32.0f);
                    entity.getEntityWorld().setBlockState(pos, Blocks.WHITE_WOOL.getDefaultState(), 2);
                }
                else{
                    player.getHungerManager().addExhaustion(16.0f);
                    entity.getEntityWorld().setBlockState(pos, Blocks.COBWEB.getDefaultState(), 2);
                }
                return true;
            }
        }
        else if(silkScore > 0) {
            player.getHungerManager().addExhaustion(8.0f);
            player.dropItem(Items.STRING); //TODO: place a tripwire string instead
            return true;
        }
        return false;
    }
}
