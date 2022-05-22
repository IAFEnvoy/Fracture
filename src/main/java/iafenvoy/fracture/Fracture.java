package iafenvoy.fracture;

import net.fabricmc.api.ModInitializer;

import iafenvoy.fracture.Registry.Cape;
import iafenvoy.fracture.Registry.ItemGroups;
import iafenvoy.fracture.Registry.Items;
import iafenvoy.fracture.Registry.Networking;
import iafenvoy.fracture.Utils.LogUtil;
import iafenvoy.fracture.Utils.ResourcesUtil;

public class Fracture implements ModInitializer {
  public static final String MOD_ID = "fracture";
  public static final String MOD_NAME = "Fracture";

  @Override
  public void onInitialize() {
    LogUtil.info("Initializing...");
    Items.Init();
    Cape.Init();
    Networking.Init();
    ResourcesUtil.INSTANCE.loadAllRecipe();
    new ItemGroups().getClass();
  }
}
