package net.iafenvoy.fracture;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.iafenvoy.fracture.Registry.Cape;
import net.iafenvoy.fracture.Registry.Items;
import net.iafenvoy.fracture.Registry.Networking;
import net.iafenvoy.fracture.Utils.ResourcesUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Fracture implements ModInitializer {
  public static final Logger LOGGER = LogManager.getLogger();
  public static final String MOD_ID = "fracture";
  public static final String MOD_NAME = "Fracture";

  @Override
  public void onInitialize() {
    LOGGER.info("Initializing...");
    Items.Init();
    Cape.Init();
    Networking.Init();
    ResourcesUtil.INSTANCE.loadAllRecipe();
  }

  public static final ItemGroup _fracture = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "fracture"))
      .icon(() -> new ItemStack(Items.end_greatsword)).appendItems(stacks -> {
        stacks.add(new ItemStack(Items.CRAFTING_TABLE_ITEM));
        stacks.add(new ItemStack(Items.end_laser_arm_sword));
        stacks.add(new ItemStack(Items.ser_patricks_pike));
        stacks.add(new ItemStack(Items.end_greatsword));
        stacks.add(new ItemStack(Items.end_karambit));
        stacks.add(new ItemStack(Items.end_handled_greatsword));
        stacks.add(new ItemStack(Items.nether_greatsword));
        stacks.add(new ItemStack(Items.golden_longsword));
        stacks.add(new ItemStack(Items.ruby_pike));
        stacks.add(new ItemStack(Items.tech_wing));
        stacks.add(new ItemStack(Items.dragon_wing));

        stacks.add(new ItemStack(Cape.human));
        stacks.add(new ItemStack(Cape.nether));
        stacks.add(new ItemStack(Cape.undead));
        stacks.add(new ItemStack(Cape.end));
      }).build();
}
