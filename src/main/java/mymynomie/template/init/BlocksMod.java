package mymynomie.template.init;

import mymynomie.template.References;
import mymynomie.template.blocks.BlockMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = References.MODID)
public class BlocksMod {
    private static final List<Block> blockList = new ArrayList<>();

    public static void init() {
        addBlockList(new BlockMod("template_block", Material.ROCK, 0).setHardness(5.0F), 0);
    }

    public static List<Block> getBlockList() {
        return blockList;
    }

    public static void addBlockList(Block block, int id) {
        getBlockList().add(id, block);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (int i = 0; i < getBlockList().size(); i++) {
            event.getRegistry().register(getBlockList().get(i));
        }
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        for (int i = 0; i < getBlockList().size(); i++) {
            event.getRegistry().register(new ItemBlock(getBlockList().get(i)).setRegistryName(Objects.requireNonNull(getBlockList().get(i).getRegistryName())));
        }
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for (int i = 0; i < getBlockList().size(); i++) {
            registerRender(Item.getItemFromBlock(getBlockList().get(i)));
        }
    }

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
    }
}