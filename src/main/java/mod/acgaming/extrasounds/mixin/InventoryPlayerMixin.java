package mod.acgaming.extrasounds.mixin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryPlayer.class)
public class InventoryPlayerMixin
{
    @Shadow
    public EntityPlayer player;

    @Inject(at = @At("RETURN"), method = "changeCurrentItem")
    public void esHotbarScrollSound(CallbackInfo info)
    {
        if (ESConfig.soundToggles.esHotbarSound)
        {
            if (ESConfig.soundToggles.esPickPlaceSound)
            {
                ItemStack esItemStack = player.inventory.getCurrentItem();
                if (!esItemStack.isEmpty()) ESSoundManager.playClickSound(player.world, player, esItemStack);
                else ESSoundManager.playSoundPlayer(player.world, player, ESSoundEvents.hotbar_slot);
            }
            else ESSoundManager.playSoundPlayer(player.world, player, ESSoundEvents.hotbar_slot);
        }
    }
}