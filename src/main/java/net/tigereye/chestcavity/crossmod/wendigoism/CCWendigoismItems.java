package net.tigereye.chestcavity.crossmod.wendigoism;

import moriyashiine.wendigoism.common.registry.WDItems;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.items.OrganBase;
import net.tigereye.chestcavity.items.VanillaOrgans;

import java.util.HashMap;
import java.util.Map;

public class CCWendigoismItems {

    private static final Identifier WENDIGOISM_FLESH = new Identifier("wendigoism", "flesh");
    private static final Identifier WENDIGOISM_CORRUPT_FLESH = new Identifier("wendigoism", "corrupt_flesh");

    public static final Item CANNIBAL_HEART = new OrganBase()
            .setOrganQuality(CCOrganScores.HEART,.5f)
            .setOrganQuality(CCWendigoismOrganScores.CANNIBAL_HEART,1);
    public static final Item TETHERED_CANNIBAL_HEART = new TetheredCannibalHeart()
            .setOrganQuality(CCOrganScores.HEART,.75f)
            .setOrganQuality(CCWendigoismOrganScores.TETHERED_CANNIBAL_HEART,1);

    public static void register() {
        registerItem("cannibal_heart", CANNIBAL_HEART);
        registerItem("tethered_cannibal_heart", TETHERED_CANNIBAL_HEART);

        RegistryEntryAddedCallback.event(Registry.ITEM).register(CCWendigoismItems::addWindegoismHeartsToExternalOrgans);
    }

    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, ChestCavity.MODID + ":" + name, item);
    }

    private static void addWindegoismHeartsToExternalOrgans(int i, Identifier identifier, Item item) {
        if(identifier == WENDIGOISM_FLESH){
            Map<Identifier,Float> flesh = new HashMap<>();
            flesh.put(CCOrganScores.MUSCLE,1f);
            VanillaOrgans.map.put(WDItems.FLESH,flesh);
        }
        if(identifier == WENDIGOISM_CORRUPT_FLESH){
            Map<Identifier,Float> corruptedFlesh = new HashMap<>();
            corruptedFlesh.put(CCOrganScores.MUSCLE,1f);
            VanillaOrgans.map.put(WDItems.CORRUPT_FLESH,corruptedFlesh);
        }
    }

}
