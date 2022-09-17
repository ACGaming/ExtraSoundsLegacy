package mod.acgaming.extrasounds.mixin;

import mezz.jei.gui.overlay.IngredientGridWithNavigation;
import mezz.jei.input.IPaged;
import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IngredientGridWithNavigation.class)
public class IngredientGridWithNavigationMixin
{
    @Final
    @Mutable
    @Shadow
    private final IPaged pageDelegate;

    public IngredientGridWithNavigationMixin(IPaged pageDelegate) {this.pageDelegate = pageDelegate;}

    @Inject(at = @At("HEAD"), method = "handleMouseScrolled", remap = false)
    public void esScrollJEIPage(int mouseX, int mouseY, int scrollDelta, CallbackInfoReturnable<Boolean> cir)
    {
        if (ESConfig.soundToggles.esScrollSound)
        {
            if (this.pageDelegate.getPageCount() > 1 && scrollDelta != 0)
            {
                ESSoundManager.playSoundPlayer(ESSoundEvents.scroll_page);
            }
        }
    }
}