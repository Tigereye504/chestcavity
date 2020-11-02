package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.managers.ChestCavityManager;
import net.tigereye.chestcavity.managers.SkeletonChestCavityManager;
import net.tigereye.chestcavity.managers.ZombieChestCavityManager;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.modifydropsapi.api.GenerateEntityLootCallbackAddLoot;

import java.util.*;

public class LootRegister {
    

    private static final Identifier DESERT_PYRAMID_LOOT_TABLE_ID = new Identifier("minecraft", "chests/desert_pyramid");



    public static void register(){ //TODO: only use loot for organs if target is unopened
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
                ChestCavityManager ccManager = chestCavityEntity.get().getChestCavityManager();
                //check if loot is already generated due to having opened the target's chest cavity
                if(ccManager.getOpened()){
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
                loot.addAll(ccManager.generateLootDrops(random,lootingLevel));
                /*
                if (ccManager instanceof ZombieChestCavityManager) {
                    if(random.nextFloat() < ChestCavity.config.ORGAN_BUNDLE_DROP_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*lootingLevel)) {
                        LinkedList<Item> organPile = new LinkedList<>();
                        organPile.add(CCItems.ROTTEN_APPENDIX);
                        organPile.add(CCItems.ROTTEN_HEART);
                        organPile.add(CCItems.ROTTEN_INTESTINE);
                        organPile.add(CCItems.ROTTEN_KIDNEY);
                        organPile.add(CCItems.ROTTEN_LIVER);
                        organPile.add(CCItems.ROTTEN_LUNG);
                        organPile.add(CCItems.ROTTEN_SPLEEN);
                        organPile.add(CCItems.ROTTEN_STOMACH);
                        int rolls = 1 + random.nextInt(3) + random.nextInt(3);
                        for (int i = 0; i < rolls; i++){
                            int roll = random.nextInt(organPile.size());
                            loot.add(new ItemStack(organPile.remove(roll)));

                        }
                    }
                }
                else if (ccManager instanceof SkeletonChestCavityManager) {
                    if(random.nextFloat() < ChestCavity.config.ORGAN_BUNDLE_DROP_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*lootingLevel)) {
                        LinkedList<Item> organPile = new LinkedList<>();
                        organPile.add(CCItems.ROTTEN_SPINE);
                        for(int i = 0; i < 16; i++){
                            organPile.add(CCItems.ROTTEN_RIB);
                        }
                        int rolls = 1 + random.nextInt(3) + random.nextInt(3);
                        for (int i = 0; i < rolls; i++){
                            int roll = random.nextInt(organPile.size());
                            loot.add(new ItemStack(organPile.remove(roll)));

                        }
                    }
                }
                else if (entity instanceof AnimalEntity || entity instanceof AbstractPiglinEntity) {
                    if(random.nextFloat() < ChestCavity.config.ORGAN_BUNDLE_DROP_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*lootingLevel)) {
                        LinkedList<Item> organPile = new LinkedList<>();
                        for(int i = 0; i < 16; i++){
                            organPile.add(CCItems.ANIMAL_RIB);
                        }
                        organPile.add(CCItems.ANIMAL_APPENDIX);
                        organPile.add(CCItems.ANIMAL_HEART);
                        organPile.add(CCItems.ANIMAL_INTESTINE);
                        organPile.add(CCItems.ANIMAL_INTESTINE);
                        organPile.add(CCItems.ANIMAL_INTESTINE);
                        organPile.add(CCItems.ANIMAL_INTESTINE);
                        organPile.add(CCItems.ANIMAL_KIDNEY);
                        organPile.add(CCItems.ANIMAL_KIDNEY);
                        organPile.add(CCItems.ANIMAL_LIVER);
                        organPile.add(CCItems.ANIMAL_LUNG);
                        organPile.add(CCItems.ANIMAL_LUNG);
                        organPile.add(CCItems.ANIMAL_SPINE);
                        organPile.add(CCItems.ANIMAL_SPLEEN);
                        organPile.add(CCItems.ANIMAL_STOMACH);
                        int rolls = 3 + random.nextInt(3) + random.nextInt(3);
                        for (int i = 0; i < rolls; i++){
                            int roll = random.nextInt(organPile.size());
                            loot.add(new ItemStack(organPile.remove(roll)));
                        }
                    }
                }
                else if (entity instanceof PatrolEntity || entity instanceof VillagerEntity || entity instanceof WanderingTraderEntity) {
                    if(random.nextFloat() < ChestCavity.config.ORGAN_BUNDLE_DROP_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*lootingLevel)) {
                        LinkedList<Item> organPile = new LinkedList<>();
                        for(int i = 0; i < 16; i++){
                            organPile.add(CCItems.HUMAN_RIB);
                        }
                        organPile.add(CCItems.HUMAN_APPENDIX);
                        organPile.add(CCItems.HUMAN_HEART);
                        organPile.add(CCItems.HUMAN_INTESTINE);
                        organPile.add(CCItems.HUMAN_INTESTINE);
                        organPile.add(CCItems.HUMAN_INTESTINE);
                        organPile.add(CCItems.HUMAN_INTESTINE);
                        organPile.add(CCItems.HUMAN_KIDNEY);
                        organPile.add(CCItems.HUMAN_KIDNEY);
                        organPile.add(CCItems.HUMAN_LIVER);
                        organPile.add(CCItems.HUMAN_LUNG);
                        organPile.add(CCItems.HUMAN_LUNG);
                        organPile.add(CCItems.HUMAN_SPINE);
                        organPile.add(CCItems.HUMAN_SPLEEN);
                        organPile.add(CCItems.HUMAN_STOMACH);
                        int rolls = 3 + random.nextInt(3) + random.nextInt(3);
                        for (int i = 0; i < rolls; i++){
                            int roll = random.nextInt(organPile.size());
                            loot.add(new ItemStack(organPile.remove(roll)));
                        }
                        //14+4d8 (32) muscles, added as single stacks because I like how it makes targets explode into meat
                        rolls = 28 + random.nextInt(8) + random.nextInt(8) + random.nextInt(8) + random.nextInt(8);
                        for (int i = 0; i < rolls; i++){
                            loot.add(new ItemStack(CCItems.HUMAN_MUSCLE));
                        }
                    }
                }
                */
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