package fracture.wings.Client;

import fracture.wings.CustomizableElytraItem;
import fracture.wings.WingItem;
// import fracture.mixins.DyeColourAccessor;
import fracture.util.CameraSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.impl.client.rendering.ColorProviderRegistryImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;
import fracture.wings.wings;
import fracture.fracture;

@Environment(EnvType.CLIENT)
public class InitClient implements ClientModInitializer{
    @Override
	public void onInitializeClient()
	{
		// if(!FabricLoader.getInstance().isModLoaded("cameraoverhaul"))
		// 	new CameraSystem();

		// ColorProviderRegistryImpl.ITEM.register((stack, tintIndex) -> tintIndex == 0 ? ((DyeColourAccessor) (Object) ((WingItem) stack.getItem()).getPrimaryColour()).getColour() : ((DyeColourAccessor) (Object) ((WingItem) stack.getItem()).getSecondaryColour()).getColour(),
		// 		wings.wings);
		FabricModelPredicateProviderRegistry.register(wings.wings, new Identifier(fracture.MOD_ID, "broken_elytra"), ((stack, world, entity) ->
        {
            return stack.getItem() == wings.wings && CustomizableElytraItem.isUsable(stack) ? 0.0F : 1.0F;
        }));

        ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
        {
            return tintIndex > 0 ? -1 : ((CustomizableElytraItem) stack.getItem()).getColor(stack);
        }, wings.wings);

        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) ->
        {
            for (BannerPattern pattern : BannerPattern.values())
            {
                Identifier textureIdentifier = CustomizableElytraItem.getTextureLocation(pattern);
                registry.register(textureIdentifier);
            }
        }));
	}
}
