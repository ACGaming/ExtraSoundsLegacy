package mod.acgaming.extrasounds.config;

import java.util.List;
import java.util.Set;

import net.minecraftforge.fml.common.Loader;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class ESMixinConfigPlugin implements IMixinConfigPlugin
{
    @Override
    public void onLoad(String s)
    {

    }

    @Override
    public String getRefMapperConfig()
    {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String s, String s1)
    {
        if (s1.contains("IngredientGridWithNavigationMixin"))
        {
            for (int i = 0; i < Loader.instance().getModList().size(); i++)
            {
                if (Loader.instance().getModList().get(i).getName().equals("Just Enough Items")) return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1)
    {

    }

    @Override
    public List<String> getMixins()
    {
        return null;
    }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo)
    {

    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo)
    {

    }
}