package mymyxyz.test2.tabs;

import mymyxyz.test2.init.ItemsMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemsTabs extends CreativeTabs {

    public ItemsTabs(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemsMod.icon);
    }

}
