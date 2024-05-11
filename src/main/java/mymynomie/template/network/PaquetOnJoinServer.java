package mymynomie.template.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Objects;

import static mymyxyz.noctuaAPI.NoctuaAPI.network;

public class PaquetOnJoinServer implements IMessage {
    public PaquetOnJoinServer() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<PaquetOnJoinServer, IMessage> {
        @Override
        public IMessage onMessage(PaquetOnJoinServer message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            network.sendTo(new PaquetOnJoinClient(Objects.requireNonNull(player.getServer()).getCurrentPlayerCount(), player.getServer().getMaxPlayers()), player);
            return null;
        }
    }
}