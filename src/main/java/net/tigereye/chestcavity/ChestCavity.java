package net.tigereye.chestcavity;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.tigereye.chestcavity.config.CCConfig;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.items.*;
import net.tigereye.chestcavity.listeners.LootRegister;
import net.fabricmc.api.ModInitializer;
import net.tigereye.chestcavity.listeners.OrganTickListeners;
import net.tigereye.chestcavity.listeners.OrganUpdateListeners;
import net.tigereye.chestcavity.ui.ChestCavityScreenHandler;

public class ChestCavity implements ModInitializer {
	public static final String MODID = "chestcavity";
	public static final boolean DEBUG_MODE = true;
	public static CCConfig config;

	//public static final ScreenHandlerType<ScreenHandler> CHEST_CAVITY_SCREEN_HANDLER;

	@Override
	public void onInitialize() {
		//Register mod resources
		AutoConfig.register(CCConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(CCConfig.class).getConfig();
		CCItems.register();
		LootRegister.register();
		OrganUpdateListeners.register();
		OrganTickListeners.register();
		VanillaOrgans.init();
		CrossModContent.register();
		//ScreenRegistry.register(CHEST_CAVITY_SCREEN_HANDLER, ChestCavityScreenHandler::new);
	}
}
