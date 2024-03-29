package iafenvoy.fracture;

import iafenvoy.fracture.Registry.Cape;
import iafenvoy.fracture.Registry.ItemGroups;
import iafenvoy.fracture.Registry.Items;
import iafenvoy.fracture.Registry.Network;
import iafenvoy.fracture.Utils.LogUtil;
import iafenvoy.fracture.Utils.ResourcesUtil;
import net.fabricmc.api.ModInitializer;

public class Fracture implements ModInitializer {
    public static final String MOD_ID = "fracture";
    public static final String MOD_NAME = "Fracture";

    @Override
    public void onInitialize() {
        LogUtil.info("Initializing...");
        new Items().getClass();
        new Cape().getClass();
        Network.Init();
        ResourcesUtil.INSTANCE.loadAllRecipe();
        new ItemGroups().getClass();

        Commands.register();
    }
}
