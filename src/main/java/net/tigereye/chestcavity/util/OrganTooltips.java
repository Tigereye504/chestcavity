package net.tigereye.chestcavity.util;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;

import net.minecraft.core.BlockPos;

import net.minecraft.core.Direction;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;

import net.minecraft.resources.Identifier;

import net.minecraft.sounds.SoundEvents;

import net.minecraft.util.Mth;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.equine.Llama;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.entity.projectile.LlamaSpit;

import net.minecraft.world.entity.projectile.ShulkerBullet;

import net.minecraft.world.entity.projectile.hurtingprojectile.DragonFireball;

import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.Blocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;

import net.minecraft.world.phys.Vec3;


import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.CCStatusEffectInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCEnchantments;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;

import java.util.*;

public final class OrganTooltips {
    public static void displayCompatibility(ItemStack itemStack, Level world, List<Component> tooltip, TooltipFlag tooltipContext) {

        CompoundTag tag = net.tigereye.chestcavity.util.ItemData.get(itemStack);
        String textString;
        boolean uuidMatch = false;
        int compatLevel = 0;
        var player=Minecraft.getInstance().player;
        if(player instanceof ChestCavityEntity cce)compatLevel=ChestCavityUtil.getCompatibilityLevel(cce.getChestCavityInstance(),itemStack);
        else compatLevel=-1;

        if(CCEnchantments.getLevel(CCEnchantments.MALPRACTICE,itemStack) > 0){
            textString = "Unsafe to use";
        }
        else if (tag != null && tag.contains(ChestCavity.COMPATIBILITY_TAG.toString())
                && CCEnchantments.getLevel(CCEnchantments.O_NEGATIVE,itemStack) <= 0) {
            tag = tag.getCompoundOrEmpty(ChestCavity.COMPATIBILITY_TAG.toString());
            String name = tag.getStringOr("name","");
            //tooltip.add(Component.literal("OrganOwnerCC: "+tag.read("owner", net.minecraft.core.UUIDUtil.CODEC).orElse(new java.util.UUID(0,0))));
            textString = "Only Compatible With: "+name;
        }
        else{
            textString = "Safe to Use";
        }

        MutableComponent text = Component.empty();
        if(compatLevel > 0) {
            text.withStyle(ChatFormatting.GREEN);
        }
        else if(compatLevel == 0){
            text.withStyle(ChatFormatting.RED);
        }
        else{
            text.withStyle(ChatFormatting.YELLOW);
        }
        text.append(textString);
        tooltip.add(text);
    }

}
