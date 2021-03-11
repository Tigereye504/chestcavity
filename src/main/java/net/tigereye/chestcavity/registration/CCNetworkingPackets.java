package net.tigereye.chestcavity.registration;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.util.NetworkUtil;

import java.util.Optional;

public class CCNetworkingPackets {
    public static final Identifier UPDATE_PACKET_ID = new Identifier(ChestCavity.MODID,"update");
    public static final Identifier RECEIVED_UPDATE_PACKET_ID = new Identifier(ChestCavity.MODID,"received_update");

    public static final Identifier HOTKEY_PACKET_ID = new Identifier(ChestCavity.MODID, "hotkey");

    public static void register(){
        ServerPlayNetworking.registerGlobalReceiver(CCNetworkingPackets.RECEIVED_UPDATE_PACKET_ID, (server, player, handler, buf, sender) -> {
            Optional<ChestCavityEntity> optional = ChestCavityEntity.of(player);
            optional.ifPresent(chestCavityEntity -> NetworkUtil.ReadChestCavityReceiveUpdatePacket(chestCavityEntity.getChestCavityInstance()));
        });

        ServerPlayNetworking.registerGlobalReceiver(CCNetworkingPackets.HOTKEY_PACKET_ID, (server, player, handler, buf, sender) -> {
            Optional<ChestCavityEntity> optional = ChestCavityEntity.of(player);
            optional.ifPresent(chestCavityEntity -> NetworkUtil.ReadChestCavityHotkeyPacket(chestCavityEntity.getChestCavityInstance(),buf));
        });
    }

    public static void registerClient(){
        ClientPlayNetworking.registerGlobalReceiver(CCNetworkingPackets.UPDATE_PACKET_ID, (client, handler, buf, responseSender) -> {
            Optional<ChestCavityEntity> optional = ChestCavityEntity.of(client.cameraEntity);
            optional.ifPresent(chestCavityEntity -> NetworkUtil.ReadChestCavityUpdatePacket(chestCavityEntity.getChestCavityInstance(), buf));
        });
    }
}
