package fracture.arms;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class wing_material implements ArmorMaterial {
	private static final int Enchantability=20;
	private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
    private static final int[] PROTECTION_VALUES = new int[] {3, 6, 8, 3}; 

	@Override
	public int getDurability(EquipmentSlot slot) {
		return BASE_DURABILITY[slot.getEntitySlotId()] * Enchantability;
	}

	@Override
	public int getEnchantability() {
		return Enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return null;
	}

	@Override
	public float getKnockbackResistance() {
		return 0;
	}

	@Override
	public String getName() {
		return "fire_materials";
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return PROTECTION_VALUES[slot.getEntitySlotId()];
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofStacks(ItemStack.EMPTY);
	}

	@Override
	public float getToughness() {
		return 0;
	}

}
