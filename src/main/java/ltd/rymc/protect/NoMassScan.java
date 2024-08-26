package ltd.rymc.protect;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import ltd.rymc.protect.event.MassScanDetectEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("unused")
public final class NoMassScan extends JavaPlugin {

    @Override
    public void onEnable() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, PacketType.Play.Server.TAB_COMPLETE) {
            public void onPacketSending(PacketEvent event) {
                List<Suggestion> suggestions = event.getPacket().getSpecificModifier(Suggestions.class).read(0).getList();
                suggestions.removeIf(suggestion -> check(suggestion, event.getPlayer()));
            }
        });

        if (!Bukkit.getPluginManager().isPluginEnabled("Skript")) return;

        try {
            SkriptAddon addonInstance = Skript.registerAddon(this);
            addonInstance.loadClasses("ltd.rymc.protect.skript","event");
        } catch (IOException ignored) {
        }
    }

    private static boolean check(Suggestion suggestion, Player player) {
        boolean check = suggestion.getText().contains(":");
        StringRange range = suggestion.getRange();
        if (range.getStart() == 0 && range.getEnd() == 1) {
            MassScanDetectEvent event = new MassScanDetectEvent(player);
            event.callEvent();
        }
        return check;
    }
}
