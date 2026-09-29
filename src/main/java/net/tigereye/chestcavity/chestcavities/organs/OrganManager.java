package net.tigereye.chestcavity.chestcavities.organs;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.google.gson.Gson;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

import net.tigereye.chestcavity.ChestCavity;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class OrganManager implements ResourceManagerReloadListener {
    private static final String RESOURCE_LOCATION = "organs";
    private static final String NBT_KEY = "organData";
    private final OrganSerializer SERIALIZER = new OrganSerializer();
    public static Map<Identifier, OrganData> GeneratedOrganData = new HashMap<>();


    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        GeneratedOrganData.clear();
        ChestCavity.LOGGER.debug("Loading organs.");
        manager.listResources(RESOURCE_LOCATION, path -> path.getPath().endsWith(".json")).forEach((id,resource) -> {
            try(InputStream stream = resource.open()) {
                Reader reader = new InputStreamReader(stream);
                Pair<Identifier,OrganData> organDataPair = SERIALIZER.read(id,new Gson().fromJson(reader,OrganJsonFormat.class));
                GeneratedOrganData.put(organDataPair.getFirst(),organDataPair.getSecond());
            } catch(Exception e) {
                ChestCavity.LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
            }
        });
        ChestCavity.LOGGER.debug("Loaded "+ GeneratedOrganData.size()+" organs.");
    }

    public static boolean hasEntry(Item item){
        return GeneratedOrganData.containsKey(net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(item));
    }

    public static OrganData getEntry(Item item){
        return GeneratedOrganData.get(net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(item));
    }

    public static boolean isTrueOrgan(Item item){
        if(hasEntry(item)){
            return !getEntry(item).pseudoOrgan;
        }
        return false;
    }

    public static OrganData readNBTOrganData(ItemStack itemStack) {
        CompoundTag nbt = net.tigereye.chestcavity.util.ItemData.get(itemStack).getCompoundOrEmpty(NBT_KEY);
        if(!nbt.isEmpty()) {
            return readNBTOrganData(nbt);
        }
        return null;
    }

    public static OrganData readNBTOrganData(@NotNull CompoundTag nbt) {
        OrganData organData = new OrganData();
        organData.pseudoOrgan = nbt.getBooleanOr("pseudoOrgan",false);
        for (String key:
             nbt.keySet()) {
            if(!key.equals("pseudoOrgan")){
                organData.organScores.put(Identifier.parse(key),nbt.getFloatOr(key,0f));
            }
        }
        return organData;
    }
}
