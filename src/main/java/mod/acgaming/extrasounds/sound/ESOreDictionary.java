package mod.acgaming.extrasounds.sound;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import mod.acgaming.extrasounds.ExtraSounds;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ESOreDictionary
{
    public static void init()
    {
        try
        {
            OreDictionary.registerOre("snow", Items.SNOWBALL);
            OreDictionary.registerOre("snow", Blocks.SNOW);
            OreDictionary.registerOre("snow", Blocks.SNOW_LAYER);
        }
        catch (Exception e)
        {
            ExtraSounds.LOGGER.error(e);
        }
    }
}