package net.tigereye.chestcavity.registration;

import net.tigereye.chestcavity.listeners.*;

public class CCListeners {
    public static void register(){
        LootTableListeners.register();
        OrganUpdateListeners.register();
        OrganTickListeners.register();
        OrganActivationListeners.register();
        OrganAddStatusEffectListeners.register();
        OrganFoodListeners.register();
        OrganFoodEffectListeners.register();
        OrganOnHitListeners.register();
    }
}
