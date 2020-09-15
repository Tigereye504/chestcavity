package net.tigereye.chestcavity.items;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.Tag;

public class VanillaOrgans {
    //a strength of 0 means nothing was found
    public static float getStrength(Item item, Tag<Item> tag){
        if(tag == CC_Items.ORGANS_APENDIX){
            if(item == Items.DIRT){
                return 1f/64;
            }
            return 0;
        }
        if(tag == CC_Items.ORGANS_HEART){
            if(item == Items.DIRT){
                return 1f/(64*27);
            }
            return 0;
        }
        if(tag == CC_Items.ORGANS_INTESTINE){
            if(item == Items.DIRT){
                return 4f/(64*27);
            }
            if(item == Items.HOPPER){
                return 1.25f/64;
            }
            return 0;
        }
        if(tag == CC_Items.ORGANS_KIDNEY){
            if(item == Items.DIRT){
                return 1f/(64*27);
            }
            if(item == Items.HONEY_BOTTLE){
                return 1.25f/16;
            }
            return 0;
        }
        if(tag == CC_Items.ORGANS_LIVER){
            if(item == Items.DIRT){
                return 1f/(64*27);
            }
            if(item == Items.MILK_BUCKET){
                return .75f;
            }
            return 0;
        }
        if(tag == CC_Items.ORGANS_LUNG){
            if(item == Items.DIRT){
                return 2f/(64*27);
            }
            if(item == Items.PUFFERFISH){
                return 1.25f/64;
            }
            return 0;
        }
        if(tag == CC_Items.ORGANS_MUSCLE){
            if(item == Items.DIRT){
                return 8f/27;
            }
            if(item == Items.PORKCHOP || item == Items.MUTTON || item == Items.BEEF){
                return 0.75f;
            }
            else if (item == Items.ROTTEN_FLESH){
                return 0.5f;
            }
            else if (item == Items.PISTON){
                return 1f;
            }
            else if (item == Items.STICKY_PISTON){
                return 1.25f;
            }
            return 0;
        }
        if(tag == CC_Items.ORGANS_RIB){
            if(item == Items.DIRT){
                return 16f/(64*27);
            }
            if(item == Items.STICK){
                return .25f/16;
            }
            if(item == Items.BONE){
                return .5f/16;
            }
            if(item == Items.IRON_BARS){
                return .75f/16;
            }
            return 0;
        }
        if(tag == CC_Items.ORGANS_SPINE){
            if(item == Items.DIRT){
                return 1f/(64*27);
            }
            return 0;
        }
        if(tag == CC_Items.ORGANS_SPLEEN){
            if(item == Items.DIRT){
                return 1f/(64*27);
            }
            return 0;
        }
        if(tag == CC_Items.ORGANS_STOMACH){
            if(item == Items.DIRT){
                return 1f/(64*27);
            }
            if(item == Items.FURNACE | item == Items.CHEST){
                return .5f;
            }
            if(item == Items.BUCKET){
                return .75f/16;
            }
            if(item == Items.ENDER_CHEST){
                return 1.25f/64;
            }
            return 0;
        }
        return 0;
    }
}