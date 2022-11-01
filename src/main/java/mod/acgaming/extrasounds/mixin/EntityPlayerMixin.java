package mod.acgaming.extrasounds.mixin;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase
{
    public EntityPlayerMixin(World worldIn)
    {
        super(worldIn);
    }

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

    @Override
    public void onNewPotionEffect(PotionEffect id)
    {
        super.onNewPotionEffect(id);
        if (ESConfig.soundToggles.esPotionSound)
        {
            if (id.getPotion().isBadEffect())
            {
                ESSoundManager.playSoundWorld(ESSoundEvents.effect_add_negative, this.getPosition());
                ESSoundManager.playSoundWorld(ESSoundEvents.effect_add_negative, this.getPosition());
            }
            else
            {
                ESSoundManager.playSoundWorld(ESSoundEvents.effect_add_positive, this.getPosition());
                ESSoundManager.playSoundWorld(ESSoundEvents.effect_add_positive, this.getPosition());
            }
        }
    }

    @Override
    public void onFinishedPotionEffect(PotionEffect effect)
    {
        super.onFinishedPotionEffect(effect);
        if (ESConfig.soundToggles.esPotionSound)
        {
            if (effect.getPotion().isBadEffect())
            {
                ESSoundManager.playSoundWorld(ESSoundEvents.effect_remove_negative, this.getPosition());
                ESSoundManager.playSoundWorld(ESSoundEvents.effect_remove_negative, this.getPosition());
            }
            else
            {
                ESSoundManager.playSoundWorld(ESSoundEvents.effect_remove_positive, this.getPosition());
                ESSoundManager.playSoundWorld(ESSoundEvents.effect_remove_positive, this.getPosition());
            }
        }
    }
}