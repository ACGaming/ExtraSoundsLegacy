package mod.acgaming.extrasounds;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.oredict.OreDictionary;

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
                    playSound(SoundEvents.BLOCK_WOOD_HIT, 2.0F, 0.2F);
                }
                else if (checkOreDict(i, "dirt") || checkOreDict(i, "gravel"))
                {
                    playSound(SoundEvents.BLOCK_GRAVEL_HIT, 2.0F, 0.2F);
                }
                else if (checkOreDict(i, "sand"))
                {
                    playSound(SoundEvents.BLOCK_SAND_HIT, 2.0F, 0.4F);
                }
                else if (checkOreDict(i, "grass"))
                {
                    playSound(SoundEvents.BLOCK_GRASS_HIT, 2.0F, 0.6F);
                }
                else if (checkOreDictPrefix(i, "ingot") || checkOreDictPrefix(i, "nugget"))
                {
                    playSound(SoundEvents.BLOCK_ANVIL_PLACE, 2.0F, 0.1F);
                }
                else if (checkOreDictPrefix(i, "gem"))
                {
                    playSound(SoundEvents.BLOCK_NOTE_CHIME, 2.0F, 0.2F);
                }
                else if (checkOreDictPrefix(i, "dust"))
                {
                    playSound(SoundEvents.BLOCK_SAND_BREAK, 2.0F, 0.6F);
                }
                else
                {
                    playSound(SoundEvents.BLOCK_STONE_HIT, 2.0F, 0.4F);
                }
            }
        }
        else if (stackIn.getItem() instanceof ItemFood)
        {
            playSound(SoundEvents.BLOCK_SLIME_STEP, 2.0F, 0.2F);
        }
        else
        {
            playSound(SoundEvents.BLOCK_STONE_HIT, 2.0F, 0.4F);
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