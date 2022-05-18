package mod.acgaming.extrasounds;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.oredict.OreDictionary;

import mod.acgaming.extrasounds.config.ESConfig;

public class SoundManager
{
    public static Random random = new Random();
    private static long lastPlayed = System.currentTimeMillis();

    public static void playSound(SoundEvent snd, float pitch, float volume)
    {
        long now = System.currentTimeMillis();
        if (now - lastPlayed > 5)
        {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getRecord(snd, pitch - 0.1F + randomOffset(), volume));
            lastPlayed = now;
        }
    }

    public static float randomOffset()
    {
        return random.nextFloat() * 0.2F;
    }

    public static void playClickSound(ItemStack stackIn)
    {
        if (OreDictionary.getOreIDs(stackIn).length > 0)
        {
            for (int i : OreDictionary.getOreIDs(stackIn))
            {
                if (checkOreDictContains(i, "wood"))
                {
                    playSound(SoundEvents.BLOCK_WOOD_HIT, 2.0F, (float) ESConfig.soundVolume.esPickPlaceWoodSound);
                }
                else if (checkOreDict(i, "dirt") || checkOreDict(i, "gravel"))
                {
                    playSound(SoundEvents.BLOCK_GRAVEL_HIT, 2.0F, (float) ESConfig.soundVolume.esPickPlaceGravelSound);
                }
                else if (checkOreDict(i, "sand"))
                {
                    playSound(SoundEvents.BLOCK_SAND_HIT, 2.0F, (float) ESConfig.soundVolume.esPickPlaceSandSound);
                }
                else if (checkOreDict(i, "grass"))
                {
                    playSound(SoundEvents.BLOCK_GRASS_HIT, 2.0F, (float) ESConfig.soundVolume.esPickPlaceGrassSound);
                }
                else if (checkOreDict(i, "wool"))
                {
                    playSound(SoundEvents.BLOCK_CLOTH_HIT, 2.0F, (float) ESConfig.soundVolume.esPickPlaceWoolSound);
                }
                else if (checkOreDictPrefix(i, "ingot") || checkOreDictPrefix(i, "nugget"))
                {
                    playSound(SoundEvents.BLOCK_ANVIL_PLACE, 2.0F, (float) ESConfig.soundVolume.esPickPlaceMetalSound);
                }
                else if (checkOreDictPrefix(i, "gem"))
                {
                    playSound(SoundEvents.BLOCK_NOTE_CHIME, 2.0F, (float) ESConfig.soundVolume.esPickPlaceGemSound);
                }
                else if (checkOreDictPrefix(i, "dust"))
                {
                    playSound(SoundEvents.BLOCK_SAND_BREAK, 2.0F, (float) ESConfig.soundVolume.esPickPlaceDustSound);
                }
                else
                {
                    playSound(SoundEvents.BLOCK_STONE_HIT, 2.0F, (float) ESConfig.soundVolume.esPickPlaceDefaultSound);
                }
            }
        }
        else if (stackIn.getItem() instanceof ItemFood)
        {
            playSound(SoundEvents.BLOCK_SLIME_STEP, 2.0F, (float) ESConfig.soundVolume.esPickPlaceFoodSound);
        }
        else
        {
            playSound(SoundEvents.BLOCK_STONE_HIT, 2.0F, (float) ESConfig.soundVolume.esPickPlaceDefaultSound);
        }
    }

    public static boolean checkOreDict(int index, String keyword)
    {
        return OreDictionary.getOreName(index).toLowerCase().equals(keyword);
    }

    public static boolean checkOreDictContains(int index, String keyword)
    {
        return OreDictionary.getOreName(index).toLowerCase().contains(keyword);
    }

    public static boolean checkOreDictPrefix(int index, String keyword)
    {
        return OreDictionary.getOreName(index).toLowerCase().startsWith(keyword);
    }
}