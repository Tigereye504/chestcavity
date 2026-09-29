package net.tigereye.chestcavity.compat.backrooms;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.*;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.registration.CCItems;
import java.util.List;
public class CCBackroomsLootRegister {
    private static boolean enabled;
    private record Entry(Item item,int weight,int trials){}
    public static void register(){enabled=true;}
    public static void apply(Identifier id,List<ItemStack> loot,RandomSource random){
        if(!enabled || !id.getNamespace().equals("backrooms"))return;
        List<Entry> entries=switch(id.getPath()){
case "chests/level0" -> List.of(new Entry(CCItems.ROTTEN_APPENDIX,1,0),new Entry(CCItems.ROTTEN_HEART,1,0),new Entry(CCItems.ROTTEN_INTESTINE,1,0),new Entry(CCItems.ROTTEN_KIDNEY,1,0),new Entry(CCItems.ROTTEN_LIVER,1,0),new Entry(CCItems.ROTTEN_LUNG,1,0),new Entry(CCItems.ROTTEN_RIB,1,4),new Entry(CCItems.ROTTEN_SPINE,1,0),new Entry(CCItems.ROTTEN_SPLEEN,1,0),new Entry(CCItems.ROTTEN_STOMACH,1,0));
case "chests/level1" -> List.of(new Entry(CCItems.ROTTEN_APPENDIX,2,0),new Entry(CCItems.ROTTEN_HEART,2,0),new Entry(CCItems.ROTTEN_INTESTINE,2,0),new Entry(CCItems.ROTTEN_KIDNEY,2,0),new Entry(CCItems.ROTTEN_LIVER,2,0),new Entry(CCItems.ROTTEN_LUNG,2,0),new Entry(CCItems.ROTTEN_RIB,2,4),new Entry(CCItems.ROTTEN_SPINE,2,0),new Entry(CCItems.ROTTEN_SPLEEN,2,0),new Entry(CCItems.ROTTEN_STOMACH,2,0),new Entry(CCItems.SMALL_ANIMAL_APPENDIX,1,0),new Entry(CCItems.SMALL_ANIMAL_HEART,1,0),new Entry(CCItems.SMALL_ANIMAL_INTESTINE,1,0),new Entry(CCItems.SMALL_ANIMAL_KIDNEY,1,0),new Entry(CCItems.SMALL_ANIMAL_LIVER,1,0),new Entry(CCItems.SMALL_ANIMAL_LUNG,1,0),new Entry(CCItems.SMALL_GILLS,1,0),new Entry(CCItems.SMALL_ANIMAL_RIB,1,4),new Entry(CCItems.SMALL_ANIMAL_SPINE,1,0),new Entry(CCItems.SMALL_ANIMAL_SPLEEN,1,0),new Entry(CCItems.SMALL_ANIMAL_STOMACH,1,0),new Entry(CCItems.SMALL_ANIMAL_MUSCLE,1,4));
case "chests/level3" -> List.of(new Entry(CCItems.HUMAN_MUSCLE,8,16),new Entry(CCItems.HUMAN_APPENDIX,4,0),new Entry(CCItems.HUMAN_KIDNEY,4,0),new Entry(CCItems.HUMAN_LIVER,4,0),new Entry(CCItems.HUMAN_RIB,4,4),new Entry(CCItems.HUMAN_SPLEEN,4,0),new Entry(CCItems.HUMAN_LUNG,2,0),new Entry(CCItems.HUMAN_HEART,1,0),new Entry(CCItems.HUMAN_INTESTINE,1,0),new Entry(CCItems.HUMAN_SPINE,1,0),new Entry(CCItems.HUMAN_STOMACH,1,0));
            default -> List.of();
        };
        if(entries.isEmpty())return;
        int weight=entries.stream().mapToInt(Entry::weight).sum();
        for(int i=0;i<ChestCavity.config.BACKROOMS_CHEST_ORGAN_LOOT_ATTEMPTS;i++)if(random.nextFloat()<ChestCavity.config.BACKROOMS_CHEST_ORGAN_LOOT_CHANCE){
            int choice=random.nextInt(weight);
            for(var entry:entries){choice-=entry.weight();if(choice<0){int count=entry.trials()==0?1:0;for(int j=0;j<entry.trials();j++)if(random.nextFloat()<.6f)count++;if(count>0)loot.add(new ItemStack(entry.item(),count));break;}}
        }
    }
}
