package net.iafenvoy.fracture.Recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class FractureRecipe {
  private static final List<FractureRecipe> INSTANCE = new ArrayList<FractureRecipe>();
  public static final FractureRecipe EMPTY = new FractureRecipe(null, null, null);
  private final Identifier id;
  private final Block[][] input;
  private final ItemStack output;

  public FractureRecipe(Identifier id, Block[][] input, ItemStack output) {
    this.id = id;
    this.input = input;
    this.output = output;
    INSTANCE.add(this);
  }

  public boolean matches(Block[][] input) {
    if (this == EMPTY)
      return false;
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++) {
        if (i == 4 && j == 4)
          continue;// middle block is not checked
        if (input[i][j] != this.input[i][j])
          return false;
      }
    return true;
  }

  public ItemStack getOutput() {
    return output;
  }

  public Identifier getId() {
    return id;
  }

  public static FractureRecipe hasAvailable(Block[][] input) {
    for (FractureRecipe recipe : INSTANCE)
      if (recipe.matches(input))
        return recipe;
    return EMPTY;
  }
}
