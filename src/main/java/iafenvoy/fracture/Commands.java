package iafenvoy.fracture;

import iafenvoy.fracture.Config.PlayerProfile;
import iafenvoy.fracture.Utils.CommandUtil;
import iafenvoy.fracture.Utils.Enum.Teams;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.text.LiteralText;

import static net.minecraft.server.command.CommandManager.literal;

public class Commands {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
                dispatcher.register(literal(Fracture.MOD_ID)
                        .then(literal("selectteam")
                                .then(literal("human").executes((context) -> {
                                    PlayerProfile.setTeam(context.getSource().getPlayer().getUuidAsString(), Teams.HUMAN);
                                    CommandUtil.sendCommand("team join human " + context.getSource().getPlayer().getName().getString());
                                    context.getSource().getPlayer().sendMessage(new LiteralText("You have joined the human team."), false);
                                    return 0;
                                }))
                                .then(literal("undead").executes((context) -> {
                                    PlayerProfile.setTeam(context.getSource().getPlayer().getUuidAsString(), Teams.UNDEAD);
                                    CommandUtil.sendCommand("team join undead " + context.getSource().getPlayer().getName().getString());
                                    context.getSource().getPlayer().sendMessage(new LiteralText("You have joined the undead team."), false);
                                    return 0;
                                }))
                                .then(literal("nether").executes((context) -> {
                                    PlayerProfile.setTeam(context.getSource().getPlayer().getUuidAsString(), Teams.NETHER);
                                    CommandUtil.sendCommand("team join nether " + context.getSource().getPlayer().getName().getString());
                                    context.getSource().getPlayer().sendMessage(new LiteralText("You have joined the nether team."), false);
                                    return 0;
                                }))
                                .then(literal("end").executes((context) -> {
                                    PlayerProfile.setTeam(context.getSource().getPlayer().getUuidAsString(), Teams.END);
                                    CommandUtil.sendCommand("team join end " + context.getSource().getPlayer().getName().getString());
                                    context.getSource().getPlayer().sendMessage(new LiteralText("You have joined the end team."), false);
                                    return 0;
                                })))));
    }
}
