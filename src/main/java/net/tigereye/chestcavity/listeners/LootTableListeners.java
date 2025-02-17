package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.loot.condition.ChestCavityUnopenedLootCondition;
import net.tigereye.chestcavity.loot.entry.ChestCavityLootEntry;
import net.tigereye.chestcavity.registration.CCItems;


public class LootTableListeners {
    

    private static final Identifier DESERT_PYRAMID_LOOT_TABLE_ID = new Identifier("minecraft", "chests/desert_pyramid");

    public static void register(){
        LootPool.Builder ccDropPoolBuilder = LootPool.builder()
                .conditionally(ChestCavityUnopenedLootCondition::new)
                        .with(ChestCavityLootEntry.builder().build());
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, supplier, setter) -> {
            supplier.pool(ccDropPoolBuilder);
            //Desert Pyramids can contain decayed ribs and spines
            if (DESERT_PYRAMID_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(BinomialLootNumberProvider.create(4,.25f))
                        .with(ItemEntry.builder(CCItems.ROTTEN_RIB));
                supplier.pool(poolBuilder);
                poolBuilder = LootPool.builder()
                        .rolls(BinomialLootNumberProvider.create(1,.3f))
                        .with(ItemEntry.builder(CCItems.ROTTEN_SPINE));
                supplier.pool(poolBuilder);
            }
        });
    }
}