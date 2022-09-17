package mod.acgaming.extrasounds.sound;

import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mod.acgaming.extrasounds.ExtraSounds;
import mod.acgaming.extrasounds.config.ESConfig;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ESSoundManager
{
    public static Map<Item, String> soundItemMap = new Object2ObjectOpenHashMap<>();
    public static Map<String, SoundEvent> soundCategoryMap = new Object2ObjectOpenHashMap<>();
    private static long lastPlayed = System.currentTimeMillis();

    public static void init()
    {
        initSoundCategoryMap();
        initSoundItemMap();
    }

    public static void initSoundCategoryMap()
    {
        soundCategoryMap.clear();

        soundCategoryMap.put("wood", SoundEvents.BLOCK_WOOD_HIT);
        soundCategoryMap.put("dirt", SoundEvents.BLOCK_GRAVEL_HIT);
        soundCategoryMap.put("gravel", SoundEvents.BLOCK_GRAVEL_HIT);
        soundCategoryMap.put("sand", SoundEvents.BLOCK_SAND_HIT);
        soundCategoryMap.put("grass", SoundEvents.BLOCK_GRASS_HIT);
        soundCategoryMap.put("wool", SoundEvents.BLOCK_CLOTH_HIT);
        soundCategoryMap.put("snow", SoundEvents.BLOCK_SNOW_HIT);
        soundCategoryMap.put("ingot", SoundEvents.BLOCK_ANVIL_PLACE);
        soundCategoryMap.put("nugget", SoundEvents.BLOCK_ANVIL_PLACE);
        soundCategoryMap.put("gem", SoundEvents.BLOCK_NOTE_CHIME);
        soundCategoryMap.put("dust", SoundEvents.BLOCK_SAND_BREAK);
    }

    public static void initSoundItemMap()
    {
        soundItemMap.clear();

        try
        {
            // ITERATE OVER CONFIG ENTRIES
            for (String entry : ESConfig.soundCategories.soundArray)
            {
                String[] splitsConfig = entry.split(";");
                String sCategory = splitsConfig[0];
                String sResLoc = splitsConfig[1];

                String[] splitsRegistry = splitsConfig[1].split(":");
                String sRegNamespace = splitsRegistry[0];
                String sRegPath = splitsRegistry[1];

                // ORE DICTIONARY
                if (sRegNamespace.equals("ore"))
                {
                    // ITERATE OVER ORE DICTIONARY NAMES
                    for (String oreName : OreDictionary.getOreNames())
                    {
                        if ((sRegPath.contains("*") && oreName.contains(sRegPath.replace("*", ""))) || oreName.equals(sRegPath))
                        {
                            //ITERATE OVER ORE DICTIONARY ENTRIES
                            for (ItemStack oreStack : OreDictionary.getOres(oreName))
                            {
                                ExtraSounds.LOGGER.info("Adding item " + oreStack.getItem().getRegistryName() + " to category " + sCategory);
                                soundItemMap.put(oreStack.getItem(), sCategory);
                            }
                        }
                    }
                }
                // REGULAR ITEM
                else
                {
                    ResourceLocation resLoc = new ResourceLocation(sResLoc);
                    if (ForgeRegistries.ITEMS.containsKey(resLoc))
                    {
                        ExtraSounds.LOGGER.info("Adding item " + ForgeRegistries.ITEMS.getValue(resLoc).getRegistryName() + " to category " + sCategory);
                        soundItemMap.put(ForgeRegistries.ITEMS.getValue(resLoc), sCategory);
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void playSoundPlayer(SoundEvent soundEvent, float pitch, float volume)
    {
        long now = System.currentTimeMillis();
        if (now - lastPlayed > 5)
        {
            Minecraft.getMinecraft().world.playSound(Minecraft.getMinecraft().player, Minecraft.getMinecraft().player.getPosition(), soundEvent, SoundCategory.PLAYERS, volume, pitch - 0.1F + randomOffset());
            lastPlayed = now;
        }
    }

    public static void playSoundPlayer(SoundEvent soundEvent)
    {
        long now = System.currentTimeMillis();
        if (now - lastPlayed > 5)
        {
            Minecraft.getMinecraft().world.playSound(Minecraft.getMinecraft().player, Minecraft.getMinecraft().player.getPosition(), soundEvent, SoundCategory.PLAYERS, 1.0F, 0.9F + randomOffset());
            lastPlayed = now;
        }
    }

    public static void playSoundWorld(SoundEvent soundEvent, BlockPos blockPos, float pitch, float volume)
    {
        long now = System.currentTimeMillis();
        if (now - lastPlayed > 5)
        {
            Minecraft.getMinecraft().world.playSound(Minecraft.getMinecraft().player, blockPos, soundEvent, SoundCategory.BLOCKS, volume, pitch - 0.1F + randomOffset());
            lastPlayed = now;
        }
    }

    public static void playSoundWorld(SoundEvent soundEvent, BlockPos blockPos)
    {
        long now = System.currentTimeMillis();
        if (now - lastPlayed > 5)
        {
            Minecraft.getMinecraft().world.playSound(Minecraft.getMinecraft().player, blockPos, soundEvent, SoundCategory.BLOCKS, 1.0F, 0.9F + randomOffset());
            lastPlayed = now;
        }
    }

    public static float randomOffset()
    {
        return Minecraft.getMinecraft().world.rand.nextFloat() * 0.2F;
    }

    public static void playClickSound(ItemStack stackIn)
    {
        Item item = stackIn.getItem();

        if (item instanceof ItemBlock)
        {
            playSoundPlayer(((ItemBlock) stackIn.getItem()).getBlock().getSoundType().getHitSound(), 2.0F, (float) ESConfig.soundVolume.esPickPlaceDefaultSound);
        }
        else if (item instanceof ItemFood)
        {
            playSoundPlayer(SoundEvents.BLOCK_SLIME_STEP, 2.0F, (float) ESConfig.soundVolume.esPickPlaceFoodSound);
        }
        else if (item instanceof ItemArmor)
        {
            playSoundPlayer(((ItemArmor) stackIn.getItem()).getArmorMaterial().getSoundEvent(), 1.0F, (float) ESConfig.soundVolume.esPickPlaceArmorSound);
        }
        else if (soundItemMap.containsKey(item))
        {
            playSoundPlayer(soundCategoryMap.get(soundItemMap.get(item)), 2.0F, (float) ESConfig.soundVolume.esPickPlaceDefaultSound);
        }
        else
        {
            playSoundPlayer(SoundEvents.BLOCK_STONE_HIT, 2.0F, (float) ESConfig.soundVolume.esPickPlaceDefaultSound);
        }
    }
}