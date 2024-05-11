package mymynomie.template;

import mymynomie.template.init.BlocksMod;
import mymynomie.template.init.ItemsMod;
import mymynomie.template.network.PaquetOnJoinClient;
import mymynomie.template.network.PaquetOnJoinServer;
import mymynomie.template.proxy.ServerProxy;
import mymynomie.template.tabs.ItemsTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, acceptedMinecraftVersions = References.MINECRAFT_VERSION)
public class Template {

    public static final CreativeTabs ItemsTabs = new ItemsTabs("ItemsTabs");


    @SidedProxy(clientSide = References.CLIENT_PROXY, serverSide = References.SERVER_PROXY, modId = References.MODID)
    public static ServerProxy proxy;
    public static SimpleNetworkWrapper network;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        network = NetworkRegistry.INSTANCE.newSimpleChannel("ChannelTemplate");
        network.registerMessage(PaquetOnJoinServer.Handler.class, PaquetOnJoinServer.class, 1, Side.SERVER);
        network.registerMessage(PaquetOnJoinClient.Handler.class, PaquetOnJoinClient.class, 2, Side.CLIENT);
        ItemsMod.init();
        BlocksMod.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event, EntityPlayer player) {
        proxy.register();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
    }

}