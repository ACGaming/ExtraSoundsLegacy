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

        @Config.Name("Open/Close Book Sound")
        @Config.Comment("Play a sound when opening and closing a writable book or recipe book")
        public boolean esOpenCloseBookSound = true;

        @Config.Name("Creative Tab Sound")
        @Config.Comment("Play a sound when selecting a creative tab")
        public boolean esCreativeTabSound = true;

        @Config.Name("Scroll JEI Page Sound")
        @Config.Comment("Play a sound when scrolling JEI pages")
        public boolean esScrollJEIPageSound = true;

        @Config.Name("Swap Hands Sound")
        @Config.Comment("Play a sound when swapping hands to hold an item")
        public boolean esSwapHandsSound = true;
    }

    public static class SoundVolume
    {
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
        @Config.Comment("Volume of the sound when picking and placing dust items in GUIs")
        public double esPickPlaceDustSound = 0.6;

        @Config.Name("Snow Item Pick/Place Sound Volume")
        @Config.Comment("Volume of the sound when picking and placing snow items in GUIs")
        public double esPickPlaceSnowSound = 0.6;

        @Config.Name("Armor Item Pick/Place Sound Volume")
        @Config.Comment("Volume of the sound when picking and placing armor items in GUIs")
        public double esPickPlaceArmorSound = 0.8;

        @Config.Name("Food Item Pick/Place Sound Volume")
        @Config.Comment("Volume of the sound when picking and placing food items in GUIs")
        public double esPickPlaceFoodSound = 0.2;
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