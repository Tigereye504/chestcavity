package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.modifydropsapi.api.BlockDropStacksCallback_AddDrops;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface OrganUpdateCallback {
    Event<OrganUpdateCallback> EVENT = EventFactory.createArrayBacked(OrganUpdateCallback.class,
            (listeners) -> (player, oldScores, newScores) -> {
                for (OrganUpdateCallback listener : listeners) {
                    listener.onOrganUpdate(player,oldScores,newScores);
                }
            });

    void onOrganUpdate(PlayerEntity player, Map<Identifier,Float> oldScores, Map<Identifier,Float> newScores);
}
