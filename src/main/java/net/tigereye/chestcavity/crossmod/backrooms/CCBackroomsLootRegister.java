package net.tigereye.chestcavity.crossmod.backrooms;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.LootTableRanges;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootFunctionTypes;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.modifydropsapi.api.GenerateEntityLootCallbackAddLoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;



public class CCBackroomsLootRegister {

    public static final Identifier LEVEL0CHEST = new Identifier("backrooms", "chests/level0");
    public static final Identifier LEVEL1CHEST = new Identifier("backrooms", "chests/level1");
    public static final Identifier LEVEL3CHEST = new Identifier("backrooms", "chests/level3");

    public static void register(){
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (LEVEL0CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootTableRange.create(ChestCavity.config.BACKROOMS_CHEST_ORGAN_LOOT_ATTEMPTS, ChestCavity.config.BACKROOMS_CHEST_ORGAN_LOOT_CHANCE))
                        .with(ItemEntry.builder(CCItems.ROTTEN_APPENDIX))
                        .with(ItemEntry.builder(CCItems.ROTTEN_HEART))
                        .with(ItemEntry.builder(CCItems.ROTTEN_INTESTINE))
                        .with(ItemEntry.builder(CCItems.ROTTEN_KIDNEY))
                        .with(ItemEntry.builder(CCItems.ROTTEN_LIVER))
                        .with(ItemEntry.builder(CCItems.ROTTEN_LUNG))
                        .with(ItemEntry.builder(CCItems.ROTTEN_RIB))
                        .withEntry(ItemEntry.builder(CCItems.ROTTEN_RIB)
                                .apply(SetCountLootFunction.builder(BinomialLootTableRange.create(4,.6f))).build())
                        .with(ItemEntry.builder(CCItems.ROTTEN_SPINE))
                        .with(ItemEntry.builder(CCItems.ROTTEN_SPLEEN))
                        .with(ItemEntry.builder(CCItems.ROTTEN_STOMACH));
                supplier.pool(poolBuilder);
            }
            if (LEVEL1CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootTableRange.create(ChestCavity.config.BACKROOMS_CHEST_ORGAN_LOOT_ATTEMPTS,ChestCavity.config.BACKROOMS_CHEST_ORGAN_LOOT_CHANCE))
                        .with(ItemEntry.builder(CCItems.ROTTEN_APPENDIX).weight(2))
                        .with(ItemEntry.builder(CCItems.ROTTEN_HEART).weight(2))
                        .with(ItemEntry.builder(CCItems.ROTTEN_INTESTINE).weight(2))
                        .with(ItemEntry.builder(CCItems.ROTTEN_KIDNEY).weight(2))
                        .with(ItemEntry.builder(CCItems.ROTTEN_LIVER).weight(2))
                        .with(ItemEntry.builder(CCItems.ROTTEN_LUNG).weight(2))
                        .withEntry(ItemEntry.builder(CCItems.ROTTEN_RIB).weight(2)
                                .apply(SetCountLootFunction.builder(BinomialLootTableRange.create(4,.6f))).build())
                        .with(ItemEntry.builder(CCItems.ROTTEN_SPINE).weight(2))
                        .with(ItemEntry.builder(CCItems.ROTTEN_SPLEEN).weight(2))
                        .with(ItemEntry.builder(CCItems.ROTTEN_STOMACH).weight(2))
                        .with(ItemEntry.builder(CCItems.SMALL_ANIMAL_APPENDIX))
                        .with(ItemEntry.builder(CCItems.SMALL_ANIMAL_HEART))
                        .with(ItemEntry.builder(CCItems.SMALL_ANIMAL_INTESTINE))
                        .with(ItemEntry.builder(CCItems.SMALL_ANIMAL_KIDNEY))
                        .with(ItemEntry.builder(CCItems.SMALL_ANIMAL_LIVER))
                        .with(ItemEntry.builder(CCItems.SMALL_ANIMAL_LUNG))
                        .with(ItemEntry.builder(CCItems.SMALL_GILLS))
                        .withEntry(ItemEntry.builder(CCItems.SMALL_ANIMAL_RIB)
                                .apply(SetCountLootFunction.builder(BinomialLootTableRange.create(4,.6f))).build())
                        .with(ItemEntry.builder(CCItems.SMALL_ANIMAL_SPINE))
                        .with(ItemEntry.builder(CCItems.SMALL_ANIMAL_SPLEEN))
                        .with(ItemEntry.builder(CCItems.SMALL_ANIMAL_STOMACH))
                        .withEntry(ItemEntry.builder(CCItems.SMALL_ANIMAL_MUSCLE)
                                .apply(SetCountLootFunction.builder(BinomialLootTableRange.create(4,.6f))).build());
                supplier.pool(poolBuilder);
            }
            if (LEVEL3CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootTableRange.create(ChestCavity.config.BACKROOMS_CHEST_ORGAN_LOOT_ATTEMPTS,ChestCavity.config.BACKROOMS_CHEST_ORGAN_LOOT_CHANCE))
                        .withEntry(ItemEntry.builder(CCItems.HUMAN_MUSCLE).weight(8)
                            .apply(SetCountLootFunction.builder(BinomialLootTableRange.create(16,.6f))).build())
                        .with(ItemEntry.builder(CCItems.HUMAN_APPENDIX).weight(4))
                        .with(ItemEntry.builder(CCItems.HUMAN_KIDNEY).weight(4))
                        .with(ItemEntry.builder(CCItems.HUMAN_LIVER).weight(4))
                        .withEntry(ItemEntry.builder(CCItems.HUMAN_RIB).weight(4)
                                .apply(SetCountLootFunction.builder(BinomialLootTableRange.create(4,.6f))).build())
                        .with(ItemEntry.builder(CCItems.HUMAN_SPLEEN).weight(4))
                        .with(ItemEntry.builder(CCItems.HUMAN_LUNG).weight(2))
                        .with(ItemEntry.builder(CCItems.HUMAN_HEART))
                        .with(ItemEntry.builder(CCItems.HUMAN_INTESTINE))
                        .with(ItemEntry.builder(CCItems.HUMAN_SPINE))
                        .with(ItemEntry.builder(CCItems.HUMAN_STOMACH));
                supplier.pool(poolBuilder);
            }
        });
    }
}
