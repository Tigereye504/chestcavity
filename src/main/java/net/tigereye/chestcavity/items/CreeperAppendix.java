package net.tigereye.chestcavity.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.util.OrganUtil;

import java.util.List;

public class CreeperAppendix extends Item {

    public CreeperAppendix() {
        super(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        super.appendTooltip(itemStack,world,tooltip,tooltipContext);
        tooltip.add(Text.translatable("lore.chestcavity.creeper_appendix.line_1").formatted(Formatting.ITALIC));
        tooltip.add(Text.translatable("lore.chestcavity.creeper_appendix.line_2").formatted(Formatting.ITALIC));
    }
}
