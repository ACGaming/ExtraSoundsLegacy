package mod.acgaming.extrasounds.mixin;

import net.minecraft.client.gui.GuiScreenBook;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
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
        if (ESConfig.soundToggles.esOpenCloseGUISound) ESSoundManager.playSound(ESSoundEvents.open_book);
    }

    @Inject(at = @At("HEAD"), method = "onGuiClosed")
    public void esCloseGUISound(CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esOpenCloseGUISound) ESSoundManager.playSound(ESSoundEvents.close_book);
    }
}