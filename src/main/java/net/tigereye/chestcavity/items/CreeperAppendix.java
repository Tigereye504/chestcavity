package net.tigereye.chestcavity.items;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import net.tigereye.chestcavity.ChestCavity;

import java.util.List;

public class CreeperAppendix extends Item {

    public CreeperAppendix(Item.Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext context, net.minecraft.world.item.component.TooltipDisplay display, java.util.function.Consumer<Component> tooltip, TooltipFlag tooltipContext) {
        super.appendHoverText(itemStack,context,display,tooltip,tooltipContext);
        tooltip.accept(Component.literal("This appears to be a fuse.").withStyle(ChatFormatting.ITALIC));
        tooltip.accept(Component.literal("It won't do much by itself.").withStyle(ChatFormatting.ITALIC));
    }
}
