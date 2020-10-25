package net.tigereye.chestcavity.managers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.items.CCItems;
import net.tigereye.chestcavity.items.ChestCavityOrgan;
import net.tigereye.chestcavity.items.VanillaOrgans;
import net.tigereye.chestcavity.listeners.OrganUpdateCallback;

import java.util.HashMap;
import java.util.Map;

public class SlimeChestCavityManager extends ChestCavityManager{


    public SlimeChestCavityManager(LivingEntity owner) {
        super(owner,1);
    }
    public SlimeChestCavityManager(LivingEntity owner,int size) {
        super(owner,size);
    }


    @Override
    public boolean EvaluateChestCavity() {
        Map<Identifier,Float> oldScores = new HashMap<>(organScores);
        ResetOrganScores();

        for (int i = 0; i < chestCavity.size(); i++)
        {
            ItemStack slot = chestCavity.getStack(i);
            if (slot != null)
            {
                Item slotitem = slot.getItem();
                if(slotitem instanceof ChestCavityOrgan){
                    ((ChestCavityOrgan) slotitem).getOrganQualityMap(slot).forEach((key,value) ->
                            organScores.put(key,organScores.getOrDefault(key,0f)+(value*slot.getCount()/slot.getMaxCount())));
                }
                else if(slotitem == Items.SLIME_BALL){
                    organScores.put(CCItems.ORGANS_HEART, organScores.getOrDefault(CCItems.ORGANS_HEART,0f)+slot.getCount());
                    organScores.put(CCItems.ORGANS_MUSCLE, organScores.getOrDefault(CCItems.ORGANS_MUSCLE,0f)+8*slot.getCount());
                    organScores.put(CCItems.ORGANS_BONE, organScores.getOrDefault(CCItems.ORGANS_BONE,0f)+4*slot.getCount());
                }
                else {
                    //check vanilla organs
                    if(VanillaOrgans.map.containsKey(slotitem)){
                        VanillaOrgans.map.get(slotitem).forEach((key,value) ->
                                organScores.put(key,organScores.getOrDefault(key,0f)+(value*slot.getCount()/slot.getMaxCount())));
                    }
                }

            }
        }
        if(!oldScores.equals(organScores))
        {
            if(ChestCavity.DEBUG_MODE) {
                try {
                    Text name = owner.getName();
                    System.out.println("[Chest Cavity] Displaying " + name.getString() +"'s organ scores:");
                }
                catch(Exception e){
                    Text name = owner.getType().getName();
                    System.out.println("[Chest Cavity] Displaying "+ name.getString() +"'s organ scores:");
                }
                organScores.forEach((key, value) ->
                        System.out.print(key.toString() + ": " + value + " "));
                System.out.print("\n");
            }
            OrganUpdateCallback.EVENT.invoker().onOrganUpdate(owner, oldScores, organScores);
            return true;
        }
        return false;
    }

    @Override
    public void fillChestCavityInventory() {
        chestCavity.setStack(0, new ItemStack(Items.SLIME_BALL, 1));
    }

    protected void ResetOrganScores(){
        organScores.clear();
        organScores.put(CCItems.ORGANS_APPENDIX, 100f);
        organScores.put(CCItems.ORGANS_INTESTINE, 4f);
        organScores.put(CCItems.ORGANS_KIDNEY, 2f);
        organScores.put(CCItems.ORGANS_LIVER, 1f);
        organScores.put(CCItems.ORGANS_SPINE, 1f);
        organScores.put(CCItems.ORGANS_BONE, .75f);
        organScores.put(CCItems.ORGANS_SPLEEN, 1f);
        organScores.put(CCItems.ORGANS_STOMACH, 1f);
        organScores.put(CCItems.ORGANS_LUNG, 2f);
    }
}
