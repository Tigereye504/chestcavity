package net.tigereye.chestcavity.registration;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;



import net.tigereye.chestcavity.ChestCavity;


import java.util.ArrayList;
import java.util.List;

public class CCKeybindings {
    private static final KeyMapping.Category ORGAN_ABILITY_KEY_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("chestcavity","organ_abilities"));
    private static net.minecraftforge.client.event.RegisterKeyMappingsEvent event;
    public static KeyMapping UTILITY_ABILITIES;
    public static Identifier UTILITY_ABILITIES_ID = Identifier.fromNamespaceAndPath(ChestCavity.MODID,"utility_abilities");
    public static List<Identifier> UTILITY_ABILITY_LIST = new ArrayList<>();
    public static KeyMapping ATTACK_ABILITIES;
    public static Identifier ATTACK_ABILITIES_ID = Identifier.fromNamespaceAndPath(ChestCavity.MODID,"attack_abilities");
    public static List<Identifier> ATTACK_ABILITY_LIST = new ArrayList<>();
    public static KeyMapping CREEPY;
    public static KeyMapping DRAGON_BREATH;
    public static KeyMapping DRAGON_BOMBS;
    public static KeyMapping FORCEFUL_SPIT;
    public static KeyMapping FURNACE_POWERED;
    public static KeyMapping IRON_REPAIR;
    public static KeyMapping PYROMANCY;
    public static KeyMapping GHASTLY;
    public static KeyMapping GRAZING;
    public static KeyMapping SHULKER_BULLETS;
    public static KeyMapping SILK;

    public static void register(net.minecraftforge.client.event.RegisterKeyMappingsEvent registrationEvent){
        event=registrationEvent;
        UTILITY_ABILITIES = register(UTILITY_ABILITIES_ID, ORGAN_ABILITY_KEY_CATEGORY,InputConstants.KEY_V);
        ATTACK_ABILITIES = register(ATTACK_ABILITIES_ID, ORGAN_ABILITY_KEY_CATEGORY,InputConstants.KEY_R);
        CREEPY = register(CCOrganScores.CREEPY, ORGAN_ABILITY_KEY_CATEGORY,InputConstants.UNKNOWN.getValue(),true);
        DRAGON_BREATH = register(CCOrganScores.DRAGON_BREATH, ORGAN_ABILITY_KEY_CATEGORY,InputConstants.UNKNOWN.getValue(),true);
        DRAGON_BOMBS = register(CCOrganScores.DRAGON_BOMBS, ORGAN_ABILITY_KEY_CATEGORY,InputConstants.UNKNOWN.getValue(),true);
        FORCEFUL_SPIT = register(CCOrganScores.FORCEFUL_SPIT, ORGAN_ABILITY_KEY_CATEGORY,InputConstants.UNKNOWN.getValue(),true);
        FURNACE_POWERED = register(CCOrganScores.FURNACE_POWERED, ORGAN_ABILITY_KEY_CATEGORY,InputConstants.UNKNOWN.getValue(),false);
        IRON_REPAIR = register(CCOrganScores.IRON_REPAIR, ORGAN_ABILITY_KEY_CATEGORY,InputConstants.UNKNOWN.getValue(),false);
        PYROMANCY = register(CCOrganScores.PYROMANCY, ORGAN_ABILITY_KEY_CATEGORY,InputConstants.UNKNOWN.getValue(),true);
        GHASTLY = register(CCOrganScores.GHASTLY, ORGAN_ABILITY_KEY_CATEGORY,InputConstants.UNKNOWN.getValue(),true);
        GRAZING = register(CCOrganScores.GRAZING, ORGAN_ABILITY_KEY_CATEGORY,InputConstants.UNKNOWN.getValue(),false);
        SHULKER_BULLETS = register(CCOrganScores.SHULKER_BULLETS, ORGAN_ABILITY_KEY_CATEGORY,InputConstants.UNKNOWN.getValue(),true);
        SILK = register(CCOrganScores.SILK, ORGAN_ABILITY_KEY_CATEGORY,InputConstants.UNKNOWN.getValue(),false);
    }

    public static KeyMapping register(Identifier id,KeyMapping.Category category,int defaultKey){
        KeyMapping mapping = new KeyMapping("key."+id.getNamespace()+"."+id.getPath(), InputConstants.Type.KEYBOARD, defaultKey, category);
        event.register(mapping);
        return mapping;
    }
    public static KeyMapping register(Identifier id,KeyMapping.Category category,int defaultKey, boolean isAttack){
        if(isAttack){ATTACK_ABILITY_LIST.add(id);}
        else{UTILITY_ABILITY_LIST.add(id);}
        return register(id, category, defaultKey);

    }
}
