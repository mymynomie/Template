package fr.mymynomie.template.items;

import com.google.common.collect.Sets;
import fr.mymynomie.template.JsonManagement.JsonManagement;
import fr.mymynomie.template.event.Event;
import javafx.geometry.Pos;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static sun.audio.AudioPlayer.player;

public class ItemObjectMod extends ItemTool {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet();
    public ItemObjectMod(String name, ToolMaterial material) {
        super(material, EFFECTIVE_ON);
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setNoRepair();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        if (!worldIn.isRemote) {

            File file = new File(JsonManagement.ReturnPath() + playerIn.getUniqueID() + ".json");

            if (playerIn.isSneaking()) {
                int PosX = playerIn.getPosition().getX();
                int PosY = playerIn.getPosition().getY();
                int PosZ = playerIn.getPosition().getZ();

                playerIn.sendMessage(new TextComponentString("\n§aTemplate | Le Point de Téléportation de l'Object à étais changer !\n").setStyle(new Style().setColor(TextFormatting.GREEN)));

                    JsonManagement.createjson(playerIn);
                    JsonManagement.setPosX(playerIn, PosX);
                    JsonManagement.setPosY(playerIn, PosY);
                    JsonManagement.setPosZ(playerIn, PosZ);

            } else {

                if (!file.exists()) {
                    playerIn.sendMessage(new TextComponentString("\n§cTemplate | Vous devez d'abord Set votre Point de Téléportation !\n").setStyle(new Style().setColor(TextFormatting.RED)));
                    return super.onItemRightClick(worldIn, playerIn, handIn);
                }

                int PosX = JsonManagement.getPosX(playerIn);
                int PosY = JsonManagement.getPosY(playerIn);
                int PosZ = JsonManagement.getPosZ(playerIn);

                Event.PlayerTP(Objects.requireNonNull(playerIn.getServer()), playerIn, PosX, PosY, PosZ);

                playerIn.sendMessage(new TextComponentString("\n§aTemplate | Vous avez étais déplacer à votre Point de téléportation défini !\n").setStyle(new Style().setColor(TextFormatting.GREEN)));

                playerIn.getHeldItemMainhand().damageItem(1, playerIn);
                if (playerIn.getHeldItemMainhand().getItemDamage() == playerIn.getHeldItemMainhand().getMaxDamage()) {
                    playerIn.getHeldItemMainhand().damageItem(1, playerIn);
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(" ");
        tooltip.add(">> Shift Click-Droit : Set de Téléportation sur le Block en dessous de vous");
        tooltip.add(" ");
        tooltip.add(">> Click-Droit pour vous Téléportez au Point de Téléportation");
        tooltip.add(" ");
    }

}