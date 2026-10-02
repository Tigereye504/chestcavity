package net.tigereye.chestcavity.registration;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.LivingEntity;
public final class CCEnchantments {
    public static final ResourceKey<Enchantment> O_NEGATIVE=key("o_negative"), SURGICAL=key("surgical"), MALPRACTICE=key("malpractice"), TOMOPHOBIA=key("tomophobia");
    private static ResourceKey<Enchantment> key(String id) { return ResourceKey.create(Registries.ENCHANTMENT,Identifier.fromNamespaceAndPath("chestcavity",id)); }
    public static int getLevel(ResourceKey<Enchantment> key, ItemStack stack) {
        return stack.getEnchantments().entrySet().stream().filter(e -> e.getKey().is(key)).mapToInt(e -> e.getIntValue()).max().orElse(0);
    }
    public static int getEnchantmentLevel(ResourceKey<Enchantment> key, LivingEntity entity) {
        return net.minecraft.world.item.enchantment.EnchantmentHelper.getEnchantmentLevel(entity.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(key),entity);
    }
}
