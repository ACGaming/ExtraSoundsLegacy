package mod.acgaming.extrasounds.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import mod.acgaming.extrasounds.ExtraSounds;

public class ESSoundEvents
{
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
    public static SoundEvent plant_flower_pot;
    public static SoundEvent pull_bow;
    public static SoundEvent scroll_page;
    public static SoundEvent swap_hands;
    public static SoundEvent teleport_ender_pearl;
    public static SoundEvent typing;
    public static SoundEvent use_spawn_egg;

    public static void preInit()
    {
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
        plant_flower_pot = register("plant_flower_pot");
        pull_bow = register("pull_bow");
        scroll_page = register("scroll_page");
        swap_hands = register("swap_hands");
        teleport_ender_pearl = register("teleport_ender_pearl");
        typing = register("typing");
        use_spawn_egg = register("use_spawn_egg");

        MinecraftForge.EVENT_BUS.register(ESSoundEvents.class);
    }

    public static SoundEvent register(String name)
    {
        ResourceLocation loc = new ResourceLocation(ExtraSounds.MODID, name);
        return new SoundEvent(loc).setRegistryName(loc);
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<SoundEvent> event)
    {
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
        event.getRegistry().register(plant_flower_pot);
        event.getRegistry().register(pull_bow);
        event.getRegistry().register(scroll_page);
        event.getRegistry().register(swap_hands);
        event.getRegistry().register(teleport_ender_pearl);
        event.getRegistry().register(typing);
        event.getRegistry().register(use_spawn_egg);
    }
}