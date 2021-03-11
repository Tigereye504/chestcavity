package net.tigereye.chestcavity.listeners;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.registration.CCKeybindings;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.NetworkUtil;

public class KeybindingClientListeners {

    public static void register(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CCKeybindings.UTILITY_ABILITIES.wasPressed()) {
                if(client.player != null) {
                    for(Identifier i : CCKeybindings.UTILITY_ABILITY_LIST) {
                        NetworkUtil.SendC2SChestCavityHotkeyPacket(i);
                    }
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CCKeybindings.ATTACK_ABILITIES.wasPressed()) {
                if(client.player != null) {
                    for(Identifier i : CCKeybindings.ATTACK_ABILITY_LIST) {
                        NetworkUtil.SendC2SChestCavityHotkeyPacket(i);
                    }
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CCKeybindings.CREEPY.wasPressed()) {
                if(client.player != null) {
                    NetworkUtil.SendC2SChestCavityHotkeyPacket(CCOrganScores.CREEPY);
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CCKeybindings.DRAGON_BREATH.wasPressed()) {
                if(client.player != null) {
                    NetworkUtil.SendC2SChestCavityHotkeyPacket(CCOrganScores.DRAGON_BREATH);
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CCKeybindings.DRAGON_BOMBS.wasPressed()) {
                if(client.player != null) {
                    NetworkUtil.SendC2SChestCavityHotkeyPacket(CCOrganScores.DRAGON_BOMBS);
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CCKeybindings.FORCEFUL_SPIT.wasPressed()) {
                if(client.player != null) {
                    NetworkUtil.SendC2SChestCavityHotkeyPacket(CCOrganScores.FORCEFUL_SPIT);
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CCKeybindings.PYROMANCY.wasPressed()) {
                if(client.player != null) {
                    NetworkUtil.SendC2SChestCavityHotkeyPacket(CCOrganScores.PYROMANCY);
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CCKeybindings.GHASTLY.wasPressed()) {
                if(client.player != null) {
                    NetworkUtil.SendC2SChestCavityHotkeyPacket(CCOrganScores.GHASTLY);
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CCKeybindings.GRAZING.wasPressed()) {
                if(client.player != null) {
                    NetworkUtil.SendC2SChestCavityHotkeyPacket(CCOrganScores.GRAZING);
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CCKeybindings.SHULKER_BULLETS.wasPressed()) {
                if(client.player != null) {
                    NetworkUtil.SendC2SChestCavityHotkeyPacket(CCOrganScores.SHULKER_BULLETS);
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CCKeybindings.SILK.wasPressed()) {
                if(client.player != null) {
                    NetworkUtil.SendC2SChestCavityHotkeyPacket(CCOrganScores.SILK);
                }
            }
        });
    }

}
