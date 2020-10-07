package net.tigereye.chestcavity;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.tigereye.chestcavity.components.CCComponent;
import net.tigereye.chestcavity.components.InventoryComponent;
import net.tigereye.chestcavity.config.CCConfig;
import net.tigereye.chestcavity.crossmod.CrossModContent;
import net.tigereye.chestcavity.items.*;
import net.tigereye.chestcavity.listeners.LootRegister;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.listeners.OrganTickListeners;
import net.tigereye.chestcavity.listeners.OrganUpdateListeners;

public class ChestCavity implements ModInitializer {
	public static final String MODID = "chestcavity";
	public static final boolean DEBUG_MODE = false;
	public static CCConfig config;

	public static final ComponentType<InventoryComponent> INVENTORYCOMPONENT =
		ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("chestcavity:inventorycomponent"), InventoryComponent.class);


	@Override
	public void onInitialize() {
		//Register mod resources
		AutoConfig.register(CCConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(CCConfig.class).getConfig();
		CCItems.register();
		EntityComponentCallback.event(PlayerEntity.class).register((player, components) -> components.put(INVENTORYCOMPONENT, new CCComponent(player)));
		LootRegister.register();
		OrganUpdateListeners.register();
		OrganTickListeners.register();
		VanillaOrgans.init();
		CrossModContent.register();
	}
}
