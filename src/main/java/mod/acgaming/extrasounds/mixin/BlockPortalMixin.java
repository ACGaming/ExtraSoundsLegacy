package mod.acgaming.extrasounds.mixin;

import net.minecraft.block.BlockPortal;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockPortal.class)
public class BlockPortalMixin
{
    @Inject(method = "trySpawnPortal", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockPortal$Size;placePortalBlocks()V"))
    public void esNetherPortal(World worldIn, BlockPos pos, CallbackInfoReturnable<Boolean> cir)
    {
        if (ESConfig.soundToggles.esNetherPortalSound) ESSoundManager.playSoundWorld(SoundEvents.BLOCK_END_PORTAL_SPAWN, pos);
    }
}