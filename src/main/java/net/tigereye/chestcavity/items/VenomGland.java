package net.tigereye.chestcavity.items;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LlamaSpit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.listeners.OrganOnHitListener;
import net.tigereye.chestcavity.registration.CCFoodComponents;
import net.tigereye.chestcavity.registration.CCStatusEffects;
import net.tigereye.chestcavity.util.OrganUtil;

import java.util.List;

public class VenomGland extends Item implements OrganOnHitListener {

    public VenomGland(Item.Properties properties) {
        super(properties.stacksTo(1).food(CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.food(), CCFoodComponents.RAW_TOXIC_ORGAN_MEAT_FOOD_COMPONENT.consumable()));
    }

    @Override
    public float onHit(DamageSource source, LivingEntity attacker, LivingEntity target, ChestCavityInstance cc, ItemStack organ, float damage) {
        if(attacker.getItemInHand(attacker.getUsedItemHand()).isEmpty()
        //venom glands don't trigger from projectiles... unless it is llama spit. Because I find that hilarious.
        ||(source.isDirect() == false && !(source.getDirectEntity() instanceof LlamaSpit))){
            if(source.isDirect() == false &&
                    !(source.getDirectEntity() instanceof LlamaSpit)){
                return damage;
            }
            //venom glands don't trigger if they are on cooldown,
            //unless that cooldown was applied this same tick
            if(attacker.hasEffect(CCStatusEffects.VENOM_COOLDOWN)){
                MobEffectInstance cooldown = attacker.getEffect(CCStatusEffects.VENOM_COOLDOWN);
                //this is to check if the cooldown was inflicted this same tick; likely because of other venom glands
                if(cooldown.getDuration() != ChestCavity.config.VENOM_COOLDOWN){
                    return damage;
                }
            }
            //failure conditions passed, the venom gland now delivers its payload
            List<MobEffectInstance> effects = OrganUtil.getStatusEffects(organ);
            if(!effects.isEmpty()){
                for(MobEffectInstance effect : effects){
                    target.addEffect(effect);
                }
            }
            else {
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
            }
            attacker.addEffect(new MobEffectInstance(CCStatusEffects.VENOM_COOLDOWN, ChestCavity.config.VENOM_COOLDOWN, 0));
            if(attacker instanceof Player){
                ((Player)attacker).causeFoodExhaustion(.1f);
            }
        }
        return damage;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext context, net.minecraft.world.item.component.TooltipDisplay display, java.util.function.Consumer<Component> tooltip, TooltipFlag tooltipContext) {
        super.appendHoverText(itemStack,context,display,tooltip,tooltipContext);
        if(!OrganUtil.getStatusEffects(itemStack).isEmpty()) {
            net.minecraft.world.item.alchemy.PotionContents.addPotionTooltip(OrganUtil.getStatusEffects(itemStack), tooltip, 1,20);
        }
    }

}
