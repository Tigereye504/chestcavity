package net.tigereye.chestcavity.chestcavities.types;
import net.minecraft.resources.Identifier;
import java.util.Map;
/** Baseline scores supplied by the server for movement and breathing prediction. */
public final class ClientChestCavityType extends DefaultChestCavityType {
    private final Map<Identifier,Float> defaults;
    public ClientChestCavityType(Map<Identifier,Float> defaults){this.defaults=Map.copyOf(defaults);}
    @Override public Map<Identifier,Float> getDefaultOrganScores(){return defaults;}
    @Override public float getDefaultOrganScore(Identifier id){return defaults.getOrDefault(id,0f);}
}
