package net.tigereye.chestcavity;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
@EventBusSubscriber(modid="chestcavity", value=Dist.CLIENT)
public final class ChestCavityClient {
    @SubscribeEvent public static void setup(net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent event) {
        event.enqueueWork(() -> net.minecraft.client.gui.screens.MenuScreens.register(ChestCavity.CHEST_CAVITY_SCREEN_HANDLER,net.tigereye.chestcavity.ui.ChestCavityScreen::new));
    }
    @SubscribeEvent public static void tooltip(net.minecraftforge.event.entity.player.ItemTooltipEvent event) {
        var data=net.tigereye.chestcavity.util.ChestCavityUtil.lookupOrgan(event.getItemStack(),null);
        if(data!=null && !data.pseudoOrgan && event.getEntity()!=null){
            net.tigereye.chestcavity.util.OrganUtil.displayOrganQuality(data.organScores,event.getToolTip());
            net.tigereye.chestcavity.util.OrganTooltips.displayCompatibility(event.getItemStack(),event.getEntity().level(),event.getToolTip(),event.getFlags());
        }
    }
    @SubscribeEvent public static void keys(net.minecraftforge.client.event.RegisterKeyMappingsEvent event) {
        net.tigereye.chestcavity.registration.CCKeybindings.register(event);
        net.tigereye.chestcavity.listeners.KeybindingClientListeners.register();
    }
}
