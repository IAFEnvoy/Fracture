package fracture.arms;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class fracture_material implements ToolMaterial{
	public static final fracture_material INSTANCE = new fracture_material();
	
	
	@Override
	public float getAttackDamage() {
		return 0;
	}

	@Override
	public int getDurability() {
		return 2000;
	}

	@Override
	public int getEnchantability() {
		return 30;
	}

	@Override
	public int getMiningLevel() {
		return 1;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return 1.0F;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(Items.NETHERITE_INGOT);
	}
    
}
