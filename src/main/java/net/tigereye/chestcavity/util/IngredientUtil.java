package net.tigereye.chestcavity.util;
import com.google.gson.*;
import net.minecraft.world.item.crafting.Ingredient;
public final class IngredientUtil {
    public static net.minecraft.core.HolderLookup.Provider registries;
    public static Ingredient parse(JsonElement json){
        if(json.isJsonObject()) {var o=json.getAsJsonObject();json=new JsonPrimitive(o.has("item")?o.get("item").getAsString():"#"+o.get("tag").getAsString());}
        return Ingredient.CODEC.parse(net.minecraft.resources.RegistryOps.create(com.mojang.serialization.JsonOps.INSTANCE,registries),json).getOrThrow();
    }
}
