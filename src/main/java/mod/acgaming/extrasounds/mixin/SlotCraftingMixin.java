package mod.acgaming.extrasounds.mixin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

import mod.acgaming.extrasounds.ExtraSounds;
import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlotCrafting.class)
public class SlotCraftingMixin
{
    @Shadow
    @Final
    private EntityPlayer player;

    @Inject(method = "onCrafting(Lnet/minecraft/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;onCrafting(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;I)V"))
    public void esCraftingSound(ItemStack stack, CallbackInfo ci)
    {
        if (ESConfig.soundToggles.esCraftingSound && !ExtraSounds.dsurround) ESSoundManager.playSoundPlayer(player.world, player, ESSoundEvents.crafting);
    }
}