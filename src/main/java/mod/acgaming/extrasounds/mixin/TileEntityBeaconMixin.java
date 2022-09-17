package mod.acgaming.extrasounds.mixin;

import javax.annotation.Nullable;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityBeacon.class)
public abstract class TileEntityBeaconMixin extends TileEntityLockable implements ITickable, ISidedInventory
{
    @Unique
    private boolean currentState;

    @Unique
    @Nullable
    private Potion primaryEffectCached;

    @Unique
    @Nullable
    private Potion secondaryEffectCached;

    @Shadow
    private boolean isComplete;

    @Shadow
    @Nullable
    private Potion primaryEffect;

    @Shadow
    @Nullable
    private Potion secondaryEffect;

    @Inject(method = "updateBeacon", at = @At(value = "TAIL"))
    public void esBeaconSoundAmbient(CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esBeaconSound && this.isComplete)
        {
            ESSoundManager.playSoundWorld(ESSoundEvents.beacon_ambient, this.pos);
            if (this.primaryEffect != primaryEffectCached || this.secondaryEffect != secondaryEffectCached)
            {
                ESSoundManager.playSoundWorld(ESSoundEvents.beacon_power, this.pos);
            }
            primaryEffectCached = this.primaryEffect;
            secondaryEffectCached = this.secondaryEffect;
        }
    }

    @Inject(method = "shouldBeamRender", at = @At(value = "HEAD"))
    public void esBeaconSound(CallbackInfoReturnable<Float> cir)
    {
        if (ESConfig.soundToggles.esBeaconSound)
        {
            if (this.isComplete && !currentState)
            {
                ESSoundManager.playSoundWorld(ESSoundEvents.beacon_activate, this.pos);
            }
            else if (!this.isComplete && currentState)
            {
                ESSoundManager.playSoundWorld(ESSoundEvents.beacon_deactivate, this.pos);
            }
            currentState = this.isComplete;
        }
    }

    @Inject(method = "setInventorySlotContents", at = @At(value = "TAIL"))
    public void esBeaconSoundPower(int index, ItemStack stack, CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esBeaconSound)
        {
            ESSoundManager.playSoundWorld(ESSoundEvents.beacon_power, this.pos);
        }
    }
}