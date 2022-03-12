package net.iafenvoy.fracture.Registry;

import net.iafenvoy.fracture.Fracture;
import net.iafenvoy.fracture.Recipe.FractureRecipe;
import net.iafenvoy.fracture.Recipe.FractureRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Recipe {
  public static void Init() {
    Registry.register(Registry.RECIPE_SERIALIZER, FractureRecipeSerializer.ID, FractureRecipeSerializer.INSTANCE);
    Registry.register(Registry.RECIPE_TYPE, new Identifier(Fracture.MOD_ID, FractureRecipe.Type.ID),
        FractureRecipe.Type.INSTANCE);
  }
}
