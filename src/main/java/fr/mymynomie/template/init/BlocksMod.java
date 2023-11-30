package fr.mymynomie.template.init;

import fr.mymynomie.template.References;
import fr.mymynomie.template.blocks.BlockMod;
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

import java.util.Objects;

@Mod.EventBusSubscriber(modid = References.MODID)
public class BlocksMod {
    public static Block ardoise_block;

    public static void init() {
        //blocks
        ardoise_block = new BlockMod("ardoise_block", Material.ROCK, 0).setHardness(5.0F);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ardoise_block
        );
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                new ItemBlock(ardoise_block).setRegistryName(Objects.requireNonNull(ardoise_block.getRegistryName()))
        );
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        registerRender(Item.getItemFromBlock(ardoise_block));
    }

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
    }
}