package mod.acgaming.extrasounds.mixin;

import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockJukebox.class)
public class BlockJukeboxMixin
{
    @Inject(method = "insertRecord", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockJukebox$TileEntityJukebox;setRecord(Lnet/minecraft/item/ItemStack;)V"))
    public void esJukeboxInsert(World worldIn, BlockPos pos, IBlockState state, ItemStack recordStack, CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esJukeboxInteractSound) ESSoundManager.playSound(ESSoundEvents.jukebox_insert);
    }

    @Inject(method = "dropRecord", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockJukebox$TileEntityJukebox;setRecord(Lnet/minecraft/item/ItemStack;)V"))
    public void esJukeboxEject(World worldIn, BlockPos pos, IBlockState state, CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esJukeboxInteractSound) ESSoundManager.playSound(ESSoundEvents.jukebox_eject);
    }
}