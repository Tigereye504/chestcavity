package net.tigereye.chestcavity.chestcavities.organscores;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.listeners.OrganUpdateCallback;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.UUID;

public class NervesOrganScore implements OrganScore{

    private final UUID MOVEMENT_MODIFIER_ID = UUID.fromString("8f56feed-589f-416f-86c5-315765d41f57");
    private final UUID ATTACK_SPEED_MODIFIER_ID =UUID.fromString("709e3e77-0586-4304-80b5-d28bc477e947");
    private final String MOVEMENT_MODIFIER_NAME = "ChestCavitySpineMovement";
    private final String ATTACK_SPEED_MODIFIER_NAME = "ChestCavitySpineAttackSpeed";
    private final Identifier ID;

    public NervesOrganScore(Identifier id){
        this.ID = id;
    }
    @Override
    public Identifier getID() {
        return ID;
    }

    @Override
    public void onChestCavityUpdate(LivingEntity entity, ChestCavityInstance cc, float oldScore, float newScore, float defaultScore){
        if(oldScore != newScore && defaultScore != 0) {
            EntityAttributeInstance att = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            if(att != null) {
                EntityAttributeModifier mod = new EntityAttributeModifier(MOVEMENT_MODIFIER_ID, MOVEMENT_MODIFIER_NAME,
                        newScore > 0 ? 0 : -1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
                ChestCavityUtil.ReplaceAttributeModifier(att, mod);
            }
        }
        ChestCavityUtil.applyChestCavityAttributeUpdate(entity,oldScore,newScore,defaultScore,
                ATTACK_SPEED_MODIFIER_ID, ATTACK_SPEED_MODIFIER_NAME,ChestCavity.config.NERVES_HASTE, EntityAttributes.GENERIC_ATTACK_SPEED,EntityAttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public void attachEventHooks() {
        OrganUpdateCallback.registerOrganScore(this);
    }
}
