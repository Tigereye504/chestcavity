package net.tigereye.chestcavity.registration;

import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.condition.ValueCheckLootCondition;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.loot.condition.ChestCavityUnopenedLootCondition;

public class CCLootConditionTypes {
    public static final LootConditionType MATCH_KILLER_WEAPON = register("match_killer_weapon", new ValueCheckLootCondition.Serializer());
    public static final LootConditionType CHEST_CAVITY_UNOPENED = register("chest_cavity_unopened", new ChestCavityUnopenedLootCondition.Serializer());

    private static LootConditionType register(String id, JsonSerializer<? extends LootCondition> serializer) {
        return Registry.register(Registries.LOOT_CONDITION_TYPE, new Identifier(ChestCavity.MODID,id), new LootConditionType(serializer));
    }
}
