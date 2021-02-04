package net.tigereye.chestcavity;

import com.mojang.blaze3d.systems.RenderSystem;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.config.CCConfig;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.listeners.LootRegister;
import net.fabricmc.api.ModInitializer;
import net.tigereye.chestcavity.listeners.OrganAddStatusEffectListeners;
import net.tigereye.chestcavity.listeners.OrganTickListeners;
import net.tigereye.chestcavity.listeners.OrganUpdateListeners;
import net.tigereye.chestcavity.registration.*;
import net.tigereye.chestcavity.ui.ChestCavityScreenHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChestCavity implements ModInitializer {
	public static final String MODID = "chestcavity";
    public static final boolean DEBUG_MODE = false;
	public static final Logger LOGGER = LogManager.getLogger();
	public static CCConfig config;
	public static final ScreenHandlerType<ChestCavityScreenHandler> CHEST_CAVITY_SCREEN_HANDLER;
	public static final Identifier CHEST_CAVITY_SCREEN_ID = new Identifier(MODID,"chest_cavity_screen");
	public static final Identifier COMPATIBILITY_TAG = new Identifier(MODID,"organ_compatibility");

	//public static final ScreenHandlerType<ScreenHandler> CHEST_CAVITY_SCREEN_HANDLER;

	static{
		CHEST_CAVITY_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(CHEST_CAVITY_SCREEN_ID, ChestCavityScreenHandler::new);
	}
	@Override
	public void onInitialize() {
		//Register mod resources
		AutoConfig.register(CCConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(CCConfig.class).getConfig();
		CCItems.register();
		LootRegister.register();
		CCRecipes.register();
		OrganUpdateListeners.register();
		OrganTickListeners.register();
		OrganAddStatusEffectListeners.register();
		CCStatusEffects.register();
		CCOtherOrgans.init();
		CCCommands.register();
		CCChestCavityTypes.register();
		CrossModContent.register();
	}
}
