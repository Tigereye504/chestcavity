package net.tigereye.chestcavity.chestcavities.types.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ChestCavityTypeJsonFormat {
    JsonArray defaultChestCavity;
    JsonArray baseOrganScores;
    JsonArray exceptionalOrgans;
    JsonArray forbiddenSlots;
    boolean bossChestCavity;
    boolean playerChestCavity;
}
