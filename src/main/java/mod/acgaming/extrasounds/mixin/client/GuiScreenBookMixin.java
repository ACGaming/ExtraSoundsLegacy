package mod.acgaming.extrasounds.mixin.client;

import net.minecraft.client.gui.GuiScreenBook;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.client.ESSoundManagerClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreenBook.class)
public class GuiScreenBookMixin
{
    @Inject(at = @At("HEAD"), method = "initGui")
    public void esOpenGUISound(CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esOpenCloseGUISound) ESSoundManagerClient.playSoundPlayer(ESSoundEvents.open_book);
    }

    @Inject(at = @At("HEAD"), method = "onGuiClosed")
    public void esCloseGUISound(CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esOpenCloseGUISound) ESSoundManagerClient.playSoundPlayer(ESSoundEvents.close_book);
    }
}