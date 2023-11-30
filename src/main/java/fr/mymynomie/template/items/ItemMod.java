package fr.mymynomie.template.items;

import fr.mymynomie.template.Main;
import net.minecraft.item.Item;

public class ItemMod extends Item {
    public ItemMod(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.ItemsTabs);
    }
}