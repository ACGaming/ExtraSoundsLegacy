package mod.acgaming.extrasounds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import mod.acgaming.extrasounds.sound.ESOreDictionary;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import mod.acgaming.extrasounds.sound.client.ESSoundEventsClient;
import mod.acgaming.extrasounds.sound.client.ESSoundManagerClient;

@Mod(modid = ExtraSounds.MODID, name = ExtraSounds.NAME, version = ExtraSounds.VERSION, acceptedMinecraftVersions = "[1.12.2]", dependencies = ExtraSounds.DEPENDENCIES)
public class ExtraSounds
{
    public static final String MODID = "extrasounds";
    public static final String NAME = "Extra Sounds";
    public static final String VERSION = "1.4.2";
    public static final String DEPENDENCIES = "required-after:mixinbooter;after:assetmover;after:jei;after:asmc;after:dsurround";
    public static final Logger LOGGER = LogManager.getLogger(NAME);
    public static boolean asmc = false;
    public static boolean assetmover = false;
    public static boolean dsurround = false;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        if (event.getSide() == Side.CLIENT) ESSoundManagerClient.preInit();
        ESSoundEvents.preInit();
        LOGGER.info("Extra Sounds pre-initialized");
    }

    @Mod.EventHandler
    public void initClient(FMLInitializationEvent event)
    {
        ESOreDictionary.init();
        ESSoundManager.init();
        LOGGER.info("Extra Sounds initialized");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        if (Loader.isModLoaded("asmc"))
        {
            asmc = true;
            LOGGER.info("ASMC detected, disabling respective sounds...");
        }
        if (Loader.isModLoaded("dsurround"))
        {
            dsurround = true;
            LOGGER.info("Dynamic Surroundings detected, disabling respective sounds...");
        }
        LOGGER.info("Extra Sounds post-initialized");
    }

    @Mod.EventHandler
    public void fmlConstruct(FMLConstructionEvent event)
    {
        try
        {
            Class.forName("com.cleanroommc.assetmover.AssetMoverAPI");
            assetmover = true;
            LOGGER.info("AssetMover detected, enabling compatibility...");
            if (event.getSide() == Side.CLIENT) ESSoundEventsClient.getBackportedSounds();
        }
        catch (ClassNotFoundException ignored)
        {
            LOGGER.warn("AssetMover not detected, some sounds will be missing...");
        }
    }
}