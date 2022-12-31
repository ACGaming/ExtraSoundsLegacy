package mod.acgaming.extrasounds.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import mod.acgaming.extrasounds.ExtraSounds;

public class ESSoundEvents
{
    public static SoundEvent beacon_activate;
    public static SoundEvent beacon_ambient;
    public static SoundEvent beacon_deactivate;
    public static SoundEvent beacon_power;
    public static SoundEvent bonemeal;
    public static SoundEvent chat_mention;
    public static SoundEvent chat_message;
    public static SoundEvent close_book;
    public static SoundEvent close_gui;
    public static SoundEvent crafting;
    public static SoundEvent creative_tab;
    public static SoundEvent drop_item;
    public static SoundEvent effect_add_negative;
    public static SoundEvent effect_add_positive;
    public static SoundEvent effect_remove_negative;
    public static SoundEvent effect_remove_positive;
    public static SoundEvent hotbar_slot;
    public static SoundEvent jukebox_eject;
    public static SoundEvent jukebox_insert;
    public static SoundEvent mob_spawner;
    public static SoundEvent nether_portal_spawn;
    public static SoundEvent open_book;
    public static SoundEvent open_gui;
    public static SoundEvent pick_place_dirt;
    public static SoundEvent pick_place_dust;
    public static SoundEvent pick_place_food;
    public static SoundEvent pick_place_gem;
    public static SoundEvent pick_place_generic;
    public static SoundEvent pick_place_grass;
    public static SoundEvent pick_place_gravel;
    public static SoundEvent pick_place_ingot;
    public static SoundEvent pick_place_nugget;
    public static SoundEvent pick_place_sand;
    public static SoundEvent pick_place_snow;
    public static SoundEvent pick_place_stone;
    public static SoundEvent pick_place_wood;
    public static SoundEvent pick_place_wool;
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
        beacon_activate = register("beacon_activate");
        beacon_ambient = register("beacon_ambient");
        beacon_deactivate = register("beacon_deactivate");
        beacon_power = register("beacon_power");
        bonemeal = register("bonemeal");
        chat_mention = register("chat_mention");
        chat_message = register("chat_message");
        close_book = register("close_book");
        close_gui = register("close_gui");
        crafting = register("crafting");
        creative_tab = register("creative_tab");
        drop_item = register("drop_item");
        effect_add_negative = register("effect_add_negative");
        effect_add_positive = register("effect_add_positive");
        effect_remove_negative = register("effect_remove_negative");
        effect_remove_positive = register("effect_remove_positive");
        hotbar_slot = register("hotbar_slot");
        jukebox_eject = register("jukebox_eject");
        jukebox_insert = register("jukebox_insert");
        mob_spawner = register("mob_spawner");
        nether_portal_spawn = register("nether_portal_spawn");
        open_book = register("open_book");
        open_gui = register("open_gui");
        pick_place_dirt = register("pick_place_dirt");
        pick_place_dust = register("pick_place_dust");
        pick_place_food = register("pick_place_food");
        pick_place_gem = register("pick_place_gem");
        pick_place_generic = register("pick_place_generic");
        pick_place_grass = register("pick_place_grass");
        pick_place_gravel = register("pick_place_gravel");
        pick_place_ingot = register("pick_place_ingot");
        pick_place_nugget = register("pick_place_nugget");
        pick_place_sand = register("pick_place_sand");
        pick_place_snow = register("pick_place_snow");
        pick_place_stone = register("pick_place_stone");
        pick_place_wood = register("pick_place_wood");
        pick_place_wool = register("pick_place_wool");
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
    }

    public static SoundEvent register(String name)
    {
        ResourceLocation loc = new ResourceLocation(ExtraSounds.MODID, name);
        return new SoundEvent(loc).setRegistryName(loc);
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<SoundEvent> event)
    {
        event.getRegistry().register(beacon_activate);
        event.getRegistry().register(beacon_ambient);
        event.getRegistry().register(beacon_deactivate);
        event.getRegistry().register(beacon_power);
        event.getRegistry().register(bonemeal);
        event.getRegistry().register(chat_mention);
        event.getRegistry().register(chat_message);
        event.getRegistry().register(close_book);
        event.getRegistry().register(close_gui);
        event.getRegistry().register(crafting);
        event.getRegistry().register(creative_tab);
        event.getRegistry().register(drop_item);
        event.getRegistry().register(effect_add_negative);
        event.getRegistry().register(effect_add_positive);
        event.getRegistry().register(effect_remove_negative);
        event.getRegistry().register(effect_remove_positive);
        event.getRegistry().register(hotbar_slot);
        event.getRegistry().register(jukebox_eject);
        event.getRegistry().register(jukebox_insert);
        event.getRegistry().register(nether_portal_spawn);
        event.getRegistry().register(open_book);
        event.getRegistry().register(open_gui);
        event.getRegistry().register(pick_place_dirt);
        event.getRegistry().register(pick_place_dust);
        event.getRegistry().register(pick_place_food);
        event.getRegistry().register(pick_place_gem);
        event.getRegistry().register(pick_place_generic);
        event.getRegistry().register(pick_place_grass);
        event.getRegistry().register(pick_place_gravel);
        event.getRegistry().register(pick_place_ingot);
        event.getRegistry().register(pick_place_nugget);
        event.getRegistry().register(pick_place_sand);
        event.getRegistry().register(pick_place_snow);
        event.getRegistry().register(pick_place_stone);
        event.getRegistry().register(pick_place_wood);
        event.getRegistry().register(pick_place_wool);
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