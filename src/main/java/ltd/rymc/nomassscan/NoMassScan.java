package ltd.rymc.nomassscan;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class NoMassScan extends JavaPlugin {

    @Override
    public void onEnable() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, PacketType.Play.Server.TAB_COMPLETE) {
            public void onPacketSending(PacketEvent event) {
                List<Suggestion> suggestions = event.getPacket().getSpecificModifier(Suggestions.class).read(0).getList();
                suggestions.removeIf(suggestion -> check(suggestion, event.getPlayer()));
            }
        });
    }

    private static boolean check(Suggestion suggestion, Player player) {
        boolean check = suggestion.getText().contains(":");
        StringRange range = suggestion.getRange();
        if (range.getStart() == 0 && range.getEnd() == 1) {
            player.kickPlayer("监测到不正确的命令提示,你是否在使用插件探查器如Meteor MassScan?\n由于反插件探查仍处于测试状态,如遇到误报请向管理报告");
        }
        return check;
    }
}
