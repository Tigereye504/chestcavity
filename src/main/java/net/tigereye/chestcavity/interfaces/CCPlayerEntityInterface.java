package net.tigereye.chestcavity.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.tigereye.chestcavity.ChestCavityManager;

import java.util.Optional;

public interface CCPlayerEntityInterface {
    static Optional<CCPlayerEntityInterface> of(PlayerEntity playerEntity) {
        if (playerEntity instanceof CCPlayerEntityInterface) {
            return Optional.of(((CCPlayerEntityInterface) playerEntity));
        }
        return Optional.empty();
    }

    ChestCavityManager getChestCavityManager();
    void setChestCavityManager(ChestCavityManager chestCavityManager);
}
