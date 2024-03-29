package iafenvoy.fracture.Items.Capes;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class CapeRender<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private final CapeModel<T> model = new CapeModel<>();

    public CapeRender(FeatureRendererContext<T, M> context) {
        super(context);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        if (livingEntity.isSpectator() || livingEntity.isInvisible() || !livingEntity.isAlive())
            return;
        if (livingEntity instanceof PlayerEntity) {
            Item item = livingEntity.getEquippedStack(EquipmentSlot.CHEST).getItem();
            if (!(item instanceof CapeItem))
                return;
            CapeItem cape = (CapeItem) item;
            if (!CapeItem.INSTANCE.contains(cape))
                return;
            PlayerEntity player = (PlayerEntity) livingEntity;
            matrixStack.push();

            // if (livingEntity.getEquippedStack(EquipmentSlot.CHEST).isEmpty())
            if (livingEntity.isInSneakingPose())
                matrixStack.translate(0.0D, 0.1D, 0.115D);
            else
                matrixStack.translate(0.0D, 0.0D, 0.125D);
            // else if (livingEntity.isInSneakingPose())
            // matrixStack.translate(0.0D, 0.1D, 0.125D);
            // else
            // matrixStack.translate(0.0D, 0.0D, 0.225D);

            final double d = MathHelper.lerp(h, player.prevCapeX, player.capeX) - MathHelper.lerp(h, player.prevX, player.getX());
            final double e = MathHelper.lerp(h, player.prevCapeY, player.capeY) - MathHelper.lerp(h, player.prevY, player.getY());
            final double m = MathHelper.lerp(h, player.prevCapeZ, player.capeZ) - MathHelper.lerp(h, player.prevZ, player.getZ());
            final float n = player.prevBodyYaw + (player.bodyYaw - player.prevBodyYaw);
            final double o = MathHelper.sin(n * 0.017453292F);
            final double p = -MathHelper.cos(n * 0.017453292F);
            float q = (float) e * 10.0F;
            q = MathHelper.clamp(q, -6.0F, 32.0F);
            float r = (float) (d * o + m * p) * 100.0F;
            r = MathHelper.clamp(r, 0.0F, 150.0F);
            float s = (float) (d * p - m * o) * 100.0F;
            s = MathHelper.clamp(s, -20.0F, 20.0F);
            if (r < 0.0F)
                r = 0.0F;

            final float t = MathHelper.lerp(h, player.prevStrideDistance, player.strideDistance);
            q += MathHelper.sin(MathHelper.lerp(h, player.prevHorizontalSpeed, player.horizontalSpeed) * 6.0F) * 32.0F * t;
            if (player.isInSneakingPose())
                q += 25.0F;

            matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
            matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
            matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));
            VertexConsumer consumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider, RenderLayer.getArmorCutoutNoCull(cape.getTexture()), false, false);
            model.render(matrixStack, consumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.pop();
        }
    }
}