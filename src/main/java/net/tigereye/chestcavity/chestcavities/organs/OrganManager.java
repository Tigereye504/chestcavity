package net.tigereye.chestcavity.chestcavities.organs;

import com.google.gson.Gson;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.tigereye.chestcavity.ChestCavity;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class OrganManager implements SimpleSynchronousResourceReloadListener {
    private static final String RESOURCE_LOCATION = "organs";
    private static final String NBT_KEY = "organData";
    private final OrganSerializer SERIALIZER = new OrganSerializer();
    public static Map<Identifier, OrganData> GeneratedOrganData = new HashMap<>();

    @Override
    public Identifier getFabricId() {
        return new Identifier(ChestCavity.MODID, RESOURCE_LOCATION);
    }

    @Override
    public void reload(ResourceManager manager) {
        GeneratedOrganData.clear();
        ChestCavity.LOGGER.info("Loading organs.");
        manager.findResources(RESOURCE_LOCATION, path -> path.getPath().endsWith(".json")).forEach((id,resource) -> {
            try(InputStream stream = resource.getInputStream()) {
                Reader reader = new InputStreamReader(stream);
                Pair<Identifier,OrganData> organDataPair = SERIALIZER.read(id,new Gson().fromJson(reader,OrganJsonFormat.class));
                GeneratedOrganData.put(organDataPair.getLeft(),organDataPair.getRight());
            } catch(Exception e) {
                ChestCavity.LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
            }
        });
        ChestCavity.LOGGER.info("Loaded "+ GeneratedOrganData.size()+" organs.");
    }

    public static boolean hasEntry(Item item){
        return GeneratedOrganData.containsKey(Registries.ITEM.getId(item));
    }

    public static OrganData getEntry(Item item){
        return GeneratedOrganData.get(Registries.ITEM.getId(item));
    }

    public static boolean isTrueOrgan(Item item){
        if(hasEntry(item)){
            return !getEntry(item).pseudoOrgan;
        }
        return false;
    }

    public static OrganData readNBTOrganData(ItemStack itemStack) {
        NbtCompound nbt = itemStack.getSubNbt(NBT_KEY);
        if(nbt != null) {
            return readNBTOrganData(nbt);
        }
        return null;
    }

    public static OrganData readNBTOrganData(@NotNull NbtCompound nbt) {
        OrganData organData = new OrganData();
        organData.pseudoOrgan = nbt.getBoolean("pseudoOrgan");
        for (String key:
             nbt.getKeys()) {
            if(!key.equals("pseudoOrgan")){
                organData.organScores.put(new Identifier(key),nbt.getFloat(key));
            }
        }
        return organData;
    }
}
