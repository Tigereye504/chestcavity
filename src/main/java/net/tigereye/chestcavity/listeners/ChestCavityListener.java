package net.tigereye.chestcavity.listeners;

import java.util.UUID;
import java.util.Map.Entry;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.attribute.EntityAttributes;
import net.tigereye.chestcavity.interfaces.CCStatusEffectInstance;
import net.tigereye.chestcavity.items.ChestCavityOrgan;
import net.tigereye.chestcavity.items.RegisterItems;
//import net.tigereye.chestcavity.mixin.*;
import net.tigereye.chestcavity.items.VanillaOrgans;

public class ChestCavityListener implements InventoryChangedListener {
	private static final int HEARTBLEEDSPEED = 20; //how fast you die from lacking a heart
	private static final int LIVERSPEED = 40; //how often the liver purifes status effects
	private static final int KIDNEYSPEED = 59; //how often the kidneys prevent blood poisoning, avoid clean multiples of LIVERSPEED
	
	PlayerEntity player;
	
	private int hearttimer = 0;
	private int kidneytimer = 0;
	private int livertimer = 0;
	//private float lungtimer = 0;
	private int spleentimer;
	private float lungleftover;

	private float heartScore = 0;
	private static final UUID heartID = UUID.fromString("edb1e124-a951-48bd-b711-782ec1364722");
	private float intestineScore = 0;
	//private static final UUID intestineID = UUID.fromString("3d9d87e8-03aa-4299-936c-1c635d3e0fe4");
	private float kidneyScore = 0;
	//private static final UUID kidneyID = UUID.fromString("37f59b36-37ae-43ff-a3f0-d23b9783666a");
	private float liverScore = 0;
	//private static final UUID liverID = UUID.fromString("e07163d8-f47d-405a-9c3e-2df8da04a797");
	private float lungScore = 0;
	//private static final UUID lungID = UUID.fromString("693af5f8-dfae-4421-a09c-b7f23ee2cf10");
	private float muscleScore = 0;
	private static final UUID muscleID1 = UUID.fromString("bf560396-9855-496e-a942-99824467e1ad");
	private static final UUID muscleID2 = UUID.fromString("979aa156-3f01-45d3-8784-56185eeef96d");
	//private static final UUID muscleID3 = UUID.fromString("282688e6-e8eb-4f63-adbb-8f8684338b5d");
	private float ribScore = 0;
	//private static final UUID ribID1 = UUID.fromString("da39bcc5-878c-4896-be01-2641b7945f65");
	//private static final UUID ribID2 = UUID.fromString("149dbe78-aa10-41a9-959c-3060e08360e4");
	private float spineScore = 0;
	private static final UUID spineID = UUID.fromString("8f56feed-589f-416f-86c5-315765d41f57");
	private float spleenScore = 0;
	private float stomachScore = 0;
	//private static final UUID stomachID = UUID.fromString("584973a2-8233-46e6-8387-c06c6ab50c40");
	
	public ChestCavityListener(PlayerEntity player)
	{
		this.player = player;
	}

	@Override
	public void onInventoryChanged(Inventory sender)
	{
		if(EvaluateChestCavity(sender)){
			UpdateHeart();
			UpdateMuscle();
			UpdateSpine();
		}
	}

	//calculates the 'organ scores' of the player's chest cavity
	//returns true if any of the scores changed
	public boolean EvaluateChestCavity(Inventory inv)
	{
		float oldHeartScore = heartScore;
		//float oldIntestineScore = intestineScore;
		//float oldKidneyScore = kidneyScore;
		//float oldLiverScore = liverScore;
		//float oldLungScore = lungScore;
		float oldMuscleScore = muscleScore;
		//float oldRibScore = ribScore;
		float oldSpineScore = spineScore;
		//float oldSpleenScore = spleenScore;
		//float oldStomachScore = stomachScore;
		heartScore = 0;
		intestineScore = 0;
		kidneyScore = 0;
		liverScore = 0;
		lungScore = 0;
		muscleScore = 0;
		ribScore = 0;
		spineScore = 0;
		spleenScore = 0;
		stomachScore = 0;
		for (int i = 0; i < inv.size(); i++)
		{
			ItemStack slot = inv.getStack(i);
			if (slot != null)
			{
				Item slotitem = slot.getItem();

				if (slotitem.isIn(RegisterItems.ORGANS_HEART)) {
					heartScore += getOrganQuality(slotitem,RegisterItems.ORGANS_HEART)*slot.getCount();
				}
				if (slotitem.isIn(RegisterItems.ORGANS_INTESTINE)) {
					intestineScore += getOrganQuality(slotitem,RegisterItems.ORGANS_INTESTINE)*slot.getCount();
				}
				if (slotitem.isIn(RegisterItems.ORGANS_KIDNEY)) {
					kidneyScore += getOrganQuality(slotitem,RegisterItems.ORGANS_KIDNEY)*slot.getCount();
				}
				if (slotitem.isIn(RegisterItems.ORGANS_LIVER)) {
					liverScore += getOrganQuality(slotitem,RegisterItems.ORGANS_LIVER)*slot.getCount();
				}
				if (slotitem.isIn(RegisterItems.ORGANS_LUNG)) {
					lungScore += getOrganQuality(slotitem,RegisterItems.ORGANS_LUNG)*slot.getCount();
				}
				if (slotitem.isIn(RegisterItems.ORGANS_MUSCLE)) {
					muscleScore += getOrganQuality(slotitem,RegisterItems.ORGANS_MUSCLE)*slot.getCount();
				}
				if (slotitem.isIn(RegisterItems.ORGANS_RIB)) {
					ribScore += getOrganQuality(slotitem,RegisterItems.ORGANS_RIB)*slot.getCount();
				}
				if (slotitem.isIn(RegisterItems.ORGANS_SPINE)) {
					//note: unlike other organs, you cannot get more than 1 point of spinescore per slot
					spineScore += Math.min(getOrganQuality(slotitem,RegisterItems.ORGANS_SPINE)*slot.getCount(),1);
				}
				if (slotitem.isIn(RegisterItems.ORGANS_SPLEEN)) {
					spleenScore += getOrganQuality(slotitem,RegisterItems.ORGANS_SPLEEN)*slot.getCount();
				}
				if (slotitem.isIn(RegisterItems.ORGANS_STOMACH)) {
					stomachScore += getOrganQuality(slotitem,RegisterItems.ORGANS_STOMACH)*slot.getCount();
				}
			}
		}
		
		System.out.println("Heart: " + heartScore + " Intestine: " + intestineScore
				+ " Kidney: " + kidneyScore + " Liver: " + liverScore
				+ " Lung: " + lungScore + " Muscle: " + muscleScore
				+ " Rib: " + ribScore + " Spine: " + spineScore
				+ " Stomach: " + stomachScore + "\n");/**/
		if( //check if anything changed
			oldHeartScore != heartScore
			//|| oldIntestineScore != intestineScore
			//|| oldKidneyScore != kidneyScore
			//|| oldLiverScore != liverScore
			//|| oldLungScore != lungScore
			|| oldMuscleScore != muscleScore
			//|| oldRibScore != ribScore
			|| oldSpineScore != spineScore
			//|| oldSpleenScore != spleenScore 
			//|| oldStomachScore != stomachScore
			)
		{
			return true;
		}
		return false;
	}

	public float applyBoneDefense(float damage){
		float boneScore = ribScore+(spineScore*3);
			return damage*(20/(1+boneScore));
	}
	
	public float applyIntestinesSaturation(float sat){
		return sat*intestineScore/4;
	}
	
	public int applyStomachHunger(int hunger){
		//sadly, in order to get saturation at all we must grant at least half a haunch of food, unless we embrace incompatability
		return Math.max((int)(hunger*stomachScore),1);
	}
	
	//returns how much air we should attempt to lose
	public int applyLungCapacityInWater(){
		float airloss = 2f/Math.max(lungScore,.1f) + lungleftover;
		lungleftover = airloss % 1;
		return (int) airloss;
		

	}

	//returns air gain
	public int applyLungCapacityOnLand(){
		//return ((int)(lungScore*3))-2;
		if (lungScore == 0) {
			player.damage(DamageSource.DROWN, 2.0F);
		}
		return 4;
	}

	public int applySpleenMetabolism(int metatimer){
		spleentimer++;
		if(spleentimer>=2){
			metatimer += spleenScore - 1;
		}
		spleentimer = 0;
		return metatimer;
	}
	public void TickHeart(){
		if (heartScore <= 0)
		{
			hearttimer++;
			if(hearttimer % HEARTBLEEDSPEED == 0){
			player.damage(DamageSource.STARVE, (hearttimer/HEARTBLEEDSPEED));
			}
		}
		else{
			hearttimer = 0;
		}
	}
	
	public void TickKidney(){
		if(kidneyScore < 2)
		{
			kidneytimer++;
			if(kidneytimer >= KIDNEYSPEED){
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, (int)(24*(2-kidneyScore))));
			kidneytimer = 0;
			}
		}
	}

	public void TickLiver(){
		int newDur;
		livertimer++;
		if(livertimer >= LIVERSPEED)
		{
			for(Entry<StatusEffect,StatusEffectInstance> iter : player.getActiveStatusEffects().entrySet()){
				//
				if(!iter.getValue().getEffectType().isBeneficial()){
					newDur = Math.max(0, iter.getValue().getDuration() + ((int)(LIVERSPEED*(.5-(liverScore/2)))));
					((CCStatusEffectInstance)iter.getValue()).CC_setDuration(newDur);
				}
			}
			livertimer = 0;
		}
	}



	private void UpdateHeart() {
		//Update Max Health Modifier
		EntityAttributeInstance att = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
		EntityAttributeModifier mod = new EntityAttributeModifier(heartID, "ChestCavityHeartMaxHP", (heartScore*6)-6, Operation.ADDITION);
		ReplaceAttributeModifier(att,mod);
	}

	private void UpdateMuscle() {
		//Update Damage Modifier
		EntityAttributeInstance att = player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
		EntityAttributeModifier mod = new EntityAttributeModifier(muscleID1, "ChestCavityMuscleAttackDamage", (muscleScore/(64*8))-1, Operation.MULTIPLY_BASE);
		ReplaceAttributeModifier(att,mod);
		//Update Move Speeeeed
		EntityAttributeInstance att2 = player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
		EntityAttributeModifier mod2 = new EntityAttributeModifier(muscleID2, "ChestCavityMuscleMovementSpeed", (muscleScore/(64*8*2))-.5, Operation.MULTIPLY_BASE);
		ReplaceAttributeModifier(att2,mod2);
	}

	private void UpdateSpine() {
		//Update Speed Modifier. No spine? NO MOVING.
		EntityAttributeInstance att = player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
		EntityAttributeModifier mod = new EntityAttributeModifier(spineID, "ChestCavitySpineMovement", Math.min(0,spineScore-1), Operation.MULTIPLY_TOTAL);
		ReplaceAttributeModifier(att,mod);
	}

	private void ReplaceAttributeModifier(EntityAttributeInstance att, EntityAttributeModifier mod)
	{
		//removes any existing mod and replaces it with the updated one.
		//if(att.hasModifier(mod))
		//{
			att.removeModifier(mod);
		//}
		att.addPersistentModifier(mod);
	}
	
	public float getHeartScore(){
		return heartScore;
	}

	public float getLungScore(){
		return lungScore;
	}

	public float getSpineScore(){
		return spineScore;
	}

	private float getOrganQuality(Item slotitem, Tag<Item> tag){
		if(slotitem instanceof ChestCavityOrgan){
			return ((ChestCavityOrgan)slotitem).getOrganQuality(tag);
		}
		else{ //note that default behavior is to assume 0 quality
			return VanillaOrgans.getStrength(slotitem,tag);
		}
	}
}
