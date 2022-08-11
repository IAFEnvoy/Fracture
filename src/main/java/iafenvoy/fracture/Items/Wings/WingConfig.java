package iafenvoy.fracture.Items.Wings;

public class WingConfig {
    public static boolean armourSlows = true;
    public static float maxSlowedMultiplier = 3F;
    public static float wingsSpeed = 0.05F;
    public static int wingsDurability = 60;
    public static float wingsAcceleration = 0.05F;
    public static float exhaustionAmount = 0.05F;
    public static float rollAmount = 1.0F;

    public WingConfig(boolean armourSlows, float maxSlowedMultiplier, float wingsSpeed, int wingsDurability,
                      float wingsAcceleration, float exhaustionAmount, float rollAmount) {
        WingConfig.armourSlows = armourSlows;
        WingConfig.maxSlowedMultiplier = maxSlowedMultiplier;
        WingConfig.wingsSpeed = wingsSpeed;
        WingConfig.wingsDurability = wingsDurability;
        WingConfig.wingsAcceleration = wingsAcceleration;
        WingConfig.exhaustionAmount = exhaustionAmount;
        WingConfig.rollAmount = rollAmount;
    }

    public WingConfig() {

    }
}
