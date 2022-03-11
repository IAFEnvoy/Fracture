package net.iafenvoy.fracture.Items.Wings.Models;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;

public class WingEntityModel<T extends LivingEntity> extends AnimalModel<T> {
  public final ModelPart rightWing;
  public final ModelPart leftWing;

  public WingEntityModel() {
    textureWidth = 64;
    textureHeight = 64;

    rightWing = new ModelPart(this);
    rightWing.setPivot(0.0F, 5.0F, 0.0F);
    // rightWing.setTextureOffset(22, 0).addCuboid(-8.0F, -1.0F, -1.0F, 10.0F,
    // 20.0F, 2.0F, 0.0F, true);

    leftWing = new ModelPart(this);
    leftWing.setPivot(0.0F, 5.0F, 0.0F);
    // leftWing.setTextureOffset(22, 22).addCuboid(-2.0F, -1.0F, -1.0F, 10.0F,
    // 20.0F, 2.0F, 0.0F, false);
  }

  @Override
  public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw,
      float headPitch) {

    if (entity.isFallFlying()) {
      this.rightWing.pitch = 0.3F;
      this.rightWing.roll = -0.7F;
      this.rightWing.yaw = 0.0F;
      this.rightWing.pivotX = 7;
      this.rightWing.pivotY = -3;
    } else {
      this.rightWing.pitch = 0.3F;
      this.rightWing.roll = 0.1F;
      this.rightWing.yaw = 0.0F;
      this.rightWing.pivotX = 9;
      this.rightWing.pivotY = 3;
    }

    this.leftWing.pivotX = -this.rightWing.pivotX;
    this.leftWing.yaw = -this.rightWing.yaw;
    this.leftWing.pivotY = this.rightWing.pivotY;
    this.leftWing.pitch = this.rightWing.pitch;
    this.leftWing.roll = -this.rightWing.roll;
  }

  @Override
  protected Iterable<ModelPart> getHeadParts() {
    return ImmutableList.of();
  }

  @Override
  protected Iterable<ModelPart> getBodyParts() {
    return ImmutableList.of(this.rightWing, this.leftWing);
  }
}