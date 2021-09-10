package fracture;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

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
		wings.Init();
	}

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
				stacks.add(new ItemStack(wings.wings));
			}).build();

	public static void log(Level level, String message) {
		LOGGER.log(level, "[" + MOD_NAME + "] " + message);
	}
}
