package net.tigereye.chestcavity.crossmod.anthropophagy;

import moriyashiine.anthropophagy.api.accessor.CannibalAccessor;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tigereye.chestcavity.items.OrganBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TetheredCannibalHeart extends OrganBase {

    @Override
    public Map<Identifier, Float> getOrganQualityMap(ItemStack item, LivingEntity entity) {
        if(item.getTag() == null){
            item.setTag(new CompoundTag());
            return getOrganQualityMap();
        }
        if(!item.getTag().contains("wendigoism")) {
            return getOrganQualityMap();
        }
        Map<Identifier,Float> retMap = new HashMap<>(organQualityMap);
        retMap.put(CCAnthropophagyListeners.CANNIBALISM_TARGET,(float)item.getTag().getInt("wendigoism"));
        return retMap;
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient()){
            if(stack.getTag() == null){
                stack.setTag(new CompoundTag());
            }
            if(!stack.getTag().contains("wendigoism")) {
                int wendigoism = 0;
                if (entity instanceof CannibalAccessor) {
                    CannibalAccessor accessor = (CannibalAccessor) entity;
                    wendigoism = accessor.getCannibalLevel();
                }
                stack.getTag().putInt("wendigoism", wendigoism);
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        int wendigoism = -1;
        if(itemStack.getTag() == null){
            itemStack.setTag(new CompoundTag());
        }
        if(itemStack.getTag().contains("wendigoism")) {
            wendigoism = itemStack.getTag().getInt("wendigoism");
        }
        LiteralText text = new LiteralText("Cannibalism: "+wendigoism);
        tooltip.add(text);
        super.appendTooltip(itemStack,world,tooltip,tooltipContext);
    }
}
