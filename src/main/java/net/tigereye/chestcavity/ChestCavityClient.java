package net.tigereye.chestcavity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.tigereye.chestcavity.listeners.KeybindingClientListeners;
import net.tigereye.chestcavity.registration.CCKeybindings;
import net.tigereye.chestcavity.registration.CCNetworkingPackets;
import net.tigereye.chestcavity.ui.ChestCavityScreen;

public class ChestCavityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(ChestCavity.CHEST_CAVITY_SCREEN_HANDLER, ChestCavityScreen::new);

        CCNetworkingPackets.registerClient();
        CCKeybindings.register();
        KeybindingClientListeners.register();
    }
}
