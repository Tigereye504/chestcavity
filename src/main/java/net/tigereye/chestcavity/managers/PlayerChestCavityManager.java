package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.GameRules;
import net.tigereye.chestcavity.items.Organ;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerChestCavityManager extends HumanChestCavityManager{

    public PlayerChestCavityManager(LivingEntity owner) {
        super(owner);
    }
    public PlayerChestCavityManager(LivingEntity owner, int size) {
        super(owner,size);
    }

    @Override
    public void setOrganCompatibility(){
        for(int i = 0; i < chestCavity.size();i++){
            ItemStack itemStack = chestCavity.getStack(i);
            if(itemStack != null && itemStack.getItem() instanceof Organ){
                CompoundTag tag = new CompoundTag();
                tag.putInt("type", COMPATIBILITY_TYPE_PERSONAL);
                tag.putUuid("owner",owner.getUuid());
                tag.putString("name",owner.getDisplayName().getString());
                itemStack.putSubTag(COMPATIBILITY_TAG.toString(),tag);
            }
        }
    }

    @Override
    public void chestCavityPostMortem(){
        if(!owner.getEntityWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
            dropUnboundOrgans();
        }
        insertWelfareOrgans();
    }

    protected void insertWelfareOrgans(){
        //urgently essential organs are: heart, spine, lung
        if(organScores.getOrDefault(CCOrganScores.HEALTH,0f) == 0){
            forcefullyAddStack(new ItemStack(CCItems.ROTTEN_HEART),4);
        }
        if(organScores.getOrDefault(CCOrganScores.BREATH,0f) == 0){
            forcefullyAddStack(new ItemStack(CCItems.ROTTEN_LUNG),3);
        }
        if(organScores.getOrDefault(CCOrganScores.NERVOUS_SYSTEM,0f) == 0){
            forcefullyAddStack(new ItemStack(CCItems.ROTTEN_SPINE),13);
        }
    }

    protected void forcefullyAddStack(ItemStack stack, int slot){
        if(!chestCavity.canInsert(stack)) {
            if (!chestCavity.canInsert(stack) && owner.getEntityWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY) && owner instanceof PlayerEntity) {
                if (!((PlayerEntity) owner).inventory.insertStack(stack)) {
                    owner.dropStack(chestCavity.removeStack(slot));
                }
            } else {
                owner.dropStack(chestCavity.removeStack(slot));
            }
        }
        chestCavity.addStack(stack);
    }

    @Override
    public List<ItemStack> generateLootDrops(Random random, int looting){
        return new ArrayList<>();
    }
}
