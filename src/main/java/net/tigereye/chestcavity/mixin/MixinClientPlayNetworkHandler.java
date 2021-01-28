package net.tigereye.chestcavity.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.text.Text;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.network.ChestCavityClientPlayerPacketListener;
import net.tigereye.chestcavity.network.ChestCavityUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler implements ChestCavityClientPlayerPacketListener {

    @Shadow
    private MinecraftClient client;


    @Override
    public void onChestCavityUpdate(ChestCavityUpdateS2CPacket packet) {
        try {
            ChestCavityEntity entity = ((ChestCavityEntity) this.client.player);
            if(entity != null) {
                entity.getChestCavityManager().setOpened(packet.getOpened());
                entity.getChestCavityManager().setOrganScores(packet.getOrganScores());
            }
            else{
                ChestCavity.LOGGER.error("Client entity is missing during update!");
            }
        }
        catch(Exception e){
            ChestCavity.LOGGER.error("Update packet failed! " + e.getMessage());
        }
    }

    @Shadow
    public void onEntitySpawn(EntitySpawnS2CPacket packet) {

    }

    @Shadow
    public void onExperienceOrbSpawn(ExperienceOrbSpawnS2CPacket packet) {

    }

    @Shadow
    public void onMobSpawn(MobSpawnS2CPacket packet) {

    }

    @Shadow
    public void onScoreboardObjectiveUpdate(ScoreboardObjectiveUpdateS2CPacket packet) {

    }

    @Shadow
    public void onPaintingSpawn(PaintingSpawnS2CPacket packet) {

    }

    @Shadow
    public void onPlayerSpawn(PlayerSpawnS2CPacket packet) {

    }

    @Shadow
    public void onEntityAnimation(EntityAnimationS2CPacket packet) {

    }

    @Shadow
    public void onStatistics(StatisticsS2CPacket packet) {

    }

    @Shadow
    public void onUnlockRecipes(UnlockRecipesS2CPacket packet) {

    }

    @Shadow
    public void onBlockDestroyProgress(BlockBreakingProgressS2CPacket packet) {

    }

    @Shadow
    public void onSignEditorOpen(SignEditorOpenS2CPacket packet) {

    }

    @Shadow
    public void onBlockEntityUpdate(BlockEntityUpdateS2CPacket packet) {

    }

    @Shadow
    public void onBlockEvent(BlockEventS2CPacket packet) {

    }

    @Shadow
    public void onBlockUpdate(BlockUpdateS2CPacket packet) {

    }

    @Shadow
    public void onGameMessage(GameMessageS2CPacket packet) {

    }

    @Shadow
    public void onChunkDeltaUpdate(ChunkDeltaUpdateS2CPacket packet) {

    }

    @Shadow
    public void onMapUpdate(MapUpdateS2CPacket packet) {

    }

    @Shadow
    public void onConfirmScreenAction(ConfirmScreenActionS2CPacket packet) {

    }

    @Shadow
    public void onCloseScreen(CloseScreenS2CPacket packet) {

    }

    @Shadow
    public void onInventory(InventoryS2CPacket packet) {

    }

    @Shadow
    public void onOpenHorseScreen(OpenHorseScreenS2CPacket packet) {

    }

    @Shadow
    public void onScreenHandlerPropertyUpdate(ScreenHandlerPropertyUpdateS2CPacket packet) {

    }

    @Shadow
    public void onScreenHandlerSlotUpdate(ScreenHandlerSlotUpdateS2CPacket packet) {

    }

    @Shadow
    public void onCustomPayload(CustomPayloadS2CPacket packet) {

    }

    @Shadow
    public void onDisconnect(DisconnectS2CPacket packet) {

    }

    @Shadow
    public void onEntityStatus(EntityStatusS2CPacket packet) {

    }

    @Shadow
    public void onEntityAttach(EntityAttachS2CPacket packet) {

    }

    @Shadow
    public void onEntityPassengersSet(EntityPassengersSetS2CPacket packet) {

    }

    @Shadow
    public void onExplosion(ExplosionS2CPacket packet) {

    }

    @Shadow
    public void onGameStateChange(GameStateChangeS2CPacket packet) {

    }

    @Shadow
    public void onKeepAlive(KeepAliveS2CPacket packet) {

    }

    @Shadow
    public void onChunkData(ChunkDataS2CPacket packet) {

    }

    @Shadow
    public void onUnloadChunk(UnloadChunkS2CPacket packet) {

    }

    @Shadow
    public void onWorldEvent(WorldEventS2CPacket packet) {

    }

    @Shadow
    public void onGameJoin(GameJoinS2CPacket packet) {

    }

    @Shadow
    public void onEntityUpdate(EntityS2CPacket packet) {

    }

    @Shadow
    public void onPlayerPositionLook(PlayerPositionLookS2CPacket packet) {

    }

    @Shadow
    public void onParticle(ParticleS2CPacket packet) {

    }

    @Shadow
    public void onPlayerAbilities(PlayerAbilitiesS2CPacket packet) {

    }

    @Shadow
    public void onPlayerList(PlayerListS2CPacket packet) {

    }

    @Shadow
    public void onEntitiesDestroy(EntitiesDestroyS2CPacket packet) {

    }

    @Shadow
    public void onRemoveEntityEffect(RemoveEntityStatusEffectS2CPacket packet) {

    }

    @Shadow
    public void onPlayerRespawn(PlayerRespawnS2CPacket packet) {

    }

    @Shadow
    public void onEntitySetHeadYaw(EntitySetHeadYawS2CPacket packet) {

    }

    @Shadow
    public void onHeldItemChange(HeldItemChangeS2CPacket packet) {

    }

    @Shadow
    public void onScoreboardDisplay(ScoreboardDisplayS2CPacket packet) {

    }

    @Shadow
    public void onEntityTrackerUpdate(EntityTrackerUpdateS2CPacket packet) {

    }

    @Shadow
    public void onVelocityUpdate(EntityVelocityUpdateS2CPacket packet) {

    }

    @Shadow
    public void onEquipmentUpdate(EntityEquipmentUpdateS2CPacket packet) {

    }

    @Shadow
    public void onExperienceBarUpdate(ExperienceBarUpdateS2CPacket packet) {

    }

    @Shadow
    public void onHealthUpdate(HealthUpdateS2CPacket packet) {

    }

    @Shadow
    public void onTeam(TeamS2CPacket packet) {

    }

    @Shadow
    public void onScoreboardPlayerUpdate(ScoreboardPlayerUpdateS2CPacket packet) {

    }

    @Shadow
    public void onPlayerSpawnPosition(PlayerSpawnPositionS2CPacket packet) {

    }

    @Shadow
    public void onWorldTimeUpdate(WorldTimeUpdateS2CPacket packet) {

    }

    @Shadow
    public void onPlaySound(PlaySoundS2CPacket packet) {

    }

    @Shadow
    public void onPlaySoundFromEntity(PlaySoundFromEntityS2CPacket packet) {

    }

    @Shadow
    public void onPlaySoundId(PlaySoundIdS2CPacket packet) {

    }

    @Shadow
    public void onItemPickupAnimation(ItemPickupAnimationS2CPacket packet) {

    }

    @Shadow
    public void onEntityPosition(EntityPositionS2CPacket packet) {

    }

    @Shadow
    public void onEntityAttributes(EntityAttributesS2CPacket packet) {

    }

    @Shadow
    public void onEntityPotionEffect(EntityStatusEffectS2CPacket packet) {

    }

    @Shadow
    public void onSynchronizeTags(SynchronizeTagsS2CPacket packet) {

    }

    @Shadow
    public void onCombatEvent(CombatEventS2CPacket packet) {

    }

    @Shadow
    public void onDifficulty(DifficultyS2CPacket packet) {

    }

    @Shadow
    public void onSetCameraEntity(SetCameraEntityS2CPacket packet) {

    }

    @Shadow
    public void onWorldBorder(WorldBorderS2CPacket packet) {

    }

    @Shadow
    public void onTitle(TitleS2CPacket packet) {

    }

    @Shadow
    public void onPlayerListHeader(PlayerListHeaderS2CPacket packet) {

    }

    @Shadow
    public void onResourcePackSend(ResourcePackSendS2CPacket packet) {

    }

    @Shadow
    public void onBossBar(BossBarS2CPacket packet) {

    }

    @Shadow
    public void onCooldownUpdate(CooldownUpdateS2CPacket packet) {

    }

    @Shadow
    public void onVehicleMove(VehicleMoveS2CPacket packet) {

    }

    @Shadow
    public void onAdvancements(AdvancementUpdateS2CPacket packet) {

    }

    @Shadow
    public void onSelectAdvancementTab(SelectAdvancementTabS2CPacket packet) {

    }

    @Shadow
    public void onCraftFailedResponse(CraftFailedResponseS2CPacket packet) {

    }

    @Shadow
    public void onCommandTree(CommandTreeS2CPacket packet) {

    }

    @Shadow
    public void onStopSound(StopSoundS2CPacket packet) {

    }

    @Shadow
    public void onCommandSuggestions(CommandSuggestionsS2CPacket packet) {

    }

    @Shadow
    public void onSynchronizeRecipes(SynchronizeRecipesS2CPacket packet) {

    }

    @Shadow
    public void onLookAt(LookAtS2CPacket packet) {

    }

    @Shadow
    public void onTagQuery(TagQueryResponseS2CPacket packet) {

    }

    @Shadow
    public void onLightUpdate(LightUpdateS2CPacket packet) {

    }

    @Shadow
    public void onOpenWrittenBook(OpenWrittenBookS2CPacket packet) {

    }

    @Shadow
    public void onOpenScreen(OpenScreenS2CPacket packet) {

    }

    @Shadow
    public void onSetTradeOffers(SetTradeOffersS2CPacket packet) {

    }

    @Shadow
    public void onChunkLoadDistance(ChunkLoadDistanceS2CPacket packet) {

    }

    @Shadow
    public void onChunkRenderDistanceCenter(ChunkRenderDistanceCenterS2CPacket packet) {

    }

    @Shadow
    public void onPlayerActionResponse(PlayerActionResponseS2CPacket packet) {

    }

    @Shadow
    public void onDisconnected(Text reason) {

    }

    @Shadow
    public ClientConnection getConnection() {
        return null;
    }
}
