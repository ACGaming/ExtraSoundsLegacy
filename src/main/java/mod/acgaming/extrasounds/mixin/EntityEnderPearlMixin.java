package mod.acgaming.extrasounds.mixin;

import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.util.math.RayTraceResult;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityEnderPearl.class)
public class EntityEnderPearlMixin
{
    @Inject(method = "onImpact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;setPositionAndUpdate(DDD)V"))
    public void esEnderPearlTeleport(RayTraceResult result, CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esEnderPearlTeleportSound) ESSoundManager.playSoundPlayer(ESSoundEvents.teleport_ender_pearl);
    }
}