package net.tigereye.chestcavity.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.tigereye.chestcavity.interfaces.CCStatusEffectInstance;
import net.tigereye.chestcavity.listeners.OrganOnHitListener;
import net.tigereye.chestcavity.managers.ChestCavityManager;

import java.util.ArrayList;
import java.util.List;

public class VenomGland extends Organ implements OrganOnHitListener {

    @Override
    public float onHit(DamageSource source, LivingEntity attacker, LivingEntity target, ChestCavityManager chestCavity, ItemStack organ, float damage) {
        if(attacker.getStackInHand(attacker.getActiveHand()).isEmpty()){
            List<StatusEffectInstance> effects = getStatusEffects(organ);
            if(!effects.isEmpty()){
                for(StatusEffectInstance effect : effects){
                    target.applyStatusEffect(effect);
                }
            }
            else {
                target.applyStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 0));
            }
        }
        return damage;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        super.appendTooltip(itemStack,world,tooltip,tooltipContext);
        if(!getStatusEffects(itemStack).isEmpty()) {
            PotionUtil.buildTooltip(itemStack, tooltip, 1);
        }
    }

    public static void setStatusEffects(ItemStack organ, ItemStack potion){
        List<StatusEffectInstance> potionList = PotionUtil.getPotionEffects(potion);
        List<StatusEffectInstance> list = new ArrayList<>();
        for (StatusEffectInstance effect : potionList) {
            StatusEffectInstance effectCopy = new StatusEffectInstance(effect);
            ((CCStatusEffectInstance) effectCopy).CC_setDuration(Math.max(1,effectCopy.getDuration()/4));
            list.add(effectCopy);
        }
        setStatusEffects(organ,list);
    }

    public static void setStatusEffects(ItemStack organ, List<StatusEffectInstance> list){
        CompoundTag tag = organ.getOrCreateTag();
        ListTag listTag = new ListTag();

        for(int i = 0; i < list.size(); ++i) {
            StatusEffectInstance effect = list.get(i);
            if (effect != null) {
                CompoundTag compoundTag = new CompoundTag();
                listTag.add(effect.toTag(compoundTag));
            }
        }
        tag.put("CustomPotionEffects",listTag);
    }


    public static List<StatusEffectInstance> getStatusEffects(ItemStack organ){
        CompoundTag tag = organ.getOrCreateTag();
        ListTag listTag;
        if (!tag.contains("CustomPotionEffects", 9)) {
            return new ArrayList<>();
        }
        else{
            listTag = tag.getList("CustomPotionEffects",10);
            List<StatusEffectInstance> list = new ArrayList<>();
            for(int i = 0; i < listTag.size(); ++i) {
                CompoundTag compoundTag = listTag.getCompound(i);
                StatusEffectInstance statusEffectInstance = StatusEffectInstance.fromTag(compoundTag);
                if (statusEffectInstance != null) {
                    list.add(statusEffectInstance);
                }
            }
            return list;
        }
    }
}
