package net.tigereye.chestcavity.listeners;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.*;
import net.tigereye.chestcavity.registration.*;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.chestcavities.organs.OrganManager;
import net.tigereye.chestcavity.recipes.SalvageRecipe;
import java.util.*;
public class LootRegister extends LootModifier {
    public static final MapCodec<LootRegister> CODEC=RecordCodecBuilder.mapCodec(i->codecStart(i).apply(i,LootRegister::new));
    public LootRegister(LootItemCondition[] conditions){super(conditions);}
    public static void register() {}
    @Override public MapCodec<? extends IGlobalLootModifier> codec(){return CODEC;}
    @Override protected ObjectArrayList<ItemStack> doApply(LootTable table,ObjectArrayList<ItemStack> loot,LootContext context){
        var id=context.getQueriedLootTableId();
        var random=context.getRandom();
        if(id.toString().equals("minecraft:chests/desert_pyramid")){
            for(int i=0;i<4;i++)if(random.nextFloat()<.25f)loot.add(new ItemStack(CCItems.ROTTEN_RIB));
            if(random.nextFloat()<.3f)loot.add(new ItemStack(CCItems.ROTTEN_RIB));
        }
        net.tigereye.chestcavity.compat.backrooms.CCBackroomsLootRegister.apply(id,loot,random);
        // Nested/block/container loot must never generate a creature's organs.
        if(!id.getPath().startsWith("entities/") || !context.hasParameter(LootContextParams.DAMAGE_SOURCE))return loot;
        var attacker=context.getOptional(LootContextParams.ATTACKING_ENTITY);
        LivingEntity killer=attacker instanceof LivingEntity living?living:null;
        boolean butcher=killer!=null && killer.getMainHandItem().is(CCTags.BUTCHERING_TOOL);
        if(context.hasParameter(LootContextParams.LAST_DAMAGE_PLAYER)){
            ChestCavityEntity.of(context.getOptional(LootContextParams.THIS_ENTITY)).ifPresent(cce->{
                var cc=cce.getChestCavityInstance();
                if(cc.opened || killer!=null && CCEnchantments.getEnchantmentLevel(CCEnchantments.TOMOPHOBIA,killer)>0)return;
                int level=killer==null?0:CCEnchantments.getEnchantmentLevel(Enchantments.LOOTING,killer)+2*CCEnchantments.getEnchantmentLevel(CCEnchantments.SURGICAL,killer);
                if(butcher)level=10+10*level;
                loot.addAll(cc.getChestCavityType().generateLootDrops(random,level));
            });
        }
        if(butcher){
            var recipes=context.getLevel().getServer().getRecipeManager().getRecipes().stream().map(h->h.value()).filter(SalvageRecipe.class::isInstance).map(SalvageRecipe.class::cast).toList();
            Map<SalvageRecipe,Integer> counts=new HashMap<>();
            for(var it=loot.iterator();it.hasNext();){var stack=it.next();if(stack.is(CCTags.SALVAGEABLE))for(var recipe:recipes)if(recipe.input().test(stack)){counts.merge(recipe,stack.getCount(),Integer::sum);it.remove();break;}}
            counts.forEach((recipe,count)->{ItemStack result=recipe.getOutput();int amount=result.getCount()*(count/recipe.required());while(amount>0){int n=Math.min(amount,result.getMaxStackSize());loot.add(result.copyWithCount(n));amount-=n;}});
        }
        if(killer!=null && CCEnchantments.getLevel(CCEnchantments.MALPRACTICE,killer.getMainHandItem())>0){
            var enchantment=context.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(CCEnchantments.MALPRACTICE);
            for(var stack:loot)if(OrganManager.isTrueOrgan(stack.getItem()))stack.enchant(enchantment,1);
        }
        return loot;
    }
}
