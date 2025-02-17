package net.tigereye.chestcavity.loot.condition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCLootConditionTypes;

public class ChestCavityUnopenedLootCondition implements LootCondition {

    public ChestCavityUnopenedLootCondition() {}

    @Override
    public LootConditionType getType() {
        return CCLootConditionTypes.CHEST_CAVITY_UNOPENED;
    }

    @Override
    public boolean test(LootContext lootContext) {
        if(lootContext.get(LootContextParameters.THIS_ENTITY) instanceof ChestCavityEntity ccEntity){
            return !ccEntity.getChestCavityInstance().opened;
        }
        return false;
    }

    public static Builder builder(ItemPredicate.Builder predicate) {
        return ChestCavityUnopenedLootCondition::new;
    }

    public static class Serializer implements JsonSerializer<ChestCavityUnopenedLootCondition> {
        public Serializer() {
        }

        public void toJson(JsonObject jsonObject, ChestCavityUnopenedLootCondition valueCheckLootCondition, JsonSerializationContext jsonSerializationContext) {
        }

        public ChestCavityUnopenedLootCondition fromJson(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return new ChestCavityUnopenedLootCondition();
        }
    }
}
