package net.tigereye.chestcavity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.util.NetworkUtil;
import net.tigereye.chestcavity.registration.CCNetworkingPackets;
import net.tigereye.chestcavity.ui.ChestCavityScreen;

import java.util.Optional;

public class ChestCavityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(ChestCavity.CHEST_CAVITY_SCREEN_HANDLER, ChestCavityScreen::new);

        ClientPlayNetworking.registerGlobalReceiver(CCNetworkingPackets.UPDATE_PACKET_ID, (client, handler, buf, responseSender) -> {
            Optional<ChestCavityEntity> optional = ChestCavityEntity.of(client.cameraEntity);
            optional.ifPresent(chestCavityEntity -> NetworkUtil.ReadChestCavityUpdatePacket(chestCavityEntity.getChestCavityManager(), buf));
        });
    }
}
