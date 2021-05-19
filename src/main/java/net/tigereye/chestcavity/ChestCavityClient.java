package net.tigereye.chestcavity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.tigereye.chestcavity.chestcavities.organs.OrganManager;
import net.tigereye.chestcavity.chestcavities.types.json.GeneratedChestCavityAssignmentManager;
import net.tigereye.chestcavity.chestcavities.types.json.GeneratedChestCavityTypeManager;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.listeners.KeybindingClientListeners;
import net.tigereye.chestcavity.registration.CCKeybindings;
import net.tigereye.chestcavity.util.NetworkUtil;
import net.tigereye.chestcavity.registration.CCNetworkingPackets;
import net.tigereye.chestcavity.ui.ChestCavityScreen;

import java.util.Optional;

public class ChestCavityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(ChestCavity.CHEST_CAVITY_SCREEN_HANDLER, ChestCavityScreen::new);

        CCNetworkingPackets.registerClient();
        CCKeybindings.register();
        KeybindingClientListeners.register();
    }
}
