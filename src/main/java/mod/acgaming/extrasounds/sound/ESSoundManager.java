package mod.acgaming.extrasounds.sound;

import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
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
import paulscode.sound.SoundSystemConfig;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ESSoundManager
{
    public static Map<Item, String> soundItemMap = new Object2ObjectOpenHashMap<>();
    public static Map<String, SoundEvent> soundCategoryMap = new Object2ObjectOpenHashMap<>();
    private static long lastPlayed = System.currentTimeMillis();

    public static void preInit()
    {
        SoundSystemConfig.setNumberNormalChannels(1024);
        SoundSystemConfig.setNumberStreamingChannels(32);
    }

    public static void init()
    {
        initSoundCategoryMap();
        initSoundItemMap();
    }

    public static void initSoundCategoryMap()
    {
        soundCategoryMap.clear();

        soundCategoryMap.put("wood", ESSoundEvents.pick_place_wood);
        soundCategoryMap.put("dirt", ESSoundEvents.pick_place_dirt);
        soundCategoryMap.put("gravel", ESSoundEvents.pick_place_gravel);
        soundCategoryMap.put("sand", ESSoundEvents.pick_place_sand);
        soundCategoryMap.put("grass", ESSoundEvents.pick_place_grass);
        soundCategoryMap.put("wool", ESSoundEvents.pick_place_wool);
        soundCategoryMap.put("snow", ESSoundEvents.pick_place_snow);
        soundCategoryMap.put("ingot", ESSoundEvents.pick_place_ingot);
        soundCategoryMap.put("nugget", ESSoundEvents.pick_place_nugget);
        soundCategoryMap.put("gem", ESSoundEvents.pick_place_gem);
        soundCategoryMap.put("dust", ESSoundEvents.pick_place_dust);
    }

    @SuppressWarnings("ConstantConditions")
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
                                if (!oreStack.isEmpty() && !soundItemMap.containsKey(oreStack.getItem()))
                                {
                                    ExtraSounds.LOGGER.info("Adding item " + oreStack.getDisplayName() + " to category " + sCategory.toUpperCase());
                                    soundItemMap.put(oreStack.getItem(), sCategory);
                                }
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
                        ExtraSounds.LOGGER.info("Adding item " + new ItemStack(ForgeRegistries.ITEMS.getValue(resLoc)).getDisplayName() + " to category " + sCategory.toUpperCase());
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
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP player = mc.player;
            if (player != null)
            {
                mc.getSoundHandler().playSound(new PositionedSoundRecord(soundEvent.getSoundName(), SoundCategory.PLAYERS, volume, pitch, false, 0, ISound.AttenuationType.NONE, (float) player.posX, (float) player.posY + 32, (float) player.posZ));
            }
            else
            {
                mc.getSoundHandler().playSound(new PositionedSoundRecord(soundEvent.getSoundName(), SoundCategory.MASTER, volume, pitch, false, 0, ISound.AttenuationType.NONE, 0F, 0F, 0F));
            }
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
            if (player != null)
            {
                mc.getSoundHandler().playSound(new PositionedSoundRecord(soundEvent.getSoundName(), SoundCategory.PLAYERS, 1.0F, 1.0F, false, 0, ISound.AttenuationType.NONE, (float) player.posX, (float) player.posY + 32, (float) player.posZ));
            }
            else
            {
                mc.getSoundHandler().playSound(new PositionedSoundRecord(soundEvent.getSoundName(), SoundCategory.MASTER, 1.0F, 1.0F, false, 0, ISound.AttenuationType.NONE, 0F, 0F, 0F));
            }
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
        if (stackIn.isEmpty()) return;

        Item item = stackIn.getItem();

        if (item instanceof ItemBlock)
        {
            playSoundPlayer(((ItemBlock) stackIn.getItem()).getBlock().getSoundType().getPlaceSound(), 2.0F, 0.4F);
        }
        else if (item instanceof ItemFood)
        {
            playSoundPlayer(ESSoundEvents.pick_place_food);
        }
        else if (item instanceof ItemArmor)
        {
            playSoundPlayer(((ItemArmor) stackIn.getItem()).getArmorMaterial().getSoundEvent(), 1.0F, 0.8F);
        }
        else if (soundItemMap.containsKey(item))
        {
            playSoundPlayer(soundCategoryMap.get(soundItemMap.get(item)));
        }
        else
        {
            playSoundPlayer(ESSoundEvents.pick_place_generic);
        }
    }
}