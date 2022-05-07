package mod.acgaming.extrasounds.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;

import mod.acgaming.extrasounds.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiContainer.class)
public class GuiContainerMixin
{
    @Inject(at = @At("HEAD"), method = "initGui")
    public void esOpenInventorySound(CallbackInfo ci)
    {
        SoundManager.playSound(SoundEvents.UI_TOAST_IN, 2.0F, 1.5F);
    }

    @Inject(at = @At("HEAD"), method = "onGuiClosed")
    public void esCloseInventorySound(CallbackInfo ci)
    {
        SoundManager.playSound(SoundEvents.UI_TOAST_OUT, 2.0F, 1.5F);
    }

    @Inject(at = @At("HEAD"), method = "handleMouseClick")
    protected void esSlotClickSound(Slot slotIn, int slotId, int mouseButton, ClickType type, CallbackInfo ci)
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