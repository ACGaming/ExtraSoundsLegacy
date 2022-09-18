package mod.acgaming.extrasounds.mixin;

import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import mod.acgaming.extrasounds.ExtraSounds;
import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemSeeds.class)
public class ItemSeedsMixin
{
    @Inject(method = "onItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;shrink(I)V"))
    public void esCropSound(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<EnumActionResult> cir)
    {
        if (worldIn.isRemote && ESConfig.soundToggles.esCropSound && !ExtraSounds.asmc)
        {
            if (worldIn.getBlockState(pos).getBlock() instanceof BlockFarmland)
            {
                ESSoundManager.playSoundWorld(ESSoundEvents.plant_crop, pos);
            }
            else if (worldIn.getBlockState(pos).getBlock() instanceof BlockSoulSand)
            {
                ESSoundManager.playSoundWorld(ESSoundEvents.plant_netherwart, pos);
            }
        }
    }
}