package net.tigereye.chestcavity.registration;

import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerLoginConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.util.NetworkUtil;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class CCNetworkingPackets {
    public static final Identifier ORGAN_DATA_PACKET_ID = new Identifier(ChestCavity.MODID,"organ_data");
    public static final Identifier UPDATE_PACKET_ID = new Identifier(ChestCavity.MODID,"update");
    public static final Identifier RECEIVED_UPDATE_PACKET_ID = new Identifier(ChestCavity.MODID,"received_update");

    public static final Identifier HOTKEY_PACKET_ID = new Identifier(ChestCavity.MODID, "hotkey");

    public static void register(){
        ServerLoginConnectionEvents.QUERY_START.register(NetworkUtil::sendOrganDataPacket);
        ServerPlayNetworking.registerGlobalReceiver(CCNetworkingPackets.RECEIVED_UPDATE_PACKET_ID, (server, player, handler, buf, sender) -> {
            Optional<ChestCavityEntity> optional = ChestCavityEntity.of(player);
            optional.ifPresent(chestCavityEntity -> NetworkUtil.ReadChestCavityReceivedUpdatePacket(chestCavityEntity.getChestCavityInstance()));
        });

        ServerPlayNetworking.registerGlobalReceiver(CCNetworkingPackets.HOTKEY_PACKET_ID, (server, player, handler, buf, sender) -> {
            Optional<ChestCavityEntity> optional = ChestCavityEntity.of(player);
            optional.ifPresent(chestCavityEntity -> NetworkUtil.ReadChestCavityHotkeyPacket(chestCavityEntity.getChestCavityInstance(),buf));
        });
        ServerLoginNetworking.registerGlobalReceiver(CCNetworkingPackets.ORGAN_DATA_PACKET_ID,(server, handler, understood, buf, synchronizer, sender) -> {
        });
    }

    public static void registerClient(){
        ClientPlayNetworking.registerGlobalReceiver(CCNetworkingPackets.UPDATE_PACKET_ID, (client, handler, buf, responseSender) -> {
            Optional<ChestCavityEntity> optional = ChestCavityEntity.of(client.cameraEntity);
            optional.ifPresent(chestCavityEntity -> NetworkUtil.ReadChestCavityUpdatePacket(chestCavityEntity.getChestCavityInstance(), buf));
        });
        ClientLoginNetworking.registerGlobalReceiver(CCNetworkingPackets.ORGAN_DATA_PACKET_ID, (client, handler, buf, responseSender) -> {
            NetworkUtil.readOrganDataPacket(buf);
            return CompletableFuture.completedFuture(PacketByteBufs.empty());
        });
    }
}
