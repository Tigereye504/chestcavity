package net.tigereye.chestcavity.crossmod.wendigoism;

import moriyashiine.wendigoism.common.Wendigoism;
import moriyashiine.wendigoism.common.registry.WDItems;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.items.CCItems;
import net.tigereye.chestcavity.items.OrganBase;
import net.tigereye.chestcavity.items.VanillaOrgans;
import net.tigereye.chestcavity.listeners.OrganTickCallback;

import java.util.HashMap;
import java.util.Map;

public class CCWendigoismItems {
    public static final Identifier ORGANS_CANNIBAL_HEART = new Identifier(ChestCavity.MODID, "organs_cannibal_heart");
    public static final Identifier ORGANS_TETHERED_CANNIBAL_HEART = new Identifier(ChestCavity.MODID, "organs_tethered_cannibal_heart");

    public static final Item CANNIBAL_HEART = new OrganBase()
            .setOrganQuality(CCItems.ORGANS_HEART,.5f)
            .setOrganQuality(ORGANS_CANNIBAL_HEART,1);
    public static final Item TETHERED_CANNIBAL_HEART = new TetheredCannibalHeart()
            .setOrganQuality(CCItems.ORGANS_HEART,.75f)
            .setOrganQuality(ORGANS_TETHERED_CANNIBAL_HEART,1);

    public static void register() {
        registerItem("cannibal_heart", CANNIBAL_HEART);
        registerItem("tethered_cannibal_heart", TETHERED_CANNIBAL_HEART);

        RegistryEntryAddedCallback.event(Registry.ITEM).register(CCWendigoismItems::addWindegoismHeartsToExternalOrgans);
    }

    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, ChestCavity.MODID + ":" + name, item);
    }

    private static void addWindegoismHeartsToExternalOrgans(int i, Identifier identifier, Item item) {
        if(item == WDItems.FLESH){
            Map<Identifier,Float> flesh = new HashMap<>();
            flesh.put(CCItems.ORGANS_MUSCLE,.75f);
            VanillaOrgans.map.put(WDItems.FLESH,flesh);
        }
        if(item == WDItems.CORRUPT_FLESH){
            Map<Identifier,Float> corruptedFlesh = new HashMap<>();
            corruptedFlesh.put(CCItems.ORGANS_MUSCLE,1f);
            VanillaOrgans.map.put(WDItems.CORRUPT_FLESH,corruptedFlesh);
        }
    }

}
