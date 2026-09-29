package net.tigereye.chestcavity.listeners;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;



import net.tigereye.chestcavity.registration.CCKeybindings;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.NetworkUtil;

public class KeybindingClientListeners {

    public static void register(){
        net.minecraftforge.event.TickEvent.ClientTickEvent.Post.BUS.addListener(event -> {
            var client=net.minecraft.client.Minecraft.getInstance();
            while (CCKeybindings.UTILITY_ABILITIES.consumeClick()) {
                if(client.player != null) {
                    for(Identifier i : CCKeybindings.UTILITY_ABILITY_LIST) {
                        NetworkUtil.SendC2SChestCavityHotkeyPacket(i);
                    }
                }
            }
        });
        net.minecraftforge.event.TickEvent.ClientTickEvent.Post.BUS.addListener(event -> {
            var client=net.minecraft.client.Minecraft.getInstance();
            while (CCKeybindings.ATTACK_ABILITIES.consumeClick()) {
                if(client.player != null) {
                    for(Identifier i : CCKeybindings.ATTACK_ABILITY_LIST) {
                        NetworkUtil.SendC2SChestCavityHotkeyPacket(i);
                    }
                }
            }
        });

        register(CCKeybindings.CREEPY,CCOrganScores.CREEPY);
        register(CCKeybindings.DRAGON_BREATH,CCOrganScores.DRAGON_BREATH);
        register(CCKeybindings.DRAGON_BOMBS,CCOrganScores.DRAGON_BOMBS);
        register(CCKeybindings.FORCEFUL_SPIT,CCOrganScores.FORCEFUL_SPIT);
        register(CCKeybindings.FURNACE_POWERED,CCOrganScores.FURNACE_POWERED);
        register(CCKeybindings.IRON_REPAIR,CCOrganScores.IRON_REPAIR);
        register(CCKeybindings.GHASTLY,CCOrganScores.GHASTLY);
        register(CCKeybindings.GRAZING,CCOrganScores.GRAZING);
        register(CCKeybindings.PYROMANCY,CCOrganScores.PYROMANCY);
        register(CCKeybindings.SHULKER_BULLETS,CCOrganScores.SHULKER_BULLETS);
        register(CCKeybindings.SILK,CCOrganScores.SILK);
    }

    public static void register(KeyMapping keybinding, Identifier id){
        net.minecraftforge.event.TickEvent.ClientTickEvent.Post.BUS.addListener(event -> {
            var client=net.minecraft.client.Minecraft.getInstance();
            while (keybinding.consumeClick()) {
                if(client.player != null) {
                    NetworkUtil.SendC2SChestCavityHotkeyPacket(id);
                }
            }
        });
    }
}
