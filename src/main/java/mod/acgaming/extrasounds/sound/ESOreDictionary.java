package mod.acgaming.extrasounds.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class ESOreDictionary
{
    public static void init()
    {
        addResLocToOreDict("snow", "minecraft:snowball");
        addResLocToOreDict("snow", "minecraft:snow");
        addResLocToOreDict("snow", "minecraft:snow_layer");
    }

    public static void addResLocToOreDict(String name, String ore)
    {
        ResourceLocation resLoc = new ResourceLocation(ore);
        if (ForgeRegistries.ITEMS.containsKey(resLoc)) OreDictionary.registerOre(name, ForgeRegistries.ITEMS.getValue(resLoc));
    }
}