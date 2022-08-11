package iafenvoy.fracture.Items.Wings;

import com.google.common.collect.ImmutableMultimap;
import iafenvoy.fracture.Fracture;
import iafenvoy.fracture.Utils.Enum.Teams;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import top.theillusivec4.caelus.api.CaelusApi;

import java.util.List;
import java.util.UUID;

public class WingItems extends ElytraItem {
    private static final Tag<Item> FREE_FLIGHT = TagRegistry.item(new Identifier(Fracture.MOD_ID, "free_flight"));
    private final Teams team;
    private final ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
    private final String name;
    private final double speed;
    private final double acceleration;

    public WingItems(String name, Settings settings, Teams team) {
        super(settings);
        this.builder.put(CaelusApi.ELYTRA_FLIGHT,
                new EntityAttributeModifier(UUID.fromString("7d9704a0-383f-11eb-adc1-0242ac120002"), "Flight", 1,
                        EntityAttributeModifier.Operation.ADDITION));
        this.speed = WingConfig.wingsSpeed;
        this.acceleration = WingConfig.wingsAcceleration;
        this.name = name;
        this.team = team;
        Registry.register(Registry.ITEM, new Identifier(Fracture.MOD_ID, name), this);
    }

    public String getWingName() {
        return this.name;
    }

    public void stopFlying(PlayerEntity player) {
        player.stopFallFlying();
    }

    public void applySpeed(PlayerEntity player) {
        Vec3d rotation = player.getRotationVector();
        Vec3d velocity = player.getVelocity();
        float modifier = WingConfig.armourSlows
                ? MathHelper.clamp(player.getArmor() / 10F, 1F, WingConfig.maxSlowedMultiplier)
                : 1F;

        player.setVelocity(
                velocity.add(rotation.x * (speed / modifier) + (rotation.x * 1.5D - velocity.x) * acceleration,
                        rotation.y * (speed / modifier) + (rotation.y * 1.5D - velocity.y) * acceleration,
                        rotation.z * (speed / modifier) + (rotation.z * 1.5D - velocity.z) * acceleration));

        if (!FREE_FLIGHT.contains(this) && !player.isCreative())
            DeleteHungerMessage.send();
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("fracture.team." + team.getTranslateKey() + ".tooltip"));
    }
}
