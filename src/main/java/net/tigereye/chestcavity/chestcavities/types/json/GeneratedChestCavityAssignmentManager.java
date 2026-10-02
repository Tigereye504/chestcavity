package net.tigereye.chestcavity.chestcavities.types.json;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;

import com.google.gson.Gson;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

import net.tigereye.chestcavity.ChestCavity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class GeneratedChestCavityAssignmentManager implements ResourceManagerReloadListener {
    private static final String RESOURCE_LOCATION = "entity_assignment";
    private final ChestCavityAssignmentSerializer SERIALIZER = new ChestCavityAssignmentSerializer();
    public static Map<Identifier, Identifier> GeneratedChestCavityAssignments = new HashMap<>();


    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        GeneratedChestCavityAssignments.clear();
        ChestCavity.LOGGER.debug("Loading chest cavity assignments.");
        manager.listResources(RESOURCE_LOCATION, path -> path.getPath().endsWith(".json")).forEach((id,resource) -> {
            try(InputStream stream = resource.open()) {
                Reader reader = new InputStreamReader(stream);
                GeneratedChestCavityAssignments.putAll(SERIALIZER.read(id,new Gson().fromJson(reader,ChestCavityAssignmentJsonFormat.class)));
            } catch(Exception e) {
                ChestCavity.LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
            }
        });
        ChestCavity.LOGGER.debug("Loaded "+GeneratedChestCavityAssignments.size()+" chest cavity assignments.");
    }
}
