package mod.acgaming.extrasounds.mixin.client;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.init.SoundEvents;

import mod.acgaming.extrasounds.ExtraSounds;
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
                if (ExtraSounds.assetmover) ESSoundManagerClient.playSoundPlayer(ESSoundEvents.typing);
                else ESSoundManagerClient.playSoundPlayer(SoundEvents.BLOCK_NOTE_HAT, 2, 0.2F);
            }
        }
    }
}