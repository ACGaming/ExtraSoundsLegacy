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
    @Config.Name("Toggles")
    public static SoundToggles soundToggles = new SoundToggles();

    @Config.Name("Volume")
    public static SoundVolume soundVolume = new SoundVolume();

    public static class SoundToggles
    {
        @Config.Name("Item Drop Sound")
        @Config.Comment("Play a sound when dropping items")
        public boolean esDropItemSound = true;

        @Config.Name("Item Pick/Place Sound")
        @Config.Comment("Play a sound when picking and placing items in GUIs")
        public boolean esPickPlaceSound = true;

        @Config.Name("Open/Close GUI Sound")
        @Config.Comment("Play a sound when opening and closing GUIs")
        public boolean esOpenCloseGUISound = true;

        @Config.Name("Chat Message Sound")
        @Config.Comment("Play a sound on chat messages")
        public boolean esMessageSound = true;

        @Config.Name("Chat Mention Sound")
        @Config.Comment("Play a sound on player name mentions in chat")
        public boolean esMentionSound = true;

        @Config.Name("Typing Sound")
        @Config.Comment("Play a sound when typing in text fields")
        public boolean esTypingSound = true;

        @Config.Name("Hotbar Slot Change Sound")
        @Config.Comment("Play a sound when changing hotbar slots")
        public boolean esHotbarSound = true;
    }

    public static class SoundVolume
    {
        @Config.Name("Item Drop Sound Volume")
        @Config.Comment("Volume of the sound when dropping items")
        public double esDropItemSoundVolume = 0.4;

        @Config.Name("Default Item Pick/Place Sound Volume")
        @Config.Comment("Volume of the fallback sound when picking and placing items in GUIs")
        public double esPickPlaceDefaultSound = 0.4;

        @Config.Name("Wood Item Pick/Place Sound Volume")
        @Config.Comment("Volume of the sound when picking and placing wooden items in GUIs")
        public double esPickPlaceWoodSound = 0.2;

        @Config.Name("Gravel Item Pick/Place Sound Volume")
        @Config.Comment("Volume of the sound when picking and placing gravel items in GUIs")
        public double esPickPlaceGravelSound = 0.2;

        @Config.Name("Sand Item Pick/Place Sound Volume")
        @Config.Comment("Volume of the sound when picking and placing sand items in GUIs")
        public double esPickPlaceSandSound = 0.4;

        @Config.Name("Grass Item Pick/Place Sound Volume")
        @Config.Comment("Volume of the sound when picking and placing grass items in GUIs")
        public double esPickPlaceGrassSound = 0.6;

        @Config.Name("Wool Item Pick/Place Sound Volume")
        @Config.Comment("Volume of the sound when picking and placing wool items in GUIs")
        public double esPickPlaceWoolSound = 0.4;

        @Config.Name("Metal Item Pick/Place Sound Volume")
        @Config.Comment("Volume of the sound when picking and placing metal items in GUIs")
        public double esPickPlaceMetalSound = 0.1;

        @Config.Name("Gem Item Pick/Place Sound Volume")
        @Config.Comment("Volume of the sound when picking and placing gem items in GUIs")
        public double esPickPlaceGemSound = 0.2;

        @Config.Name("Dust Item Pick/Place Sound Volume")
        @Config.Comment("Volume of the sound when picking and placing gem items in GUIs")
        public double esPickPlaceDustSound = 0.6;

        @Config.Name("Food Item Pick/Place Sound Volume")
        @Config.Comment("Volume of the sound when picking and placing food items in GUIs")
        public double esPickPlaceFoodSound = 0.2;

        @Config.Name("Open/Close GUI Sound Volume")
        @Config.Comment("Volume of the sound when opening and closing GUIs")
        public double esOpenCloseGUISoundVolume = 1.5;

        @Config.Name("Chat Message Sound Volume")
        @Config.Comment("Volume of the sound on chat messages")
        public double esMessageSoundVolume = 0.5;

        @Config.Name("Chat Mention Sound Volume")
        @Config.Comment("Volume of the sound on player name mentions in chat")
        public double esMentionSoundVolume = 0.7;

        @Config.Name("Typing Sound Volume")
        @Config.Comment("Volume of the sound when typing in text fields")
        public double esTypingSoundVolume = 0.2;

        @Config.Name("Hotbar Slot Change Sound Volume")
        @Config.Comment("Volume of the sound when changing hotbar slots")
        public double esHotbarSoundVolume = 0.15;
    }

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