package mod.acgaming.extrasounds.mixin;

import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.math.BlockPos;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobSpawnerBaseLogic.class)
public abstract class MobSpawnerBaseLogicMixin
{
    @Shadow
    public abstract BlockPos getSpawnerPosition();

    @Inject(method = "updateSpawner", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/storage/AnvilChunkLoader;spawnEntity(Lnet/minecraft/entity/Entity;Lnet/minecraft/world/World;)V"))
    public void esSpawnerSound(CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esSpawnerSound) ESSoundManager.playSoundWorld(SoundEvents.ITEM_FIRECHARGE_USE, this.getSpawnerPosition(), 0.5F, 0.4F);
    }
}