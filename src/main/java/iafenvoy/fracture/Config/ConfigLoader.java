package iafenvoy.fracture.Config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import iafenvoy.fracture.Utils.FileUtils;
import iafenvoy.fracture.Utils.LogUtil;
import java.util.HashMap;
import java.util.Map.Entry;

public class ConfigLoader {
  private static final HashMap<String, String> data = new HashMap<>();
  private static final String configPath = "./config/fracture/config.json";

  public static void loadConfig() {
    LogUtil.info("Start to load Fracture config");
    data.clear();
    try {
      JsonObject jsonObject = new JsonParser().parse(FileUtils.readFile(configPath)).getAsJsonObject();
      for (Entry<String, JsonElement> key : jsonObject.entrySet())
        data.put(key.getKey(), key.getValue().getAsString());
      LogUtil.info("Succeed to load config");
    } catch (Exception e) {
      LogUtil.error("Fail to load config, reason : " + e.getMessage());
    }
  }

  public static void saveConfig() {
    LogUtil.info("Start to save Fracture config");
    try {
      JsonObject object = new JsonObject();
      for (Entry<String, String> entry : data.entrySet())
        object.addProperty(entry.getKey(), entry.getValue());
      FileUtils.saveFile(configPath, object.toString());
      LogUtil.info("Succeed to save config");
    } catch (Exception e) {
      LogUtil.error("Fail to save config, reason : " + e.getMessage());
    }
  }

  public static String get(String key) {
    return data.get(key);
  }

  public static void set(String key, String value) {
    data.put(key, value);
  }
}
