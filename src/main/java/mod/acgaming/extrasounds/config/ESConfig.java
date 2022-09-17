package mod.acgaming.extrasounds.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import mod.acgaming.extrasounds.ExtraSounds;
import mod.acgaming.extrasounds.sound.ESSoundManager;

@Config(modid = ExtraSounds.MODID, name = "ExtraSounds")
public class ESConfig
{
    @Config.Name("Toggles")
    public static SoundToggles soundToggles = new SoundToggles();

    @Config.Name("Volume")
    public static SoundVolume soundVolume = new SoundVolume();

    @Config.Name("Pick/Place Categories")
    public static SoundCategories soundCategories = new SoundCategories();

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

        @Config.Name("Scroll Sound")
        @Config.Comment("Play a sound when scrolling creative rows or JEI pages")
        public boolean esScrollSound = true;

        @Config.Name("Swap Hands Sound")
        @Config.Comment("Play a sound when swapping hands to hold an item")
        public boolean esSwapHandsSound = true;

        @Config.Name("Pull Bow Sound")
        @Config.Comment("Play a sound when pulling a bow")
        public boolean esPullBowSound = true;

        @Config.Name("Ender Pearl Teleport Sound")
        @Config.Comment("Play a sound when teleporting via ender pearls")
        public boolean esEnderPearlTeleportSound = true;

        @Config.Name("Place Boat Sound")
        @Config.Comment("Play a sound when placing a boat")
        public boolean esPlaceBoatSound = true;

        @Config.Name("Place Minecart Sound")
        @Config.Comment("Play a sound when placing a minecart")
        public boolean esPlaceMinecartSound = true;

        @Config.Name("Jukebox Interact Sound")
        @Config.Comment("Play a sound when interacting with a jukebox")
        public boolean esJukeboxInteractSound = true;

        @Config.Name("Flower Pot Plant Sound")
        @Config.Comment("Play a sound when planting flowers in a flower pot")
        public boolean esFlowerPotPlantSound = true;

        @Config.Name("Spawn Egg Sound")
        @Config.Comment("Play a sound when spawning an entity with a spawn egg")
        public boolean esUseSpawnEggSound = true;

        @Config.Name("Bonemeal Sound")
        @Config.Comment("Play a sound when applying bonemeal")
        public boolean esBonemealSound = true;

        @Config.Name("Spawner Sound")
        @Config.Comment("Play a sound when mob spawners spawn an entity")
        public boolean esSpawnerSound = true;

        @Config.Name("Plant Crops Sound")
        @Config.Comment("Play a sound when planting a crop")
        public boolean esCropSound = true;

        @Config.Name("Nether Portal Sound")
        @Config.Comment("Play a sound when a nether portal is constructed")
        public boolean esNetherPortalSound = true;

        @Config.Name("Crafting Sound")
        @Config.Comment("Play a sound when taking the craft result")
        public boolean esCraftingSound = true;

        @Config.Name("Beacon Sound")
        @Config.Comment("Play a sound when activating, deactivating and running a beacon")
        public boolean esBeaconSound = true;
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

    public static class SoundCategories
    {
        @Config.Name("Pick/Place Sounds")
        @Config.Comment({
            "Categories of sounds when picking and placing items in GUIs",
            "Available categories: wood, dirt, gravel, sand, grass, wool, snow, ingot, nugget, gem, dust",
            "Syntax: CATEGORY;REGISTRY_NAME",
            "Use * for wildcards"
        })
        public String[] soundArray = new String[]
            {
                "wood;ore:stickWood",
                "ingot;ore:ingot*",
                "nugget;ore:nugget*",
                "gem;ore:gem*",
                "dust;ore:dust*"
            };
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
                ESSoundManager.initSoundItemMap();
                ExtraSounds.LOGGER.info(ExtraSounds.NAME + " config reloaded!");
            }
        }
    }
}