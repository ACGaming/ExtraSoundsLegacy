package mod.acgaming.extrasounds.mixin;

import mezz.jei.gui.overlay.IngredientGridWithNavigation;
import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IngredientGridWithNavigation.class)
public class IngredientGridWithNavigationMixin
{
    @Inject(at = @At("HEAD"), method = "handleMouseScrolled", remap = false)
    protected void esScrollJEIPage(int mouseX, int mouseY, int scrollDelta, CallbackInfoReturnable<Boolean> cir)
    {
        if (ESConfig.soundToggles.esScrollJEIPageSound)
        {
            if (scrollDelta != 0)
            {
                ESSoundManager.playSound(ESSoundEvents.scroll_page);
            }
        }
    }
}