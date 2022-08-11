package iafenvoy.fracture.Utils;

import com.google.gson.JsonParser;
import iafenvoy.fracture.Recipe.FractureRecipe;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourcesUtil {
    public static final ResourcesUtil INSTANCE = new ResourcesUtil();

    public void loadAllRecipe() {
        try {
            InputStream listFile = this.getClass().getResourceAsStream("/data/recipes.txt");
            assert listFile != null;
            String[] list = FileUtils.readByLines(new InputStreamReader(listFile)).split("\n");
            for (String s : list) {
                InputStream recipeFile = this.getClass().getResourceAsStream("/data/fracture_recipes/" + s + ".json");
                assert recipeFile != null;
                readRecipe(s, new InputStreamReader(recipeFile));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readRecipe(String name, InputStreamReader input) throws IOException {
        String data = FileUtils.readByLines(input);
        FractureRecipe.read(name.toLowerCase(), new JsonParser().parse(data).getAsJsonObject());
    }
}
