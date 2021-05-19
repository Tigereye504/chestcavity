package net.tigereye.chestcavity;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerLoginConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.tigereye.chestcavity.chestcavities.organs.OrganManager;
import net.tigereye.chestcavity.chestcavities.types.json.GeneratedChestCavityAssignmentManager;
import net.tigereye.chestcavity.chestcavities.types.json.GeneratedChestCavityTypeManager;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCNetworkingPackets;
import net.tigereye.chestcavity.util.NetworkUtil;

import java.util.Optional;

public class ChestCavityServer implements DedicatedServerModInitializer{

    @Override
    public void onInitializeServer() {
        ServerPlayNetworking.registerGlobalReceiver(CCNetworkingPackets.RECEIVED_UPDATE_PACKET_ID, (server, player, handler, buf, sender) -> {
            Optional<ChestCavityEntity> optional = ChestCavityEntity.of(player);
            optional.ifPresent(chestCavityEntity -> NetworkUtil.ReadChestCavityReceivedUpdatePacket(chestCavityEntity.getChestCavityInstance()));
        });
        ServerPlayNetworking.registerGlobalReceiver(CCNetworkingPackets.HOTKEY_PACKET_ID, (server, player, handler, buf, sender) -> {
            Optional<ChestCavityEntity> optional = ChestCavityEntity.of(player);
            optional.ifPresent(chestCavityEntity -> NetworkUtil.ReadChestCavityHotkeyPacket(chestCavityEntity.getChestCavityInstance(),buf));
        });
    }
}
