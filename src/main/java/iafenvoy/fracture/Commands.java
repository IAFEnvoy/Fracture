package iafenvoy.fracture;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import iafenvoy.fracture.Config.PlayerProfile;
import iafenvoy.fracture.Utils.Enum.Teams;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

public class Commands {
  public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
    LiteralArgumentBuilder<ServerCommandSource> builder = literal("fracture")
        .then(literal("selectteam")
            .then(literal("human").executes((context) -> {
              PlayerProfile.setTeam(context.getSource().getPlayer().getEntityName(), Teams.HUMAN);
              return 0;
            }))
            .then(literal("undead").executes((context) -> {
              PlayerProfile.setTeam(context.getSource().getPlayer().getEntityName(), Teams.UNDEAD);
              return 0;
            }))
            .then(literal("nether").executes((context) -> {
              PlayerProfile.setTeam(context.getSource().getPlayer().getEntityName(), Teams.NETHER);
              return 0;
            }))
            .then(literal("end").executes((context) -> {
              PlayerProfile.setTeam(context.getSource().getPlayer().getEntityName(), Teams.END);
              return 0;
            })));
    dispatcher.register(builder);
  }
}
