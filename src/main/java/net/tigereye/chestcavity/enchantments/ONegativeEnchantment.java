package net.tigereye.chestcavity.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ONegativeEnchantment extends Enchantment{

    public ONegativeEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.VANISHABLE, new EquipmentSlot[]{});
    }


    public int getMinPower(int level) {
        return 50*level;
    }

    public int getMaxPower(int level) {
        return 100*level;
    }

    public int getMaxLevel() {
        return 2;
    }

    public boolean isAcceptableItem(ItemStack stack) {
        return true;
    }

    public boolean isTreasure() {
        return true;
    }

    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    public boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }



}
