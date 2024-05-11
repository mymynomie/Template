package mymynomie.template.network;

import io.netty.buffer.ByteBuf;
import mymyxyz.noctuaAPI.proxy.client.NoctuaIcon;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PaquetOnJoinClient implements IMessage {

    private int player;
    private int maxplayer;

    public PaquetOnJoinClient() {
    }

    public PaquetOnJoinClient(int player, int maxplayer) {
        this.player = player;
        this.maxplayer = maxplayer;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.player = buf.readInt();
        this.maxplayer = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.player);
        buf.writeInt(this.maxplayer);
    }

    public static class Handler implements IMessageHandler<PaquetOnJoinClient, IMessage> {
        @Override
        public IMessage onMessage(PaquetOnJoinClient message, MessageContext ctx) {
            NoctuaIcon.discordRPC(message.player, message.maxplayer);
            return null;
        }
    }
}