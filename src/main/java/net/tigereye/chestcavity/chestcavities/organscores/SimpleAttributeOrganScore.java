package net.tigereye.chestcavity.chestcavities.organscores;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.listeners.OrganUpdateCallback;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.UUID;

public class SimpleAttributeOrganScore implements OrganScore{

    private final UUID MODIFIER_ID;
    private final String MODIFIER_NAME;
    private final Identifier ID;
    private final float scaler;
    private final EntityAttribute attribute;
    private final EntityAttributeModifier.Operation operation;

    public SimpleAttributeOrganScore(Identifier id, String modifierUUID, String modifierName, float scaler, EntityAttribute attribute, EntityAttributeModifier.Operation operation){
        this.ID = id;
        MODIFIER_ID = UUID.fromString(modifierUUID);
        MODIFIER_NAME = modifierName;
        this.scaler = scaler;
        this.attribute = attribute;
        this.operation = operation;
    }
    @Override
    public Identifier getID() {
        return ID;
    }

    @Override
    public void onChestCavityUpdate(LivingEntity entity, ChestCavityInstance cc, float oldScore, float newScore, float defaultScore){
        ChestCavityUtil.applyChestCavityAttributeUpdate(entity,oldScore,newScore,defaultScore,
                MODIFIER_ID, MODIFIER_NAME,scaler,attribute,operation);
    }

    @Override
    public void attachEventHooks() {
        OrganUpdateCallback.registerOrganScore(this);
    }
}
