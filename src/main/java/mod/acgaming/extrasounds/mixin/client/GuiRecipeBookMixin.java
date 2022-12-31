package mod.acgaming.extrasounds.mixin.client;

import net.minecraft.client.gui.recipebook.GuiRecipeBook;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.client.ESSoundManagerClient;
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
            if (open) ESSoundManagerClient.playSoundPlayer(ESSoundEvents.open_book);
            else ESSoundManagerClient.playSoundPlayer(ESSoundEvents.close_book);
        }
    }
}