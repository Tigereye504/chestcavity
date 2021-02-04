package net.tigereye.chestcavity.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.listeners.OrganOnHitListener;
import net.tigereye.chestcavity.registration.CCStatusEffects;
import net.tigereye.chestcavity.util.OrganUtil;

import java.util.List;

public class VenomGland extends Organ implements OrganOnHitListener {

    @Override
    public float onHit(DamageSource source, LivingEntity attacker, LivingEntity target, ChestCavityInstance cc, ItemStack organ, float damage) {
        if(attacker.getStackInHand(attacker.getActiveHand()).isEmpty()){
            if(attacker.hasStatusEffect(CCStatusEffects.VENOM_COOLDOWN)){
                StatusEffectInstance cooldown = attacker.getStatusEffect(CCStatusEffects.VENOM_COOLDOWN);
                //this is to check if the cooldown was inflicted this same tick; likely because of other venom glands
                if(cooldown.getDuration() != ChestCavity.config.VENOM_COOLDOWN){
                    return damage;
                }
            }
            List<StatusEffectInstance> effects = OrganUtil.getStatusEffects(organ);
            if(!effects.isEmpty()){
                for(StatusEffectInstance effect : effects){
                    target.applyStatusEffect(effect);
                }
            }
            else {
                target.applyStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 0));
            }
            attacker.applyStatusEffect(new StatusEffectInstance(CCStatusEffects.VENOM_COOLDOWN, ChestCavity.config.VENOM_COOLDOWN, 0));
            if(attacker instanceof PlayerEntity){
                ((PlayerEntity)attacker).addExhaustion(.1f);
            }
        }
        return damage;
        //TODO: add cooldown to venom gland
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        super.appendTooltip(itemStack,world,tooltip,tooltipContext);
        if(!OrganUtil.getStatusEffects(itemStack).isEmpty()) {
            PotionUtil.buildTooltip(itemStack, tooltip, 1);
        }
    }


}
