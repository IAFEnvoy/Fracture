package fracture.wings;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import top.theillusivec4.caelus.api.CaelusApi;

import java.util.UUID;

import fracture.fracture;

public class WingItem extends TrinketItem
{
	public static boolean armourSlows = true;
	public static float maxSlowedMultiplier = 3F;
	public static float wingsSpeed = 0.05F;
	public static int wingsDurability = 60;
	public static float wingsAcceleration = 0.05F;
	public static float exhaustionAmount = 0.05F;
	public static float rollAmount = 1.0F;

	private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
	private ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
	private DyeColor primaryColour;
	private DyeColor secondaryColour;
	private WingType wingType;
	private boolean shouldSlowfall;
	private static final Tag<Item> FREE_FLIGHT = TagRegistry.item(new Identifier(fracture.MOD_ID, "free_flight"));
	private static final Tag<Item> MELTS = TagRegistry.item(new Identifier(fracture.MOD_ID, "melts"));
	/**
	 * The speed the wings allow the player to travel at. Default: 0.05D.
	 */
	private double speed;
	/**
	 * The speed at which the player will accelerate. Also controls turn radius. Default 0.05D.
	 */
	private double acceleration;

	/**
	 * @param speed The speed the wings allow the player to travel at. Default: 0.05D.
	 * @param acceleration The speed at which the player will accelerate. Also controls turn radius. Default 0.05D.
	 */
	public WingItem(double speed, double acceleration, DyeColor primaryColour, DyeColor secondaryColour, WingType wingType)
	{
		super(new Item.Settings().group(fracture._fracture).maxCount(1).maxDamage(wingsDurability).rarity(wingType == WingType.UNIQUE ? Rarity.EPIC : Rarity.RARE));
		this.builder.put(CaelusApi.ELYTRA_FLIGHT, new EntityAttributeModifier(UUID.fromString("7d9704a0-383f-11eb-adc1-0242ac120002"),
				"Flight", 1, EntityAttributeModifier.Operation.ADDITION));
		this.attributeModifiers = this.builder.build();
		this.speed = speed;
		this.acceleration = acceleration;
		this.primaryColour = primaryColour;
		this.secondaryColour = secondaryColour;
		this.wingType = wingType;
	}

	/**
	 * The default constructor. It sets {@link WingItem#speed} and {@link WingItem#acceleration} to 0.05D.
	 */
	public WingItem(DyeColor primaryColour, DyeColor secondaryColour, WingType wingType)
	{
		super(new Item.Settings().group(fracture._fracture).maxCount(1).maxDamage(wingsDurability).rarity(wingType == WingType.UNIQUE ? Rarity.EPIC : Rarity.RARE));
		this.builder.put(CaelusApi.ELYTRA_FLIGHT, new EntityAttributeModifier(UUID.fromString("7d9704a0-383f-11eb-adc1-0242ac120002"),
				"Flight", 1, EntityAttributeModifier.Operation.ADDITION));
		this.attributeModifiers = this.builder.build();
		this.speed = wingsSpeed;
		this.acceleration = wingsAcceleration;
		this.primaryColour = primaryColour;
		this.secondaryColour = secondaryColour;
		this.wingType = wingType;
	}

	@Override
	public void tick(PlayerEntity player, ItemStack stack)
	{
		if(player.getHungerManager().getFoodLevel() <= 6)
			return;

		if(player.isFallFlying())
		{
			if(player.forwardSpeed > 0)
				applySpeed(player);

			if(player.isSneaking())
				stopFlying(player);

			if(player.getPos().y > player.world.getHeight() + 64 && player.age % 20 == 0 && MELTS.contains(this))
				stack.damage(1, player, (p) -> p.sendEquipmentBreakStatus(EquipmentSlot.CHEST));
		}
		else
		{
			if(player.isOnGround() || player.isTouchingWater())
				shouldSlowfall = false;

			if(shouldSlowfall)
			{
				player.fallDistance = 0F;
				player.setVelocity(player.getVelocity().x, -0.4, player.getVelocity().z);
			}
		}
	}

	@Override
	public boolean canWearInSlot(String group, String slot)
	{
		return group.equals(SlotGroups.CHEST) && slot.equals(Slots.CAPE);
	}

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getTrinketModifiers(String group, String slot, UUID uuid, ItemStack stack)
	{
		return this.attributeModifiers;
	}

	public DyeColor getPrimaryColour()
	{
		return this.primaryColour;
	}

	public DyeColor getSecondaryColour()
	{
		return this.secondaryColour;
	}

	public WingType getWingType()
	{
		return this.wingType;
	}

	public void stopFlying(PlayerEntity player)
	{
		shouldSlowfall = true;
		player.stopFallFlying();
	}

	public void applySpeed(PlayerEntity player)
	{
		shouldSlowfall = false;
		Vec3d rotation = player.getRotationVector();
		Vec3d velocity = player.getVelocity();
		float modifier = armourSlows ? MathHelper.clamp(player.getArmor() / 10F, 1F, maxSlowedMultiplier) : 1F;

		player.setVelocity(velocity.add(rotation.x * (speed / modifier) + (rotation.x * 1.5D - velocity.x) * acceleration,
										rotation.y * (speed / modifier) + (rotation.y * 1.5D - velocity.y) * acceleration,
										rotation.z * (speed / modifier) + (rotation.z * 1.5D - velocity.z) * acceleration));

		if(!FREE_FLIGHT.contains(this) && !player.isCreative())
			DeleteHungerMessage.send();
	}

	public enum WingType
	{
		FEATHERED, DRAGON, MECHANICAL_FEATHERED, MECHANICAL_LEATHER, LIGHT, UNIQUE
	}
}
