package mod.acgaming.extrasounds.sound.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import paulscode.sound.SoundSystemConfig;

public class ESSoundManagerClient
{
    private static long lastPlayed = System.currentTimeMillis();

    public static void preInit()
    {
        if (ESConfig.miscSettings.esSoundChannels)
        {
            SoundSystemConfig.setNumberNormalChannels(1024);
            SoundSystemConfig.setNumberStreamingChannels(32);
        }
    }

    public static void playSoundPlayer(SoundEvent soundEvent, float pitch, float volume)
    {
        long now = System.currentTimeMillis();
        if (now - lastPlayed > 5)
        {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP player = mc.player;
            if (player != null) mc.getSoundHandler().playSound(new PositionedSoundRecord(soundEvent.getSoundName(), SoundCategory.PLAYERS, volume, pitch - 0.1F + randomOffset(), false, 0, ISound.AttenuationType.NONE, (float) player.posX, (float) player.posY + 32, (float) player.posZ));
            else mc.getSoundHandler().playSound(new PositionedSoundRecord(soundEvent.getSoundName(), SoundCategory.MASTER, volume, pitch - 0.1F + randomOffset(), false, 0, ISound.AttenuationType.NONE, 0F, 0F, 0F));
            lastPlayed = now;
        }
    }

    public static void playSoundPlayer(SoundEvent soundEvent)
    {
        long now = System.currentTimeMillis();
        if (now - lastPlayed > 5)
        {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP player = mc.player;
            if (player != null) mc.getSoundHandler().playSound(new PositionedSoundRecord(soundEvent.getSoundName(), SoundCategory.PLAYERS, 1.0F, 0.9F + randomOffset(), false, 0, ISound.AttenuationType.NONE, (float) player.posX, (float) player.posY + 32, (float) player.posZ));
            else mc.getSoundHandler().playSound(new PositionedSoundRecord(soundEvent.getSoundName(), SoundCategory.MASTER, 1.0F, 0.9F + randomOffset(), false, 0, ISound.AttenuationType.NONE, 0F, 0F, 0F));
            lastPlayed = now;
        }
    }

    public static void playClickSound(ItemStack stackIn)
    {
        if (stackIn.isEmpty()) return;
        Item item = stackIn.getItem();
        if (ESSoundManager.soundItemMap.containsKey(item)) playSoundPlayer(ESSoundManager.soundCategoryMap.get(ESSoundManager.soundItemMap.get(item)));
        else if (ESSoundManager.soundFinalMap.containsKey(item)) playSoundPlayer(ESSoundManager.soundFinalMap.get(item), 2.0F, 0.4F);
        else playSoundPlayer(ESSoundEvents.pick_place_generic);
    }

    public static float randomOffset()
    {
        return Minecraft.getMinecraft().world != null ? Minecraft.getMinecraft().world.rand.nextFloat() * 0.2F : 0.1F;
    }
}