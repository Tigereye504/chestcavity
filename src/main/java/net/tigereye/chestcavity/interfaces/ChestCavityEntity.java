package net.tigereye.chestcavity.interfaces;

import net.minecraft.entity.Entity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

import java.util.Optional;

public interface ChestCavityEntity {
    static Optional<ChestCavityEntity> of(Entity entity) {
        if (entity instanceof ChestCavityEntity) {
            return Optional.of(((ChestCavityEntity) entity));
        }
        return Optional.empty();
    }

    ChestCavityInstance getChestCavityInstance();
    void setChestCavityInstance(ChestCavityInstance cc);
}
