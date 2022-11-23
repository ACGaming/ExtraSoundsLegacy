package mod.acgaming.extrasounds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import mod.acgaming.extrasounds.sound.ESOreDictionary;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;

@Mod(modid = ExtraSounds.MODID, name = ExtraSounds.NAME, version = ExtraSounds.VERSION, acceptedMinecraftVersions = "[1.12.2]", dependencies = ExtraSounds.DEPENDENCIES, clientSideOnly = true)
public class ExtraSounds
{
    public static final String MODID = "extrasounds";
    public static final String NAME = "Extra Sounds";
    public static final String VERSION = "1.12.2-1.3.3";
    public static final String DEPENDENCIES = "required-after:mixinbooter;required-after:assetmover;after:jei;after:asmc;after:dsurround";
    public static final Logger LOGGER = LogManager.getLogger(NAME);
    public static boolean asmc = false;
    public static boolean dsurround = false;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ESSoundEvents.preInit();
        ESSoundManager.preInit();
        LOGGER.info("Extra Sounds pre-initialized");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
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
}