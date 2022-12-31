package mod.acgaming.extrasounds.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.client.ESSoundManagerClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiContainerCreative.class)
public class GuiContainerCreativeMixin
{
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

    @Inject(at = @At("HEAD"), method = "setCurrentCreativeTab")
    public void esTabClickSound(CreativeTabs tab, CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esCreativeTabSound)
        {
            ESSoundManagerClient.playSoundPlayer(ESSoundEvents.creative_tab);
        }
    }

    @Inject(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/inventory/GuiContainerCreative$ContainerCreative;scrollTo(F)V"))
    public void esScrollSound(CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esScrollSound)
        {
            ESSoundManagerClient.playSoundPlayer(ESSoundEvents.scroll_page);
        }
    }
}