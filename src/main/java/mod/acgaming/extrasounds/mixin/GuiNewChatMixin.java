package mod.acgaming.extrasounds.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.text.ITextComponent;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiNewChat.class)
public class GuiNewChatMixin
{
    @Inject(at = @At("RETURN"), method = "setChatLine")
    public void esMessageMentionSound(ITextComponent chatComponent, int chatLineId, int updateCounter, boolean displayOnly, CallbackInfo ci)
    {
        if (Minecraft.getMinecraft().player == null || displayOnly) return;

        if (ESConfig.soundToggles.esMessageSound)
        {
            ESSoundManager.playSound(ESSoundEvents.chat_message);
        }

        if (ESConfig.soundToggles.esMentionSound)
        {
            String msg = chatComponent.getUnformattedText();
            EntityPlayerSP player = Minecraft.getMinecraft().player;

            // ONLY HANDLE CHAT MESSAGES BY PLAYERS
            if (msg.startsWith("<") && (msg.contains(player.getName()) || msg.contains(player.getDisplayNameString())))
            {
                // AVOID SELF-PINGS
                if (!msg.startsWith("<" + player.getName() + ">") || !msg.startsWith("<" + player.getDisplayNameString() + ">"))
                {
                    ESSoundManager.playSound(ESSoundEvents.chat_mention);
                }
            }
        }
    }
}