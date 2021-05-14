package net.tigereye.chestcavity.registration;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class CCKeybindings {
    private static String ORGAN_ABILITY_KEY_CATEGORY = "organ_abilities";
    public static KeyBinding UTILITY_ABILITIES;
    public static Identifier UTILITY_ABILITIES_ID = new Identifier(ChestCavity.MODID,"utility_abilities");
    public static List<Identifier> UTILITY_ABILITY_LIST = new ArrayList<>();
    public static KeyBinding ATTACK_ABILITIES;
    public static Identifier ATTACK_ABILITIES_ID = new Identifier(ChestCavity.MODID,"attack_abilities");
    public static List<Identifier> ATTACK_ABILITY_LIST = new ArrayList<>();
    public static KeyBinding CREEPY;
    public static KeyBinding DRAGON_BREATH;
    public static KeyBinding DRAGON_BOMBS;
    public static KeyBinding FORCEFUL_SPIT;
    public static KeyBinding FURNACE_POWERED;
    public static KeyBinding PYROMANCY;
    public static KeyBinding GHASTLY;
    public static KeyBinding GRAZING;
    public static KeyBinding SHULKER_BULLETS;
    public static KeyBinding SILK;

    public static void register(){
        UTILITY_ABILITIES = register(UTILITY_ABILITIES_ID, ORGAN_ABILITY_KEY_CATEGORY,GLFW.GLFW_KEY_V);
        ATTACK_ABILITIES = register(ATTACK_ABILITIES_ID, ORGAN_ABILITY_KEY_CATEGORY,GLFW.GLFW_KEY_R);
        CREEPY = register(CCOrganScores.CREEPY, ORGAN_ABILITY_KEY_CATEGORY,GLFW.GLFW_KEY_KP_DECIMAL,true);
        DRAGON_BREATH = register(CCOrganScores.DRAGON_BREATH, ORGAN_ABILITY_KEY_CATEGORY,GLFW.GLFW_KEY_KP_DECIMAL,true);
        DRAGON_BOMBS = register(CCOrganScores.DRAGON_BOMBS, ORGAN_ABILITY_KEY_CATEGORY,GLFW.GLFW_KEY_KP_DECIMAL,true);
        FORCEFUL_SPIT = register(CCOrganScores.FORCEFUL_SPIT, ORGAN_ABILITY_KEY_CATEGORY,GLFW.GLFW_KEY_KP_DECIMAL,true);
        FURNACE_POWERED = register(CCOrganScores.FURNACE_POWERED, ORGAN_ABILITY_KEY_CATEGORY,GLFW.GLFW_KEY_KP_DECIMAL,false);
        PYROMANCY = register(CCOrganScores.PYROMANCY, ORGAN_ABILITY_KEY_CATEGORY,GLFW.GLFW_KEY_KP_DECIMAL,true);
        GHASTLY = register(CCOrganScores.GHASTLY, ORGAN_ABILITY_KEY_CATEGORY,GLFW.GLFW_KEY_KP_DECIMAL,true);
        GRAZING = register(CCOrganScores.GRAZING, ORGAN_ABILITY_KEY_CATEGORY,GLFW.GLFW_KEY_KP_DECIMAL,false);
        SHULKER_BULLETS = register(CCOrganScores.SHULKER_BULLETS, ORGAN_ABILITY_KEY_CATEGORY,GLFW.GLFW_KEY_KP_DECIMAL,true);
        SILK = register(CCOrganScores.SILK, ORGAN_ABILITY_KEY_CATEGORY,GLFW.GLFW_KEY_KP_DECIMAL,false);
    }

    public static KeyBinding register(Identifier id,String category,int defaultKey){
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key."+id.getNamespace()+"."+id.getPath(), // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                defaultKey, // The keycode of the key
                "category."+id.getNamespace()+"."+category // The translation key of the keybinding's category.
        ));
    }
    public static KeyBinding register(Identifier id,String category,int defaultKey, boolean isAttack){
        if(isAttack){ATTACK_ABILITY_LIST.add(id);}
        else{UTILITY_ABILITY_LIST.add(id);}
        return register(id, category, defaultKey);

    }
}
