package mod.acgaming.extrasounds.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import mod.acgaming.extrasounds.ExtraSounds;
import zone.rong.mixinbooter.IEarlyMixinLoader;

@IFMLLoadingPlugin.Name("ESCore")
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.SortingIndex(Integer.MIN_VALUE)
public class ESLoadingPlugin implements IFMLLoadingPlugin, IEarlyMixinLoader
{
    static
    {
        ExtraSounds.LOGGER.info("Extra Sounds Core initializing...");
    }

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[0];
    }

    @Override
    public String getModContainerClass()
    {
        return ESContainer.class.getName();
    }

    @Nullable
    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {

    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

    @Override
    public List<String> getMixinConfigs()
    {
        List<String> mixins = new ArrayList<>();
        mixins.add("extrasounds.mixins.json");
        return mixins;
    }
}