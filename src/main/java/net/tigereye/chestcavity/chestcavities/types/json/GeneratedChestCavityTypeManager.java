package net.tigereye.chestcavity.chestcavities.types.json;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;

import com.google.gson.Gson;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.types.GeneratedChestCavityType;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class GeneratedChestCavityTypeManager implements ResourceManagerReloadListener {
    private static final String RESOURCE_LOCATION = "types";
    private final ChestCavityTypeSerializer SERIALIZER = new ChestCavityTypeSerializer();
    public static Map<Identifier, GeneratedChestCavityType> GeneratedChestCavityTypes = new HashMap<>();


    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        GeneratedChestCavityTypes.clear();
        ChestCavity.LOGGER.debug("Loading chest cavity types.");
        manager.listResources(RESOURCE_LOCATION, path -> path.getPath().endsWith(".json")).forEach((id,resource) -> {
            try(InputStream stream = resource.open()) {
                Reader reader = new InputStreamReader(stream);
                GeneratedChestCavityTypes.put(id,SERIALIZER.read(id,new Gson().fromJson(reader,ChestCavityTypeJsonFormat.class)));
            } catch(Exception e) {
                ChestCavity.LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
            }
        });
        ChestCavity.LOGGER.debug("Loaded "+GeneratedChestCavityTypes.size()+" chest cavity types.");
    }
}
