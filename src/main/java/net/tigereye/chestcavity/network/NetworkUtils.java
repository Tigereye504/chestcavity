package net.tigereye.chestcavity.network;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.managers.ChestCavityManager;

import java.util.HashMap;
import java.util.Map;

public class NetworkUtils {
    public static PacketByteBuf WriteChestCavityUpdatePacket(ChestCavityManager ccm){
        Map<Identifier,Float> organScores = ccm.getOrganScores();
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(ccm.getOpened());
        buf.writeInt(organScores.size());
        organScores.forEach((id, value) -> {
            buf.writeString(id.toString());
            buf.writeFloat(value);
        });
        return buf;
    }

    public static void ReadChestCavityUpdatePacket(ChestCavityManager ccm, PacketByteBuf buf){
        Map<Identifier,Float> organScores = new HashMap<>();
        ccm.setOpened(buf.readBoolean());
        int entries = buf.readInt();
        for(int i = 0; i < entries; i++){
            organScores.put(new Identifier(buf.readString()),buf.readFloat());
        }
        ccm.setOrganScores(organScores);
    }
}
