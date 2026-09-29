package net.tigereye.chestcavity.registration;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.util.ChestCavityUtil;
@EventBusSubscriber(modid="chestcavity")
public final class CCEntityEvents {
    @SubscribeEvent public static void hurt(net.minecraftforge.event.entity.living.LivingHurtEvent event){
        ChestCavityEntity.of(event.getSource().getEntity()).ifPresent(c->event.setAmount(ChestCavityUtil.onHit(c.getChestCavityInstance(),event.getSource(),event.getEntity(),event.getAmount())));
    }
    @SubscribeEvent public static void damage(net.minecraftforge.event.entity.living.LivingDamageEvent event){
        ChestCavityEntity.of(event.getSource().getEntity()).ifPresent(c->{if(event.getAmount()>0)net.tigereye.chestcavity.listeners.OrganOnHitCallback.EVENT.invoker().onHit(c.getChestCavityInstance().owner,event.getEntity(),c.getChestCavityInstance());});
    }
    @SubscribeEvent public static void mining(net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed event){
        ChestCavityEntity.of(event.getEntity()).ifPresent(c->event.setNewSpeed(ChestCavityUtil.applyNervesToMining(c.getChestCavityInstance(),event.getNewSpeed())));
    }
    @SubscribeEvent public static void clone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event){
        ((ChestCavityEntity)event.getEntity()).getChestCavityInstance().clone(((ChestCavityEntity)event.getOriginal()).getChestCavityInstance());
    }
    @SubscribeEvent public static void dimension(net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent event){
        net.tigereye.chestcavity.util.NetworkUtil.SendS2CChestCavityUpdatePacket(((ChestCavityEntity)event.getEntity()).getChestCavityInstance());
    }
}
