package mymyxyz.test2.items;

import mymyxyz.test2.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class MyItemMod extends Item {
    public MyItemMod(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.ItemsTabs);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {
            playerIn.sendMessage(new TextComponentString("\nTest2"));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}