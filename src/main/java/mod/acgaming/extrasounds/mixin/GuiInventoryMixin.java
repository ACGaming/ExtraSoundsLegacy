package mod.acgaming.extrasounds.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiInventory.class)
public class GuiInventoryMixin
{
    @Inject(at = @At("HEAD"), method = "handleMouseClick")
    public void esSlotClickSound(Slot slotIn, int slotId, int mouseButton, ClickType type, CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esPickPlaceSound)
        {
            if (slotIn != null)
            {
                // PLACE SOUND
                if (!Minecraft.getMinecraft().player.inventory.getItemStack().isEmpty()) ESSoundManager.playClickSound(Minecraft.getMinecraft().player.inventory.getItemStack());

                // PICK SOUND
                if (!slotIn.getStack().isEmpty()) ESSoundManager.playClickSound(slotIn.getStack());
            }
        }
    }
}