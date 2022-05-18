package mod.acgaming.extrasounds.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.init.SoundEvents;

import mod.acgaming.extrasounds.SoundManager;
import mod.acgaming.extrasounds.config.ESConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin
{
    @Inject(method = "processKeyBinds", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/InventoryPlayer;currentItem:I"))
    private void esHotbarSlotSound(CallbackInfo info)
    {
        if (ESConfig.soundToggles.esHotbarSound) SoundManager.playSound(SoundEvents.BLOCK_NOTE_HAT, 1.8F, (float) ESConfig.soundVolume.esHotbarSoundVolume);
    }
}