package net.tigereye.chestcavity.loot.entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootDataKey;
import net.minecraft.loot.LootDataType;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableReporter;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.entry.*;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.minecraft.world.World;
import net.tigereye.chestcavity.chestcavities.organs.OrganManager;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.recipes.SalvageRecipe;
import net.tigereye.chestcavity.registration.CCEnchantments;
import net.tigereye.chestcavity.registration.CCLootPoolEntryTypes;
import net.tigereye.chestcavity.registration.CCTags;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.function.Consumer;

public class ChestCavityLootEntry extends LeafEntry {

    private static List<SalvageRecipe> salvageRecipeList;

    ChestCavityLootEntry(int weight, int quality, LootCondition[] conditions, LootFunction[] functions) {
        super(weight, quality, conditions, functions);
    }

    public LootPoolEntryType getType() {
        return CCLootPoolEntryTypes.CHEST_CAVITY_LOOT;
    }

    public void generateLoot(Consumer<ItemStack> lootConsumer, LootContext context) {
        if (!context.hasParameter(LootContextParameters.BLOCK_STATE) && context.get(LootContextParameters.THIS_ENTITY) instanceof ChestCavityEntity cce) {
            int looting = 0;
            boolean butcher = false;
            boolean malpractice = false;
            LivingEntity killer = context.get(LootContextParameters.KILLER_ENTITY) instanceof LivingEntity _killer ? _killer : null;
            if(killer != null) {
                if(EnchantmentHelper.getEquipmentLevel(CCEnchantments.TOMOPHOBIA,killer) > 0){
                    return;
                }
                looting = EnchantmentHelper.getLooting(killer) + (2*EnchantmentHelper.getEquipmentLevel(CCEnchantments.SURGICAL,killer));
                if (killer.getStackInHand(killer.getActiveHand()).isIn(CCTags.BUTCHERING_TOOL)) {
                    looting *= 10;
                    butcher = true;
                }
                if (EnchantmentHelper.getLevel(CCEnchantments.MALPRACTICE, killer.getStackInHand(killer.getActiveHand())) > 0) {
                    malpractice = true;
                }
            }
            List<ItemStack> loot = cce.getChestCavityInstance().getChestCavityType().generateLootDrops(context.getRandom(), looting);
            if(butcher) {
                processButchering(context.getWorld(),loot);
            }
            if(malpractice){
                processMalpractice(loot);
            }
            loot.forEach(lootConsumer);
        }
    }

    private void processButchering(World world, List<ItemStack> loot){
        //first, remove everything that can be salvaged from the loot and count them up
        Map<SalvageRecipe, Integer> salvageResults = new HashMap<>();
        Iterator<ItemStack> i = loot.iterator();
        if (salvageRecipeList == null) {
            salvageRecipeList = new ArrayList<>();
            List<CraftingRecipe> recipes = world.getRecipeManager().listAllOfType(RecipeType.CRAFTING);
            for (CraftingRecipe recipe : recipes) {
                if (recipe instanceof SalvageRecipe) {
                    salvageRecipeList.add((SalvageRecipe) recipe);
                }
            }
        }
        while (i.hasNext()) {
            ItemStack stack = i.next();
            if (stack.isIn(CCTags.SALVAGEABLE)) {
                for (SalvageRecipe recipe : salvageRecipeList) {
                    if (recipe.getInput().test(stack)) {
                        salvageResults.put(recipe, salvageResults.getOrDefault(recipe, 0) + stack.getCount());
                        i.remove();
                        break;
                    }
                }
            }
        }
        //then, get the output of the salvage and add it to the loot
        salvageResults.forEach((recipe, count) -> {
            ItemStack out = recipe.getOutput(world.getRegistryManager());
            out.setCount(out.getCount() * (count / recipe.getRequired()));
            loot.add(out);
        });
    }

    private void processMalpractice(List<ItemStack> loot) {
        //organs gain malpractice
        for (ItemStack stack : loot) {
            if (OrganManager.isTrueOrgan(stack.getItem())) {
                stack.addEnchantment(CCEnchantments.MALPRACTICE, 1);
            }
        }
    }

    public void validate(LootTableReporter reporter) {
        super.validate(reporter);
    }

    public static LeafEntry.Builder<?> builder() {
        return builder(ChestCavityLootEntry::new);
    }

    public static class Serializer extends LeafEntry.Serializer<ChestCavityLootEntry> {
        @Override
        protected ChestCavityLootEntry fromJson(JsonObject entryJson, JsonDeserializationContext context, int weight, int quality, LootCondition[] conditions, LootFunction[] functions) {
            return new ChestCavityLootEntry(weight,quality,conditions,functions);
        }
    }
}
