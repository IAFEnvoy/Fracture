package iafenvoy.fracture.Config;

import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import iafenvoy.fracture.Utils.FileUtils;

public class ConfigLoader {
  private static final HashMap<String, String> data = new HashMap<>();
  private static final String configPath = "./config/fracture/config.json";

  public static void loadConfig() {
    data.clear();
    try {
      JsonObject jsonObject = new JsonParser().parse(FileUtils.readFile(configPath)).getAsJsonObject();
      for (Entry<String, JsonElement> key : jsonObject.entrySet())
        data.put(key.getKey(), key.getValue().getAsString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void saveConfig() {
    try {
      JsonObject object = new JsonObject();
      for (Entry<String, String> entry : data.entrySet())
        object.addProperty(entry.getKey(), entry.getValue());
      FileUtils.saveFile(configPath, object.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String get(String key) {
    return data.get(key);
  }

  public static void set(String key, String value) {
    data.put(key, value);
  }
}
