package mod.acgaming.extrasounds.core;

import java.util.Collections;
import java.util.List;

import net.minecraftforge.fml.common.Loader;

import zone.rong.mixinbooter.ILateMixinLoader;

public class ESMixinLoader implements ILateMixinLoader
{
    @Override
    public List<String> getMixinConfigs()
    {
        return ESLoadingPlugin.isClient ? Collections.singletonList("extrasounds.jei.mixins.json") : Collections.singletonList("");
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig)
    {
        if (ESLoadingPlugin.isClient)
        {
            if (mixinConfig.equals("extrasounds.jei.mixins.json"))
            {
                return Loader.isModLoaded("jei");
            }
        }
        return false;
    }
}