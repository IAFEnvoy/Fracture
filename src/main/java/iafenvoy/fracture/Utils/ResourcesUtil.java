package iafenvoy.fracture.Utils;

import com.google.gson.JsonParser;
import iafenvoy.fracture.Fracture;
import iafenvoy.fracture.Recipe.FractureRecipeSerializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import net.minecraft.util.Identifier;

public class ResourcesUtil {
  public static final ResourcesUtil INSTANCE = new ResourcesUtil();

  public void loadAllRecipe() {
    try {
      InputStream listFile = this.getClass().getResourceAsStream("/data/recipes.txt");
      String[] list = FileUtils.readByLines(new InputStreamReader(listFile)).split("\n");
      for (String s : list) {
        InputStream recipeFile = this.getClass().getResourceAsStream("/data/fracture_recipes/" + s + ".json");
        readRecipe(s, new InputStreamReader(recipeFile));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void readRecipe(String name, InputStreamReader input) throws IOException {
    String data = FileUtils.readByLines(input);
    FractureRecipeSerializer.read(new Identifier(Fracture.MOD_ID, name.toLowerCase()),
        new JsonParser().parse(data).getAsJsonObject());
  }
}
