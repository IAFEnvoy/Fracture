package iafenvoy.fracture.Items.Wings;

import iafenvoy.fracture.Fracture;
import iafenvoy.fracture.Items.Wings.Models.LeatherWingModel;
import iafenvoy.fracture.Items.Wings.Models.TechWingsModel;
import iafenvoy.fracture.Items.Wings.Models.WingEntityModel;
import iafenvoy.fracture.Registry.Items;
import iafenvoy.fracture.Utils.Color4f;
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
    Identifier layer1 = null, layer2 = null;
    Color4f lwingcolorl1 = null, lwingcolorl2 = null, rwingcolorl1 = null, rwingcolorl2 = null;

    if (entity instanceof PlayerEntity) {
      if (entity.getEquippedStack(EquipmentSlot.CHEST).getItem().equals(Items.tech_wing)) {
        lwingModel = new TechWingsModel<>(true);
        rwingModel = new TechWingsModel<>(false);
        layer1 = new Identifier(Fracture.MOD_ID, "textures/wing/tech_wings.png");
        layer2 = new Identifier(Fracture.MOD_ID, "textures/wing/tech_wings_2.png");
        lwingcolorl1 = new Color4f(0, 255, 255, 127);
        lwingcolorl2 = new Color4f(255, 255, 255, 240);
        rwingcolorl1 = new Color4f(0, 255, 255, 127);
        rwingcolorl2 = new Color4f(255, 255, 255, 240);
      } else if (entity.getEquippedStack(EquipmentSlot.CHEST).getItem().equals(Items.dragon_wing)) {
        lwingModel = new LeatherWingModel<>(true);
        rwingModel = new LeatherWingModel<>(false);
        layer1 = new Identifier(Fracture.MOD_ID, "textures/wing/dragon_wings.png");
        layer2 = new Identifier(Fracture.MOD_ID, "textures/wing/dragon_wings_2.png");
        lwingcolorl1 = new Color4f(255, 255, 255, 240);
        lwingcolorl2 = new Color4f(31, 31, 31, 240);
        rwingcolorl1 = new Color4f(255, 255, 255, 240);
        rwingcolorl2 = new Color4f(31, 31, 31, 240);
      } else
        return;
    } else
      return;

    matrices.push();
    matrices.translate(0.0D, 0.0D, 0.125D);
    this.getContextModel().copyStateTo(this.lwingModel);
    this.lwingModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.renderWings(matrices, vertexConsumers, layer2, light, lwingcolorl2, true);
    this.renderWings(matrices, vertexConsumers, layer1, light, lwingcolorl1, true);
    matrices.pop();

    matrices.push();
    matrices.translate(0.0D, 0.0D, 0.125D);
    this.getContextModel().copyStateTo(this.rwingModel);
    this.rwingModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    this.renderWings(matrices, vertexConsumers, layer2, light, rwingcolorl2, false);
    this.renderWings(matrices, vertexConsumers, layer1, light, rwingcolorl1, false);
    matrices.pop();
  }

  private void renderWings(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier layerName,
      int light, Color4f color, boolean left) {
    VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers,
        RenderLayer.getEntityTranslucent(layerName), false, false);
    if (left)
      this.lwingModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color.r, color.g, color.b,
          color.a);
    else
      this.rwingModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color.r, color.g, color.b,
          color.a);
  }
}