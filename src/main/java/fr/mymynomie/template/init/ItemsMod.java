package fr.mymynomie.template.init;

import fr.mymynomie.template.Main;
import fr.mymynomie.template.References;
import fr.mymynomie.template.items.ItemMod;
import fr.mymynomie.template.items.ItemObjectMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = References.MODID)
public class ItemsMod {

    public static Item icon;
    public static Item object;

    public static ItemTool.ToolMaterial MaterialObject;

    public static void init() {

        MaterialObject = EnumHelper.addToolMaterial("MaterialObject", 0, 3, 0, -4F, 0);

        icon = new ItemMod("icon");
        object = new ItemObjectMod("object", MaterialObject).setCreativeTab(Main.ItemsTabs);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(

                icon, object

        );
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {

        registerRender(icon);
        registerRender(object);

    }

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
    }
}