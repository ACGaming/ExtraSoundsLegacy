package mod.acgaming.extrasounds.mixin;

import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
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

    @Shadow
    public abstract World getSpawnerWorld();

    @Inject(method = "updateSpawner", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/storage/AnvilChunkLoader;spawnEntity(Lnet/minecraft/entity/Entity;Lnet/minecraft/world/World;)V"))
    public void esSpawnerSound(CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esSpawnerSound) ESSoundManager.playSoundWorld(this.getSpawnerWorld(), null, ESSoundEvents.mob_spawner, this.getSpawnerPosition());
    }
}