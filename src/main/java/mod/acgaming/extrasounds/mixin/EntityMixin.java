package mod.acgaming.extrasounds.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import mod.acgaming.extrasounds.config.ESConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Entity.class)
public abstract class EntityMixin
{
    @Shadow
    public World world;

    @Shadow
    public abstract void playSound(SoundEvent soundIn, float volume, float pitch);

    @Inject(method = "playStepSound", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getSoundType()Lnet/minecraft/block/SoundType;"), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void esPlayStepSoundSnow(BlockPos pos, Block blockIn, CallbackInfo ci, SoundType soundtype)
    {
        if (!ESConfig.soundToggles.esCombinationStepSound) return;
        this.playSound(soundtype.getStepSound(), soundtype.getVolume() * 0.08F, soundtype.getPitch() - 0.3F);
    }

    @Inject(method = "playStepSound", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;", ordinal = 1), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    public void esPlayStepSoundCarpet(BlockPos pos, Block blockIn, CallbackInfo ci, SoundType soundtype)
    {
        if (!ESConfig.soundToggles.esCombinationStepSound) return;
        if (this.world.getBlockState(pos.up()).getBlock() == Blocks.CARPET)
        {
            this.playSound(soundtype.getStepSound(), soundtype.getVolume() * 0.08F, soundtype.getPitch() - 0.3F);
            SoundType soundtypeCarpet = Blocks.CARPET.getSoundType();
            this.playSound(soundtypeCarpet.getStepSound(), soundtypeCarpet.getVolume() * 0.15F, soundtypeCarpet.getPitch());
            ci.cancel();
        }
    }
}