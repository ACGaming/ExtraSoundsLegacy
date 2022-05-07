package mod.acgaming.extrasounds.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.text.ITextComponent;

import mod.acgaming.extrasounds.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiNewChat.class)
public class GuiNewChatMixin
{
    @Inject(at = @At("RETURN"), method = "setChatLine")
    private void esMessageSound(ITextComponent chatComponent, int chatLineId, int updateCounter, boolean displayOnly, CallbackInfo ci)
    {
        if (Minecraft.getMinecraft().player == null || displayOnly) return;
        String msg = chatComponent.getUnformattedText();
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        SoundManager.playSound(SoundEvents.BLOCK_NOTE_HAT, 2.0F, 0.5F);

        // ONLY HANDLE CHAT MESSAGES BY PLAYERS
        if (msg.startsWith("<") && (msg.contains(player.getName()) || msg.contains(player.getDisplayNameString())))
        {
            // AVOID SELF-PINGS
            if (!msg.startsWith("<" + player.getName() + ">") || !msg.startsWith("<" + player.getDisplayNameString() + ">"))
            {
                SoundManager.playSound(SoundEvents.BLOCK_NOTE_CHIME, 1.8F, 0.7F);
            }
        }
    }
}