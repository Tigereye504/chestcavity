package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.modifydropsapi.api.GenerateBlockLootCallbackModifyLoot;
import net.tigereye.modifydropsapi.api.GenerateEntityLootCallbackAddLoot;

import java.util.*;

public class LootRegister {
    

    private static final Identifier DESERT_PYRAMID_LOOT_TABLE_ID = new Identifier("minecraft", "chests/desert_pyramid");



    public static void register(){
        GenerateEntityLootCallbackAddLoot.EVENT.register((type, lootContext) -> {
            List<ItemStack> loot = new ArrayList<>();
            if (lootContext.hasParameter(LootContextParameters.LAST_DAMAGE_PLAYER)) {
                int lootingLevel;
                Random random;
                Entity entity = lootContext.get(LootContextParameters.THIS_ENTITY);
                Optional<ChestCavityEntity> chestCavityEntity = ChestCavityEntity.of(entity);
                //check that the entity does have a chest cavity
                if(!chestCavityEntity.isPresent()){
                    return loot;
                }
                ChestCavityInstance cc = chestCavityEntity.get().getChestCavityInstance();
                //check if loot is already generated due to having opened the target's chest cavity
                if(cc.opened){
                    return loot;
                }
                //get looting level and random
                if(lootContext.get(LootContextParameters.KILLER_ENTITY) instanceof LivingEntity){
                    lootingLevel = EnchantmentHelper.getLooting((LivingEntity) lootContext.get(LootContextParameters.KILLER_ENTITY));
                    random = lootContext.getRandom();
                }
                else{
                    lootingLevel = 0;
                    random = new Random();
                }
                //with all this passed, finally we ask the chest cavity manager what the loot will actually be.
                loot.addAll(cc.type.generateLootDrops(random,lootingLevel));
            }

            return loot;
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (DESERT_PYRAMID_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootTableRange.create(4,.25f))
                        .with(ItemEntry.builder(CCItems.ROTTEN_RIB));
                supplier.pool(poolBuilder);
                poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootTableRange.create(1,.3f))
                        .with(ItemEntry.builder(CCItems.ROTTEN_RIB));
                supplier.pool(poolBuilder);
            }
        });
    }
}