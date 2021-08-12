package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.chestcavities.organs.OrganManager;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.recipes.SalvageRecipe;
import net.tigereye.chestcavity.registration.CCEnchantments;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCTags;
import net.tigereye.modifydropsapi.api.GenerateEntityLootCallbackAddLoot;
import net.tigereye.modifydropsapi.api.GenerateEntityLootCallbackModifyLoot;

import java.util.*;

public class LootRegister {
    

    private static final Identifier DESERT_PYRAMID_LOOT_TABLE_ID = new Identifier("minecraft", "chests/desert_pyramid");

    private static List<SalvageRecipe> salvageRecipeList;

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
                    LivingEntity killer = (LivingEntity) lootContext.get(LootContextParameters.KILLER_ENTITY);
                    //check if loot is forbidden due to malpractice
                    if(EnchantmentHelper.getEquipmentLevel(CCEnchantments.TOMOPHOBIA, killer) > 0){
                        return loot;
                    }
                    lootingLevel = EnchantmentHelper.getEquipmentLevel(Enchantments.LOOTING, killer);
                    lootingLevel += EnchantmentHelper.getEquipmentLevel(CCEnchantments.SURGICAL, killer)*2;
                    if(killer.getStackInHand(killer.getActiveHand()).isIn(CCTags.BUTCHERING_TOOL))
                    {
                        lootingLevel = 10 + 10*lootingLevel;
                    }
                    random = lootContext.getRandom();
                }
                else{
                    lootingLevel = 0;
                    random = new Random();
                }
                //with all this passed, finally we ask the chest cavity manager what the loot will actually be.
                loot.addAll(cc.getChestCavityType().generateLootDrops(random,lootingLevel));
            }

            return loot;
        });

        GenerateEntityLootCallbackModifyLoot.EVENT.register((type,lootContext,loot) -> {
            if (lootContext.hasParameter(LootContextParameters.KILLER_ENTITY)) {
                LivingEntity killer = (LivingEntity) lootContext.get(LootContextParameters.KILLER_ENTITY);
                if(killer.getStackInHand(killer.getActiveHand()).isIn(CCTags.BUTCHERING_TOOL)){
                    //first, remove everything that can be salvaged from the loot and count them up
                    Map<SalvageRecipe, Integer> salvageResults = new HashMap<>();
                    Iterator<ItemStack> i = loot.iterator();
                    if(salvageRecipeList == null){
                        salvageRecipeList = new ArrayList<>();
                        List<CraftingRecipe> recipes = killer.world.getRecipeManager().listAllOfType(RecipeType.CRAFTING);
                        for(CraftingRecipe recipe : recipes){
                            if(recipe instanceof SalvageRecipe){
                                salvageRecipeList.add((SalvageRecipe) recipe);
                            }
                        }
                    }
                    while(i.hasNext()){
                        ItemStack stack = i.next();
                        if(stack.isIn(CCTags.SALVAGEABLE)){
                            for (SalvageRecipe recipe: salvageRecipeList) {
                                if(recipe.getInput().test(stack)){
                                    salvageResults.put(recipe,salvageResults.getOrDefault(recipe,0)+stack.getCount());
                                    i.remove();
                                    break;
                                }
                            }
                        }
                    }
                    //then, get the output of the salvage and add it to the loot
                    salvageResults.forEach((recipe,count) -> {
                        ItemStack out = recipe.getOutput();
                        out.setCount(out.getCount()*(count/recipe.getRequired()));
                        loot.add(out);
                    });
                }

                //organs gain malpractice
                if(EnchantmentHelper.getLevel(CCEnchantments.MALPRACTICE,killer.getStackInHand(killer.getActiveHand())) > 0){
                    for (ItemStack stack : loot) {
                        if (OrganManager.isTrueOrgan(stack.getItem())) {
                            stack.addEnchantment(CCEnchantments.MALPRACTICE, 1);
                        }
                    }
                }
            }
            return loot;
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (DESERT_PYRAMID_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(4,.25f))
                        .with(ItemEntry.builder(CCItems.ROTTEN_RIB));
                supplier.pool(poolBuilder);
                poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(1,.3f))
                        .with(ItemEntry.builder(CCItems.ROTTEN_RIB));
                supplier.pool(poolBuilder);
            }
        });
    }
}