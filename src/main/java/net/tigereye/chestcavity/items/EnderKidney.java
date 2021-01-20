package net.tigereye.chestcavity.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class EnderKidney extends Organ {
    public static boolean teleportRandomly(LivingEntity entity) {
        if (!entity.world.isClient() && entity.isAlive()) {
            double d = entity.getX() + (entity.getRandom().nextDouble() - 0.5D) * 64.0D;
            double e = entity.getY() + entity.getRandom().nextDouble() * 16.0D;
            double f = entity.getZ() + (entity.getRandom().nextDouble() - 0.5D) * 64.0D;
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
        mutable.move(Direction.UP);
        BlockState blockState2 = entity.world.getBlockState(mutable);
        while(blockState.getMaterial().blocksMovement() || blockState2.getMaterial().blocksMovement()){
            mutable.move(Direction.UP);
            if(blockState2.getMaterial().blocksMovement()){
                blockState = entity.world.getBlockState(mutable);
                blockState2 = entity.world.getBlockState(mutable.up());
            }
            else{
                blockState = blockState2;
                blockState2 = entity.world.getBlockState(mutable);
            }
        }


        //blockState = entity.world.getBlockState(mutable);
        boolean bl = blockState.getMaterial().blocksMovement();
        boolean bl2 = blockState.getMaterial().isLiquid();
        if (bl || bl2/*&& !bl2*/) {
            entity.teleport(x, mutable.getY()+.1, z);
            if (!entity.isSilent()) {
                entity.world.playSound((PlayerEntity)null, entity.prevX, entity.prevY, entity.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, entity.getSoundCategory(), 1.0F, 1.0F);
                entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }

            return true;
        } else {
            return false;
        }
    }
}
