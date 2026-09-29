package net.tigereye.chestcavity.test;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.*;
import net.tigereye.chestcavity.ui.ChestCavityScreen;
import net.tigereye.chestcavity.util.ChestCavityUtil;

/** Runs only with -PwithGameTests -PsmokeTest in a disposable development world. */
@EventBusSubscriber(modid="chestcavity",value=Dist.CLIENT)
public final class ClientSmokeTest {
    private static int ticks,worldTicks,slot;
    private static boolean skippedExperimentalWarning;
    private static volatile float originalDefense;
    private static ItemStack removed=ItemStack.EMPTY;
    @SubscribeEvent public static void tick(TickEvent.ClientTickEvent.Post event){
        if(!Boolean.getBoolean("chestcavity.smokeTest"))return;
        var mc=Minecraft.getInstance();var screen=mc.gui.screen();
        if(screen!=null&&ticks%200==0)ChestCavity.LOGGER.info("CHESTCAVITY_CLIENT_SCREEN: {}",screen.getClass().getSimpleName());
        if(!skippedExperimentalWarning && screen instanceof net.minecraft.client.gui.screens.BackupConfirmScreen && screen.getTitle().equals(net.minecraft.network.chat.Component.translatable("selectWorld.backupQuestion.experimental"))){
            for(var child:screen.children())if(child instanceof net.minecraft.client.gui.components.Button button&&button.active&&button.getMessage().equals(net.minecraft.network.chat.Component.translatable("selectWorld.backupJoinSkipButton"))){skippedExperimentalWarning=true;button.onPress(null);break;}
        }
        if(++ticks>2400){ChestCavity.LOGGER.error("CHESTCAVITY_CLIENT_SMOKE_TIMEOUT");mc.stop();return;}
        if(mc.level==null||mc.player==null)return;
        var server=mc.getSingleplayerServer();var uuid=mc.player.getUUID();
        if(++worldTicks==20)server.execute(()->{
            var player=server.getPlayerList().getPlayer(uuid);
            player.setGameMode(net.minecraft.world.level.GameType.CREATIVE);
            player.setItemInHand(net.minecraft.world.InteractionHand.MAIN_HAND,new ItemStack(CCItems.CHEST_OPENER));
            ((net.tigereye.chestcavity.items.ChestOpener)CCItems.CHEST_OPENER).use(player.level(),player,net.minecraft.world.InteractionHand.MAIN_HAND);
            var cc=((ChestCavityEntity)player).getChestCavityInstance();originalDefense=cc.getOrganScore(CCOrganScores.DEFENSE);
            if(!cc.opened||originalDefense<=0)throw new IllegalStateException("Self surgery failed");
            ChestCavity.LOGGER.info("CHESTCAVITY_CLIENT_CAVITY_OPENED");
        });
        if(worldTicks==60){
            if(!(mc.gui.screen() instanceof ChestCavityScreen cavity)||cavity.getMenu().slots.size()!=63)throw new IllegalStateException("Cavity screen/27 slots missing");
            server.execute(()->{
                var cc=((ChestCavityEntity)server.getPlayerList().getPlayer(uuid)).getChestCavityInstance();slot=-1;
                for(int i=0;i<27;i++){var data=ChestCavityUtil.lookupOrgan(cc.inventory.getItem(i),cc.getChestCavityType());if(data!=null&&data.organScores.getOrDefault(CCOrganScores.DEFENSE,0f)>0){slot=i;break;}}
                if(slot<0)throw new IllegalStateException("Missing rib slot");removed=cc.inventory.removeItem(slot,cc.inventory.getItem(slot).getCount());
            });
        }
        if(worldTicks==100){
            var cc=((ChestCavityEntity)mc.player).getChestCavityInstance();
            if(!cc.opened||cc.getOrganScore(CCOrganScores.DEFENSE)>=originalDefense)throw new IllegalStateException("Organ change not synchronized");
            server.execute(()->((ChestCavityEntity)server.getPlayerList().getPlayer(uuid)).getChestCavityInstance().inventory.setItem(slot,removed));
        }
        if(worldTicks==140){
            var cc=((ChestCavityEntity)mc.player).getChestCavityInstance();
            if(cc.getOrganScore(CCOrganScores.DEFENSE)!=originalDefense)throw new IllegalStateException("Restored organ not synchronized");
            var cavity=(ChestCavityScreen)mc.gui.screen();
            if(ChestCavityUtil.getCompatibilityLevel(cc,cavity.getMenu().slots.get(slot).getItem())<1)throw new IllegalStateException("Donor compatibility not synchronized");
            Screenshot.grab(mc.gameDirectory,"chestcavity-migration-smoke.png",mc.gameRenderer.mainRenderTarget(),1,message->ChestCavity.LOGGER.info("CHESTCAVITY_CLIENT_SCREENSHOT: {}",message.getString()));
        }
        if(worldTicks==180){ChestCavity.LOGGER.info("CHESTCAVITY_CLIENT_SMOKE_PASSED: self surgery, 27 slots, organ replacement, network and donor UUID");mc.stop();}
    }
}
