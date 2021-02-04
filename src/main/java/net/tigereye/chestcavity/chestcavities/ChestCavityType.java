package net.tigereye.chestcavity.chestcavities;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

import java.util.List;
import java.util.Map;
import java.util.Random;

public interface ChestCavityType {

    public Map<Identifier,Float> getDefaultOrganScores();
    public float getDefaultOrganScore(Identifier id);
    public ChestCavityInventory getDefaultChestCavity();

    public void fillChestCavityInventory(ChestCavityInventory chestCavity);
    public void loadBaseOrganScores(Map<Identifier, Float> organScores);
    public boolean catchExceptionalOrgan(ItemStack slot,Map<Identifier, Float> organScores);

    public List<ItemStack> generateLootDrops(Random random, int looting);

    public void setOrganCompatibility(ChestCavityInstance instance);
    public float getHeartBleedCap();
    public boolean isOpenable(ChestCavityInstance instance);
    public void onDeath(ChestCavityInstance instance);
}
