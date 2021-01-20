package net.tigereye.chestcavity.crossmod.anthropophagy;

import moriyashiine.anthropophagy.common.registry.APItems;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCOtherOrgans;

import java.util.HashMap;
import java.util.Map;

public class CCAnthropophagyItems {

    private static final Identifier WENDIGOISM_FLESH = new Identifier("wendigoism", "flesh");
    private static final Identifier WENDIGOISM_CORRUPT_FLESH = new Identifier("wendigoism", "corrupt_flesh");

    public static final Item CANNIBAL_HEART = new CannibalHeart()
            .setOrganQuality(CCOrganScores.HEALTH,.5f)
            .setOrganQuality(CCAnthropophagyOrganScores.CANNIBAL_HEART,1);
    public static final Item TETHERED_CANNIBAL_HEART = new TetheredCannibalHeart()
            .setOrganQuality(CCOrganScores.HEALTH,.75f)
            .setOrganQuality(CCAnthropophagyOrganScores.TETHERED_CANNIBAL_HEART,1);

    public static void register() {
        registerItem("cannibal_heart", CANNIBAL_HEART);
        registerItem("tethered_cannibal_heart", TETHERED_CANNIBAL_HEART);

        RegistryEntryAddedCallback.event(Registry.ITEM).register(CCAnthropophagyItems::addWindegoismHeartsToExternalOrgans);
    }

    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, ChestCavity.MODID + ":" + name, item);
    }

    private static void addWindegoismHeartsToExternalOrgans(int i, Identifier identifier, Item item) {
        if(identifier == WENDIGOISM_FLESH){
            Map<Identifier,Float> flesh = new HashMap<>();
            flesh.put(CCOrganScores.SPEED,1f);
            flesh.put(CCOrganScores.STRENGTH,1f);
            CCOtherOrgans.map.put(APItems.FLESH,flesh);
        }
        if(identifier == WENDIGOISM_CORRUPT_FLESH){
            Map<Identifier,Float> corruptedFlesh = new HashMap<>();
            corruptedFlesh.put(CCOrganScores.SPEED,1f);
            corruptedFlesh.put(CCOrganScores.STRENGTH,1f);
            CCOtherOrgans.map.put(APItems.CORRUPT_FLESH,corruptedFlesh);
        }
    }

}
