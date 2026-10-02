package net.tigereye.chestcavity.registration;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.tigereye.chestcavity.recipes.*;
public final class CCRecipes {
    public static void register(){
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,Identifier.fromNamespaceAndPath("chestcavity","crafting_salvage"),SalvageRecipe.SERIALIZER);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,Identifier.fromNamespaceAndPath("chestcavity","crafting_special_infuse_venom_gland"),InfuseVenomGland.SERIALIZER);
    }
}
