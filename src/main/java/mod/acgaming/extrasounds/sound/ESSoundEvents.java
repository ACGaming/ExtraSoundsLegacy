package mod.acgaming.extrasounds.sound;

import java.util.Map;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.cleanroommc.assetmover.AssetMoverAPI;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mod.acgaming.extrasounds.ExtraSounds;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ESSoundEvents
{
    public static SoundEvent beacon_ambient;
    public static SoundEvent beacon_activate;
    public static SoundEvent beacon_deactivate;
    public static SoundEvent beacon_power;
    public static SoundEvent chat_mention;
    public static SoundEvent chat_message;
    public static SoundEvent close_book;
    public static SoundEvent close_gui;
    public static SoundEvent creative_tab;
    public static SoundEvent drop_item;
    public static SoundEvent hotbar_slot;
    public static SoundEvent jukebox_eject;
    public static SoundEvent jukebox_insert;
    public static SoundEvent open_book;
    public static SoundEvent open_gui;
    public static SoundEvent place_boat;
    public static SoundEvent place_minecart;
    public static SoundEvent plant_crop;
    public static SoundEvent plant_flower_pot;
    public static SoundEvent plant_netherwart;
    public static SoundEvent pull_bow;
    public static SoundEvent scroll_page;
    public static SoundEvent swap_hands;
    public static SoundEvent teleport_ender_pearl;
    public static SoundEvent typing;
    public static SoundEvent use_spawn_egg;

    public static void preInit()
    {
        getBackportedSounds();

        beacon_ambient = register("beacon_ambient");
        beacon_activate = register("beacon_activate");
        beacon_deactivate = register("beacon_deactivate");
        beacon_power = register("beacon_power");
        chat_mention = register("chat_mention");
        chat_message = register("chat_message");
        close_book = register("close_book");
        close_gui = register("close_gui");
        creative_tab = register("creative_tab");
        drop_item = register("drop_item");
        hotbar_slot = register("hotbar_slot");
        jukebox_eject = register("jukebox_eject");
        jukebox_insert = register("jukebox_insert");
        open_book = register("open_book");
        open_gui = register("open_gui");
        place_boat = register("place_boat");
        place_minecart = register("place_minecart");
        plant_crop = register("plant_crop");
        plant_flower_pot = register("plant_flower_pot");
        plant_netherwart = register("plant_netherwart");
        pull_bow = register("pull_bow");
        scroll_page = register("scroll_page");
        swap_hands = register("swap_hands");
        teleport_ender_pearl = register("teleport_ender_pearl");
        typing = register("typing");
        use_spawn_egg = register("use_spawn_egg");

        MinecraftForge.EVENT_BUS.register(ESSoundEvents.class);
    }

    public static void getBackportedSounds()
    {
        Map<String, String> backportMap = new Object2ObjectOpenHashMap<>();

        // PLANT CROP
        backportMap.put("assets/minecraft/sounds/item/plant/crop1.ogg", "assets/extrasounds/sounds/plant/crop1.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/crop2.ogg", "assets/extrasounds/sounds/plant/crop2.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/crop3.ogg", "assets/extrasounds/sounds/plant/crop3.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/crop4.ogg", "assets/extrasounds/sounds/plant/crop4.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/crop5.ogg", "assets/extrasounds/sounds/plant/crop5.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/crop6.ogg", "assets/extrasounds/sounds/plant/crop6.ogg");

        // PLANT NETHERWART
        backportMap.put("assets/minecraft/sounds/item/plant/netherwart1.ogg", "assets/extrasounds/sounds/plant/netherwart1.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/netherwart2.ogg", "assets/extrasounds/sounds/plant/netherwart2.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/netherwart3.ogg", "assets/extrasounds/sounds/plant/netherwart3.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/netherwart4.ogg", "assets/extrasounds/sounds/plant/netherwart4.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/netherwart5.ogg", "assets/extrasounds/sounds/plant/netherwart5.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/netherwart6.ogg", "assets/extrasounds/sounds/plant/netherwart6.ogg");

        // BEACON
        backportMap.put("assets/minecraft/sounds/block/beacon/ambient.ogg", "assets/extrasounds/sounds/block/beacon/ambient.ogg");
        backportMap.put("assets/minecraft/sounds/block/beacon/activate.ogg", "assets/extrasounds/sounds/block/beacon/activate.ogg");
        backportMap.put("assets/minecraft/sounds/block/beacon/deactivate.ogg", "assets/extrasounds/sounds/block/beacon/deactivate.ogg");
        backportMap.put("assets/minecraft/sounds/block/beacon/power1.ogg", "assets/extrasounds/sounds/block/beacon/power1.ogg");
        backportMap.put("assets/minecraft/sounds/block/beacon/power2.ogg", "assets/extrasounds/sounds/block/beacon/power2.ogg");
        backportMap.put("assets/minecraft/sounds/block/beacon/power3.ogg", "assets/extrasounds/sounds/block/beacon/power3.ogg");

        AssetMoverAPI.fromMinecraft("1.19.2", backportMap);
    }

    public static SoundEvent register(String name)
    {
        ResourceLocation loc = new ResourceLocation(ExtraSounds.MODID, name);
        return new SoundEvent(loc).setRegistryName(loc);
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<SoundEvent> event)
    {
        event.getRegistry().register(beacon_ambient);
        event.getRegistry().register(beacon_activate);
        event.getRegistry().register(beacon_deactivate);
        event.getRegistry().register(beacon_power);
        event.getRegistry().register(chat_mention);
        event.getRegistry().register(chat_message);
        event.getRegistry().register(close_book);
        event.getRegistry().register(close_gui);
        event.getRegistry().register(creative_tab);
        event.getRegistry().register(drop_item);
        event.getRegistry().register(hotbar_slot);
        event.getRegistry().register(jukebox_eject);
        event.getRegistry().register(jukebox_insert);
        event.getRegistry().register(open_book);
        event.getRegistry().register(open_gui);
        event.getRegistry().register(place_boat);
        event.getRegistry().register(place_minecart);
        event.getRegistry().register(plant_crop);
        event.getRegistry().register(plant_flower_pot);
        event.getRegistry().register(plant_netherwart);
        event.getRegistry().register(pull_bow);
        event.getRegistry().register(scroll_page);
        event.getRegistry().register(swap_hands);
        event.getRegistry().register(teleport_ender_pearl);
        event.getRegistry().register(typing);
        event.getRegistry().register(use_spawn_egg);
    }
}