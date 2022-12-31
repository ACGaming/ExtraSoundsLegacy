package mod.acgaming.extrasounds.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.client.ESSoundManagerClient;
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
                if (!esItemStack.isEmpty()) ESSoundManagerClient.playClickSound(esItemStack);
                else ESSoundManagerClient.playSoundPlayer(ESSoundEvents.hotbar_slot);
            }
            else ESSoundManagerClient.playSoundPlayer(ESSoundEvents.hotbar_slot);
        }
    }

    @Inject(method = "processKeyBinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/NetHandlerPlayClient;sendPacket(Lnet/minecraft/network/Packet;)V"))
    public void esSwapHandsSound(CallbackInfo info)
    {
        if (ESConfig.soundToggles.esSwapHandsSound) ESSoundManagerClient.playSoundPlayer(ESSoundEvents.swap_hands);
    }
}