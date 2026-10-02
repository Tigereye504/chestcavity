package net.tigereye.chestcavity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.Minecraft;
import net.tigereye.chestcavity.registration.CCNetworkingPackets;
import net.tigereye.chestcavity.util.NetworkUtil;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
public final class ClientNetworking {
    public static void handle(int kind,FriendlyByteBuf buffer) {
        if(kind==CCNetworkingPackets.ORGAN_DATA) NetworkUtil.readOrganDataPacket(buffer);
        else if(kind==CCNetworkingPackets.UPDATE) ChestCavityEntity.of(Minecraft.getInstance().player).ifPresent(c -> NetworkUtil.ReadChestCavityUpdatePacket(c.getChestCavityInstance(),buffer));
        else throw new IllegalArgumentException("Invalid clientbound chest cavity packet");
    }
}
