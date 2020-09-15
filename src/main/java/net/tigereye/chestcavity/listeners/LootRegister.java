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
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.items.CC_Items;
import net.tigereye.modifydropsapi.api.LivingEntityDropLootCallback_AddDrops;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootRegister {
    

    private static final Identifier VILLAGER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/villager");
    private static final Identifier PILLAGER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/pillager");
    private static final Identifier EVOKER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/evoker");
    private static final Identifier WITCH_LOOT_TABLE_ID = new Identifier("minecraft", "entities/witch");
    private static final Identifier WANDERING_TRADER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/wandering_trader");
    private static final Identifier ILLUSIONER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/illusioner");

    private static final Identifier COW_LOOT_TABLE_ID = new Identifier("minecraft", "entities/cow");
    private static final Identifier PIG_LOOT_TABLE_ID = new Identifier("minecraft", "entities/pig");
    private static final Identifier SHEEP_LOOT_TABLE_ID = new Identifier("minecraft", "entities/sheep");
    private static final Identifier HORSE_LOOT_TABLE_ID = new Identifier("minecraft", "entities/horse");
    private static final Identifier PIGLIN_LOOT_TABLE_ID = new Identifier("minecraft", "entities/piglin");

    private static final Identifier ZOMBIE_LOOT_TABLE_ID = new Identifier("minecraft", "entities/zombie");
    private static final Identifier ZOMBIE_PIGLIN_LOOT_TABLE_ID = new Identifier("minecraft", "entities/zombified_piglin");
    private static final Identifier HUSK_LOOT_TABLE_ID = new Identifier("minecraft", "entities/husk");
    private static final Identifier DROWNED_PIGLIN_LOOT_TABLE_ID = new Identifier("minecraft", "entities/drowned");

    private static final Identifier SKELETON_LOOT_TABLE_ID = new Identifier("minecraft", "entities/skeleton");
    private static final Identifier WITHER_SKELETON_LOOT_TABLE_ID = new Identifier("minecraft", "entities/wither_skeleton");
    
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
                                    loot.add(new ItemStack(CC_Items.rottenApendix));
                                    break;
                                case 1:
                                    loot.add(new ItemStack(CC_Items.rottenHeart));
                                    break;
                                case 2:
                                    loot.add(new ItemStack(CC_Items.rottenIntestine));
                                    break;
                                case 3:
                                    loot.add(new ItemStack(CC_Items.rottenKidney));
                                    break;
                                case 4:
                                    loot.add(new ItemStack(CC_Items.rottenLiver));
                                    break;
                                case 5:
                                    loot.add(new ItemStack(CC_Items.rottenLung));
                                    break;
                                case 6:
                                    loot.add(new ItemStack(CC_Items.rottenSpleen));
                                    break;
                                case 7:
                                    loot.add(new ItemStack(CC_Items.rottenStomach));
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
                                loot.add(new ItemStack(CC_Items.rottenSpine));
                            }
                            else{
                                loot.add(new ItemStack(CC_Items.rottenRib));
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
                                loot.add(new ItemStack(CC_Items.animalRib));
                            }
                            else if(roll == 5){
                                loot.add(new ItemStack(CC_Items.animalApendix));
                            }
                            else if(roll == 6){
                                loot.add(new ItemStack(CC_Items.animalHeart));
                            }
                            else if(roll == 7){
                                loot.add(new ItemStack(CC_Items.animalIntestine));
                            }
                            else if(roll == 8){
                                loot.add(new ItemStack(CC_Items.animalKidney));
                            }
                            else if(roll == 9){
                                loot.add(new ItemStack(CC_Items.animalLiver));
                            }
                            else if(roll == 10){
                                loot.add(new ItemStack(CC_Items.animalLung));
                            }
                            else if(roll == 11){
                                loot.add(new ItemStack(CC_Items.animalSpine));
                            }
                            else if(roll == 12){
                                loot.add(new ItemStack(CC_Items.animalSpleen));
                            }
                            else{
                                loot.add(new ItemStack(CC_Items.animalStomach));
                            }
                        }
                    }
                }
                else if (entity instanceof PatrolEntity || entity instanceof VillagerEntity || entity instanceof WanderingTraderEntity) {
                    if(random.nextFloat() < .025 + (.01f*EnchantmentHelper.getLooting(player))) {
                        int rolls = 13 + random.nextInt(52);
                        for (int i = 0; i < rolls; i++){
                            int roll = random.nextInt(78);
                            if(roll < 4) {
                                loot.add(new ItemStack(CC_Items.rib));
                            }
                            else if(roll == 5){
                                loot.add(new ItemStack(CC_Items.apendix));
                            }
                            else if(roll == 6){
                                loot.add(new ItemStack(CC_Items.heart));
                            }
                            else if(roll == 7){
                                loot.add(new ItemStack(CC_Items.intestine));
                            }
                            else if(roll == 8){
                                loot.add(new ItemStack(CC_Items.kidney));
                            }
                            else if(roll == 9){
                                loot.add(new ItemStack(CC_Items.liver));
                            }
                            else if(roll == 10){
                                loot.add(new ItemStack(CC_Items.lung));
                            }
                            else if(roll == 11){
                                loot.add(new ItemStack(CC_Items.spine));
                            }
                            else if(roll == 12){
                                loot.add(new ItemStack(CC_Items.spleen));
                            }
                            else if(roll == 13){
                                loot.add(new ItemStack(CC_Items.stomach));
                            }
                            else{
                                loot.add(new ItemStack(CC_Items.muscle));
                            }
                        }
                    }
                }
            }

            return loot;
        });
    }
}