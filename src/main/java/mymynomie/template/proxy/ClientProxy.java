package mymynomie.template.proxy;

import mymynomie.template.proxy.client.Icon;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends ServerProxy {

    public ClientProxy() {
        MinecraftForge.EVENT_BUS.register(this);
        new Icon().register();
    }

    @Override
    public void register() {
    }
}
