package mod.acgaming.extrasounds.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import mod.acgaming.extrasounds.ExtraSounds;

@Config(modid = ExtraSounds.MODID, name = "ExtraSounds")
public class ESConfig
{
    @Config.Name("Item Drop Sound")
    @Config.Comment("Play a sound when dropping items")
    public static boolean esDropItemSound = true;

    @Config.Name("Item Pick/Place Sound")
    @Config.Comment("Play a sound when picking and placing items in GUIs")
    public static boolean esSlotClickSound = true;

    @Config.Name("Open/Close GUI Sound")
    @Config.Comment("Play a sound when opening and closing GUIs")
    public static boolean esOpenCloseGUISound = true;

    @Config.Name("Chat Message Sound")
    @Config.Comment("Play a sound on chat messages")
    public static boolean esMessageSound = true;

    @Config.Name("Chat Mention Sound")
    @Config.Comment("Play a sound on player name mentions in chat")
    public static boolean esMentionSound = true;

    @Config.Name("Typing Sound")
    @Config.Comment("Play a sound when typing in text fields")
    public static boolean esTypingSound = true;

    @Config.Name("Hotbar Slot Change Sound")
    @Config.Comment("Play a sound when changing hotbar slots")
    public static boolean esHotbarSound = true;

    @Mod.EventBusSubscriber(modid = ExtraSounds.MODID)
    public static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(ExtraSounds.MODID))
            {
                ConfigManager.sync(ExtraSounds.MODID, Config.Type.INSTANCE);
            }
        }
    }
}