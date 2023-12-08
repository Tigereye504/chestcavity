package net.tigereye.chestcavity.util;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.chestcavities.organs.OrganData;
import net.tigereye.chestcavity.chestcavities.organs.OrganManager;
import net.tigereye.chestcavity.listeners.OrganActivationListeners;
import net.tigereye.chestcavity.registration.CCNetworkingPackets;

import java.util.HashMap;
import java.util.Map;

public class NetworkUtil {
    public static PacketByteBuf WriteChestCavityUpdatePacket(ChestCavityInstance cc){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(cc.opened);
        buf.writeInt(cc.getOrganScores().size());
        cc.getOrganScores().forEach((id, value) -> {
            buf.writeString(id.toString());
            buf.writeFloat(value);
        });
        return buf;
    }

    public static void ReadChestCavityUpdatePacket(ChestCavityInstance cc, PacketByteBuf buf){
        Map<Identifier,Float> organScores = new HashMap<>();
        cc.opened = buf.readBoolean();
        int entries = buf.readInt();
        for(int i = 0; i < entries; i++){
            organScores.put(new Identifier(buf.readString()),buf.readFloat());
        }
        cc.setOrganScores(organScores);
        SendC2SChestCavityReceivedUpdatePacket(cc);
    }

    public static boolean SendS2CChestCavityUpdatePacket(ChestCavityInstance cc){
        cc.updatePacket = NetworkUtil.WriteChestCavityUpdatePacket(cc);
        return SendS2CChestCavityUpdatePacket(cc,cc.updatePacket);
    }
    public static boolean SendS2CChestCavityUpdatePacket(ChestCavityInstance cc, PacketByteBuf buf){
        if((!cc.owner.getWorld().isClient()) && cc.owner instanceof ServerPlayerEntity) {
            ServerPlayerEntity spe = (ServerPlayerEntity)cc.owner;
            if(spe.networkHandler != null) try {
                ServerPlayNetworking.send(spe, CCNetworkingPackets.UPDATE_PACKET_ID, buf);
                return true;
            }
            catch(Exception e){
                return false;
            }
            else{
                return false;
            }
        }
        return false;
    }

    public static void ReadChestCavityReceivedUpdatePacket(ChestCavityInstance cc) {
        cc.updatePacket = null;
    }

    public static boolean SendC2SChestCavityReceivedUpdatePacket(ChestCavityInstance cc){
        ClientPlayNetworking.send(CCNetworkingPackets.RECEIVED_UPDATE_PACKET_ID, PacketByteBufs.empty());
        return SendS2CChestCavityUpdatePacket(cc,cc.updatePacket);
    }

    public static PacketByteBuf WriteChestCavityHotkeyPacket(Identifier organScore) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeIdentifier(organScore);
        return buf;
    }

    public static void ReadChestCavityHotkeyPacket(ChestCavityInstance cc, PacketByteBuf buf) {
        Identifier id = buf.readIdentifier();
        OrganActivationListeners.activate(id,cc);
    }

    public static void SendC2SChestCavityHotkeyPacket(Identifier organScore){
        ClientPlayNetworking.send(CCNetworkingPackets.HOTKEY_PACKET_ID, NetworkUtil.WriteChestCavityHotkeyPacket(organScore));
    }

    public static void sendOrganDataPacket(ServerLoginNetworkHandler serverLoginNetworkHandler, MinecraftServer minecraftServer, PacketSender packetSender, ServerLoginNetworking.LoginSynchronizer loginSynchronizer) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(OrganManager.GeneratedOrganData.size());
        OrganManager.GeneratedOrganData.forEach((id,data) -> {
            buf.writeIdentifier(id);
            buf.writeBoolean(data.pseudoOrgan);
            buf.writeInt(data.organScores.size());
            data.organScores.forEach((ability,score) -> {
                buf.writeIdentifier(ability);
                buf.writeFloat(score);
            });
        });
        packetSender.sendPacket(CCNetworkingPackets.ORGAN_DATA_PACKET_ID,buf);
    }

    public static boolean readOrganDataPacket(PacketByteBuf buf){
        OrganManager.GeneratedOrganData.clear();
        try {
            int organCount = buf.readInt();
            for (int i = 0; i < organCount; i++) {
                Identifier organID = buf.readIdentifier();
                OrganData organData = new OrganData();
                organData.pseudoOrgan = buf.readBoolean();
                int organAbilityCount = buf.readInt();
                for (int j = 0; j < organAbilityCount; j++) {
                    organData.organScores.put(buf.readIdentifier(), buf.readFloat());
                }
                OrganManager.GeneratedOrganData.put(organID, organData);
            }
            ChestCavity.LOGGER.info("loaded "+organCount+" organs from server");
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
}
