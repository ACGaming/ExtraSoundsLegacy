package mod.acgaming.extrasounds.sound;

import java.util.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
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
    public static Map<String, SoundEvent> soundCategoryMap = new Object2ObjectOpenHashMap<>();
    public static Map<Item, String> soundItemMap = new Object2ObjectOpenHashMap<>();
    public static Map<Item, SoundEvent> soundFinalMap = new Object2ObjectOpenHashMap<>();
    private static long lastPlayed = System.currentTimeMillis();

    public static void preInit()
    {
        if (ESConfig.miscSettings.esSoundChannels)
        {
            SoundSystemConfig.setNumberNormalChannels(1024);
            SoundSystemConfig.setNumberStreamingChannels(32);
        }
    }

    public static void init()
    {
        initSoundCategoryMap();
        initSoundItemMap();
        initSoundFinalMap();
    }

    public static void initSoundCategoryMap()
    {
        soundCategoryMap.clear();

        soundCategoryMap.put("dirt", ESSoundEvents.pick_place_dirt);
        soundCategoryMap.put("dust", ESSoundEvents.pick_place_dust);
        soundCategoryMap.put("gem", ESSoundEvents.pick_place_gem);
        soundCategoryMap.put("grass", ESSoundEvents.pick_place_grass);
        soundCategoryMap.put("gravel", ESSoundEvents.pick_place_gravel);
        soundCategoryMap.put("ingot", ESSoundEvents.pick_place_ingot);
        soundCategoryMap.put("nugget", ESSoundEvents.pick_place_nugget);
        soundCategoryMap.put("sand", ESSoundEvents.pick_place_sand);
        soundCategoryMap.put("snow", ESSoundEvents.pick_place_snow);
        soundCategoryMap.put("stone", ESSoundEvents.pick_place_stone);
        soundCategoryMap.put("wood", ESSoundEvents.pick_place_wood);
        soundCategoryMap.put("wool", ESSoundEvents.pick_place_wool);
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

    public static void initSoundFinalMap()
    {
        soundFinalMap.clear();

        try
        {
            for (Item item : ForgeRegistries.ITEMS.getValuesCollection())
            {
                SoundEvent soundEvent = null;
                if (item instanceof ItemBlock) soundEvent = ((ItemBlock) item).getBlock().getSoundType().getPlaceSound();
                else if (item instanceof ItemFood) soundEvent = ESSoundEvents.pick_place_food;
                else if (item instanceof ItemArmor) soundEvent = ((ItemArmor) item).getArmorMaterial().getSoundEvent();
                else if (item == Items.WATER_BUCKET || item == Items.MILK_BUCKET) soundEvent = SoundEvents.ITEM_BUCKET_FILL;
                else if (item == Items.LAVA_BUCKET) soundEvent = SoundEvents.ITEM_BUCKET_FILL_LAVA;
                else if (item instanceof UniversalBucket)
                {
                    FluidStack fluidStack = ((UniversalBucket) item).getFluid(new ItemStack(item));
                    if (fluidStack != null) soundEvent = fluidStack.getFluid().getFillSound();
                }
                // CHECK ITEM'S RECIPE INGREDIENTS FOR KNOWN SOUND EVENTS
                else if (item.getRegistryName() != null && CraftingManager.REGISTRY.containsKey(item.getRegistryName()))
                {
                    IRecipe recipe = CraftingManager.REGISTRY.getObject(item.getRegistryName());
                    if (recipe != null)
                    {
                        List<Ingredient> ingredientList = new ArrayList<>(recipe.getIngredients());
                        List<ItemStack> ingredientStackList = new ArrayList<>();
                        // GET ALL INGREDIENT ITEM STACKS
                        for (Ingredient ingredient : ingredientList) ingredientStackList.addAll(Arrays.asList(ingredient.getMatchingStacks()));
                        if (ingredientStackList.size() > 0)
                        {
                            // SORT LIST BY FREQUENCY
                            ingredientStackList.sort(Comparator.comparing(i -> Collections.frequency(ingredientStackList, i)).reversed());
                            // GET MOST FREQUENT ITEM (BEST EFFORT)
                            Item ingredientItem = ingredientStackList.get(0).getItem();
                            if (soundItemMap.containsKey(ingredientItem)) soundEvent = soundCategoryMap.get(soundItemMap.get(ingredientItem));
                            else if (ingredientItem instanceof ItemBlock) soundEvent = ((ItemBlock) ingredientItem).getBlock().getSoundType().getPlaceSound();
                        }
                    }
                }
                if (soundEvent != null) soundFinalMap.put(item, soundEvent);
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
        if (soundItemMap.containsKey(item)) playSoundPlayer(soundCategoryMap.get(soundItemMap.get(item)));
        else if (soundFinalMap.containsKey(item)) playSoundPlayer(soundFinalMap.get(item), 2.0F, 0.4F);
        else playSoundPlayer(ESSoundEvents.pick_place_generic);
    }
}