package fracture.wings.models;

import dev.emi.trinkets.api.TrinketsApi;
import fracture.wings.wings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class WingsFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
	private final ElytraWingModel<T> leftWing = new ElytraWingModel<>(false);
	private final ElytraWingModel<T> rightWing = new ElytraWingModel<>(true);
	private static final Identifier TEXTURE_DYEABLE_ELYTRA = new Identifier("fracture", "textures/entity/wings.png");

	public WingsFeatureRenderer(FeatureRendererContext<T, M> context) {
		super(context);
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity,
			float f, float g, float h, float j, float k, float l) {
		ItemStack elytra = tryFindElytra(livingEntity);
		if (elytra != ItemStack.EMPTY) {
			matrixStack.push();
			matrixStack.translate(0.0D, 0.0D, 0.125D);
			renderSplit(matrixStack, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l, elytra);
			matrixStack.pop();
		}
	}

	public Identifier getElytraTexture(ItemStack stack, T entity) {
		return TEXTURE_DYEABLE_ELYTRA;
	}

	public void renderSplit(MatrixStack matrixStackIn, VertexConsumerProvider vertexConsumerProvider, int i,
			T livingEntity, float f, float g, float h, float j, float k, float l, ItemStack elytra) {
		renderSplitFallback(matrixStackIn, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l, elytra, leftWing);
		renderSplitFallback(matrixStackIn, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l, elytra,
				rightWing);
	}

	public void renderSplitFallback(MatrixStack matrixStackIn, VertexConsumerProvider vertexConsumerProvider, int i,
			T livingEntity, float f, float g, float h, float j, float k, float l, ItemStack elytra,
			ElytraWingModel<T> wingIn) {
		Identifier elytraTexture;
		if (livingEntity instanceof AbstractClientPlayerEntity) {
			AbstractClientPlayerEntity abstractclientplayerentity = (AbstractClientPlayerEntity) livingEntity;
			if (abstractclientplayerentity.canRenderElytraTexture()
					&& abstractclientplayerentity.getElytraTexture() != null) {
				elytraTexture = abstractclientplayerentity.getElytraTexture();
			} else if (abstractclientplayerentity.canRenderCapeTexture()
					&& abstractclientplayerentity.getCapeTexture() != null
					&& abstractclientplayerentity.isPartVisible(PlayerModelPart.CAPE)) {
				elytraTexture = abstractclientplayerentity.getCapeTexture();
			} else {
				elytraTexture = getElytraTexture(elytra, livingEntity);
			}
		} else {
			elytraTexture = getElytraTexture(elytra, livingEntity);
		}
		this.getContextModel().copyStateTo(wingIn);
		wingIn.setAngles(livingEntity, f, g, j, k, l);
		VertexConsumer glintConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider,
				RenderLayer.getArmorCutoutNoCull(elytraTexture), false, elytra.hasGlint());
		wingIn.render(matrixStackIn, glintConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	public ItemStack getColytraSubItem(ItemStack stack) {
		NbtCompound colytraChestTag = stack.getSubTag("colytra:ElytraUpgrade");
		if (colytraChestTag != null) {
			ItemStack elytraStack = ItemStack.fromNbt(colytraChestTag);
			if (elytraStack.getItem() == wings.wings) {
				return elytraStack;
			}
		}
		return ItemStack.EMPTY;
	}

	public ItemStack tryFindElytra(LivingEntity entity) {
		ItemStack elytra = entity.getEquippedStack(EquipmentSlot.CHEST);
		elytra = getColytraSubItem(elytra);
		if (elytra != ItemStack.EMPTY) {
			return elytra;
		}
		if (FabricLoader.getInstance().isModLoaded("trinkets") && entity instanceof PlayerEntity) {
			elytra = TrinketsApi.getTrinketComponent((PlayerEntity) entity).getStack("chest", "cape");
		}
		if (elytra.getItem() == wings.wings) {
			return elytra;
		}
		return ItemStack.EMPTY;
	}
}
