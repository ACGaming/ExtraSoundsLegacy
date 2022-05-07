package mod.acgaming.extrasounds.mixin;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.init.SoundEvents;

import mod.acgaming.extrasounds.SoundManager;
import mod.acgaming.extrasounds.config.ESConfig;
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
        if (ESConfig.esTypingSound)
        {
            if (cir.getReturnValue()) SoundManager.playSound(SoundEvents.BLOCK_NOTE_HAT, 2.0F, 0.2F);
        }
    }
}