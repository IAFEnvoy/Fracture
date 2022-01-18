package fracture.wings.WingsAPI;

import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;

import fracture.fracture;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import top.theillusivec4.caelus.api.CaelusApi;

public class WingItem extends ElytraItem {
	private ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
	private String name;
	private static final Tag<Item> FREE_FLIGHT = TagRegistry.item(new Identifier(fracture.MOD_ID, "free_flight"));
	/**
	 * The speed the wings allow the player to travel at. Default: 0.05D.
	 */
	private double speed;
	/**
	 * The speed at which the player will accelerate. Also controls turn radius.
	 * Default 0.05D.
	 */
	private double acceleration;

	/**
	 * The default constructor. It sets {@link WingItem#speed} and
	 * {@link WingItem#acceleration} to 0.05D.
	 */
	public WingItem(Rarity r, String name,Settings settings) {
		super(settings);
		this.builder.put(CaelusApi.ELYTRA_FLIGHT,
				new EntityAttributeModifier(UUID.fromString("7d9704a0-383f-11eb-adc1-0242ac120002"), "Flight", 1,
						EntityAttributeModifier.Operation.ADDITION));
		this.speed = WingConfig.wingsSpeed;
		this.acceleration = WingConfig.wingsAcceleration;
		this.name = name;
	}

	public WingItem(Rarity r, String name) {
		super(new Item.Settings().group(fracture._fracture).maxCount(1).maxDamage(WingConfig.wingsDurability).rarity(r));
		this.builder.put(CaelusApi.ELYTRA_FLIGHT,
				new EntityAttributeModifier(UUID.fromString("7d9704a0-383f-11eb-adc1-0242ac120002"), "Flight", 1,
						EntityAttributeModifier.Operation.ADDITION));
		this.speed = WingConfig.wingsSpeed;
		this.acceleration = WingConfig.wingsAcceleration;
		this.name = name;
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
}
