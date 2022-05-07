package mod.acgaming.extrasounds.mixin;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;

import mod.acgaming.extrasounds.SoundManager;
import mod.acgaming.extrasounds.config.ESConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryPlayer.class)
public class InventoryPlayerMixin
{
    @Inject(at = @At("RETURN"), method = "changeCurrentItem")
    private void esHotbarScrollSound(CallbackInfo info)
    {
        if (ESConfig.esHotbarSound) SoundManager.playSound(SoundEvents.BLOCK_NOTE_HAT, 1.8F, 0.15F);
    }
}