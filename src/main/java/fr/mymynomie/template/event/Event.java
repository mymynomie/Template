package fr.mymynomie.template.event;

import fr.mymynomie.template.proxy.ServerProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class Event extends ServerProxy {

    public static void PlayerTP(MinecraftServer server, EntityPlayer player, int PosX, int PosY, int PosZ) {

        server.commandManager.executeCommand(server, "tp " + player.getName() + " " + PosX + " " + PosY + " " + PosZ);

    }

}
