package net.tigereye.chestcavity.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChestCavityUpdateS2CPacket implements Packet<ClientPlayPacketListener> {
    private boolean opened;
    private int entries;
    private Map<Identifier,Float> organScores;

    public ChestCavityUpdateS2CPacket() {
    }

    public ChestCavityUpdateS2CPacket(boolean opened, Map<Identifier,Float> organScores) {
        this.opened = opened;
        this.entries = organScores.size();
        this.organScores = organScores;
    }

    public void read(PacketByteBuf buf) throws IOException {
        this.opened = buf.readBoolean();
        this.entries = buf.readInt();
        this.organScores = new HashMap<>();
        for(int i = 0; i < entries; i++){
            organScores.put(new Identifier(buf.readString()),buf.readFloat());
        }
    }

    public void write(PacketByteBuf buf) throws IOException {
        buf.writeBoolean(this.opened);
        buf.writeInt(this.entries);
        organScores.forEach((id, value) -> {
            buf.writeString(id.toString());
            buf.writeFloat(value);
        });
    }

    public void apply(ClientPlayPacketListener clientPlayPacketListener) {
        ((ChestCavityClientPlayerPacketListener)clientPlayPacketListener).onChestCavityUpdate(this);
    }

    @Environment(EnvType.CLIENT)
    public boolean getOpened() {
        return this.opened;
    }

    @Environment(EnvType.CLIENT)
    public Map<Identifier, Float> getOrganScores() {
        return this.organScores;
    }
}
