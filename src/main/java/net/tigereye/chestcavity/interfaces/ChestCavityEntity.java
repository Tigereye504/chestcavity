package net.tigereye.chestcavity.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.tigereye.chestcavity.managers.ChestCavityManager;

import java.util.Optional;

public interface ChestCavityEntity {
    static Optional<ChestCavityEntity> of(Entity entity) {
        if (entity instanceof ChestCavityEntity) {
            return Optional.of(((ChestCavityEntity) entity));
        }
        return Optional.empty();
    }

    ChestCavityManager getChestCavityManager();
    void setChestCavityManager(ChestCavityManager chestCavityManager);
    boolean damage(DamageSource source, float damage);
}
