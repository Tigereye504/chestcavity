package net.tigereye.chestcavity.listeners;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.items.ChestCavityOrgan;
import net.tigereye.chestcavity.items.CC_Items;
import net.tigereye.chestcavity.items.VanillaOrgans;


public class ChestCavityListener implements InventoryChangedListener {
	
	PlayerEntity player;

	private int spleenTimer = 0;
	private float lungLeftover;

	private Map<Identifier,Float> organScores = new HashMap<>();

	
	public ChestCavityListener(PlayerEntity player)
	{
		this.player = player;
	}

	@Override
	public void onInventoryChanged(Inventory sender)
	{
		EvaluateChestCavity(sender);
	}

	//calculates the 'organ scores' of the player's chest cavity
	//returns true if any of the scores changed
	public boolean EvaluateChestCavity(Inventory inv)
	{
		Map<Identifier,Float> oldScores = new HashMap<>(organScores);
		organScores.clear();

		for (int i = 0; i < inv.size(); i++)
		{
			ItemStack slot = inv.getStack(i);
			if (slot != null)
			{
				Item slotitem = slot.getItem();
				if(slotitem instanceof ChestCavityOrgan){
					((ChestCavityOrgan) slotitem).getOrganQualityMap().forEach((key,value) ->
							organScores.put(key,organScores.getOrDefault(key,0f)+(value*slot.getCount())));
				}
				else {
					//check vanilla organs
					if(VanillaOrgans.map.containsKey(slotitem)){
						VanillaOrgans.map.get(slotitem).forEach((key,value) ->
								organScores.put(key,organScores.getOrDefault(key,0f)+(value*slot.getCount())));
					}
				}
			}
		}

		organScores.forEach((key,value) ->
			System.out.print(key.toString()+": "+value+" "));
		System.out.print("\n");

		if(oldScores.equals(organScores))
		{
			OrganUpdateCallback.EVENT.invoker().onOrganUpdate(player, oldScores, organScores);
			return true;
		}
		return false;
	}

	public float getOrganScore(Identifier id) {
		return organScores.getOrDefault(id, 0f);
	}

	public float applyBoneDefense(float damage){
		float boneScore = organScores.getOrDefault(CC_Items.ORGANS_RIB,0f)
							+(organScores.getOrDefault(CC_Items.ORGANS_SPINE,0f)*3);
		return damage*(20/(1+boneScore));
	}
	
	public float applyIntestinesSaturation(float sat){
		return sat*organScores.getOrDefault(CC_Items.ORGANS_INTESTINE,0f)/4;
	}
	
	public int applyStomachHunger(int hunger){
		//sadly, in order to get saturation at all we must grant at least half a haunch of food, unless we embrace incompatability
		return Math.max((int)(hunger*organScores.getOrDefault(CC_Items.ORGANS_STOMACH,0f)),1);
	}
	
	//returns how much air we should attempt to lose
	public int applyLungCapacityInWater(){
		float airloss = 2f/Math.max(organScores.getOrDefault(CC_Items.ORGANS_LUNG,0f),.1f) + lungLeftover;
		lungLeftover = airloss % 1;
		return (int) airloss;
	}

	//returns air gain
	public int applyLungCapacityOnLand(){
		if (organScores.getOrDefault(CC_Items.ORGANS_LUNG,0f) == 0) {
			player.damage(DamageSource.DROWN, 2.0F);
		}
		//return ((int)(lungScore*3))-2;
		return 4; //TODO: why is applyLungCapacityOnLand returning 4?
	}

	public int applySpleenMetabolism(int metatimer){
		spleenTimer++;
		if(spleenTimer >=2){
			metatimer += organScores.getOrDefault(CC_Items.ORGANS_SPLEEN,0f) - 1;
		}
		spleenTimer = 0;
		return metatimer;
	}




}
