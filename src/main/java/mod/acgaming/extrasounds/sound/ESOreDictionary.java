package mod.acgaming.extrasounds.sound;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

public class ESOreDictionary
{
    public static void init()
    {
        OreDictionary.registerOre("snow", Items.SNOWBALL);
        OreDictionary.registerOre("snow", Blocks.SNOW);
        OreDictionary.registerOre("snow", Blocks.SNOW_LAYER);

        MinecraftForge.EVENT_BUS.register(ESOreDictionary.class);
    }
}