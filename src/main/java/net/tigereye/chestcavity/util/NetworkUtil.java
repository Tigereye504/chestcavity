package net.tigereye.chestcavity.util;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.chestcavities.organs.OrganData;
import net.tigereye.chestcavity.chestcavities.organs.OrganManager;
import net.tigereye.chestcavity.listeners.OrganActivationListeners;
import net.tigereye.chestcavity.registration.CCNetworkingPackets;
import java.util.HashMap;
public final class NetworkUtil {
    private static byte[] encode(java.util.function.Consumer<FriendlyByteBuf> writer) {
        var buffer=new FriendlyByteBuf(io.netty.buffer.Unpooled.buffer());
        try { writer.accept(buffer); byte[] data=new byte[buffer.readableBytes()]; buffer.readBytes(data); return data; }
        finally { buffer.release(); }
    }
    private static int count(FriendlyByteBuf buffer,int max) { int n=buffer.readVarInt(); if(n<0||n>max) throw new IllegalArgumentException("Invalid chest cavity entry count"); return n; }
    public static void ReadChestCavityUpdatePacket(ChestCavityInstance cc,FriendlyByteBuf buffer) {
        boolean opened=buffer.readBoolean(); cc.compatibility_id=buffer.readUUID();
        var defaults=new HashMap<Identifier,Float>();int d=count(buffer,1024);
        for(int i=0;i<d;i++){var id=buffer.readIdentifier();float value=buffer.readFloat();if(!Float.isFinite(value))throw new IllegalArgumentException("Invalid baseline score");defaults.put(id,value);}
        cc.setClientDefaultScores(defaults);var scores=new HashMap<Identifier,Float>();
        int n=count(buffer,1024); for(int i=0;i<n;i++) { var id=buffer.readIdentifier(); float score=buffer.readFloat(); if(!Float.isFinite(score)) throw new IllegalArgumentException("Invalid organ score"); scores.put(id,score); }
        cc.opened=opened; cc.setOrganScores(scores); SendC2SChestCavityReceivedUpdatePacket(cc);
    }
    public static boolean SendS2CChestCavityUpdatePacket(ChestCavityInstance cc) {
        if(!(cc.owner instanceof ServerPlayer)) return false;
        cc.updatePacket=encode(buffer -> { buffer.writeBoolean(cc.opened); buffer.writeUUID(cc.compatibility_id); var defaults=cc.getChestCavityType().getDefaultOrganScores();buffer.writeVarInt(defaults.size());defaults.forEach((id,value)->{buffer.writeIdentifier(id);buffer.writeFloat(value);}); buffer.writeVarInt(cc.getOrganScores().size()); cc.getOrganScores().forEach((id,value)->{buffer.writeIdentifier(id);buffer.writeFloat(value);}); });
        return SendS2CChestCavityUpdatePacket(cc,cc.updatePacket);
    }
    public static boolean SendS2CChestCavityUpdatePacket(ChestCavityInstance cc,byte[] data) {
        if(cc.owner instanceof ServerPlayer player && player.connection!=null && data!=null) { CCNetworkingPackets.sendToPlayer(player,CCNetworkingPackets.UPDATE,data); return true; } return false;
    }
    public static void ReadChestCavityReceivedUpdatePacket(ChestCavityInstance cc) { cc.updatePacket=null; }
    public static boolean SendC2SChestCavityReceivedUpdatePacket(ChestCavityInstance cc) { CCNetworkingPackets.sendToServer(CCNetworkingPackets.ACK,new byte[0]); return true; }
    public static void ReadChestCavityHotkeyPacket(ChestCavityInstance cc,FriendlyByteBuf buffer) { OrganActivationListeners.activate(buffer.readIdentifier(),cc); }
    public static void SendC2SChestCavityHotkeyPacket(Identifier id) { CCNetworkingPackets.sendToServer(CCNetworkingPackets.HOTKEY,encode(buffer -> buffer.writeIdentifier(id))); }
    public static void sendOrganDataPacket(ServerPlayer player) {
        CCNetworkingPackets.sendToPlayer(player,CCNetworkingPackets.ORGAN_DATA,encode(buffer -> {
            buffer.writeVarInt(OrganManager.GeneratedOrganData.size());
            OrganManager.GeneratedOrganData.forEach((id,data)->{ buffer.writeIdentifier(id);buffer.writeBoolean(data.pseudoOrgan);buffer.writeVarInt(data.organScores.size());data.organScores.forEach((ability,score)->{buffer.writeIdentifier(ability);buffer.writeFloat(score);}); });
        }));
    }
    public static boolean readOrganDataPacket(FriendlyByteBuf buffer) {
        var data=new HashMap<Identifier,OrganData>(); int n=count(buffer,10000);
        for(int i=0;i<n;i++) { var id=buffer.readIdentifier(); var organ=new OrganData(); organ.pseudoOrgan=buffer.readBoolean(); int m=count(buffer,1024);for(int j=0;j<m;j++) organ.organScores.put(buffer.readIdentifier(),buffer.readFloat()); data.put(id,organ); }
        OrganManager.GeneratedOrganData=data; return true;
    }
}
