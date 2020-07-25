package net.tigereye.chestcavity.listeners;

import java.util.UUID;
import java.util.Map.Entry;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.attribute.EntityAttributes;

import net.tigereye.chestcavity.items.RegisterItems;
//import net.tigereye.chestcavity.mixin.*;

public class ChestCavityListener implements InventoryChangedListener {
	private static final int HEARTBLEEDSPEED = 20; //bigger is slower
	
	PlayerEntity player;
	
	private int hearttimer = 0;
	private int kidneytimer = 0;
	private int livertimer = 0;
	private float lungtimer = 0;
	private int spleentimer;

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
			if (slot == null)
			{
				
			}
			//hearts
			else if (slot.getItem() == RegisterItems.heart)
			{
				heartScore += slot.getCount();
			}
			else if (slot.getItem() == RegisterItems.rottenHeart)
			{
				heartScore += ((float)slot.getCount())*.5;
			}
			//intestines
			else if (slot.getItem() == RegisterItems.intestine)
			{
				intestineScore += slot.getCount();
			}
			else if (slot.getItem() == RegisterItems.rottenIntestine)
			{
				intestineScore += ((float)slot.getCount())*.5;
			}
			//kidneys
			else if (slot.getItem() == RegisterItems.kidney)
			{
				kidneyScore += slot.getCount();
			}
			else if (slot.getItem() == RegisterItems.rottenKidney)
			{
				kidneyScore += ((float)slot.getCount())*.5;
			}
			//livers
			else if (slot.getItem() == RegisterItems.liver)
			{
				liverScore += slot.getCount();
			}
			else if (slot.getItem() == RegisterItems.rottenLiver)
			{
				liverScore += ((float)slot.getCount())*.5;
			}
			//lungs
			else if (slot.getItem() == RegisterItems.lung)
			{
				lungScore += slot.getCount();
			}
			else if (slot.getItem() == RegisterItems.rottenLung)
			{
				lungScore += ((float)slot.getCount())*.5;
			}
			//muscles
			else if (slot.getItem() == RegisterItems.muscle)
			{
				muscleScore += slot.getCount();
			}
			else if (slot.getItem() == Items.BEEF)
			{
				muscleScore += ((float)slot.getCount())*.75;
			}
			else if (slot.getItem() == Items.PORKCHOP)
			{
				muscleScore += ((float)slot.getCount())*.75;
			}
			else if (slot.getItem() == Items.MUTTON)
			{
				muscleScore += ((float)slot.getCount())*.75;
			}
			else if (slot.getItem() == Items.ROTTEN_FLESH)
			{
				muscleScore += ((float)slot.getCount())*.5;
			}
			//ribs
			else if (slot.getItem() == RegisterItems.rib)
			{
				ribScore += slot.getCount();
			}
			else if (slot.getItem() == Items.BONE)
			{
				ribScore += ((float)slot.getCount())/32;
			}
			//spines
			else if (slot.getItem() == RegisterItems.spine)
			{
				spineScore += slot.getCount();
			}
			else if (slot.getItem() == RegisterItems.rottenSpine)
			{
				spineScore += ((float)slot.getCount())*.5;
			}
			else if (slot.getItem().isIn(ItemTags.FENCES))
			{
				spineScore += Math.min(((float)slot.getCount())*.5,1);
			}
			//spleens
			else if (slot.getItem() == RegisterItems.spleen)
			{
				spleenScore += slot.getCount();
			}
			else if (slot.getItem() == RegisterItems.rottenSpleen)
			{
				spleenScore += ((float)slot.getCount())*.5;
			}
			//stomachs
			else if (slot.getItem() == RegisterItems.stomach)
			{
				stomachScore += slot.getCount();
			}
			else if (slot.getItem() == RegisterItems.rottenStomach)
			{
				stomachScore += ((float)slot.getCount())*.5;
			}
		}
		/*
		System.out.println("Heart: " + heartScore + " Intestine: " + intestineScore
				+ " Kidney: " + kidneyScore + " Liver: " + liverScore
				+ " Lung: " + lungScore + " Muscle: " + muscleScore
				+ " Rib: " + ribScore + " Spine: " + spineScore
				+ " Stomach: " + stomachScore + "\n");*/
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
		//TODO: BUG: hunger UI pretends to fill even when gains are set to nothing
		return (int)(hunger*stomachScore);
	}
	//returns how much air we should attempt to lose
	public int applyLungCapacityInWater(){
		lungtimer++; //one tick has passed
		int airloss = 0;
		if(lungtimer >= (lungScore/2))
		{ //if lung capacity has been reached
			lungtimer -= (lungScore/2); //deduct lung capacity from timer
			airloss = 1; //lose some air
			if(lungtimer >= 1)//at least one tick still remains because your lungs suck
			{
				airloss = 2;
			}
		}
		return airloss;
	}

	//returns air gain
	public int applyLungCapacityOnLand(){
		return ((int)(lungScore*3))-2;
	}

	public int applySpleenMetabolism(int metatimer){
		spleentimer++;
		if(spleentimer>=2){
			metatimer += spleenScore - 1;
		}
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
		if (kidneyScore < 1){
			kidneytimer++;
			if(kidneytimer >= 60){
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 60));
			kidneytimer = 0;
			}
		}
		else if (kidneyScore < 2)
		{
			kidneytimer++;
			if(kidneytimer >= 2){
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 1));
			kidneytimer = 0;
			}
		}
	}

	public void TickLiver(){
		int newDur = 0;
		livertimer++;
		if(livertimer >= 2)
		{
			for(Entry<StatusEffect,StatusEffectInstance> iter : player.getActiveStatusEffects().entrySet()){
				if(!iter.getValue().isPermanent()){
					newDur = Math.max(iter.getValue().getDuration() - ((int)((liverScore-1))),0);
					iter.setValue(new StatusEffectInstance(iter.getValue().getEffectType(),newDur,iter.getValue().getAmplifier()));
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
		EntityAttributeModifier mod2 = new EntityAttributeModifier(muscleID2, "ChestCavityMuscleMovementSpeed", (muscleScore/(64*8))-1, Operation.MULTIPLY_BASE);
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
		//System.out.println(att.toString());
		//System.out.println(att.getModifiers().toString());
		if(att.hasModifier(mod));
		{
			//System.out.println("found modifier, removing it");
			att.removeModifier(mod);
		}
		//System.out.println("adding modifier");
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
}
