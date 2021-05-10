package net.tigereye.chestcavity.chestcavities.organs;

import com.google.gson.Gson;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.Item;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class OrganManager implements SimpleSynchronousResourceReloadListener {
    private static final String RESOURCE_LOCATION = "organs";
    private final OrganSerializer SERIALIZER = new OrganSerializer();
    public static Map<Identifier, OrganData> GeneratedOrganData = new HashMap<>();

    @Override
    public Identifier getFabricId() {
        return new Identifier(ChestCavity.MODID, RESOURCE_LOCATION);
    }

    @Override
    public void apply(ResourceManager manager) {
        GeneratedOrganData.clear();
        ChestCavity.LOGGER.info("Loading organs.");
        for(Identifier id : manager.findResources(RESOURCE_LOCATION, path -> path.endsWith(".json"))) {
            try(InputStream stream = manager.getResource(id).getInputStream()) {
                Reader reader = new InputStreamReader(stream);
                Pair<Identifier,OrganData> organDataPair = SERIALIZER.read(id,new Gson().fromJson(reader,OrganJsonFormat.class));
                GeneratedOrganData.put(organDataPair.getLeft(),organDataPair.getRight());
            } catch(Exception e) {
                ChestCavity.LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
            }
        }
        ChestCavity.LOGGER.info("Loaded "+ GeneratedOrganData.size()+" organs.");
    }

    public static boolean hasEntry(Item item){
        return GeneratedOrganData.containsKey(Registry.ITEM.getId(item));
    }

    public static OrganData getEntry(Item item){
        return GeneratedOrganData.get(Registry.ITEM.getId(item));
    }

    public static boolean isTrueOrgan(Item item){
        if(hasEntry(item)){
            return !getEntry(item).pseudoOrgan;
        }
        return false;
    }
}
