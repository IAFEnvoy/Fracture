package fracture;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fracture.items.items;
import fracture.wings.wings;

public class fracture implements ModInitializer {
	public static Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "fracture";
	public static final String MOD_NAME = "Fracture";

	@Override
	public void onInitialize() {
		log(Level.INFO, "Initializing");
		items.Init();
    ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("DebugSystem").then(ClientCommandManager
        .argument("R1", StringArgumentType.word())
        .then(ClientCommandManager.argument("G1", StringArgumentType.word())
            .then(ClientCommandManager.argument("B1", StringArgumentType.word())
                .then(ClientCommandManager.argument("R2", StringArgumentType.word())
                    .then(ClientCommandManager.argument("G2", StringArgumentType.word()).then(
                        ClientCommandManager.argument("B2", StringArgumentType.word()).executes(fracture::debug))))))));
	}

  private static int debug(CommandContext<FabricClientCommandSource> context) {
    try {
      x = Float.parseFloat(StringArgumentType.getString(context, "R1"));
      y = Float.parseFloat(StringArgumentType.getString(context, "G1"));
      z = Float.parseFloat(StringArgumentType.getString(context, "B1"));
      xr = Float.parseFloat(StringArgumentType.getString(context, "R2"));
      yr = Float.parseFloat(StringArgumentType.getString(context, "G2"));
      zr = Float.parseFloat(StringArgumentType.getString(context, "B2"));
    } catch (Exception e) {
      e.printStackTrace();
      return 1;
    }
    // System.out.println(StringArgumentType.getString(context, "R1"));
    return 0;
  }

  public static float x = 0.0F, y = 0.0F, z = 0.0F, xr = 0.0F, yr = 0.0F, zr = 0.0F;

	public static final ItemGroup _fracture = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "fracture"))
			.icon(() -> new ItemStack(items.ceris_big_knief)).appendItems(stacks -> {
				stacks.add(new ItemStack(items.ceris_knief));
				stacks.add(new ItemStack(items.patrick_halberd));
				stacks.add(new ItemStack(items.dancer_arm));
				stacks.add(new ItemStack(items.ceris_big_knief));
				stacks.add(new ItemStack(items.ciara_boomerang));
				stacks.add(new ItemStack(items.blackbone_arm));
				stacks.add(new ItemStack(items.long_golden_sword));
				stacks.add(new ItemStack(items.nether_princess_arm));
        stacks.add(new ItemStack(wings.tech_wing));
			}).build();

	public static void log(Level level, String message) {
		LOGGER.log(level, "[" + MOD_NAME + "] " + message);
	}
}
