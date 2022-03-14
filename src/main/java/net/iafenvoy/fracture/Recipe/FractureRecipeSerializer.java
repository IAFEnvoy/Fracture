package net.iafenvoy.fracture.Recipe;

import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FractureRecipeSerializer {
  public static FractureRecipe read(Identifier id, JsonObject json) {
    try {
      Block[][] input = new Block[9][9];
      JsonArray map = json.getAsJsonArray("map");
      HashMap<Character, String> keyMap = new HashMap<Character, String>();
      JsonArray keys = json.getAsJsonArray("keys");
      for (JsonElement jsonElement : keys)
        keyMap.put(jsonElement.getAsJsonObject().get("name").getAsCharacter(),
            jsonElement.getAsJsonObject().get("item").getAsString());
      for (int i = 0; i < 9; i++) {
        JsonElement row = map.get(i);
        for (int j = 0; j < 9; j++) {
          Character itemKey = row.getAsString().charAt(j);
          if (itemKey == ' ')
            input[i][j] = Blocks.AIR;
          else if (keyMap.containsKey(itemKey))
            input[i][j] = Registry.BLOCK.get(new Identifier(keyMap.get(itemKey)));
          else
            input[i][j] = Blocks.AIR;
        }
      }
      ItemStack output = new ItemStack(Registry.ITEM.get(new Identifier(json.get("output").getAsString())));
      return new FractureRecipe(id, input, output);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
