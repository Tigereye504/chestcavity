package net.tigereye.chestcavity.chestcavities.types.generated.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.types.generated.GeneratedChestCavityType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChestCavityAssignmentSerializer {
    //remember: the first identifier is the entity, the second is the chest cavity type
    public Map<Identifier, Identifier> read(Identifier id, ChestCavityAssignmentJsonFormat ccaJson) {

        if (ccaJson.chestcavity == null) {
            throw new JsonSyntaxException("Chest cavity assignment " + id + " must have a chest cavity type");
        }
        if (ccaJson.entities == null) {
            throw new JsonSyntaxException("Chest cavity assignment " + id + " must have a list of entities");
        }
        //bossChestCavity should default to false
        //playerChestCavity should default to false

        Map<Identifier, Identifier> assignments = new HashMap<>();
        Identifier chestcavitytype = new Identifier(ccaJson.chestcavity);
        int i = 0;
        for (JsonElement entry :
                ccaJson.entities) {
            ++i;
            try {
                assignments.put(new Identifier(entry.getAsString()),chestcavitytype);
            } catch (Exception e) {
                ChestCavity.LOGGER.error("Error parsing entry no. " + i + " in " + id.toString() + "'s entity list");
            }
        }
        return assignments;
    }
}