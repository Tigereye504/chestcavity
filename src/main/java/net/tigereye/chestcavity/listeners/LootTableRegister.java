package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.items.RegisterItems;

public class LootTableRegister {
    

    private static final Identifier VILLAGER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/villager");
    private static final Identifier PILLAGER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/pillager");
    private static final Identifier EVOKER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/evoker");
    private static final Identifier WITCH_LOOT_TABLE_ID = new Identifier("minecraft", "entities/witch");
    private static final Identifier WANDERING_TRADER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/wandering_trader");
    private static final Identifier ILLUSIONER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/illusioner");

    private static final Identifier ZOMBIE_LOOT_TABLE_ID = new Identifier("minecraft", "entities/zombie");
    private static final Identifier ZOMBIE_PIGLIN_LOOT_TABLE_ID = new Identifier("minecraft", "entities/zombified_piglin");
    private static final Identifier HUSK_LOOT_TABLE_ID = new Identifier("minecraft", "entities/husk");
    private static final Identifier DROWNED_PIGLIN_LOOT_TABLE_ID = new Identifier("minecraft", "entities/drowned");

    private static final Identifier SKELETON_LOOT_TABLE_ID = new Identifier("minecraft", "entities/skeleton");
    private static final Identifier WITHER_SKELETON_LOOT_TABLE_ID = new Identifier("minecraft", "entities/wither_skeleton");
    
    public static void register(){
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
           if (ZOMBIE_LOOT_TABLE_ID.equals(id) 
                || ZOMBIE_PIGLIN_LOOT_TABLE_ID.equals(id)
                || HUSK_LOOT_TABLE_ID.equals(id)
                || DROWNED_PIGLIN_LOOT_TABLE_ID.equals(id)
                ) {
                LootPool pool = FabricLootPoolBuilder.builder()
                    .rolls(BinomialLootTableRange.create(3, 7))
                    .withEntry(ItemEntry.builder(RegisterItems.rottenApendix).build())
                    .withEntry(ItemEntry.builder(RegisterItems.rottenHeart).build())
                    .withEntry(ItemEntry.builder(RegisterItems.rottenIntestine).build())
                    .withEntry(ItemEntry.builder(RegisterItems.rottenKidney).build())
                    .withEntry(ItemEntry.builder(RegisterItems.rottenLiver).build())
                    .withEntry(ItemEntry.builder(RegisterItems.rottenLung).build())
                    .withEntry(ItemEntry.builder(RegisterItems.rottenSpleen).build())
                    .withEntry(ItemEntry.builder(RegisterItems.rottenStomach).build())
                    .conditionally(KilledByPlayerLootCondition.builder())
                    .conditionally(RandomChanceWithLootingLootCondition.builder(.025f, .01f))
                    .build();
                supplier.withPool(pool);
            }
            if (SKELETON_LOOT_TABLE_ID.equals(id)
                || WITHER_SKELETON_LOOT_TABLE_ID.equals(id)
                ) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(BinomialLootTableRange.create(1, 4))
                    .with(ItemEntry.builder(RegisterItems.rottenSpine))
                    .with(ItemEntry.builder(RegisterItems.rottenRib).weight(4))
                    .conditionally(KilledByPlayerLootCondition.builder())
                    .conditionally(RandomChanceWithLootingLootCondition.builder(.025f, .01f));
                supplier.pool(poolBuilder);
            }
            if (VILLAGER_LOOT_TABLE_ID.equals(id) 
                || WANDERING_TRADER_LOOT_TABLE_ID.equals(id)
                || PILLAGER_LOOT_TABLE_ID.equals(id)
                || EVOKER_LOOT_TABLE_ID.equals(id)
                || ILLUSIONER_LOOT_TABLE_ID.equals(id)
                || WITCH_LOOT_TABLE_ID.equals(id)
                ) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(BinomialLootTableRange.create(13, 64))
                    .with(ItemEntry.builder(RegisterItems.apendix))
                    .with(ItemEntry.builder(RegisterItems.heart))
                    .with(ItemEntry.builder(RegisterItems.intestine))
                    .with(ItemEntry.builder(RegisterItems.kidney))
                    .with(ItemEntry.builder(RegisterItems.liver))
                    .with(ItemEntry.builder(RegisterItems.lung))
                    .with(ItemEntry.builder(RegisterItems.muscle).weight(64))
                    .with(ItemEntry.builder(RegisterItems.rib).weight(4))
                    .with(ItemEntry.builder(RegisterItems.spine))
                    .with(ItemEntry.builder(RegisterItems.spleen))
                    .with(ItemEntry.builder(RegisterItems.stomach))
                    .conditionally(KilledByPlayerLootCondition.builder())
                    .conditionally(RandomChanceWithLootingLootCondition.builder(.025f, .01f));
                supplier.pool(poolBuilder);
            }
        });
    }
}