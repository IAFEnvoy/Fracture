package iafenvoy.fracture.Utils;

import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.LiteralText;

public class CommandUtil {
    public static MinecraftServer server;

    public static void sendCommand(String command) {
        server.getCommandManager().execute(server.getCommandSource(), command);
    }

    public static void addTeam(String name, String prefix) {
        Scoreboard sb = server.getScoreboard();
        Team team = sb.getTeam(name);
        if (team != null) {
            LogUtil.warn("Team " + name + " already exists");
            team.setPrefix(new LiteralText(prefix));
            return;
        }
        team = sb.addTeam(name);
        team.setPrefix(new LiteralText(prefix));
    }
}
