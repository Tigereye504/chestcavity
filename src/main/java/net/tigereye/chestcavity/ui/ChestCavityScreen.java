package net.tigereye.chestcavity.ui;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
public final class ChestCavityScreen extends AbstractContainerScreen<ChestCavityScreenHandler> {
    private static final Identifier TEXTURE=Identifier.withDefaultNamespace("textures/gui/container/shulker_box.png");
    public ChestCavityScreen(ChestCavityScreenHandler menu,Inventory inventory,Component title) { super(menu,inventory,title,176,166); }
    @Override public void extractBackground(GuiGraphicsExtractor graphics,int mouseX,int mouseY,float partialTick) { super.extractBackground(graphics,mouseX,mouseY,partialTick); graphics.blit(RenderPipelines.GUI_TEXTURED,TEXTURE,leftPos,topPos,0,0,imageWidth,imageHeight,256,256); }
    @Override public void extractRenderState(GuiGraphicsExtractor graphics,int mouseX,int mouseY,float partialTick) { super.extractRenderState(graphics,mouseX,mouseY,partialTick); extractTooltip(graphics,mouseX,mouseY); }
}
