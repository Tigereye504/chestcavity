package net.tigereye.chestcavity.test;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.alchemy.*;
import net.minecraft.world.effect.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.registries.RegisterEvent;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.chestcavities.instance.*;
import net.tigereye.chestcavity.chestcavities.organs.OrganManager;
import net.tigereye.chestcavity.chestcavities.types.json.*;
import net.tigereye.chestcavity.registration.*;
import net.tigereye.chestcavity.recipes.*;
import net.tigereye.chestcavity.util.*;
import java.util.List;
@EventBusSubscriber(modid="chestcavity")
public final class MigrationGameTests {
    @SubscribeEvent public static void register(RegisterEvent event){
        event.register(Registries.TEST_FUNCTION,h->{
            h.register(Identifier.parse("chestcavity:content"),MigrationGameTests::content);
            h.register(Identifier.parse("chestcavity:organ_roundtrip"),MigrationGameTests::roundtrip);
            h.register(Identifier.parse("chestcavity:organ_physiology"),MigrationGameTests::physiology);
            h.register(Identifier.parse("chestcavity:salvage_recipe"),MigrationGameTests::salvage);
            h.register(Identifier.parse("chestcavity:venom_infusion"),MigrationGameTests::venom);
            h.register(Identifier.parse("chestcavity:abilities"),MigrationGameTests::abilities);
            h.register(Identifier.parse("chestcavity:effect_metabolism"),MigrationGameTests::effects);
            h.register(Identifier.parse("chestcavity:organ_loot"),MigrationGameTests::loot);
            h.register(Identifier.parse("chestcavity:food_digestion"),MigrationGameTests::food);
        });
    }
    private static Cow cow(GameTestHelper h){return new Cow(EntityTypes.COW,h.getLevel());}
    private static ChestCavityInstance cavity(Cow c){return ((ChestCavityEntity)c).getChestCavityInstance();}
    private static void content(GameTestHelper h){
        h.assertValueEqual(CCItems.ALL.size(),182,"Registered item count");
        h.assertValueEqual(OrganManager.GeneratedOrganData.size(),144,"Organ data count");
        h.assertValueEqual(GeneratedChestCavityTypeManager.GeneratedChestCavityTypes.size(),44,"Cavity type count");
        h.assertValueEqual(GeneratedChestCavityAssignmentManager.GeneratedChestCavityAssignments.size(),274,"Entity assignment count");
        var recipes=h.getLevel().getServer().getRecipeManager().getRecipes();
        h.assertValueEqual((int)recipes.stream().filter(r->r.id().identifier().getNamespace().equals("chestcavity")).count(),123,"Recipe count");
        for(var key:List.of(CCEnchantments.MALPRACTICE,CCEnchantments.O_NEGATIVE,CCEnchantments.SURGICAL,CCEnchantments.TOMOPHOBIA))h.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(key);
        for(var type:GeneratedChestCavityTypeManager.GeneratedChestCavityTypes.values())h.assertValueEqual(type.getDefaultChestCavity().getContainerSize(),27,"Cavity template slots");
        h.succeed();
    }
    private static void roundtrip(GameTestHelper h){
        var owner=cow(h);var cc=cavity(owner);ChestCavityUtil.openChestCavity(cc);
        h.assertTrue(cc.getOrganScore(CCOrganScores.HEALTH)>0,"Cow must have working heart");
        cc.heartBleedTimer=9;cc.metabolismRemainder=.25f;cc.photosynthesisProgress=123;
        CompoundTag tag=new CompoundTag();cc.toTag(tag);
        var other=cavity(cow(h));other.fromTag(tag,other.owner);
        h.assertTrue(other.opened,"Lost opened state");h.assertTrue(other.compatibility_id.equals(cc.compatibility_id),"Lost donor UUID");
        h.assertValueEqual(other.photosynthesisProgress,123,"Lost photosynthesis");
        h.assertValueEqual(other.heartBleedTimer,9,"Lost heart timer");
        h.assertTrue(other.getOrganScores().equals(cc.getOrganScores()),"Lost organ scores");
        for(int i=0;i<27;i++)h.assertTrue(ItemStack.matches(cc.inventory.getItem(i),other.inventory.getItem(i)),"Lost organ in slot "+i);
        var fresh=cavity(cow(h));ChestCavityUtil.openChestCavity(fresh);
        var organ=cc.inventory.getItem(0);int before=fresh.inventory.getItem(0).getCount();organ.shrink(1);
        h.assertValueEqual(fresh.inventory.getItem(0).getCount(),before,"Shared mutable default organs");h.succeed();
    }
    private static void physiology(GameTestHelper h){
        var cc=cavity(cow(h));ChestCavityUtil.openChestCavity(cc);
        int slot=-1;for(int i=0;i<27;i++){var data=ChestCavityUtil.lookupOrgan(cc.inventory.getItem(i),cc.getChestCavityType());if(data!=null&&data.organScores.getOrDefault(CCOrganScores.HEALTH,0f)>0){slot=i;break;}}
        h.assertTrue(slot>=0,"No heart slot");var heart=cc.inventory.removeItem(slot,1);
        h.assertTrue(cc.getOrganScore(CCOrganScores.HEALTH)<=0,"Removing heart must recalculate scores");
        ChestCavityUtil.organUpdate(cc);float health=cc.owner.getHealth();
        cc.heartBleedTimer=1;int previousRate=net.tigereye.chestcavity.ChestCavity.config.HEARTBLEED_RATE;
        try { net.tigereye.chestcavity.ChestCavity.config.HEARTBLEED_RATE=1;net.tigereye.chestcavity.listeners.OrganTickListeners.TickHealth(cc.owner,cc); } finally {net.tigereye.chestcavity.ChestCavity.config.HEARTBLEED_RATE=previousRate;}
        h.assertTrue(cc.owner.getHealth()<health,"Heart failure must damage owner");
        cc.inventory.setItem(slot,heart);h.assertTrue(cc.getOrganScore(CCOrganScores.HEALTH)>0,"Heart replacement must restore function");h.succeed();
    }
    private static void salvage(GameTestHelper h){
        var recipe=h.getLevel().getServer().getRecipeManager().getRecipes().stream().filter(r->r.id().identifier().toString().equals("chestcavity:salvage_butchered_meat")).map(r->(SalvageRecipe)r.value()).findFirst().orElseThrow();
        ItemStack muscle=new ItemStack(CCItems.ANIMAL_MUSCLE);
        var input=CraftingInput.of(2,2,List.of(muscle.copy(),muscle.copy(),muscle.copy(),muscle.copy()));
        h.assertTrue(recipe.matches(input,h.getLevel()),"Four muscle slots should salvage");var output=recipe.assemble(input);
        h.assertTrue(output.is(CCItems.RAW_BUTCHERED_MEAT),"Incorrect salvage output");h.assertValueEqual(output.getCount(),1,"Salvage amount");
        h.assertTrue(!recipe.matches(CraftingInput.of(1,1,List.of(muscle)),h.getLevel()),"Partial salvage must fail");h.succeed();
    }
    private static void venom(GameTestHelper h){
        var gland=new ItemStack(CCItems.VENOM_GLAND);var potion=PotionContents.createItemStack(Items.POTION,Potions.POISON);
        var recipe=new InfuseVenomGland();var input=CraftingInput.of(2,1,List.of(gland,potion));var result=recipe.assemble(input);
        h.assertTrue(!result.isEmpty(),"Venom infusion failed");
        var before=OrganUtil.getStatusEffects(potion);var after=OrganUtil.getStatusEffects(result);
        h.assertValueEqual(after.size(),before.size(),"Missing potion effects");h.assertValueEqual(after.getFirst().getDuration(),before.getFirst().getDuration()/4,"Venom duration");
        h.assertTrue(OrganUtil.getStatusEffects(gland).isEmpty(),"Recipe mutated input");
        h.assertTrue(!recipe.matches(CraftingInput.of(3,1,List.of(gland,potion,new ItemStack(Items.DIRT))),h.getLevel()),"Extra item should invalidate infusion");h.succeed();
    }
    private static void abilities(GameTestHelper h){
        var owner=cow(h);h.assertTrue(FuelUtil.burnDuration(owner,new ItemStack(Items.COAL))>0,"Coal furnace fuel");
        h.assertValueEqual(FuelUtil.burnDuration(owner,new ItemStack(Items.STONE)),0,"Nonfuel item");
        OrganUtil.spawnSpit(owner);OrganUtil.spawnPyromancyFireball(owner);OrganUtil.spawnDragonBomb(owner);OrganUtil.spawnGhastlyFireball(owner);
        h.succeed();
    }

    private static void effects(GameTestHelper h){
        var owner=cow(h);var cc=cavity(owner);ChestCavityUtil.openChestCavity(cc);
        cc.getOrganScores().put(CCOrganScores.DETOXIFICATION,0f);
        var poison=new MobEffectInstance(MobEffects.POISON,100);
        owner.addEffect(poison);h.assertTrue(owner.hasEffect(MobEffects.POISON),"Poison must apply");
        h.assertTrue(owner.getEffect(MobEffects.POISON).getDuration()>100,"Missing liver must extend poison duration");
        cc.getOrganScores().put(CCOrganScores.BUFF_PURGING,2f);
        owner.addEffect(new MobEffectInstance(MobEffects.SPEED,100));
        h.assertTrue(owner.getEffect(MobEffects.SPEED).getDuration()<100,"Buff purging must shorten beneficial effects");h.succeed();
    }
    private static void food(GameTestHelper h){
        var player=h.makeMockServerPlayerInLevel();var cc=((ChestCavityEntity)player).getChestCavityInstance();
        ChestCavityUtil.openChestCavity(cc);player.getFoodData().setFoodLevel(0);player.getFoodData().setSaturation(0);
        cc.getOrganScores().put(CCOrganScores.DIGESTION,2f);cc.getOrganScores().put(CCOrganScores.NUTRITION,4f);
        var bread=new ItemStack(Items.BREAD);bread.get(net.minecraft.core.component.DataComponents.FOOD).onConsume(h.getLevel(),player,bread,net.minecraft.world.item.component.Consumables.DEFAULT_FOOD);
        h.assertValueEqual(player.getFoodData().getFoodLevel(),10,"Double digestion must double bread nutrition");
        cc.getOrganScores().put(CCOrganScores.ROTGUT,1f);
        new net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER,100),1f).apply(h.getLevel(),new ItemStack(Items.ROTTEN_FLESH),player);
        h.assertTrue(!player.hasEffect(MobEffects.HUNGER),"Rotgut must filter rotten-food hunger");h.succeed();
    }
    private static void loot(GameTestHelper h){
        var player=h.makeMockServerPlayerInLevel();var owner=cow(h);
        player.setItemInHand(net.minecraft.world.InteractionHand.MAIN_HAND,new ItemStack(CCItems.IRON_CLEAVER));
        player.getMainHandItem().enchant(h.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(CCEnchantments.SURGICAL),3);
        h.assertTrue(player.getMainHandItem().is(CCTags.BUTCHERING_TOOL),"Cleaver must have butchering tag");
        var table=h.getLevel().getServer().reloadableRegistries().getLootTable(net.minecraft.resources.ResourceKey.create(Registries.LOOT_TABLE,Identifier.parse("minecraft:entities/cow")));
        var params=new net.minecraft.world.level.storage.loot.LootParams.Builder(h.getLevel())
            .withParameter(net.minecraft.world.level.storage.loot.parameters.LootContextParams.THIS_ENTITY,owner)
            .withParameter(net.minecraft.world.level.storage.loot.parameters.LootContextParams.ORIGIN,owner.position())
            .withParameter(net.minecraft.world.level.storage.loot.parameters.LootContextParams.DAMAGE_SOURCE,owner.damageSources().playerAttack(player))
            .withParameter(net.minecraft.world.level.storage.loot.parameters.LootContextParams.ATTACKING_ENTITY,player)
            .withParameter(net.minecraft.world.level.storage.loot.parameters.LootContextParams.LAST_DAMAGE_PLAYER,player)
            .create(net.minecraft.world.level.storage.loot.parameters.LootContextParamSets.ENTITY);
        var drops=table.getRandomItems(params,123L);
        h.assertTrue(drops.stream().anyMatch(stack->net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem()).getNamespace().equals("chestcavity")),"Forge loot modifier must add butchered loot");
        ChestCavityUtil.openChestCavity(cavity(owner));
        var after=table.getRandomItems(params,123L);
        h.assertTrue(after.stream().noneMatch(stack->OrganManager.isTrueOrgan(stack.getItem())),"Opened cavity must not generate duplicate organs");h.succeed();
    }
}
