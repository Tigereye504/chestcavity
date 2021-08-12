package net.tigereye.chestcavity.mixin;
/*
import com.mojang.datafixers.util.Pair;
import ladysnake.requiem.api.v1.entity.InventoryLimiter;
import ladysnake.requiem.api.v1.entity.InventoryPart;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//TODO: get disabled slots working, then re-install in mixins.json
@Mixin(Slot.class)
public abstract class MixinSlot {
    //move this somewhere else...

    private static final Lazy<Pair<Identifier, Identifier>> LOCKED_SPRITE_REF = new Lazy(() -> {
        return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, RequiemClient.LOCKED_SLOT_SPRITE);
    });

    @Unique
    protected boolean CC_forbidden;

    public MixinSlot() {
    }

    @Inject(
            method = {"<init>"},
            at = {@At("RETURN")}
    )
    private void constructor(Inventory inventory, int index, int x, int y, CallbackInfo ci) {
        if (inventory instanceof ChestCavityInventory) {
            ChestCavityInstance cc = ((ChestCavityInventory)inventory).getInstance();
            if(cc != null){
                //this.CC_forbidden = cc.type.isSlotForbidden(index);
                ChestCavity.LOGGER.info("Slot " + index + " forming");
                if(this.CC_forbidden) {
                    ChestCavity.LOGGER.info("Slot " + index + "is forbidden");
                }
            }
        }
    }

    @Unique
    private boolean shouldBeLocked() {
        return this.CC_forbidden;
    }

    @Unique
    private boolean shouldBeInvisible() {
        return this.CC_forbidden;
    }

    @Inject(
            method = {"canInsert"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void canInsert(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (this.CC_forbidden) {
            cir.setReturnValue(false);
        }

    }

    @Inject(
            method = {"canTakeItems"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void canTakeItems(PlayerEntity playerEntity, CallbackInfoReturnable<Boolean> cir) {
        if (this.CC_forbidden) {
            cir.setReturnValue(false);
        }

    }

    @Inject(
            method = {"getBackgroundSprite"},
            at = {@At("HEAD")},
            cancellable = true
    )
    @Environment(EnvType.CLIENT)
    private void getLockedSprite(CallbackInfoReturnable<Pair<Identifier, Identifier>> cir) {
        if (this.CC_forbidden) {
            cir.setReturnValue((Pair)LOCKED_SPRITE_REF.get());
        }

    }

    @Inject(
            method = {"doDrawHoveringEffect"},
            at = {@At("HEAD")},
            cancellable = true
    )
    @Environment(EnvType.CLIENT)
    private void preventSpecialRender(CallbackInfoReturnable<Boolean> cir) {
        if (this.CC_forbidden) {
            cir.setReturnValue(false);
        }

    }
}
*/