package mod.acgaming.extrasounds.mixin;

import net.minecraft.client.gui.recipebook.GuiRecipeBook;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiRecipeBook.class)
public class GuiRecipeBookMixin
{
    @Inject(at = @At("HEAD"), method = "setVisible")
    public void esRecipeBookSound(boolean open, CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esOpenCloseBookSound)
        {
            if (open) ESSoundManager.playSoundPlayer(ESSoundEvents.open_book);
            else ESSoundManager.playSoundPlayer(ESSoundEvents.close_book);
        }
    }
}