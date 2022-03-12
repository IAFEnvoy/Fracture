package net.iafenvoy.fracture.Recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class FractureRecipe implements Recipe<FractureCraftingInventory> {
  private final Ingredient[][] inputs;
  private final ItemStack output;
  private final Identifier id;

  public FractureRecipe(Identifier id, Ingredient[][] inputs, ItemStack output) {
    this.id = id;
    this.inputs = inputs;
    this.output = output;
  }

  public Ingredient getInput(int line, int column) {
    if (line >= 0 && line < this.inputs.length && column >= 0 && column < this.inputs[line].length)
      return this.inputs[line][column];
    else
      return Ingredient.EMPTY;
  }

  @Override
  public ItemStack craft(FractureCraftingInventory inventory) {
    return ItemStack.EMPTY;
  }

  @Override
  public boolean fits(int arg0, int arg1) {
    return false;
  }

  @Override
  public Identifier getId() {
    return id;
  }

  @Override
  public ItemStack getOutput() {
    return output;
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return FractureRecipeSerializer.INSTANCE;
  }

  @Override
  public RecipeType<?> getType() {
    return Type.INSTANCE;
  }

  @Override
  public boolean matches(FractureCraftingInventory inventory, World world) {
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (!getInput(j, i).test(inventory.getStack(i + j * 9)))
          return false;
    return true;
  }

  public static class Type implements RecipeType<FractureRecipe> {
    private Type() {
    }

    public static final Type INSTANCE = new Type();
    public static final String ID = "nine_nine_recipe";
  }
}
