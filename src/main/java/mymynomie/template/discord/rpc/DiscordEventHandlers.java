package mymynomie.template.discord.rpc;

import com.sun.jna.Callback;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscordEventHandlers extends Structure {
    private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList("ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest"));
    public OnReady ready;
    public OnStatus disconnected;
    public OnStatus errored;
    public OnGameUpdate joinGame;
    public OnGameUpdate spectateGame;
    public OnJoinRequest joinRequest;

    public DiscordEventHandlers() {
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof DiscordEventHandlers)) {
            return false;
        } else {
            DiscordEventHandlers that = (DiscordEventHandlers) o;
            return Objects.equals(this.ready, that.ready) && Objects.equals(this.disconnected, that.disconnected) && Objects.equals(this.errored, that.errored) && Objects.equals(this.joinGame, that.joinGame) && Objects.equals(this.spectateGame, that.spectateGame) && Objects.equals(this.joinRequest, that.joinRequest);
        }
    }

    public int hashCode() {
        return Objects.hash(this.ready, this.disconnected, this.errored, this.joinGame, this.spectateGame, this.joinRequest);
    }

    protected List<String> getFieldOrder() {
        return FIELD_ORDER;
    }

    public interface OnJoinRequest extends Callback {
        void accept(DiscordUser var1);
    }

    public interface OnGameUpdate extends Callback {
        void accept(String var1);
    }

    public interface OnStatus extends Callback {
        void accept(int var1, String var2);
    }

    public interface OnReady extends Callback {
        void accept(DiscordUser var1);
    }
}