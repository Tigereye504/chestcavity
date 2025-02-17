package net.tigereye.chestcavity.loot.condition;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.predicate.item.ItemPredicate;
import net.tigereye.chestcavity.registration.CCLootConditionTypes;

public class MatchKillerWeaponLootCondition implements LootCondition {
    final ItemPredicate predicate;

    public MatchKillerWeaponLootCondition(ItemPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public LootConditionType getType() {
        return CCLootConditionTypes.MATCH_KILLER_WEAPON;
    }

    @Override
    public boolean test(LootContext lootContext) {
        Entity entity = lootContext.get(LootContextParameters.DIRECT_KILLER_ENTITY);
        ItemStack tool = null;
        if(entity instanceof LivingEntity lEntity){
            try {
                tool = lEntity.getStackInHand(lEntity.getActiveHand());
            }
            catch(Exception e){
                tool = null;
            }
        }
        return tool != null && this.predicate.test(tool);
    }

    public static Builder builder(ItemPredicate.Builder predicate) {
        return () -> new MatchKillerWeaponLootCondition(predicate.build());
    }
}
