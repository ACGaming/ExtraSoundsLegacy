package mod.acgaming.extrasounds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import mod.acgaming.extrasounds.sound.ESSoundEvents;

@Mod(modid = ExtraSounds.MODID, name = ExtraSounds.NAME, version = ExtraSounds.VERSION, acceptedMinecraftVersions = "[1.12.2]", dependencies = ExtraSounds.DEPENDENCIES, clientSideOnly = true)
public class ExtraSounds
{
    public static final String MODID = "extrasounds";
    public static final String NAME = "Extra Sounds";
    public static final String VERSION = "1.12.2-1.1.0";
    public static final String DEPENDENCIES = "required-after:mixinbooter;after:jei";
    public static final Logger LOGGER = LogManager.getLogger("ES");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ESSoundEvents.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        LOGGER.info("Extra Sounds initialized");
    }
}