package mymynomie.template.init;

import mymynomie.template.References;
import mymynomie.template.Template;
import mymynomie.template.items.ItemMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = References.MODID)
public class ItemsMod {
    private static final List<Item> itemList = new ArrayList<>();

    public static void init() {
        addItemList(new ItemMod("icon", 1, Template.ItemsTabs), 0);

    }

    public static List<Item> getItemList() {
        return itemList;
    }

    public static void addItemList(Item item, int id) {
        getItemList().add(id, item);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (int i = 0; i < getItemList().size(); i++) {
            event.getRegistry().register(getItemList().get(i));
        }
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for (int i = 0; i < getItemList().size(); i++) {
            registerRender(getItemList().get(i));
        }
    }

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
    }
}