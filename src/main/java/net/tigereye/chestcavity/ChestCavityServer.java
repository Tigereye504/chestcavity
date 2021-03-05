package net.tigereye.chestcavity;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCNetworkingPackets;
import net.tigereye.chestcavity.util.NetworkUtil;

import java.util.Optional;

public class ChestCavityServer implements DedicatedServerModInitializer{

    @Override
    public void onInitializeServer() {
        ServerPlayNetworking.registerGlobalReceiver(CCNetworkingPackets.RECEIVED_UPDATE_PACKET_ID, (server, player, handler, buf, sender) -> {
            Optional<ChestCavityEntity> optional = ChestCavityEntity.of(player);
            optional.ifPresent(chestCavityEntity -> NetworkUtil.ReadChestCavityReceiveUpdatePacket(chestCavityEntity.getChestCavityInstance()));
        });
    }
}
