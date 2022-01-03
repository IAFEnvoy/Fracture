package fracture.wings;

import fracture.fracture;
import fracture.items.items;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class WingsFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
  private WingEntityModel<T> lwingModel = new WingEntityModel<>();
  private WingEntityModel<T> rwingModel = new WingEntityModel<>();

  public WingsFeatureRenderer(FeatureRendererContext<T, M> context) {
    super(context);
  }

  @Override
  public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle,
      float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
    if (entity instanceof PlayerEntity) {
      if (entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == items.wings) {
        renderTexture(matrices, vertexConsumers,
            light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);

      }
    }
  }

  private void renderTexture(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity,
      float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
    lwingModel = new TechWingsModel<>(true);
    rwingModel = new TechWingsModel<>(false);

    Identifier layer1 = new Identifier(fracture.MOD_ID, "textures/wing/tech_wings.png");

    matrices.push();
    matrices.translate(0.0D, 0.0D, 0.125D);
    this.getContextModel().copyStateTo(this.lwingModel);
    this.lwingModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.renderWings(matrices, vertexConsumers, layer1, light, true);
    matrices.pop();

    matrices.push();
    matrices.translate(0.0D, 0.0D, 0.125D);
    this.getContextModel().copyStateTo(this.rwingModel);
    this.rwingModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.renderWings(matrices, vertexConsumers, layer1, light, false);
    matrices.pop();
  }

  private void renderWings(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier layerName,
      int light, boolean left) {
    VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers,
        RenderLayer.getEntityTranslucent(layerName), false, false);
    if (left)
      this.lwingModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 0, 0, 0, 0);
    else
      this.rwingModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 0, 0, 0, 0);
  }
}
