package net.tigereye.chestcavity.registration;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.enchantments.MalpracticeCurseEnchantment;
import net.tigereye.chestcavity.enchantments.ONegativeEnchantment;
import net.tigereye.chestcavity.enchantments.SurgicalEnchantment;
import net.tigereye.chestcavity.enchantments.TomophobiaEnchantment;

public class CCEnchantments {
    public static final Enchantment O_NEGATIVE = new ONegativeEnchantment();
    public static final Enchantment SURGICAL = new SurgicalEnchantment();
    public static final Enchantment MALPRACTICE = new MalpracticeCurseEnchantment();
    public static final Enchantment TOMOPHOBIA = new TomophobiaEnchantment();

    public static void register() {
        Registry.register(Registry.ENCHANTMENT, new Identifier(ChestCavity.MODID, "o_negative"), O_NEGATIVE);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ChestCavity.MODID, "surgical"), SURGICAL);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ChestCavity.MODID, "malpractice"), MALPRACTICE);
        Registry.register(Registry.ENCHANTMENT, new Identifier(ChestCavity.MODID, "tomophobia"), TOMOPHOBIA);
    }
}
