package net.tigereye.chestcavity;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.tigereye.chestcavity.config.CCConfig;
import net.tigereye.chestcavity.registration.*;
import net.tigereye.chestcavity.ui.ChestCavityScreenHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.registries.RegisterEvent;

@Mod("chestcavity")
@Mod.EventBusSubscriber(modid="chestcavity")
public final class ChestCavity {
    public static final String MODID="chestcavity";
    public static final boolean DEBUG_MODE=false;
    public static final org.apache.logging.log4j.Logger LOGGER=org.apache.logging.log4j.LogManager.getLogger();
    public static CCConfig config = new CCConfig();
    public static final Identifier CHEST_CAVITY_SCREEN_ID=Identifier.fromNamespaceAndPath(MODID,"chest_cavity_screen");
    public static final Identifier COMPATIBILITY_TAG=Identifier.fromNamespaceAndPath(MODID,"organ_compatibility");
    public static final MenuType<ChestCavityScreenHandler> CHEST_CAVITY_SCREEN_HANDLER=new MenuType<>(ChestCavityScreenHandler::new,FeatureFlags.DEFAULT_FLAGS);
    public ChestCavity(net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext context) {
        config=CCConfig.load();
        CCListeners.register();
        CCCommands.register();
        CCNetworkingPackets.register();
        net.tigereye.chestcavity.compat.CrossModContent.register();
    }
    @SubscribeEvent public static void register(RegisterEvent event) {
        event.register(net.minecraftforge.registries.ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> helper.register(Identifier.fromNamespaceAndPath(MODID,"organs"),net.tigereye.chestcavity.listeners.LootRegister.CODEC));
        if (event.getRegistryKey().equals(Registries.MOB_EFFECT)) CCStatusEffects.register();
        if (event.getRegistryKey().equals(Registries.ITEM)) CCItems.register(event);
        if (event.getRegistryKey().equals(Registries.RECIPE_SERIALIZER)) CCRecipes.register();
        event.register(Registries.MENU, helper -> helper.register(CHEST_CAVITY_SCREEN_ID,CHEST_CAVITY_SCREEN_HANDLER));
        event.register(Registries.CREATIVE_MODE_TAB, helper -> helper.register(Identifier.fromNamespaceAndPath(MODID,"organs"),CreativeModeTab.builder().title(Component.translatable("itemGroup.chestcavity.organs")).icon(() -> new ItemStack(CCItems.HUMAN_STOMACH)).displayItems((params,output) -> CCItems.ALL.values().forEach(output::accept)).build()));
    }
    @SubscribeEvent public static void reload(net.minecraftforge.event.AddReloadListenerEvent event) {
        net.tigereye.chestcavity.util.IngredientUtil.registries=event.getRegistries();
        event.addListener((net.minecraft.server.packs.resources.ResourceManagerReloadListener) manager -> {
            CCTagOrgans.init();
            new net.tigereye.chestcavity.chestcavities.organs.OrganManager().onResourceManagerReload(manager);
            new net.tigereye.chestcavity.chestcavities.types.json.GeneratedChestCavityTypeManager().onResourceManagerReload(manager);
            new net.tigereye.chestcavity.chestcavities.types.json.GeneratedChestCavityAssignmentManager().onResourceManagerReload(manager);
        });
    }
}
