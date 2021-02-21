package net.tigereye.chestcavity.registration;

import net.tigereye.chestcavity.listeners.*;

public class CCListeners {
    public static void register(){
        LootRegister.register();
        OrganUpdateListeners.register();
        OrganTickListeners.register();
        OrganActivationListeners.register();
        OrganAddStatusEffectListeners.register();
        OrganFoodListeners.register();
        OrganFoodEffectListeners.register();
    }
}
