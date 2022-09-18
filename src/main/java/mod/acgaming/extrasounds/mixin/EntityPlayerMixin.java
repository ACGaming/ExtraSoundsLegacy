package mod.acgaming.extrasounds.mixin;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public class EntityPlayerMixin
{
    @Inject(at = @At("TAIL"), method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/item/EntityItem;")
    public void esDropItemSound(ItemStack droppedItem, boolean dropAround, boolean traceItem, CallbackInfoReturnable<EntityItem> cir)
    {
        if (ESConfig.soundToggles.esDropItemSound)
        {
            if (traceItem && !droppedItem.isEmpty())
            {
                float range = 0.1F;
                float pitch = 1.0F + range * (1.0F * droppedItem.getItem().getItemStackLimit() / droppedItem.getCount()) - range / 2;
                ESSoundManager.playSoundPlayer(ESSoundEvents.drop_item, pitch, 1.0F);
            }
        }
    }
}