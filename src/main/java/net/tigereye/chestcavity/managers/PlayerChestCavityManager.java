package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.GameRules;
import net.tigereye.chestcavity.items.OrganBase;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCOrganScores;

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
            if(itemStack != null && itemStack.getItem() instanceof OrganBase){
                CompoundTag tag = new CompoundTag();
                tag.putInt("type", COMPATIBILITY_TYPE_PERSONAL);
                tag.putUuid("owner",owner.getUuid());
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
        if(organScores.getOrDefault(CCOrganScores.HEART,0f) == 0){
            forcefullyAddStack(new ItemStack(CCItems.ROTTEN_HEART),4);
        }
        if(organScores.getOrDefault(CCOrganScores.LUNG,0f) == 0){
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

}
