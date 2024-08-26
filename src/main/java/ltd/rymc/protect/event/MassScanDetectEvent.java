package ltd.rymc.protect.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class MassScanDetectEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;

    public MassScanDetectEvent(Player player) {
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return this.player;
    }
}
