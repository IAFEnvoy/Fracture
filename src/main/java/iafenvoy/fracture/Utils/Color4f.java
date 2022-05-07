package iafenvoy.fracture.Utils;

public class Color4f {
  public float r,g,b,a;
  public Color4f(int r, int g, int b, int a) {
    this.r = r / 255.0F;
    this.g = g / 255.0F;
    this.b = b / 255.0F;
    this.a = a / 255.0F;
  }
}
