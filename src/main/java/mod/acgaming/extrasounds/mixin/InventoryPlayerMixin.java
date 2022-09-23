package mod.acgaming.extrasounds.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryPlayer.class)
public class InventoryPlayerMixin
{
    @Inject(at = @At("RETURN"), method = "changeCurrentItem")
    public void esHotbarScrollSound(CallbackInfo info)
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
}