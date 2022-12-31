package mod.acgaming.extrasounds.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.client.ESSoundManagerClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiContainer.class)
public class GuiContainerMixin
{
    @Shadow
    protected boolean dragSplitting;
    @Unique
    private int prevRemnant;
    @Shadow
    private int dragSplittingRemnant;

    @Inject(at = @At("HEAD"), method = "initGui")
    public void esOpenGUISound(CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esOpenCloseGUISound) ESSoundManagerClient.playSoundPlayer(ESSoundEvents.open_gui);
    }

    @Inject(at = @At("HEAD"), method = "onGuiClosed")
    public void esCloseGUISound(CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esOpenCloseGUISound) ESSoundManagerClient.playSoundPlayer(ESSoundEvents.close_gui);
    }

    @Inject(at = @At("HEAD"), method = "handleMouseClick")
    public void esSlotClickSound(Slot slotIn, int slotId, int mouseButton, ClickType type, CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esPickPlaceSound && slotIn != null)
        {
            if (!Minecraft.getMinecraft().player.inventory.getItemStack().isEmpty())
            {
                // PLACE SOUND
                ESSoundManagerClient.playClickSound(Minecraft.getMinecraft().player.inventory.getItemStack());
            }
            else if (!slotIn.getStack().isEmpty())
            {
                // PICK SOUND
                ESSoundManagerClient.playClickSound(slotIn.getStack());
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "updateDragSplitting")
    public void esSlotDragSound(CallbackInfo ci)
    {
        ItemStack esItemStack = Minecraft.getMinecraft().player.inventory.getItemStack();
        if (ESConfig.soundToggles.esPickPlaceSound && !esItemStack.isEmpty() && this.dragSplitting)
        {
            if (prevRemnant != this.dragSplittingRemnant)
            {
                ESSoundManagerClient.playClickSound(esItemStack);
                prevRemnant = this.dragSplittingRemnant;
            }
        }
    }
}