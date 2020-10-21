package net.tigereye.chestcavity;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.tigereye.chestcavity.config.CCConfig;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.items.*;
import net.tigereye.chestcavity.listeners.LootRegister;
import net.fabricmc.api.ModInitializer;
import net.tigereye.chestcavity.listeners.OrganTickListeners;
import net.tigereye.chestcavity.listeners.OrganUpdateListeners;

public class ChestCavity implements ModInitializer {
	public static final String MODID = "chestcavity";
	public static final boolean DEBUG_MODE = true;
	public static CCConfig config;

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
	}
}
