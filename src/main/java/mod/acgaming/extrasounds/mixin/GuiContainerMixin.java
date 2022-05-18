package mod.acgaming.extrasounds.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;

import mod.acgaming.extrasounds.SoundManager;
import mod.acgaming.extrasounds.config.ESConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiContainer.class)
public class GuiContainerMixin
{
    @Inject(at = @At("HEAD"), method = "initGui")
    public void esOpenGUISound(CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esOpenCloseGUISound) SoundManager.playSound(SoundEvents.UI_TOAST_IN, 2.0F, (float) ESConfig.soundVolume.esOpenCloseGUISoundVolume);
    }

    @Inject(at = @At("HEAD"), method = "onGuiClosed")
    public void esCloseGUISound(CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esOpenCloseGUISound) SoundManager.playSound(SoundEvents.UI_TOAST_OUT, 2.0F, (float) ESConfig.soundVolume.esOpenCloseGUISoundVolume);
    }

    @Inject(at = @At("HEAD"), method = "handleMouseClick")
    protected void esSlotClickSound(Slot slotIn, int slotId, int mouseButton, ClickType type, CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esPickPlaceSound)
        {
            if (slotIn != null)
            {
                // PLACE SOUND
                if (!Minecraft.getMinecraft().player.inventory.getItemStack().isEmpty()) SoundManager.playClickSound(Minecraft.getMinecraft().player.inventory.getItemStack());

                // PICK SOUND
                if (!slotIn.getStack().isEmpty()) SoundManager.playClickSound(slotIn.getStack());
            }
        }
    }
}