package iafenvoy.fracture;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import iafenvoy.fracture.Config.PlayerProfile;
import iafenvoy.fracture.Utils.CommandUtil;
import iafenvoy.fracture.Utils.Enum.Teams;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

public class Commands {
  public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
    LiteralArgumentBuilder<ServerCommandSource> builder = literal("fracture")
        .then(literal("selectteam")
            .then(literal("human").executes((context) -> {
              PlayerProfile.setTeam(context.getSource().getPlayer().getUuidAsString(), Teams.HUMAN);
              CommandUtil.sendCommand("team join human " + context.getSource().getPlayer().getName().getString());
              return 0;
            }))
            .then(literal("undead").executes((context) -> {
              PlayerProfile.setTeam(context.getSource().getPlayer().getUuidAsString(), Teams.UNDEAD);
              CommandUtil.sendCommand("team join undead " + context.getSource().getPlayer().getName().getString());
              return 0;
            }))
            .then(literal("nether").executes((context) -> {
              PlayerProfile.setTeam(context.getSource().getPlayer().getUuidAsString(), Teams.NETHER);
              CommandUtil.sendCommand("team join nether " + context.getSource().getPlayer().getName().getString());
              return 0;
            }))
            .then(literal("end").executes((context) -> {
              PlayerProfile.setTeam(context.getSource().getPlayer().getUuidAsString(), Teams.END);
              CommandUtil.sendCommand("team join end " + context.getSource().getPlayer().getName().getString());
              return 0;
            })));
    dispatcher.register(builder);
  }
}
