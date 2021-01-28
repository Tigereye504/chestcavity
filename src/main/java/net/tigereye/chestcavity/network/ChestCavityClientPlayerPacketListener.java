package net.tigereye.chestcavity.network;

import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;

public interface ChestCavityClientPlayerPacketListener extends ClientPlayPacketListener {

    void onChestCavityUpdate(ChestCavityUpdateS2CPacket packet);
}
