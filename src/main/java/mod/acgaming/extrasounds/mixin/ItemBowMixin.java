package mod.acgaming.extrasounds.mixin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBow.class)
public abstract class ItemBowMixin
{
    @Inject(method = "onItemRightClick", at = @At(value = "RETURN"))
    public void esPullBowSound(World worldIn, EntityPlayer playerIn, EnumHand handIn, CallbackInfoReturnable<ActionResult<ItemStack>> cir)
    {
        if (worldIn.isRemote && ESConfig.soundToggles.esPullBowSound)
        {
            if (!this.findAmmo(playerIn).isEmpty() || playerIn.capabilities.isCreativeMode) ESSoundManager.playSoundPlayer(ESSoundEvents.pull_bow);
        }
    }

    @Shadow
    protected abstract ItemStack findAmmo(EntityPlayer player);
}