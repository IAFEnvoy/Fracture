package iafenvoy.fracture.Recipe;

import iafenvoy.fracture.Registry.Items;
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
    if (id != null) {
      this.input[4][4] = Items.CRAFTING_TABLE;
      INSTANCE.add(this);
    }
  }

  public boolean matches(Block[][] input) {
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (input[i][j] != this.input[i][j])
          return false;
    return true;
  }

  public ItemStack getOutput() {
    return output;
  }

  public Identifier getId() {
    return id;
  }

  public static Block[][] rotate(Block[][] input) {
    Block[][] output = new Block[9][9];
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        output[i][j] = input[8 - j][i];
    return output;
  }

  public static FractureRecipe hasAvailable(Block[][] input) {
    for (FractureRecipe recipe : INSTANCE){
      if (recipe.matches(input))
        return recipe;
      if(recipe.matches(rotate(input)))
        return recipe;
      if(recipe.matches(rotate(rotate(input))))
        return recipe;
      if(recipe.matches(rotate(rotate(rotate(input)))))
        return recipe;
      }
    return EMPTY;
  }
}
