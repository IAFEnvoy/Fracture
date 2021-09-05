package fracture;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fracture.arms.fracture_material;

public class fracture implements ModInitializer {
	public static Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID="fracture";
	public static final String MOD_NAME="Fracture";

	public static ToolItem ceris_knief = new SwordItem(fracture_material.INSTANCE, 30 - 1, 10 - 4,
                        new Item.Settings().group(fracture._fracture));
        public static ToolItem patrick_halberd = new SwordItem(fracture_material.INSTANCE, 25 - 1, 3 - 4,
                        new Item.Settings().group(fracture._fracture));
        public static ToolItem dancer_arm = new SwordItem(fracture_material.INSTANCE, 27 - 1, 4 - 4,
                        new Item.Settings().group(fracture._fracture));
        public static ToolItem ceris_big_knief = new SwordItem(fracture_material.INSTANCE, 10 - 1, 6 - 4,
                        new Item.Settings().group(fracture._fracture));
        public static ToolItem ciara_boomerang = new SwordItem(fracture_material.INSTANCE, 8 - 1, 10 - 4,
                        new Item.Settings().group(fracture._fracture));
        public static ToolItem blackbone_arm = new SwordItem(fracture_material.INSTANCE, 10 - 1, 2 - 4,
                        new Item.Settings().group(fracture._fracture));
        public static ToolItem long_golden_sword = new SwordItem(fracture_material.INSTANCE, 8 - 1, 2 - 4,
                        new Item.Settings().group(fracture._fracture));
        public static ToolItem nether_princess_arm = new SwordItem(fracture_material.INSTANCE, 10 - 1, 7 - 4,
                        new Item.Settings().group(fracture._fracture));
	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ceris_knief"), ceris_knief);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "patrick_halberd"), patrick_halberd);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dancer_arm"), dancer_arm);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ceris_big_knief"), ceris_big_knief);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ciara_boomerang"), ciara_boomerang);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "blackbone_arm"), blackbone_arm);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "long_golden_sword"), long_golden_sword);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nether_princess_arm"), nether_princess_arm);
	}
	public static final ItemGroup _fracture = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "fracture"))
			.icon(() -> new ItemStack(fracture.ceris_big_knief)).appendItems(stacks -> {
				stacks.add(new ItemStack(fracture.ceris_knief));
				stacks.add(new ItemStack(fracture.patrick_halberd));
				stacks.add(new ItemStack(fracture.dancer_arm));
				stacks.add(new ItemStack(fracture.ceris_big_knief));
				stacks.add(new ItemStack(fracture.ciara_boomerang));
				stacks.add(new ItemStack(fracture.blackbone_arm));
				stacks.add(new ItemStack(fracture.long_golden_sword));
				stacks.add(new ItemStack(fracture.nether_princess_arm));
			}).build();
			public static void log(Level level, String message) {
				LOGGER.log(level, "[" + MOD_NAME + "] " + message);
			}
}
