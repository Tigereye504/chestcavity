package net.tigereye.chestcavity.registration;

import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;

public class CCNetworkingPackets {
    public static final Identifier UPDATE_PACKET_ID = new Identifier(ChestCavity.MODID,"update");
    public static final Identifier RECEIVED_UPDATE_PACKET_ID = new Identifier(ChestCavity.MODID,"received_update");
}
