package net.tigereye.chestcavity.registration;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootPoolEntryType;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.tigereye.chestcavity.loot.entry.ChestCavityLootEntry;
import org.apache.commons.lang3.ArrayUtils;

public class CCLootPoolEntryTypes {
    public static final LootPoolEntryType CHEST_CAVITY_LOOT = register("chest_cavity_loot", new ChestCavityLootEntry.Serializer());

    private static LootPoolEntryType register(String id, JsonSerializer<? extends LootPoolEntry> jsonSerializer) {
        return Registry.register(Registries.LOOT_POOL_ENTRY_TYPE, new Identifier(id), new LootPoolEntryType(jsonSerializer));
    }
}
