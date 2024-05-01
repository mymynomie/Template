package mymyxyz.test2;

import mymyxyz.test2.init.BlocksMod;
import mymyxyz.test2.init.ItemsMod;
import mymyxyz.test2.proxy.ServerProxy;
import mymyxyz.test2.tabs.ItemsTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, acceptedMinecraftVersions = References.MINECRAFT_VERSION)
public class Main {

    public static final CreativeTabs ItemsTabs = new ItemsTabs("ItemsTabs");


    @SidedProxy(clientSide = References.CLIENT_PROXY, serverSide = References.SERVER_PROXY, modId = References.MODID)
    public static ServerProxy proxy;
    public static SimpleNetworkWrapper network;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
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