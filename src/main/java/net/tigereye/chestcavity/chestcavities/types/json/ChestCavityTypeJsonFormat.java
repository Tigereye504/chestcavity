package net.tigereye.chestcavity.chestcavities.types.json;

import com.google.gson.JsonArray;

public class ChestCavityTypeJsonFormat {
    JsonArray defaultChestCavity;
    JsonArray baseOrganScores;
    JsonArray exceptionalOrgans;
    JsonArray forbiddenSlots;
    boolean bossChestCavity = false;
    boolean playerChestCavity = false;
    float dropRateMultiplier = 1;
}
