package fr.mymynomie.template.commands;

import com.google.common.collect.Lists;
import fr.mymynomie.template.JsonManagement.JsonManagement;
import javafx.geometry.Pos;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.io.*;
import java.util.*;

public class Object extends CommandBase {

    public String getName() {
        return "object";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getUsage(ICommandSender sender) {
        return "object";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) { return true; }

    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        if (args.length == 1) {

            if (Objects.equals(args[0], "set")) {
                EntityPlayer player = server.getServer().getPlayerList().getPlayerByUsername(sender.getName());

                int PosX = player.getPosition().getX();
                int PosY = player.getPosition().getY();
                int PosZ = player.getPosition().getZ();

                JsonManagement.createjson(player);
                JsonManagement.setPosX(player, PosX);
                JsonManagement.setPosY(player, PosY);
                JsonManagement.setPosZ(player, PosZ);

                player.sendMessage(new TextComponentString("\n§aTemplate | Votre Point de téléportation à était changer !"));

                return;
            }

            EntityPlayer playerIn = server.getServer().getPlayerList().getPlayerByUsername(args[0]);
            File file = new File(JsonManagement.ReturnPath() + playerIn.getUniqueID() + ".json");
            if (file.exists()) {
                int posX2 = JsonManagement.getPosX(playerIn);
                int posY2 = JsonManagement.getPosY(playerIn);
                int posZ2 = JsonManagement.getPosZ(playerIn);


                sender.sendMessage(new TextComponentString("\n§aTemplate | Vous avez était télépoter au point de téléportation du Joueur : " + args[0] + "\n"));
                Objects.requireNonNull(server.getServer()).commandManager.executeCommand(server, "tp " + sender.getName() + " " + posX2 + " " + posY2 + " " + posZ2);
                return;
            } else {
                sender.sendMessage(new TextComponentString("\n§cTemplate | Le Joueur " + playerIn.getName() + " ne possède pas de Point de Téléportation !\n"));
            }
            return;
        }

        if (args.length == 2 && Objects.equals(args[0], "set")) {
            EntityPlayer playerIn = server.getServer().getPlayerList().getPlayerByUsername(args[1]);

            int PosX = sender.getPosition().getX();
            int PosY = sender.getPosition().getY();
            int PosZ = sender.getPosition().getZ();

            JsonManagement.createjson(playerIn);
            JsonManagement.setPosX(playerIn, PosX);
            JsonManagement.setPosY(playerIn, PosY);
            JsonManagement.setPosZ(playerIn, PosZ);

            sender.sendMessage(new TextComponentString("\n§aTemplate | Le Point de téléportation du Joueur " + playerIn.getName() + " à était changer !"));

            return;
        }

        EntityPlayer player = server.getServer().getPlayerList().getPlayerByUsername(sender.getName());
        File file = new File(JsonManagement.ReturnPath() + player.getUniqueID() + ".json");

        if (file.exists()) {

            int posX = JsonManagement.getPosX(player);
            int posY = JsonManagement.getPosY(player);
            int posZ = JsonManagement.getPosZ(player);

            sender.sendMessage(new TextComponentString("\n§aTemplate | Une Brêche vous transporte au point de départ !\n"));
            Objects.requireNonNull(server.getServer()).commandManager.executeCommand(server, "tp " + sender.getName() + " " + posX + " " + posY + " " + posZ);
        } else {
            player.sendMessage(new TextComponentString("\n§cTemplate | Vous ne possèdez pas de Point de Téléportation !\n"));
        }
    }

    @Override
    public List<String> getAliases() {
        return Lists.newArrayList("obj");
    }

    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {

        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        }
        if (args.length == 2 && Objects.equals(args[0], "set")) {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        }
        return Collections.emptyList();
    }
}
