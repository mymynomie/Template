package mymynomie.template.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemMod extends Item {

    public ItemMod(String name, Integer size, CreativeTabs creativeTabs) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setNoRepair();
        if (size != null) setMaxStackSize(size);
        if (creativeTabs != null) setCreativeTab(creativeTabs);
    }
}
