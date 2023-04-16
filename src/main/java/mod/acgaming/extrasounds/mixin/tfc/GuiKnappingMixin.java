package mod.acgaming.extrasounds.mixin.tfc;

import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.init.SoundEvents;

import mod.acgaming.extrasounds.sound.client.ESSoundManagerClient;
import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.dries007.tfc.client.gui.GuiKnapping;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = GuiKnapping.class, remap = false)
public class GuiKnappingMixin
{
    @Shadow
    @Final
    private KnappingType type;

    @Redirect(method = "actionPerformed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiButton;playPressSound(Lnet/minecraft/client/audio/SoundHandler;)V"))
    public void esKnappingPlayPressSound(GuiButton b1, SoundHandler soundHandler, GuiButton b2)
    {
        if (type.equals(KnappingType.CLAY) || type.equals(KnappingType.FIRE_CLAY))
        {
            ESSoundManagerClient.playSoundPlayer(SoundEvents.BLOCK_GRAVEL_BREAK, 1.2F, 0.8F);
        }
        else if (type.equals(KnappingType.STONE))
        {
            ESSoundManagerClient.playSoundPlayer(SoundEvents.BLOCK_STONE_BREAK, 2, 0.8F);
        }
        else if (type.equals(KnappingType.LEATHER))
        {
            ESSoundManagerClient.playSoundPlayer(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2, 0.8F);
        }
        else
        {
            ESSoundManagerClient.playSoundPlayer(SoundEvents.UI_BUTTON_CLICK, 1, 1);
        }
    }
}