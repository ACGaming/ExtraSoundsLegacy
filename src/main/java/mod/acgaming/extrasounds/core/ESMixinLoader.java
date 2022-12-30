package mod.acgaming.extrasounds.core;

import java.util.List;

import com.google.common.collect.Lists;
import net.minecraftforge.fml.common.Loader;

import zone.rong.mixinbooter.ILateMixinLoader;

public class ESMixinLoader implements ILateMixinLoader
{
    @Override
    public List<String> getMixinConfigs()
    {
        return Lists.newArrayList("extrasounds.jei.mixins.json");
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig)
    {
        if (mixinConfig.equals("extrasounds.jei.mixins.json"))
        {
            return Loader.isModLoaded("jei");
        }
        return false;
    }
}