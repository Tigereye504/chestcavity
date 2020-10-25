package net.tigereye.chestcavity.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.tigereye.chestcavity.managers.ChestCavityManager;

import java.util.Optional;

public interface ChestCavityEntity {
    static Optional<ChestCavityEntity> of(PlayerEntity playerEntity) {
        if (playerEntity instanceof ChestCavityEntity) {
            return Optional.of(((ChestCavityEntity) playerEntity));
        }
        return Optional.empty();
    }

    ChestCavityManager getChestCavityManager();
    void setChestCavityManager(ChestCavityManager chestCavityManager);
}
