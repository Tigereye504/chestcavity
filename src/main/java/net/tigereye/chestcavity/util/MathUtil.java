package net.tigereye.chestcavity.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class MathUtil {
    public static float horizontalDistanceTo(Entity entity1,Entity entity2) {
        float f = (float)(entity1.getX() - entity2.getX());
        float h = (float)(entity1.getZ() - entity2.getZ());
        return MathHelper.sqrt(f * f + h * h);
    }
}
