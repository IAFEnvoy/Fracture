package iafenvoy.fracture.Items.Weapons;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class FractureMaterial implements ToolMaterial {
    public static final FractureMaterial INSTANCE = new FractureMaterial(0, 2000, 30, ItemStack.EMPTY);
    private float attackDamage;
    private int durability;
    private int enchantability;
    private Ingredient repairIngredient;

    public FractureMaterial(float attackDamage, int durability, int enchantability, ItemStack repairItem) {
        this.attackDamage = attackDamage;
        this.durability = durability;
        this.enchantability = enchantability;
        this.repairIngredient = Ingredient.ofStacks(repairItem);
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 1.0F;
    }

    @Override
    public int getMiningLevel() {
        return 1;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    public FractureMaterial setEnchantability(int enchantability) {
        this.enchantability = enchantability;
        return this;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    public FractureMaterial setAttackDamage(float attackDamage) {
        this.attackDamage = attackDamage;
        return this;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    public FractureMaterial setDurability(int durability) {
        this.durability = durability;
        return this;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }

    public FractureMaterial setRepairIngredient(ItemStack repairItem) {
        this.repairIngredient = Ingredient.ofStacks(repairItem);
        return this;
    }
}
