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
    @Config.Name("Sound Toggles")
    public static SoundToggles soundToggles = new SoundToggles();

    @Config.Name("Pick/Place Categories")
    public static SoundCategories soundCategories = new SoundCategories();

    @Config.Name("Miscellaneous Settings")
    public static MiscSettings miscSettings = new MiscSettings();

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

        @Config.Name("Potion Sound")
        @Config.Comment("Play a sound when potion effects get added or removed from the player")
        public boolean esPotionSound = false;

        @Config.Name("Combination Step Sound")
        @Config.Comment
            ({
                "Recreates the combination step sound introduced in snapshot 23w12a",
                "Plays an additional muffled step sound for blocks beneath carpets and snow"
            })
        public boolean esCombinationStepSound = true;
    }

    public static class SoundCategories
    {
        @Config.Name("Pick/Place Sounds")
        @Config.Comment
            ({
                "Categories of sounds when picking and placing items in GUIs",
                "Available categories: dirt, dust, gem, grass, gravel, ingot, nugget, sand, snow, stone, wood, wool",
                "Syntax: CATEGORY;REGISTRY_NAME",
                "Use * for ore dictionary wildcards",
                "Examples | ingot;ore:example_ore | gem;mod_id:example_item"
            })
        public String[] soundArray = new String[]
            {
                "dust;ore:dust*",
                "gem;ore:gem*",
                "ingot;ore:ingot*",
                "nugget;ore:nugget*",
                "snow;ore:snow",
                "stone;ore:stone*",
                "gem;minecraft:diamond_axe",
                "gem;minecraft:diamond_hoe",
                "gem;minecraft:diamond_pickaxe",
                "gem;minecraft:diamond_shovel",
                "gem;minecraft:diamond_sword",
                "ingot;minecraft:golden_axe",
                "ingot;minecraft:golden_hoe",
                "ingot;minecraft:golden_pickaxe",
                "ingot;minecraft:golden_shovel",
                "ingot;minecraft:golden_sword",
                "ingot;minecraft:iron_axe",
                "ingot;minecraft:iron_hoe",
                "ingot;minecraft:iron_pickaxe",
                "ingot;minecraft:iron_shovel",
                "ingot;minecraft:iron_sword",
                "stone;minecraft:stone_axe",
                "stone;minecraft:stone_hoe",
                "stone;minecraft:stone_pickaxe",
                "stone;minecraft:stone_shovel",
                "stone;minecraft:stone_sword",
                "wood;minecraft:wooden_axe",
                "wood;minecraft:wooden_hoe",
                "wood;minecraft:wooden_pickaxe",
                "wood;minecraft:wooden_shovel",
                "wood;minecraft:wooden_sword"
            };
    }

    public static class MiscSettings
    {
        @Config.Name("Increase Sound Channels")
        @Config.Comment
            ({
                "Increase sound channels to improve audio playback",
                "Disable this if you're facing sound issues or have another mod installed handling this"
            })
        public boolean esSoundChannels = true;
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