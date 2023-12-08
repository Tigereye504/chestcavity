package net.tigereye.chestcavity.chestcavities.types.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.types.GeneratedChestCavityType;

import java.util.*;

public class ChestCavityTypeSerializer {
    public GeneratedChestCavityType read(Identifier id, ChestCavityTypeJsonFormat cctJson) {
        //ChestCavityTypeJsonFormat cctJson = new Gson().fromJson(json, ChestCavityTypeJsonFormat.class);

        if (cctJson.defaultChestCavity == null) {
            throw new JsonSyntaxException("Chest Cavity Types must have a default chest cavity!");
        }

        if (cctJson.exceptionalOrgans == null) cctJson.exceptionalOrgans = new JsonArray();
        if (cctJson.baseOrganScores == null) cctJson.baseOrganScores = new JsonArray();
        if (cctJson.forbiddenSlots == null) cctJson.forbiddenSlots = new JsonArray();
        //bossChestCavity defaults to false
        //playerChestCavity defaults to false
        //dropRateMultiplier defaults to true

        GeneratedChestCavityType cct = new GeneratedChestCavityType();
        cct.setForbiddenSlots(readForbiddenSlotsFromJson(id,cctJson));
        cct.setDefaultChestCavity(readDefaultChestCavityFromJson(id,cctJson,cct.getForbiddenSlots()));
        cct.setBaseOrganScores(readBaseOrganScoresFromJson(id,cctJson));
        cct.setExceptionalOrganList(readExceptionalOrgansFromJson(id,cctJson));
        cct.setDropRateMultiplier(cctJson.dropRateMultiplier);
        cct.setPlayerChestCavity(cctJson.playerChestCavity);
        cct.setBossChestCavity(cctJson.bossChestCavity);

        /*
        Ingredient input = Ingredient.fromJson(recipeJson.ingredient);
        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.result))
                // Validate the inputted item actually exists
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.result));
        ItemStack output = new ItemStack(outputItem, recipeJson.count);
        */


        return cct;
    }

    private ChestCavityInventory readDefaultChestCavityFromJson(Identifier id, ChestCavityTypeJsonFormat cctJson, List<Integer> forbiddenSlots){
        ChestCavityInventory inv = new ChestCavityInventory();
        int i = 0;
        for (JsonElement entry:
                cctJson.defaultChestCavity) {
            ++i;
            try {
                JsonObject obj = entry.getAsJsonObject();
                if (!obj.has("item")) {
                    ChestCavity.LOGGER.error("Missing item component in entry no." + i + " in " + id.toString() + "'s default chest cavity");
                } else if (!obj.has("position")) {
                    ChestCavity.LOGGER.error("Missing position component in entry no. " + i + " in " + id.toString() + "'s default chest cavity");
                } else {
                    Identifier itemID = new Identifier(obj.get("item").getAsString());
                    Optional<Item> itemOptional = Registries.ITEM.getOrEmpty(new Identifier(obj.get("item").getAsString()));
                    if (itemOptional.isPresent()) {
                        Item item = itemOptional.get();
                        ItemStack stack;
                        if (obj.has("count")) {
                            int count = obj.get("count").getAsInt();
                            stack = new ItemStack(item, count);
                        } else {
                            stack = new ItemStack(item, item.getMaxCount());
                        }
                        int pos = obj.get("position").getAsInt();
                        if(pos >= inv.size()) {
                            ChestCavity.LOGGER.error("Position component is out of bounds in entry no. " + i + " in " + id.toString() + "'s default chest cavity");
                        }
                        else if(forbiddenSlots.contains(pos)){
                            ChestCavity.LOGGER.error("Position component is forbidden in entry no. " + i + " in " + id.toString() + "'s default chest cavity");
                        }
                        else{
                            inv.setStack(pos, stack);
                        }
                    } else {
                        ChestCavity.LOGGER.error("Unknown "+itemID.toString()+" in entry no. " + i + " in " + id.toString() + "'s default chest cavity");
                    }

                }
            }
            catch(Exception e){
                ChestCavity.LOGGER.error("Error parsing entry no. " + i + " in " + id.toString() + "'s default chest cavity");
            }
        }
        return inv;
    }

    private Map<Identifier,Float> readBaseOrganScoresFromJson(Identifier id, ChestCavityTypeJsonFormat cctJson){
        return readOrganScoresFromJson(id, cctJson.baseOrganScores);
    }

    private Map<Ingredient, Map<Identifier,Float>> readExceptionalOrgansFromJson(Identifier id, ChestCavityTypeJsonFormat cctJson){
        Map<Ingredient, Map<Identifier,Float>> exceptionalOrgans = new HashMap<>();

        int i = 0;
        for (JsonElement entry:
                cctJson.exceptionalOrgans) {
            ++i;
            try {
                JsonObject obj = entry.getAsJsonObject();
                if (!obj.has("ingredient")) {
                    ChestCavity.LOGGER.error("Missing ingredient component in entry no." + i + " in " + id.toString() + "'s exceptional organs");
                } else if (!obj.has("value")) {
                    ChestCavity.LOGGER.error("Missing value component in entry no. " + i + " in " + id.toString() + "'s exceptional organs");
                } else {
                    Ingredient ingredient = Ingredient.fromJson(obj.get("ingredient"));
                    exceptionalOrgans.put(ingredient,readOrganScoresFromJson(id,obj.get("value").getAsJsonArray()));
                }
            }
            catch(Exception e){
                ChestCavity.LOGGER.error("Error parsing entry no. " + i + " in " + id.toString() + "'s exceptional organs");
            }
        }

        return exceptionalOrgans;
    }

    private Map<Identifier,Float> readOrganScoresFromJson(Identifier id, JsonArray json){
        Map<Identifier,Float> organScores = new HashMap<>();
        for (JsonElement entry:
                json) {
            try {
                JsonObject obj = entry.getAsJsonObject();
                if (!obj.has("id")) {
                    ChestCavity.LOGGER.error("Missing id component in " + id.toString() + "'s organ scores");
                } else if (!obj.has("value")) {
                    ChestCavity.LOGGER.error("Missing value component in " + id.toString() + "'s organ scores");
                } else {
                    Identifier ability = new Identifier(obj.get("id").getAsString());
                    organScores.put(ability,obj.get("value").getAsFloat());
                }
            }
            catch(Exception e){
                ChestCavity.LOGGER.error("Error parsing " + id.toString() + "'s organ scores!");
            }
        }
        return organScores;
    }

    private List<Integer> readForbiddenSlotsFromJson(Identifier id, ChestCavityTypeJsonFormat cctJson){
        List<Integer> list = new ArrayList<>();
        for (JsonElement entry:
                cctJson.forbiddenSlots) {
            try {
                int slot = entry.getAsInt();
                list.add(slot);
            }
            catch(Exception e){
                ChestCavity.LOGGER.error("Error parsing " + id.toString() + "'s organ scores!");
            }
        }
        return list;
    }
}
