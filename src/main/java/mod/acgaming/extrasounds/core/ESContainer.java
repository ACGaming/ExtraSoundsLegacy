package mod.acgaming.extrasounds.core;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

public class ESContainer extends DummyModContainer
{
    public ESContainer()
    {
        super(new ModMetadata());
        ModMetadata meta = this.getMetadata();
        meta.modId = "extrasoundscore";
        meta.name = "Extra Sounds Core";
        meta.description = "Core functionality of Extra Sounds";
        meta.version = "1.12.2-1.0.0";
        meta.authorList.add("ACGaming");
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        bus.register(this);
        return true;
    }
}