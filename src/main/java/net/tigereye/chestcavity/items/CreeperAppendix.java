package net.tigereye.chestcavity.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class CreeperAppendix extends Item {

    public CreeperAppendix() {
        super(new Settings().maxCount(1));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        super.appendTooltip(itemStack,world,tooltip,tooltipContext);
        tooltip.add(Text.literal("This appears to be a fuse.").formatted(Formatting.ITALIC));
        tooltip.add(Text.literal("It won't do much by itself.").formatted(Formatting.ITALIC));
    }
}
