package mymynomie.template.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TemplateCommand extends CommandBase {

    private final String name;
    private final List<String> aliases;
    private final boolean commandOfPlayer;

    public TemplateCommand(String name, List<String> aliases, boolean commandOfPlayer) {
        this.name = name;
        this.aliases = aliases;
        this.commandOfPlayer = commandOfPlayer;
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isShort(String s) {
        try {
            Short.parseShort(s);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isUUID(String s) {
        try {
            UUID.fromString(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static EntityPlayer getPlayer(MinecraftServer server, String playerName) {
        return server.getPlayerList().getPlayerByUsername(playerName);
    }

    public static boolean playerIsOnline(MinecraftServer server, EntityPlayer player) {
        return server.getPlayerList().getPlayers().contains(player);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return this.name;
    }

    @Override
    public List<String> getAliases() {
        if (this.aliases != null) {
            return aliases;
        }
        return Collections.emptyList();
    }

    @Override
    public int getRequiredPermissionLevel() {
        if (this.commandOfPlayer) {
            return 0;
        }
        return 4;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {

    }
}
