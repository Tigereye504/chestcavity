package net.tigereye.chestcavity.config;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.tigereye.chestcavity.ChestCavity;

public class CCModMenuIntegration implements ModMenuApi {
    @Override
    public String getModId() {
        return ChestCavity.MODID; // Return your modid here
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(CCConfig.class, parent).get();
    }
}