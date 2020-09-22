package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.items.CC_Items;
import net.tigereye.modifydropsapi.api.LivingEntityDropLootCallback_AddDrops;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootRegister {
    

    private static final Identifier DESERT_PYRAMID_LOOT_TABLE_ID = new Identifier("minecraft", "chests/desert_pyramid");



    public static void register(){
        LivingEntityDropLootCallback_AddDrops.EVENT.register((entity, source, causedByPlayer) -> {
            List<ItemStack> loot = new ArrayList<>();
            if (source.getAttacker() instanceof PlayerEntity) {
                Random random = new Random();
                PlayerEntity player = (PlayerEntity) source.getAttacker();
                if (entity instanceof ZombieEntity) {
                    if(random.nextFloat() < .025 + (.01f*EnchantmentHelper.getLooting(player))) {
                        int rolls = 3 + random.nextInt(5);
                        for (int i = 0; i < rolls; i++){
                            int roll = random.nextInt(8);
                            switch (roll) {
                                case 0:
                                    loot.add(new ItemStack(CC_Items.ROTTEN_APPENDIX));
                                    break;
                                case 1:
                                    loot.add(new ItemStack(CC_Items.ROTTEN_HEART));
                                    break;
                                case 2:
                                    loot.add(new ItemStack(CC_Items.ROTTEN_INTESTINE));
                                    break;
                                case 3:
                                    loot.add(new ItemStack(CC_Items.ROTTEN_KIDNEY));
                                    break;
                                case 4:
                                    loot.add(new ItemStack(CC_Items.ROTTEN_LIVER));
                                    break;
                                case 5:
                                    loot.add(new ItemStack(CC_Items.ROTTEN_LUNG));
                                    break;
                                case 6:
                                    loot.add(new ItemStack(CC_Items.ROTTEN_SPLEEN));
                                    break;
                                case 7:
                                    loot.add(new ItemStack(CC_Items.ROTTEN_STOMACH));
                                    break;
                            }
                        }
                    }
                }
                else if (entity instanceof SkeletonEntity) {
                    if(random.nextFloat() < .025 + (.01f*EnchantmentHelper.getLooting(player))) {
                        int rolls = 1 + random.nextInt(4);
                        for (int i = 0; i < rolls; i++){
                            int roll = random.nextInt(5);
                            if(roll == 4) {
                                loot.add(new ItemStack(CC_Items.ROTTEN_SPINE));
                            }
                            else{
                                loot.add(new ItemStack(CC_Items.ROTTEN_RIB));
                            }
                        }
                    }
                }
                else if (entity instanceof AnimalEntity || entity instanceof AbstractPiglinEntity) {
                    if(random.nextFloat() < .025 + (.01f*EnchantmentHelper.getLooting(player))) {
                        int rolls = 4 + random.nextInt(6);
                        for (int i = 0; i < rolls; i++){
                            int roll = random.nextInt(14);
                            if(roll < 4) {
                                loot.add(new ItemStack(CC_Items.ANIMAL_RIB));
                            }
                            else if(roll == 5){
                                loot.add(new ItemStack(CC_Items.ANIMAL_APPENDIX));
                            }
                            else if(roll == 6){
                                loot.add(new ItemStack(CC_Items.ANIMAL_HEART));
                            }
                            else if(roll == 7){
                                loot.add(new ItemStack(CC_Items.ANIMAL_INTESTINE));
                            }
                            else if(roll == 8){
                                loot.add(new ItemStack(CC_Items.ANIMAL_KIDNEY));
                            }
                            else if(roll == 9){
                                loot.add(new ItemStack(CC_Items.ANIMAL_LIVER));
                            }
                            else if(roll == 10){
                                loot.add(new ItemStack(CC_Items.ANIMAL_LUNG));
                            }
                            else if(roll == 11){
                                loot.add(new ItemStack(CC_Items.ANIMAL_SPINE));
                            }
                            else if(roll == 12){
                                loot.add(new ItemStack(CC_Items.ANIMAL_SPLEEN));
                            }
                            else{
                                loot.add(new ItemStack(CC_Items.ANIMAL_STOMACH));
                            }
                        }
                    }
                }
                else if (entity instanceof PatrolEntity || entity instanceof VillagerEntity || entity instanceof WanderingTraderEntity) {
                    if(random.nextFloat() < .025 + (.01f*EnchantmentHelper.getLooting(player))) {
                        int rolls = 4 + random.nextInt(6);
                        for (int i = 0; i < rolls; i++){
                            int roll = random.nextInt(78);
                            if(roll < 4) {
                                loot.add(new ItemStack(CC_Items.RIB));
                            }
                            else if(roll == 5){
                                loot.add(new ItemStack(CC_Items.APPENDIX));
                            }
                            else if(roll == 6){
                                loot.add(new ItemStack(CC_Items.HEART));
                            }
                            else if(roll == 7){
                                loot.add(new ItemStack(CC_Items.INTESTINE));
                            }
                            else if(roll == 8){
                                loot.add(new ItemStack(CC_Items.KIDNEY));
                            }
                            else if(roll == 9){
                                loot.add(new ItemStack(CC_Items.LIVER));
                            }
                            else if(roll == 10){
                                loot.add(new ItemStack(CC_Items.LUNG));
                            }
                            else if(roll == 11){
                                loot.add(new ItemStack(CC_Items.SPINE));
                            }
                            else if(roll == 12){
                                loot.add(new ItemStack(CC_Items.SPLEEN));
                            }
                            else if(roll == 13){
                                loot.add(new ItemStack(CC_Items.STOMACH));
                            }
                        }
                        //16+4d8 muscles, added as single stacks because I like how it makes targets explode into meat
                        rolls = 20 + random.nextInt(8) + random.nextInt(8) + random.nextInt(8) + random.nextInt(8);
                        for (int i = 0; i < rolls; i++){
                            loot.add(new ItemStack(CC_Items.MUSCLE));
                        }
                    }
                }
            }

            return loot;
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (DESERT_PYRAMID_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootTableRange.create(4))
                        .with(ItemEntry.builder(CC_Items.ROTTEN_RIB))
                        .conditionally(RandomChanceLootCondition.builder(.25f));
                supplier.pool(poolBuilder);
                poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootTableRange.create(1))
                                .with(ItemEntry.builder(CC_Items.ROTTEN_RIB))
                                .conditionally(RandomChanceLootCondition.builder(.3f));
                supplier.pool(poolBuilder);
            }
        });
    }
}