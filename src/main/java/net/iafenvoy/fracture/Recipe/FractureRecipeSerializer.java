package net.iafenvoy.fracture.Recipe;

import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FractureRecipeSerializer implements RecipeSerializer<FractureRecipe> {

  public static final FractureRecipeSerializer INSTANCE = new FractureRecipeSerializer();
  // This will be the "type" field in the json
  public static final Identifier ID = new Identifier("fracture:nine_nine_recipe");

  @Override
  public FractureRecipe read(Identifier id, JsonObject json) {
    try {
      Ingredient[][] input = new Ingredient[9][9];
      JsonArray map = json.getAsJsonArray("map");
      HashMap<Character, String> keyMap = new HashMap<Character, String>();
      JsonArray keys = json.getAsJsonArray("keys");
      for (JsonElement jsonElement : keys)
        keyMap.put(jsonElement.getAsJsonObject().get("name").getAsCharacter(),
            jsonElement.getAsJsonObject().get("item").getAsString());
      for (int i = 0; i < 9; i++) {
        JsonArray row = map.get(i).getAsJsonArray();
        for (int j = 0; j < 9; j++) {
          Character itemKey = row.getAsString().charAt(j);
          if (itemKey == ' ')
            input[i][j] = Ingredient.EMPTY;
          else if (keyMap.containsKey(itemKey))
            input[i][j] = Ingredient.ofItems(Registry.ITEM.get(new Identifier(keyMap.get(itemKey))));
          else
            input[i][j] = Ingredient.EMPTY;
        }
      }
      ItemStack output = new ItemStack(Registry.ITEM.get(new Identifier(json.get("output").getAsString())));
      return new FractureRecipe(id, input, output);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public FractureRecipe read(Identifier id, PacketByteBuf buf) {
    Ingredient[][] inputs = new Ingredient[9][9];
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        inputs[i][j] = Ingredient.fromPacket(buf);
    ItemStack output = buf.readItemStack();
    return new FractureRecipe(id, inputs, output);
  }

  @Override
  public void write(PacketByteBuf buf, FractureRecipe recipe) {
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        recipe.getInput(i, j).write(buf);
    buf.writeItemStack(recipe.getOutput());
  }
}
