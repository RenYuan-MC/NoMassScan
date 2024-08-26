package ltd.rymc.protect.skript.event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ltd.rymc.protect.event.MassScanDetectEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAnimationEvent;

@SuppressWarnings("unused")
public class MassScanEvent {
    static {
        Skript.registerEvent("mass scan detect event", SimpleEvent.class, MassScanDetectEvent.class, "mass scan detect");
        EventValues.registerEventValue(PlayerAnimationEvent.class, Player.class, new Getter<Player, PlayerAnimationEvent>() {
            @Override
            public Player get(PlayerAnimationEvent event) {
                return event.getPlayer();
            }
        }, 0);
    }
}