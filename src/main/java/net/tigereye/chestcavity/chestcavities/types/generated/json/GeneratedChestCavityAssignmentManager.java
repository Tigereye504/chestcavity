package net.tigereye.chestcavity.chestcavities.types.generated.json;

import com.google.gson.Gson;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.types.generated.GeneratedChestCavityType;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class GeneratedChestCavityAssignmentManager implements SimpleSynchronousResourceReloadListener {
    private static final String RESOURCE_LOCATION = "entity_assignment";
    private final ChestCavityAssignmentSerializer SERIALIZER = new ChestCavityAssignmentSerializer();
    public static Map<Identifier, Identifier> GeneratedChestCavityAssignments = new HashMap<>();

    @Override
    public Identifier getFabricId() {
        return new Identifier(ChestCavity.MODID, RESOURCE_LOCATION);
    }

    @Override
    public void apply(ResourceManager manager) {
        GeneratedChestCavityAssignments.clear();
        ChestCavity.LOGGER.info("Loading Chest Cavity Assignments");
        for(Identifier id : manager.findResources(RESOURCE_LOCATION, path -> path.endsWith(".json"))) {
            try(InputStream stream = manager.getResource(id).getInputStream()) {
                Reader reader = new InputStreamReader(stream);
                GeneratedChestCavityAssignments.putAll(SERIALIZER.read(id,new Gson().fromJson(reader,ChestCavityAssignmentJsonFormat.class)));
            } catch(Exception e) {
                ChestCavity.LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
            }
        }
    }
}
