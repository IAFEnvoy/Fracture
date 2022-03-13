package net.iafenvoy.fracture;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.iafenvoy.fracture.Registry.Items;
import net.iafenvoy.fracture.Registry.Networking;
import net.iafenvoy.fracture.Utils.ResourcesUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Fracture implements ModInitializer {
  public static Logger LOGGER = LogManager.getLogger();
  public static final String MOD_ID = "fracture";
  public static final String MOD_NAME = "Fracture";

  @Override
  public void onInitialize() {
    log(Level.INFO, "Initializing");
    Items.Init();
    Networking.Init();
    ResourcesUtil.INSTANCE.loadAllRecipe();
  }

  public static final ItemGroup _fracture = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "fracture"))
      .icon(() -> new ItemStack(Items.ceris_big_knief)).appendItems(stacks -> {
        stacks.add(new ItemStack(Items.saber));
        stacks.add(new ItemStack(Items.patrick_halberd));
        stacks.add(new ItemStack(Items.dancer_arm));
        stacks.add(new ItemStack(Items.ceris_big_knief));
        stacks.add(new ItemStack(Items.ciara_karambit));
        stacks.add(new ItemStack(Items.blackbone_arm));
        stacks.add(new ItemStack(Items.long_golden_sword));
        stacks.add(new ItemStack(Items.nether_princess_arm));
      }).build();

  public static void log(Level level, String message) {
    LOGGER.log(level, "[" + MOD_NAME + "] " + message);
  }
}
