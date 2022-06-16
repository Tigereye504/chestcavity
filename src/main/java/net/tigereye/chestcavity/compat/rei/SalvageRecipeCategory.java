package net.tigereye.chestcavity.compat.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Label;
import me.shedaniel.rei.api.client.gui.widgets.Slot;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.api.client.gui.Renderer;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.tigereye.chestcavity.registration.CCItems;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SalvageRecipeCategory implements DisplayCategory<SalvageRecipeDisplay> {

    public static final MutableText TITLE = Text.translatable("rei.chestcavity.salvage_recipe");
    public static final EntryStack<ItemStack> ICON = EntryStacks.of(CCItems.IRON_CLEAVER);

    public static final int TEXT_LIGHT = 0xFF404040;
    public static final int TEXT_DARK = 0xFFBBBBBB;

    @Override
    public Renderer getIcon() {
        return ICON;
    }

    @Override
    public Text getTitle() {
        return TITLE;
    }

    @Override
    public CategoryIdentifier<? extends SalvageRecipeDisplay> getCategoryIdentifier() {
        return SalvageRecipeDisplay.IDENTIFIER;
    }

    public Label applyFormat(Label label) {
        return label.noShadow().leftAligned().color(TEXT_LIGHT, TEXT_DARK);
    }

    @Override
    public List<Widget> setupDisplay(SalvageRecipeDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 58, bounds.getCenterY() - 27);
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 60, startPoint.y + 18)));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 95, startPoint.y + 19)));

        int i = 0;
        for (int y = 0; y < 3; y++){
            for (int x = 0; x < 3; x++){
                Slot slot = Widgets.createSlot(new Point(startPoint.x + 1 + x * 18, startPoint.y + 1 + y * 18)).markInput();
                if(display.getInputEntries().size() > i){
                    slot.entries(display.getInputEntries().get(i)).markInput();
                }
                widgets.add(slot);
                i++;
            }
        }
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 95, startPoint.y + 19)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        return widgets;

    }
}
