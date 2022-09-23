package mod.acgaming.extrasounds.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin
{
    @Inject(method = "processKeyBinds", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/InventoryPlayer;currentItem:I", shift = At.Shift.AFTER))
    public void esHotbarSlotSound(CallbackInfo info)
    {
        if (ESConfig.soundToggles.esHotbarSound)
        {
            if (ESConfig.soundToggles.esPickPlaceSound)
            {
                ItemStack esItemStack = Minecraft.getMinecraft().player.inventory.getCurrentItem();
                if (!esItemStack.isEmpty()) ESSoundManager.playClickSound(Minecraft.getMinecraft().player.inventory.getCurrentItem());
                else ESSoundManager.playSoundPlayer(ESSoundEvents.hotbar_slot);
            }
            else ESSoundManager.playSoundPlayer(ESSoundEvents.hotbar_slot);
        }
    }

    @Inject(method = "processKeyBinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/NetHandlerPlayClient;sendPacket(Lnet/minecraft/network/Packet;)V"))
    public void esSwapHandsSound(CallbackInfo info)
    {
        if (ESConfig.soundToggles.esSwapHandsSound) ESSoundManager.playSoundPlayer(ESSoundEvents.swap_hands);
    }
}