package mymynomie.template.proxy.client;

import mymynomie.template.References;
import mymynomie.template.Template;
import mymynomie.template.discord.rpc.DiscordEventHandlers;
import mymynomie.template.discord.rpc.DiscordRPC;
import mymynomie.template.discord.rpc.DiscordRichPresence;
import mymynomie.template.network.PaquetOnJoinServer;
import mymynomie.template.network.PaquetOnLeaveServer;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.lwjgl.opengl.Display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;

public class Icon {
    public static ByteBuffer[] icons = new ByteBuffer[2];
    public static final String path = "/assets/" + References.MODID + "/textures/icons/";

    static {
        Icon.icons[0] = createIcon(path + "icon16.png"); // 16
        Icon.icons[1] = createIcon(path + "icon32.png"); // 32
        Display.setIcon(Icon.icons);
    }

    public Icon() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ByteBuffer createIcon(String path) {
        BufferedImage image;
        try {
            image = ImageIO.read(Objects.requireNonNull(Icon.class.getResource(path)));
            return imageToByteBuffer(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ByteBuffer imageToByteBuffer(BufferedImage image) {
        if (image.getType() != BufferedImage.TYPE_INT_ARGB_PRE) {
            BufferedImage convertedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
            Graphics2D g = convertedImage.createGraphics();
            double width = image.getWidth() * (double) 1;
            double height = image.getHeight() * (double) 1;
            g.drawImage(image, (int) ((convertedImage.getWidth() - width) / 2), (int) ((convertedImage.getHeight() - height) / 2), (int) width, (int) height, null);
            g.dispose();
            image = convertedImage;
        }
        byte[] imageBuffer = new byte[image.getWidth() * image.getHeight() * 4];
        int counter = 0;
        for (int i = 0; i < image.getHeight(); i++)
            for (int j = 0; j < image.getWidth(); j++) {
                int colorSpace = image.getRGB(j, i);
                imageBuffer[counter] = (byte) (colorSpace << 8 >> 24);
                imageBuffer[counter + 1] = (byte) (colorSpace << 16 >> 24);
                imageBuffer[counter + 2] = (byte) (colorSpace << 24 >> 24);
                imageBuffer[counter + 3] = (byte) (colorSpace >> 24);
                counter += 4;
            }
        return ByteBuffer.wrap(imageBuffer);
    }

    public static void discordRPC(int player, int maxplayer) {
        DiscordRPC discordRPC = DiscordRPC.INSTANCE;
        String applicationId = "1238250181493919815";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        discordRPC.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.largeImageKey = "";
        presence.largeImageText = References.NAME;
        presence.smallImageKey = "";
        presence.smallImageText = "";
        presence.details = "";
        presence.state = "";
        presence.partyId = "";
        presence.partyMax = maxplayer;
        presence.partySize = player;
        discordRPC.Discord_UpdatePresence(presence);
    }

    public void register() {
        OBJLoader.INSTANCE.addDomain(References.MODID);
        Display.setTitle("Template - V " + References.MODID);
        discordRPC(0, 0);
    }

    @SubscribeEvent
    public void PlayerIn(PlayerEvent.PlayerLoggedInEvent evt) {
        Template.network.sendToServer(new PaquetOnJoinServer());
    }

    @SubscribeEvent
    public void PlayerOut(PlayerEvent.PlayerLoggedOutEvent evt) {
        Template.network.sendToServer(new PaquetOnLeaveServer());
    }

}
