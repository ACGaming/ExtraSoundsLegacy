package mod.acgaming.extrasounds.mixin.client;

import net.minecraft.client.gui.GuiTextField;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.client.ESSoundManagerClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GuiTextField.class)
public class GuiTextFieldMixin
{
    @Inject(at = @At("RETURN"), method = "textboxKeyTyped")
    public void esTypingSound(char chr, int modifiers, CallbackInfoReturnable<Boolean> cir)
    {
        if (ESConfig.soundToggles.esTypingSound)
        {
            if (cir.getReturnValue())
            {
                ESSoundManagerClient.playSoundPlayer(ESSoundEvents.typing);
            }
        }
    }
}