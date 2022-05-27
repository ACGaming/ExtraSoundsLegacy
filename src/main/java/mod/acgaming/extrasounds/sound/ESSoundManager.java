package mod.acgaming.extrasounds.sound;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import mod.acgaming.extrasounds.config.ESConfig;

@SideOnly(Side.CLIENT)
public class ESSoundManager
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

    public static void playSound(SoundEvent snd)
    {
        long now = System.currentTimeMillis();
        if (now - lastPlayed > 5)
        {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getRecord(snd, 0.9F + randomOffset(), 1.0F));
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
                else if (checkOreDictPrefix(i, "snow"))
                {
                    playSound(SoundEvents.BLOCK_SNOW_HIT, 2.0F, (float) ESConfig.soundVolume.esPickPlaceSnowSound);
                }
                else
                {
                    playSound(SoundEvents.BLOCK_STONE_HIT, 2.0F, (float) ESConfig.soundVolume.esPickPlaceDefaultSound);
                }
            }
        }
        else if (stackIn.getItem() instanceof ItemBlock)
        {
            ItemBlock itemBlock = (ItemBlock) stackIn.getItem();
            playSound(itemBlock.getBlock().getSoundType().getHitSound(), 2.0F, (float) ESConfig.soundVolume.esPickPlaceDefaultSound);
        }
        else if (stackIn.getItem() instanceof ItemFood)
        {
            playSound(SoundEvents.BLOCK_SLIME_STEP, 2.0F, (float) ESConfig.soundVolume.esPickPlaceFoodSound);
        }
        else if (stackIn.getItem() instanceof ItemArmor)
        {
            if (((ItemArmor) stackIn.getItem()).getArmorMaterial().equals(ItemArmor.ArmorMaterial.LEATHER))
            {
                playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F, (float) ESConfig.soundVolume.esPickPlaceArmorSound);
            }
            else if (((ItemArmor) stackIn.getItem()).getArmorMaterial().equals(ItemArmor.ArmorMaterial.CHAIN))
            {
                playSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0F, (float) ESConfig.soundVolume.esPickPlaceArmorSound);
            }
            else if (((ItemArmor) stackIn.getItem()).getArmorMaterial().equals(ItemArmor.ArmorMaterial.GOLD))
            {
                playSound(SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0F, (float) ESConfig.soundVolume.esPickPlaceArmorSound);
            }
            else if (((ItemArmor) stackIn.getItem()).getArmorMaterial().equals(ItemArmor.ArmorMaterial.IRON))
            {
                playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, (float) ESConfig.soundVolume.esPickPlaceArmorSound);
            }
            else if (((ItemArmor) stackIn.getItem()).getArmorMaterial().equals(ItemArmor.ArmorMaterial.DIAMOND))
            {
                playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, (float) ESConfig.soundVolume.esPickPlaceArmorSound);
            }
            else
            {
                playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.0F, (float) ESConfig.soundVolume.esPickPlaceArmorSound);
            }
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