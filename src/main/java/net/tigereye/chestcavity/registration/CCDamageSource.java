package net.tigereye.chestcavity.registration;

import net.minecraft.entity.damage.DamageSource;

public class CCDamageSource extends DamageSource {
    public static final DamageSource HEARTBLEED = new CCDamageSource("ccHeartbleed").setBypassesArmor();
    public static final DamageSource ORGAN_REJECTION = new CCDamageSource("ccOrganRejection").setBypassesArmor();

    public CCDamageSource(String name) {
        super(name);
    }


}
