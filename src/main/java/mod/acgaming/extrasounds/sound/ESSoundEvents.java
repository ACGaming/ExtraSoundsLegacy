package mod.acgaming.extrasounds.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import mod.acgaming.extrasounds.ExtraSounds;

public class ESSoundEvents
{
    public static SoundEvent drop_item;
    public static SoundEvent open_gui;
    public static SoundEvent close_gui;
    public static SoundEvent chat_message;
    public static SoundEvent chat_mention;
    public static SoundEvent typing;
    public static SoundEvent hotbar_slot;
    public static SoundEvent open_book;
    public static SoundEvent close_book;
    public static SoundEvent creative_tab;
    public static SoundEvent scroll_page;
    public static SoundEvent swap_hands;

    public static void preInit()
    {
        drop_item = register("drop_item");
        open_gui = register("open_gui");
        close_gui = register("close_gui");
        chat_message = register("chat_message");
        chat_mention = register("chat_mention");
        typing = register("typing");
        hotbar_slot = register("hotbar_slot");
        open_book = register("open_book");
        close_book = register("close_book");
        creative_tab = register("creative_tab");
        scroll_page = register("scroll_page");
        swap_hands = register("swap_hands");

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
        event.getRegistry().register(drop_item);
        event.getRegistry().register(open_gui);
        event.getRegistry().register(close_gui);
        event.getRegistry().register(chat_message);
        event.getRegistry().register(chat_mention);
        event.getRegistry().register(typing);
        event.getRegistry().register(hotbar_slot);
        event.getRegistry().register(open_book);
        event.getRegistry().register(close_book);
        event.getRegistry().register(creative_tab);
        event.getRegistry().register(scroll_page);
        event.getRegistry().register(swap_hands);
    }
}