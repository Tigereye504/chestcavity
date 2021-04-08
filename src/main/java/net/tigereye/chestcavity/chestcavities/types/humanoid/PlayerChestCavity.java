package net.tigereye.chestcavity.chestcavities.types.humanoid;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.GameRules;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.items.Organ;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerChestCavity extends HumanChestCavity implements ChestCavityType {

    @Override
    public void setOrganCompatibility(ChestCavityInstance cc){
        for(int i = 0; i < cc.inventory.size();i++){
            ItemStack itemStack = cc.inventory.getStack(i);
            if(itemStack != null && itemStack.getItem() instanceof Organ){
                CompoundTag tag = new CompoundTag();
                tag.putUuid("owner",cc.compatibility_id);
                tag.putString("name",cc.owner.getDisplayName().getString());
                itemStack.putSubTag(ChestCavity.COMPATIBILITY_TAG.toString(),tag);
            }
        }
    }

    @Override
    public void onDeath(ChestCavityInstance cc){
        cc.projectileQueue.clear();
        if(cc.connectedCrystal != null) {
            cc.connectedCrystal.setBeamTarget(null);
            cc.connectedCrystal = null;
        }
        if(cc.opened && !ChestCavity.config.KEEP_CHEST_CAVITY) {
            ChestCavityUtil.dropUnboundOrgans(cc);
        }
        insertWelfareOrgans(cc);
    }

    protected void insertWelfareOrgans(ChestCavityInstance cc){
        //urgently essential organs are: heart, spine, lung, and just a touch of strength
        if(cc.getOrganScore(CCOrganScores.HEALTH) == 0){
            forcefullyAddStack(cc, new ItemStack(CCItems.ROTTEN_HEART),4);
        }
        if(cc.getOrganScore(CCOrganScores.BREATH) == 0){
            forcefullyAddStack(cc, new ItemStack(CCItems.ROTTEN_LUNG),3);
        }
        if(cc.getOrganScore(CCOrganScores.NERVOUS_SYSTEM) == 0){
            forcefullyAddStack(cc, new ItemStack(CCItems.ROTTEN_SPINE),13);
        }
        if(cc.getOrganScore(CCOrganScores.STRENGTH) == 0){
            forcefullyAddStack(cc, new ItemStack(Items.ROTTEN_FLESH,16),0);
        }
    }

    protected void forcefullyAddStack(ChestCavityInstance cc, ItemStack stack, int slot){
        if(!cc.inventory.canInsert(stack)) {
            if (!cc.inventory.canInsert(stack) && cc.owner.getEntityWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY) && cc.owner instanceof PlayerEntity) {
                if (!((PlayerEntity) cc.owner).inventory.insertStack(stack)) {
                    cc.owner.dropStack(cc.inventory.removeStack(slot));
                }
            } else {
                cc.owner.dropStack(cc.inventory.removeStack(slot));
            }
        }
        cc.inventory.addStack(stack);
    }

    @Override
    public List<ItemStack> generateLootDrops(Random random, int looting){
        return new ArrayList<>();
    }


}
