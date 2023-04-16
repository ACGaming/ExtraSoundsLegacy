package mod.acgaming.extrasounds.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.Loader;

import net.dries007.tfc.client.gui.GuiKnapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import zone.rong.mixinextras.injector.WrapWithCondition;

@Mixin(GuiScreen.class)
public class GuiScreenMixin
{
    @Shadow
    public Minecraft mc;

    @WrapWithCondition(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiButton;playPressSound(Lnet/minecraft/client/audio/SoundHandler;)V"))
    public boolean esKnappingMouseClicked(GuiButton button, SoundHandler soundHandler)
    {
        if (!Loader.isModLoaded("tfc")) return true;
        else return !(this.mc.currentScreen instanceof GuiKnapping);
    }
}