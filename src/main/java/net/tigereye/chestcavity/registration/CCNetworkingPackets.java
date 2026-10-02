package net.tigereye.chestcavity.registration;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.util.NetworkUtil;
@EventBusSubscriber(modid="chestcavity")
public final class CCNetworkingPackets {
    public static final int ORGAN_DATA=0, UPDATE=1, ACK=2, HOTKEY=3;
    private static final net.minecraftforge.network.SimpleChannel CHANNEL=ChannelBuilder.named(Identifier.fromNamespaceAndPath("chestcavity","main")).networkProtocolVersion(1).simpleChannel();
    public record Packet(int kind, byte[] data) {}
    public static void register() {
        CHANNEL.messageBuilder(Packet.class,0)
            .encoder((packet,buffer) -> { buffer.writeVarInt(packet.kind()); buffer.writeByteArray(packet.data()); })
            .decoder(buffer -> new Packet(buffer.readVarInt(),buffer.readByteArray(1048576)))
            .consumer(CCNetworkingPackets::handle).add();
    }
    private static void handle(Packet packet, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            FriendlyByteBuf buffer=new FriendlyByteBuf(io.netty.buffer.Unpooled.wrappedBuffer(packet.data()));
            try {
                if (context.isClientSide()) {
                    net.tigereye.chestcavity.ClientNetworking.handle(packet.kind(),buffer);
                } else if(context.getSender()!=null) {
                    ChestCavityEntity.of(context.getSender()).ifPresent(entity -> {
                        if(packet.kind()==ACK) NetworkUtil.ReadChestCavityReceivedUpdatePacket(entity.getChestCavityInstance());
                        else if(packet.kind()==HOTKEY) NetworkUtil.ReadChestCavityHotkeyPacket(entity.getChestCavityInstance(),buffer);
                        else throw new IllegalArgumentException("Invalid serverbound chest cavity packet");
                    });
                }
            } finally { buffer.release(); }
        });
        context.setPacketHandled(true);
    }
    public static void sendToPlayer(net.minecraft.server.level.ServerPlayer player,int kind,byte[] data) { CHANNEL.send(new Packet(kind,data),PacketDistributor.PLAYER.with(player)); }
    public static void sendToServer(int kind,byte[] data) { CHANNEL.send(new Packet(kind,data),PacketDistributor.SERVER.noArg()); }
    @SubscribeEvent public static void sync(net.minecraftforge.event.OnDatapackSyncEvent event) {
        for(var player:event.getPlayers()) {
            NetworkUtil.sendOrganDataPacket(player);
            ChestCavityEntity.of(player).ifPresent(c -> NetworkUtil.SendS2CChestCavityUpdatePacket(c.getChestCavityInstance()));
        }
    }
}
