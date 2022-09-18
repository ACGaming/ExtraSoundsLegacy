package mod.acgaming.extrasounds.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin extends Entity
{
    public EntityLivingBaseMixin(World worldIn)
    {
        super(worldIn);
    }

    @Inject(method = "onNewPotionEffect", at = @At(value = "TAIL"))
    public void esPotionAddSound(PotionEffect id, CallbackInfo ci)
    {
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

    @Inject(method = "onFinishedPotionEffect", at = @At(value = "TAIL"))
    public void esPotionRemoveSound(PotionEffect effect, CallbackInfo ci)
    {
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