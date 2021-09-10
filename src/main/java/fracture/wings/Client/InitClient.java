package fracture.wings.Client;

import fracture.wings.WingItem;
import fracture.mixins.DyeColourAccessor;
import fracture.util.CameraSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.impl.client.rendering.ColorProviderRegistryImpl;
import net.fabricmc.loader.api.FabricLoader;
import fracture.wings.wings;

@Environment(EnvType.CLIENT)
public class InitClient implements ClientModInitializer{
    @Override
	public void onInitializeClient()
	{
		if(!FabricLoader.getInstance().isModLoaded("cameraoverhaul"))
			new CameraSystem();

		ColorProviderRegistryImpl.ITEM.register((stack, tintIndex) -> tintIndex == 0 ? ((DyeColourAccessor) (Object) ((WingItem) stack.getItem()).getPrimaryColour()).getColour() : ((DyeColourAccessor) (Object) ((WingItem) stack.getItem()).getSecondaryColour()).getColour(),
				wings.wings);
	}
}
