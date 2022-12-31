package mod.acgaming.extrasounds.sound;

import java.util.*;
import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mod.acgaming.extrasounds.ExtraSounds;
import mod.acgaming.extrasounds.config.ESConfig;

public class ESSoundManager
{
    public static Map<String, SoundEvent> soundCategoryMap = new Object2ObjectOpenHashMap<>();
    public static Map<Item, String> soundItemMap = new Object2ObjectOpenHashMap<>();
    public static Map<Item, SoundEvent> soundFinalMap = new Object2ObjectOpenHashMap<>();
    private static long lastPlayed = System.currentTimeMillis();

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
        if (ExtraSounds.assetmover) soundCategoryMap.put("gem", ESSoundEvents.pick_place_gem);
        else soundCategoryMap.put("gem", ESSoundEvents.pick_place_generic);
        soundCategoryMap.put("grass", ESSoundEvents.pick_place_grass);
        soundCategoryMap.put("gravel", ESSoundEvents.pick_place_gravel);
        if (ExtraSounds.assetmover) soundCategoryMap.put("ingot", ESSoundEvents.pick_place_ingot);
        else soundCategoryMap.put("ingot", ESSoundEvents.pick_place_generic);
        if (ExtraSounds.assetmover) soundCategoryMap.put("nugget", ESSoundEvents.pick_place_nugget);
        else soundCategoryMap.put("nugget", ESSoundEvents.pick_place_generic);
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

    public static void playSoundPlayer(World world, EntityPlayer player, SoundEvent soundEvent, float pitch, float volume)
    {
        if (player == null) return;
        long now = System.currentTimeMillis();
        if (now - lastPlayed > 5)
        {
            world.playSound(player, player.getPosition(), soundEvent, SoundCategory.PLAYERS, volume, pitch - 0.1F + randomOffset(world));
            lastPlayed = now;
        }
    }

    public static void playSoundPlayer(World world, EntityPlayer player, SoundEvent soundEvent)
    {
        if (player == null) return;
        long now = System.currentTimeMillis();
        if (now - lastPlayed > 5)
        {
            world.playSound(player, player.getPosition(), soundEvent, SoundCategory.PLAYERS, 1.0F, 0.9F + randomOffset(world));
            lastPlayed = now;
        }
    }

    public static void playSoundWorld(World world, @Nullable EntityPlayer player, SoundEvent soundEvent, BlockPos blockPos, float pitch, float volume)
    {
        if (world == null) return;
        long now = System.currentTimeMillis();
        if (now - lastPlayed > 5)
        {
            world.playSound(player, blockPos, soundEvent, SoundCategory.BLOCKS, volume, pitch - 0.1F + randomOffset(world));
            lastPlayed = now;
        }
    }

    public static void playSoundWorld(World world, @Nullable EntityPlayer player, SoundEvent soundEvent, BlockPos blockPos)
    {
        if (world == null) return;
        long now = System.currentTimeMillis();
        if (now - lastPlayed > 5)
        {
            world.playSound(player, blockPos, soundEvent, SoundCategory.BLOCKS, 1.0F, 0.9F + randomOffset(world));
            lastPlayed = now;
        }
    }

    public static void playClickSound(World world, EntityPlayer player, ItemStack stackIn)
    {
        if (stackIn.isEmpty()) return;
        Item item = stackIn.getItem();
        if (ESSoundManager.soundItemMap.containsKey(item)) playSoundPlayer(world, player, ESSoundManager.soundCategoryMap.get(ESSoundManager.soundItemMap.get(item)));
        else if (ESSoundManager.soundFinalMap.containsKey(item)) playSoundPlayer(world, player, ESSoundManager.soundFinalMap.get(item), 2.0F, 0.4F);
        else playSoundPlayer(world, player, ESSoundEvents.pick_place_generic);
    }

    public static float randomOffset(World world)
    {
        return world != null ? world.rand.nextFloat() * 0.2F : 0.1F;
    }
}